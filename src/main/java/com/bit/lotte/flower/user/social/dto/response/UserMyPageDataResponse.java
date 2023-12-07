package com.bit.lotte.flower.user.social.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserMyPageDataResponse {

  private String nickname;
  private String phoneNumber;
  private String email;
  private String likesCnt;
  private String counponCnt;

}
