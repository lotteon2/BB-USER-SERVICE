package com.bit.lotte.flower.user.social.service;

import com.bit.lotte.flower.user.social.dto.command.UserLoginCommand;
import com.bit.lotte.flower.user.social.dto.response.UserDataResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SocialUserIdentityManager {

  private final SocialUserDataResponseService userDataResponse;
  private final SocialUserLoginManager socialUserLoginManager;

  @Transactional
  public UserDataResponse get(UserLoginCommand userLoginCommand) {
    socialUserLoginManager.process(userLoginCommand);
    return userDataResponse.getUserData(userLoginCommand.getSocialId().getValue());
  }
}
