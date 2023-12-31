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
        .phoneNumber(userCreateCommand.getPhoneNumber()).profileImage(DefaultProfileImagerURL.PROFILE_DEFAULT_IMAGE_URL).oauthId(
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
        .email(socialUser.getEmail()).nickname(socialUser.getNickname()).profileImage(socialUser.getProfileImage())
        .phoneNumber(socialUser.getPhoneNumber()).build();
  }



  public static SocialUser updateUserInfo(SocialUser socialUser, String nickname, String email,
      String phoneNumber) {
    return SocialUser.builder().id(socialUser.getId()).oauthId(socialUser.getOauthId())
        .profileImage(socialUser.getProfileImage()).phoneNumber(phoneNumber).email(email)
        .nickname(nickname).isDeleted(socialUser.getIsDeleted()).build();
  }

    public static SocialUser changeUserStatus(SocialUser socialUser,Boolean status ) {
    return SocialUser.builder().id(socialUser.getId()).oauthId(socialUser.getOauthId())
        .profileImage(socialUser.getProfileImage()).phoneNumber(socialUser.getPhoneNumber()).email(socialUser.getEmail())
        .nickname(socialUser.getNickname()).isDeleted(status).build();
  }
}
