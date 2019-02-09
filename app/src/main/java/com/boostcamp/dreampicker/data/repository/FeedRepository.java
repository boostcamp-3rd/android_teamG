package com.boostcamp.dreampicker.data.repository;

import com.boostcamp.dreampicker.data.model.ProfileFeed;

import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Single;

public interface FeedRepository {

    /**
     * 유저가 업로드 한 투표 목록 페이징
     *
     * @param userId 유저 ID
     * @param startAfter 이 값의 다음 데이터를 요청
     * @param pageSize 페이지의 size
     * @return 피드 리스트 stream
     */
    @NonNull
    Single<List<ProfileFeed>> getProfileFeedList(@NonNull final String userId, final Date startAfter, final int pageSize);
}
