package com.bit.lotte.flower.user.admin;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bit.lotte.flower.user.admin.dto.response.StoreManagerApplicationFormResponse;
import com.bit.lotte.flower.user.admin.service.GetStoreManagerApplicationService;
import com.bit.lotte.flower.user.store.entity.StoreManager;
import com.bit.lotte.flower.user.store.service.FindStoreMangerService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;


@ExtendWith(MockitoExtension.class)
class GetStoreManagerApplicationServiceTest {

  @Mock
  private FindStoreMangerService findStoreMangerService;

  @InjectMocks
  private GetStoreManagerApplicationService getStoreManagerApplicationService;

  @Test
  void testGetApplicationsWithPagination() {

    List<Long> storeManagerIdList = Arrays.asList(1L, 2L, 3L);
    Pageable pageable = Pageable.ofSize(2);

    List<StoreManager> mockedStoreManagerList = Arrays.asList(
        new StoreManager(1L, "Manager1", "12345", "test"),
        new StoreManager(2L, "Manager2", "67890", "test"),
        new StoreManager(3L, "Manager3", "ABCDE", "test")
    );

    when(findStoreMangerService.findByLongId(anyLong())).thenReturn(mockedStoreManagerList.get(0));

    StoreManagerApplicationFormResponse response = getStoreManagerApplicationService.getApplications(
        storeManagerIdList, pageable);

    verify(findStoreMangerService, times(storeManagerIdList.size())).findByLongId(anyLong());
    assertEquals(2,response.getData().size());

  }

}