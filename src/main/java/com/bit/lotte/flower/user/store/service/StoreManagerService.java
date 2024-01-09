package com.bit.lotte.flower.user.store.service;

import com.bit.lotte.flower.user.common.valueobject.BaseId;
import com.bit.lotte.flower.user.common.valueobject.StoreId;
import com.bit.lotte.flower.user.common.valueobject.UserId;
import org.springframework.stereotype.Service;

@Service
public interface StoreManagerService<ID extends UserId> {
 ID updateBusinessNumber(String email, String businessNumberImage);
}
