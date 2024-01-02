package com.bit.lotte.flower.user.store.service.auth;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.bit.lotte.flower.user.common.valueobject.AuthId;
import com.bit.lotte.flower.user.store.dto.response.StoreManagerLoginResponse;
import com.bit.lotte.flower.user.store.entity.StoreManager;
import com.bit.lotte.flower.user.store.exception.StoreUserDomainException;
import com.bit.lotte.flower.user.store.repository.StoreManagerJpaRepository;
import com.bit.lotte.flower.user.store.service.FindStoreMangerService;
import com.bit.lotte.flower.user.store.service.StoreManagerLoginResponseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class StoreManagerLoginResponseTest {

  @Mock
  StoreManagerJpaRepository repository;
  @Mock
  FindStoreMangerService findStoreMangerByLongIdService;
  @InjectMocks
  StoreManagerLoginResponseService<AuthId> loginResponseService;

  private final Long validStoreManagerId = 1L;


  private StoreManager initUser() {
    return StoreManager.builder().name("test").id(validStoreManagerId)
        .businessNumberImage("testImage").email("test@gmail.com").build();
  }

  @DisplayName("스토어 매니저 로그인시 유저가 존재할 때 유저 네임 반환")
  @Test
  void UserNameResponse_WhenUserIsExist_GetUserName() {
    when(findStoreMangerByLongIdService.findByLongId(validStoreManagerId)).thenReturn(initUser());

    StoreManagerLoginResponse response = loginResponseService.getStoreManagerResponse(
       new AuthId( validStoreManagerId));
    assertNotNull(response.getName());
  }

  @DisplayName("스토어 매니저 로그인시 유저가 존재하지 않을 때 StoreUserDomainException Throw")
  @Test
  void UserNameResponse_WhenUserIsExist_ThrowStoreUserDomainException() {

when(findStoreMangerByLongIdService.findByLongId(validStoreManagerId)).thenThrow(StoreUserDomainException.class);
    assertThrows(StoreUserDomainException.class, () -> {
      loginResponseService.getStoreManagerResponse(new AuthId(validStoreManagerId));
    });

  }


}
