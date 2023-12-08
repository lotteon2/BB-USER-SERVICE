package com.bit.lotte.flower.user.store.service;

import com.bit.lotte.flower.user.common.valueobject.BaseId;
import org.springframework.stereotype.Service;

@Service
public interface StoreManagerService<ID extends BaseId> {
  void updateBusinessNumber(ID userId, String businessNumberImage);
}
