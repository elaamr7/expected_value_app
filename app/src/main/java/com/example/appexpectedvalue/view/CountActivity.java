package com.example.appexpectedvalue.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appexpectedvalue.controller.CountController;
import com.example.appexpectedvalue.model.DemandProb;
import com.example.appexpectedvalue.model.Model;
import com.example.appexpectedvalue.adapter.DemandProbAdapter;
import com.example.appexpectedvalue.databinding.ActivityCountBinding;
import com.example.appexpectedvalue.model.Result;

import java.util.ArrayList;

public class CountActivity extends AppCompatActivity {

    ActivityCountBinding binding;
    DemandProbAdapter adapter;
    Model model;
    CountController controller;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        setToolbar();
        setDemandProbActivity();
        setCountActivity();
    }
    private void setToolbar(){
        setSupportActionBar(binding.toolbarCount.toolbar);
        getSupportActionBar().setTitle("Mulai Menghitung");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void init(){
        model = new Model();
        controller = new CountController(model);

        ArrayList<DemandProb> demandProbs = new ArrayList();
        demandProbs.add(new DemandProb());
        adapter = new DemandProbAdapter(demandProbs);
        binding.rvDemandProb.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvDemandProb.setAdapter(adapter);

    }
    private void setDemandProbActivity(){
        EditText etQuantity = binding.etQuantityDemand;
        RecyclerView recyclerView = binding.rvDemandProb;
        etQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ArrayList<DemandProb> demandProbs = new ArrayList();
                if(charSequence.length()!=0){
                    for(int j=0; j<Integer.parseInt(etQuantity.getText().toString()); j++){
                        demandProbs.add(new DemandProb());
                    }
                    adapter = new DemandProbAdapter(demandProbs);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(adapter);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    private void createAdapterDemandProb(){

    }

    private void setCountActivity(){
        binding.buttonCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setPurchasePrice(Integer.parseInt(binding.etPurchasePrice.getText().toString()));
                model.setSellingPrice(Integer.parseInt(binding.etSellingPrice.getText().toString()));
                model.setDemands(adapter.getDemandProbs());
                int supplies[] = new int[model.getDemands().size()];
                for(int i=0; i<model.getDemands().size(); i++){
                    supplies[i] = model.getDemands().get(i).getDemand();
                }
                model.setSupplies(supplies);

                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("result",controller.getResult());
                startActivity(intent);
            }
        });

    }
}
