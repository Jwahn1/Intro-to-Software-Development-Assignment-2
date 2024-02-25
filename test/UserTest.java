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

    assertNotNull(returnedUser,"returnedUser is null when it should return a User object");
    //above assert guards against compilation errors with the below assert
    assertEquals(returnedUser,testClient, "returnedUser object  doesn't match original inputted Object");

    //when given a name for a user that isn't in the hash map, it should return a null User object

    returnedUser = User.find(friendNames[2]);
    assertNull(returnedUser,"returned user object is not null when inputted name isn't in the hashmap");
  }



  /*test checks whether the Friend() function correctly returns the friended user, whether both user's adj hashmaps
  have each user added and whether friending a user that is already a friend doesn't change the status of each user's
  hash map
   */
  //note:the function description doesn't say anything about what happens when you input a string for a user
  //that doesn't exist, so I can't test it with black box method?
  @Test
  void testFriend() {
    User client = new User(friendNames[0]);
    User newFriend = new User(friendNames[1]);

    User returnedUser = client.friend(newFriend.name);

    //checks whether returnedUser is the same object as the inputted newFriend
    assertEquals(newFriend,returnedUser,"the returnedUser was not the inputted user");

    //checks whether client and the object added as newFriend's friend are both equal
    assertEquals(client.adj.get(newFriend.name),newFriend,"" +
    "newFriend and the User object added to adj as client's friend are not equal");


    //checks whether client and the object added as newFriend's friend are both equal
    assertEquals(newFriend.adj.get(client.name),client,"" +
    "client and the User object added to adj as newFriend's friend are not equal ");

    //friending a user again shouldn't change their status

    returnedUser = client.friend(newFriend.name);

    //checks whether returnedUser is the same object as the inputted newFriend
    assertEquals(newFriend,returnedUser,"the returnedUser was not the inputted user");

    //checks whether client and the object added as newFriend's friend are both equal
    assertEquals(client.adj.get(newFriend.name),newFriend,"" +
    "newFriend and the User object added to adj as client's friend are not equal");


    //checks whether client and the object added as newFriend's friend are both equal
    assertEquals(newFriend.adj.get(client.name),client,"" +
    "client and the User object added to adj as newFriend's friend are not equal ");

  }
  /*
  tests whether unfriend() function returns the correct user Object and whether both the client and the newFriend
  objects are removed from each others adj friend lists
   */
  @Test
  void testUnfriend() {
    User client = new User(friendNames[0]);
    User newFriend = new User(friendNames[1]);

    User returnedUser = client.friend(newFriend.name);

    client.unfriend(newFriend.name);

    //checks whether returnedUser is the same object as the inputted newFriend
    assertEquals(newFriend,returnedUser,"the returnedUser was not the inputted user");

    //checks whether client and the object added as newFriend's friend are both equal
    assertNotEquals(client.adj.get(newFriend.name),newFriend,"" +
    "newFriend is still in the client's adj friend list");

    //checks whether client and the object added as newFriend's friend are both equal
    assertNotEquals(newFriend.adj.get(client.name),client,"" +
    "client is still in the newFriend's adj friend list");
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