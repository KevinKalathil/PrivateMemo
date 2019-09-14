package com.kalathil.privatememo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    String correctPass,correctUser, entryUser,entryPass;
    EditText username,password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.button);
        username.setHint("Username");
        password.setHint("Password");

        login.setText("Login");

        SharedPreferences sharedPreference = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        final SharedPreferences.Editor preferenceEditor = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit();
        entryUser = sharedPreference.getString("USER",null);
        entryPass = sharedPreference.getString("PASS",null);

        if(entryUser==null&&entryPass==null){
            login.setText("Set Credentials");
            username.setHint("Create Username");
            password.setHint("Create Password");
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    preferenceEditor.putString("USER",username.getText().toString()).apply();
                    preferenceEditor.putString("PASS",password.getText().toString()).apply();
                    Intent intent = new Intent(MainActivity.this,MyNotes.class);
                    startActivity(intent);
                }
            });
        }
        else{
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(check(username.getText().toString(),password.getText().toString(),entryPass,entryUser)){
                        Intent intent = new Intent(MainActivity.this,MyNotes.class);
                        startActivity(intent);
                    }
                }
            });
        }




    }

    public static Boolean check(String username,String password,String correctPass,String correctUser){

        if(username.equals(correctUser)&&password.equals(correctPass)){
            return true;
        }

        return false;
    }

}
