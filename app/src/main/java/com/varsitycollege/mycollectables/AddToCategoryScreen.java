package com.varsitycollege.mycollectables;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class AddToCategoryScreen extends AppCompatActivity {

    String itemName, itemDescription;
    Character itemRarity, selectCategory;

    EditText itemNameUserInput;
    EditText itemDescriptionUserInput;
    Spinner rarityUserInput;
    Spinner selectCategoryUserInput;

    Button addToSubmitButton;
    Button addImageButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_category_screen);

        String[] arraySpinnerRarity = new String[]{
                "Common", "Uncommon", "Rare", "One-of-a-kind"
        };

        Spinner s = (Spinner) findViewById(R.id.rarityUserInput);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinnerRarity);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);


        itemNameUserInput = (EditText) findViewById(R.id.itemNameUserInput);
        itemDescriptionUserInput = (EditText) findViewById(R.id.itemDescriptionUserInput);

        rarityUserInput = (Spinner) findViewById(R.id.rarityUserInput);
        selectCategoryUserInput = (Spinner) findViewById(R.id.rarityUserInput);



        addImageButton = (Button) findViewById(R.id.addImageButton);
        addImageButton.setOnClickListener(new View.OnClickListener() {
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



        addToSubmitButton = (Button) findViewById(R.id.addToSubmitButton);
        addToSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                itemName = itemNameUserInput.getText().toString();
                itemDescription = itemDescriptionUserInput.getText().toString();

                //itemRarity = rarityUserInput.getSelectedItem();

            }
        });


    }
}
