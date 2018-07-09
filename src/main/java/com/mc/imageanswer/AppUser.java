package com.mc.imageanswer;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @ManyToMany
    private Set<AppRole> roles;

    @OneToMany(mappedBy = "myFriend")
    private Set<Friend> myFriends;

    public AppUser(){
        this.roles = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AppRole> roles) {
        this.roles = roles;
    }

    public Set<Friend> getMyFriends() {
        return myFriends;
    }

    public void setMyFriends(Set<Friend> myFriends) {
        this.myFriends = myFriends;
    }

    public void addRole(AppRole role){
        this.roles.add(role);
    }

}
