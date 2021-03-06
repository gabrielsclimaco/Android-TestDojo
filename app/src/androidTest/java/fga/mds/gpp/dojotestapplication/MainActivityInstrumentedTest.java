package fga.mds.gpp.dojotestapplication;

import android.support.test.rule.ActivityTestRule;
import android.widget.Toast;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fga.mds.gpp.dojotestapplication.Controller.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(JUnit4.class)
public class MainActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void shouldShowEditText() {
        onView(withId(R.id.name_edit_text)).perform(typeText("Carol"));
        onView(withId(R.id.age_edit_text)).perform(typeText("13"));
        onView(withId(R.id.submit_button)).perform(click());

        onView(withId(R.id.main_text)).check(matches(isDisplayed()));
        onView(withId(R.id.main_text)).check(matches(withText("Arrasou viado")));
    }

    @Test
    public void shouldSaveUser() {
        onView(withId(R.id.name_edit_text)).perform(typeText("Carol"));
        onView(withId(R.id.age_edit_text)).perform(typeText("13"));
        onView(withId(R.id.submit_button)).perform(click());

        MainActivity activity = rule.getActivity();
        onView(withText("200")).inRoot(withDecorView(not(is(activity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }
}
