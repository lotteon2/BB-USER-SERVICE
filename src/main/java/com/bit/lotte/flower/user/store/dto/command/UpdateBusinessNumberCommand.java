package com.bit.lotte.flower.user.store.dto.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class UpdateBusinessNumberCommand {
  private String email;
  private String businessNumberImage;
}
