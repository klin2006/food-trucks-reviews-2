package foodTruckReviews;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component

public class FoodtruckPopulator implements CommandLineRunner{
	@Resource
	private FoodtruckRepository foodtruckRepo;
	
	@Resource
	private TagRepository cuisineRepo;
	
	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private CommentRepository commentRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
		Tag mexican = cuisineRepo.save(new Tag("Mexican"));
		Tag italian = cuisineRepo.save(new Tag("Italian"));
		Tag american = cuisineRepo.save(new Tag("American"));
		Tag mediterranean = cuisineRepo.save(new Tag("Mediterranean"));
		
		
		Foodtruck mikeys = foodtruckRepo.save(new Foodtruck("Mikey's Late Night Slice", "mikeys", american, italian));
		Foodtruck kabob = foodtruckRepo.save(new Foodtruck("Kabob Time", "kabob", mediterranean));
		Foodtruck rays = foodtruckRepo.save(new Foodtruck("Ray Ray's Hog Pit", "rayrays", american));
		Foodtruck halal = foodtruckRepo.save(new Foodtruck("Halal New York Gyro", "halal", mediterranean));
		Foodtruck arepa = foodtruckRepo.save(new Foodtruck("La Arepa Picante", "arepa", mexican));
		Foodtruck mrgrill = foodtruckRepo.save(new Foodtruck("Mr. Grill Tacos", "mrtaco", mexican));
		
		
		
		reviewRepo.save(new Review("Ryan's Mikey's Review", "It is definitely in the name. Mikey's is one of the best places to get a slice of pizza in the middle of the night. They have a very broad variety of pizzas but they also have the option of buying a pie.", mikeys));
		reviewRepo.save(new Review("Nicole's Mikey's Review", "Like pizza? Like places that stay open late? Like little wait time? Mikey's is your place. Nuff said.", mikeys));
		reviewRepo.save(new Review("Ryan's Kabob Time Review", " Kabob time is a food truck that is very near Ray Ray's and looks to have a good menu. As I really want to try it when at class but Ray Ray's gets in the way. I look forward to getting the new review up as soon as I can get over my BBQ addiction.", kabob));
		reviewRepo.save(new Review("Nicole's Ray Ray's Review", "Ray Ray's is amazing. I really enjoy the variety of the BBQ that is available, plus with the different sauces that \r\n" + 
				"you are able to try with the meat.", rays));
		reviewRepo.save(new Review("Ryan's Ray Ray's Review", "Ray Ray's BBQ has great quality meat that have great smoke rings as well as tasty sides it's a great choice for those who love great BBQ. They also have by the pound and catering options to fill out almost any need.", rays));
		reviewRepo.save(new Review("Kathy's Halal New York Gyro Review", "Halal New York Gyro is on the westside of Columbus right next to a Marathon gas station. It's decently priced, fairly timely, and pretty good. Would recommend!", halal));
		reviewRepo.save(new Review("Kathy's La Arepa Picante Review","La Arepa Picante is right across the street from the Casino. It has a wide variety of food which is reasonably priced. There is very little wait time and is delish!", arepa));
		reviewRepo.save(new Review("Kathy's Mr grill Tacos Review", "Mr. Grill Tacos is a favorite but there is a really long wait...one time it took us 45 minutes to get our food. It was worth it in the end, but don't got if you are in a rush.", mrgrill));
		
		Comment comment1 = commentRepo.save(new Comment("This review is nuts", reviewRepo.findByReviewName("Ryan's Mikey's Review")));
	}

}
