package com.boostcamp.dreampicker.view.upload;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.boostcamp.dreampicker.R;
import com.boostcamp.dreampicker.databinding.ActivityUploadBinding;
import com.boostcamp.dreampicker.utils.Util;
import com.boostcamp.dreampicker.view.BaseActivity;
import com.tedpark.tedpermission.rx2.TedRx2Permission;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import gun0912.tedbottompicker.TedBottomPicker;
import io.reactivex.disposables.Disposable;

public class UploadActivity extends BaseActivity<ActivityUploadBinding> {
    private static final String CAMERA = Manifest.permission.CAMERA;
    private static final String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;

    private boolean leftImageAnimationToggle = false;
    private boolean rightImageAnimationToggle = false;

    private ConstraintLayout rootLayout;

    private ConstraintSet rootSet = new ConstraintSet();
    private ConstraintSet leftSet = new ConstraintSet();
    private ConstraintSet rightSet = new ConstraintSet();

    private ImageView leftImage, rightImage;

    private Disposable disposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        rootLayout = findViewById(R.id.cl_upload_root);
        rootSet.clone(rootLayout);
        leftSet.clone(this, R.layout.activity_upload_detail_left);
        rightSet.clone(this, R.layout.activity_upload_detail_right);

        leftImage = findViewById(R.id.iv_upload_left);
        rightImage = findViewById(R.id.iv_upload_right);

        leftImage.setOnClickListener((v) -> onImageClick(Util.LEFT));
        rightImage.setOnClickListener((v) -> onImageClick(Util.RIGHT));
    }

    public void onImageClick(@Util.VoteFlag int flag) {
        disposable = TedRx2Permission.with(this)
                .setPermissions(CAMERA, WRITE_EXTERNAL_STORAGE)
                .request()
                .subscribe(result -> {
                    if (result.isGranted()) {
                        showBottomPicker(flag);
                    } else {
                        Toast.makeText(this, "권한이 없습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void showBottomPicker(@Util.VoteFlag int flag) {
        if (leftImageAnimationToggle || rightImageAnimationToggle) {
            applyLayoutAnimation(flag);
        } else {
            new TedBottomPicker.Builder(this)
                    .setOnImageSelectedListener(uri -> applyImage(uri, flag))
                    .setPeekHeight(800)
                    .showTitle(true)
                    .create()
                    .show(getSupportFragmentManager());
        }
    }

    private void applyImage(Uri uri, @Util.VoteFlag int flag) {
        if(flag == Util.LEFT) {
            leftImage.setImageURI(uri);
        } else {
            rightImage.setImageURI(uri);
        }
        applyLayoutAnimation(flag);
    }

    private void applyLayoutAnimation(@Util.VoteFlag int flag) {
        TransitionManager.beginDelayedTransition(rootLayout);
        ConstraintSet set = getCurrentSet(flag);
        set.applyTo(rootLayout);
    }

    private ConstraintSet getCurrentSet(@Util.VoteFlag int flag) {
        if (flag == Util.LEFT) {
            leftImageAnimationToggle = !leftImageAnimationToggle;
            return leftImageAnimationToggle ? leftSet : rootSet;
        } else {
            rightImageAnimationToggle = !rightImageAnimationToggle;
            return rightImageAnimationToggle ? rightSet : rootSet;
        }
    }

    public static Intent getLaunchIntent(Context context) {
        Intent intent = new Intent(context, UploadActivity.class);
        return intent;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_upload;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(!disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
