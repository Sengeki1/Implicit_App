package com.example.implicit_intent_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ImplicitIntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_implicit_intent);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnWebPage = findViewById(R.id.buttonWebPage);
        Button btnPhone = findViewById(R.id.buttonPhone);
        Button btnSMS = findViewById(R.id.buttonSMS);
        EditText editWebPage = findViewById(R.id.editWebPage);
        EditText editNumber = findViewById(R.id.editNumber);


        Intent intent1 = new Intent(Intent.ACTION_VIEW);
        Intent intent2 = new Intent(Intent.ACTION_DIAL);
        Intent intent3 = new Intent(Intent.ACTION_SENDTO);


        btnWebPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageWebPage = editWebPage.getText().toString();
                intent1.setData(Uri.parse(messageWebPage));

                if (intent1.resolveActivity(getPackageManager()) == null) {
                    startActivity(intent1);
                } else {
                    Log.d("ImplicitIntent", "No activity found to handle web intent");
                }
            }
        });

        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messagePhone = editNumber.getText().toString();
                intent2.setData(Uri.parse(messagePhone));

                if (intent2.resolveActivity(getPackageManager()) == null) {
                    startActivity(intent2);
                } else {
                    Log.d("ImplicitIntent", "No activity found to handle Phone intent");
                }
            }
        });

        btnSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messagePhone = editNumber.getText().toString();
                intent2.setData(Uri.parse(messagePhone));

                intent3.putExtra("sms_body", "Hi, am marco!");
                if (intent3.resolveActivity(getPackageManager()) == null) {
                    startActivity(intent3);
                } else {
                    Log.d("ImplicitIntent", "No activity found to handle SMS intent");
                }
            }
        });
    }
}