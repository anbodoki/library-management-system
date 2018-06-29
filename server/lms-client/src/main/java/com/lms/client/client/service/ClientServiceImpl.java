package com.lms.client.client.service;

import com.lms.client.client.storage.ClientHelper;
import com.lms.client.client.storage.ClientStorage;
import com.lms.client.client.storage.model.Client;
import com.lms.common.dto.client.ClientDTO;
import com.lms.common.dto.response.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientStorage storage;

    @Autowired
    public ClientServiceImpl(ClientStorage storage) {
        this.storage = storage;
    }

    @Override
    public ListResult<ClientDTO> find(String query, int limit, int offset) throws Exception {
        ListResult<Client> schools = storage.find(query, limit, offset);
        ListResult<ClientDTO> result = schools.copy(ClientDTO.class);
        result.setResultList(ClientHelper.fromEntities(schools.getResultList()));
        return result;
    }

    @Override
    public ListResult<ClientDTO> find(Long id, String firstName, String lastName, String email, String phone, Long schoolId, int limit, int offset) throws Exception {
        ListResult<Client> schools = storage.find(id, firstName, lastName, email, phone, schoolId, limit, offset);
        ListResult<ClientDTO> result = schools.copy(ClientDTO.class);
        result.setResultList(ClientHelper.fromEntities(schools.getResultList()));
        return result;
    }
}
