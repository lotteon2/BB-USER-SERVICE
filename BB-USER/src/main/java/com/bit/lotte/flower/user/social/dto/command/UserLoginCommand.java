package com.bit.lotte.flower.user.social.dto.command;

import com.bit.lotte.flower.user.common.valueobject.AuthId;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserLoginCommand {

  @NotNull
  private AuthId socialId;
  @NotNull
  private String nickname;
  @NotNull
  private String email;


}
