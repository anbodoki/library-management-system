package com.lms.client.school.storage;

import com.lms.client.school.storage.model.School;
import com.lms.client.school.storage.model.University;
import com.lms.common.dto.client.SchoolDTO;
import com.lms.common.dto.client.UniversityDTO;

import java.util.ArrayList;
import java.util.List;

public class SchoolHelper {

    public static School toEntity(SchoolDTO school) {
        if (school == null) {
            return null;
        }
        School result = new School();
        result.setId(school.getId());
        result.setName(school.getName());
        result.setDescription(school.getDescription());
        if (school.getUniversity() != null) {
            result.setUniversity(University.valueOf(school.getUniversity().name()));
        }
        return result;
    }

    public static SchoolDTO fromEntity(School school) {
        if (school == null) {
            return null;
        }
        SchoolDTO result = new SchoolDTO();
        result.setId(school.getId());
        result.setName(school.getName());
        result.setDescription(school.getDescription());
        if (school.getUniversity() != null) {
            result.setUniversity(UniversityDTO.valueOf(school.getUniversity().name()));
        }
        return result;
    }

    public static List<School> toEntities(List<SchoolDTO> schools) {
        if (schools == null) {
            return null;
        }
        List<School> result = new ArrayList<>();
        for (SchoolDTO school : schools) {
            result.add(toEntity(school));
        }
        return result;
    }

    public static List<SchoolDTO> fromEntities(List<School> schools) {
        if (schools == null) {
            return null;
        }
        List<SchoolDTO> result = new ArrayList<>();
        for (School school : schools) {
            result.add(fromEntity(school));
        }
        return result;
    }
}
