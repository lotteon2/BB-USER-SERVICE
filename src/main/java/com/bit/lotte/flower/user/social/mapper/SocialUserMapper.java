package com.bit.lotte.flower.user.social.mapper;


import com.bit.lotte.flower.user.common.DefaultProfileImagerURL;
import com.bit.lotte.flower.user.social.dto.command.UserLoginCommand;
import com.bit.lotte.flower.user.social.dto.response.UserLoginDataResponse;
import com.bit.lotte.flower.user.social.dto.response.UserDataDto;
import com.bit.lotte.flower.user.social.entity.SocialUser;

public class SocialUserMapper {


  private SocialUserMapper()
  {

  }

  public static UserLoginDataResponse getLoginResponse(String nickName,
      boolean isPhoneNumberRegistered) {
    return UserLoginDataResponse.builder()
        .nickname(nickName)
        .isPhoneNumberIsRegistered(isPhoneNumberRegistered).build();

  }


  public static SocialUser createSocialUserByLoginCommand(UserLoginCommand userCreateCommand) {
    return SocialUser.builder().isDeleted(false).nickname(userCreateCommand.getNickname()).email(
            userCreateCommand.getEmail())
        .phoneNumber(null).profileImage(DefaultProfileImagerURL.PROFILE_DEFAULT_IMAGE_URL).oauthId(
            userCreateCommand.getSocialId().getValue()).build();
  }

  public static UserLoginDataResponse createUserLoginCommandBySocialUser(String profileImage,
      String nickname, Boolean socialUserPhoneIsRegistered) {
    return UserLoginDataResponse.builder().isPhoneNumberIsRegistered(socialUserPhoneIsRegistered)
        .nickname(
            nickname).profileImage(profileImage).build();
  }

  public static UserDataDto socialUserToUserMyPageDataResponse(SocialUser socialUser) {
    return UserDataDto.builder()
        .email(socialUser.getEmail()).nickname(socialUser.getNickname())
        .phoneNumber(socialUser.getPhoneNumber()).build();
  }

  public static SocialUser updatedUserPhoneNumber(SocialUser socialUser, String phoneNumber) {
    return SocialUser.builder().email(socialUser.getEmail()).isDeleted(socialUser.getIsDeleted())
        .nickname(
            socialUser.getNickname())
        .phoneNumber(phoneNumber).profileImage(socialUser.getProfileImage())
        .oauthId(socialUser.getOauthId()).id(socialUser.getId()).build();
  }


  public static SocialUser updateUserInfo(SocialUser socialUser, String nickname, String email,
      String phoneNumber) {
    return SocialUser.builder().id(socialUser.getId()).oauthId(socialUser.getOauthId())
        .profileImage(socialUser.getProfileImage()).phoneNumber(phoneNumber).email(email)
        .nickname(email).isDeleted(socialUser.getIsDeleted()).build();
  }
}
