package com.example.appexpectedvalue.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.appexpectedvalue.R;
import com.example.appexpectedvalue.databinding.ActivityResultBinding;
import com.example.appexpectedvalue.model.Result;

public class ResultActivity extends AppCompatActivity {

    ActivityResultBinding binding;
    Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setToolbar();
        setResultActivity();
        setFooter();
    }
    private void setToolbar(){
        setSupportActionBar(binding.toolbarResult.toolbar);
        getSupportActionBar().setTitle("Hasil Perhitungan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void setResultActivity(){
        Intent intent = getIntent();
        result = (Result)intent.getSerializableExtra("result");
        binding.result.setText(String.valueOf(result.getResult()) + " item");
    }
    private void setFooter(){
        binding.buttonDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, DetailsActivity.class);
                intent.putExtra("result",result);
                startActivity(intent);
            }
        });
        binding.buttonRecalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}