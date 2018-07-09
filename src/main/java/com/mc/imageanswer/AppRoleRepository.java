package com.mc.imageanswer;

import org.springframework.data.repository.CrudRepository;

public interface AppRoleRepository extends CrudRepository<AppRole, Long>{
    AppRole findByRole(String role);
}
