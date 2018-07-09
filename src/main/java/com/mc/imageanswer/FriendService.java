package com.mc.imageanswer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class FriendService {

    private AppUser thisUser;

    @Autowired
    AppUserRepository users;

    @Autowired
    FriendRepository friends;

    public Iterable<Friend> getMyFriends(Authentication myDetails){
        return friends.findAllByMyFriend(users.findByUsername(myDetails.getName()));
    }

    public Iterable<Friend> rankMyFriends(Authentication myDetails){
        return friends.findAllByMyFriendOrderByMyRankDesc(users.findByUsername(myDetails.getName()));
    }

    public Friend save(Friend aFriend, Authentication authentication){
        thisUser = users.findByUsername(authentication.getName());
        aFriend.setMyFriend(thisUser);
        return friends.save(aFriend);
    }


}
