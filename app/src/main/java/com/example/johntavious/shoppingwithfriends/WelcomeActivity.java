package com.example.johntavious.shoppingwithfriends;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

/**
 * The hompe page of a logged-in user
 * @version 1.0
 */
public class WelcomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Bundle extras = getIntent().getExtras();
        String userName = "";
        if (extras != null) {
            userName = extras.getString("user");
        }
        TextView welcomeText = (TextView)findViewById(R.id.welcome_text);
        welcomeText.setText("Welcome, " + userName);

        // Friend's List
        Button friend_list_button = (Button) findViewById(R.id.friend_list_button);
        final Intent goToFriendList = new Intent(this, FriendList.class);
        goToFriendList.putExtra("name", userName);
        friend_list_button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View view) {
                        startActivity(goToFriendList);
                    }
                }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Signs the user out of the app, returns to login screen
     * @param view the signout button click
     */
    public void onSignOutClick(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
