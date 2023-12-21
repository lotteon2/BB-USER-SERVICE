package com.bit.lotte.flower.user.admin.http.feign;

import bloomingblooms.response.CommonResponse;
import com.bit.lotte.flower.user.common.valueobject.StoreManagerStatus;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "get-store-manager-applications", url = "${service.auth.domain}")
public interface GetUserApplicationsByStatusFeignRequest {
  @GetMapping("/store-manager/{status}")
  public CommonResponse<List<Long>> getStoreManagerApplications(@PathVariable StoreManagerStatus status);
}

