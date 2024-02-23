package com.mobile.quanlybanhangonline.activity.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.quanlybanhangonline.activity.MainActivity;
import com.mobile.quanlybanhangonline.activity.user.MenuUserActivity;
import com.mobile.quanlybanhangonline.R;
import com.mobile.quanlybanhangonline.activity.admin.MenuActivity;
import com.mobile.quanlybanhangonline.dbhandler.LoginHandler;

public class LoginActivity extends Activity {

    Button btnRegister;
    Button btnLogin;
    EditText etPassword;
    EditText etUsername;
    TextView forgotPassword;
    LoginHandler loginHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginHandler = new LoginHandler(this);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        forgotPassword = (TextView) findViewById(R.id.forgot_password);

        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),
                        RegisterActivity.class);
                startActivity(i);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),
                        ForgotPasswordActivity.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if (loginHandler.checkLogin(username, password) == "admin") {
                    Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),
                            "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                    finish();
                } else if (loginHandler.checkLogin(username, password) == "customer"){
                    MainActivity.user_id = loginHandler.getUserId(username);
                    Intent i = new Intent(getApplicationContext(), MenuUserActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),
                            "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Tài khoản hoặc mật khẩu không đúng, hãy kiểm tra lại.",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
