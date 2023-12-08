package com.bit.lotte.flower.user.social.service;

import com.bit.lotte.flower.user.social.dto.response.UserDataDto;
import com.bit.lotte.flower.user.social.entity.SocialUser;
import com.bit.lotte.flower.user.social.mapper.SocialUserMapper;
import com.bit.lotte.flower.user.social.repository.FindSocialUserByLongIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class GetUserInfoWithService implements
    GetUserInfoService {
  private final FindSocialUserByLongIdService findUserByIdService;
  @Override
  public UserDataDto getUserdata(Long id) {
    SocialUser socialUser = findUserByIdService.findUserElseThrowError(id);
    return SocialUserMapper.socialUserToUserMyPageDataResponse(socialUser);
  }
}
