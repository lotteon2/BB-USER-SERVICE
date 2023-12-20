package com.bit.lotte.flower.user.admin.dto;


import com.bit.lotte.flower.user.common.valueobject.StoreId;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor

@Getter
public class StoreManagerApplicationData {
  private Long key;
  private String storeManagerName;
  private String storeManagerBusinessNumber;
  private LocalDateTime requestDate;
}
