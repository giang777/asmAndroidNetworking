package com.example.manga.api;

import com.example.manga.elements.child.User;
import com.example.manga.elements.get.Data_Response_ListBlogs;
import com.example.manga.elements.get.Data_Response_ListChapter;
import com.example.manga.elements.get.Data_Response_ListComics;
import com.example.manga.elements.post.Data_Response_UserLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class LinkApi {

    public final static String URI = "http://192.168.1.8:3000/";

    public final static String GET_LIST_COMICS = "comics/list";

    public final static String GET_LIST_BLOGS  = "blogs/list";

    public final static String GET_LIST_CHAPTERS = "chapters/?id=";

    public final static String POST_USER_LOGIN = "users/login";

    public final static String POST_USER_REGISTER = "users/register";

    public final static String GET_LIST_COMMENT = "comments/list?id=";

    public final static String POST_COMMENT = "comments/add";

}
