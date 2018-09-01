package com.life.shelter.people.homeless;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import custom_font.MyEditText;

public class User extends AppCompatActivity{
    private String mEmail;
    private String mName;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private boolean status = false;

    /**
     *  Constructor
     */
    public User() {
        // get Auth instance
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        mEmail = currentUser != null ? currentUser.getEmail() : null;
        mName = currentUser != null ? currentUser.getDisplayName() : null;
    }

    /**
     *  Create New Account
     * @param viewEmail EditText
     * @param viewPassword EditText
     * @param viewPasswordConfirm EditText
     * @return status boolean true if created and false if not
     */
    public boolean create(MyEditText viewEmail, MyEditText viewPassword, MyEditText viewPasswordConfirm) {
        // set data
        String email = viewEmail.getText().toString().trim();
        String password = viewPassword.getText().toString().trim();
        // valid data
        if (TextUtils.isEmpty(email)) {
            viewEmail.setError("Please enter email address!");
            return false;
        }
        if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            viewEmail.setError("Invalid email address");
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            viewPassword.setError("Please enter password!");
            return false;
        }
        if (!viewPasswordConfirm.getText().toString().matches(password)) {
            viewPasswordConfirm.setError("Password don't match!");
            return false;
        }
        //create new user and save data to fire base db
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    status = task.isSuccessful();
                }
            });
        // return true if created and false if not
        return status;
    }

    /**
     *  Login to account with password && email
     * @param viewEmail EditText
     * @param viewPassword EditText
     * @return status boolean true if valid info and login , false if not
     */
    public boolean login(MyEditText viewEmail, MyEditText viewPassword) {
        // set data
        String email = viewEmail.getText().toString().trim();
        String password = viewPassword.getText().toString().trim();
        // valid data
        if (TextUtils.isEmpty(email)) {
            viewEmail.setError("Please enter email address!");
            return false;
        }
        if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            viewEmail.setError("Invalid email address");
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            viewPassword.setError("Please enter password!");
            return false;
        }
        // try to login with fire base db
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    status = task.isSuccessful();
                }
            });

        return status;
    }

    /**
     *  Reset user password by email when lose it
     * @param viewEmail EditText
     * @return status boolean true if done , false if not
     */
    public boolean resetPasswordByEmail(MyEditText viewEmail) {
        // set data
        String email = viewEmail.getText().toString().trim();
        // valid data
        if (TextUtils.isEmpty(email)) {
            viewEmail.setError("Please enter email address!");
            return false;
        }
        if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            viewEmail.setError("Invalid email address");
            return false;
        }
        //Log.v(""+User.this,String.format("email : %s",email));
        // try to send reset email to user
        mAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    status = task.isSuccessful();
                }
            });

        return status;
    }

    /**
     * logout current user
     */
    public void logout() {
        mAuth.signOut();
    }

    /**
     *  Check if user is signed in
     * @return user_status boolean
     */
    public boolean isLogin() {
        return currentUser != null;
    }

    /**
     *  Get user name
     * @return mName String
     */
    public String getName() {
        return mName;
    }

    /**
     *  get user email
     * @return mEmail Sting
     */
    public String getEmail() {
        return mEmail;
    }

    /**
     *  Logout user if app no long in use
     */
    @Override
    protected void onStop() {
        super.onStop();
        this.logout();
    }
}
