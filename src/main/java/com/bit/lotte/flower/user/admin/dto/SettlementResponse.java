package com.bit.lotte.flower.user.admin.dto;

import com.bit.lotte.flower.user.common.valueobject.StoreId;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SettlementResponse<ID extends StoreId> {

  private Integer totalCnt;
  private Integer year;
  private Integer month;
  private StoreDto<ID> store;
  List<SettlementDto> settlementDtoList;
}
