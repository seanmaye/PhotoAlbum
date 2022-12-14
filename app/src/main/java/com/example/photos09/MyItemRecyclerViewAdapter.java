package com.example.photos09;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {
    Context context;
    List<Photo> photos;
    ImageView preview;
     int selectedPos = 0;
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView image;
        ImageView check;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image =itemView.findViewById(R.id.imageView2);
            check=itemView.findViewById(R.id.imageView3);
        }
        void bind(final Photo photo){
            if(selectedPos ==-1){
                check.setVisibility(View.GONE);
            }else{
                if(selectedPos==getAdapterPosition()){
                    check.setVisibility(View.VISIBLE);
                }else{
                    check.setVisibility(View.GONE);
                }
            }
        }
        @Override
        public void onClick(View view) {
            notifyItemChanged(selectedPos);
            selectedPos = getLayoutPosition();
            notifyItemChanged(selectedPos);
            preview.findViewById(R.id.imageView);
            preview.setImageURI(getSelected().getUri());
        }
    }

    public MyItemRecyclerViewAdapter(Context context, List<Photo> photos){
        this.context=context;
        this.photos=photos;
    }
    @NonNull
    @Override
    public MyItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view=inflater.from(parent.getContext()).inflate(R.layout.single_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyItemRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.image.setImageURI(photos.get(position).getUri());
        holder.itemView.setSelected(selectedPos == position);

    }

    @Override
    public int getItemCount() {
        return photos.size();
    }
    public Photo getSelected() {
        if(selectedPos != -1){
            return photos.get(selectedPos);
        }
        return null;
    }
    public int getSelectedIndex() {
        if(selectedPos != -1){
            return selectedPos;
        }
        return -1;
    }
}