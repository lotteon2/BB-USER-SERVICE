package com.bit.lotte.flower.user.social.service;

import com.bit.lotte.flower.user.social.dto.response.UserLoginDataResponse;
import com.bit.lotte.flower.user.social.entity.SocialUser;
import com.bit.lotte.flower.user.social.exception.SocialUserDomainException;
import com.bit.lotte.flower.user.social.mapper.SocialUserMapper;
import com.bit.lotte.flower.user.social.repository.SocialUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SocialUserDataResponseService {

  private final SocialUserJpaRepository repository;

  public UserLoginDataResponse getUserData(Long oauthId) {
    SocialUser socialUser = repository.findByOauthIdAndIsDeletedFalse(oauthId)
        .orElseThrow(() -> {
          throw new SocialUserDomainException("존재하지 않은 소셜 유저 계정입니다.");
        });

    return SocialUserMapper.getLoginResponse(socialUser.getNickname(), isUserPhoneRegistered(
        socialUser.getPhoneNumber()));
  }

  private boolean isUserPhoneRegistered(String phoneNumber) {
    return phoneNumber != null;
  }
}
