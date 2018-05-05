package com.lms.atom.category.service;

import com.lms.atom.category.storage.CategoryHelper;
import com.lms.atom.category.storage.CategoryRepository;
import com.lms.atom.category.storage.CategoryStorage;
import com.lms.atom.category.storage.model.Category;
import com.lms.atom.exception.AtomException;
import com.lms.atom.messages.Messages;
import com.lms.common.dto.atom.category.CategoryDTO;
import com.lms.common.dto.response.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryStorage storage;
    private final CategoryRepository repository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository, CategoryStorage storage) {
        this.repository = repository;
        this.storage = storage;
    }

    @Override
    public ListResult<CategoryDTO> find(String query, int limit, int offset) throws Exception {
        ListResult<Category> materialTypes = storage.find(query, limit, offset);
        ListResult<CategoryDTO> result = materialTypes.copy(CategoryDTO.class);
        result.setResultList(CategoryHelper.fromEntities(materialTypes.getResultList()));
        return result;
    }

    @Override
    public ListResult<CategoryDTO> find(Long id, String code, String name, String description, int limit, int offset) throws Exception {
        ListResult<Category> materialTypes = storage.find(id, code, name, description, limit, offset);
        ListResult<CategoryDTO> result = materialTypes.copy(CategoryDTO.class);
        result.setResultList(CategoryHelper.fromEntities(materialTypes.getResultList()));
        return result;
    }

    @Override
    public CategoryDTO update(CategoryDTO category) throws Exception {
        Category saved;
        try {
            saved = repository.getOne(category.getId());
            if (saved == null) {
                throw new AtomException(Messages.get("categoryNotExists"));
            }
        } catch (EntityNotFoundException e) {
            throw new AtomException(Messages.get("categoryNotExists"));
        }
        if (!category.getCode().equals(saved.getCode())) {
            try {
                Category byCode = repository.getByCode(category.getCode());
                if (byCode != null) {
                    throw new AtomException(Messages.get("categoryWithThisCodeAlreadyExists"));
                }
            } catch (EntityNotFoundException e) {
                throw new AtomException(Messages.get("categoryWithThisCodeAlreadyExists"));
            }
        }
        Category save = repository.save(CategoryHelper.toEntity(category));
        return CategoryHelper.fromEntity(save);
    }

    @Override
    public CategoryDTO save(CategoryDTO category) throws AtomException {
        try {
            Category byCode = repository.getByCode(category.getCode());
            if (byCode != null) {
                throw new AtomException(Messages.get("categoryWithThisCodeAlreadyExists"));
            }
        } catch (EntityNotFoundException e) {
            throw new AtomException(Messages.get("categoryWithThisCodeAlreadyExists"));
        }
        Category save = repository.save(CategoryHelper.toEntity(category));
        return CategoryHelper.fromEntity(save);
    }

    @Override
    public void delete(Long id) throws Exception {
        repository.delete(id);
    }
}
