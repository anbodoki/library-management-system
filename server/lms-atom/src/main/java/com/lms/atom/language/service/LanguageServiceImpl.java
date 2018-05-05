package com.lms.atom.language.service;

import com.lms.atom.exception.AtomException;
import com.lms.atom.language.storage.LanguageHelper;
import com.lms.atom.language.storage.LanguageRepository;
import com.lms.atom.language.storage.LanguageStorage;
import com.lms.atom.language.storage.model.Language;
import com.lms.atom.messages.Messages;
import com.lms.common.dto.atom.language.LanguageDTO;
import com.lms.common.dto.response.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class LanguageServiceImpl implements LanguageService {

    private final LanguageStorage storage;
    private final LanguageRepository repository;

    @Autowired
    public LanguageServiceImpl(LanguageStorage storage, LanguageRepository repository) {
        this.storage = storage;
        this.repository = repository;
    }

    @Override
    public ListResult<LanguageDTO> find(String query, int limit, int offset) throws Exception {
        ListResult<Language> materialTypes = storage.find(query, limit, offset);
        ListResult<LanguageDTO> result = materialTypes.copy(LanguageDTO.class);
        result.setResultList(LanguageHelper.fromEntities(materialTypes.getResultList()));
        return result;
    }

    @Override
    public ListResult<LanguageDTO> find(Long id, String code, String name, int limit, int offset) throws Exception {
        ListResult<Language> materialTypes = storage.find(id, name, code, limit, offset);
        ListResult<LanguageDTO> result = materialTypes.copy(LanguageDTO.class);
        result.setResultList(LanguageHelper.fromEntities(materialTypes.getResultList()));
        return result;
    }

    @Override
    public LanguageDTO update(LanguageDTO language) throws AtomException {
        try {
            Language old = repository.getOne(language.getId());
            if (!old.getCode().equals(language.getCode())) {
                Language byCode = repository.getByCode(language.getCode());
                if (byCode != null) {
                    throw new AtomException(Messages.get("languageWithThisCodeAlreadyExists"));
                }
            }
        } catch (EntityNotFoundException e) {
            throw new AtomException(Messages.get("languageWithThisIdNotExists"));
        }
        Language saved = repository.save(LanguageHelper.toEntity(language));
        return LanguageHelper.fromEntity(saved);
    }

    @Override
    public LanguageDTO save(LanguageDTO language) throws AtomException {
        Language byCode = repository.getByCode(language.getCode());
        if (byCode != null) {
            throw new AtomException(Messages.get("languageWithThisCodeAlreadyExists"));
        }
        Language saved = repository.save(LanguageHelper.toEntity(language));
        return LanguageHelper.fromEntity(saved);
    }

    @Override
    public void delete(Long id) throws Exception {
        repository.delete(id);
    }
}
