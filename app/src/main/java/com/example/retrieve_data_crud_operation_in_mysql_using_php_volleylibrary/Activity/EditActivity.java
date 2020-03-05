package com.example.retrieve_data_crud_operation_in_mysql_using_php_volleylibrary.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.retrieve_data_crud_operation_in_mysql_using_php_volleylibrary.R;

import java.util.HashMap;
import java.util.Map;

public class EditActivity extends AppCompatActivity {

    EditText etName, etEmail, etContact, etAddress, etId;
    String url = "https://retrofit2practice.000webhostapp.com/update.php";
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        etId = findViewById(R.id.editId);
        etName = findViewById(R.id.editName);
        etEmail = findViewById(R.id.editEmail);
        etContact = findViewById(R.id.editContact);
        etAddress = findViewById(R.id.editAddress);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        etId.setText(MainActivity.models.get(position).getId());
        etName.setText(MainActivity.models.get(position).getName());
        etEmail.setText(MainActivity.models.get(position).getEmail());
        etContact.setText(MainActivity.models.get(position).getContact());
        etAddress.setText(MainActivity.models.get(position).getAddress());


    }

    public void btnUpdate(View view) {


        final String id = etId.getText().toString();
        final String name = etName.getText().toString();
        final String email = etEmail.getText().toString();
        final String contact = etContact.getText().toString();
        final String address = etAddress.getText().toString();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(EditActivity.this, response, Toast.LENGTH_SHORT).show();

//                Start main activity add finish this activity
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();

                progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();


                progressDialog.dismiss();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("name", name);
                params.put("email", email);
                params.put("contact", contact);
                params.put("address", address);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(EditActivity.this);
        requestQueue.add(request);


    }
}
