package com.bit.lotte.flower.user.store.handler;

import com.bit.lotte.flower.user.common.valueobject.UserId;
import com.bit.lotte.flower.user.store.dto.command.UpdateBusinessNumberCommand;
import com.bit.lotte.flower.user.store.http.message.InitStoreManagerAuthorizationPublisher;
import com.bit.lotte.flower.user.store.http.message.InitStoreManagerStatusSqsPublisher;
import com.bit.lotte.flower.user.store.service.StoreManagerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StoreManagerStatusInitHandler {

  private final StoreManagerService<UserId> storeManagerService;
  private final InitStoreManagerAuthorizationPublisher<UserId> publisher;
  private final InitStoreManagerStatusSqsPublisher initStoreManagerStatusSqsPublisher;


  @Transactional
  public void initStoreManagerStatus(UpdateBusinessNumberCommand command)
      throws JsonProcessingException {
    UserId storeId = storeManagerService.updateBusinessNumber(command.getEmail(),
        command.getBusinessNumberImage());
    publisher.publish(storeId);
    initStoreManagerStatusSqsPublisher.publish();
  }

}
