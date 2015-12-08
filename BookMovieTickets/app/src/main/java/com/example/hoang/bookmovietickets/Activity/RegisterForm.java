package com.example.hoang.bookmovietickets.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoang.bookmovietickets.Helper.DBHelper;
import com.example.hoang.bookmovietickets.R;
import com.example.hoang.bookmovietickets.Model.UserModel;

import java.util.List;

public class RegisterForm extends AppCompatActivity {
    TextView lgIn;
    EditText username;
    EditText password;
    EditText passwordConfirmation;
    Button btncreate;
    private List<UserModel> userList;
    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_form);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FindAllView();
        setFocusChange();
        setClickBtn();


    }

    public void FindAllView(){
        username = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.pass);
        passwordConfirmation = (EditText) findViewById(R.id.rePass);
        username.setImeOptions(EditorInfo.IME_ACTION_DONE);
        username.setImeActionLabel("Done", KeyEvent.KEYCODE_ENTER);
        password.setImeOptions(EditorInfo.IME_ACTION_DONE);
        password.setImeActionLabel("Done", KeyEvent.KEYCODE_ENTER);
        passwordConfirmation.setImeOptions(EditorInfo.IME_ACTION_DONE);
        passwordConfirmation.setImeActionLabel("Done", KeyEvent.KEYCODE_ENTER);
        btncreate = (Button) findViewById(R.id.createAcc);
        lgIn = (TextView) findViewById(R.id.backToLgIn);
        dbHelper = new DBHelper(this);
        userList = dbHelper.getListUser();
    }

    public boolean checkPass(){
        if(password.getText().toString().length() < 4) return  false;
        if(password.getText().toString().equals(passwordConfirmation.getText().toString())) return true;
        return false;
    }

    public boolean checkUser(){
        if(username.getText().toString().length() < 4 || passwordConfirmation.getText().toString().length() < 4) return  false;
        if (userList.size() > 0){
            for (UserModel model : userList){
                if (username.getText().toString().equalsIgnoreCase(model.getUsername())){
                    return false;
                }
            }
        }
        return true;
    }

    public void setClickBtn(){
        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkPass()) {
                    Toast.makeText(getApplicationContext(), "Password Not Match or Too Short", Toast.LENGTH_SHORT).show();
                } else {
                    if (!checkUser()) {
                        Toast.makeText(getApplicationContext(), "Excisting or Invalid Username", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "SuccessFully Create Account", Toast.LENGTH_SHORT).show();
                        String user = username.getText().toString();
                        String pass = password.getText().toString();
                        UserModel userModel = new UserModel();
                        userModel.setUsername(user);
                        userModel.setPassword(pass);

                        dbHelper.createUser(userModel);
                        finish();
                    }
                }

            }
        });


        lgIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setFocusChange(){
        username.setBackgroundResource(R.drawable.unfocus_edittext);
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    username.setBackgroundResource(R.drawable.focus_edittext);
                } else {
                    username.setBackgroundResource(R.drawable.unfocus_edittext);
                }
            }
        });

        password.setBackgroundResource(R.drawable.unfocus_edittext);
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    password.setBackgroundResource(R.drawable.focus_edittext);
                } else {
                    password.setBackgroundResource(R.drawable.unfocus_edittext);
                }
            }
        });

        passwordConfirmation.setBackgroundResource(R.drawable.unfocus_edittext);
        passwordConfirmation.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    passwordConfirmation.setBackgroundResource(R.drawable.focus_edittext);
                } else{
                    passwordConfirmation.setBackgroundResource(R.drawable.unfocus_edittext);
                }
            }
        });
    }
}
