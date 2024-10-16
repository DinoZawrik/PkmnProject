package ru.mirea.novikovmp.pkmn;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String surname;
    private String name;
    private String familyName;
    private String group;

    public Student() {}

    public Student(String surname, String name, String familyName, String group) {
        this.surname = surname;
        this.name = name;
        this.familyName = familyName;
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", group='" + group + '\'' +
                '}';
    }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getFamilyName() { return familyName; }
    public void setFamilyName(String familyName) { this.familyName = familyName; }

    public String getGroup() { return group; }
    public void setGroup(String group) { this.group = group; }
}