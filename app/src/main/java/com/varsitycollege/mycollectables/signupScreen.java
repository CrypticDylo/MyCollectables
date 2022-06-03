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

public class signupScreen extends AppCompatActivity {

    //code attribution
//this code was taken from  firebase docs
//Firebase. (2022). Authenticate with Google on Android  |  Firebase Documentation. [online] Available at: https://firebase.google.com/docs/auth/android/google-signin#:~:text=You%20can%20let%20your%20users%20authenticate%20with%20Firebase,in%20your%20module%20%28app-level%29%20Gradle%20file%20%28usually%20app%2Fbuild.gradle%29. [Accessed 3 Jun. 2022].




    //declare variables to work with objects in the activity_signup_screen

    Button signupBtn;
    EditText FName, UserName, Password;
    Button Google, Facebook;
    FirebaseAuth fAuth;
    ProgressBar progressbar;
    TextView Login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);

        //saves the user entered values to the variables

        FName = findViewById(R.id.usernameUserInput);
        UserName = findViewById(R.id.nameUserInput);
        Password = findViewById((R.id.passwordUserInput));
        Google =findViewById(R.id.signupWGBtnSignupPage);
        Facebook = findViewById(R.id.SignupWFBtnSignupPage);
        signupBtn = (Button) findViewById(R.id.signupBtnSignupPage);
        Login = findViewById(R.id.alreadyHaveAccountText);

        // Creates an Instance in firebase to capture the values

        fAuth = FirebaseAuth.getInstance();
        progressbar = findViewById(R.id.progressBar);



        //sign up conditions to check if the values entered are valid

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName =  FName.getText().toString().trim();
                String UName = UserName.getText().toString().trim();
                String passW = Password.getText().toString().trim();

                if (TextUtils.isEmpty(fullName)){
                    FName.setError("Full Name is required");
                    return;
                }

                if (TextUtils.isEmpty(UName)){
                    UserName.setError("Please Enter an Email");
                    return;
                }

                if (TextUtils.isEmpty(passW)){
                    Password.setError("Password is required");
                    return;
                }

                if (passW.length() < 6){
                    Password.setError("Password must be than 6 characters");
                    return;
                }

                progressbar.setVisibility(View.VISIBLE);

                //register user in firebase

                fAuth.createUserWithEmailAndPassword(UName,passW).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(signupScreen.this, "You have successfully signed up", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),LoginScreen.class));
                       }else{
                            Toast.makeText(signupScreen.this, "An error has occurred  " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressbar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginScreen.class));
            }
        });
    }


}