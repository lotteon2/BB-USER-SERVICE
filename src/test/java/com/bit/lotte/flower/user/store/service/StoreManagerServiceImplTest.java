package com.bit.lotte.flower.user.store.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bit.lotte.flower.user.common.valueobject.UserId;
import com.bit.lotte.flower.user.store.entity.StoreManager;
import com.bit.lotte.flower.user.store.exception.StoreUserDomainException;
import com.bit.lotte.flower.user.store.repository.StoreManagerJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StoreManagerServiceImplTest {

  @Mock
  StoreManagerJpaRepository repository;
  @Mock
  FindStoreMangerByLongIdService findStoreMangerByLongIdService;
  @InjectMocks
  StoreManagerServiceImpl storeManagerService;

  @Test
  void UpdateStoreManagerBusinessNumber_WhenStoreUserExist_SaveOccuredOnce() {
    StoreManager mockStoreManager = mock(StoreManager.class);

    when(findStoreMangerByLongIdService.findByLongId(mockStoreManager.getId())).thenReturn(
        mockStoreManager);
    storeManagerService.updateBusinessNumber(new UserId(mockStoreManager.getId()),
        mockStoreManager.getBusinessNumberImage());

    verify(repository, times(1)).save(any(StoreManager.class));

  }

  @Test
  void UpdateStoreManagerPhoneNumber_WhenStoreUserExist_ThrowStoreUserException() {

    when(findStoreMangerByLongIdService.findByLongId(anyLong())).thenThrow(
        StoreUserDomainException.class);

    assertThrowsExactly(StoreUserDomainException.class,()->{
      storeManagerService.updateBusinessNumber(new UserId(anyLong()),"business Number");
    });
  }

}