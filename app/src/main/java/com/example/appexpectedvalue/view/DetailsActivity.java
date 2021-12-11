package com.example.appexpectedvalue.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.appexpectedvalue.R;
import com.example.appexpectedvalue.databinding.ActivityDetailsBinding;
import com.example.appexpectedvalue.model.Result;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding binding;
    Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        setToolbar();
        setTable();
        setResult();
    }
    private void init(){
        Intent intent = getIntent();
        result = (Result) intent.getSerializableExtra("result");
    }
    private void setToolbar(){
        setSupportActionBar(binding.toolbarDetails.toolbar);
        getSupportActionBar().setTitle("Detail Perhitungan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void setTable(){
        int column = result.getMatrix()[0].length;
        int row = result.getMatrix().length;
        TableLayout tableLayout = binding.tablePayOff;

        for(int i=0; i<row; i++) {
            TableRow tableRow = new TableRow(this);
            for (int j = 0; j < column; j++) {
                if (i == 0 && j == 0){
                    TextView textView = new TextView(this);
                    textView.setPadding(30, 30, 30, 30);
                    textView.setBackgroundColor(getResources().getColor(R.color.white));
                    tableRow.addView(textView);
                }
                else if (j != column - 1) {
                    if(i==0 || (j==0 && i!=0)) {
                        TextView textView = new TextView(this);
                        if(i==0){
                            textView.setText(String.valueOf(result.getMatrix()[i][j] + "(" + result.getProbability()[j-1] + ")"));
                        }
                        else{
                            textView.setText(String.valueOf(result.getMatrix()[i][j]));
                        }

                        textView.setPadding(30, 30, 30, 30);

                        textView.setTextColor(getResources().getColor(R.color.white));
                        textView.setBackgroundColor(getResources().getColor(R.color.black));
                        textView.setTextSize(16);
                        tableRow.addView(textView);
                    }
                    else{
                        TextView textView = new TextView(this);
                        textView.setPadding(30, 30, 30, 30);
                        textView.setText(String.valueOf(result.getMatrix()[i][j]));
                        textView.setTextColor(getResources().getColor(R.color.black));
                        textView.setBackgroundColor(getResources().getColor(R.color.white));
                        tableRow.addView(textView);

                    }
                }
                else if(j==column-1){

                    if(i!=0){
                        TextView textView = new TextView(this);
                        textView.setPadding(30, 30, 30, 30);
                        textView.setText(String.valueOf(result.getMatrix()[i][j]));
                        textView.setTextColor(getResources().getColor(R.color.black));
                        textView.setBackgroundColor(getResources().getColor(R.color.white));
                        tableRow.addView(textView);
                    }
                    else{
                        TextView textView = new TextView(this);
                        textView.setText("EV");
                        textView.setPadding(30, 30, 30, 30);
                        textView.setTextColor(getResources().getColor(R.color.white));
                        textView.setBackgroundColor(getResources().getColor(R.color.design_default_color_secondary));
                        textView.setTextSize(16);
                        tableRow.addView(textView);
                    }


                }
            }
            tableLayout.addView(tableRow);
        }


    }
    private void setResult(){
        binding.largestEv.setText(String.valueOf(result.getResult()));
    }
}