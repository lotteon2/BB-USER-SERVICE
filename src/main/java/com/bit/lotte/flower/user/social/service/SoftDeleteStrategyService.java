package com.bit.lotte.flower.user.social.service;

import com.bit.lotte.flower.user.common.valueobject.UserId;
import com.bit.lotte.flower.user.social.entity.SocialUser;
import com.bit.lotte.flower.user.social.mapper.SocialUserMapper;
import com.bit.lotte.flower.user.social.repository.FindSocialUserByIdService;
import com.bit.lotte.flower.user.social.repository.SocialUserJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SoftDeleteStrategyService implements UserWithdrawalService<UserId> {

  private final FindSocialUserByIdService findSocialUserByIdService;
  private final SocialUserJpaRepository repository;

  @Override
  public void userWithdrawal(UserId userId) {
    SocialUser socialUser = findSocialUserByIdService.findUserByUserIdElseThrowError(userId.getValue());

    SocialUser notSoftDeletedUser = getNotSoftDeletedUser(repository
        .findAllByOauthId(socialUser.getOauthId()));

    SocialUser updatedToDeleted = SocialUserMapper.changeUserStatus(notSoftDeletedUser,
        Boolean.TRUE);
    repository.save(updatedToDeleted);
  }


  private SocialUser getNotSoftDeletedUser(List<SocialUser> allSocialUserByOauthId) {

    return allSocialUserByOauthId.stream()
        .filter(user -> !user.getIsDeleted())
        .findFirst()
        .orElse(null);
  }

}
