package com.example.manga.api;

import com.example.manga.MainActivity;
import com.example.manga.api.LinkApi;
import com.example.manga.elements.child.Blogs;
import com.example.manga.elements.child.Comics;
import com.example.manga.elements.get.Data_Response_ListBlogs;
import com.example.manga.elements.get.Data_Response_ListComics;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

public class BlogsApi {

    public List<Blogs> getDataBlogs(){
        String data = "";
        try {
            URL url = new URL(LinkApi.URI + LinkApi.GET_LIST_BLOGS );
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
            Data_Response_ListBlogs data_response_listBlogs = gson.fromJson(data,Data_Response_ListBlogs.class);
            List<Blogs> list = data_response_listBlogs.getData();

            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
