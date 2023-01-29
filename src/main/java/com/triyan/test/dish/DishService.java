package com.triyan.test.dish;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DishService {

    private final DishRepository dishRepository;

    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<Dish> getDishes() {
		return dishRepository.findAll();
	}

    public Dish getDish(Long id) {
        Optional<Dish> optionalDish = dishRepository.findById(id);
        return optionalDish.isPresent() ? optionalDish.get() : null;
    }

    public String addDish(Dish dish) {
        Optional<Dish> optionalDish = dishRepository.findDishByAssignee(dish.getAssignee());
        if(optionalDish.isPresent()) {
            return "Record with assignee " + dish.getAssignee() + " already exists!";
        }
        
        dishRepository.save(dish);
        return "Added new record!";
    }

    public Boolean removeDish(Long id) {
        if(dishRepository.existsById(id)) {
            dishRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Boolean updateDish(Long id, Dish dish) {
        Optional<Dish> optionalDish = dishRepository.findById(id);
        if(optionalDish.isPresent()) {
            Dish oldDish = optionalDish.get();
            oldDish.setAssignee(dish.getAssignee());
            oldDish.setCategory(dish.getCategory());
            oldDish.setDishName(dish.getDishName());
            return true;
        }
        return false;
    }

    public List<ICategoryCount> getCountByCategory() {
        return dishRepository.countByCategoryOrderByCountDesc();
    }
}
