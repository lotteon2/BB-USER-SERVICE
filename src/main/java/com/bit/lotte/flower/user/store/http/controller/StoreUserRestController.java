package com.bit.lotte.flower.user.store.http.controller;

import bloomingblooms.response.CommonResponse;
import com.bit.lotte.flower.user.store.dto.command.UpdateBusinessNumberCommand;
import com.bit.lotte.flower.user.store.handler.StoreManagerStatusInitHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StoreUserRestController {


  private final StoreManagerStatusInitHandler storeManagerStatusInitHandler;


  @PatchMapping("/stores")
  public CommonResponse<String> reRegisterBusinessNumber(
      @RequestBody UpdateBusinessNumberCommand command) throws JsonProcessingException {
    storeManagerStatusInitHandler.initStoreManagerStatus(command);
    return CommonResponse.success("초기화 요청 완료");
  }


}
