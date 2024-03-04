package com.example.journal1;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.journal1.databinding.ActivityDetailedBinding;

public class detailedActivity extends AppCompatActivity {
    ActivityDetailedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent=this.getIntent();
        if(intent!=null){
            String name = intent.getStringExtra("name");
            String desc=intent.getStringExtra("desc");
            int image=intent.getIntExtra("image",R.drawable.pizza);

            binding.detailName.setText(name);
            binding.detailDesc.setText(desc);
            binding.detailImage.setImageResource(image);
        }
    }
    public void onBackPressed() {
        super.onBackPressed();
        // Finish this activity to return to the previous activity (MainActivity)
        finish();
    }
}