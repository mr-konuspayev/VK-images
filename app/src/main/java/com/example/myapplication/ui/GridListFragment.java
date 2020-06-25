package com.example.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.pojo.Album;
import com.example.myapplication.pojo.Photo;
import com.example.myapplication.ui.GridViewModel.Factory;
import com.example.myapplication.ui.grid.GridItem;

import static com.example.myapplication.ui.GridViewModel.Depth.ALBUMS;
import static com.example.myapplication.ui.GridViewModel.Depth.PHOTOS;

/**
 * A fragment representing a list of Items.
 */
public class GridListFragment extends Fragment {
    private GridViewModel viewModel;

    public static GridListFragment newInstance(Bundle args) {
        GridListFragment fragment = new GridListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, new Factory(getArguments())).get(GridViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView =
                (RecyclerView) inflater.inflate(R.layout.fragment_albums, container, false);
        recyclerView.setHasFixedSize(true);

        GridRVA gridRVA = new GridRVA(this::act);
        viewModel.items().observe(getViewLifecycleOwner(), gridRVA::submitList);
        recyclerView.setAdapter(gridRVA);

        return recyclerView;
    }

    private void act(GridItem gridItem) {
        switch (viewModel.getDepth()) {
            case ALBUMS:
                Bundle bundle = new Bundle();
                bundle.putSerializable(GridViewModel.Depth.class.getName(), PHOTOS);
                bundle.putLong("albumId", gridItem.getId());
                //noinspection ConstantConditions
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, GridListFragment.newInstance(bundle))
                        .addToBackStack(PHOTOS.name())
                        .commit();
                break;
            case PHOTOS:
                break;
            default:
                throw new RuntimeException("Unhandled depth");
        }
    }

    private void openAlbum(Album album) {
    }

    private void openPhoto(Photo photo) {

    }
}