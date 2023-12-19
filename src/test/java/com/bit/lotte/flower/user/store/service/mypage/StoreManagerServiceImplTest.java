package com.bit.lotte.flower.user.store.service.mypage;

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
import com.bit.lotte.flower.user.store.service.FindStoreMangerService;
import com.bit.lotte.flower.user.store.service.StoreManagerServiceImpl;
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
  FindStoreMangerService findStoreMangerByLongIdService;
  @InjectMocks
  StoreManagerServiceImpl storeManagerService;

  @Test
  void UpdateStoreManagerBusinessNumber_WhenStoreUserExist_SaveOccuredOnce() {
    StoreManager mockStoreManager = mock(StoreManager.class);

    when(findStoreMangerByLongIdService.findByEmail(mockStoreManager.getEmail())).thenReturn(
        mockStoreManager);

    storeManagerService.updateBusinessNumber(mockStoreManager.getEmail(),
        mockStoreManager.getBusinessNumberImage());

    verify(repository, times(1)).save(any(StoreManager.class));

  }

  @Test
  void UpdateStoreManagerPhoneNumber_WhenStoreUserExist_ThrowStoreUserException() {

    when(findStoreMangerByLongIdService.findByEmail(anyString())).thenThrow(
        StoreUserDomainException.class);

    assertThrowsExactly(StoreUserDomainException.class,()->{
      storeManagerService.updateBusinessNumber("test@email.com","business Number");
    });
  }

}