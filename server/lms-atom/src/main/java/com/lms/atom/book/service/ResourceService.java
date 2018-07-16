package com.lms.atom.book.service;

import com.lms.atom.exception.AtomException;
import com.lms.common.dto.atom.resource.ResourceCopyDTO;
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
                                 String edition,
                                 String publisher,
                                 Date fromEditionDate, Date toEditionDate,
                                 Long language,
                                 String isbn,
                                 String udc,
                                 ResourceTypeDTO resourceType,
                                 Long materialTypeCode,
                                 Date fromCreationDate, Date toCreationDate,
                                 Date fromModificationDate, Date toModificationDate,
                                 Long categoryCode,
                                 String issn,
                                 String place,
                                 int limit, int offset) throws Exception;

    ListResult<ResourceDTO> findSpecial(Long id,
                                 String name,
                                 String author,
                                 String subName,
                                 String edition,
                                 String publisher,
                                 Date fromEditionDate, Date toEditionDate,
                                 Long language,
                                 String isbn,
                                 String udc,
                                 ResourceTypeDTO resourceType,
                                 Long materialTypeCode,
                                 Date fromCreationDate, Date toCreationDate,
                                 Date fromModificationDate, Date toModificationDate,
                                 List<Long> categoryIds,
                                 String issn,
                                 String place,
                                 int limit, int offset) throws Exception;

    ResourceDTO update(ResourceDTO resource) throws AtomException;

    ResourceDTO save(ResourceDTO resource) throws AtomException;

    void delete(Long bookId) throws Exception;

    List<ComboObject> getResourceTypes();

    ResourceDTO getResourceById(Long id) throws AtomException;

    long resourcesCount();

    ResourceCopyDTO addResourceCopy(ResourceCopyDTO resourceCopy) throws AtomException;

    void removeResourceCopy(Long resourceCopyId) throws AtomException;

    ResourceCopyDTO getResourceCopyByIdentifier(String identifier);
}
