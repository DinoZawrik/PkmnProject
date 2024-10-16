package ru.mirea.novikovmp.pkmn;

import java.io.Serializable;

public class Student implements Serializable {
    public static final long serialVersionUID = 1L;

    private String surname;
    private String name;
    private String familyName;
    private String group;

    public Student(String surname, String name, String familyName, String group) {
        this.surname = surname;
        this.name = name;
        this.familyName = familyName;
        this.group = group;
    }

    // Геттеры
    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getGroup() {
        return group;
    }

    // Сеттеры
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student{\n" +
                "  surname='" + surname + "'\n" +
                "  name='" + name + "'\n" +
                "  familyName='" + familyName + "'\n" +
                "  group='" + group + "'\n" +
                "}\n";
    }
}