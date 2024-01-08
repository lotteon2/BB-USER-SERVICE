package com.bit.lotte.flower.user.store.exception;

import bloomingblooms.errors.DomainException;

public class StoreUserDomainException extends DomainException {

  public StoreUserDomainException(String service) {
    super(service);
  }
}
