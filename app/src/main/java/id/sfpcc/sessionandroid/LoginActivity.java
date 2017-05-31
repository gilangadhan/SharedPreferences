/*
 * Copyright (c) 2017. Gilang Ramadhan (gilangramadhan96.gr@gmail.com)
 */

package id.sfpcc.sessionandroid;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText mPassword; // password
    AutoCompleteTextView mEmail; //email
    String password, email; //menampung string
    helper help;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //pengenalan widget
        mEmail = (AutoCompleteTextView) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        Button signin = (Button) findViewById(R.id.email_sign_in_button);
        help = new helper(LoginActivity.this);
        //aksi ketika button diklik
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cek();
            }
        });
    }

    private void Cek() {
        mEmail.setError(null);
        mPassword.setError(null);
        if (help.emailkosong(mEmail)){
            mEmail.setError("EMAIL KOSONG");
        }else if(help.passwordkosong(mPassword)){
            mPassword.setError("PASSWORD KOSONG");
        }else if (help.isEmailValid(mEmail)){
            mEmail.setError("EMAIL INVALID");
        }else {
            esekusi();
        }
    }

    private void esekusi() {
        String nama = "Gilang Ganteng";
        email = mEmail.getText().toString().trim();
        password = mPassword.getText().toString().trim();

        //session
        help.createLoginSession(email, password, nama);
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }
}

