package com.client.sample.validation;

import com.client.sample.model.Client;

public class ClientValidation {

    public boolean validateData(Client client) {
        try {
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
