package com.example.demobiling.repository;

import com.example.demobiling.entites.AuthInfo;
import com.example.demobiling.entites.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AuthInfoRepository extends JpaRepository<AuthInfo, Long> {

    @Query("select c from Client c where c.authInfo.email =: email")
    Optional<Client> findByEmail(String email);

    @Query("select case when count (c)>0 then true else false end " +
            "from Client c where c.authInfo.email like : email")
    boolean existsByEmail(@Param(value = "email") String email);
}