package com.boostcamp.dreampicker.data.repository;

import com.boostcamp.dreampicker.data.model.Feed;
import com.boostcamp.dreampicker.data.model.FeedUploadRequest;
import com.boostcamp.dreampicker.data.model.MyFeed;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Completable;
import io.reactivex.Single;

public class FeedRepositoryImpl implements FeedRepository {
    private static volatile FeedRepository INSTANCE = null;

    public static FeedRepository getInstance(@NonNull FirebaseFirestore firestore) {
        if (INSTANCE == null) {
            synchronized (FeedRepositoryImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FeedRepositoryImpl(firestore);
                }
            }
        }
        return INSTANCE;
    }

    private FeedRepositoryImpl(@NonNull FirebaseFirestore firestore) {
        this.firestore = firestore;
    }

    @NonNull
    private final FirebaseFirestore firestore;
    private final String COLLECTION_FEED = "feed";
    private final String COLLECTION_USER = "user";
    private final String SUBCOLLECTION_MYFEEDS = "myFeeds";
    private final String FIELD_DATE = "date";

    @NonNull
    @Override
    public Single<List<Feed>> getNotEndedMyFollowerFeedList(@NonNull Date startAfter, int pageSize) {
        return null;
    }

    @NonNull
    @Override
    public Single<Feed> vote(@NonNull String feedId, @NonNull String selectionId) {
        return null;
    }

    @NonNull
    @Override
    public Completable uploadFeed(@NonNull FeedUploadRequest feed) {
        return null;
    }

    @NonNull
    @Override
    public Single<List<MyFeed>> getFeedListByUserId(@NonNull String userId, Date startAfter, int pageSize) {
        return Single.create(emitter ->
                firestore.collection(COLLECTION_USER).document(userId)
                        .collection(SUBCOLLECTION_MYFEEDS)
                        .orderBy(FIELD_DATE, Query.Direction.DESCENDING)
                        .startAfter(startAfter)
                        .limit(pageSize)
                        .get()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) { // 쿼리 성공

                                // result 없거나 리스트가 비어있으면 null 반환
                                List<DocumentSnapshot> result =
                                        task.getResult() == null || task.getResult().isEmpty()
                                                ? null : task.getResult().getDocuments();

                                if (result != null) { // 결과 존재
                                    List<MyFeed> feedList = new ArrayList<>();
                                    for (DocumentSnapshot document : result) {
                                        feedList.add(document.toObject(MyFeed.class));
                                    }
                                    emitter.onSuccess(feedList);
                                } else {
                                    emitter.onError(new Throwable("result is empty"));
                                }
                            } else {
                                // 쿼리 실패
                                emitter.onError(task.getException());
                            }
                        })
                        .addOnFailureListener(emitter::onError));
    }
}
