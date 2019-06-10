package com.byusluer.recipeproject.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@Entity
public class UnitOfMeasure {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String unitOfmeasure;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnitOfmeasure() {
        return unitOfmeasure;
    }

    public void setUnitOfmeasure(String unitOfmeasure) {
        this.unitOfmeasure = unitOfmeasure;
    }
}
