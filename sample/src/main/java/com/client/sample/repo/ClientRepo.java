package com.client.sample.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.client.sample.model.Client;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {

    Client findByEmail(String email);
}
