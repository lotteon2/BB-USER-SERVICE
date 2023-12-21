package com.bit.lotte.flower.user.store.http.controller;

import bloomingblooms.response.CommonResponse;
import com.bit.lotte.flower.user.common.valueobject.UserId;
import com.bit.lotte.flower.user.store.dto.command.StoreManagerSignUpCommand;
import com.bit.lotte.flower.user.store.dto.command.UpdateBusinessNumberCommand;
import com.bit.lotte.flower.user.store.dto.response.StoreManagerLoginResponse;
import com.bit.lotte.flower.user.store.http.message.InitStoreManagerAuthorizationPublisher;
import com.bit.lotte.flower.user.store.mapper.StoreManagerMapper;
import com.bit.lotte.flower.user.store.service.StoreManagerLoginResponseService;
import com.bit.lotte.flower.user.store.service.StoreManagerService;
import com.bit.lotte.flower.user.store.service.StoreManagerSignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StoreUserRestController {

  private final StoreManagerLoginResponseService storeManagerLoginResponseService;
  private final StoreManagerSignUpService storeManagerCreateService;
  private final StoreManagerService<UserId> storeManagerService;
  private final InitStoreManagerAuthorizationPublisher<UserId> publisher;

  /**
   * @param signUpCommand Auth서버로부터 만들어지는 스토어 매니저 회원가입이다.
   */
  @PostMapping("/store-manager")
  public CommonResponse<String> storeManagerSignUp(
      @RequestBody StoreManagerSignUpCommand signUpCommand) {
    storeManagerCreateService.signUp(
        StoreManagerMapper.createCommandToStoreManager(signUpCommand));
    return CommonResponse.success("회원가입 성공");
  }


  @GetMapping("/stores/{storeMangerId}")
  public CommonResponse<StoreManagerLoginResponse> login(
      @PathVariable Long storeMangerId) {
    return CommonResponse.success(
        storeManagerLoginResponseService.getStoreManagerResponse(storeMangerId));
  }


  @PatchMapping("/stores")
  public CommonResponse<String> reRegisterBusinessNumber(
      @RequestBody UpdateBusinessNumberCommand command) {
    UserId storeId = storeManagerService.updateBusinessNumber(command.getEmail(),
        command.getBusinessNumberImage());
    publisher.publish(storeId);
    return CommonResponse.success("초기화 요청 완료");
  }


}
