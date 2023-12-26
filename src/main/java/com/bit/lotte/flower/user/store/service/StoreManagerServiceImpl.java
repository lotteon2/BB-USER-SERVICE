package com.bit.lotte.flower.user.store.service;

import com.bit.lotte.flower.user.common.valueobject.UserId;
import com.bit.lotte.flower.user.store.entity.StoreManager;
import com.bit.lotte.flower.user.store.mapper.StoreManagerMapper;
import com.bit.lotte.flower.user.store.repository.StoreManagerJpaRepository;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice.AssignReturned.ToReturned;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class StoreManagerServiceImpl implements
    StoreManagerService<UserId> {

  private final StoreManagerJpaRepository repository;
  private final FindStoreMangerService findStoreMangerByIdService;
  private final EntityManager entityManager;


  @Transactional
  @Override
  public UserId updateBusinessNumber(String email, String businessNumberImage) {
    StoreManager storeManager = findStoreMangerByIdService.findByEmail(email);
    StoreManager updatedManager = repository.save(
        StoreManagerMapper.storeManagerUpdatedBusinessNumber(
            storeManager, businessNumberImage));

    entityManager.detach(storeManager);
    updatedManager.setUpdatedAt(LocalDateTime.now());

    storeManager = entityManager.merge(storeManager);

    return new UserId(storeManager.getId());
  }
}
