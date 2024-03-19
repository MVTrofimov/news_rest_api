package com.example.news_service_rest_api.repository;

import com.example.news_service_rest_api.model.Client;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DatabaseClientRepository extends JpaRepository<Client, Long> {

    @EntityGraph(attributePaths = {"roles"})
    Optional<Client> findByName(String name);

    Boolean existsByName(String name);

}
