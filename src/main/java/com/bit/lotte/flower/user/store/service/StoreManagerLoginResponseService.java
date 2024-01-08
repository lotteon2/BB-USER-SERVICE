package com.bit.lotte.flower.user.store.service;


import com.bit.lotte.flower.user.common.valueobject.AuthId;
import com.bit.lotte.flower.user.store.dto.response.StoreManagerLoginResponse;
import com.bit.lotte.flower.user.store.entity.StoreManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StoreManagerLoginResponseService<ID extends AuthId> {

  private final FindStoreMangerService findStoreMangerByLongIdService;

  public StoreManagerLoginResponse getStoreManagerResponse(ID storeMangerId){
    StoreManager storeManager = findStoreMangerByLongIdService.findByLongId(storeMangerId.getValue());
    return new StoreManagerLoginResponse(storeManager.getName());
  }
}
