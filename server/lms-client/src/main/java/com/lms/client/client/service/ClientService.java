package com.lms.client.client.service;

import com.lms.client.exception.ClientException;
import com.lms.common.dto.client.CardDTO;
import com.lms.common.dto.client.ClientDTO;
import com.lms.common.dto.response.ListResult;

public interface ClientService {

    ListResult<ClientDTO> find(String query, int limit, int offset) throws Exception;

    ListResult<ClientDTO> find(Long id, String firstName, String lastName, String email, String phone, Long schoolId, Boolean active, int limit, int offset) throws Exception;

    ClientDTO activate(ClientDTO client) throws Exception;

    ClientDTO deactivate(ClientDTO client) throws Exception;

    ClientDTO getById(Long id) throws ClientException;

    ClientDTO update(ClientDTO client);

    ClientDTO getByEmail(String email);

    ClientDTO save(ClientDTO client);

    ClientDTO getAuthorizedClient() throws ClientException;

    ClientDTO getClientForCard(String identifier) throws Exception;

    CardDTO activateCard(Long cardId) throws Exception;

    CardDTO deactivateCard(Long cardId) throws Exception;

    void deleteCard(Long cardId) throws Exception;

    long getClientCount();
}
