package com.bit.lotte.flower.user.social.http.feign;

import bloomingblooms.response.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "get-user-usable-coupon-cnt", url = "${service.store.domain}")
public interface UserUsableCouponCntFeignRequest {
  @GetMapping("/client/stores/coupons/count")
  CommonResponse<Integer> getUsableUserCouponCnt(@RequestHeader Long userId);
}
