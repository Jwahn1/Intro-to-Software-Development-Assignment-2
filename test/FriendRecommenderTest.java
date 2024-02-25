import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class FriendRecommenderTest {
  private static String[] friendNames = {"Alice", "Bob", "Carol"};

  // clear the static users HashMap before each test
  @AfterEach
  void clearUsers() {
    User.users.clear();
  }

  // recommending friends between two users where the second has another friend should recommend that friend
  @Test
  void testTwoFriends() {
    User u = new User(friendNames[0]);
    User f = new User(friendNames[1]);
    User g = new User(friendNames[2]);
    u.friend(f.name);
    f.friend(g.name);
    FriendRecommender fr = new FriendRecommender();
    ArrayList<String> al = new ArrayList<String>();
    fr.makeRecommendations(u, f, al);
    assertEquals(1, al.size(), "Wrong recommendation count");
    assertEquals(u.name + " and " + g.name + " should be friends", al.get(0), "Incorrect recommendation");
  }

  /*
   Test checks whether Returned arraylist is empty and whether the returned message is the expected one as specified in
   function description
   */
  @Test
  void testMakeRecommendations() {
    User u = new User("A");
    User f = new User(friendNames[1]);
    User g = new User("B");

    //the final message should be "A and B should be friends"
    f.friend(g.name);
    u.friend(f.name);
    
    FriendRecommender fr = new FriendRecommender();
    ArrayList<String> recommended = new ArrayList<String>();

    fr.makeRecommendations(u,f,recommended);

    //checks whether the arraylist is empty guarding the below assert
    assertNotEquals(0,recommended.size(),"the function returns no message");

    //checks whether the returned message is as expected
    assertEquals(recommended.get(0),"A and B should be friends","" +
    "The function does not return a message recommending the friends for A");




  }

  // add more tests as needed using white-box, black-box or a mix of testing strategies
}