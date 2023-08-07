package com.example.manga.api;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.util.Log;

import com.example.manga.MainActivity;
import com.example.manga.api.LinkApi;
import com.example.manga.elements.child.Chapters;
import com.example.manga.elements.child.Comics;
import com.example.manga.elements.child.Follow;
import com.example.manga.elements.child.User;
import com.example.manga.elements.get.Data_Response_ListChapter;
import com.example.manga.elements.get.Data_Response_ListComics;
import com.example.manga.elements.post.Data_Response_FollowComic;
import com.example.manga.elements.post.Data_Response_UserLogin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersApi {

    public Data_Response_UserLogin postUserLogin(String username,String email,String passwrd,String urlPost){
        String data = "";
        try {
            URL url = new URL(LinkApi.URI + urlPost);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/json");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username",username);
            jsonObject.put("email",email);
            jsonObject.put("passwrd",passwrd);

            OutputStream outputStream = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(  outputStream )   );
            writer.append(jsonObject.toString());
            writer.flush();
            writer.close();
            outputStream.close();

            InputStream inputStream = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String dong;

            while(  (dong = reader.readLine()) != null  ){
                builder.append( dong ).append("\n");
            }

            reader.close();
            inputStream.close();
            conn.disconnect();
            data = builder.toString();

            Gson gson = new Gson();
            Data_Response_UserLogin data_response_userLogin = gson.fromJson(data,Data_Response_UserLogin.class);

            return data_response_userLogin;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Data_Response_FollowComic patchUserFlComic(Follow follow){
        String data = "";
        try {
            URL url = new URL(LinkApi.URI + LinkApi.PATCH_USER_FOLLOW + follow.get_id());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PATCH");
            conn.setRequestProperty("Content-Type","application/json");

            Gson gson = new Gson();
            String json = gson.toJson(follow);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("listComics",json);
            jsonObject.put("isFollow",follow.isFollow());
            OutputStream outputStream = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(  outputStream )   );
            writer.append(jsonObject.toString());
            writer.flush();
            writer.close();
            outputStream.close();

            InputStream inputStream = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String dong;

            while(  (dong = reader.readLine()) != null  ){
                builder.append( dong ).append("\n");
            }

            reader.close();
            inputStream.close();
            conn.disconnect();
            data = builder.toString();

            Gson gson1 = new Gson();
            Data_Response_FollowComic dataResponseFollowComic = gson1.fromJson(data,Data_Response_FollowComic.class);

            return dataResponseFollowComic;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
