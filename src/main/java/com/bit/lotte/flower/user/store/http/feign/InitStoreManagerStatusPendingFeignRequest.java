package com.bit.lotte.flower.user.store.http.feign;

import bloomingblooms.response.CommonResponse;
import com.bit.lotte.flower.user.store.http.feign.dto.UpdateStoreManagerPendingStatusDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "init-store-manager-status",url = "${service.auth.domain}")
public interface InitStoreManagerStatusPendingFeignRequest {
  @PutMapping("/client/store-manager")
  public CommonResponse<String> publish(@RequestBody UpdateStoreManagerPendingStatusDto dto);
}
