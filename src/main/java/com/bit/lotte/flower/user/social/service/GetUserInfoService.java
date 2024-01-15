package com.bit.lotte.flower.user.social.service;

import com.bit.lotte.flower.user.common.valueobject.BaseId;
import com.bit.lotte.flower.user.social.dto.response.UserDataDto;
import org.springframework.stereotype.Service;

@Service
public interface GetUserInfoService<ID extends BaseId> {
  UserDataDto getUserdata(ID id);
}
