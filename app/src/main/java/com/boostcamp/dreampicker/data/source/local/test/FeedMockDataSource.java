package com.boostcamp.dreampicker.data.source.local.test;

import com.boostcamp.dreampicker.R;
import com.boostcamp.dreampicker.data.model.Feed;
import com.boostcamp.dreampicker.data.model.Image;
import com.boostcamp.dreampicker.data.model.User;
import com.boostcamp.dreampicker.data.source.FeedDataSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Single;

public class FeedMockDataSource implements FeedDataSource {
    private static volatile FeedMockDataSource INSTANCE;

    private FeedMockDataSource() {}

    public static FeedMockDataSource getInstance(){
        if(INSTANCE == null){
            synchronized (FeedMockDataSource.class){
                if(INSTANCE == null){
                    INSTANCE = new FeedMockDataSource();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Single<List<Feed>> getAllFeed() {
        List<Feed> feedList = new ArrayList<>();
        Image image1 = new Image("image-0-up", R.drawable.image1, "카페");
        Image image2 = new Image("image-0-down", R.drawable.image2, "술집");
        User user1 = new User("user-0", "박신혜", R.drawable.profile);
        Feed feed1 = new Feed(
                "feed-0",
                Arrays.asList(image1, image2),
                user1,
                "내일 소개남이랑 첫 데이트인데 장소 좀 골라주세요~!ㅎㅎ",
                "2018.01.28",
                false
        );

        Image image3 = new Image("image-1-up", R.drawable.jajang, "짜장면");
        Image image4 = new Image("image-1-down", R.drawable.jambong, "짬뽕");
        User user2 = new User("user-1", "공유", R.drawable.profile2);

        Feed feed2 = new Feed(
                "feed-1",
                Arrays.asList(image3, image4),
                user2,
                "짬뽕, 짜장면",
                "2019.01.29",
                false
        );

        feedList.add(feed1);
        feedList.add(feed2);

        return Single.just(feedList);
    }

    @Override
    public Single<List<Feed>> searchAllFeed(String searchKey) {
        List<Feed> feedList = new ArrayList<>();
        Image image1 = new Image("image-0-up", R.drawable.image1, "카페");
        Image image2 = new Image("image-0-down", R.drawable.image2, "술집");
        User user1 = new User("user-0", "박신혜", R.drawable.profile);
        Feed feed1 = new Feed(
                "feed-0",
                Arrays.asList(image1, image2),
                user1,
                "내일 소개남이랑 첫 데이트인데 장소 좀 골라주세요~!ㅎㅎ",
                "2018.01.28",
                false
        );

        Image image3 = new Image("image-1-up", R.drawable.jajang, "짜장면");
        Image image4 = new Image("image-1-down", R.drawable.jambong, "짬뽕");
        User user2 = new User("user-1", "공유", R.drawable.profile2);

        Feed feed2 = new Feed(
                "feed-1",
                Arrays.asList(image3, image4),
                user2,
                "짬뽕, 짜장면",
                "2019.01.29",
                false
        );

        feedList.add(feed1);
        feedList.add(feed2);

        return Single.just(feedList);
    }

    @Override
    public Single<List<Feed>> addMainFeedList(String userId, int pageIndex, int pageUnit) {
        List<Feed> feedList = new ArrayList<>();
        Image image1 = new Image("image-0-up", R.drawable.image1, "카페");
        Image image2 = new Image("image-0-down", R.drawable.image2, "술집");
        User user1 = new User("user-0", "박신혜", R.drawable.profile);
        Feed feed1 = new Feed(
                "feed-0",
                Arrays.asList(image1, image2),
                user1,
                "내일 소개남이랑 첫 데이트인데 장소 좀 골라주세요~!ㅎㅎ",
                "2018.01.28",
                false
        );

        Image image3 = new Image("image-1-up", R.drawable.jajang, "짜장면");
        Image image4 = new Image("image-1-down", R.drawable.jambong, "짬뽕");
        User user2 = new User("user-1", "공유", R.drawable.profile2);

        Feed feed2 = new Feed(
                "feed-1",
                Arrays.asList(image3, image4),
                user2,
                "짬뽕, 짜장면",
                "2019.01.29",
                false
        );

        feedList.add(feed1);
        feedList.add(feed2);

        return Single.just(feedList);
    }

    @Override
    public void updateFeedVote(String feedId, String userId, int voteFlag) {

    }

    @Override
    public Single<List<Feed>> addSearchFeedList(String searchKey, int pageIndex, int pageUnit) {
        List<Feed> feedList = new ArrayList<>();
        Image image1 = new Image("image-0-up", R.drawable.image1, "카페");
        Image image2 = new Image("image-0-down", R.drawable.image2, "술집");
        User user1 = new User("user-0", "박신혜", R.drawable.profile);
        Feed feed1 = new Feed(
                "feed-0",
                Arrays.asList(image1, image2),
                user1,
                "내일 소개남이랑 첫 데이트인데 장소 좀 골라주세요~!ㅎㅎ",
                "2018.01.28",
                false
        );

        Image image3 = new Image("image-1-up", R.drawable.jajang, "짜장면");
        Image image4 = new Image("image-1-down", R.drawable.jambong, "짬뽕");
        User user2 = new User("user-1", "공유", R.drawable.profile2);

        Feed feed2 = new Feed(
                "feed-1",
                Arrays.asList(image3, image4),
                user2,
                "짬뽕, 짜장면",
                "2019.01.29",
                false
        );

        feedList.add(feed1);
        feedList.add(feed2);

        return Single.just(feedList);
    }

    @Override
    public void toggleFeedState(String feedId, boolean isEnded) {

    }
}