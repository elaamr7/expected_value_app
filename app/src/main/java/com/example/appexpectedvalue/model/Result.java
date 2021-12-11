package com.example.appexpectedvalue.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Result implements Serializable {

    int[][] matrix  = new int[0][0];
    int result;
    double[] probability  = new double[0];

    public Result(int[][] matrix, int result, double[] probability){
        this.matrix = matrix;
        this.result = result;
        this.probability = probability;
    }

    protected Result(Parcel in) {
        result = in.readInt();
    }
    public int[][] getMatrix() {
        return matrix;
    }

    public int getResult() {
        return result;
    }

    public double[] getProbability(){
        return probability;}

}
