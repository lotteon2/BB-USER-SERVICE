package com.bit.lotte.flower.user.store.service;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.bit.lotte.flower.user.store.entity.StoreManager;
import com.bit.lotte.flower.user.store.exception.StoreUserDomainException;
import com.bit.lotte.flower.user.store.repository.StoreManagerJpaRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FindStoreMangerByLongIdServiceTest {


  @Mock
  StoreManagerJpaRepository repository;
  @InjectMocks
  FindStoreMangerByLongIdService findStoreMangerByLongIdService;

  @Test
  void FindStoreManager_WhenStoreManagerExist_GetStoreManager() {
    StoreManager mockStoreManager = mock(StoreManager.class);
    when(repository.findById(any())).thenReturn(
        Optional.of(mockStoreManager));
    findStoreMangerByLongIdService.findByLongId(mockStoreManager.getId());
    assertNotNull(mockStoreManager);
  }

  @Test
  void FindStoreManager_WhenStoreManagerIsNotExist_ThrowStoreUserDomainException() {
    StoreManager mockStoreManager = mock(StoreManager.class);
    when(repository.findById(any())).thenThrow(StoreUserDomainException.class);

    assertThrowsExactly(StoreUserDomainException.class, () -> {
      findStoreMangerByLongIdService.findByLongId(mockStoreManager.getId());
    });
  }
}