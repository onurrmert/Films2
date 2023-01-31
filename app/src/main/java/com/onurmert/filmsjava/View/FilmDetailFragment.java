package com.onurmert.filmsjava.View;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.onurmert.filmsjava.Model.FilmsModel;
import com.onurmert.filmsjava.R;
import com.onurmert.filmsjava.ViewModel.FilmDetailViewModel;
import com.onurmert.filmsjava.databinding.FragmentFilmDetailBinding;
import com.squareup.picasso.Picasso;

public class FilmDetailFragment extends Fragment {

    private FragmentFilmDetailBinding binding;

    private FilmDetailViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFilmDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(FilmDetailViewModel.class);
        viewModel.getOneFilm(requireContext(), getID());
    }

    @Override
    public void onResume() {
        super.onResume();
        getOneFilm();
    }

    private void getOneFilm(){
        viewModel.filmsModel.observe(requireActivity(), new Observer<FilmsModel>() {
            @Override
            public void onChanged(FilmsModel filmsModel) {
                init(filmsModel);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void init(FilmsModel filmsModel){

        binding.ratingText.setText("Rating:" + filmsModel.getRating());
        binding.summaryText.setText(filmsModel.getSummary());
        binding.titleText.setText(filmsModel.getTitle());

        Picasso.get()
                .load(filmsModel.getLarge_cover_image())
                .error(R.drawable.ic_launcher_background)
                .into(binding.filmImageView);

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filmsLink(filmsModel);
            }
        });
    }

    private void filmsLink(FilmsModel filmsModel){
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, filmsModel.getUrlFilm());
        startActivity(intent);
    }

    private int getID(){
        Bundle bundle = getArguments();
        FilmDetailFragmentArgs args = FilmDetailFragmentArgs.fromBundle(bundle);
        return args.getId();
    }
}