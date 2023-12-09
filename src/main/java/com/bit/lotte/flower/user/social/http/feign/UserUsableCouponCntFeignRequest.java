package com.bit.lotte.flower.user.social.http.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("${service.coupon.domain}")
@FeignClient(name = "get-user-usable-coupon-cnt")
public interface UserUsableCouponCntFeignRequest {
  @GetMapping("/coupons/count")
  ResponseEntity<Long> getUsableUserCouponCnt(@RequestHeader Long userId);
}
