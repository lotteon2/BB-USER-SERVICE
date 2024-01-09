package com.bit.lotte.flower.user.social.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class UserMypageResponse {

  private String nickname;
  private String phoneNumber;
  private String email;
  private String profileImage;
  Integer couponCnt;
  Long likesCnt;


}
