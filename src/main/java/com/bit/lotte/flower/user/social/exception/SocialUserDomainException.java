package com.bit.lotte.flower.user.social.exception;

import bloomingblooms.errors.DomainException;

public class SocialUserDomainException extends DomainException {
  public SocialUserDomainException(String service) {
    super(service);
  }
}
