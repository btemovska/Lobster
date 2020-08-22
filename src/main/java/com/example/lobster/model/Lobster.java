package com.example.lobster.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Lobster {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    private Specie specie;
    @ManyToMany
    private Set<HashTag> hashTags;

    public Lobster() {}

    public Lobster(String name, String description, Specie specie){
        this.name = name;
        this.description = description;
        this.specie = specie;
        this.hashTags = new HashSet<>();
    }

    public Long getId(){
        return id;}
    public String getName(){
        return name;}
    public String getDescription() {
        return description; }
    public Specie getSpecie() {
        return specie; }
    public Collection<HashTag> getHashTags(){ return hashTags;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lobster lobster = (Lobster) o;
        return Objects.equals(id, lobster.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
    public void addHashTag(HashTag hashTagToAdd) { hashTags.add(hashTagToAdd);}
    public void deleteHashTag(HashTag hashTagToRemove) { hashTags.remove(hashTagToRemove);}

}
