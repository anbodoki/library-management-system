package com.lms.atom.language.storage;

import com.lms.atom.language.storage.model.Language;
import com.lms.common.dto.atom.language.LanguageDTO;

import java.util.ArrayList;
import java.util.List;

public class LanguageHelper {

    public static Language toEntity(LanguageDTO language) {
        if (language == null) {
            return null;
        }
        Language result = new Language();
        result.setId(language.getId());
        result.setCode(language.getCode());
        result.setName(language.getName());
        return result;
    }

    public static LanguageDTO fromEntity(Language language) {
        if (language == null) {
            return null;
        }
        LanguageDTO result = new LanguageDTO();
        result.setId(language.getId());
        result.setCode(language.getCode());
        result.setName(language.getName());
        return result;
    }

    public static List<Language> toEntities(List<LanguageDTO> languages) {
        if (languages == null) {
            return null;
        }
        List<Language> result = new ArrayList<>();
        for (LanguageDTO language : languages) {
            result.add(toEntity(language));
        }
        return result;
    }

    public static List<LanguageDTO> fromEntities(List<Language> languages) {
        if (languages == null) {
            return null;
        }
        List<LanguageDTO> result = new ArrayList<>();
        for (Language language : languages) {
            result.add(fromEntity(language));
        }
        return result;
    }
}
