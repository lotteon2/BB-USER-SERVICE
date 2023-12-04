package com.bit.lotte.flower.user.social.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bit.lotte.flower.user.common.valueobject.AuthId;
import com.bit.lotte.flower.user.social.dto.command.UserLoginCommand;
import com.bit.lotte.flower.user.social.entity.SocialUser;
import com.bit.lotte.flower.user.social.repository.SocialUserJpaRepository;
import jakarta.transaction.Transactional;
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
        .socialId(new AuthId(randomLongValue)).build();
  }

  @BeforeEach
  void setRandom() {
    random = new Random();
  }

  @DisplayName("유저가 존재하지 않을 때 유저 생성 테스트")
  @Test
  void SocialUserLogin_WhenUserIsNotRegistered_CreateUser() {
    Long userId = getCommand(testOauthId).getSocialId().getValue();
    loginManager.process(getCommand(userId));
    assertTrue(repository.findById(userId).isPresent());
  }

  @DisplayName("유저가 존재하고 유저의 상태가 모두 isDeleted인 상태인 경우 새로운 계정 생성 후 ")
  @Test
  void SocialUserLogin_WhenUserIsRegisteredAndSameIdUserIsAllDeleted_CreateNewUserWhichIsNotDeleted() {
    List<SocialUser> deletedUsers = setAllUsersByStatus(true, testOauthId);
    repository.saveAll(deletedUsers);
    Long userId = getCommand(testOauthId).getSocialId().getValue();
    repository.save(saveSocialUserById(testOauthId));
    loginManager.process(getCommand(userId));
    List<SocialUser> notDeletedUsers = repository.findAllByOauthId(testOauthId)
        .stream()
        .filter(user -> !user.getIsDeleted())
        .collect(Collectors.toList());
    assertEquals(1,notDeletedUsers.size());

  }

  @DisplayName("유저가 존재하고 유저의 상태가 IsDeleted가 false인 경우 새로운 계정 생성을 하지 않는 테스트")
  @Test
  void SocialUserLogin_WhenUserIsRegisteredAndThereIsNotDeletedUser_NotCreateUserSameSize() {

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



  private SocialUser saveSocialUserById(Long id) {
    return SocialUser.builder().oauthId(id).profileImage("test.jpg").phoneNumber("1245")
        .nickname("testNickname").isDeleted(false).build();
  }


}

