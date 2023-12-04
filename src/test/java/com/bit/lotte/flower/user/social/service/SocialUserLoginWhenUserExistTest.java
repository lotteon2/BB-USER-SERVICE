package com.bit.lotte.flower.user.social.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bit.lotte.flower.user.common.valueobject.AuthId;
import com.bit.lotte.flower.user.social.dto.command.UserLoginCommand;
import com.bit.lotte.flower.user.social.entity.SocialUser;
import com.bit.lotte.flower.user.social.repository.SocialUserJpaRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class SocialUserLoginWhenUserExistTest {

  final Long testOauthId = 1L;
  @Mock
  SocialUserJpaRepository repository;
  @InjectMocks
  CreateNewUserWhenUserStatusIsDeleted newUserWhenUserStatusIsDeleted;

  private UserLoginCommand getCommand() {
    return UserLoginCommand.builder().email("test@gmail.com").nickname("testNickname")
        .socialId(new AuthId(testOauthId)).build();
  }

  private SocialUser savedSocialUser() {
    return SocialUser.builder().oauthId(testOauthId).build();
  }

  @DisplayName("존재하는 유저의 isDeleted가 true인 경우 유저 create 테스트")
  @Test
  void UserLoginProcess_WhenUserStatusIsDeleted_CreateNewUser() {
    UserLoginCommand command = getCommand();

    when(repository.findByOauthIdAndIsDeletedFalse(anyLong())).thenReturn(Optional.empty());
    doAnswer(invocation -> {
      SocialUser socialUser = invocation.getArgument(0);
      return socialUser;
    }).when(repository).save(any(SocialUser.class));

    newUserWhenUserStatusIsDeleted.processUser(command);

    verify(repository, times(1)).save(any(SocialUser.class));
  }

  @DisplayName("존재하는 유저의 isDeleted가 false인 경우 유저 create를 하지 않는 테스트")
  @Test
  void UserLoginProcess_WhenUserStatusIsNotDeleted_NotCreateUser() {
    UserLoginCommand command = getCommand();

    when(repository.findByOauthIdAndIsDeletedFalse(anyLong())).thenReturn(
        Optional.of(savedSocialUser()));

    newUserWhenUserStatusIsDeleted.processUser(command);

    verify(repository, never()).save(any(SocialUser.class));
  }


}
