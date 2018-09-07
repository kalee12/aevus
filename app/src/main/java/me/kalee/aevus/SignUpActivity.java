package me.kalee.aevus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_up);
    }

    public void goLogIn(View V) {
        Intent goLogIn = new Intent(SignUpActivity.this, LogInActivity.class);
        startActivity(goLogIn);
    }
}
