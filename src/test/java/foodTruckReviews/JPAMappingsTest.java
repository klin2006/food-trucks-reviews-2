package foodTruckReviews;

import javax.annotation.Resource;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;

import java.util.Collection;
import java.util.Optional;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import foodTruckReviews.Cuisine;
import foodTruckReviews.CuisineRepository;

import foodTruckReviews.Foodtruck;
import foodTruckReviews.FoodtruckRepository;

import foodTruckReviews.Review;
import foodTruckReviews.ReviewRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest

public class JPAMappingsTest {
	@Resource
	private CuisineRepository cuisineRepo;
	
	@Resource

	private FoodtruckRepository foodtruckRepo;

	
	@Resource
	private TestEntityManager entityManager;
	
	@Resource
	ReviewRepository reviewRepo;
	
	
	@Test
	public void shouldSaveAndLoadCuisine() {
		Cuisine cuisine = new Cuisine("type");
		cuisine = cuisineRepo.save(cuisine);
		long cuisineId = cuisine.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Cuisine> result = cuisineRepo.findById(cuisineId);
		cuisine = result.get();
		assertThat(cuisine.getType(), is("type"));
		
	}
	
	@Test
	public void shouldGenerateCuisineId() {
		Cuisine cuisine = new Cuisine("type");
		cuisine = cuisineRepo.save(cuisine);
		long cuisineId = cuisine.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(cuisineId, is(greaterThan(0L)));
		
	}

	@Test
	public void shouldSaveAndLoadFoodtruck() {
		Foodtruck foodtruck = new Foodtruck("name", "map");
		foodtruck = foodtruckRepo.save(foodtruck);
		long foodtruckId = foodtruck.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Foodtruck> result = foodtruckRepo.findById(foodtruckId);
		foodtruck = result.get();
		assertThat(foodtruck.getName(), is("name"));
		
	}
	
	@Test
	public void shouldEstablishFoodtruckToCuisinesRelationships() {
		Cuisine american = new Cuisine("american");
		american = cuisineRepo.save(american);
		Cuisine italian = new Cuisine("italian");
		italian = cuisineRepo.save(italian);
		
		Foodtruck foodtruck = new Foodtruck("Mikey's Late Night Slice", "map1", italian, american);
		foodtruck = foodtruckRepo.save(foodtruck);
		long foodtruckId = foodtruck.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Foodtruck> result = foodtruckRepo.findById(foodtruckId);
		foodtruck = result.get();
		assertThat(foodtruck.getCuisines(), containsInAnyOrder(american, italian));
	}
	
	@Test
	public void shouldFindFoodtrucksForCuisine() {
		Cuisine mexican = cuisineRepo.save(new Cuisine("mexican"));

		Foodtruck foodtruck1 = foodtruckRepo.save(new Foodtruck("Mr. Grill Tacos", "map1", mexican));
		Foodtruck foodtruck2 = foodtruckRepo.save(new Foodtruck("La Arepa Picante", "map2", mexican ));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Foodtruck> foodtrucksForCuisine = foodtruckRepo.findByCuisinesContains(mexican);
		assertThat(foodtrucksForCuisine, containsInAnyOrder(foodtruck1, foodtruck2));
		
	}
	
	@Test
	public void shouldFindFoodtrucksForCuisineId() {
		Cuisine mediterranean  = cuisineRepo.save(new Cuisine("mediterranean"));

		long cuisineId = mediterranean.getId();

		
		Foodtruck Foodtruck3 = foodtruckRepo.save(new Foodtruck("Halal NeywYork Gyro", "map3", mediterranean));
		Foodtruck Foodtruck4 = foodtruckRepo.save(new Foodtruck("Kabob Time", "map4", mediterranean));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Foodtruck> FoodtrucksForCuisine = foodtruckRepo.findByCuisinesId(cuisineId);
		assertThat(FoodtrucksForCuisine, containsInAnyOrder(Foodtruck3, Foodtruck4));
		
	}
	
	@Test
	public void shouldSaveReviewToFoodtruckRelationship() {
		Foodtruck foodtruck = foodtruckRepo.save(new Foodtruck("Halal NeywYork Gyro", "map3"));
		long foodtruckId = foodtruck.getId();
		
		Review review = reviewRepo.save(new Review("Review1", foodtruck));
		Review review2 = reviewRepo.save(new Review("Review2", foodtruck));
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Foodtruck>result = foodtruckRepo.findById(foodtruckId);
		foodtruck = result.get();
		assertThat(foodtruck.getReviews(), containsInAnyOrder(review, review2));
		
	}
	

}

