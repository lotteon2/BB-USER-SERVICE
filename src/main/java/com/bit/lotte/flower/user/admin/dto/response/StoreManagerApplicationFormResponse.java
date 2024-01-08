package com.bit.lotte.flower.user.admin.dto.response;

import com.bit.lotte.flower.user.admin.dto.StoreManagerApplicationData;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StoreManagerApplicationFormResponse {
  private Integer totalCnt;
  private List<StoreManagerApplicationData> data;
}
