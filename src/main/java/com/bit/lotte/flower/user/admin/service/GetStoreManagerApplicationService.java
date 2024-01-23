package com.bit.lotte.flower.user.admin.service;


import com.bit.lotte.flower.user.admin.dto.StoreManagerApplicationData;
import com.bit.lotte.flower.user.admin.dto.response.StoreManagerApplicationFormResponse;
import com.bit.lotte.flower.user.store.entity.StoreManager;
import com.bit.lotte.flower.user.store.repository.StoreManagerJpaRepository;
import com.bit.lotte.flower.user.store.service.FindStoreMangerService;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    List<StoreManagerApplicationData> data = mapToData(storeManagerListByStatus);
    Page<StoreManagerApplicationData> dataByPage = getPageOfData(data, pageable);
    return StoreManagerApplicationFormResponse.builder()
        .data(dataByPage.getContent()).totalCnt(data.size()).build();
  }

  private Page<StoreManagerApplicationData> getPageOfData(List<StoreManagerApplicationData> data,
      Pageable pageable) {
    int pageSize = pageable.getPageSize();
    int pageNumber = pageable.getPageNumber();
    int fromIndex = pageNumber * pageSize;
    int toIndex = Math.min(fromIndex + pageSize, data.size());

    List<StoreManagerApplicationData> pageData = data.subList(fromIndex, toIndex);
    return new PageImpl<>(pageData, pageable, data.size());
  }

  public List<StoreManagerApplicationData> mapToData(List<StoreManager> managerList) {
    List<StoreManagerApplicationData> applicationDataList = new ArrayList<>();
    for (StoreManager storeManager : managerList) {
      applicationDataList.add(
          StoreManagerApplicationData.builder().key(storeManager.getId())
              .storeManagerName(storeManager.getName())
              .requestDate(storeManager.getUpdatedAt())
              .storeManagerBusinessNumber(storeManager.getBusinessNumberImage())
              .businessNumber(storeManager.getBusinessNumber()).build());
    }
    return applicationDataList;
  }

}
