package fga.mds.gpp.dojotestapplication.DAO;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import fga.mds.gpp.dojotestapplication.R;

class WebClient {

    public String post(String json) {
        String url = "https://trezentos-api.herokuapp.com/";
        return performsRequisition(json, url);
    }

    void insertUser(String json) {
//        String url = "http://192.168.0.12:3000/api/user/register";
        String url = "https://trezentos-api.herokuapp.com/api/user/register";
        performsRequisition(json, url);

    }

    @Nullable
    private String performsRequisition(String json, String urlToRequest) {
        try {
            URL url = new URL(urlToRequest);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            connection.setDoOutput(true);

            PrintStream output = new PrintStream(connection.getOutputStream());
            output.println(json);

            connection.connect();

            Scanner scanner = new Scanner(connection.getInputStream());
            return scanner.next(); //return response
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
