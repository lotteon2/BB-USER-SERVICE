package com.bit.lotte.flower.user.common.valueobject;


import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
public class StoreId extends BaseId<Long> {

  public StoreId(Long value) {
    super(value);
  }
}
