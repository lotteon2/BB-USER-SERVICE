package com.bit.lotte.flower.user.social.service;

import com.bit.lotte.flower.user.social.dto.response.UserDataResponse;
import com.bit.lotte.flower.user.social.entity.SocialUser;
import com.bit.lotte.flower.user.social.exception.SocialUserDomainException;
import com.bit.lotte.flower.user.social.mapper.SocialUserMapper;
import com.bit.lotte.flower.user.social.repository.SocialUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SocialUserLoginResponseService {


  private final SocialUserJpaRepository repository;

  public UserDataResponse getUserLoginResponseWithNotSoftDeleted(Long oauthId) {
    SocialUser socialUser = repository.findByOauthIdAndIsDeletedFalse(oauthId).orElseThrow(() -> {
      throw new SocialUserDomainException("해당 유저를 찾을 수 없습니다.");
    });
    return SocialUserMapper.createUserLoginCommandBySocialUser(socialUser.getProfileImage(),
        socialUser.getNickname(),
        isPhoneNumberRegistered(socialUser.getPhoneNumber()));
  }

  private Boolean isPhoneNumberRegistered(String phoneNumber) {
    return phoneNumber !=null;
  }
}
