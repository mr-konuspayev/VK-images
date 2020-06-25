package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory;

import com.example.myapplication.R;
import com.vk.api.sdk.VK;
import com.vk.api.sdk.auth.VKAccessToken;
import com.vk.api.sdk.auth.VKAuthCallback;
import com.vk.api.sdk.auth.VKScope;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;

import static com.example.myapplication.ui.GridViewModel.Depth;
import static com.example.myapplication.ui.GridViewModel.Depth.ALBUMS;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this, new AndroidViewModelFactory(getApplication()))
                .get(MainViewModel.class);
        viewModel.userName().observe(this, s -> getSupportActionBar().setTitle(s));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (VK.isLoggedIn())
            load();
        else
            startLogin();
    }

    public void startLogin() {
        VK.login(this, Collections.singletonList(VKScope.PHOTOS));
    }

    private void load() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(ALBUMS.name());
        if (fragment == null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(Depth.class.getName(), ALBUMS);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, GridListFragment.newInstance(bundle), ALBUMS.name())
                    .addToBackStack(ALBUMS.name())
                    .commit();
        }
        viewModel.onLogin();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, R.id.logout, 0, R.string.logout);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                onBackPressed();
                return true;
            case R.id.logout:
                relogin();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void relogin() {
        //noinspection StatementWithEmptyBody
        while (getSupportFragmentManager().popBackStackImmediate()) {
        }
        VK.logout();
        VK.login(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, new VKAuthCallback() {
            @Override
            public void onLogin(@NotNull VKAccessToken vkAccessToken) {
                load();
                viewModel.onLogin();
            }

            @Override
            public void onLoginFailed(int i) {
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        if (!getSupportFragmentManager().popBackStackImmediate())
            super.onBackPressed();
    }
}