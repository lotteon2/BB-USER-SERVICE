package com.bit.lotte.flower.user.admin.service;


import com.bit.lotte.flower.user.admin.dto.StoreManagerApplicationData;
import com.bit.lotte.flower.user.admin.dto.response.StoreManagerApplicationFormResponse;
import com.bit.lotte.flower.user.common.valueobject.StoreId;
import com.bit.lotte.flower.user.common.valueobject.StoreManagerStatus;
import com.bit.lotte.flower.user.social.repository.FindSocialUserByLongIdService;
import com.bit.lotte.flower.user.store.entity.StoreManager;
import com.bit.lotte.flower.user.store.repository.StoreManagerJpaRepository;
import com.bit.lotte.flower.user.store.service.FindStoreMangerService;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetStoreManagerApplicationService {

  private final FindStoreMangerService findStoreMangerService;

  @Transactional
  public StoreManagerApplicationFormResponse getApplications(List<Long> storeManagerIdList,
      Pageable pageable) {

    List<StoreManager> storeManagerListByStatus = new ArrayList<>();

    for (Long id : storeManagerIdList) {
      storeManagerListByStatus.add(findStoreMangerService.findByLongId(id));
    }
        List < StoreManagerApplicationData > data = mapToData(storeManagerListByStatus);

    return StoreManagerApplicationFormResponse.builder().data(data).totalCnt(data.size()).build();

  }

  public List<StoreManagerApplicationData> mapToData(List<StoreManager> managerList) {
    List<StoreManagerApplicationData> applicationDataList = new ArrayList<>();
    for (StoreManager storeManager : managerList) {
      applicationDataList.add(
          StoreManagerApplicationData.builder().key(storeManager.getId())
              .requestDate(storeManager.getUpdatedAt()).storeManagerName(storeManager.getName())
              .requestDate(storeManager.getCreatedAt())
              .storeManagerBusinessNumber(storeManager.getBusinessNumberImage()).build());
    }
    return applicationDataList;
  }

}
