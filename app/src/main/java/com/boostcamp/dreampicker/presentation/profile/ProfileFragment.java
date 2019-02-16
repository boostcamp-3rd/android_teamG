package com.boostcamp.dreampicker.presentation.profile;

import android.os.Bundle;
import android.view.View;

import com.boostcamp.dreampicker.R;
import com.boostcamp.dreampicker.data.common.FirebaseManager;
import com.boostcamp.dreampicker.databinding.FragmentProfileBinding;
import com.boostcamp.dreampicker.presentation.BaseFragment;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding> {

    @Inject
    ProfileViewModelFactory factory;
    @Inject
    String userId;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
        initRecyclerView();
    }

    private void initViewModel() {
        final ProfileViewModel viewModel =
                ViewModelProviders.of(this, factory).get(ProfileViewModel.class);
        binding.container.setVm(viewModel);
    }

    private void initRecyclerView() {
        MyFeedAdapter adapter = new MyFeedAdapter(item ->
                binding.container.getVm().toggleVoteEnded(item, !item.isEnded()),
                userId.equals(FirebaseManager.getCurrentUserId()));

        binding.container.rvProfileFeed.setAdapter(adapter);

        binding.container.getVm().getIsLoading().observe(this, isLoading -> {
            if (!isLoading) {
                adapter.notifyDataSetChanged();
            }
        });
    }

}
