package com.bit.lotte.flower.user.social.service;

import com.bit.lotte.flower.user.social.entity.SocialUser;
import com.bit.lotte.flower.user.social.mapper.SocialUserMapper;
import com.bit.lotte.flower.user.social.repository.FindSocialUserByIdService;
import com.bit.lotte.flower.user.social.repository.SocialUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SocialUserUpdateServiceImpl implements
    SocialUpdateUserService {

  private final FindSocialUserByIdService findUserByIdService;
  private final SocialUserJpaRepository repository;

  @Override
  public void updateUserInfo(Long userId, String nickname, String email, String phoneNumber) {
    SocialUser socialUser = findUserByIdService.findUserByUserIdElseThrowError(userId);
    repository.save(
        SocialUserMapper.updateUserInfo(socialUser, nickname, email, phoneNumber)
    );
  }

}

