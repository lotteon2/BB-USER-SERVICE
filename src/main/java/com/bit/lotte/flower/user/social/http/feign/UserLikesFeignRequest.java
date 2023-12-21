package com.bit.lotte.flower.user.social.http.feign;

import bloomingblooms.response.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "get-user-likes-cnt", url = "${service.likes.domain}")
public interface UserLikesFeignRequest {

  @GetMapping("/client/likes-cnt")
  CommonResponse<Long> getUserLikesCnt(@RequestHeader Long userId);

}
