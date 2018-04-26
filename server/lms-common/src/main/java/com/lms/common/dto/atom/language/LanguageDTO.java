package com.lms.common.dto.atom.language;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class LanguageDTO implements Serializable {

    private Long id;
    @NotNull
    private String code;
    @NotNull
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
