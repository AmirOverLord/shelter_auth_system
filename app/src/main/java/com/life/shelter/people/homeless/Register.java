package com.life.shelter.people.homeless;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import custom_font.MyEditText;
import custom_font.MyTextView;

public class Register extends AppCompatActivity {

    private TextView shelter;
    private MyTextView login , signup;
    private MyEditText editTextEmail, editTextPassword, editTextPasswordConfirm;
    // User Authentication
    private User mUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // link id's
        shelter = findViewById(R.id.title_reister);
        login =  findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextPasswordConfirm = findViewById(R.id.password_confirm);

        // set custom font text
        Typeface custom_fonts = Typeface.createFromAsset(getAssets(), "fonts/ArgonPERSONAL-Regular.otf");
        shelter.setTypeface(custom_fonts);

        // set login listener
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send user to login activity
                Intent it = new Intent(Register.this, Login.class);
                startActivity(it);
            }
        });
        // set sign up listener
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check for create new account
                if (mUser.create(editTextEmail,editTextPassword,editTextPasswordConfirm)) {
                    // show success message
                    Toast.makeText(Register.this, "Your Account has been Created.",
                            Toast.LENGTH_SHORT).show();
                    // redirect to home page if create success
                    Intent it = new Intent(Register.this, home.class);
                    startActivity(it);
                } else {
                    // If sign up fails, display a message to the user.
                    Toast.makeText(Register.this, "Failed to create your Account.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
