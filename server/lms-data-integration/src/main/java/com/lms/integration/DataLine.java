package com.lms.integration;

import java.util.Objects;

public class DataLine {

    private String name;
    private String author;
    private int edition;
    private String year;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "DataLine{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", edition=" + edition +
                ", year='" + year + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DataLine)) return false;
        DataLine line = (DataLine) o;
        return getEdition() == line.getEdition() &&
                Objects.equals(getName(), line.getName()) &&
                Objects.equals(getAuthor(), line.getAuthor()) &&
                Objects.equals(getYear(), line.getYear());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAuthor(), getEdition(), getYear());
    }
}
