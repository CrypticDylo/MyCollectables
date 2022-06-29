package com.varsitycollege.mycollectables;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

public class MyStats extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stats);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //code below creates options for our menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                startActivity(new Intent(getApplicationContext(), CategoriesScreen.class));
                finish();
                return true;
            case R.id.nav_addCategory:
                startActivity(new Intent(getApplicationContext(), AddNewCategory.class));
                finish();
                return true;
            case R.id.nav_addToCategory:
                startActivity(new Intent(getApplicationContext(), AddToCategoryScreen.class));
                finish();
                return true;
            case R.id.nav_myStats:
                startActivity(new Intent(getApplicationContext(), MyStats.class));
                finish();
                return true;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginScreen.class));
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
