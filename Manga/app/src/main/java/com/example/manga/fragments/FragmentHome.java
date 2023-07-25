package com.example.manga.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.manga.R;
import com.example.manga.fragments.home.FragmentMain;
import com.example.manga.fragments.home.FragmentNews;
import com.example.manga.fragments.home.FragmentProfile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class FragmentHome extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener{
    private BottomNavigationView bottomNavigationView;
    private final int MENU_TITEL_1 = R.id.menu_titel1;
    private final int MENU_TITEL_2 = R.id.menu_titel2;
    private final int MENU_TITEL_3 = R.id.menu_titel3;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.bottomNavigationView = view.findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
        replaceFragment(new FragmentMain(),"FRAGMENT_MAIN");
    }

    private void replaceFragment(Fragment fragment,String tag) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fragment_open,R.anim.fragment_exit);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.replace(R.id.fragment_container_view_tag,fragment);
        fragmentTransaction.commit();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

            if(id == MENU_TITEL_1){
                replaceFragment(new FragmentMain(),"FRAGMENT_MAIN");
            }else if(id == MENU_TITEL_2){
                replaceFragment(new FragmentNews(),"FRAGMENT_NEW");
            }else if(id == MENU_TITEL_3){
                replaceFragment(new FragmentProfile(),"FRAGMENT_PROFILE");
            }

        return true;
    }


}
