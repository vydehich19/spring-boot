package com.client.sample.controller;

import com.client.sample.model.Client;
import com.client.sample.repo.ClientRepo;
//import com.client.sample.validation.ClientValidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ClientController {

    @Autowired
    private ClientRepo clientRepo;

    // @Autowired
    // private ClientValidation clientValidation;

    @GetMapping("/getClient/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {

        Optional<Client> clientObj = clientRepo.findById(id);
        if (clientObj.isPresent()) {
            return new ResponseEntity<>(clientObj.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/getClient/{email}")
    public ResponseEntity<Client> getClientByEmail(@PathVariable String email) {

        Client clientObj = clientRepo.findByEmail(email);
        if (clientObj != null) {
            return new ResponseEntity<>(clientObj, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/addClient")
    public ResponseEntity<Client> addClientData(@RequestBody Client client) {
        try {
            Boolean isValid = true;// clientValidation.validateData(client);
            if (isValid) {
                Client clientObj = clientRepo.save(client);
                return new ResponseEntity<>(clientObj, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateClient/{clientId}")
    public ResponseEntity<Client> updateClientDataById(@PathVariable Long clientId, @RequestBody Client client) {
        try {

            Optional<Client> clientObj = clientRepo.findById(clientId);
            if (clientObj.isPresent()) {
                Client updatedClient = clientObj.get();
                updatedClient.setFirstName(client.getFirstName());
                updatedClient.setLastName(client.getLastName());
                updatedClient.setEmail(client.getEmail());
                updatedClient.setCity(client.getCity());
                updatedClient.setState(client.getState());
                updatedClient.setCountry(client.getCountry());

                clientRepo.save(updatedClient);
                return new ResponseEntity<>(updatedClient, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/updateClient/{email}")
    public ResponseEntity<Client> updateClientDataByEmail(@PathVariable String email, @RequestBody Client client) {
        try {

            Client clientObj = clientRepo.findByEmail(email);
            if (clientObj != null) {
                Client updatedClient = clientObj;
                updatedClient.setFirstName(client.getFirstName());
                updatedClient.setLastName(client.getLastName());
                updatedClient.setCity(client.getCity());
                updatedClient.setState(client.getState());
                updatedClient.setCountry(client.getCountry());

                clientRepo.save(updatedClient);
                return new ResponseEntity<>(updatedClient, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/deleteClient/{id}")
    public ResponseEntity<HttpStatus> deleteClientDatabyId(@PathVariable Long id) {

        try {

            clientRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/deleteClient/{email}")
    public ResponseEntity<HttpStatus> deleteClientDatabyEmail(@PathVariable String email) {

        try {

            clientRepo.deleteByEmail(email);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
