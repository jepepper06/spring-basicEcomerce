package com.jepepper.sellingApp.repository;

import com.jepepper.sellingApp.domain.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {
    Client findByUsername(String username);
    Page<Client> findAll(Pageable pageable);
}
