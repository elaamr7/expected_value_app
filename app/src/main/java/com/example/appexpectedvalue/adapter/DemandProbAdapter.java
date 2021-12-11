package com.example.appexpectedvalue.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appexpectedvalue.model.DemandProb;
import com.example.appexpectedvalue.R;

import java.util.ArrayList;
import java.util.List;

public class DemandProbAdapter extends RecyclerView.Adapter<DemandProbAdapter.ViewHolder> {

    ArrayList<DemandProb> models = new ArrayList();

    public DemandProbAdapter(ArrayList models){
        this.models = models;
    }
    @NonNull
    @Override
    public DemandProbAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_demandprob,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DemandProbAdapter.ViewHolder holder, int position) {
        DemandProb model = models.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText etDemand;
        EditText etProbability;
        boolean isOnTextChanged = false;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etDemand = itemView.findViewById(R.id.et_demand);
            etProbability = itemView.findViewById(R.id.et_probability);
        }
        public void bind(DemandProb model){
            etDemand.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    isOnTextChanged = true;
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if(isOnTextChanged){
                        isOnTextChanged = false;
                    }
                    if(!editable.toString().isEmpty()) {
                        model.setDemand(Integer.parseInt(editable.toString()));
                    }
                }
            });
            etProbability.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    isOnTextChanged = true;
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if(isOnTextChanged){
                        isOnTextChanged = false;
                    }
                    if(!editable.toString().isEmpty()) {
                        model.setProbability(Double.parseDouble(editable.toString()));
                    }
                }
            });
        }
    }
    public List<DemandProb> getDemandProbs(){
        return models;
    }
}
