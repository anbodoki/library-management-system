package com.lms.atom.language.service;

import com.lms.atom.exception.AtomException;
import com.lms.common.dto.atom.language.LanguageDTO;
import com.lms.common.dto.response.ListResult;

public interface LanguageService {

    ListResult<LanguageDTO> find(String query, int limit, int offset) throws Exception;

    ListResult<LanguageDTO> find(Long id, String code, String name, int limit, int offset) throws Exception;

    LanguageDTO update(LanguageDTO language) throws AtomException;

    LanguageDTO save(LanguageDTO language) throws AtomException;

    void delete(Long id) throws Exception;
}
