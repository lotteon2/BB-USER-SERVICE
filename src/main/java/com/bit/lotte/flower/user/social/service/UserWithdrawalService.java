package com.bit.lotte.flower.user.social.service;

import com.bit.lotte.flower.user.common.valueobject.UserId;
import org.springframework.stereotype.Service;

@Service
public interface UserWithdrawalService<ID extends UserId> {
  public void userWithdrawal(ID userId);
}
