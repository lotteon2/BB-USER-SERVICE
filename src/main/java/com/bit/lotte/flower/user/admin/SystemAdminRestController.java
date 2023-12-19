package com.bit.lotte.flower.user.admin;

import bloomingblooms.response.CommonResponse;
import com.bit.lotte.flower.user.admin.dto.SettlementResponse;
import com.bit.lotte.flower.user.common.valueobject.StoreId;
import com.bit.lotte.flower.user.common.valueobject.StoreManagerStatus;
import com.bit.lotte.flower.user.dto.StoreInfoResponse;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemAdminRestController {


  @GetMapping("/admin/settlement")
  public CommonResponse<SettlementResponse<StoreId>> getSettlements(
      @RequestParam int year,
      @RequestParam int month,
      @RequestParam Long storeId,
      @RequestParam Pageable pageable) {

  }

  @GetMapping("/admin/stores")
  public CommonResponse<StoreInfoResponse> getStoreInfo(
      @RequestParam String sido,
      @RequestParam String gugun,
      @RequestParam Pageable pageable) {

  }

  @GetMapping("/admin/store-managers/applications")
  public CommonResponse<StoreInfoResponse> getStroeManagerApplications(
      @RequestParam StoreManagerStatus status,
      @RequestParam Pageable pageable) {

  }


    @GetMapping("/admin/sales")
  public CommonResponse<StoreInfoResponse> getSalesTop10() {

  }



}
