package com.triyan.test.dish;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "dishes")
public class Dish {
    @Id
    @SequenceGenerator(
        name = "dish_sequence",
        sequenceName = "dish_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "dish_sequence"
    )
    private Long id;
    private String assignee;
    private String category;
    private String dishName;
    
    public Dish() {
    }

    public Dish(Long id, String assignee, String category, String dishName) {
        this.id = id;
        this.assignee = assignee;
        this.category = category;
        this.dishName = dishName;
    }

    public Dish(String assignee, String category, String dishName) {
        this.assignee = assignee;
        this.category = category;
        this.dishName = dishName;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAssignee() {
        return assignee;
    }
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getDishName() {
        return dishName;
    }
    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    @Override
    public String toString() {
        return "Dish [id=" + id + ", assignee=" + assignee + ", category=" + category + ", dishName=" + dishName + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((assignee == null) ? 0 : assignee.hashCode());
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((dishName == null) ? 0 : dishName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Dish other = (Dish) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (assignee == null) {
            if (other.assignee != null)
                return false;
        } else if (!assignee.equals(other.assignee))
            return false;
        if (category == null) {
            if (other.category != null)
                return false;
        } else if (!category.equals(other.category))
            return false;
        if (dishName == null) {
            if (other.dishName != null)
                return false;
        } else if (!dishName.equals(other.dishName))
            return false;
        return true;
    }

    
}
