package com.bit.lotte.flower.user.admin.http.message;

import com.bit.lotte.flower.user.admin.http.feign.GetUserApplicationsByStatusFeignRequest;
import com.bit.lotte.flower.user.common.valueobject.StoreManagerStatus;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetUserApplicationByIdRequestImpl implements
    GetUserApplicationByIdRequest {

  private final GetUserApplicationsByStatusFeignRequest feignRequest;


  @Override
  public List<Long> request(StoreManagerStatus storeManagerStatus) {
    return feignRequest.getStoreManagerApplications(storeManagerStatus);
  }
}
