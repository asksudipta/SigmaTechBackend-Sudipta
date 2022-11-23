package se.sigma.sigmatechbackend.models.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserTest {

    @Test
    void createEmptyUser() {
        User user = new User();
        Assertions.assertNotNull(user);
    }


    @Test
    void testConstructorWithAllArgs() {
        User actualUser = new User(123, "Simon", "Andersson", "simon.andersson@example.org", 1, "simonanders", "iloveyou",
                LocalDate.parse("1990-01-01"));

        assertEquals("simon.andersson@example.org", actualUser.getEmail());
        assertEquals("simonanders", actualUser.getUserName());
        assertEquals(1, actualUser.getUserLevel().intValue());
        assertEquals(123, actualUser.getUserId().intValue());
        assertEquals("1990-01-01", actualUser.getRegDate().toString());
        assertEquals("iloveyou", actualUser.getPassword());
        assertEquals("Andersson", actualUser.getLastName());
        assertEquals("Simon", actualUser.getFirstName());
    }

    //Email//---------------------------------------------------------------------------------
    @Test
    void setUserEmailWithNullParam() {
        User user = new User();
        Assertions.assertThrows(IllegalArgumentException.class, () -> user.setEmail(null));
    }

    @Test
    void setUserEmailWithEmptyEmail() {
        User user = new User();
        Assertions.assertThrows(IllegalArgumentException.class, () -> user.setEmail(""));
    }

    //UserName//---------------------------------------------------------------------------------
    @Test
    void setUserNameWithCorrectParam() {
        User user = new User();
        String expected = "Tomas";
        user.setUserName(expected);
        Assertions.assertEquals(expected, user.getUserName());
    }

    @Test
    void setUserNameWithNullParam() {
        User user = new User();
        Assertions.assertThrows(IllegalArgumentException.class, () -> user.setUserName(null));
    }

    @Test
    void setUserNameWithEmptyName() {
        User user = new User();
        Assertions.assertThrows(IllegalArgumentException.class, () -> user.setUserName(""));
    }

    //UserId//---------------------------------------------------------------------------------
    @Test
    void testSetUserId() {
        User user = new User();
        user.setUserId(123);
        assertEquals(123, user.getUserId().intValue());
    }

    @Test
    void testSetUserId2() {
        assertThrows(IllegalArgumentException.class, () -> (new User()).setUserId(-1));
    }

    //RegDate//---------------------------------------------------------------------------------
    @Test
    void setUserRegdateWithNullParam() {
        User user = new User();
        Assertions.assertThrows(IllegalArgumentException.class, () -> user.setRegDate(null));
    }

    //FirstName//---------------------------------------------------------------------------------
    @Test
    void setFirstNameWithCorrectParam() {
        User user = new User();
        String expected = "Tomas";
        user.setFirstName(expected);
        Assertions.assertEquals(expected, user.getFirstName());
    }

    @Test
    void setFirstNameWithNullParam() {
        User user = new User();
        Assertions.assertThrows(IllegalArgumentException.class, () -> user.setFirstName(null));
    }

    @Test
    void setFirstNameWithEmptyName() {
        User user = new User();
        Assertions.assertThrows(IllegalArgumentException.class, () -> user.setFirstName(""));
    }

    //LastName//---------------------------------------------------------------------------------
    @Test
    void setLastNameWithCorrectParam() {
        User user = new User();
        String expected = "Tomas";
        user.setLastName(expected);
        Assertions.assertEquals(expected, user.getLastName());
    }

    @Test
    void setLastNameWithNullParam() {
        User user = new User();
        Assertions.assertThrows(IllegalArgumentException.class, () -> user.setLastName(null));
    }

    @Test
    void setLastNameWithEmptyName() {
        User user = new User();
        Assertions.assertThrows(IllegalArgumentException.class, () -> user.setLastName(""));
    }

    //Password//---------------------------------------------------------------------------------
    @Test
    void setPassword() {
        User user = new User();
        user.setPassword("iloveyou");
        assertEquals("iloveyou", user.getPassword());
    }

}
