package com.bit.lotte.flower.user.social.http.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("${service.likes.domain}")
@FeignClient(name = "get-user-likes-cnt")
public interface UserLikesFeignRequest {

  @GetMapping("/likes-cnt")
  ResponseEntity<Long> getUserLikesCnt(@RequestHeader Long userId);

}
