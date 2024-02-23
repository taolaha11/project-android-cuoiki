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
import com.mobile.quanlybanhangonline.dbhandler.ForgotPasswordHandler;


public class VerifyActivity extends Activity {
    Button btnVerify;
    EditText etCode;
    ForgotPasswordHandler forgotPasswordHandler;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        forgotPasswordHandler = new ForgotPasswordHandler(this);
        btnVerify = (Button) findViewById(R.id.btnVerify);
        etCode = (EditText) findViewById(R.id.etCode);

        Intent intent = getIntent();
        String code = intent.getStringExtra("code");
        String email = intent.getStringExtra("email");


        btnVerify.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String txtCode = etCode.getText().toString().trim();
                if (txtCode.equals(code)) {
                    Intent i = new Intent(getApplicationContext(), UpdatePasswordActivity.class);
                    i.putExtra("email", email);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Mã xác nhận sai, vui lòng nhập lại",
                            Toast.LENGTH_LONG).show();
                    etCode.setText("");
                }
            }
        });
    }
}
