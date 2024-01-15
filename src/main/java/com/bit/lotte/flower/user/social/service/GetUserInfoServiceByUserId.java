package com.bit.lotte.flower.user.social.service;

import com.bit.lotte.flower.user.common.valueobject.UserId;
import com.bit.lotte.flower.user.social.dto.response.UserDataDto;
import com.bit.lotte.flower.user.social.entity.SocialUser;
import com.bit.lotte.flower.user.social.mapper.SocialUserMapper;
import com.bit.lotte.flower.user.social.repository.FindSocialUserByIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("GetUserInfoServiceByUserId")
public class GetUserInfoServiceByUserId implements
    GetUserInfoService<UserId> {

  private final FindSocialUserByIdService findUserByIdService;

  @Override
  public UserDataDto getUserdata(UserId id) {
    SocialUser socialUser = findUserByIdService.findUserByUserIdElseThrowError(id.getValue());
    return SocialUserMapper.socialUserToUserMyPageDataResponse(socialUser);
  }
}
