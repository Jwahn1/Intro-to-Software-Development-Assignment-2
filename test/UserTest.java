import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class UserTest {
  private static String[] friendNames = {"Alice", "Bob", "Carol"};

  // clear the static users HashMap before each test
  @AfterEach
  void clearUsers() {
    User.users.clear();
  }

  // Test constructing a user adds them to the HashMap
  @Test
  void testConstructor() {
    User u = new User(friendNames[0]);
    assertEquals(friendNames[0], u.name, "Incorrect name");
    assertEquals(1, User.users.size(), "Incorrect size");
    assertEquals(u, User.users.get(friendNames[0]), "User not in HashMap");
  }

  @Test
  /*
   tests the Find() function, tests whether the function returns the two specified values return
   values (null,wanted User object)
   */
  void testFind() {
    User testClient = new User(friendNames[0]);

    //use Find() function
    User returnedUser = User.find(testClient.name);

    //when given a name for a user that has been inputted into the hashmap, it should return a User object, not null
    //User.find("string").name looks into the user hashmap and uses the "string" as the key

    assertNotNull(returnedUser,"returned user object is null when User is in the hashmap");
    //above assert guards against compilation errors with the below assert
    assert returnedUser.name.equals(testClient.name): "returnedUser object name doesn't match original inputted name";

    //when given a name for a user that isn't in the hash map, it should return a null User object

    returnedUser = User.find(friendNames[2]);
    assertNull(returnedUser,"returned user object is not null when inputted name isn't in the hashmap");
  }

  @Test
  void testFriend() {
  }

  @Test
  void testUnfriend() {
  }

  @Test
  void testLeave() {
  }

  @Test
  void testIsFriend() {
  }

  // add more tests as needed using white-box, black-box or a mix of testing strategies
  // Note: you need to add multiple tests for each method in User.java
}