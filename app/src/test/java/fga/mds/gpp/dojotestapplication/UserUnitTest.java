package fga.mds.gpp.dojotestapplication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import fga.mds.gpp.dojotestapplication.Controller.MainActivity;
import fga.mds.gpp.dojotestapplication.Exception.UserException;
import fga.mds.gpp.dojotestapplication.Model.User;

import static junit.framework.TestCase.assertFalse;

public class UserUnitTest {

    private User testUser;

    @Test
    public void shouldValidateNullName() throws UserException {
        boolean isValid = false;

        try {
            testUser = new User(null, "12");

            isValid = true;
        } catch (UserException userException) {
            isValid = false;
        }

        assertFalse(isValid);
    }

    @Test
    public void shouldValidateNullAge() throws UserException {
        boolean isValid = false;

        try {
            testUser = new User("nome", null);

            isValid = true;
        } catch (UserException userException) {
            isValid = false;
        }

        assertFalse(isValid);
    }

    @Test
    public void shouldValidateEmpityName() throws UserException {
        boolean isValid = false;

        try {
            testUser = new User("", "12");

            isValid = true;
        } catch (UserException userException) {
            isValid = false;
        }

        assertFalse(isValid);
    }

    @Test
    public void shouldValidateEmpityAge() throws UserException {
        boolean isValid = false;

        try {
            testUser = new User("acsdcsacsd", "");

            isValid = true;
        } catch (UserException userException) {
            isValid = false;
        }

        assertFalse(isValid);
    }
}
