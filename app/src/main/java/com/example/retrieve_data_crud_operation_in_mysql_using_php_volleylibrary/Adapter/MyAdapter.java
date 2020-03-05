package com.example.retrieve_data_crud_operation_in_mysql_using_php_volleylibrary.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.retrieve_data_crud_operation_in_mysql_using_php_volleylibrary.Model.Model;
import com.example.retrieve_data_crud_operation_in_mysql_using_php_volleylibrary.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyAdapter extends ArrayAdapter<Model> {

    Context context;
    List<Model> arrayListModel;

//    Remove int id and Use List<>

    public MyAdapter(@NonNull Context context, List<Model> arrayListModel) {
        super(context,R.layout.list_item, arrayListModel);

        this.context = context;
        this.arrayListModel = arrayListModel;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,null,true);

        TextView tvIdId = view.findViewById(R.id.tvIdId);
        TextView tvNameId = view.findViewById(R.id.tvNameId);

        tvIdId.setText(arrayListModel.get(position).getId());
        tvNameId.setText(arrayListModel.get(position).getName());

        return view;
    }
}
