package com.bit.lotte.flower.user.store.service;

import com.bit.lotte.flower.user.store.entity.StoreManager;
import com.bit.lotte.flower.user.store.repository.StoreManagerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StoreManagerSignUpService{

  private final StoreManagerJpaRepository repository;

  public void signUp(StoreManager storeManager) {
    repository.save(storeManager);
  }
}
