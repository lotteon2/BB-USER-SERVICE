package com.bit.lotte.flower.user.social.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
