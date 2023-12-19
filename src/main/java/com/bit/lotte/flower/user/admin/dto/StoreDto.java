package com.bit.lotte.flower.user.admin.dto;

import com.bit.lotte.flower.user.common.valueobject.StoreId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StoreDto<ID extends StoreId> {
  private ID storeId;
  private String storeCode;
  private String storeName;
  private String detailInfo;
  private String storeThumbnailInfo;
  private Float averageRating;
  private String phoneNumber;
  private String accountNumber;
  private String bank;
}
