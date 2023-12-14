package com.bit.lotte.flower.user.store.http.feign;

import com.bit.lotte.flower.user.store.http.feign.dto.UpdateStoreManagerPendingStausDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "init-storemanager-status",url = "${service.auth.domain}")
public interface InitStoreManagerStatusPendingFeignRequest {
  @PatchMapping("/admin/store-manager")
  public void publish(@RequestBody UpdateStoreManagerPendingStausDto dto);
}
