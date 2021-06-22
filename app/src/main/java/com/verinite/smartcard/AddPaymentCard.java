package com.verinite.smartcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddPaymentCard extends AppCompatActivity {

    private Button addCardManually;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment_card);

        addCardManually = (Button) findViewById(R.id.addCardManual);

        addCardManually.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddCardManually.class);
                startActivity(intent);
            }
        });
    }
}