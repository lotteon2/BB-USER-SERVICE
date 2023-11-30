package com.bit.lotte.flower.user.social.mapper;


import com.bit.lotte.flower.user.common.DefaultProfileImagerURL;
import com.bit.lotte.flower.user.social.dto.command.UserLoginCommand;
import com.bit.lotte.flower.user.social.dto.response.UserLoginResponse;
import com.bit.lotte.flower.user.social.entity.SocialUser;

public class SocialUserMapper {

  public static UserLoginResponse getLoginResponse(String nickName,
      boolean isPhoneNumberRegistered) {
    return UserLoginResponse.builder().nickName(nickName)
        .isPhoneNumberIsRegistered(isPhoneNumberRegistered).build();

  }

  public static SocialUser createSocialUserByLoginCommand(UserLoginCommand userCreateCommand) {
    return SocialUser.builder().isDeleted(false).nickname(userCreateCommand.getNickname())
        .phoneNumber(null).profileImage(DefaultProfileImagerURL.PROFILE_DEFAULT_IMAGE_URL).oauthId(
            userCreateCommand.getSocialId()).build();
  }
}
