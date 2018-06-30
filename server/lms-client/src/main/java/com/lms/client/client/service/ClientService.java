package com.lms.client.client.service;

import com.lms.common.dto.client.ClientDTO;
import com.lms.common.dto.response.ListResult;

public interface ClientService {

    ListResult<ClientDTO> find(String query, int limit, int offset) throws Exception;

    ListResult<ClientDTO> find(Long id, String firstName, String lastName, String email, String phone, Long schoolId, Boolean active, int limit, int offset) throws Exception;
}
