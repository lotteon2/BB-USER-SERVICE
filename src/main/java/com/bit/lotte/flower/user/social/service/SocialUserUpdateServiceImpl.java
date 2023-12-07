package com.bit.lotte.flower.user.social.service;

import com.bit.lotte.flower.user.social.dto.response.UserMyPageDataResponse;
import com.bit.lotte.flower.user.social.entity.SocialUser;
import com.bit.lotte.flower.user.social.exception.SocialUserDomainException;
import com.bit.lotte.flower.user.social.mapper.SocialUserMapper;
import com.bit.lotte.flower.user.social.repository.SocialUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SocialUserUpdateServiceImpl implements
    SocialUpdateUserService {

  private final SocialUserJpaRepository repository;


  @Override
  public void updatePhoneNumber(Long userId, String phoneNumber) {

    repository.save()
  }

  @Override
  public void updateUserInfo(Long userId, String nickname, String email, String phoneNumber) {

  }

  private SocialUser
}
