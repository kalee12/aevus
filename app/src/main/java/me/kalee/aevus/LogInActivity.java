package me.kalee.aevus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_log_in);
    }

    public void goSignUp(View V) {
        Intent goSignUp = new Intent(LogInActivity.this, SignUpActivity.class);
        goSignUp.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(goSignUp);
    }

}
