package com.example.synctheory.SignUp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.synctheory.R;


public class SignUpViewFragment extends Fragment implements SignUpContract.View{

    private SignUpContract.Presenter mPresenter;
    //UI elements in the sign up xml view
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

        //assign the email edittext to appopriate UI element
        etEmail = root.findViewById(R.id.etEmail);

        //assign the phoneNumber edittext to appopriate UI element
        etNumber = root.findViewById(R.id.etPhoneNumber);

        //assign the password edittext to appopriate UI element
        etPassword = root.findViewById(R.id.etPassword);

        //assign the elevated user checkbox var to appopriate UI element
        checkElevUser = root.findViewById(R.id.checkElevUser);

        //assigns signup button var to the button defined in XML
        btnSignUp = root.findViewById(R.id.btnSignUp);

        //assign the button click listener to notify presenter when the sign up button is pressed
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.signUpClicked();
            }
        });

        //assigns Back button var to the button defined in XML
        btnBack = root.findViewById(R.id.btnBack);

        //assign the button click listener to notify presenter when the back button is pressed
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

    //method that closes the sign up activity when the user presses sign up button
    @Override
    public void finishSignUpActivity() {
        getActivity().finish();
    }


    //getters for the edit text values with error checking if user doesn't enter anything
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

    //returns the value of the check elevated user boolean
    @Override
    public Boolean getCheckElevUser() { return checkElevUser.isChecked(); }

    //setter for the sign up presenter
    @Override
    public void setPresenter(SignUpContract.Presenter presenter) { mPresenter = presenter; }
    //setters for the sign up view edit texts
    @Override
    public void setEtEmail(String email) { this.etEmail.setText(email); }
    @Override
    public void setEtNumber(String number) { this.etNumber.setText(number); }
    @Override
    public void setEtPassword(String password) { this.etPassword.setText(password); }
}