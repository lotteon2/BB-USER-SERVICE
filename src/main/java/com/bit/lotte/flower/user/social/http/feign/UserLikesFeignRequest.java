package com.bit.lotte.flower.user.social.http.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "get-user-likes-cnt", value = "${service.likes.domain}")
public interface UserLikesFeignRequest {

  @GetMapping("/likes-cnt")
  ResponseEntity<Long> getUserLikesCnt(@RequestHeader Long userId);

}
