package com.bit.lotte.flower.user.store.http.feign;

import com.bit.lotte.flower.user.common.valueobject.BaseId;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "init-storemanager-status",value = "${service.auth.domain}")
public interface InitStoreManagerStatusPendingFeignRequest<ID extends BaseId> {
  public void publish(@RequestHeader ID userId);
}
