package com.bit.lotte.flower.user.store.service;

import com.bit.lotte.flower.user.store.repository.StoreManagerJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class StoreManagerLoginResponseTest {

  @InjectMocks
  StoreManagerLoginResponseService loginResponseService;
  @Mock
  StoreManagerJpaRepository repository;



  @Test
  void UserNameResponse_WhenUserIsExist_GetUserName(){

  }

    @Test
  void UserNameResponse_WhenUserIsExist_Throw(){

  }



}
