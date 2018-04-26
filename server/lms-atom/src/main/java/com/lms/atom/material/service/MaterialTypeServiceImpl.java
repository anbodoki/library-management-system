package com.lms.atom.material.service;

import com.lms.atom.exception.AtomException;
import com.lms.atom.material.storage.MaterialTypeHelper;
import com.lms.atom.material.storage.MaterialTypeRepository;
import com.lms.atom.material.storage.MaterialTypeStorage;
import com.lms.atom.material.storage.model.MaterialType;
import com.lms.atom.messages.Messages;
import com.lms.common.dto.atom.materialtype.MaterialTypeDTO;
import com.lms.common.dto.response.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class MaterialTypeServiceImpl implements MaterialTypeService {

    private final MaterialTypeStorage storage;
    private final MaterialTypeRepository repository;

    @Autowired
    public MaterialTypeServiceImpl(MaterialTypeStorage storage, MaterialTypeRepository repository) {
        this.storage = storage;
        this.repository = repository;
    }

    @Override
    public ListResult<MaterialTypeDTO> find(String query, int limit, int offset) throws Exception {
        ListResult<MaterialType> materialTypes = storage.find(query, limit, offset);
        ListResult<MaterialTypeDTO> result = materialTypes.copy(MaterialTypeDTO.class);
        result.setResultList(MaterialTypeHelper.fromEntities(materialTypes.getResultList()));
        return result;
    }

    @Override
    public ListResult<MaterialTypeDTO> find(Long id, String code, String name, String description, int limit, int offset) throws Exception {
        ListResult<MaterialType> materialTypes = storage.find(id, code, name, description, limit, offset);
        ListResult<MaterialTypeDTO> result = materialTypes.copy(MaterialTypeDTO.class);
        result.setResultList(MaterialTypeHelper.fromEntities(materialTypes.getResultList()));
        return result;
    }

    @Override
    public MaterialTypeDTO update(MaterialTypeDTO materialType) throws Exception {
        MaterialType saved;
        try {
            saved = repository.getOne(materialType.getId());
            if (saved == null) {
                throw new AtomException(Messages.get("materialTypeNotExists"));
            }
        } catch (EntityNotFoundException e) {
            throw new AtomException(Messages.get("materialTypeNotExists"));
        }
        if (!materialType.getCode().equals(saved.getCode())) {
            try {
                MaterialType byCode = repository.getByCode(materialType.getCode());
                if (byCode == null) {
                    throw new AtomException(Messages.get("materialTypeWithThisCodeAlreadyExists"));
                }
            } catch (EntityNotFoundException e) {
                throw new AtomException(Messages.get("materialTypeWithThisCodeAlreadyExists"));
            }
        }
        MaterialType save = repository.save(MaterialTypeHelper.toEntity(materialType));
        return MaterialTypeHelper.fromEntity(save);
    }

    @Override
    public MaterialTypeDTO save(MaterialTypeDTO materialType) throws AtomException {
        try {
            MaterialType byCode = repository.getByCode(materialType.getCode());
            if (byCode == null) {
                throw new AtomException(Messages.get("materialTypeWithThisCodeAlreadyExists"));
            }
        } catch (EntityNotFoundException e) {
            throw new AtomException(Messages.get("materialTypeWithThisCodeAlreadyExists"));
        }
        MaterialType save = repository.save(MaterialTypeHelper.toEntity(materialType));
        return MaterialTypeHelper.fromEntity(save);
    }

    @Override
    public void delete(Long id) throws Exception {
        repository.delete(id);
    }
}
