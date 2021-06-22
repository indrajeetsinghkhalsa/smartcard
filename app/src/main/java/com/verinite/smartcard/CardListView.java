package com.verinite.smartcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CardListView extends AppCompatActivity {

    private ListView listCardId;
    private DBHelper dbHelper;
    private Button addCardId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list_view);

        dbHelper = new DBHelper(this);
        addCardId = (Button) findViewById(R.id.addCard);

        addCardId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddCardManually.class));
            }
        });

        List<Map<String,String>> cards = dbHelper.fetchCardName();
        String[] from = {"card_name", "card_no"};

        Log.d("data", String.valueOf(cards));
        int ids[] = {R.id.cardsListItemId};

        listCardId = (ListView) findViewById(R.id.listCard);
        ArrayAdapter<Map<String,String>> cardList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cards);
        listCardId.setAdapter(cardList);
    }
}