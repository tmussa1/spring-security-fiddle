package com.mc.imageanswer;

import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Long>{
    AppUser findByUsername(String s);
}
