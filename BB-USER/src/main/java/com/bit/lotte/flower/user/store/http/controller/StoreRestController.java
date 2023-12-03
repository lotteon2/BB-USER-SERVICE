package com.bit.lotte.flower.user.store.http.controller;

import bloomingblooms.response.CommonResponse;
import com.bit.lotte.flower.user.store.dto.response.StoreManagerLoginResponse;
import com.bit.lotte.flower.user.store.dto.command.StoreManagerSignUpCommand;
import com.bit.lotte.flower.user.store.mapper.StoreManagerCommandMapper;
import com.bit.lotte.flower.user.store.service.StoreManagerLoginResponseService;
import com.bit.lotte.flower.user.store.service.StoreManagerSignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StoreRestController {

  private final StoreManagerLoginResponseService storeManagerLoginResponseService;
  private final StoreManagerSignUpService storeManagerCreateService;

  /**
   * @param signUpCommand Auth서버로부터 만들어지는 스토어 매니저 회원가입이다.
   */
  @PostMapping("/users/store-manager")
  public CommonResponse<String> storeManagerSignUp(
      @RequestBody StoreManagerSignUpCommand signUpCommand) {
    storeManagerCreateService.signUp(
        StoreManagerCommandMapper.createCommandToStoreManager(signUpCommand));
    return CommonResponse.success("회원가입 성공");
  }


  @GetMapping("/users/store-managers/{storeMangerId}")
  public ResponseEntity<StoreManagerLoginResponse> login(
      @PathVariable Long storeMangerId) {
    return ResponseEntity.ok(storeManagerLoginResponseService.getStoreManagerResponse(storeMangerId));
  }



}
