package com.bit.lotte.flower.user.social.service;

import com.bit.lotte.flower.user.common.valueobject.UserId;
import com.bit.lotte.flower.user.social.entity.SocialUser;
import com.bit.lotte.flower.user.social.mapper.SocialUserMapper;
import com.bit.lotte.flower.user.social.repository.FindSocialUserByLongIdService;
import com.bit.lotte.flower.user.social.repository.SocialUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SoftDeleteStrategyService implements UserWithdrawalService<UserId> {

  private final FindSocialUserByLongIdService findSocialUserByLongIdService;
  private final SocialUserJpaRepository repository;

  @Override
  public void userWithdrawal(UserId userId) {
    SocialUser socialUser = findSocialUserByLongIdService.findUserElseThrowError(userId.getValue());
    SocialUser updatedToDeleted = SocialUserMapper.changeUserStatus(socialUser,Boolean.TRUE);
    repository.save(updatedToDeleted);
  }


}
