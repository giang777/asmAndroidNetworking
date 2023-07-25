package com.example.manga.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.manga.MainActivity;
import com.example.manga.R;
import com.squareup.picasso.Picasso;

public class FragmentProfile extends Fragment {

    private ImageView avatar;
    private TextView username;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.avatar = view.findViewById(R.id.img_avatar_UserLogin);
        this.username = view.findViewById(R.id.name_UserLogin);

        try {
            Picasso.get().load(MainActivity.UserLogin.getAvatar()).into(avatar);
        }catch (Exception e){

        }

        username.setText(MainActivity.UserLogin.getUsername());


    }
}
