package foodTruckReviews;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping	("/foodtrucks")

public class FoodtruckRestController {
	
	@Resource
	private FoodtruckRepository foodtruckRepo;
	
	@Resource
	private TagRepository tagRepo;
	
	@RequestMapping("")
			public Iterable<Foodtruck>findAllFoodtrucks(){
				return foodtruckRepo.findAll();
			}
	
	@RequestMapping("/{id}")
	public Optional<Foodtruck>findOneFoodtruck(@PathVariable long id){
		return foodtruckRepo.findById(id);
	}
	
	@RequestMapping("/tags/{tagType}")
	public Collection<Foodtruck> findAllFoodtrucksByTag(@PathVariable(value = "tagType") String tagType){
	Tag tag = tagRepo.findByTypeIgnoreCaseLike(tagType);
		return foodtruckRepo.findByTagsContains(tag); 
	}
}
