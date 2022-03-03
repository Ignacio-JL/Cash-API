package com.cash.cashapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cash.cashapi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
