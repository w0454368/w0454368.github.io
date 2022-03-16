package com.example.miniproject.model;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "course")
    private String course;

    @Column(name = "class_section")
    private int section;

    public Student() {

    }

    public Student(String firstName, String lastName, String course, int section) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.course = course;
        this.section = section;
    }

    public long getId() { return this.id; }
    public String getFirstName() { return this.firstName; }
    public String getLastName() { return this.lastName; }
    public String getCourse() { return this.course; }
    public int getSection() { return this.section; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public void setSection(int section) {
        this.section = section;
    }

    @Override
    public String toString() {
        return "Student [id = " + id + ", first_name = " + firstName + ", last_name = " + lastName + ", course = " + course + ", class_section = " + section + "]";
    }
}
