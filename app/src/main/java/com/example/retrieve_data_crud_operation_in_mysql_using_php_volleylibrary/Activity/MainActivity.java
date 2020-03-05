package com.example.retrieve_data_crud_operation_in_mysql_using_php_volleylibrary.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.retrieve_data_crud_operation_in_mysql_using_php_volleylibrary.Adapter.MyAdapter;
import com.example.retrieve_data_crud_operation_in_mysql_using_php_volleylibrary.Model.Model;
import com.example.retrieve_data_crud_operation_in_mysql_using_php_volleylibrary.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarException;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    MyAdapter adapter;
    public static ArrayList<Model> models = new ArrayList<>();
    String url = "https://retrofit2practice.000webhostapp.com/retrieve.php";
    String deleteUrl = "https://retrofit2practice.000webhostapp.com/delete.php";
    Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listViewId);

        adapter = new MyAdapter(this, models);
        listView.setAdapter(adapter);

//        OnTeam Click Listener

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

//                 Using Alert Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

//                Using Progress Dialog
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

//                Using DialogItem

                CharSequence[] dialogItem = {"View Data", "Update Data", "Delete Data"};
                builder.setTitle(models.get(position).getName());

                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {

//                           Case 0 View Data

                            case 0:
                                startActivity(new Intent(getApplicationContext(), DetailActivity.class)
                                        .putExtra("position", position));
                                break;

//                                Case 1 Update Data

                            case 1:
                                startActivity(new Intent(getApplicationContext(), EditActivity.class)
                                        .putExtra("position", position));
                                break;

//                                Case 2 Delete Data

                            case 2:
                                deleteData(models.get(position).getId());
                                break;
                        }

                    }
                });

                builder.create().show();


            }
        });

        retrieveData();


    }

//    Delete Data from Server

    private void deleteData(final String id) {

        StringRequest request = new StringRequest(Request.Method.POST, deleteUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equalsIgnoreCase("Data Deleted")) {
                            Toast.makeText(MainActivity.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(MainActivity.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();

                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("id", id);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }

//     Data get from server

    public void retrieveData() {
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        models.clear();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String id = object.getString("id");
                                    String name = object.getString("name");
                                    String email = object.getString("email");
                                    String contact = object.getString("contact");
                                    String address = object.getString("address");

                                    model = new Model(id, name, email, contact, address);

                                    models.add(model);
                                    adapter.notifyDataSetChanged();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
}
