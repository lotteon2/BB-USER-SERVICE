package com.bit.lotte.flower.user.social.service.auth;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.bit.lotte.flower.user.common.valueobject.UserId;
import com.bit.lotte.flower.user.social.entity.SocialUser;
import com.bit.lotte.flower.user.social.exception.SocialUserDomainException;
import com.bit.lotte.flower.user.social.repository.FindSocialUserByIdService;
import com.bit.lotte.flower.user.social.service.GetUserInfoServiceByUserId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetUserInfoServiceTest {

  @Mock
  FindSocialUserByIdService findSocialUserByIdService;
  @InjectMocks
  GetUserInfoServiceByUserId getUserInfoService;


  @DisplayName("유저가 존재할 때 UserDataDto 반환")
  @Test
  void UserDataServiceTest_WhenUserIsExist_GetUserDto() {
    SocialUser mockUser = mock(SocialUser.class);

    when(findSocialUserByIdService.findUserByUserIdElseThrowError(anyLong())).thenReturn(mockUser);

    assertDoesNotThrow(()->{
      getUserInfoService.getUserdata(new UserId(mockUser.getId()));
    });

  }


  @DisplayName("유저가 존재하지 않을 때 Throw SocialUserException")
  @Test
  void UserDataServiceTest_WhenUserIsNotExist_ThrowSocialUserException() {
    Mockito.when(findSocialUserByIdService.findUserByUserIdElseThrowError(anyLong()))
        .thenThrow(SocialUserDomainException.class);

    assertThrowsExactly(SocialUserDomainException.class, () -> {
      getUserInfoService.getUserdata(new UserId(anyLong()));
    });
  }
}