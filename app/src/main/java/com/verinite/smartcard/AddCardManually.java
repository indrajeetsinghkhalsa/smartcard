package com.verinite.smartcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCardManually extends AppCompatActivity {

    EditText cardNoId, cardHolderNameId, cardPinId, cardNameId, cvvNoId, securityCodeId, expireDateId;
    Button addCardId;

    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card_manually);
        cardNoId = (EditText) findViewById(R.id.cardNo);
        cardHolderNameId = (EditText) findViewById(R.id.cardHolderName);
        cardPinId = (EditText) findViewById(R.id.pin);
        cardNameId = (EditText) findViewById(R.id.cardName);
        cvvNoId = (EditText) findViewById(R.id.cvvNo);
        securityCodeId = (EditText) findViewById(R.id.securityCode);
        expireDateId = (EditText) findViewById(R.id.expireDate);

        addCardId = (Button) findViewById(R.id.addCard);

        dbHelper = new DBHelper(this);

        addCardId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardNo = cardNoId.getText().toString();
                String cardHolderName = cardHolderNameId.getText().toString();
                String cardPin = cardPinId.getText().toString();
                String cardName = cardNameId.getText().toString();
                String cvvNo = cvvNoId.getText().toString();
                String securityCode = securityCodeId.getText().toString();
                String expireDate = expireDateId.getText().toString();

                if(!(cardNo.equals("") || cardHolderName.equals("") || cardPin.equals("") ||
                    cardName.equals("") || cvvNo.equals("") || securityCode.equals("") ||
                    expireDate.equals(""))){

                    if(cardNo.matches("\\d+(?:\\.\\d+)?") && (cardNo.length() >= 15 && cardNo.length() <= 19) ){

                        if (cvvNo.matches("\\d+(?:\\.\\d+)?") && (cvvNo.length() >= 3 && cvvNo.length() <= 4)){

                            if(securityCode.matches("\\d+(?:\\.\\d+)?") && securityCode.length() == 4){

                                if(cardPin.matches("\\d+(?:\\.\\d+)?") && cardPin.length() == 4){

                                    if(dbHelper.insertCard(cardNo, "B", cardHolderName, cardName, cardPin, cvvNo, securityCode, expireDate)){

                                        Toast.makeText(AddCardManually.this,"Card Inserted", Toast.LENGTH_SHORT).show();

                                        startActivity(new Intent(getApplicationContext(), CardListView.class));

                                    }else{
                                        Toast.makeText(AddCardManually.this,"Insertion failed", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(AddCardManually.this,"pin No format invalid "+cardPin, Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(AddCardManually.this,"security No format invalid "+securityCode, Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(AddCardManually.this,"cvv No format invalid "+cvvNo, Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(AddCardManually.this,"card No format invalid "+cardNo, Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(AddCardManually.this, "Provide all details", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}