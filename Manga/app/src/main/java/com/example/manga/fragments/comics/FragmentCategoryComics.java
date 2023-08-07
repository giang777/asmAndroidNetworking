package com.example.manga.fragments.comics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.manga.R;
import com.example.manga.adapter.TabAdapter;
import com.example.manga.interface_item.SenDataTabLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class FragmentCategoryComics extends Fragment {
    private TabLayout tabLayout;
    private TabAdapter tabAdapter;
    private ViewPager2 viewPager2;
    private ImageView btn_back;

    private FragmentListComics fragmentListComics;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_comics,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.tabLayout = view.findViewById(R.id.tab_list_comic);
        this.viewPager2 = view.findViewById(R.id.view_list_comic);
        this.tabAdapter = new TabAdapter(getActivity());
        this.btn_back = view.findViewById(R.id.btn_back);
        this.fragmentListComics = new FragmentListComics();

        viewPager2.setAdapter(tabAdapter);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Tất cả");
                        break;
                    case 1:
                        tab.setText("Shounen");
                        break;
                    case 2:
                        tab.setText("Isekai");
                        break;
                    case 3:
                        tab.setText("Love");
                        break;
                    case 4:
                        tab.setText("School");
                        break;
                }
            }
        }).attach();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }
}
