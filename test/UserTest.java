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



  /*test checks whether the Friend() function correctly returns the friended user and whether both user's adj hashmaps
  have each user added
   */
  @Test
  void testFriendOnce() {
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



  }
  //test checks whether friending a user twice returns the same values as being friended once since the status
  //between both users shouldn't change
  @Test
  void testFriendTwice() {
    User client = new User(friendNames[0]);
    User newFriend = new User(friendNames[1]);

    //friends twice
    client.friend(newFriend.name);
    User returnedUser = client.friend(newFriend.name);

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

  /*
  tests whether the request User object is removed from both the user static hashmap and every other users adj friend list
   */
  @Test
  void testLeave() {
    User client = new User(friendNames[0]);
    User newFriend = new User(friendNames[1]);
    //store name of removed user for testing
    String removed = newFriend.name;

    client.friend(newFriend.name);

    //remove newFriend from social network
    User returnedUser = User.find(removed);

    //if removed, newFriend should be unfindable and should return null
    assertNull(returnedUser,"newFriend was not removed from the static hashmap");

    //newFriend should also not be in client's adj friend list
    returnedUser = client.adj.get(removed);
    assertNotEquals(returnedUser.name,removed,"newFriend is still in client's friend list after being removed");

  }

  /*
  test checks whether isFriend() returns true when both  users are friends and if it returns false when
  they are not friends
   */
  @Test
  void testIsFriend() {
    User client = new User(friendNames[0]);
    User newFriend = new User(friendNames[1]);

    client.friend(newFriend.name);

    //checks when newFriend is  in friendslist
    assert (client.isFriend(newFriend)) : "function doesn't return true when both users are friends";

    //remove newFriend from friends list
    User returnedUser = client.unfriend(newFriend.name);

    //checks when newFriend is not in friendslist
    assert !(client.isFriend(newFriend)) : "function doesn't return false when both users aren't friends";
  }

  // add more tests as needed using white-box, black-box or a mix of testing strategies
  // Note: you need to add multiple tests for each method in User.java
}