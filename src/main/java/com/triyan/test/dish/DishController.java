package com.triyan.test.dish;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "api/v1/dishes")
public class DishController {
	
	private final DishService dishService;

	@Autowired
    public DishController(DishService dishService) {
		this.dishService = dishService;
	}

	@GetMapping("/all")
    public List<Dish> getDishes() {
		return dishService.getDishes();
	}

	@GetMapping("/{id}")
	public Dish getDish(@PathVariable Long id) {
		Dish result = dishService.getDish(id);
		if(result == null) {
			throw new ResponseStatusException(
  				HttpStatus.NOT_FOUND, "Entity not found!"
			);
		};
		return result;
	}

	@PutMapping("{id}")
	public String updateDish(@PathVariable Long id, @RequestBody Dish dish) {
		if(!dishService.updateDish(id, dish)) {
			throw new ResponseStatusException(
  				HttpStatus.NOT_FOUND, "Entity not found!"
			);
		}
		return "Success";
	}

	@PostMapping(value = "/add")
	public String addDish(@RequestBody Dish dish) {
		return dishService.addDish(dish);
	}

	@DeleteMapping("/{id}")
	public String removeDish(@PathVariable Long id) {
		if(!dishService.removeDish(id)) {
			throw new ResponseStatusException(
  				HttpStatus.NOT_FOUND, "Entity not found!"
			);
		}
		return "Success";
	}
}
