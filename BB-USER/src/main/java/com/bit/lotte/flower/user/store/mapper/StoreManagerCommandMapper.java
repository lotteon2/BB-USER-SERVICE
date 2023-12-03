package com.bit.lotte.flower.user.store.mapper;

import com.bit.lotte.flower.user.store.dto.command.StoreManagerSignUpCommand;
import com.bit.lotte.flower.user.store.entity.StoreManager;

public class StoreManagerCommandMapper {


  public static StoreManager createCommandToStoreManager(StoreManagerSignUpCommand signUpCommand) {
    return StoreManager.builder().businessNumberImage(signUpCommand.getBusinessNumberImage()).email(
        signUpCommand.getEmail()).id(signUpCommand.getId()).name(signUpCommand.getName()).build();
  }

}
