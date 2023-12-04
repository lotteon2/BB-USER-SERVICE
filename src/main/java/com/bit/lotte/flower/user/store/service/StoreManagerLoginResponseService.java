package com.bit.lotte.flower.user.store.service;


import com.bit.lotte.flower.user.store.dto.response.StoreManagerLoginResponse;
import com.bit.lotte.flower.user.store.entity.StoreManager;
import com.bit.lotte.flower.user.store.exception.StoreUserDomainException;
import com.bit.lotte.flower.user.store.repository.StoreManagerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StoreManagerLoginResponseService {

  private final StoreManagerJpaRepository repository;

  public StoreManagerLoginResponse getStoreManagerResponse(Long storeMangerId){
    StoreManager storeManager = repository.findById(storeMangerId).orElseThrow(()->{
      throw new StoreUserDomainException("존재하지 않는 스토어 매니저입니다.");
    });
    return new StoreManagerLoginResponse(storeManager.getName());
  }
}
