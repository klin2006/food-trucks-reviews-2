package foodTruckReviews;

import javax.annotation.Resource;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;

import java.util.Collection;
import java.util.Optional;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import foodTruckReviews.Tag;
import foodTruckReviews.TagRepository;

import foodTruckReviews.Foodtruck;
import foodTruckReviews.FoodtruckRepository;

import foodTruckReviews.Review;
import foodTruckReviews.ReviewRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest

public class JPAMappingsTest {
	
	@Resource
	private TagRepository tagRepo;
	
	@Resource

	private FoodtruckRepository foodtruckRepo;
			
	@Resource
	private TestEntityManager entityManager;
	
	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private CommentRepository commentRepo;
	
	
	
	
	@Test
	public void shouldSaveAndLoadTag() {
		Tag tag = new Tag("type");
		tag = tagRepo.save(tag);
		long tagId = tag.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Tag> result = tagRepo.findById(tagId);
		tag = result.get();
		assertThat(tag.getType(), is("type"));
		
	}
	
	@Test
	public void shouldGenerateTagId() {
		Tag tag = new Tag("type");
		tag = tagRepo.save(tag);
		long tagId = tag.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(tagId, is(greaterThan(0L)));
		
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
	public void shouldEstablishFoodtruckToTagsRelationships() {
		Tag american = new Tag("american");
		american = tagRepo.save(american);
		Tag italian = new Tag("italian");
		italian = tagRepo.save(italian);
		
		Foodtruck foodtruck = new Foodtruck("Mikey's Late Night Slice", "map1", italian, american);
		foodtruck = foodtruckRepo.save(foodtruck);
		long foodtruckId = foodtruck.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Foodtruck> result = foodtruckRepo.findById(foodtruckId);
		foodtruck = result.get();
		assertThat(foodtruck.getTags(), containsInAnyOrder(american, italian));
	}
	
	@Test
	public void shouldFindFoodtrucksForTag() {
		Tag mexican = tagRepo.save(new Tag("mexican"));

		Foodtruck foodtruck1 = foodtruckRepo.save(new Foodtruck("Mr. Grill Tacos", "map1", mexican));
		Foodtruck foodtruck2 = foodtruckRepo.save(new Foodtruck("La Arepa Picante", "map2", mexican ));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Foodtruck> foodtrucksForCuisine = foodtruckRepo.findByTagsContains(mexican);
		assertThat(foodtrucksForCuisine, containsInAnyOrder(foodtruck1, foodtruck2));
		
	}
	
	@Test
	public void shouldFindFoodtrucksForTagId() {
		Tag mediterranean  = tagRepo.save(new Tag("mediterranean"));

		long cuisineId = mediterranean.getId();
		
		Foodtruck foodtruck3 = foodtruckRepo.save(new Foodtruck("Halal NeywYork Gyro", "map3", mediterranean));
		Foodtruck foodtruck4 = foodtruckRepo.save(new Foodtruck("Kabob Time", "map4", mediterranean));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Foodtruck> FoodtrucksForCuisine = foodtruckRepo.findByTagsId(cuisineId);
		assertThat(FoodtrucksForCuisine, containsInAnyOrder(foodtruck3, foodtruck4));
		
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
	
	@Test
<<<<<<< HEAD
	public void shouldSaveTagToFoodtruck() {
		Tag mediterranean  = tagRepo.save(new Tag("mediterranean"));
		long cuisineId = mediterranean.getId();
		
		Foodtruck foodtruck3 = foodtruckRepo.save(new Foodtruck("Halal NeywYork Gyro", "map3", mediterranean));
		
		Tag american = tagRepo.save(new Tag("american"));
		long tagId = american.getId();

		foodtruck3.addTag(american);
		foodtruckRepo.save(foodtruck3);
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Foodtruck>result = foodtruckRepo.findById(foodtruck3.getId());
		Foodtruck retrieveFoodtruck = result.get();
		
		assertThat(retrieveFoodtruck.getTags(), containsInAnyOrder(american, mediterranean));
		
		Optional<Tag>result2 = tagRepo.findById(american.getId());
		Tag retrieveTag = result2.get();
		assertThat(retrieveTag.getFoodtrucks(), contains(foodtruck3));
		
		
		
	
		
		
		
	}
=======
	public void shouldSaveAndLoadComment() {
		Foodtruck foodtruck = foodtruckRepo.save(new Foodtruck("food truck", "map"));
		Review review = reviewRepo.save(new Review("review", foodtruck));
		Comment comment = new Comment("comment", review);
		comment = commentRepo.save(comment);
		long commentId = comment.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Comment> result = commentRepo.findById(commentId);
		comment = result.get();
		assertThat(comment.getComment(), is("comment"));
	}
	
	@Test
	public void shouldGenerateCommentId() {
		Foodtruck foodtruck = foodtruckRepo.save(new Foodtruck("food truck", "map"));
		Review review = reviewRepo.save(new Review("review", foodtruck));
		Comment comment = new Comment("comment", review);
		comment = commentRepo.save(comment);
		long commentId = comment.getId();
		
		entityManager.flush();
		entityManager.clear();
		assertThat(commentId, is(greaterThan(0L)));
	}
	
	@Test
	public void shouldEstablishCommentForReviewsRelationship() {
		Foodtruck foodtruck = foodtruckRepo.save(new Foodtruck("Halal NeywYork Gyro", "map3"));
		Review review = new Review("review", foodtruck);
		Comment comment1 = new Comment("comment 1", review);
		comment1 = commentRepo.save(comment1);
		Comment comment2 = new Comment("comment 2", review);
		comment2 = commentRepo.save(comment2);
		
		review = reviewRepo.save(review);
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		assertThat(review.getComments(), containsInAnyOrder(comment1, comment2));
	}
	
	@Test
	public void shouldFindReviewForComment() {
		Foodtruck foodtruck = foodtruckRepo.save(new Foodtruck("Halal NeywYork Gyro", "map3"));
		Review review = reviewRepo.save(new Review("review", foodtruck));
		Comment comment1 = new Comment("comment 1", review);
		comment1 = commentRepo.save(comment1);
	
		entityManager.flush();
		entityManager.clear();
		
		Collection<Review> reviewForComment = reviewRepo.findByCommentsContains(comment1);
		
		assertThat(reviewForComment, containsInAnyOrder(review));
	}
	
	@Test
	public void shouldFindReviewForCommentId() {
		Foodtruck foodtruck = foodtruckRepo.save(new Foodtruck("Halal NeywYork Gyro", "map3"));
		Review review = reviewRepo.save(new Review("review", foodtruck));
		Comment comment1 = new Comment("comment 1", review);
		comment1 = commentRepo.save(comment1);
		long comment1Id = comment1.getId();
		
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Review> reviewForComment = reviewRepo.findByCommentsId(comment1Id);
		assertThat(reviewForComment, containsInAnyOrder(review));
		
	}
	
	
>>>>>>> 65c8d53ade28a90cb84a55584e4013559b66a737
}	
