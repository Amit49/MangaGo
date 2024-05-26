package com.example.Vyvymanga;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MM_RecyclerViewAdapter extends RecyclerView.Adapter<MM_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<MangaLinkModel> mangaLinkModels;
    public MM_RecyclerViewAdapter(Context context, ArrayList<MangaLinkModel> mangaLinkModels) {
        this.context = context;
        this.mangaLinkModels = mangaLinkModels;
    }

    @NonNull
    @Override
    public MM_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row,parent,false);
        return new MM_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MM_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.tvName.setText(mangaLinkModels.get(position).getName());
        holder.setLinkToLoad(mangaLinkModels.get(position).getLink());
    }

    @Override
    public int getItemCount() {
        return mangaLinkModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
//        Button button;
        TextView tvName;
        String linkToLoad;

        public void setLinkToLoad(String linkToLoad) {
            this.linkToLoad = linkToLoad;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.NameOfWebsite);

        CardView cardView = itemView.findViewById(R.id.myCard);
        cardView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i("AMIT", "onClick: "+getAdapterPosition());
            Intent intent = new Intent(v.getContext(), GeckoviewAactivity.class);
            intent.putExtra("key", linkToLoad);
            v.getContext().startActivity(intent);
//                    if (v.isSelected()) {
//                        cardView.setCardBackgroundColor(cardView.getResources().getColor(R.color.selected_color, null));
//                    } else {
//                        cardView.setCardBackgroundColor(cardView.getResources().getColor(R.color.default_color, null));
//                    }
//                    v.setSelected(!v.isSelected());  // Toggle selected state
        }
    });
        }
    }
}
