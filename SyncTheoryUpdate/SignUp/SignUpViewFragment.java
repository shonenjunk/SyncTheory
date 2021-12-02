package com.example.synctheory.SignUp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.example.synctheory.UserHelperClass;

import com.example.synctheory.Login.LoginContract;
import com.example.synctheory.R;


public class SignUpViewFragment extends Fragment implements SignUpContract.View{

    private SignUpContract.Presenter mPresenter;
    EditText etEmail;
    EditText etNumber;
    EditText etPassword;
    Button btnSignUp;
    Button btnBack;
    CheckBox checkElevUser;

    public SignUpViewFragment() {
        // Required empty public constructor
    }

    public static SignUpViewFragment newInstance() {
        SignUpViewFragment fragment = new SignUpViewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_sign_up_view, container, false);

        etEmail = root.findViewById(R.id.etEmail);

        etNumber = root.findViewById(R.id.etPhoneNumber);

        etPassword = root.findViewById(R.id.etPassword);

        checkElevUser = root.findViewById(R.id.checkElevUser);

        btnSignUp = root.findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.signUpClicked();
            }
        });

        btnBack = root.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.backClicked();
            }
        });

        return root;
    }

    @Override
    public void onResume(){ super.onResume(); }

    @Override
    public void finishSignUpActivity() {
        getActivity().finish();
    }


    @Override
    public String getEtEmail() {
        if(this.etEmail.getText() == null){
            return "";
        }
        else{
            return String.valueOf(this.etEmail.getText());
        }
    }
    @Override
    public String getEtNumber() {
        if(this.etNumber.getText() == null){
            return "";
        }
        else{
            return String.valueOf(this.etNumber.getText());
        }
    }
    @Override
    public String getEtPassword() {
        if(this.etPassword.getText() == null){
            return "";
        }
        else{
            return String.valueOf(this.etPassword.getText());
        }
    }

    @Override
    public Boolean getCheckElevUser() { return checkElevUser.isChecked(); }

    @Override
    public void setPresenter(SignUpContract.Presenter presenter) { mPresenter = presenter; }
    @Override
    public void setEtEmail(String email) { this.etEmail.setText(email); }
    @Override
    public void setEtNumber(String number) { this.etNumber.setText(number); }
    @Override
    public void setEtPassword(String password) { this.etPassword.setText(password); }
}