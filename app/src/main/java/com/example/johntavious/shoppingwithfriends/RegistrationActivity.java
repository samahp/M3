package com.example.johntavious.shoppingwithfriends;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.io.File;

/**
 * Represents the registration activity of the application
 */

public class RegistrationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    /**
     * Cancels the Registration Activity and returns User
     * to the opening page
     * @param view the cancel button click
     */
    public void onCancelClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Attempts to create a new User once "done" is clicked
     * and log him into the system
     * @param view the done button click
     */
    /**TODO: also check Username uniqueness, and fix bug allowing empty names,
     * , as well as name that contain spaces"**/
    public void onDoneClick(View view) {
        String name = ((EditText)findViewById(R.id.name_field)).getText().toString().trim();
        String email = ((EditText)findViewById(R.id.email_field)).getText().toString().trim();
        String password = ((EditText)findViewById(R.id.password_field)).getText().toString();
        if (!isValidUsername(name)) {
            EditText nameView = (EditText)findViewById(R.id.name_field);
            nameView.setError("This name is either taken or invalid. Usernames" +
                            " cannot contain \"@\" or spaces and must be more than 2 " +
                            "characters"
            );
            nameView.requestFocus();
            } else if (!LoginActivity.isPasswordValid(password)) {
                    EditText passwordView = (EditText)findViewById(R.id.password_field);
                    passwordView.setError("Passwords must be at least 4 characters");
                    passwordView.requestFocus();
            } else if (LoginActivity.emailValid(email)) {
                    User user = new User(name, email, password);
                    LoginActivity.addUser(user);
                    Intent intent = new Intent(this, WelcomeActivity.class);
                    intent.putExtra("user", user.getName());
                    startActivity(intent);
            } else {
                    EditText emailView = (EditText) findViewById(R.id.email_field);
                    emailView.setError(getString(R.string.email_taken));
                    emailView.requestFocus();
            }
        }

    /**
     * Determines if a Username is valid or not (usernames are unique)
     * @param n the Username to check
     * @return true if valid, false otherwise
     */
    public static boolean isValidUsername(String n) {
        if (n != null && !n.contains(" ") && !n.contains("@") && n.length() > 2) {
            for (User user : LoginActivity.getUsers()) {
                if (user.getName().equalsIgnoreCase(n)) {
                    return false; //return false b/c the name is taken
                }
            }
            return true; //we've searched all names and no one is using it
        }
        return false; //will return false here if n contains one of the "bad" characters or is null

    }
}
