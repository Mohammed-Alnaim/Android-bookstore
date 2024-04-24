package ir.debook.debook;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    FragmentTransaction transaction;
    Toolbar toolbar;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    setupToolbar();
    setupBottomNavigationView();
    setupDefaultFragment();
}

private void setupToolbar() {
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
}

private void setupBottomNavigationView() {
    bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
    bottomNavigationView.setSelectedItemId(R.id.home);
    bottomNavigationView.setOnNavigationItemSelectedListener(this);
}

private void setupDefaultFragment() {
    fragmentManager = getSupportFragmentManager(); // Use getSupportFragmentManager for compatibility
    fragment = new HomeFragment();
    transaction = fragmentManager.beginTransaction();
    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
    transaction.replace(R.id.main_container, fragment).commit();
}

@Override
public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
        case R.id.home:
            fragment = new HomeFragment();
            break;
        case R.id.category:
            fragment = new CategoryFragment();
            break;
        case R.id.search:
            fragment = new SearchFragment();
            break;
        case R.id.timeline:
            fragment = new TimelineFragment();
            break;
        case R.id.profile:
            fragment = new ProfileFragment();
            break;
    }
    updateFragment(fragment);
    return true;
}

private void updateFragment(Fragment newFragment) {
    transaction = fragmentManager.beginTransaction();
    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
    transaction.replace(R.id.main_container, newFragment).commit();
}

}
