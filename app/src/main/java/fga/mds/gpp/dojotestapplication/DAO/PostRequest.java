package fga.mds.gpp.dojotestapplication.DAO;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import fga.mds.gpp.dojotestapplication.Model.User;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostRequest extends AsyncTask<String, String, String> {

    private User user;
    private String url;
    private Context context;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private String responseCode;

    public PostRequest(User user, String url, Context context){
        this.user = user;
        this.url = url;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        builder.addQueryParameter("email", user.getName() + "@" + user.getAge() + ".com");
        builder.addQueryParameter("password", user.getAge().toString());
        builder.addQueryParameter("name", user.getName());
        String urlWithParams = builder.build().toString();

        RequestBody body = RequestBody.create(null, "");
        Request request = new Request.Builder()
                .url(urlWithParams)
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            responseCode = String.valueOf(response.code());
            return response.body().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Toast.makeText(context, responseCode, Toast.LENGTH_SHORT).show();
    }
}
