package com.lms.client.school.service;

import com.lms.client.exception.ClientException;
import com.lms.client.messages.Messages;
import com.lms.client.school.storage.SchoolHelper;
import com.lms.client.school.storage.SchoolRepository;
import com.lms.client.school.storage.SchoolStorage;
import com.lms.client.school.storage.model.School;
import com.lms.client.school.storage.model.University;
import com.lms.common.dto.client.SchoolDTO;
import com.lms.common.dto.client.UniversityDTO;
import com.lms.common.dto.response.ComboObject;
import com.lms.common.dto.response.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository repository;
    private final SchoolStorage storage;

    @Autowired
    public SchoolServiceImpl(SchoolRepository repository, SchoolStorage storage) {
        this.repository = repository;
        this.storage = storage;
    }

    @Override
    public ListResult<SchoolDTO> find(String query, int limit, int offset) throws Exception {
        ListResult<School> schools = storage.find(query, limit, offset);
        ListResult<SchoolDTO> result = schools.copy(SchoolDTO.class);
        result.setResultList(SchoolHelper.fromEntities(schools.getResultList()));
        return result;
    }

    @Override
    public ListResult<SchoolDTO> find(Long id, String name, UniversityDTO university, int limit, int offset) throws Exception {
        ListResult<School> schools = storage.find(id, name, university != null ? University.valueOf(university.name()) : null, limit, offset);
        ListResult<SchoolDTO> result = schools.copy(SchoolDTO.class);
        result.setResultList(SchoolHelper.fromEntities(schools.getResultList()));
        return result;
    }

    @Override
    public SchoolDTO update(SchoolDTO school) throws Exception {
        return SchoolHelper.fromEntity(repository.save(SchoolHelper.toEntity(school)));
    }

    @Override
    public SchoolDTO save(SchoolDTO school) throws Exception {
        return SchoolHelper.fromEntity(repository.save(SchoolHelper.toEntity(school)));
    }

    @Override
    public void delete(Long id) throws Exception {
        repository.delete(id);
    }

    @Override
    public List<ComboObject> getUniversities() {
        List<ComboObject> result = new ArrayList<>();
        for (University userType : University.values()) {
            result.add(new ComboObject(userType.name(), Messages.get(University.class.getSimpleName() + "_" + userType.name())));
        }
        return result;
    }
}
