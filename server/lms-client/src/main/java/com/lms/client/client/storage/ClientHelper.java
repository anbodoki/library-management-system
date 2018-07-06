package com.lms.client.client.storage;

import com.lms.client.client.storage.model.Client;
import com.lms.client.school.storage.SchoolHelper;
import com.lms.common.dto.client.ClientDTO;

import java.util.ArrayList;
import java.util.List;

public class ClientHelper {

    public static ClientDTO fromEntity(Client client) {
        if (client == null) {
            return null;
        }
        ClientDTO result = new ClientDTO();
        result.setId(client.getId());
        result.setFirstName(client.getFirstName());
        result.setLastName(client.getLastName());
        result.setEmail(client.getEmail());
        result.setPhone(client.getPhone());
        result.setImgUrl(client.getImgUrl());
        result.setSchool(SchoolHelper.fromEntity(client.getSchool()));
        result.setActive(client.getActive());
        result.setImageUrl(client.getImageUrl());
        return result;
    }

    public static Client toEntity(ClientDTO client) {
        if (client == null) {
            return null;
        }
        Client result = new Client();
        result.setId(client.getId());
        result.setFirstName(client.getFirstName());
        result.setLastName(client.getLastName());
        result.setEmail(client.getEmail());
        result.setPhone(client.getPhone());
        result.setImgUrl(client.getImgUrl());
        result.setSchool(SchoolHelper.toEntity(client.getSchool()));
        result.setActive(client.getActive());
        result.setImageUrl(client.getImageUrl());
        return result;
    }

    public static List<ClientDTO> fromEntities(List<Client> clients) {
        if (clients == null) {
            return null;
        }
        List<ClientDTO> result = new ArrayList<>();
        for (Client client : clients) {
            result.add(fromEntity(client));
        }
        return result;
    }

    public static List<Client> toEntities(List<ClientDTO> clients) {
        if (clients == null) {
            return null;
        }
        List<Client> result = new ArrayList<>();
        for (ClientDTO client : clients) {
            result.add(toEntity(client));
        }
        return result;
    }
}