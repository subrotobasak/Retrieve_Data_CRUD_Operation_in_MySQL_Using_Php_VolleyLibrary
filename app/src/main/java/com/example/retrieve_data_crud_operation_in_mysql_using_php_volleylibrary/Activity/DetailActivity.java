package com.example.retrieve_data_crud_operation_in_mysql_using_php_volleylibrary.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.retrieve_data_crud_operation_in_mysql_using_php_volleylibrary.R;

public class DetailActivity extends AppCompatActivity {

    TextView tvIdId, tvNameId, tvEmailId, tvContact, tvAddress;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


//        Initializing Views
        tvIdId = findViewById(R.id.tvIdId);
        tvNameId = findViewById(R.id.tvNameId);
        tvEmailId = findViewById(R.id.tvEmailId);
        tvContact = findViewById(R.id.tvContactId);
        tvAddress = findViewById(R.id.tvAddressId);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

//        Get All Data

        tvIdId.setText("ID: " + MainActivity.models.get(position).getId());
        tvNameId.setText("Name: " + MainActivity.models.get(position).getName());
        tvEmailId.setText("Email: " + MainActivity.models.get(position).getEmail());
        tvContact.setText("Contact: " + MainActivity.models.get(position).getContact());
        tvAddress.setText("Address: " + MainActivity.models.get(position).getAddress());

    }
}
