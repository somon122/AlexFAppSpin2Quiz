package com.world_tech_point.visiting_earnapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.world_tech_point.visiting_earnapp.earning.QuizActivity;
import com.world_tech_point.visiting_earnapp.earning.Spin2Activity;
import com.world_tech_point.visiting_earnapp.earning.SpinningActivity;
import com.world_tech_point.visiting_earnapp.withdraw.WalletActivity;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.ViewHolder> {

    private Context context;
    private List<SliderClass> sliderClassList;
    private ViewPager2 viewPager2;
    private SliderClass sliderClass;

    public SliderAdapter(Context context, List<SliderClass> sliderClassList, ViewPager2 viewPager2) {
        this.context = context;
        this.sliderClassList = sliderClassList;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.slide_view_cointainer,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderAdapter.ViewHolder holder, final int position) {

        sliderClass = sliderClassList.get(position);
        Picasso.get().load(sliderClass.getmImage()).fit().into(holder.roundedImageView);
        holder.btnTitle.setText(sliderClass.getmBtnTitle());
        holder.title.setText(sliderClass.getmTitle());

        holder.btnTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goMethod(position);

            }
        });
    }

    private void goMethod(int position) {

       if (position==0){
           context.startActivity(new Intent(context, QuizActivity.class));
       } else if (position == 1) {
           context.startActivity(new Intent(context, Spin2Activity.class));
       }else if (position == 2) {
           context.startActivity(new Intent(context, SpinningActivity.class));
       }else {
           Toast.makeText(context, "No Match", Toast.LENGTH_SHORT).show();
       }

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
        return sliderClassList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView roundedImageView;
        TextView title;
        Button btnTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            roundedImageView = itemView.findViewById(R.id.roundedImageView);
            title = itemView.findViewById(R.id.title_id);
            btnTitle = itemView.findViewById(R.id.btnTitle);

        }
    }
}
