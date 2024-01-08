package com.bit.lotte.flower.user.social.dto.command;

import com.bit.lotte.flower.user.common.valueobject.UserId;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserLoginCommand {

  @NotNull
  private UserId socialId;
  @NotNull
  private String nickname;
  @NotNull
  private String phoneNumber;
  @NotNull
  private String email;


}
