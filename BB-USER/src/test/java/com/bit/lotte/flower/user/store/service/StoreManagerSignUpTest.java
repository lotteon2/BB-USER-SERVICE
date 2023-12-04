package com.bit.lotte.flower.user.store.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.bit.lotte.flower.user.store.dto.command.StoreManagerSignUpCommand;
import com.bit.lotte.flower.user.store.entity.StoreManager;
import com.bit.lotte.flower.user.store.mapper.StoreManagerCommandMapper;
import com.bit.lotte.flower.user.store.repository.StoreManagerJpaRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;


@ExtendWith(MockitoExtension.class)
class StoreManagerSignUpTest {

  private final Long testUserId = 1L;
  @Mock
  StoreManagerJpaRepository repository;
  @InjectMocks
  StoreManagerSignUpService storeManagerSignUpService;

  private StoreManagerSignUpCommand createTestStoreManagerAccount() {
    return StoreManagerSignUpCommand.builder().businessNumberImage("randomImage")
        .email("randomEmail").name("randomName").id(testUserId).build();
  }

  private StoreManager getStoreManagerFromCommand(StoreManagerSignUpCommand signUpCommand) {
    return StoreManager.builder().businessNumberImage(signUpCommand.getBusinessNumberImage()).email(
        signUpCommand.getEmail()).id(signUpCommand.getId()).name(signUpCommand.getName()).build();
  }


  @DisplayName("스토어 매니저 회원가입 테스트")
  @Test
  void SignUp_WhenThereIsRequestFromAuthSever_CreateStoreManager() {
    StoreManager manager =
        getStoreManagerFromCommand(createTestStoreManagerAccount());

    when(repository.save(any(StoreManager.class))).thenReturn(manager);
    when(repository.findById(testUserId)).thenReturn(Optional.of(manager));

    storeManagerSignUpService.signUp(manager);

    assertNotNull(repository.findById(testUserId).orElse(null));
  }


}
