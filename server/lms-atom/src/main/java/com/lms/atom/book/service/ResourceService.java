package com.lms.atom.book.service;

import com.lms.atom.exception.AtomException;
import com.lms.common.dto.atom.resource.ResourceDTO;
import com.lms.common.dto.atom.resource.ResourceTypeDTO;
import com.lms.common.dto.response.ComboObject;
import com.lms.common.dto.response.ListResult;

import java.util.Date;
import java.util.List;

public interface ResourceService {

    ListResult<ResourceDTO> find(String query, int limit, int offset) throws Exception;

    ListResult<ResourceDTO> find(Long id,
                                 String name,
                                 String author,
                                 String subName,
                                 Integer edition,
                                 String publisher,
                                 Date fromEditionDate, Date toEditionDate,
                                 String language,
                                 String isbn,
                                 String udc,
                                 String identifier,
                                 ResourceTypeDTO resourceType,
                                 String materialTypeCode,
                                 Date fromCreationDate, Date toCreationDate,
                                 Date fromModificationDate, Date toModificationDate,
                                 String categoryCode,
                                 int limit, int offset) throws Exception;

    ResourceDTO update(ResourceDTO resource) throws AtomException;

    ResourceDTO save(ResourceDTO resource) throws AtomException;

    void delete(Long bookId) throws Exception;

    List<ComboObject> getResourceTypes();

    ResourceDTO getResourceById(Long id) throws AtomException;
}
