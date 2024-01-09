package com.bit.lotte.flower.user.store.service.auth;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import com.bit.lotte.flower.user.store.entity.StoreManager;
import com.bit.lotte.flower.user.store.repository.StoreManagerJpaRepository;
import com.bit.lotte.flower.user.store.service.StoreManagerSignUpService;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class StoreManagerSignUpTest {

  @Mock
  StoreManagerJpaRepository repository;
  @InjectMocks
  StoreManagerSignUpService storeManagerSignUpService;


  @DisplayName("스토어 매니저 회원가입 테스트")
  @Test
  void SignUp_WhenThereIsRequestFromAuthSever_CreateStoreManager() {

    when(repository.save(any(StoreManager.class))).thenReturn(mock(StoreManager.class));

    storeManagerSignUpService.signUp(mock(StoreManager.class));

    verify(repository, times(1)).save(any(StoreManager.class));

  }

}
