package com.varsitycollege.mycollectables;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Telephony;
import android.text.DynamicLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNewCategory extends AppCompatActivity {

    String categoryName, categoryDescription;
    int numOfCategoryItems;

    private Button submitAddedCategory;

    EditText categoryNameUserInput;
    EditText numberOfItemsUserInput;
    EditText categoryDescriptionUserInput;

    Button newaddimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_category);
        categoryNameUserInput = (EditText) findViewById(R.id.categoryNameUserInput);
        categoryDescriptionUserInput = (EditText) findViewById(R.id.categoryDescriptionUserInput);
        numberOfItemsUserInput = (EditText) findViewById(R.id.numberOfItemsUserInput);
        newaddimage = (Button) findViewById(R.id.newaddimage);
        newaddimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    Intent intent = new Intent();
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(intent);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        submitAddedCategory = (Button) findViewById(R.id.addToSubmitButton);
        submitAddedCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                categoryName = categoryNameUserInput.getText().toString();
                categoryDescription = categoryDescriptionUserInput.getText().toString();
                numOfCategoryItems = Integer.valueOf(numberOfItemsUserInput.getText().toString());
                submitCategory();
            }
        });
    }

    public void submitCategory(){

        CreateView();
        Intent intent = new Intent(this, CategoriesScreen.class);
        startActivity(intent);
    }

    private void CreateView() {


    }
}