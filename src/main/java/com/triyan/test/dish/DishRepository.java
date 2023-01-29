package com.triyan.test.dish;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// Derived query reference: https://www.baeldung.com/spring-data-derived-queries
@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    
    // Translates to SELECT * FROM dishes AS d WHERE d.assignee = assignee
    Optional<Dish> findDishByAssignee(String assignee);

    @Query("SELECT d.category AS category, COUNT(*) AS count FROM Dish AS d GROUP BY d.category ORDER BY count DESC")
    List<ICategoryCount> countByCategoryOrderByCountDesc();
}
