package com.example.lobster.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Specie {

    @Id
    @GeneratedValue
    private Long id;
    private String type;
    @OneToMany(mappedBy = "specie")
    private Collection<Lobster> lobsters;

    public Specie() {}

    public Specie(String type){
        this.type = type;
    }

    public Long getId(){
        return id;}
    public String getType(){
        return type;}
    public Collection<Lobster> getLobsters(){
        return lobsters;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specie specie = (Specie) o;
        return Objects.equals(id, specie.id);
    }

    @Override
//    public int hashCode() {
//        return super.hashCode();
//    }
    public int hashCode() {return Objects.hash(id);}
}
