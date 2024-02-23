package com.mobile.quanlybanhangonline.activity.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobile.quanlybanhangonline.R;
import com.mobile.quanlybanhangonline.dbhandler.CustomerHandler;

public class UpdatePasswordActivity extends Activity {
    Button btnVerify;
    EditText etNewPassword;
    EditText etNewPasswordConfirm;
    CustomerHandler customerHandler;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        customerHandler = new CustomerHandler(this);
        btnVerify = (Button) findViewById(R.id.btnVerify);
        etNewPassword = (EditText) findViewById(R.id.etNewPassword);
        etNewPasswordConfirm = (EditText) findViewById(R.id.etNewPasswordConfirm);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");


        btnVerify.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String password = etNewPassword.getText().toString().trim();
                String passwordConfirm = etNewPasswordConfirm.getText().toString().trim();

                if (!password.equals(passwordConfirm)) {
                    Toast.makeText(getApplicationContext(),
                            "Mật khẩu không trùng khớp",
                            Toast.LENGTH_LONG).show();
                } else if (customerHandler.updatePassword(email,password)) {
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),
                            "Cập nhật mật khẩu thành công",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
