package fga.mds.gpp.dojotestapplication;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import fga.mds.gpp.dojotestapplication.Controller.MainActivity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityUnityTest {

    private MainActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void checkIfButtonIsShowing() {

        EditText name = (EditText) activity.findViewById(R.id.name_edit_text);
        EditText age = (EditText) activity.findViewById(R.id.age_edit_text);

        name.setText("carol");
        age.setText("12");

        Button submit = (Button) activity.findViewById(R.id.submit_button);
        submit.performClick();

        TextView mainText = (TextView) activity.findViewById(R.id.main_text);
        assertThat(mainText.getVisibility(), is(View.VISIBLE));
    }
}
