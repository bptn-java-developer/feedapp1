package com.bptn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bptn.jpa.UserID;

public interface LoginRepository extends JpaRepository<UserID, String> {

}
