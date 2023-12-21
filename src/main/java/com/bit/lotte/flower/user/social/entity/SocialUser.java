package com.bit.lotte.flower.user.social.entity;

import com.bit.lotte.flower.user.common.entity.BaseEntity;
import javax.persistence.Column;
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
public class SocialUser extends BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long oauthId;
  private String email;
  private String nickname;
  private String profileImage;
  private String phoneNumber;
  private Boolean isDeleted;
}
