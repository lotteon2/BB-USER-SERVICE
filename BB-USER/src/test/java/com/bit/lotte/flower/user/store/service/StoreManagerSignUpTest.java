package com.bit.lotte.flower.user.store.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.bit.lotte.flower.user.store.dto.command.StoreManagerSignUpCommand;
import com.bit.lotte.flower.user.store.mapper.StoreManagerCommandMapper;
import com.bit.lotte.flower.user.store.repository.StoreManagerJpaRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ActiveProfiles("test")
@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StoreManagerSignUpTest {

  private final Long testUserId = 1L;
  @Autowired
  StoreManagerJpaRepository repository;
  @Autowired
  StoreManagerSignUpService storeManagerSignUpService;

  private StoreManagerSignUpCommand createTestStoreManagerAccount() {
    return StoreManagerSignUpCommand.builder().businessNumberImage("randomImage")
        .email("randomEmail").name("randomName").id(testUserId).build();
  }

  @Test
  void SignUp_WhenThereIsRequestFromAuthSever_CreateStoreManager() {
    storeManagerSignUpService.signUp(
        StoreManagerCommandMapper.createCommandToStoreManager(createTestStoreManagerAccount()));
    assertNotNull(repository.findById(testUserId).get());
  }


}
