package com.bit.lotte.flower.user.social.service.auth;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.bit.lotte.flower.user.social.entity.SocialUser;
import com.bit.lotte.flower.user.social.exception.SocialUserDomainException;
import com.bit.lotte.flower.user.social.repository.FindSocialUserByLongIdService;
import com.bit.lotte.flower.user.social.service.GetUserInfoServiceImpl;
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
  FindSocialUserByLongIdService findSocialUserByLongIdService;
  @InjectMocks
  GetUserInfoServiceImpl getUserInfoService;


  @DisplayName("유저가 존재할 때 UserDataDto 반환")
  @Test
  void UserDataServiceTest_WhenUserIsExist_GetUserDto() {
    SocialUser mockUser = mock(SocialUser.class);

    when(findSocialUserByLongIdService.findUserElseThrowError(anyLong())).thenReturn(mockUser);

    assertDoesNotThrow(()->{
      getUserInfoService.getUserdata(mockUser.getId());
    });

  }


  @DisplayName("유저가 존재하지 않을 때 Throw SocialUserException")
  @Test
  void UserDataServiceTest_WhenUserIsNotExist_ThrowSocialUserException() {
    Mockito.when(findSocialUserByLongIdService.findUserElseThrowError(anyLong()))
        .thenThrow(SocialUserDomainException.class);

    assertThrowsExactly(SocialUserDomainException.class, () -> {
      getUserInfoService.getUserdata(anyLong());
    });
  }
}