package com.bit.lotte.flower.user.store.http.feign.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateStoreManagerPendingStausDto {

  @NotNull
  private final String status = "ROLE_STORE_MANAGER_PENDING";
  @NotNull
  private Long storeManagerId;
}
