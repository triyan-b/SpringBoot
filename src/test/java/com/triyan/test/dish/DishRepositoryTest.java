package com.triyan.test.dish;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class DishRepositoryTest {
    
    @Autowired
    private DishRepository dishRepository;

    @AfterEach
    void tearDown() {
        dishRepository.deleteAll();
    }

    @Test
    void testCountByCategoryOrderByCountDesc() {
        // given
        List<Dish> dishes = List.of(
            new Dish("A", "Starter", "D0"),
            new Dish("B", "Starter", "D1"),
            new Dish("C", "Starter", "D2"),
            new Dish("D", "Starter", "D3"),
            new Dish("E", "Starter", "D4"),
            new Dish("F", "Main", "D5"),
            new Dish("G", "Main", "D6"),
            new Dish("H", "Dessert", "D7"),
            new Dish("I", "Dessert", "D8"),
            new Dish("J", "Dessert", "D9")
        );
        dishRepository.saveAll(dishes);

        // when
        List<ICategoryCount> counts = dishRepository.countByCategoryOrderByCountDesc();
        
        // then
        assertThat(counts.size()).isEqualTo(3);
        assertThat(counts.get(0).getCategory()).isEqualTo("Starter");
        assertThat(counts.get(0).getCount()).isEqualTo(5);
        assertThat(counts.get(1).getCategory()).isEqualTo("Dessert");
        assertThat(counts.get(1).getCount()).isEqualTo(3);
        assertThat(counts.get(2).getCategory()).isEqualTo("Main");
        assertThat(counts.get(2).getCount()).isEqualTo(2);
    }

    @Test
    void itShouldFindDishByAssignee() {
        // given
        Dish dish = new Dish(
            "Triyan",
            "Main",
            "Pasta"
        );
        dishRepository.save(dish);
        
        // when
        Optional<Dish> result = dishRepository.findDishByAssignee(dish.getAssignee());
        
        // then
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(dish);
    }

    @Test
    void itShouldNotFindDishByAssignee() {
        // given
        String assignee = "DoesNotExist";
        
        // when
        Optional<Dish> result = dishRepository.findDishByAssignee(assignee);
        
        // then
        assertThat(result.isPresent()).isFalse();
    }
}
