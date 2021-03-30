package com.example.projetofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterScreen extends AppCompatActivity{


    private Button buttonRegister;
    private EditText editTextFullname;
    private EditText editTextUsername;
    private EditText editTextEmail;
    private EditText editTextPhone;
    private EditText editTextPassword;
    private EditText editTextRepeatPassword;
    private RadioButton genderMale;
    private RadioButton genderFemale;
    private Button buttonSignIn;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private String fullname;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private String repeatPassword;
    private String gender;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        buttonRegister = findViewById(R.id.registerButton);
        editTextFullname = findViewById(R.id.fullnameRegister);
        editTextUsername = findViewById(R.id.usernameRegister);
        editTextEmail = findViewById(R.id.emailRegister);
        editTextPhone = findViewById(R.id.phoneRegister);
        editTextPassword = findViewById(R.id.passwordRegister);
        editTextRepeatPassword = findViewById(R.id.repeatPasswordRegister);
        genderMale = findViewById(R.id.maleButton);
        genderFemale = findViewById(R.id.femaleButton);
        buttonSignIn = findViewById(R.id.signInRegister);

        databaseReference = firebaseDatabase.getInstance().getReference("User");
        firebaseAuth = FirebaseAuth.getInstance();
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterScreen.this, LoginScreen.class));
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fullname = editTextFullname.getText().toString();
                username = editTextUsername.getText().toString();
                email = editTextEmail.getText().toString();
                phoneNumber = editTextPhone.getText().toString();
                password = editTextPassword.getText().toString();
                repeatPassword = editTextRepeatPassword.getText().toString();

                if(genderMale.isChecked()){
                    gender = "Masculino";
                }
                if(genderFemale.isChecked()){
                    gender = "Feminino";
                }

                if(TextUtils.isEmpty(fullname)){
                    Toast.makeText(RegisterScreen.this, "Please Enter Fullname", Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(email)){
                    Toast.makeText(RegisterScreen.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(username)){
                    Toast.makeText(RegisterScreen.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(phoneNumber)){
                    Toast.makeText(RegisterScreen.this, "Please Enter Phone Number", Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterScreen.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(repeatPassword)){
                    Toast.makeText(RegisterScreen.this, "Confirm Password", Toast.LENGTH_SHORT).show();
                }else if(!password.equals(repeatPassword)) {
                    Toast.makeText(RegisterScreen.this, "Password doesnt match", Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(gender)) {
                    Toast.makeText(RegisterScreen.this, "Please Enter Gender", Toast.LENGTH_SHORT).show();
                }

                user = new User(fullname, username, email, password, phoneNumber, gender);
                registerUser();
            }
        });

    }

    public void registerUser(){

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterScreen.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            updateUI(user);
                        }
                    }
                });

    }

    public void updateUI(FirebaseUser currentUser) {
        String keyId = currentUser.getUid();
        databaseReference.child(keyId).setValue(user); //adding user info to database
        Intent loginIntent = new Intent(this, LoginScreen.class);
        startActivity(loginIntent);
    }

}
