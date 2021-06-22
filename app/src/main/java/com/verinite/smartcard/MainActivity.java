package com.verinite.smartcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText userName, password, rePassword;
    private Button signup, signin;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        rePassword = (EditText) findViewById(R.id.rePassword);

        signin = (Button) findViewById(R.id.signin);
        signup = (Button) findViewById(R.id.signup);

        dbHelper = new DBHelper(this);

        if(dbHelper.checkUsernameExists()){
            startActivity(new Intent(getApplicationContext(), Login.class));
        }

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userName.getText().toString();
                String pass = password.getText().toString();
                String rePass = rePassword.getText().toString();

                if( user.equals("") || pass.equals("") || rePass.equals("")){
                    Toast.makeText(MainActivity.this, "Plase enter all the fields", Toast.LENGTH_SHORT).show();
                }else{
                    if(pass.equals(rePass)){
                        if(dbHelper.checkUsernameExists(user) == false){
                           Boolean result = dbHelper.insertUserCredential(user, pass);
                           if(result){
                               Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(getApplicationContext(), Home.class);
                               startActivity(intent);

                           }else {
                               Toast.makeText(MainActivity.this, "Registered Failed", Toast.LENGTH_SHORT).show();
                           }
                        }else{
                            Toast.makeText(MainActivity.this, "User already exists please login", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MainActivity.this, "Password does not match with 2nd password", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }

}