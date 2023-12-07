package com.bit.lotte.flower.user.social.service;

import com.bit.lotte.flower.user.social.entity.SocialUser;
import com.bit.lotte.flower.user.social.exception.SocialUserDomainException;
import com.bit.lotte.flower.user.social.mapper.SocialUserMapper;
import com.bit.lotte.flower.user.social.repository.FindSocialUserByLongIdService;
import com.bit.lotte.flower.user.social.repository.SocialUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SocialUserUpdateServiceImpl implements
    SocialUpdateUserService {

  private final FindSocialUserByLongIdService findUserByIdService;
  private final SocialUserJpaRepository repository;


  @Override
  public void updatePhoneNumber(Long userId, String phoneNumber) {
    SocialUser socialUser = findUserByIdService.findUserElseThrowError(userId);
    repository.save(
        SocialUserMapper.updatedUserPhoneNumber(socialUser, phoneNumber));
  }

  @Override
  public void updateUserInfo(Long userId, String nickname, String email, String phoneNumber) {
    SocialUser socialUser = findUserByIdService.findUserElseThrowError(userId);
    repository.save(
        SocialUserMapper.updateUserInfo(socialUser, nickname, email, phoneNumber)
    );
  }

}
