package com.bit.lotte.flower.user.social.service;

import com.bit.lotte.flower.user.social.dto.command.UserLoginCommand;
import org.springframework.stereotype.Service;

@Service
public interface SocialUserLoginWhenUserExist {
  public void processUser(UserLoginCommand command);
}
