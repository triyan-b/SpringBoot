package com.triyan.test.dish;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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
    void shouldAddDish() {
        // given
        Dish dish = new Dish(
                "A",
                "Main",
                "D0");
        String assignee = dish.getAssignee();
        given(dishRepository.findDishByAssignee(assignee)).willReturn(Optional.ofNullable(null));

        // when
        dishService.addDish(dish);

        // then
        ArgumentCaptor<Dish> dishCaptor = ArgumentCaptor.forClass(Dish.class);
        verify(dishRepository).save(dishCaptor.capture());
        assertThat(dishCaptor.getValue()).isEqualTo(dish);
    }

    @Test
    void shouldNotAddDish() {
        // given
        Dish dish = new Dish(
                "A",
                "Main",
                "D0");
        String assignee = dish.getAssignee();

        given(dishRepository.findDishByAssignee(assignee))
                .willReturn(Optional.of(
                        new Dish(
                                1L,
                                "A",
                                "Starter",
                                "D1")));

        // when
        dishService.addDish(dish);

        // then
        verify(dishRepository, never()).save(any(Dish.class));
    }

    @Test
    void testGetCountByCategory() {
        // given
        
        // when
        dishService.getCountByCategory();
        
        // then
        verify(dishRepository).countByCategoryOrderByCountDesc();
    }

    @Test
    void shouldGetDish() {
        // given
        Dish dish = new Dish(
                0L,
                "A",
                "Main",
                "D0");
        Long id = dish.getId();
        given(dishRepository.findById(id)).willReturn(Optional.of(dish));

        // when
        Dish result = dishService.getDish(id);

        // then
        verify(dishRepository).findById(id);
        assertThat(result).isEqualTo(dish);
    }

    @Test
    void shouldNotGetNonExistentDish() {
        // given
        Long id = 0L;
        given(dishRepository.findById(id)).willReturn(Optional.ofNullable(null));

        // when
        Dish result = dishService.getDish(id);

        // then
        verify(dishRepository).findById(id);
        assertThat(result).isNull();
    }

    @Test
    void shouldGetAllDishes() {
        // when
        dishService.getDishes();

        // then
        verify(dishRepository).findAll();
    }

    @Test
    void shouldRemoveDish() {
        // given
        Long id = 0L;
        given(dishRepository.existsById(id)).willReturn(true);

        // when
        Boolean result = dishService.removeDish(id);

        // then
        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
        verify(dishRepository).deleteById(idCaptor.capture());
        assertThat(idCaptor.getValue()).isEqualTo(id);
        assertThat(result).isTrue();
    }

    @Test
    void shouldNotRemoveNonExistentDish() {
        // given
        Long id = 0L;
        given(dishRepository.existsById(id)).willReturn(false);

        // when
        Boolean result = dishService.removeDish(id);

        // then
        verify(dishRepository, never()).deleteById(id);
        assertThat(result).isFalse();
    }

    @Test
    void shouldUpdateDish() {
        // given
        Long id = 0L;
        given(dishRepository.findById(id)).willReturn(Optional.of(new Dish()));

        // when
        Boolean result = dishService.updateDish(id, new Dish());

        // then
        assertThat(result).isTrue();
    }

    @Test
    void shouldNotUpdateNonExistentDish() {
        // given
        Long id = 0L;
        given(dishRepository.findById(id)).willReturn(Optional.ofNullable(null));

        // when
        Boolean result = dishService.updateDish(id, any(Dish.class));

        // then
        assertThat(result).isFalse();
    }
}
