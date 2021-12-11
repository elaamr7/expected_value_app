package com.example.appexpectedvalue.model;

import java.util.List;

public class Model {
    private int sellingPrice;
    private int purchasePrice;
    private int[] supplies;
    private List<DemandProb> demands;
    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    public void setDemands(List<DemandProb> demands){
        this.demands = demands;
    }
    public List<DemandProb> getDemands(){

        return demands;
    }
    public int[] getSupplies() {

        return supplies;
    }
    public void setSupplies(int[] supplies) {

        this.supplies = supplies;
    }

    public int getRow(){

        return supplies.length;
    }
    public int getRowColumn(){

        return demands.size();
    }


}
