package com.bit.lotte.flower.user.social.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDataResponse {
  private String nickname;
  private boolean isPhoneNumberIsRegistered;
  private String profileImage;
}
