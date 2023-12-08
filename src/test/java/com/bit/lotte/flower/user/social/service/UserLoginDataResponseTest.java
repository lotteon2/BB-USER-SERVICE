package com.bit.lotte.flower.user.social.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.bit.lotte.flower.user.social.dto.response.UserLoginDataResponse;
import com.bit.lotte.flower.user.social.entity.SocialUser;
import com.bit.lotte.flower.user.social.exception.SocialUserDomainException;
import com.bit.lotte.flower.user.social.repository.SocialUserJpaRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserLoginDataResponseTest {

  final Long testValidOauthId = 2L;
  final Long testNotExistOauthId = 1L;
  @InjectMocks
  SocialUserLoginResponseService loginResponseService;
  @Mock
  SocialUserJpaRepository repository;

  private SocialUser savedSocialUser() {
    return SocialUser.builder()
        .nickname("testNickname")
        .profileImage("test.jpg")
        .isDeleted(false)
        .build();
  }

  @DisplayName("유저가 존재하지 않을 때 Throw SocialUserDomainException")
  @Test
  void GetUserResponse_WhenUserIsNotExisted_ThrowSocialUserDomainException() {
    when(repository.findByOauthIdAndIsDeletedFalse(testNotExistOauthId)).thenThrow(
        SocialUserDomainException.class);

    assertThrows(SocialUserDomainException.class, () -> {
      loginResponseService.getUserLoginResponseWithNotSoftDeleted(testNotExistOauthId);
    });
  }

  @DisplayName("유저가 존재할 때 유저 정보, UserLoginResponse 리턴")
  @Test
  void GetUserResponse_WhenUserExisted_GetUserLoginResponse() {
    when(repository.findByOauthIdAndIsDeletedFalse(testValidOauthId)).thenReturn(
        Optional.of(savedSocialUser()));

    UserLoginDataResponse response =loginResponseService.getUserLoginResponseWithNotSoftDeleted(testValidOauthId);

    assertNotNull(response);


  }
}
