package com.lms.client.school.service;

import com.lms.client.exception.ClientException;
import com.lms.common.dto.client.ClientDTO;
import com.lms.common.dto.client.SchoolDTO;
import com.lms.common.dto.client.UniversityDTO;
import com.lms.common.dto.response.ComboObject;
import com.lms.common.dto.response.ListResult;

import java.util.List;

public interface SchoolService {

    ListResult<SchoolDTO> find(String query, int limit, int offset) throws Exception;

    ListResult<SchoolDTO> find(Long id, String name, UniversityDTO university, int limit, int offset) throws Exception;

    SchoolDTO update(SchoolDTO school) throws Exception;

    SchoolDTO save(SchoolDTO school) throws Exception;

    void delete(Long id) throws Exception;

    List<ComboObject> getUniversities();

    SchoolDTO getById(Long schoolId) throws ClientException;
}
