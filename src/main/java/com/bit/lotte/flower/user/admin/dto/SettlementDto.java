package com.bit.lotte.flower.user.admin.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SettlementDto {

  private Long key;
  private String storeName;
  private Date settlementDate;
  private Long settlementAmount;
  private String bankName;
  private String accountNumber;

}
