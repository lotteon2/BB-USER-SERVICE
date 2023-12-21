package com.bit.lotte.flower.user.social.repository;


import com.bit.lotte.flower.user.social.entity.SocialUser;
import com.bit.lotte.flower.user.social.exception.SocialUserDomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindSocialUserByLongIdService {

  private final SocialUserJpaRepository repository;

  public SocialUser findUserElseThrowError(Long id) {
    return repository.findById(id).orElseThrow(() -> {
      throw new SocialUserDomainException("존재하지 않는 회원입니다.");
    });
  }

}
