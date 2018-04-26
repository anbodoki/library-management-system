package com.lms.atom.book.service;

import com.lms.atom.book.storage.ResourceHelper;
import com.lms.atom.book.storage.ResourceRepository;
import com.lms.atom.book.storage.ResourceStorage;
import com.lms.atom.book.storage.model.Resource;
import com.lms.atom.book.storage.model.ResourceType;
import com.lms.atom.exception.AtomException;
import com.lms.atom.messages.Messages;
import com.lms.common.dto.atom.resource.ResourceDTO;
import com.lms.common.dto.atom.resource.ResourceTypeDTO;
import com.lms.common.dto.response.ComboObject;
import com.lms.common.dto.response.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {

    private final ResourceStorage storage;
    private final ResourceRepository repository;

    @Autowired
    public ResourceServiceImpl(ResourceStorage storage, ResourceRepository repository) {
        this.storage = storage;
        this.repository = repository;
    }

    @Override
    public ListResult<ResourceDTO> find(String query, int limit, int offset) throws Exception {
        ListResult<Resource> materialTypes = storage.find(query, limit, offset);
        ListResult<ResourceDTO> result = materialTypes.copy(ResourceDTO.class);
        result.setResultList(ResourceHelper.fromEntities(materialTypes.getResultList()));
        return result;
    }

    @Override
    public ListResult<ResourceDTO> find(Long id,
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
                                        int limit, int offset) throws Exception {
        ListResult<Resource> materialTypes = storage.find(id,
                name,
                author,
                subName,
                edition,
                publisher,
                fromEditionDate, toEditionDate,
                language,
                isbn,
                udc,
                identifier,
                (resourceType == null ?  null : ResourceType.valueOf(resourceType.name())),
                materialTypeCode,
                fromCreationDate, toCreationDate,
                fromModificationDate, toModificationDate,
                limit, offset);
        ListResult<ResourceDTO> result = materialTypes.copy(ResourceDTO.class);
        result.setResultList(ResourceHelper.fromEntities(materialTypes.getResultList()));
        return result;
    }

    @Override
    public ResourceDTO update(ResourceDTO resource) throws AtomException {
        Resource old;
        try {
            old = repository.getOne(resource.getId());
        } catch (EntityNotFoundException e) {
            throw new AtomException(Messages.get("resourceWithThisIDAlreadyExists"));
        }
        if (!old.getIdentifier().equals(resource.getIdentifier())) {
            if (repository.getByIdentifier(resource.getIdentifier()) != null) {
                throw new AtomException(Messages.get("resourceWithThisIdentifierAlreadyExists"));
            }
        }
        if (!old.getIsbn().equals(resource.getIsbn())) {
            if (repository.getByIsbn(resource.getIsbn()) != null) {
                throw new AtomException(Messages.get("resourceWithThisISBNAlreadyExists"));
            }
        }
        if (!old.getUdc().equals(resource.getUdc())) {
            if (repository.getByUdc(resource.getUdc()) != null) {
                throw new AtomException(Messages.get("resourceWithThisUDCAlreadyExists"));
            }
        }
        resource.setModificationDate(new Date());
        Resource saved = repository.save(ResourceHelper.toEntity(resource));
        return ResourceHelper.fromEntity(saved);
    }

    @Override
    public ResourceDTO save(ResourceDTO resource) throws AtomException {
        Resource old = repository.getByIdentifier(resource.getIdentifier());
        if (old == null) {
            throw new AtomException(Messages.get("resourceWithThisIdentifierAlreadyExists"));
        }
        old = repository.getByIsbn(resource.getIsbn());
        if (old == null) {
            throw new AtomException(Messages.get("resourceWithThisISBNAlreadyExists"));
        }
        old = repository.getByUdc(resource.getUdc());
        if (old == null) {
            throw new AtomException(Messages.get("resourceWithThisUDCAlreadyExists"));
        }
        resource.setCreationDate(new Date());
        Resource saved = repository.save(ResourceHelper.toEntity(resource));
        return ResourceHelper.fromEntity(saved);
    }

    @Override
    public void delete(Long bookId) throws Exception {
        try {
            repository.getOne(bookId);
        } catch (EntityNotFoundException e) {
            throw new AtomException(Messages.get("resourceWithThisIDAlreadyExists"));
        }
        repository.delete(bookId);
        //TODO delete files uploaded for this book
    }

    @Override
    public List<ComboObject> getResourceTypes() {
        List<ComboObject> result = new ArrayList<>();
        for (ResourceType userType : ResourceType.values()) {
            result.add(new ComboObject(userType.name(), Messages.get(ResourceType.class.getSimpleName() + "_" + userType.name())));
        }
        return result;
    }
}
