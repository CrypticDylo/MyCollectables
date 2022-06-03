package com.varsitycollege.mycollectables;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginScreen extends AppCompatActivity {
    //code attribution
//this code was based from  firebase docs
//Firebase. (2022). Authenticate with Google on Android  |  Firebase Documentation. [online] Available at: https://firebase.google.com/docs/auth/android/google-signin#:~:text=You%20can%20let%20your%20users%20authenticate%20with%20Firebase,in%20your%20module%20%28app-level%29%20Gradle%20file%20%28usually%20app%2Fbuild.gradle%29. [Accessed 3 Jun. 2022].


    private Button loginBtn;
    Button sig;
    EditText Email, passW;
    ProgressBar proB;
    FirebaseAuth fAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        loginBtn = (Button) findViewById(R.id.loginBtnLoginPage);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        //creates a onclick listener to open registration
        sig = (Button)findViewById(R.id.CreateAcc);
        sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Reg();
            }
        });


//declares the fields from xml to variables

        Email = findViewById(R.id.usernameUserInput);
        passW = findViewById(R.id.passwordUserInput);
        proB = findViewById(R.id.progressBarLog);
        fAuth = FirebaseAuth.getInstance();





        loginBtn.setOnClickListener(new View.OnClickListener() {

//checks if the users information has met the conditions
            @Override
            public void onClick(View view) {
                String mail = Email.getText().toString().trim();
                String pass = passW.getText().toString().trim();

                if (TextUtils.isEmpty(mail)){
                    Email.setError("Please Enter your Email");
                    return;
                }

                if (TextUtils.isEmpty(pass)){
                    passW.setError("Password is required");
                    return;
                }

                if (pass.length() < 6){
                    passW.setError("Password must be than 6 characters");
                    return;
                }

                proB.setVisibility(View.VISIBLE);

                //check if user has registered

                fAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()) {
                           Toast.makeText(LoginScreen.this, "You have successfully logged in", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(getApplicationContext(),CategoriesScreen.class));
                      }else{
                           Toast.makeText(LoginScreen.this, "An error has occurred  " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                           proB.setVisibility(View.GONE);
                      }
                    }
                });
            }


        });





    }





    public void login(){

        Intent intent = new Intent(this, CategoriesScreen.class);
        startActivity(intent);
    }

    public void Reg(){

        Intent intent = new Intent(this, signupScreen.class);
        startActivity(intent);
    }

    public void sig(View view) {
    }
}