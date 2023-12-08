package com.bit.lotte.flower.user.store.mapper;

import com.bit.lotte.flower.user.store.dto.command.StoreManagerSignUpCommand;
import com.bit.lotte.flower.user.store.entity.StoreManager;

public class StoreManagerMapper {


  public static StoreManager createCommandToStoreManager(StoreManagerSignUpCommand signUpCommand) {
    return StoreManager.builder().businessNumberImage(signUpCommand.getBusinessNumberImage()).email(
        signUpCommand.getEmail()).id(signUpCommand.getId()).name(signUpCommand.getName()).build();
  }

  public static StoreManager storeManagerUpdatedPhoneNumber(StoreManager storeManager,
      String businessNumberImage) {
    return StoreManager.builder().email(storeManager.getEmail())
        .businessNumberImage(businessNumberImage).id(storeManager.getId())
        .name(storeManager.getName()).build();
  }
}
