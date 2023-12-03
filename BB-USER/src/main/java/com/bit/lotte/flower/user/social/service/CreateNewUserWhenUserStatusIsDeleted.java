package com.bit.lotte.flower.user.social.service;

import com.bit.lotte.flower.user.social.dto.command.UserLoginCommand;
import com.bit.lotte.flower.user.social.entity.SocialUser;
import com.bit.lotte.flower.user.social.mapper.SocialUserMapper;
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
  public void processUser(UserLoginCommand command) {
    if (repository.findByOauthIdAndIsDeletedFalse(command.getSocialId().getValue()).isEmpty()) {
      repository.save(SocialUserMapper.createSocialUserByLoginCommand(command));
    }
  }
}
