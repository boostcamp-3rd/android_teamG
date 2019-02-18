package com.boostcamp.dreampicker.extension.databinding;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.boostcamp.dreampicker.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import me.gujun.android.taggroup.TagGroup;

public class BindingUtil {
    @SuppressWarnings("unchecked")
    @BindingAdapter({"items"})
    public static <T, VH extends RecyclerView.ViewHolder> void setItems(
            @NonNull final RecyclerView recyclerView,
            @Nullable final List<T> items) {
        final ListAdapter<T, VH> adapter = (ListAdapter<T, VH>) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.submitList(items == null ? null : new ArrayList<>(items));
        }
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter({"pagedItems"})
    public static <T, VH extends RecyclerView.ViewHolder> void setPagedItems(
            @NonNull final RecyclerView recyclerView,
            @Nullable final PagedList<T> items) {
        final PagedListAdapter<T, VH> adapter = (PagedListAdapter<T, VH>) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.submitList(items);
        }
    }

    @BindingAdapter({"tagItems"})
    public static void setTagItems(@NonNull final TagGroup tagGroup, @Nullable final List<String> tagList) {
        if (tagList != null) {
            tagGroup.setTags(tagList);
            tagGroup.setVisibility(View.VISIBLE);
        } else {
            tagGroup.setVisibility(View.GONE);
        }
    }

    @BindingAdapter(value = {"rcProgress", "rcMax"}, requireAll = true)
    public static void setProgress(@NonNull final RoundCornerProgressBar progressBar, final int progress, final int max) {
        progressBar.setMax(max);
        progressBar.setProgress(progress);
    }

    @SuppressLint("SimpleDateFormat")
    @BindingAdapter({"date"})
    public static void convertDateToDisplayText(@NonNull final TextView textView,
                                                @Nullable final Date inputDate) {
        if (inputDate == null) {
            return;
        }

        final SimpleDateFormat dateYearOnly = new SimpleDateFormat("yyyy.MM.dd");

        String displayString = "";

        long diffTimeMillis = System.currentTimeMillis() - inputDate.getTime(); // 경과된 시간 (ms)
        int diffTime = (int) (diffTimeMillis / (1000 * 60)); // 분 단위로 변경

        if (diffTime >= 0) {
            if (diffTime < 60) { // ~ 59분 전
                displayString = diffTime + "분 전";
            } else {
                diffTime = diffTime / 60; // 시간 단위로 변경
                if (diffTime < 24) { // ~ 23시간 전
                    displayString = diffTime + "시간 전";
                } else { // 날짜 출력
                    displayString = dateYearOnly.format(inputDate);
                }
            }
        }
        textView.setText(displayString);
    }


    @SuppressLint("ResourceAsColor")
    @BindingAdapter(value = {"isEnded", "isVotePosition"})
    public static void setVoteButton(@NonNull final Button button, final boolean isEnded, final boolean isVotePosition) {
        if (isEnded) {
            button.setBackgroundResource(R.color.colorGray);
            button.setClickable(false);
            button.setText(R.string.feed_detail_ended_vote_button);
        } else {
            button.setText(R.string.feed_detail_vote_button);
            if (isVotePosition) {
                button.setBackgroundResource(R.color.colorGray);
                button.setClickable(false);
            } else {
                button.setBackgroundResource(R.color.colorPrimaryDark);
                button.setClickable(true);
            }
        }

    }
}
