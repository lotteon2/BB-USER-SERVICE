package com.bit.lotte.flower.user.store.dto.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StoreManagerSignUpCommand {
  private Long id;
  private String email;
  private String businessNumberImage;
  private String name;
}
