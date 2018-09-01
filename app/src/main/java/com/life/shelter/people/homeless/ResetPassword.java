package com.life.shelter.people.homeless;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import custom_font.MyEditText;
import custom_font.MyTextView;

public class ResetPassword  extends AppCompatActivity {
    private TextView shelter;
    private MyTextView createBtn, loginBtn, resetBtn;
    private MyEditText editTextEmail;
    // User Authentication
    private User mUser = new User();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        shelter = findViewById(R.id.title_reset);
        createBtn = findViewById(R.id.create_reset);
        loginBtn = findViewById(R.id.login_reset);
        resetBtn = findViewById(R.id.reset_button);
        editTextEmail = findViewById(R.id.email_reset);
        // set custom text font for text title
        Typeface custom_fonts = Typeface.createFromAsset(getAssets(), "fonts/ArgonPERSONAL-Regular.otf");
        shelter.setTypeface(custom_fonts);
        // Create new account
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.v(""+ ResetPassword.this,"Create new Account button.");
                Intent it = new Intent(ResetPassword.this, Register.class);
                startActivity(it);
            }
        });
        // Login exist user
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.v(""+ ResetPassword.this,"Login button.");
                Intent it = new Intent(ResetPassword.this, Login.class);
                startActivity(it);
            }
        });
        // Reset Account Password by email
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log.v(""+ ResetPassword.this,"Rest Password button.");
                // check for reset password by email
                if (mUser.resetPasswordByEmail(editTextEmail)) {
                    // show success message
                    Toast.makeText(ResetPassword.this, "Reset Password email has been send to your email address!.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // If reset fails, display a message to the user.
                    Toast.makeText(ResetPassword.this, "Failed to send reset password email, try again.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
