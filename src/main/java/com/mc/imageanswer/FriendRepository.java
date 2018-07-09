package com.mc.imageanswer;

import org.springframework.data.repository.CrudRepository;

public interface FriendRepository extends CrudRepository<Friend, Long> {
    Iterable<Friend> findAllByMyFriend(AppUser aFriend);
    Iterable<Friend> findAllByMyFriendOrderByMyRankDesc(AppUser aFriend);
}
