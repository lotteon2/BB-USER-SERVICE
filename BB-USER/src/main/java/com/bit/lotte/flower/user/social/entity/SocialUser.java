package com.bit.lotte.flower.user.social.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SocialUser {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Long oauthId;
  private String nickname;
  private String profileImage;
  private String phoneNumber;
  private Boolean isDeleted;
}
