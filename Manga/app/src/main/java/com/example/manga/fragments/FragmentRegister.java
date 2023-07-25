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
import com.example.manga.services.RegisterService;

public class FragmentRegister extends Fragment {
    private EditText username,email,passwrd,passwrd2;
    private TextView goToLogin;
    private AppCompatButton button;
    private IntentFilter filter;

    private String TAG = "FRAGMENT_REGISTER";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.username = view.findViewById(R.id.name_Register);
        this.email = view.findViewById(R.id.email_Register);
        this.passwrd = view.findViewById(R.id.passwrd_Register);
        this.passwrd2 = view.findViewById(R.id.passwrd2_Register);
        this.goToLogin = view.findViewById(R.id.tv_GoToLogin);
        this.button = view.findViewById(R.id.btn_Register);

        this.filter = new IntentFilter(RegisterService.ACTION_REGISTER);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(resultReceiver, filter);

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),RegisterService.class);
                intent.setAction(RegisterService.ACTION_REGISTER);
                intent.putExtra(RegisterService.EXTRA_USERNAME,username.getText().toString());
                intent.putExtra(RegisterService.EXTRA_EMAIL,email.getText().toString());
                intent.putExtra(RegisterService.EXTRA_PASSWRD,passwrd.getText().toString());
                intent.putExtra(RegisterService.EXTRA_PASSWRD2,passwrd2.getText().toString());
                getActivity().startService(intent);
            }
        });
    }

    private BroadcastReceiver resultReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (RegisterService.ACTION_REGISTER.equals(intent.getAction())) {
                boolean result = intent.getBooleanExtra(RegisterService.EXTRA_RESULT,false);
                String msg = intent.getStringExtra(RegisterService.EXTRA_MSG);
                User user = (User) intent.getSerializableExtra(RegisterService.EXTRA_USER);
                MainActivity.UserLogin = user;
                if(result){
                    getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fragment_open,R.anim.fragment_exit).replace(R.id.fragment_container,new FragmentHome()).addToBackStack(TAG).commit();
                }else{
                    Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

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
}
