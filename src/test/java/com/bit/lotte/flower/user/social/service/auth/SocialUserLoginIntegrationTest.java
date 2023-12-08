package com.bit.lotte.flower.user.social.service.auth;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bit.lotte.flower.user.common.valueobject.UserId;
import com.bit.lotte.flower.user.social.dto.command.UserLoginCommand;
import com.bit.lotte.flower.user.social.dto.response.UserLoginDataResponse;
import com.bit.lotte.flower.user.social.entity.SocialUser;
import com.bit.lotte.flower.user.social.repository.SocialUserJpaRepository;
import com.bit.lotte.flower.user.social.service.SocialUserCreateService;
import com.bit.lotte.flower.user.social.service.SocialUserLoginManager;
import com.bit.lotte.flower.user.social.service.SocialUserLoginResponseService;
import com.bit.lotte.flower.user.social.service.SocialUserLoginWhenUserExist;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@SpringBootTest
@ExtendWith(SpringExtension.class)
class SocialUserLoginIntegrationTest {

  Long testOauthId = 1L;

  Random random;

  @Autowired
  SocialUserLoginManager loginManager;
  @Autowired
  SocialUserJpaRepository repository;
  @Autowired
  SocialUserCreateService socialUserCreateService;
  @Autowired
  SocialUserLoginWhenUserExist socialUserLoginWhenUserExist;
  @Autowired
  SocialUserLoginResponseService socialUserLoginResponseService;

  private static UserLoginCommand getCommand(Long randomLongValue) {
    return UserLoginCommand.builder().email("test@gmail.com").nickname("testNickname")
        .socialId(new UserId(randomLongValue)).build();
  }

  @BeforeEach
  void setRandom() {
    random = new Random();
  }

  @DisplayName("유저가 존재하지 않을 때 유저 생성 테스트")
  @Test
  void SocialUserLogin_WhenUserIsNotRegistered_CreateUserAndGetUserDataResponse() {
    Long userId = getCommand(testOauthId).getSocialId().getValue();
    UserLoginDataResponse response = loginManager.process(getCommand(userId));
    assertTrue(repository.findById(userId).isPresent());
    assertNotNull(response);

  }

  @DisplayName("유저가 존재하고 유저의 상태가 모두 isDeleted인 상태인 경우 새로운 계정 생성하는 테스트 ")
  @Test
  void SocialUserLogin_WhenUserIsRegisteredAndSameIdUserIsAllDeleted_CreateNewUserWhichIsNotDeletedAndGetResponse() {
    List<SocialUser> deletedUsers = setAllUsersByStatus(true, testOauthId);
    repository.saveAll(deletedUsers);

    Long userId = getCommand(testOauthId).getSocialId().getValue();
    repository.save(saveSocialUserById(testOauthId));

    UserLoginDataResponse response = loginManager.process(getCommand(userId));

    List<SocialUser> notDeletedUsers = repository.findAllByOauthId(testOauthId)
        .stream()
        .filter(user -> !user.getIsDeleted())
        .collect(Collectors.toList());
    assertEquals(1, notDeletedUsers.size());
    assertNotNull(response);

  }

  @DisplayName("유저가 존재하고 하나의 유저의 상태가 IsDeleted가 false인 경우 새로운 계정 생성을 하지 않는 테스트")
  @Test
  void SocialUserLogin_WhenUserIsRegisteredAndThereIsNotDeletedUser_NotCreateUserSameSizeAndGetResponse() {
    repository.saveAll(setTwoUserOneUserIsNotDeletedStatus(testOauthId));
    int sizeBeforeInput = repository.findAllByOauthId(testOauthId).size();
    UserLoginDataResponse response = loginManager.process(getCommand(testOauthId));
    int sizeAfterLoginProcess = repository.findAllByOauthId(testOauthId).size();

    assertEquals(sizeBeforeInput, sizeAfterLoginProcess);
    assertNotNull(response);

  }

  private List<SocialUser> setAllUsersByStatus(Boolean status, Long oauthId) {
    List<SocialUser> deletedUsers = new ArrayList<>();
    SocialUser user1 = SocialUser.builder()
        .id(1L)
        .oauthId(oauthId)
        .nickname("User1")
        .profileImage("profile1.jpg")
        .phoneNumber("1234567890")
        .isDeleted(status)
        .build();
    deletedUsers.add(user1);

    SocialUser user2 = SocialUser.builder()
        .id(2L)
        .oauthId(oauthId)
        .nickname("User2")
        .profileImage("profile2.jpg")
        .phoneNumber("9876543210")
        .isDeleted(status)
        .build();
    deletedUsers.add(user2);

    return deletedUsers;
  }

  private List<SocialUser> setTwoUserOneUserIsNotDeletedStatus(Long oauthId) {
    List<SocialUser> deletedUsers = new ArrayList<>();
    SocialUser user1 = SocialUser.builder()
        .id(1L)
        .oauthId(oauthId)
        .nickname("User1")
        .profileImage("profile1.jpg")
        .phoneNumber("1234567890")
        .isDeleted(false)
        .build();
    deletedUsers.add(user1);

    SocialUser user2 = SocialUser.builder()
        .id(2L)
        .oauthId(oauthId)
        .nickname("User2")
        .profileImage("profile2.jpg")
        .phoneNumber("9876543210")
        .isDeleted(true)
        .build();
    deletedUsers.add(user2);

    return deletedUsers;
  }


  private SocialUser saveSocialUserById(Long id) {
    return SocialUser.builder().oauthId(id).profileImage("test.jpg").phoneNumber("1245")
        .nickname("testNickname").isDeleted(false).build();
  }


}

