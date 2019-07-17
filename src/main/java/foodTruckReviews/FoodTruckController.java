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
	
	@Resource
	CommentRepository commentRepo;

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
//			model.addAttribute("foodtrucks", foodTruckRepo.findByTagsContains(tag.get()));
			model.addAttribute("foodtrucks", tag.get().getFoodtrucks());

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
		return "partial/tags-list-added";
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
		return "partial/tags-list-removed";
	}

	@RequestMapping(path ="/tags/{tagType}/{id}", method= RequestMethod.POST)
	public void addTagToFoodTruck(String tagType, long id) {
		Tag tagToAdd = tagRepo.findByType(tagType);
		if(tagToAdd == null) {
			tagToAdd = new Tag(tagType);
		}
		Foodtruck foodTruckToAddTo = foodTruckRepo.findById(id).get();
		
		foodTruckToAddTo.addTag(tagToAdd);
		foodTruckRepo.save(foodTruckToAddTo);
		
	}
	

	@RequestMapping("/comment")
	public String findOneComment(@RequestParam(value="id") Long id, Model model) throws CommentNotFoundException {
		Optional<Comment> comment = commentRepo.findById(id);
		
		if(comment.isPresent()) {
			model.addAttribute("comments", comment.get());
			model.addAttribute("reviews", reviewRepo.findByCommentsContains(comment.get()));
			return ("comment");
		}
		throw new CommentNotFoundException();

	}
	@RequestMapping("/show-all-comments")
	public String findAllComments(Model model) {
		model.addAttribute("comments", commentRepo.findAll());
		return ("show-all-comments");
		
	}
	
	@RequestMapping("/add-review")
	public String addReview(String reviewReview, String foodtruckName) {
		Foodtruck foodtruck = foodTruckRepo.findByName(foodtruckName);
		Review newReview = reviewRepo.findByReview(reviewReview);
		
		if (newReview == null) {
		newReview = new Review(reviewReview, foodtruck);
		reviewRepo.save(newReview);
		}
		
		return "redirect:/show-all-foodtrucks";
	}
	
	@RequestMapping("/add-foodtruck-tag")
	public String addTagToFoodtruck(String foodtruckName, String foodtruckMap, String tagType) {
		Tag tag = tagRepo.findByType(tagType);
		
		if(tag == null) {
		tag = new Tag(tagType);
		tagRepo.save(tag);
		}
		
		Foodtruck newFoodtruck = foodTruckRepo.findByName(foodtruckName);
		if (newFoodtruck == null) {
			newFoodtruck = new Foodtruck(foodtruckName, foodtruckMap, tag);
			foodTruckRepo.save(newFoodtruck);
			}
			
			return "redirect:/show-all-foodtrucks";
		}
	
	@RequestMapping("/find-by-tag")
	public String findFoodtrucksByTag(String tagType, Model model) {
	Tag tag = tagRepo.findByType(tagType);
	model.addAttribute("foodtrucks",foodTruckRepo.findByTagsContains(tag));
	
	return "/tag";
	}

		
	}
	 


;