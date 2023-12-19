package com.bit.lotte.flower.user.store.service;

import com.bit.lotte.flower.user.common.valueobject.StoreId;
import com.bit.lotte.flower.user.common.valueobject.UserId;
import com.bit.lotte.flower.user.store.entity.StoreManager;
import com.bit.lotte.flower.user.store.mapper.StoreManagerMapper;
import com.bit.lotte.flower.user.store.repository.StoreManagerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StoreManagerServiceImpl implements
    StoreManagerService<UserId> {

  private final StoreManagerJpaRepository repository;
  private final FindStoreMangerService findStoreMangerByIdService;

  @Override
  public UserId updateBusinessNumber(String email, String businessNumberImage) {
    StoreManager storeManager = findStoreMangerByIdService.findByEmail(email);
   repository.save(StoreManagerMapper.storeManagerUpdatedPhoneNumber(
        storeManager, businessNumberImage));

   return new UserId(storeManager.getId());
  }
}
