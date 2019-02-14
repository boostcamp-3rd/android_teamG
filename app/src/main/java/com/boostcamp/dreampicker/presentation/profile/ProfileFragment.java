package com.boostcamp.dreampicker.presentation.profile;

import android.os.Bundle;
import android.view.View;

import com.boostcamp.dreampicker.R;
import com.boostcamp.dreampicker.data.Injection;
import com.boostcamp.dreampicker.databinding.FragmentProfileBinding;
import com.boostcamp.dreampicker.presentation.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding> {
    private static final String ARGUMENT_USER_ID = "ARGUMENT_USER_ID";

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance(@NonNull String userId) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARGUMENT_USER_ID, userId);
        fragment.setArguments(args);
        return fragment;
    }

    private String userId;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            userId = savedInstanceState.getString(ARGUMENT_USER_ID);
        } else {
            final Bundle args = getArguments();
            if (args != null) {
                userId = args.getString(ARGUMENT_USER_ID);
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
        initRecyclerView();
    }

    private void initViewModel() {
        ProfileViewModel viewModel = ViewModelProviders.of(this,
                new ProfileViewModelFactory(Injection.provideUserRepository(), userId))
                .get(ProfileViewModel.class);
        binding.setVm(viewModel);
    }

    private void initRecyclerView() {
        MyFeedAdapter adapter = new MyFeedAdapter(item ->
                binding.getVm().toggleVoteEnded(item, !item.isEnded()));
        binding.rvProfileFeed.setAdapter(adapter);

        // TODO. item 갱신
        binding.getVm().getIsLoading().observe(this, isLoading -> {
            if (!isLoading) {
                adapter.notifyDataSetChanged();
            }
        });
    }

}
