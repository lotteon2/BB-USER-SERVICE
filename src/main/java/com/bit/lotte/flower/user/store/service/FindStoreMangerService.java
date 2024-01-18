package com.bit.lotte.flower.user.store.service;

import com.bit.lotte.flower.user.store.entity.StoreManager;
import com.bit.lotte.flower.user.store.exception.StoreUserDomainException;
import com.bit.lotte.flower.user.store.repository.StoreManagerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FindStoreMangerService {

  private final StoreManagerJpaRepository repository;


  public StoreManager findByLongId(Long id) {
    return repository.findById(id).orElseThrow(() -> {
      throw new StoreUserDomainException("존재하지 않는 스토어 매니저입니다.");
    });
  }

    public StoreManager findByEmail (String email){
      return repository.findByEmail(email).orElseThrow(() -> {
        throw new StoreUserDomainException("존재하지 않는 스토어 매니저입니다.");
      });
    }


  }
