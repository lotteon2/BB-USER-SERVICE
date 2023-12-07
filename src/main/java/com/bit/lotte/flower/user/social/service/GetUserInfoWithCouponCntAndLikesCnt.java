package com.bit.lotte.flower.user.social.service;

import com.bit.lotte.flower.user.social.dto.response.UserMyPageDataResponse;
import com.bit.lotte.flower.user.social.entity.SocialUser;
import com.bit.lotte.flower.user.social.exception.SocialUserDomainException;
import com.bit.lotte.flower.user.social.mapper.SocialUserMapper;
import com.bit.lotte.flower.user.social.repository.FindUserByIdService;
import com.bit.lotte.flower.user.social.repository.SocialUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class GetUserInfoWithCouponCntAndLikesCnt implements
    GetUserInfoService {
  private final FindUserByIdService findUserByIdService;
  @Override
  public UserMyPageDataResponse getUserdata(Long id, Long couponCnt, Long likesCnt) {
    SocialUser socialUser = findUserByIdService.findUserElseThrowError(id);
    return SocialUserMapper.socialUserToUserMyPageDataResponse(socialUser,couponCnt,likesCnt);
  }
}
