package com.lms.atom.language.storage;

import com.lms.atom.language.storage.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    Language getByCode(String code);
}
