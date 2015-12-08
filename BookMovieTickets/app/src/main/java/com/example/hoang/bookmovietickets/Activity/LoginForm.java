package com.example.hoang.bookmovietickets.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoang.bookmovietickets.Helper.DBHelper;
import com.example.hoang.bookmovietickets.Model.SessionModel;
import com.example.hoang.bookmovietickets.R;
import com.example.hoang.bookmovietickets.Model.UserModel;

import java.util.List;

public class LoginForm extends AppCompatActivity {

    EditText username;
    EditText password;
    TextView register;
    CheckBox chkRemember;
    Button logIn;

    private String prefname = "my_data";
    private DBHelper dbHelper;
    public static boolean checkLgIn = false;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setting default screen to login.xml
        setContentView(R.layout.login_form);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FindViewID();
        setEditText();
        setClick();

    }

    public void FindViewID(){
        username = (EditText) findViewById(R.id.editNick);
        password = (EditText) findViewById(R.id.editPass);
        username.setImeOptions(EditorInfo.IME_ACTION_DONE);
        username.setImeActionLabel("Done", KeyEvent.KEYCODE_ENTER);
        password.setImeOptions(EditorInfo.IME_ACTION_DONE);
        password.setImeActionLabel("Done", KeyEvent.KEYCODE_ENTER);
        register = (TextView) findViewById(R.id.toRegister);
        logIn = (Button) findViewById(R.id.btnLogin);
        chkRemember = (CheckBox) findViewById(R.id.chkRemember);
        dbHelper = new DBHelper(this);
    }

    public void setClick(){
        final boolean[] check = {false};
       logIn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               List<UserModel> userList = dbHelper.getListUser();
               if (userList.size() > 0) {
                   for (UserModel model : userList) {
                       if (username.getText().toString().equalsIgnoreCase(model.getUsername())) {
                           if (password.getText().toString().equals(model.getPassword())) {
                               checkLgIn = true;
                               savingPreference();
                               Toast.makeText(LoginForm.this, "Login Successful", Toast.LENGTH_SHORT).show();
                               Intent intent = getIntent();
                               intent.putExtra("isLogin", checkLgIn);
                               setResult(RESULT_OK, intent);
                               SessionModel sessionModel = new SessionModel(1, model.getId());
                               dbHelper.createSession(sessionModel);
                               finish();
                           } else {
                               Toast.makeText(LoginForm.this, "Wrong password", Toast.LENGTH_LONG).show();
                           }
                       } else {
                           Toast.makeText(LoginForm.this, "This account is not exists", Toast.LENGTH_LONG).show();
                       }
                   }
               }else {
                   Toast.makeText(LoginForm.this, "This account is not exists", Toast.LENGTH_LONG).show();
               }
           }
       });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginForm.this, RegisterForm.class);
                startActivity(intent);// dang ki day, chi can quan tam log in xong, thoat log in ra
            }
        });
    }

    public void setEditText(){
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

    }

    @Override
    protected void onResume() {
        super.onResume();
        restoringPreference();
    }

    public void savingPreference(){
        SharedPreferences sharedPreferences = getSharedPreferences(prefname, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String nick = username.getText().toString();
        String pass = password.getText().toString();
        boolean chk = chkRemember.isChecked();
        if (!chk){
            editor.clear();
        }else {
            editor.putString("user", nick);
            editor.putString("pass", pass);
            editor.putBoolean("isCheck", chk);
        }
        editor.commit();
    }

    public void restoringPreference(){
        SharedPreferences sharedPreferences = getSharedPreferences(prefname, MODE_PRIVATE);
        boolean chk = sharedPreferences.getBoolean("isCheck", false);
        if (chk){
            String user = sharedPreferences.getString("user", "");
            String pass = sharedPreferences.getString("pass", "");
            username.setText(user);
            password.setText(pass);
        }
        chkRemember.setChecked(chk);
    }



}
