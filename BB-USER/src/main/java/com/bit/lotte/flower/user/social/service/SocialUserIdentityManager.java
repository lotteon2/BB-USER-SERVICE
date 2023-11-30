package com.bit.lotte.flower.user.social.service;

import com.bit.lotte.flower.user.social.dto.command.UserLoginCommand;
import com.bit.lotte.flower.user.social.dto.response.UserLoginResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SocialUserIdentityManager {

  private final SocialUserDataResponseService userDataResponse;
  private final SocialUserLoginManager socialUserLoginManager;

  @Transactional
  public UserLoginResponse get(UserLoginCommand userLoginCommand) {
    socialUserLoginManager.proccess(userLoginCommand);
    return userDataResponse.getUserData(userLoginCommand.getSocialId());
  }
}
