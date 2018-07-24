package com.lms.integration;

import com.lms.atom.book.service.ResourceService;
import com.lms.atom.category.service.CategoryService;
import com.lms.atom.exception.AtomException;
import com.lms.atom.language.service.LanguageService;
import com.lms.atom.material.service.MaterialTypeService;
import com.lms.common.dto.atom.category.CategoryDTO;
import com.lms.common.dto.atom.language.LanguageDTO;
import com.lms.common.dto.atom.materialtype.MaterialTypeDTO;
import com.lms.common.dto.atom.resource.ResourceCopyDTO;
import com.lms.common.dto.atom.resource.ResourceDTO;
import com.lms.common.dto.atom.resource.ResourceTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Transactional
public class DataIntegrationService {

    private final ResourceService resourceService;
    private final CategoryService categoryService;
    private final MaterialTypeService materialTypeService;
    private final LanguageService languageService;

    @Autowired
    public DataIntegrationService(ResourceService resourceService, CategoryService categoryService, MaterialTypeService materialTypeService, LanguageService languageService) {
        this.resourceService = resourceService;
        this.categoryService = categoryService;
        this.materialTypeService = materialTypeService;
        this.languageService = languageService;
    }

    @PostConstruct
    public void postConstruct() throws Exception {
        Map<DataLine, List<String>> read = DataParser.read();
        if (read == null) {
            return;
        }
        List<CategoryDTO> categories = categoryService.find(null, -1, -1).getResultList();
        List<MaterialTypeDTO> materialTypes = materialTypeService.find(null, -1, -1).getResultList();
        Map<String, LanguageDTO> languages = mapLanguages();
        for (Map.Entry<DataLine, List<String>> entry : read.entrySet()) {
            CategoryDTO category = categories.get(new Random().nextInt(categories.size()));
            MaterialTypeDTO materialType = materialTypes.get(new Random().nextInt(materialTypes.size()));
            ResourceDTO resource = new ResourceDTO();
            resource.setName(entry.getKey().getName());
            resource.setAuthor(entry.getKey().getAuthor());
            resource.setSubName(entry.getKey().getName());
            resource.setEdition("" + entry.getKey().getEdition());
            resource.setEditionYear(entry.getKey().getYear());
            resource.setLanguage(languages.get(LanguageUtils.detectLanguage(resource.getName())));
            resource.setResourceType(ResourceTypeDTO.PRINTED);
            resource.setMaterialType(materialType);
            resource.setCreationDate(new Date());
            resource.setCategory(category);
            resource.setQuantity(entry.getValue().size());
            resource = resourceService.justSave(resource);
            for (String identifier : entry.getValue()) {
                ResourceCopyDTO copy = new ResourceCopyDTO();
                copy.setResource(resource);
                copy.setIdentifier(identifier);
                copy.setName(identifier);
                copy.setAvailability(true);
                try {
                    resourceService.justAddResourceCopy(copy);
                } catch (AtomException ignored) {
                }
            }
        }
    }

    private Map<String, LanguageDTO> mapLanguages() throws Exception {
        List<LanguageDTO> result = languageService.find(null, -1, -1).getResultList();
        Map<String, LanguageDTO> map = new HashMap<>();
        for (LanguageDTO lang : result) {
            map.put(lang.getCode(), lang);
        }
        return map;
    }
}
