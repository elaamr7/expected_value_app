package com.example.appexpectedvalue.controller;

import android.util.Log;
import android.view.View;

import com.example.appexpectedvalue.databinding.ActivityCountBinding;
import com.example.appexpectedvalue.model.Model;
import com.example.appexpectedvalue.model.Result;

public class CountController {

    Model model;
    int row;
    int column;
    int[][] matrix  = new int[0][0];
    private static int profit;

    public CountController(Model model){
        this.model = model;

    }

    public void init(){
        row = model.getSupplies().length + 1;
        column = model.getDemands().size() + 2;
        matrix = new int[row][column];
        Log.d("####", String.valueOf(model.getDemands().get(0).getDemand()));
        int i=0;
        for(int j=1; j<column-1; j++){
            matrix[i][j] = model.getDemands().get(j-1).getDemand();
        }
        System.out.println();
        for(int j=1; j<row; j++){
            matrix[j][i] = model.getSupplies()[j-1];
        }
    }
    public void countPayOff(){
        for(int i=1; i<row; i++){
            for(int j=1; j<column-1; j++){
                int supply = model.getSupplies()[i-1];
                int demand = model.getDemands().get(j-1).getDemand();
                if(demand==supply || demand<supply ){
                    profit = (model.getSellingPrice()*demand) - (model.getPurchasePrice()*supply);
                }
                matrix[i][j] = profit;
            }
        }
    }
    public void countExpectedValue(){
        for(int i=1; i<row; i++){
            int total = 0;
            for(int j=1; j<column; j++){
                if(j==column-1){
                    matrix[i][j] = total;
                }
                else{
                    total+= matrix[i][j]*model.getDemands().get(j-1).getProbability();
                }

            }
        }
    }
    public int getLargestExpectedValue(){
        int result = 0;
        if(row!=2){
            for(int i=0; i<row-1; i++){
                if (matrix[i][column - 1] >= matrix[i + 1][column - 1]) {
                    result = matrix[i][column - 1];
                }
            }

        }
        else{
            result = matrix[1][column-1];
        }

        return result;
    }
    public Result getResult(){
        init();
        countPayOff();
        countExpectedValue();

        double[] probability = new double[column-1];

        for(int i=0; i<model.getDemands().size(); i++){
            probability[i] = model.getDemands().get(i).getProbability();
        }

        Result result = new Result(matrix,getLargestExpectedValue(),probability);
        return result;
    }



}
