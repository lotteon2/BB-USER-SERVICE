package com.bit.lotte.flower.user.social.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bit.lotte.flower.user.common.valueobject.UserId;
import com.bit.lotte.flower.user.social.entity.SocialUser;
import com.bit.lotte.flower.user.social.exception.SocialUserDomainException;
import com.bit.lotte.flower.user.social.mapper.SocialUserMapper;
import com.bit.lotte.flower.user.social.repository.FindSocialUserByLongIdService;
import com.bit.lotte.flower.user.social.repository.SocialUserJpaRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserWithdrawalServiceTest {

 private SoftDeleteStrategyService softDeleteStrategyService;
 @Mock
 private FindSocialUserByLongIdService findSocialUserByLongIdService;
 @Mock
 private SocialUserJpaRepository socialUserJpaRepository;


 @BeforeEach
 void init() {
  softDeleteStrategyService = new SoftDeleteStrategyService(findSocialUserByLongIdService,
      socialUserJpaRepository);
 }

 @DisplayName("유저 존재하지 않을 때 Throw SocialUserDomainException")
 @Test
 void UserWithdrawal_WhenUserIsNotExisted_ThrowSocialUserDomainException() {
  when(findSocialUserByLongIdService.findUserElseThrowError(1L)).thenThrow(
      SocialUserDomainException.class);

  assertThrows(SocialUserDomainException.class, () -> {
   softDeleteStrategyService.userWithdrawal(new UserId(1L));
  });
 }

 @DisplayName("유저 존재할 때 유저 회원탈퇴")
 @Test
 void UserWithdrawal_WhenUserIsExist_UserIsDeletedTrue() {
  SocialUser socialUserNotDeleted = SocialUser.builder().isDeleted(false).id(1L).oauthId(1L)
      .build();

  when(findSocialUserByLongIdService.findUserElseThrowError(1L)).thenReturn(socialUserNotDeleted);
  when(socialUserJpaRepository.findAllByOauthId(1L)).thenReturn(List.of(socialUserNotDeleted));

  softDeleteStrategyService.userWithdrawal(new UserId(1L));

    ArgumentCaptor<SocialUser> captor = ArgumentCaptor.forClass(SocialUser.class);

  verify(socialUserJpaRepository, times(1)).save(captor.capture());

  SocialUser capturedUser = captor.getValue();

  assertTrue(capturedUser.getIsDeleted());


 }

}

