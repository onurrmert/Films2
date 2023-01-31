package com.onurmert.filmsjava.View;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.onurmert.filmsjava.Adapter.CurrentRecyclerAdapter;
import com.onurmert.filmsjava.R;
import com.onurmert.filmsjava.Retrofit.ApiClient;
import com.onurmert.filmsjava.ViewModel.CurrentViewModel;
import com.onurmert.filmsjava.Model.FilmsModel;
import com.onurmert.filmsjava.databinding.FragmentCurrentBinding;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CurrentFragment extends Fragment {

    private FragmentCurrentBinding binding;

    private CurrentViewModel viewModel;

    private final ApiClient apiClient =  new ApiClient();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCurrentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiClient.getFilms(requireContext());

        viewModel = new ViewModelProvider(requireActivity()).get(CurrentViewModel.class);
        viewModel.getAllFilms(requireContext());

        getFilmList();
    }

    @Override
    public void onResume() {
        super.onResume();

        viewModel.getAllFilms(requireContext());
        getFilmList();
        swipeRefresh();
    }

    private void swipeRefresh(){

        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.getAllFilms(requireContext());
                getFilmList();
                binding.swipeRefresh.setRefreshing(false);
            }
        });
    }

    private void getFilmList(){

        viewModel.filmList.observe(requireActivity(), new Observer<List<FilmsModel>>() {
            @Override
            public void onChanged(List<FilmsModel> filmsModels) {
                createRecycler(filmsModels);
            }
        });
    }

    private void createRecycler(List<FilmsModel> filmsModels){

        ArrayList<FilmsModel> filmsModelArrayList =  new ArrayList<>(filmsModels);
        Collections.reverse(filmsModelArrayList);

        GridLayoutManager manager = new GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false);
        binding.currentRecyclerView.setLayoutManager(manager);
        binding.currentRecyclerView.setAdapter(new CurrentRecyclerAdapter(filmsModelArrayList));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        MenuInflater menuInflater = new MenuInflater(requireContext());
        menuInflater.inflate(R.menu.refresh_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.refresh){
            refresh();
        }
        return super.onOptionsItemSelected(item);
    }

    private void refresh(){

        viewModel.delete(requireContext());

        ProgressDialog progressDialog = new ProgressDialog(requireContext());
        progressDialog.setMessage("Refreshing");
        progressDialog.setCancelable(false);
        progressDialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.hide();
                Toast.makeText(requireContext(), "Refresh...", Toast.LENGTH_SHORT).show();
                insert();
            }
        }, 2000);

        insert();
    }

    private void insert(){
        apiClient.getFilms(requireContext());
        viewModel.getAllFilms(requireContext());
        swipeRefresh();
    }
}