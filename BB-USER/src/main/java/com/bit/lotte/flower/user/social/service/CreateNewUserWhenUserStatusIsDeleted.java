package com.bit.lotte.flower.user.social.service;

import com.bit.lotte.flower.user.social.entity.SocialUser;
import com.bit.lotte.flower.user.social.repository.SocialUserJpaRepository;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateNewUserWhenUserStatusIsDeleted implements
    SocialUserLoginWhenUserExist {

  private final SocialUserJpaRepository repository;
  @Override
  public void processUser(Long oauthId) {
    repository.findAllByOauthId(oauthId).stream().map(SocialUser::getIsDeleted).collect(Collectors.toList());}
}
