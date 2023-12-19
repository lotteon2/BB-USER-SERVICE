package com.bit.lotte.flower.user.store.repository;

import com.bit.lotte.flower.user.store.entity.StoreManager;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreManagerJpaRepository extends JpaRepository<StoreManager, Long> {

  public Optional<StoreManager> findByEmail(String email);



}
