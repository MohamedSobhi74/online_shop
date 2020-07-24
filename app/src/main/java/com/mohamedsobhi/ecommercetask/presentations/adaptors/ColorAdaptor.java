package com.mohamedsobhi.ecommercetask.presentations.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.mohamedsobhi.ecommercetask.R;
import com.mohamedsobhi.ecommercetask.entities.productDetails.Color;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColorAdaptor extends RecyclerView.Adapter<ColorAdaptor.ColorViewHolder> {
    Context context;
    List<Color> colors;
    int selectedPosition = 0;
    public static MutableLiveData<String> color_selcted = new MutableLiveData<>();

    public ColorAdaptor(Context context, List<Color> colors) {
        this.context = context;
        this.colors = colors;
    }


    @NonNull
    @Override
    public ColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.color_item, parent, false);
        return new ColorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorViewHolder holder, int position) {


        holder.colorText.setText(colors.get(position).getName());
        if (selectedPosition == position) {
            holder.colorCard.setBackgroundResource(R.drawable.border_bold_background);
            holder.colorText.setTextSize(20);
            color_selcted.setValue(colors.get(position).getName());
        } else {
            holder.colorCard.setBackgroundResource(R.drawable.border_gray_background);
            holder.colorText.setTextSize(18);
        }

        holder.colorCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;
                notifyDataSetChanged();

            }
        });
    }


    @Override
    public int getItemCount() {
        return colors.size();
    }


    public class ColorViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.color_text)
        TextView colorText;
        @BindView(R.id.color_card)
        ConstraintLayout colorCard;

        public ColorViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

