package com.example.manga.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.manga.MainActivity;
import com.example.manga.R;
import com.example.manga.elements.child.User;
import com.example.manga.services.LoginService;

public class FragmentLogin extends Fragment {
    private AppCompatButton button;
    private EditText email,passwrd;
    private TextView goToRegister;
    private IntentFilter filter;

    private String TAG = "FRAGMENT_LOGIN";
    private ProgressBar progressBar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.email = view.findViewById(R.id.email_Login);
        this.passwrd = view.findViewById(R.id.passwrd_login);
        this.goToRegister = view.findViewById(R.id.tv_GoToRegister);
        this.button = view.findViewById(R.id.btn_Login);
        this.progressBar = view.findViewById(R.id.progressBar);
        this.filter = new IntentFilter(LoginService.ACTION_LOGIN);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(resultReceiver, filter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getContext(), LoginService.class);
                intent.setAction(LoginService.ACTION_LOGIN);
                intent.putExtra(LoginService.EXTRA_EMAIL, email.getText().toString());
                intent.putExtra(LoginService.EXTRA_PASSWRD, passwrd.getText().toString());
                getContext().startService(intent);

            }
        });

        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fragment_open,R.anim.fragment_exit).replace(R.id.fragment_container,new FragmentRegister()).addToBackStack(TAG).commit();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(resultReceiver,this.filter);
    }


    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(resultReceiver);
    }

    private BroadcastReceiver resultReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (LoginService.ACTION_LOGIN.equals(intent.getAction())) {
                boolean result = intent.getBooleanExtra(LoginService.RESULT,false);
                String msg = intent.getStringExtra(LoginService.MESSAGE);
                User user = (User) intent.getSerializableExtra(LoginService.USER);
                progressBar.setVisibility(View.GONE);
                MainActivity.UserLogin = user;
                if(result){
                    getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fragment_open,R.anim.fragment_exit).replace(R.id.fragment_container,new FragmentHome()).addToBackStack(TAG).commit();
                }else{
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

}
