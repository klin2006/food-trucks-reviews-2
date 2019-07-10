package foodTruckReviews;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;
import static org.hamcrest.Matchers.*;
import java.util.Arrays;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(FoodTruckController.class)
public class FoodTruckControllerMockMvcTest {
	
	@Resource
	private MockMvc mvc;
	
	@MockBean
	private FoodtruckRepository foodTruckRepo;
	
	@MockBean
	private CuisineRepository cuisineRepo;
	
	@MockBean
	private ReviewRepository reviewRepo;
	
	@MockBean
	private TagRepository tagRepo;
	
	
	@Mock
	Foodtruck foodTruck;
	
	@Mock
	Foodtruck anotherFoodtruck;
	
	@Mock
	Review review;
	
	@Mock
	Review anotherReview;
	
	@Mock
	Cuisine cuisine;
	
	@Mock
	Cuisine anotherCuisine;
	
	@Mock
	Tag tag;
	
	@Mock
	Tag anotherTag;
	
	
	@Test
	public void shouldRouteToSingleFoodTruckView() throws Exception{
		long arbitraryFoodTruckId = 1;
		when(foodTruckRepo.findById(arbitraryFoodTruckId)).thenReturn(Optional.of(foodTruck));
		
		mvc.perform(get("/foodtruck?id=1")).andExpect(view().name(is("foodtruck")));
	}
	
	@Test
	public void shouldRouteToSingleFoodTruck() throws Exception{
		long arbitraryFoodTruckId = 1;
		when(foodTruckRepo.findById(arbitraryFoodTruckId)).thenReturn(Optional.of(foodTruck));
		
		mvc.perform(get("/foodtruck?id=1")).andExpect(status().isOk());
		
		}
	
	@Test
	public void shouldNotRouteToSingleFoodTruck() throws Exception {
		mvc.perform(get("/foodtruck?id=1")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldPutSingleFoodTruckIntoModel() throws Exception {
		when(foodTruckRepo.findById(1L)).thenReturn(Optional.of(foodTruck));
		mvc.perform(get("/foodtruck?id=1")).andExpect(model().attribute("foodtrucks", is (foodTruck)));
	}
	
	@Test
	public void shouldRouteToAllFoodTrucksView () throws Exception {
		mvc.perform(get("/show-all-foodtrucks")).andExpect(view().name(is("show-all-foodtrucks")));
	}
	
	@Test
	public void shouldBeokForAllFoodTrucks () throws Exception {
		mvc.perform(get("/show-all-foodtrucks")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldPutAllFoodTrucksIntoModel() throws Exception {
		Collection<Foodtruck> allFoodTrucks = Arrays.asList(foodTruck, anotherFoodtruck);
		when(foodTruckRepo.findAll()).thenReturn(allFoodTrucks);
		
		mvc.perform(get("/show-all-foodtrucks")).andExpect(model().attribute("foodtrucks", is(allFoodTrucks)));
	}
		
	@Test
	public void shouldRouteToAllReviewsView () throws Exception {
		mvc.perform(get("/show-all-reviews")).andExpect(view().name(is("show-all-reviews")));
	}
	@Test
	public void shouldBeokForAllReviews () throws Exception {
		mvc.perform(get("/show-all-reviews")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldPutAllReviewsIntoModel() throws Exception {
		Collection<Review> allReviews = Arrays.asList(review, anotherReview);
		when(reviewRepo.findAll()).thenReturn(allReviews);
		
		mvc.perform(get("/show-all-reviews")).andExpect(model().attribute("reviews", is(allReviews)));
	}
	
	@Test
	public void shouldRouteToSingleCuisineView() throws Exception{
		long arbitraryCuisineId = 1;
		when(cuisineRepo.findById(arbitraryCuisineId)).thenReturn(Optional.of(cuisine));
		
		mvc.perform(get("/cuisine?id=1")).andExpect(view().name(is("cuisine")));
	}
	
	@Test
	public void shouldRouteToSingleCuisine() throws Exception{
		long arbitraryCuisineId = 1;
		when(cuisineRepo.findById(arbitraryCuisineId)).thenReturn(Optional.of(cuisine));
		
		mvc.perform(get("/cuisine?id=1")).andExpect(status().isOk());
		
		}
	
	@Test
	public void shouldNotRouteToSingleCuisine() throws Exception {
		mvc.perform(get("/cuisine?id=1")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldPutSingleCuisineIntoModel() throws Exception {
		when(cuisineRepo.findById(1L)).thenReturn(Optional.of(cuisine));
		mvc.perform(get("/cuisine?id=1")).andExpect(model().attribute("cuisines", is (cuisine)));
	}
	
	@Test
	public void shouldRouteToAllCuisinesView () throws Exception {
		mvc.perform(get("/show-all-cuisines")).andExpect(view().name(is("show-all-cuisines")));
	}
	@Test
	public void shouldBeokForAllCuisines () throws Exception {
		mvc.perform(get("/show-all-cuisines")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldPutAllCuisinesIntoModel() throws Exception {
		Collection<Cuisine> allCuisines = Arrays.asList(cuisine, anotherCuisine);
		when(cuisineRepo.findAll()).thenReturn(allCuisines);
		
		mvc.perform(get("/show-all-cuisines")).andExpect(model().attribute("cuisines", is(allCuisines)));
	}
	
	
	@Test
	public void shouldRouteToSingleTagView() throws Exception{
		long arbitraryTagId = 1;
		when(tagRepo.findById(arbitraryTagId)).thenReturn(Optional.of(tag));
		
		mvc.perform(get("/tag?id=1")).andExpect(view().name(is("tag")));
	}
	@Test
	
	public void ShouldRouteToSingleTag() throws Exception{
		long arbitraryTagId = 1;
		when(tagRepo.findById(arbitraryTagId)).thenReturn(Optional.of(tag));
		
		mvc.perform(get("/tag?id=1")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldNotRouteToSingleTag() throws Exception {
		mvc.perform(get("/tag?id=1")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldPutSingleTagIntoModel() throws Exception {
		when(tagRepo.findById(1L)).thenReturn(Optional.of(tag));
		mvc.perform(get("/tag?id=1")).andExpect(model().attribute("tags", is (tag)));
	}
	
	@Test
	public void shouldRouteToAllTagssView () throws Exception {
		mvc.perform(get("/show-all-tags")).andExpect(view().name(is("show-all-tags")));
	}
	@Test
	public void shouldBeokForAllTags () throws Exception {
		mvc.perform(get("/show-all-tags")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldPutAllTagsIntoModel() throws Exception {
		Collection<Tag> allTags = Arrays.asList(tag, anotherTag);
		when(tagRepo.findAll()).thenReturn(allTags);
		
		mvc.perform(get("/show-all-tags")).andExpect(model().attribute("tags", is(allTags)));
	}
	
}
