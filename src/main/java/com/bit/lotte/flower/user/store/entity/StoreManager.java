package com.bit.lotte.flower.user.store.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class StoreManager {

  @Id
  private Long id;
  private String name;
  private String email;
  private String businessNumberImage;
}
