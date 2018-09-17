package me.kalee.aevus;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements CycleFragment.OnFragmentInteractionListener,
                                                                WeatherFragment.OnFragmentInteractionListener,
                                                                LogFragment.OnFragmentInteractionListener,
                                                                CareFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(MainActivity.this, LogInActivity.class);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.activity_main);

        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("", R.drawable.ic_aevus_star_nfocus);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("", R.drawable.ic_aevus_sun_nfocus);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("", R.drawable.ic_aevus_moon_nfocus);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("", R.drawable.ic_aevus_water_nfocus);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);
        bottomNavigation
                .setInactiveColor(Color.parseColor(
                        "#"+Integer.toHexString(getResources()
                                .getColor(R.color.bottom_nav_inactive))));
        bottomNavigation
                .setAccentColor(Color.parseColor(
                        "#"+Integer.toHexString(getResources()
                                .getColor(R.color.bottom_nav_active))));

        Button signOut = findViewById(R.id.button_signout);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                finish();
                startActivity(intent);
            }
        });

        CycleFragment fragment = new CycleFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container_main, fragment, "cycle");
        ft.commit();

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                Fragment currentFrag = getSupportFragmentManager().findFragmentById(R.id.container_main);
                switch (position) {
                    case 0:
                        if (!currentFrag.getTag().equals("cycle")) {
                            CycleFragment fragment = new CycleFragment();
                            transaction(fragment, "cycle");
                        }
                        break;
                    case 1:
                        if (!currentFrag.getTag().equals("weather")) {
                            WeatherFragment fragment = new WeatherFragment();
                            transaction(fragment, "weather");
                        }
                        break;
                    case 2:
                        if (!currentFrag.getTag().equals("log")) {
                            LogFragment fragment = new LogFragment();
                            transaction(fragment, "log");
                        }
                        break;
                    case 3:
                        if (!currentFrag.getTag().equals("care")) {
                            CareFragment fragment = new CareFragment();
                            transaction(fragment, "care");
                        }
                        break;
                }

                return true;
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void transaction(Fragment fragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container_main, fragment, tag);
        ft.addToBackStack(null);
        ft.commit();
    }
}
