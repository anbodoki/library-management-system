package com.lms.client.client.service;

import com.lms.client.client.storage.ClientHelper;
import com.lms.client.client.storage.ClientRepository;
import com.lms.client.client.storage.ClientStorage;
import com.lms.client.client.storage.model.Client;
import com.lms.client.exception.ClientException;
import com.lms.client.messages.Messages;
import com.lms.common.dto.client.ClientDTO;
import com.lms.common.dto.response.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientStorage storage;
    private final ClientRepository repository;

    @Autowired
    public ClientServiceImpl(ClientStorage storage, ClientRepository repository) {
        this.storage = storage;
        this.repository = repository;
    }

    @Override
    public ListResult<ClientDTO> find(String query, int limit, int offset) throws Exception {
        ListResult<Client> schools = storage.find(query, limit, offset);
        ListResult<ClientDTO> result = schools.copy(ClientDTO.class);
        result.setResultList(ClientHelper.fromEntities(schools.getResultList()));
        return result;
    }

    @Override
    public ListResult<ClientDTO> find(Long id, String firstName, String lastName, String email, String phone, Long schoolId, Boolean active, int limit, int offset) throws Exception {
        ListResult<Client> schools = storage.find(id, firstName, lastName, email, phone, schoolId, active, limit, offset);
        ListResult<ClientDTO> result = schools.copy(ClientDTO.class);
        result.setResultList(ClientHelper.fromEntities(schools.getResultList()));
        return result;
    }

    @Override
    public ClientDTO activate(ClientDTO client) throws Exception {
        Client curr = repository.getOne(client.getId());
        curr.setActive(true);
        Client saved = repository.save(curr);
        return ClientHelper.fromEntity(saved);
    }

    @Override
    public ClientDTO deactivate(ClientDTO client) throws Exception {
        Client curr = repository.getOne(client.getId());
        curr.setActive(false);
        Client saved = repository.save(curr);
        return ClientHelper.fromEntity(saved);
    }

    @Override
    public ClientDTO getById(Long id) throws ClientException {
        try {
            return ClientHelper.fromEntity(repository.getOne(id));
        } catch (EntityNotFoundException e) {
            throw new ClientException(Messages.get("clientWithThisIdNotExists"));
        }
    }

    @Override
    public ClientDTO update(ClientDTO client) {
        return ClientHelper.fromEntity(repository.save(ClientHelper.toEntity(client)));
    }

    @Override
    public ClientDTO getByEmail(String email) {
        return ClientHelper.fromEntity(repository.findByEmail(email));
    }

    @Override
    public ClientDTO save(ClientDTO client) {
        ClientDTO byEmail = getByEmail(client.getEmail());
        if (byEmail == null) {
            return ClientHelper.fromEntity(repository.save(ClientHelper.toEntity(client)));
        }
        return byEmail;
    }

    @Override
    public ClientDTO getAuthorizedClient() throws ClientException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client client = repository.findByEmail(authentication.getName());
        if (client == null) {
            throw new ClientException(Messages.get("clientNotExists"));
        }
        return ClientHelper.fromEntity(client);
    }
}
