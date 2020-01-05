package com.example.redisTDD2.repository;

import com.example.redisTDD2.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class ClientRepositoryImpl implements ClientRepository {
    private static final String INDEX_CLIENT = "CLIENT";
//    private RedisTemplate<String, Client> redisTemplate;
    private HashOperations hashOperation;

    @Autowired
    public ClientRepositoryImpl(RedisTemplate<String, Client> redisTemplate) {
//        this.redisTemplate = redisTemplate;
        this.hashOperation = redisTemplate.opsForHash();
    }

    public ClientRepositoryImpl() {
    }

    @Override
    public void add(Client client) {
        hashOperation.put(INDEX_CLIENT, client.getId(), client);
    }

    @Override
    public Client findById(String id) {
        return (Client) hashOperation.get(INDEX_CLIENT, id);
    }

    @Override
    public Map<String, Client> findAll() {
        return hashOperation.entries(INDEX_CLIENT);
    }

    @Override
    public void update(Client client) {
        add(client);
    }

    @Override
    public void delete(String id) {
        hashOperation.delete(INDEX_CLIENT, id);
    }

    @Override
    public boolean isContained(String id){
        return hashOperation.hasKey(INDEX_CLIENT, id);
    }
}
