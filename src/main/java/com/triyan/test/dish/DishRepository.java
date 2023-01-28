package com.triyan.test.dish;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    
    // Translates to SELECT * FROM dishes AS d WHERE d.assignee = assignee
    Optional<Dish> findDishByAssignee(String assignee);
}
