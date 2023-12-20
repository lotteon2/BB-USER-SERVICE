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
  private final GetUserApplicationByIdRequest getUserApplicationByIdRequest;


  @GetMapping("/admin/store-managers/applications")
  public ResponseEntity<StoreManagerApplicationFormResponse> getStoreManagerApplications(
      @RequestParam(defaultValue = "ROLE_STORE_MANAGER_PENDING") StoreManagerStatus status,
      Pageable pageable) {
    List<Long> storeUserListByStatus = getUserApplicationByIdRequest.request(status);
    return ResponseEntity.ok(
        getStoreManagerApplicationService.getApplications(storeUserListByStatus, pageable));

  }




}
