package com.bit.lotte.flower.user.social.repository;


import com.bit.lotte.flower.user.social.entity.SocialUser;
import com.bit.lotte.flower.user.social.exception.SocialUserDomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindSocialUserByIdService {

  private final SocialUserJpaRepository repository;

  public SocialUser findUserByUserIdElseThrowError(Long userId) {
    return repository.findById(userId).orElseThrow(() -> {
      throw new SocialUserDomainException("존재하지 않는 회원입니다.");
    });

  }

  public SocialUser findUserByOauthIdElseThrowAnError(Long oauthId) {
    return repository.findByOauthId(oauthId).orElseThrow(() -> {
      throw new SocialUserDomainException("존재하지 않는 회원입니다.");
    });


  }

}
