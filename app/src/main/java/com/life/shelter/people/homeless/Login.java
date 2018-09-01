package com.life.shelter.people.homeless;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import custom_font.MyEditText;
import custom_font.MyTextView;

public class Login extends AppCompatActivity {
    private TextView shelter;
    private MyTextView createBtn, loginBtn, resetBtn;
    private MyEditText editTextEmail, editTextPassword;
    // User Authentication
    private User mUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        createBtn = findViewById(R.id.create_button);
        loginBtn = findViewById(R.id.login_button);
        resetBtn = findViewById(R.id.reset_password);
        shelter = findViewById(R.id.title_login);
        editTextEmail = findViewById(R.id.email_login);
        editTextPassword = findViewById(R.id.password_login);
        // set custom text font for text title
        Typeface custom_fonts = Typeface.createFromAsset(getAssets(), "fonts/ArgonPERSONAL-Regular.otf");
        shelter.setTypeface(custom_fonts);
        // Create new account
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.v(""+ Login.this,"Create new Account button.");
                Intent it = new Intent(Login.this, Register.class);
                startActivity(it);
            }
        });
        // Reset Your Password
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(""+ Login.this,"Reset Password button.");
                Intent it = new Intent(Login.this, ResetPassword.class);
                startActivity(it);
            }
        });
        // login exist user
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check for create new account
                if (mUser.login(editTextEmail,editTextPassword)) {
                    // show success message
                    Toast.makeText(Login.this, "Login successful, Moving you to home page...",
                            Toast.LENGTH_SHORT).show();
                    // redirect to home page if create success
                    Intent it = new Intent(Login.this, home.class);
                    startActivity(it);
                } else {
                    // If sign up fails, display a message to the user.
                    Toast.makeText(Login.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
