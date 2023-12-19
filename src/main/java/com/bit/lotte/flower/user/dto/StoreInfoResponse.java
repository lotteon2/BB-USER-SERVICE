package com.bit.lotte.flower.user.dto;

import com.bit.lotte.flower.user.common.valueobject.StoreId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class StoreInfoResponse<ID extends StoreId> {
  private Integer totalCnt;
  private StoreInfoDto<ID> storeInfoDto;
}
