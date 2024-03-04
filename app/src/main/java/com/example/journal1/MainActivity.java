package com.example.journal1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.journal1.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ListAdapter adapter;
    ArrayList<ListData> dataArrayList=new ArrayList<>();
    ListData listData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int[] imagelist={R.drawable.pizza,R.drawable.donut,R.drawable.frenchfries,R.drawable.hotdog,R.drawable.taco};
        int[] desclist={R.string.pizzadesc,R.string.donutdesc,R.string.friesdesc,R.string.hotdogdesc,R.string.tacodesc};
        String[] namelist={"Pizza","Donut","French Fries","Hot-Dog","Taco"};

        for (int i = 0; i < imagelist.length; i++) {
            listData = new ListData(imagelist[i], namelist[i], getString(desclist[i]));
            dataArrayList.add(listData);
        }

        adapter = new listAdapter(MainActivity.this, dataArrayList);
        binding.listview.setAdapter(adapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0 && i < namelist.length && i < desclist.length && i < imagelist.length) {
                    Intent intent = new Intent(MainActivity.this, detailedActivity.class);
                    intent.putExtra("name", namelist[i]);
                    intent.putExtra("desc", getString(desclist[i]));
                    intent.putExtra("image", imagelist[i]);
                    startActivity(intent);
                }
            }
        });
    }
}



class ListData {
    String name, desc;
    int image;

    ListData(int image, String name, String desc) {
        this.image = image;
        this.name = name;
        this.desc = desc;
    }
}

class listAdapter extends ArrayAdapter<ListData>{
    public listAdapter(@NonNull Context context, ArrayList<ListData> dataArrayList) {
        super(context, R.layout.list_item,dataArrayList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        ListData listData = getItem(position);
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        if (listData != null) { // Check for null
            ImageView listImage = view.findViewById(R.id.listImage);
            TextView listName = view.findViewById(R.id.listName);
            listImage.setImageResource(listData.image);
            listName.setText(listData.name);
        }
        return view;
    }
}