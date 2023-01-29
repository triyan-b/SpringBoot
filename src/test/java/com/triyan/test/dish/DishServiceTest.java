package com.triyan.test.dish;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DishServiceTest {
    
    @Mock
    private DishRepository dishRepository;
    private DishService dishService;

    @BeforeEach
    void setUp() {
        dishService = new DishService(dishRepository);
    }


    @Test
    void testAddDish() {
        
    }

    @Test
    void testGetCountByCategory() {

    }

    @Test
    void testGetDish() {

    }

    @Test
    void canGetDishes() {
        // when
        dishService.getDishes();

        // then
        verify(dishRepository).findAll();
    }

    @Test
    void testRemoveDish() {

    }

    @Test
    void testUpdateDish() {

    }
}
