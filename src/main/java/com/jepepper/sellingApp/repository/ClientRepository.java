package com.jepepper.sellingApp.repository;

import com.jepepper.sellingApp.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
    Client findByUsername(String username);
}
