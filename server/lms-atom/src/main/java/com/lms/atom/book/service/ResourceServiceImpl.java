package com.lms.atom.book.service;

import com.lms.atom.book.storage.*;
import com.lms.atom.book.storage.model.Resource;
import com.lms.atom.book.storage.model.ResourceCopy;
import com.lms.atom.book.storage.model.ResourceType;
import com.lms.atom.borrow.BorrowConcurrencyHelper;
import com.lms.atom.exception.AtomException;
import com.lms.atom.messages.Messages;
import com.lms.common.dto.atom.resource.ResourceCopyDTO;
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
    private final ResourceCopyRepository copyRepository;
    private final ResourceCopyStorage copyStorage;

    @Autowired
    public ResourceServiceImpl(ResourceStorage storage, ResourceRepository repository, ResourceCopyRepository copyRepository, ResourceCopyStorage copyStorage) {
        this.storage = storage;
        this.repository = repository;
        this.copyRepository = copyRepository;
        this.copyStorage = copyStorage;
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
                                        String edition,
                                        String publisher,
                                        String editionYear,
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
                                        int limit, int offset) throws Exception {
        ListResult<Resource> materialTypes = storage.find(id,
                name,
                author,
                subName,
                edition,
                publisher,
                editionYear,
                language,
                isbn,
                udc,
                (resourceType == null ? null : ResourceType.valueOf(resourceType.name())),
                materialTypeCode,
                fromCreationDate, toCreationDate,
                fromModificationDate, toModificationDate,
                categoryCode,
                issn,
                place,
                limit, offset);
        ListResult<ResourceDTO> result = materialTypes.copy(ResourceDTO.class);
        result.setResultList(ResourceHelper.fromEntities(materialTypes.getResultList()));
        return result;
    }

    @Override
    public ListResult<ResourceDTO> findSpecial(Long id,
                                               String name,
                                               String author,
                                               String subName,
                                               String edition,
                                               String publisher,
                                               String editionYear,
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
                                               int limit, int offset) throws Exception {
        ListResult<Resource> materialTypes = storage.findSpecial(id,
                name,
                author,
                subName,
                edition,
                publisher,
                editionYear,
                language,
                isbn,
                udc,
                (resourceType == null ? null : ResourceType.valueOf(resourceType.name())),
                materialTypeCode,
                fromCreationDate, toCreationDate,
                fromModificationDate, toModificationDate,
                categoryIds,
                issn,
                place,
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
        Resource old = repository.getByIsbn(resource.getIsbn());
        if (old != null) {
            throw new AtomException(Messages.get("resourceWithThisISBNAlreadyExists"));
        }
        old = repository.getByUdc(resource.getUdc());
        if (old != null) {
            throw new AtomException(Messages.get("resourceWithThisUDCAlreadyExists"));
        }
        resource.setCreationDate(new Date());
        Resource saved = repository.save(ResourceHelper.toEntity(resource));
        return ResourceHelper.fromEntity(saved);
    }

    @Override
    public ResourceDTO justSave(ResourceDTO resource) throws Exception{
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

    @Override
    public ResourceDTO getResourceById(Long id) throws AtomException {
        try {
            return ResourceHelper.fromEntity(repository.getOne(id));
        } catch (EntityNotFoundException e) {
            throw new AtomException(Messages.get("resourceWithThisIdNotExists"));
        }
    }

    @Override
    public long resourcesCount() {
        return repository.count();
    }

    @Override
    public ResourceCopyDTO addResourceCopy(ResourceCopyDTO resourceCopy) throws AtomException {
        ResourceCopy byIdentifier = copyRepository.findByIdentifier(resourceCopy.getIdentifier());
        if (byIdentifier != null) {
            throw new AtomException(Messages.get("resourceCopyWithThisIdentifierAlreadyExistsInSystem"));
        }
        ResourceCopyDTO resourceCopyDTO = ResourceCopyHelper.fromEntity(copyRepository.save(ResourceCopyHelper.toEntity(resourceCopy)));
        ResourceDTO resourceById = getResourceById(resourceCopyDTO.getResource().getId());
        try {
            BorrowConcurrencyHelper.lock(resourceById.getId());
            resourceById.setQuantity(resourceById.getQuantity() + 1);
            repository.save(ResourceHelper.toEntity(resourceById));
        } finally {
            BorrowConcurrencyHelper.unlock(resourceById.getId());
        }
        return resourceCopyDTO;
    }

    @Override
    public ResourceCopyDTO justAddResourceCopy(ResourceCopyDTO resourceCopy) throws AtomException {
        ResourceCopy byIdentifier = copyRepository.findByIdentifier(resourceCopy.getIdentifier());
        if (byIdentifier != null) {
            throw new AtomException(Messages.get("resourceCopyWithThisIdentifierAlreadyExistsInSystem"));
        }
        return ResourceCopyHelper.fromEntity(copyRepository.save(ResourceCopyHelper.toEntity(resourceCopy)));
    }

    @Override
    public ResourceCopyDTO update(ResourceCopyDTO resourceCopy) throws AtomException {
        return ResourceCopyHelper.fromEntity(copyRepository.save(ResourceCopyHelper.toEntity(resourceCopy)));
    }

    @Override
    public void removeResourceCopy(Long resourceCopyId) throws AtomException {
        ResourceCopy one = copyRepository.getOne(resourceCopyId);
        ResourceDTO resourceById = getResourceById(one.getResource().getId());
        try {
            BorrowConcurrencyHelper.lock(resourceById.getId());
            resourceById.setQuantity(resourceById.getQuantity() - 1);
            repository.save(ResourceHelper.toEntity(resourceById));
        } finally {
            BorrowConcurrencyHelper.unlock(resourceById.getId());
        }
        copyRepository.delete(resourceCopyId);
    }

    @Override
    public ResourceCopyDTO getResourceCopyByIdentifier(String identifier) {
        return ResourceCopyHelper.fromEntity(copyRepository.findByIdentifier(identifier));
    }

    @Override
    public ListResult<ResourceCopyDTO> getResourcesCopies(long resourceId, String query, int limit, int offset) {
        ListResult<ResourceCopy> materialTypes = copyStorage.getResourcesCopies(resourceId, query, limit, offset);
        ListResult<ResourceCopyDTO> result = materialTypes.copy(ResourceCopyDTO.class);
        result.setResultList(ResourceCopyHelper.fromEntities(materialTypes.getResultList()));
        return result;
    }

    @Override
    public long getCopiesCount() {
        return copyRepository.count();
    }
}
