package com.bit.lotte.flower.user.social.dto.feign;

import com.bit.lotte.flower.user.common.valueobject.UserId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SoftDeleteSocialUserDto<ID extends UserId> {
  ID socialId;
}
