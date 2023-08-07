package com.example.manga.api;

import android.util.Log;

import com.example.manga.MainActivity;
import com.example.manga.api.LinkApi;
import com.example.manga.elements.child.Blogs;
import com.example.manga.elements.child.Chapters;
import com.example.manga.elements.get.Data_Response_Comics;
import com.example.manga.elements.get.Data_Response_ListBlogs;
import com.example.manga.elements.get.Data_Response_ListChapter;
import com.example.manga.elements.get.Data_Response_ListComics;
import com.example.manga.elements.child.Comics;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ComicsApi {


   public List<Comics> getDataComics(String category){
       String data = "";
       try {
           URL url = new URL(LinkApi.URI + LinkApi.GET_LIST_COMICS + category);
           HttpURLConnection conn = (HttpURLConnection) url.openConnection();

           InputStream inputStream = conn.getInputStream();

           BufferedReader reader = new BufferedReader( new InputStreamReader(  inputStream )   );

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
           Data_Response_ListComics data_response_listComics = gson.fromJson(data,Data_Response_ListComics.class);
           List<Comics> list = data_response_listComics.getData();

           return list;
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
   }

    public Chapters getDataChapter(String id){
        String data = "";
        try {
            URL url = new URL(LinkApi.URI + LinkApi.GET_LIST_CHAPTERS + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            InputStream inputStream = conn.getInputStream();

            BufferedReader reader = new BufferedReader( new InputStreamReader(  inputStream )   );

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
            Data_Response_ListChapter data_response_listChapter = gson.fromJson(data,Data_Response_ListChapter.class);
            Chapters chapters = data_response_listChapter.getData();

            return chapters;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateViewOrFollowConmic(String action,String id,boolean isFollow){
        String data = "";
        try {
            URL url = new URL(LinkApi.URI + LinkApi.PATCH_COMIC_UPDATE + action);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PATCH");
            conn.setRequestProperty("Content-Type","application-json");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",id);
            jsonObject.put("isFollow",isFollow);
            OutputStream outputStream = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(outputStream));
            writer.append(jsonObject.toString());
            writer.flush();
            writer.close();
            outputStream.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void updateViewOrFollowConmic(String action,String id){
        String data = "";
        try {
            URL url = new URL(LinkApi.URI + LinkApi.PATCH_COMIC_UPDATE + action);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PATCH");
            conn.setRequestProperty("Content-Type","application-json");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",id);
            OutputStream outputStream = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(outputStream));
            writer.append(jsonObject.toString());
            writer.flush();
            writer.close();
            outputStream.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Comics getComicsId(String id){
        String data = "";
        try {
            URL url = new URL(LinkApi.URI + LinkApi.GET_COMIC_ID + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            InputStream inputStream = conn.getInputStream();

            BufferedReader reader = new BufferedReader( new InputStreamReader(  inputStream )   );

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
            Data_Response_Comics dataResponseComics = gson.fromJson(data,Data_Response_Comics.class);
            Comics comics = dataResponseComics.getData();

            return comics;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
