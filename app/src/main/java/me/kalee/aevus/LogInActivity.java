package me.kalee.aevus;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity implements LogInFragment.OnFragmentInteractionListener,
                                                                SignUpFragment.OnFragmentInteractionListener,
                                                                GetLogInFragment.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_log_in);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(LogInActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        LogInFragment fragment = new LogInFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, fragment, "Log In");
        ft.commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(LogInActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void goSignUp(View V) {
        SignUpFragment fragment = new SignUpFragment();
        transaction(fragment, "sign up");
    }

    public void goLogIn(View V) {
        LogInFragment fragment = new LogInFragment();
        getSupportFragmentManager().popBackStackImmediate();
    }

    public void getLogIn(View V) {
        GetLogInFragment fragment = new GetLogInFragment();
        transaction(fragment, "get");
    }

    private void transaction(Fragment fragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment, tag);
        ft.addToBackStack(null);
        ft.commit();
    }
}
