package com.bit.lotte.flower.user.social.service;

import com.bit.lotte.flower.user.social.dto.command.UserLoginCommand;
import com.bit.lotte.flower.user.social.mapper.SocialUserMapper;
import com.bit.lotte.flower.user.social.repository.SocialUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SocialUserCreateService {
  private final SocialUserJpaRepository repository;
  public void create(UserLoginCommand userCreateCommand){
    repository.save(SocialUserMapper.createSocialUserByLoginCommand(userCreateCommand));
  }
}
