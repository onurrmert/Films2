package com.onurmert.filmsjava.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.onurmert.filmsjava.Model.FilmsModel;
import com.onurmert.filmsjava.R;
import com.onurmert.filmsjava.View.CurrentFragmentDirections;
import com.onurmert.filmsjava.databinding.CurrentRecyclerRowBinding;
import java.util.ArrayList;

public class CurrentRecyclerAdapter extends RecyclerView.Adapter<CurrentRecyclerAdapter.CurentViewHolder>{

    private final ArrayList<FilmsModel> filmsModelList;

    public CurrentRecyclerAdapter(ArrayList<FilmsModel> filmsModelList){
        this.filmsModelList = filmsModelList;
    }

    public static class CurentViewHolder extends RecyclerView.ViewHolder {

        public CurrentRecyclerRowBinding binding = CurrentRecyclerRowBinding.bind(itemView);

        public CurentViewHolder(View itemView){
            super(itemView);
        }
    }

    @NonNull
    @Override
    public CurentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.current_recycler_row, parent, false);
        return new CurentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CurentViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(holder.binding.currentRowImageView.getContext())
                .load(filmsModelList.get(position).getLarge_cover_image())
                .error(R.drawable.ic_launcher_background)
                .into(holder.binding.currentRowImageView);

        holder.binding.ratingText.setText(filmsModelList.get(position).getRating());
        holder.binding.yearText.setText(filmsModelList.get(position).getYear());

        holder.binding.curentRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections directions = CurrentFragmentDirections.actionCurrentFragmentToFilmDetailFragment(filmsModelList.get(position).getId());
                Navigation.findNavController(view).navigate(directions);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filmsModelList.size();
    }
}
