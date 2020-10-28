package com.world_tech_point.visiting_earnapp.withdraw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.world_tech_point.visiting_earnapp.R;

import java.util.List;

public class WithdrawAdapter extends RecyclerView.Adapter<WithdrawAdapter.ViewHolder> {

    private Context context;
    private List<WithdrawClass>withdrawClassList;
    private WithdrawClass withdrawClass;

    public WithdrawAdapter(Context context, List<WithdrawClass> withdrawClassList) {
        this.context = context;
        this.withdrawClassList = withdrawClassList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.history_view_model,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        withdrawClass = withdrawClassList.get(position);

        holder.numberTV.setText(withdrawClass.getNumber());
        holder.amountTV.setText(withdrawClass.getAmount());
        holder.statusTV.setText(withdrawClass.getStatus());
        holder.methodTV.setText(withdrawClass.getpMethod());

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return withdrawClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView numberTV, amountTV,statusTV,methodTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            numberTV = itemView.findViewById(R.id.historyModelNumber);
            amountTV = itemView.findViewById(R.id.historyModelAmount);
            statusTV = itemView.findViewById(R.id.historyModelStatus);
            methodTV = itemView.findViewById(R.id.historyModelMethod);


        }
    }
}
