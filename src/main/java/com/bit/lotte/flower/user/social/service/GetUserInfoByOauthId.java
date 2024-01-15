package com.bit.lotte.flower.user.social.service;

import com.bit.lotte.flower.user.common.valueobject.AuthId;
import com.bit.lotte.flower.user.social.dto.response.UserDataDto;
import com.bit.lotte.flower.user.social.entity.SocialUser;
import com.bit.lotte.flower.user.social.mapper.SocialUserMapper;
import com.bit.lotte.flower.user.social.repository.FindSocialUserByIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("GetUserInfoByOauthId")
public class GetUserInfoByOauthId implements GetUserInfoService<AuthId> {

  private final FindSocialUserByIdService findUserByIdService;

  @Override
  public UserDataDto getUserdata(AuthId id) {
    SocialUser socialUser = findUserByIdService.findUserByOauthIdElseThrowAnError(id.getValue());
    return SocialUserMapper.socialUserToUserMyPageDataResponse(socialUser);
  }
}

