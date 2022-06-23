package com.varsitycollege.mycollectables;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Telephony;
import android.text.DynamicLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddNewCategory extends AppCompatActivity {

    String categoryName, categoryDescription;
    int numOfCategoryItems;

    private Button submitAddedCategory;

    EditText categoryNameUserInput;
    EditText numberOfItemsUserInput;
    EditText categoryDescriptionUserInput;


    Button newaddimage;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;




    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            removeItem(v);
        }

        private void removeItem(View v) {
            int selectedItemPosition = recyclerView.getChildPosition(v);
            RecyclerView.ViewHolder viewHolder
                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
            TextView textViewName
                    = (TextView) viewHolder.itemView.findViewById(R.id.textViewCatName);
            String selectedName = (String) textViewName.getText();
            int selectedItemId = -1;
            for (int i = 0; i < MyData.catNameArray.length; i++) {
                if (selectedName.equals(MyData.catNameArray[i])) {
                    selectedItemId = MyData.numOfItemsArray[i];
                }
            }
            removedItems.add(selectedItemId);
            data.remove(selectedItemPosition);
            adapter.notifyItemRemoved(selectedItemPosition);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.numberOfItemsUserInput) {
            //check if any items to add
            if (removedItems.size() != 0) {
                addRemovedItemToList();
            } else {
                Toast.makeText(this, "Add Category", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }

    private void addRemovedItemToList() {
        int addItemAtListPosition = 3;
        data.add(addItemAtListPosition, new DataModel(
                MyData.catNameArray[removedItems.get(0)],
                MyData.catDescriptionArray[removedItems.get(0)],
                MyData.catImageArray[removedItems.get(0)],
                MyData.numOfItemsArray[removedItems.get(0)]
        ));
        adapter.notifyItemInserted(addItemAtListPosition);
        removedItems.remove(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_category);

        categoryNameUserInput = (EditText) findViewById(R.id.categoryNameUserInput);
        categoryDescriptionUserInput = (EditText) findViewById(R.id.categoryDescriptionUserInput);
        numberOfItemsUserInput = (EditText) findViewById(R.id.numberOfItemsUserInput);
        newaddimage = (Button) findViewById(R.id.newaddimage);

        myOnClickListener = new MyOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<DataModel>();
        for (int i = 0; i < MyData.catNameArray.length; i++) {
            data.add(new DataModel(
                    MyData.catNameArray[i],
                    MyData.catDescriptionArray[i],
                    MyData.catImageArray[i],
                    MyData.numOfItemsArray[i]

            ));
        }

        removedItems = new ArrayList<Integer>();

        adapter = new CustomAdapter(data);
        recyclerView.setAdapter(adapter);

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


        myOnClickListener = new MyOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<DataModel>();
        for (int i = 0; i < MyData.catNameArray.length; i++) {
            data.add(new DataModel(
                    MyData.catNameArray[i],
                    MyData.catDescriptionArray[i],
                    MyData.catImageArray[i],
                    MyData.numOfItemsArray[i]

            ));
        }

        removedItems = new ArrayList<Integer>();

        adapter = new CustomAdapter(data);
        recyclerView.setAdapter(adapter);

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

//Code Attributions Justin
/**
 code attribution
 This method was taken from abhiandroid
 https://abhiandroid.com/programming/camera
 */
/**
 code attribution
 This method was taken from tutorialspoint
 https://www.tutorialspoint.com/android/android_camera.htm
 */
/**
 code attribution
 This method was taken from Youtube
 https://www.youtube.com/watch?v=zeI0M9PtOBA&ab_channel=BTechDays
 BTech Days
 https://www.youtube.com/c/BTechDays
 */

//Dylan
/**
 code attribution
 This method was taken from JournalDev
 https://www.journaldev.com/10024/android-recyclerview-android-cardview-example-tutorial
 */
/**
 code attribution
 This method was taken from YouTube
 https://www.youtube.com/watch?v=1vN_wuAahqA
 */
/**
 code attribution
 This method was taken from Youtube
 https://www.youtube.com/watch?v=EJrmgJT2NnI
 Droid Guru
 https://www.youtube.com/Droid Guru
 */




/**
 String categoryName, categoryDescription;
 int numOfCategoryItems;
 code attribution
 This method was taken from abhiandroid
 https://abhiandroid.com/programming/camera code attribution
 This method was taken from tutorialspoint
 https://www.tutorialspoint.com/android/android_camera.htm     code attribution
 This method was taken from Youtube
 https://www.youtube.com/watch?v=zeI0M9PtOBA&ab_channel=BTechDays     BTech Days
 https://www.youtube.com/c/BTechDays    EditText categoryNameUserInput;
 EditText numberOfItemsUserInput;
 EditText categoryDescriptionUserInput;
 EditText itemNameUserInput;
 EditText itemDescriptionUserInput;
 Button addToSubmitButton;
 Button addnewImage;
 Button newaddimage;
 String itemName, itemDescription;
 Character itemRarity, selectCategory;
 Spinner rarityUserInput;
 Spinner selectCategoryUserInput;
 private Button submitAddedCategory;
 private ImageView imgCameraImage;
 private static final int REQUEST_IMAGE_CAPTURE=0;
 private static final int REQUEST_IMAGE_CAPTURE_PERMISSION=100;
 private Toolbar toolbar;
 private DrawerLayout drawerLayout;
 private ActionBarDrawerToggle toggleOnOff;
 private NavigationView navigationView;
 @Override
 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.activity_add_new_category);
 categoryNameUserInput = (EditText) findViewById(R.id.categoryNameUserInput);
 categoryDescriptionUserInput = (EditText) findViewById(R.id.categoryDescriptionUserInput);
 numberOfItemsUserInput = (EditText) findViewById(R.id.numberOfItemsUserInput);
 addnewImage = findViewById(R.id.addimage);
 imgCameraImage = findViewById(R.id.imageView);
 addnewImage.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View v) {
 //check if we have camera permission
 if (ActivityCompat.checkSelfPermission(AddNewCategory.this, Manifest.permission.CAMERA)
 != PackageManager.PERMISSION_GRANTED)
 {
 final String[] permissions = {Manifest.permission.CAMERA};
 //request permission -this is asynchronous
 ActivityCompat.requestPermissions(AddNewCategory.this,
 permissions,REQUEST_IMAGE_CAPTURE_PERMISSION);
 }
 else
 {
 takePhoto();
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
 //Requests permission to use the camera
 public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
 super.onRequestPermissionsResult(requestCode, permissions, grantResults);
 if (requestCode == REQUEST_IMAGE_CAPTURE_PERMISSION &&
 ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
 PackageManager.PERMISSION_GRANTED) {
 //Permission granted so take the photo
 takePhoto();
 }
 }
 //Take photo
 public void takePhoto(){
 Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
 startActivityForResult(i,REQUEST_IMAGE_CAPTURE);
 }
 //Displays the taken photo in the App
 @Override
 protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
 super.onActivityResult(requestCode, resultCode, data);
 if(requestCode==REQUEST_IMAGE_CAPTURE && data!=null){
 Bitmap bitmap=(Bitmap)data.getExtras().get("data");
 imgCameraImage.setImageBitmap(bitmap);
 }
 };
 } */












