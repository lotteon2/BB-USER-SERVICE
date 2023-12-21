package com.bit.lotte.flower.user.social.service;

import com.bit.lotte.flower.user.common.valueobject.AuthId;
import com.bit.lotte.flower.user.common.valueobject.UserId;
import com.bit.lotte.flower.user.social.entity.SocialUser;
import com.bit.lotte.flower.user.social.exception.SocialUserDomainException;
import com.bit.lotte.flower.user.social.repository.SocialUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MapAuthIdToUserIdService<INPUT extends AuthId> {

  private final SocialUserJpaRepository repository;

  public UserId convert(INPUT authId) {
    SocialUser socialUser = repository.findByOauthIdAndIsDeletedFalse(authId.getValue())
        .orElseThrow(() -> {
          throw new SocialUserDomainException("존재하지 않는 소셜 유저입니다.");
        });

    return new UserId(socialUser.getId());
  }
}
