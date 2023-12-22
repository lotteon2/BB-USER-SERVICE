package com.bit.lotte.flower.user.store.http.controller;

import bloomingblooms.response.CommonResponse;
import com.bit.lotte.flower.user.common.valueobject.UserId;
import com.bit.lotte.flower.user.store.dto.command.UpdateBusinessNumberCommand;
import com.bit.lotte.flower.user.store.http.message.InitStoreManagerAuthorizationPublisher;
import com.bit.lotte.flower.user.store.service.StoreManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StoreUserRestController {


  private final StoreManagerService<UserId> storeManagerService;
  private final InitStoreManagerAuthorizationPublisher<UserId> publisher;



  @PatchMapping("/stores")
  public CommonResponse<String> reRegisterBusinessNumber(
      @RequestBody UpdateBusinessNumberCommand command) {
    UserId storeId = storeManagerService.updateBusinessNumber(command.getEmail(),
        command.getBusinessNumberImage());
    publisher.publish(storeId);
    return CommonResponse.success("초기화 요청 완료");
  }


}
