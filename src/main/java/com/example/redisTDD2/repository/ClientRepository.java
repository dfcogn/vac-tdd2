package com.example.redisTDD2.repository;

import com.example.redisTDD2.model.Client;

import java.util.Map;

public interface ClientRepository {
    void add(Client client);
    Client findById(String id);
    Map<String, Client> findAll();
    void update(Client client);
    void delete(String id);
    boolean isContained(String id);
}
