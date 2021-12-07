package com.example.synctheory.Login;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import com.example.synctheory.R;

public class LoginActivity extends AppCompatActivity {
    LoginContract.View mView;
    LoginContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        mView = (LoginContract.View) getSupportFragmentManager().findFragmentById(R.id.fragmentLoginView);
        mPresenter = new LoginPresenter();
        mPresenter.setView(mView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.start();
    }
}