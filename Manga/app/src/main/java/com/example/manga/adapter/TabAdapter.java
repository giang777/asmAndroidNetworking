package com.example.manga.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.manga.fragments.comics.FragmentListComics;
import com.example.manga.fragments.comics.category.FragmantComicsLove;
import com.example.manga.fragments.comics.category.FragmentComicsIsekai;
import com.example.manga.fragments.comics.category.FragmentComicsSchool;
import com.example.manga.fragments.comics.category.FragmentComicsShounen;

public class TabAdapter extends FragmentStateAdapter {


    public TabAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentListComics();
                break;
            case 1:
                fragment = new FragmentComicsShounen();
                break;
            case 2:
                fragment = new FragmentComicsIsekai();
                break;
            case 3:
                fragment = new FragmantComicsLove();
                break;
            case 4:
                fragment = new FragmentComicsSchool();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
