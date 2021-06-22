package com.verinite.smartcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.verinite.smartcard.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {

    private EditText userName, password;
    private Button signin;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        signin = (Button) findViewById(R.id.signin);

        dbHelper = new DBHelper(this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userName.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("") || pass.equals("")){
                    Toast.makeText(Login.this, "please enter all fields", Toast.LENGTH_SHORT).show();
                }else {
                    if(dbHelper.checkUsernameAndPassword(user, pass)){
                        Toast.makeText(Login.this, "Successfully login", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Home.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(Login.this, "login Failed please try again", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }


}