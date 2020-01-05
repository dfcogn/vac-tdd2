package com.example.redisTDD2.controller;

import com.example.redisTDD2.model.Client;
import com.example.redisTDD2.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class MainController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/client/all")
    public Map<String, Client> getAll(){
        return clientRepository.findAll();
    }

    @GetMapping("/client/add/{id}/{name}/{lastName}")
    public Client add(@PathVariable String id, @PathVariable String name, @PathVariable String lastName){
        clientRepository.add(new Client(id, name, lastName));
        return clientRepository.findById(id);
    }

    @GetMapping("/client/update/{id}/{name}/{lastName}")
    public Client update(@PathVariable String id, @PathVariable String name, @PathVariable String lastName){
        clientRepository.update(new Client(id, name, lastName));
        return clientRepository.findById(id);
    }

    @GetMapping("/client/delete/{id}")
    public String update(@PathVariable String id){
        clientRepository.delete(id);
        return "client deleted";
    }
}
