package com.bit.lotte.flower.user.admin.http.controller;

import com.bit.lotte.flower.user.admin.dto.response.StoreManagerApplicationFormResponse;
import com.bit.lotte.flower.user.admin.http.message.GetUserApplicationByIdRequest;
import com.bit.lotte.flower.user.admin.service.GetStoreManagerApplicationService;
import com.bit.lotte.flower.user.common.valueobject.StoreManagerStatus;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SystemAdminRestController {

  private final GetStoreManagerApplicationService getStoreManagerApplicationService;
  private final GetUserApplicationByIdRequest request;

  @GetMapping("/admin/store-manager/applications")
  public ResponseEntity<StoreManagerApplicationFormResponse> getStoreManagerApplications(
      @RequestParam(defaultValue = "ROLE_STORE_MANAGER_PENDING") StoreManagerStatus status,
       Pageable pageable) {
    List<Long> storeUserListByStatus = request.request(status);
    return ResponseEntity.ok(getStoreManagerApplicationService.getApplications(storeUserListByStatus, pageable));

  }

//
//  @GetMapping("/admin/settlement")
//  public CommonResponse<SettlementResponse<StoreId>> getSettlements(
//      @RequestParam int year,
//      @RequestParam int month,
//      @RequestParam Long storeId,
//      @RequestParam Pageable pageable) {
//
//  }
//
//  @GetMapping("/admin/stores")
//  public CommonResponse<StoreInfoResponse> getStoreInfo(
//      @RequestParam String sido,
//      @RequestParam String gugun,
//      @RequestParam Pageable pageable) {
//
//  }
//
//  @GetMapping("/admin/store-managers/applications")
//  public CommonResponse<StoreInfoResponse> getStroeManagerApplications(
//      @RequestParam StoreManagerStatus status,
//      @RequestParam Pageable pageable) {
//
//  }
//
//
//    @GetMapping("/admin/sales")
//  public CommonResponse<StoreInfoResponse> getSalesTop10() {
//
//  }


}
