package com.example.projetofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen extends AppCompatActivity {


    private EditText emailField;
    private EditText passwordField;
    private TextView signUpButton;
    private Button loginButton;
    private FirebaseAuth auth;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        auth = FirebaseAuth.getInstance();
        emailField = (EditText) findViewById(R.id.usernameLogin);
        passwordField = (EditText) findViewById(R.id.passwordLogin);
        signUpButton = (TextView) findViewById(R.id.signUpButton);
        loginButton = (Button) findViewById(R.id.loginButton);


        if (auth.getCurrentUser() != null) {
            updateUI(auth.getCurrentUser());
        }


        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                email = emailField.getText().toString();
                password = passwordField.getText().toString();
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginScreen.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    FirebaseUser user = auth.getCurrentUser();
                                    updateUI(user);
                                }
                            }
                        });
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginScreen.this, RegisterScreen.class));

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            updateUI(currentUser);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            updateUI(currentUser);
        }
    }

    public void updateUI(FirebaseUser currentUser) {
        Intent uploadImage = new Intent(getApplicationContext(), UploadImage.class);
        uploadImage.putExtra("email", currentUser.getEmail());
        startActivity(uploadImage);
    }

}
