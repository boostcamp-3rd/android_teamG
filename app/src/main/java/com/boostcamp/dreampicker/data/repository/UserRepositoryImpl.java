package com.boostcamp.dreampicker.data.repository;

import com.boostcamp.dreampicker.data.model.UserDetail;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import io.reactivex.Single;

public class UserRepositoryImpl implements UserRepository {
    private static UserRepository INSTANCE = null;

    public static UserRepository getInstance(@NonNull final FirebaseFirestore firestore) {
        if (INSTANCE == null) {
            synchronized (UserRepositoryImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserRepositoryImpl(firestore);
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    private final FirebaseFirestore firestore;
    private final String COLLECTION_USER = "user";

    private UserRepositoryImpl(@NonNull final FirebaseFirestore firestore) {
        this.firestore = firestore;
    }

    @NonNull
    @Override
    public Single<UserDetail> getUserDetail(@NonNull final String userId) {

        return Single.create(emitter ->
                firestore.collection(COLLECTION_USER).document(userId)
                        .get()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // 쿼리 성공
                                DocumentSnapshot document = task.getResult();
                                if (document != null && !document.exists()) { // 결과 존재
                                    emitter.onSuccess(document.toObject(UserDetail.class));
                                } else { // 결과 없음
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
