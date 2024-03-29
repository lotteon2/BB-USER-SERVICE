package com.bit.lotte.flower.user.social.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bit.lotte.flower.user.social.entity.SocialUser;
import com.bit.lotte.flower.user.social.exception.SocialUserDomainException;
import com.bit.lotte.flower.user.social.repository.FindSocialUserByIdService;
import com.bit.lotte.flower.user.social.repository.SocialUserJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateUserServiceTest {


  @Mock
  private SocialUserJpaRepository socialUserJpaRepository;
  @Mock
  private FindSocialUserByIdService findSocialUserByIdService;
  private SocialUserUpdateServiceImpl socialUpdateUserService;


  @BeforeEach
  public void setUp() {
    socialUpdateUserService = new SocialUserUpdateServiceImpl(findSocialUserByIdService,
        socialUserJpaRepository);
  }

  @DisplayName("유저가 존재하지 않을 때 SocialUserDomainException Throw")
  @Test
  void UpdateUser_WhenUserIsNotExist_ThrowSocialUserDomainException() {
    when(findSocialUserByIdService.findUserByUserIdElseThrowError(1L)).thenThrow(SocialUserDomainException.class);

    assertThrows(SocialUserDomainException.class, () -> {
      socialUpdateUserService.updateUserInfo(1L, "nickname", "email", "phoneNumber");
    });

  }


  @DisplayName("유저가 존재할 때 repository save happened once")
  @Test
  void UpdateUser_WhenUserIsExist_SaveHappenedOnce() {
    SocialUser socialUser = SocialUser.builder().id(1L).build();
    when(findSocialUserByIdService.findUserByUserIdElseThrowError(1L)).thenReturn(socialUser);

    socialUpdateUserService.updateUserInfo(1L, "nickname", "email", "phoneNumber");

    verify(socialUserJpaRepository, times(1)).save(
        any(SocialUser.class));

  }

}
