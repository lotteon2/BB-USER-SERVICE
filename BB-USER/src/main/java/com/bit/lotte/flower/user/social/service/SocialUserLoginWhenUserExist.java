package com.bit.lotte.flower.user.social.service;

import org.springframework.stereotype.Service;

@Service
public interface SocialUserLoginWhenUserExist {
  public void processUser(Long oauthId);
}
