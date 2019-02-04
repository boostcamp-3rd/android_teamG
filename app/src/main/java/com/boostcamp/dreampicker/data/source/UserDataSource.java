package com.boostcamp.dreampicker.data.source;

import com.boostcamp.dreampicker.data.model.User;
import com.boostcamp.dreampicker.data.model.UserDetail;

import java.util.List;

import io.reactivex.Single;

public interface UserDataSource {

    // [프로필] 해당 유저의 프로필 정보
    Single<UserDetail> getProfileUserDetail(String userId);

    // [프로필] 해당 유저가 팔로잉한 유저 리스트 페이징
    Single<List<User>> addProfileFollowingList(String userId, int pageIndex, int pageUnit);

    // [프로필] 해당 유저를 팔로잉한 유저 리스트 페이징
    Single<List<User>> addProfileFollowerList(String userId, int pageIndex, int pageUnit);

    // [검색] searchKey 기준 유저 검색 결과 리스트 페이징
    Single<List<User>> addSearchUserList(String searchKey, int pageIndex, int pageUnit);
}