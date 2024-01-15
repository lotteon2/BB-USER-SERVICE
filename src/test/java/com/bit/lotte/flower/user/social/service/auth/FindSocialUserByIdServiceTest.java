package com.bit.lotte.flower.user.social.service.auth;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;

import com.bit.lotte.flower.user.social.entity.SocialUser;
import com.bit.lotte.flower.user.social.exception.SocialUserDomainException;
import com.bit.lotte.flower.user.social.repository.FindSocialUserByIdService;
import com.bit.lotte.flower.user.social.repository.SocialUserJpaRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FindSocialUserByIdServiceTest {


  @Mock
  SocialUserJpaRepository repository;
  @InjectMocks
  FindSocialUserByIdService findSocialUserByIdService;

  @Test
  void FindUser_WhenUserIsExist_GetUser() {
    SocialUser mockUser = mock(SocialUser.class);
    Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(mockUser));

    SocialUser result = findSocialUserByIdService.findUserByUserIdElseThrowError(1L);

    assertNotNull(result);
  }

  @Test
  void FindUser_WhenUserIsNotExist_ThrowSocialUserDomainException() {
    Mockito.when(repository.findById(anyLong())).thenThrow(SocialUserDomainException.class);

        assertThrowsExactly(SocialUserDomainException.class, () -> {
          findSocialUserByIdService.findUserByUserIdElseThrowError(1L);
        });
  }

}
