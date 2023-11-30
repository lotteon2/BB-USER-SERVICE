package com.bit.lotte.flower.user.store.repository;

import com.bit.lotte.flower.user.store.entity.StoreUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreJpaRepository extends JpaRepository<StoreUser,Long> {

}
