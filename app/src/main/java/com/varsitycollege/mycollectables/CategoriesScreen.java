package com.varsitycollege.mycollectables;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class CategoriesScreen extends AppCompatActivity {

    private Button addCategoryBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarCategoriesScreen);
        setSupportActionBar(toolbar);

        addCategoryBtn = (Button) findViewById(R.id.addCategoryFromCategoriesScreenBtn);
        addCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCategory();
            }
        });
    }

    public void addCategory() {
        Intent intent = new Intent(this, AddNewCategory.class);
        startActivity(intent);
    }

//code attribution
//this code was based from javatpoint
//www.javatpoint.com. (2021). Android Option Menu Example - javatpoint. [online] Available at: https://www.javatpoint.com/android-option-menu-example [Accessed 3 Jun. 2022].


//code below creates a menu button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //code below creates options for our menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                Toast.makeText(getApplicationContext(), "Item 1 Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.nav_addCategory:
                startActivity(new Intent(getApplicationContext(), AddNewCategory.class));
                finish();
                return true;
            case R.id.nav_addToCategory:
                startActivity(new Intent(getApplicationContext(),AddToCategoryScreen.class));
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