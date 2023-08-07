package com.example.manga.api;

import com.example.manga.elements.child.Comics;
import com.example.manga.elements.child.Comments;
import com.example.manga.elements.child.Feedback;
import com.example.manga.elements.get.Data_Response_ListComics;
import com.example.manga.elements.get.Data_Response_ListComment;
import com.example.manga.elements.get.Data_Response_ListFeedback;
import com.example.manga.elements.post.Data_Response_Post;
import com.example.manga.elements.post.Data_Response_UserLogin;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class CommentApi {

    public List<Comments> getDataComment(String id,int limit){
        String data = "";
        try {
            URL url = new URL(LinkApi.URI + LinkApi.GET_LIST_COMMENT + id + "&limit=" +limit);
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
            Data_Response_ListComment dataResponseListComment = gson.fromJson(data,Data_Response_ListComment.class);
            List<Comments> list = dataResponseListComment.getData();

            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Data_Response_Post postComment(String cmt,String id_user,String id_comic){
        String data = "";
        try {
            URL url = new URL(LinkApi.URI + LinkApi.POST_COMMENT);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/json");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id_comic",id_comic);
            jsonObject.put("id_user",id_user);
            jsonObject.put("content",cmt);

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
            Data_Response_Post post = gson.fromJson(data,Data_Response_Post.class);

            return post;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public List<Feedback> getDataFeedBack(String id, int limit){
        String data = "";
        try {
            URL url = new URL(LinkApi.URI + LinkApi.GET_LIST_FEEDBACK + id + "&limit=" +limit);
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
            Data_Response_ListFeedback dataResponseListFeedback = gson.fromJson(data, Data_Response_ListFeedback.class);
            List<Feedback> list = dataResponseListFeedback.getData();

            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Data_Response_Post postFeedback(String cmt,String id_user,String id_blog){
        String data = "";
        try {
            URL url = new URL(LinkApi.URI + LinkApi.POST_FEEDBACK);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/json");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id_blog",id_blog);
            jsonObject.put("id_user",id_user);
            jsonObject.put("content",cmt);

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
            Data_Response_Post post = gson.fromJson(data,Data_Response_Post.class);

            return post;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
