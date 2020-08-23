package com.example.lobster.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
public class HashTag {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "hashTags")
    @JsonIgnore
    private Collection<Lobster> lobsters;

    public HashTag() {}

    public HashTag(String name) {
        this.name = name;
        lobsters = new ArrayList<>();
    }

    public Long getId() { return id; }
    public String getName() { return name;}
    public Collection<Lobster> getLobsters() { return lobsters;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashTag hashTag = (HashTag) o;
        return Objects.equals(id, hashTag.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

}
