package foodTruckReviews;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FoodTruckController {

	@Resource
	TagRepository tagRepo;

	@Resource
	ReviewRepository reviewRepo;

	@Resource
	FoodtruckRepository foodTruckRepo;
	
	@RequestMapping("/foodtruck")
	public String findOneFoodTruck(@RequestParam(value = "id") Long id, Model model) throws FoodTruckNotFoundException {
		Optional<Foodtruck> foodTruck = foodTruckRepo.findById(id);

		if (foodTruck.isPresent()) {
			model.addAttribute("foodtrucks", foodTruck.get());
			return ("foodtruck");
		}
		throw new FoodTruckNotFoundException();
	}

	@RequestMapping("/show-all-foodtrucks")
	public String findAllFoodTrucks(Model model) {
		model.addAttribute("foodtrucks", foodTruckRepo.findAll());
		return "show-all-foodtrucks";

	}

	@RequestMapping("/review")
	public String findOneReview(@RequestParam(value = "id") Long id, Model model) throws ReviewNotFoundException {
		Optional<Review> review = reviewRepo.findById(id);

		if (review.isPresent()) {
			model.addAttribute("reviews", review.get());
			return ("review");
		}
		throw new ReviewNotFoundException();

	}

	@RequestMapping("/show-all-reviews")
	public String findAllReviews(Model model) {
		model.addAttribute("reviews", reviewRepo.findAll());
		return ("show-all-reviews");

	}

	@RequestMapping("/tag")
	public String findOneTag(@RequestParam(value = "id") Long id, Model model) throws TagNotFoundException {
		Optional<Tag> tag = tagRepo.findById(id);

		if (tag.isPresent()) {
			model.addAttribute("tags", tag.get());
			model.addAttribute("foodtrucks", foodTruckRepo.findByTagsContains(tag.get()));

			return ("tag");
		}
		throw new TagNotFoundException();

	}

	@RequestMapping("/show-all-tags")
	public String findAllTags(Model model) {
		model.addAttribute("tags", tagRepo.findAll());
		return ("show-all-tags");
	}
	
	@RequestMapping(path="/tags/{tagType}", method=RequestMethod.POST)
	public String addTag(@PathVariable String tagType, Model model) {
		Tag tagToAdd = tagRepo.findByType(tagType);
		if(tagToAdd == null) {
			tagToAdd=new Tag(tagType);
			tagRepo.save(tagToAdd);		
		}
		model.addAttribute("tags", tagRepo.findAll());
		return "partials/tags-list-added";
	}
	
	@RequestMapping(path ="/tags/remove/{id}", method= RequestMethod.POST)
	public String removeTag(@PathVariable Long id, Model model) {
		Optional<Tag> tagToRemoveResult = tagRepo.findById(id);
		Tag tagToRemove = tagToRemoveResult.get();
		
		for(Foodtruck foodtruck: tagToRemove.getFoodtrucks()) {
			foodtruck.removeTag(tagToRemove);
			foodTruckRepo.save(foodtruck);
		}
		
		
		tagRepo.delete(tagToRemove);
		model.addAttribute("tags", tagRepo.findAll());
		return "partials/tags-list-removed";
	}
	
	
}	

