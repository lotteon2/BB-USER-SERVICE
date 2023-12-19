package com.bit.lotte.flower.user.store.service;


import com.bit.lotte.flower.user.store.dto.response.StoreManagerLoginResponse;
import com.bit.lotte.flower.user.store.entity.StoreManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StoreManagerLoginResponseService {

  private final FindStoreMangerService findStoreMangerByLongIdService;

  public StoreManagerLoginResponse getStoreManagerResponse(Long storeMangerId){
    StoreManager storeManager = findStoreMangerByLongIdService.findByLongId(storeMangerId);
    return new StoreManagerLoginResponse(storeManager.getName());
  }
}
