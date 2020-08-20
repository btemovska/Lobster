package com.example.lobster.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Lobster {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    private Specie specie;

    public Lobster() {}

    public Lobster(String name, String description, Specie specie){
        this.name = name;
        this.description = description;
        this.specie = specie;
    }

    public Long getId(){
        return id;}
    public String getName(){
        return name;}
    public String getDescription() {
        return description; }
    public Specie getSpecie() {
        return specie; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lobster lobster = (Lobster) o;
        return Objects.equals(id, lobster.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id, name, description, specie); }

}
