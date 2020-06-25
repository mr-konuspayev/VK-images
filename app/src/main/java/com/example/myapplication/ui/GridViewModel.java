package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.pojo.Album;
import com.example.myapplication.pojo.Items;
import com.example.myapplication.pojo.Photo;
import com.example.myapplication.pojo.Response;
import com.example.myapplication.requests.AlbumsRequest;
import com.example.myapplication.requests.PhotosRequest;
import com.example.myapplication.ui.grid.AlbumAdapter;
import com.example.myapplication.ui.grid.GridItem;
import com.example.myapplication.ui.grid.PhotoAdapter;
import com.vk.api.sdk.VK;
import com.vk.api.sdk.VKApiCallback;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.myapplication.ui.GridViewModel.Depth.PHOTOS;

public class GridViewModel extends ViewModel {
    private final Depth depth;
    private final MutableLiveData<List<GridItem>> items = new MutableLiveData<>();

    public GridViewModel(Bundle bundle) {
        depth = (Depth) bundle.getSerializable(Depth.class.getName());
        if (depth == null)
            throw new NullPointerException("Depth cannot be null");
        if (depth == Depth.ALBUMS) {
            loadAlbums();
        } else if (depth == PHOTOS) {
            long albumId = bundle.getLong("albumId", -1);
            if (albumId == -1)
                throw new IllegalStateException("albumId is absent");
            loadPhotos(albumId);
        } else
            throw new RuntimeException("Unhandled depth");
    }

    private void loadAlbums() {
        VK.execute(new AlbumsRequest(), new VKApiCallback<Response<Items<Album>>>() {
            @Override
            public void success(Response<Items<Album>> itemsResponse) {
                items.setValue(itemsResponse.getResponse().getItems()
                        .stream()
                        .map(AlbumAdapter::new)
                        .collect(Collectors.toList()));
            }

            @Override
            public void fail(@NotNull Exception e) {
            }
        });
    }

    private void loadPhotos(long albumId) {
        VK.execute(new PhotosRequest(albumId), new VKApiCallback<Response<Items<Photo>>>() {
            @Override
            public void success(Response<Items<Photo>> itemsResponse) {
                items.setValue(itemsResponse.getResponse().getItems()
                        .stream()
                        .map(PhotoAdapter::new)
                        .collect(Collectors.toList()));
            }

            @Override
            public void fail(@NotNull Exception e) {
            }
        });
    }

    public Depth getDepth() {
        return depth;
    }

    public LiveData<List<GridItem>> items() {
        return items;
    }

    public enum Depth {
        ALBUMS,
        PHOTOS
    }

    public static class Factory implements ViewModelProvider.Factory {
        private final Bundle bundle;

        public Factory(Bundle bundle) {
            this.bundle = bundle;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            //noinspection unchecked
            return (T) new GridViewModel(bundle);
        }
    }
}
