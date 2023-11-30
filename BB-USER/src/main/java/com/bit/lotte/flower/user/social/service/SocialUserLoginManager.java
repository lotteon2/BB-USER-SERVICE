package com.bit.lotte.flower.user.social.service;

import com.bit.lotte.flower.user.social.dto.command.UserLoginCommand;
import com.bit.lotte.flower.user.social.entity.SocialUser;
import com.bit.lotte.flower.user.social.repository.SocialUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SocialUserLoginManager {
  private final SocialUserJpaRepository repository;
  private final SocialUserCreateService socialUserCreateService;
  private final SocialUserLoginWhenUserExist socialUserLoginWhenUserExist ;

  public void proccess(UserLoginCommand userLoginCommand){
    Long oauthId = userLoginCommand.getSocialId();
    if(repository.findByOauthId(oauthId).isPresent()){
    socialUserLoginWhenUserExist.processUser(oauthId);
    }
    else{
      socialUserCreateService.create(userLoginCommand);
    }
  }
}
