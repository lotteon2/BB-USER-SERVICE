package com.bit.lotte.flower.user.social.dto.command;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class
UpdateUserInfoCommand {
  @Nullable
  private String nickname;
  @Nullable
  private String email;
  @Nullable
  private String phoneNumber;
}
