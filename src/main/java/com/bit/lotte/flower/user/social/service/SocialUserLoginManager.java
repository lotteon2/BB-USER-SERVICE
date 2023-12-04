package com.bit.lotte.flower.user.social.service;

import com.bit.lotte.flower.user.social.dto.command.UserLoginCommand;
import com.bit.lotte.flower.user.social.dto.response.UserDataResponse;
import com.bit.lotte.flower.user.social.repository.SocialUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * 해당 LoginManager는 인가 서버통해서 생성되는 유저를 다루는 클래스 이다.
 * Auth서버에서 넘겨주는 데이터 혹은 필요한 데이터가 달라질 때마다 해당 클래스는 변경사항이 크다.
 * 즉 요구사항이 양쪽
 */
@RequiredArgsConstructor
@Service
public class SocialUserLoginManager {
  private final SocialUserJpaRepository repository;
  private final SocialUserCreateService socialUserCreateService;
  private final SocialUserLoginWhenUserExist socialUserLoginWhenUserExist ;
  private final SocialUserLoginResponseService socialUserLoginResponseService;

  public UserDataResponse process(UserLoginCommand userLoginCommand){
    Long oauthId = userLoginCommand.getSocialId().getValue();
    if(!repository.findAllByOauthId(oauthId).isEmpty()){
    socialUserLoginWhenUserExist.processUser(userLoginCommand);
    }
    else{
      socialUserCreateService.create(userLoginCommand);
    }
    return socialUserLoginResponseService.getUserLoginResponseWithNotSoftDeleted(oauthId);
  }

}
