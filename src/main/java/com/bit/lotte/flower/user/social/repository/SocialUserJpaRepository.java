package com.bit.lotte.flower.user.social.repository;

import com.bit.lotte.flower.user.social.entity.SocialUser;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserJpaRepository extends JpaRepository<SocialUser, Long> {

  Optional<SocialUser> findByOauthIdAndIsDeletedFalse(Long oauthId);
  List<SocialUser> findAllByOauthId(Long oauthId);
  Optional<SocialUser> findByOauthId(Long oauthId);
}
