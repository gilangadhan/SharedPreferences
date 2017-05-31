/*
 * Copyright (c) 2017. Gilang Ramadhan (gilangramadhan96.gr@gmail.com)
 */

package id.sfpcc.sessionandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    helper help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        help = new helper(getApplicationContext());
        help.cekLogin();

        HashMap<String, String> user = help.getUserDetail();
        String nama = user.get(help.NAMA);
        String password = user.get(help.PASSWORD);
        String email = user.get(help.EMAIL);

        Toast.makeText(getApplicationContext(), "Namaku " + nama + "\n Emailku " + email + "\n Passwordku " + password, Toast.LENGTH_SHORT).show();
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                help.logoutUser();
                finish();
            }
        });
    }
}
