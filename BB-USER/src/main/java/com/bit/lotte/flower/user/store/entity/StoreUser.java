package com.bit.lotte.flower.user.store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StoreUser {

  @Id
  private Long id;
  private String email;
  private String businessNumberImage;
}
