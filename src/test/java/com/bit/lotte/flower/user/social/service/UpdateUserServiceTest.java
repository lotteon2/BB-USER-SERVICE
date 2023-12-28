package com.bit.lotte.flower.user.social.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bit.lotte.flower.user.social.entity.SocialUser;
import com.bit.lotte.flower.user.social.exception.SocialUserDomainException;
import com.bit.lotte.flower.user.social.repository.FindSocialUserByLongIdService;
import com.bit.lotte.flower.user.social.repository.SocialUserJpaRepository;
import java.util.Optional;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateUserServiceTest {


  @Mock
  private SocialUserJpaRepository socialUserJpaRepository;
  @Mock
  private FindSocialUserByLongIdService findSocialUserByLongIdService;
  private SocialUserUpdateServiceImpl socialUpdateUserService;


  @BeforeEach
  public void setUp() {
    socialUpdateUserService = new SocialUserUpdateServiceImpl(findSocialUserByLongIdService,
        socialUserJpaRepository);
  }

  @DisplayName("유저가 존재하지 않을 때 SocialUserDomainException Throw")
  @Test
  void UpdateUser_WhenUserIsNotExist_ThrowSocialUserDomainException() {
    when(findSocialUserByLongIdService.findUserElseThrowError(1L)).thenThrow(SocialUserDomainException.class);

    assertThrows(SocialUserDomainException.class, () -> {
      socialUpdateUserService.updateUserInfo(1L, "nickname", "email", "phoneNumber");
    });

  }


  @DisplayName("유저가 존재할 때 repository save happened once")
  @Test
  void UpdateUser_WhenUserIsExist_SaveHappenedOnce() {
    SocialUser socialUser = SocialUser.builder().id(1L).build();
    when(findSocialUserByLongIdService.findUserElseThrowError(1L)).thenReturn(socialUser);

    socialUpdateUserService.updateUserInfo(1L, "nickname", "email", "phoneNumber");

    verify(socialUserJpaRepository, times(1)).save(
        any(SocialUser.class));

  }

}
