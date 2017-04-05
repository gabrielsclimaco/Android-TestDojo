package fga.mds.gpp.dojotestapplication.Controller;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import fga.mds.gpp.dojotestapplication.DAO.PostRequest;
import fga.mds.gpp.dojotestapplication.Exception.UserException;
import fga.mds.gpp.dojotestapplication.Model.User;
import fga.mds.gpp.dojotestapplication.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit_button: {
                saveUser();
            }
        }
    }

    private void saveUser() {
        //Gets the name typed in the view by the user
        EditText nameEditText= (EditText) findViewById(R.id.name_edit_text);
        String userName = nameEditText.getText().toString();

        //Gets the age typed in the view by the user
        EditText ageEditText = (EditText) findViewById(R.id.age_edit_text);
        String userAge = ageEditText.getText().toString();

        TextView mainText = (TextView) findViewById(R.id.main_text);

        try{
            User user = new User(userName, userAge);

            PostRequest postRequest = new PostRequest(user,
                    "https://trezentos-api.herokuapp.com/api/user/register",
                    getApplicationContext());
            postRequest.execute();

            mainText.setVisibility(View.VISIBLE);
        }
        catch(UserException userException){
            String errorMessage = userException.getMessage();

            if(errorMessage.equals(getString(R.string.emptyAgeErrorMessage))){
                ageEditText.requestFocus();
                ageEditText.setError(getString(R.string.emptyAgeErrorMessage));
            }

            if(errorMessage.equals(getString(R.string.emptyNameErrorMessage))){
                nameEditText.requestFocus();
                nameEditText.setError(getString(R.string.emptyNameErrorMessage));
            }

            if(errorMessage.equals(getString(R.string.maxNameErrorMessage))){
                nameEditText.requestFocus();
                nameEditText.setError(getString(R.string.maxNameErrorMessage));
            }

            mainText.setVisibility(View.GONE);
        }
    }

    private String createUserAuthentication(User user) {
        Uri.Builder builder = new Uri.Builder();
        builder.appendQueryParameter("email", user.getName() + "@" + user.getAge() + ".com");
        builder.appendQueryParameter("password", user.getAge().toString());
        builder.appendQueryParameter("name", user.getName());

        Log.d("LoginController", "createUserAuthentication ");

        return builder.build().getEncodedQuery();
    }
}
