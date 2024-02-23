package com.mobile.quanlybanhangonline.activity.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobile.quanlybanhangonline.R;
import com.mobile.quanlybanhangonline.dbhandler.ForgotPasswordHandler;

import java.util.Random;

public class ForgotPasswordActivity extends Activity {
    Button sendEmail;
    EditText etEmail;
    ForgotPasswordHandler forgotPasswordHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        forgotPasswordHandler = new ForgotPasswordHandler(this);
        sendEmail = (Button) findViewById(R.id.btnSendEmail);
        etEmail = (EditText) findViewById(R.id.etEmail);

        sendEmail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String email = etEmail.getText().toString().trim();
                if (!forgotPasswordHandler.checkEmail(email)) {
                    Toast.makeText(getApplicationContext(),
                            "Tài khoản không tồn tại.",
                            Toast.LENGTH_LONG).show();
                    etEmail.setText("");
                } else {
                    Random random = new Random();
                    String code = String.valueOf(random.nextInt(900000) + 100000);

                    forgotPasswordHandler.sendEmail(email, code);
                    Intent i = new Intent(getApplicationContext(), VerifyActivity.class);
                    i.putExtra("code", code);
                    i.putExtra("email", email);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),
                            "Đã gửi email xác nhận",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
