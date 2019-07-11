package foodTruckReviews;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping	("/tags")

public class TagRestController {
	@Resource
	private FoodtruckRepository foodtruckRepo;
	
	@Resource
	private TagRepository tagRepo;
	
	@RequestMapping("")
			public Iterable<Tag>findAllTags(){
				return tagRepo.findAll();
			}
	
	@RequestMapping("/{id}")
	public Optional<Tag>findOneTag(@PathVariable long id){
		return tagRepo.findById(id);
	}
	
	@RequestMapping("/{tagType}/foodtrucks")
	public Collection<Foodtruck> findAllFoodtrucksByTag(@PathVariable(value = "tagType") String tagType){
	Tag tag = tagRepo.findByTypeIgnoreCaseLike(tagType);
		return foodtruckRepo.findByTagsContains(tag); 
	}
}

