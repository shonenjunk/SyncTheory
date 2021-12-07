package com.example.synctheory.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import com.example.synctheory.Login.LoginContract;
import com.example.synctheory.Login.LoginPresenter;
import com.example.synctheory.R;
import android.os.Bundle;
import android.view.WindowManager;

public class SignUpActivity extends AppCompatActivity {

    SignUpContract.View mView;
    SignUpContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        mView = (SignUpContract.View) getSupportFragmentManager().findFragmentById(R.id.fragmentSignUpView);
        mPresenter = new SignUpPresenter();
        mPresenter.setView(mView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.start();
    }
}