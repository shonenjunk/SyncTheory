package com.example.synctheory.Login;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.synctheory.CourseList.CourseListActivity;
import com.example.synctheory.R;
import com.example.synctheory.SignUp.SignUpActivity;
import com.google.android.material.textfield.TextInputEditText;


public class LoginViewFragment extends Fragment implements LoginContract.View {

    private LoginContract.Presenter mPresenter;
    Button btnGo;
    Button btnNewUser;
    EditText etEmail;
    TextInputEditText etPassword;
    ImageView ivLogo;
    TextView tvMessage;

    public LoginViewFragment() {
        // Required empty public constructor
    }

    public static LoginViewFragment newInstance() {
        LoginViewFragment fragment = new LoginViewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public void onResume(){ super.onResume(); }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_login_view, container, false);

        //go button, launches the goClicked method that checks if user entered valid login information
        btnGo = root.findViewById(R.id.btnGo);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.goClicked();
            }
        });

        //new user button, launches sign-up viewer
        btnNewUser = root.findViewById(R.id.btnNewUser);
        btnNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.newUserClicked();
            }
        });

        //UI elements
        etEmail = root.findViewById(R.id.etEmail);
        etPassword = root.findViewById(R.id.etPassword);
        tvMessage = root.findViewById(R.id.tvMessage);
        ivLogo = root.findViewById(R.id.ivLogo);

        return root;
    }

    /**
     * createNewUser -- method that creates a new user in firebase based on the information entered.
     */
    @Override
    public void createNewUser() {
        Intent newUserIntent = new Intent(this.getContext(), SignUpActivity.class);
        Pair[] pairs = new Pair[4];
        pairs[0] = new Pair<View, String>(ivLogo, "logo_image");
        pairs[1] = new Pair<View, String>(tvMessage, "welcome_message");
        pairs[2] = new Pair<View, String>(etEmail, "email_transition");
        pairs[3] = new Pair<View, String>(etPassword, "password_transition");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this.getActivity(), pairs);
        startActivity(newUserIntent, options.toBundle());
    }

    /**
     * startCourseListActivity -- method that starts course list activity when user enters valid login information
     * @param isElevatedUser -- boolean indicating if user is a teacher or not
     */
    @Override
    public void startCourseListActivity(Boolean isElevatedUser) {
        Intent courseListIntent = new Intent(this.getContext(), CourseListActivity.class);
        courseListIntent.putExtra("isElevatedUser", isElevatedUser);
        startActivity(courseListIntent);
    }


    @Override
    public String getEtEmail() {
        String email = this.etEmail.getText().toString();

        if(email.isEmpty()){
            return "";
        }
        else{
            return email;
        }
    }
    @Override
    public String getEtPassword() {
        String password = this.etPassword.getText().toString();
        if(password.isEmpty()){
            return "";
        }
        else{
            return password;
        }
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }
    @Override
    public void setEtEmail(String email) { this.etEmail.setText(email); }
    @Override
    public void setEtPassword(String password) { this.etPassword.setText(password); }

    /**
     * setPasswordError -- method called when user enters invalid password, sets error message in password editText
     */
    @Override
    public void setPasswordError() {
        Drawable error = getResources().getDrawable(R.drawable.mtrl_ic_error);
        error.setBounds(-10, 0, 0, error.getIntrinsicHeight());
        etPassword.setError("Incorrect Password. Try Again", error);
        etPassword.requestFocus();
    }

    /**
     * setEmailError -- method called when user enters invalid email, sets error message in username editText
     */
    @Override
    public void setEmailError() {
        etEmail.setError("No such user exists.");
        etEmail.requestFocus();
    }
}