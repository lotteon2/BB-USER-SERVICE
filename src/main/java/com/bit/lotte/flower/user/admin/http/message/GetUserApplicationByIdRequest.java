package com.bit.lotte.flower.user.admin.http.message;

import com.bit.lotte.flower.user.common.valueobject.StoreManagerStatus;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface GetUserApplicationByIdRequest {
  public List<Long> request(StoreManagerStatus storeManagerStatus);
}
