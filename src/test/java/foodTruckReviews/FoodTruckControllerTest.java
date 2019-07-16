package foodTruckReviews;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;


public class FoodTruckControllerTest {
	
	@InjectMocks
	FoodTruckController underTest;
	
	@Mock
	private Foodtruck foodTruck;
	
	@Mock
	private Foodtruck anotherFoodtruck;
	
	@Mock
	private FoodtruckRepository foodTruckRepo;
	
	@Mock
	private Review review;
	
	@Mock
	private Review anotherReview;
	
	@Mock
	private ReviewRepository reviewRepo;
	
	@Mock
	private Tag tag;
	
	@Mock
	private Tag anotherTag;
	
	@Mock
	private TagRepository tagRepo;
	
	@Mock
	private Model model;
	
	@Mock
	private CommentRepository commentRepo;
	
	@Mock
	private Comment comment;
	
	@Mock
	private Comment anotherComment;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAddSingleFoodTruckToModel() throws FoodTruckNotFoundException {
		long arbitraryFoodTruckId = 1;
		when(foodTruckRepo.findById(arbitraryFoodTruckId)).thenReturn(Optional.of(foodTruck));
		
		underTest.findOneFoodTruck(arbitraryFoodTruckId, model);
		verify(model).addAttribute("foodtrucks", foodTruck);
	}
	
	@Test
	public void shouldAddAllFoodTrucksToModel() {
		Collection<Foodtruck> allFoodTrucks = Arrays.asList(foodTruck, anotherFoodtruck);
		when(foodTruckRepo.findAll()).thenReturn(allFoodTrucks);
		
		underTest.findAllFoodTrucks(model);
		verify(model).addAttribute("foodtrucks", allFoodTrucks);
	}
	
	@Test
	public void shouldAddSingleReviewToModel() throws ReviewNotFoundException {
		long arbitraryReviewId = 1;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
		
		underTest.findOneReview(arbitraryReviewId, model);
		verify(model).addAttribute("reviews", review);
		
		
	}
	@Test
	public void shouldAddAllReviewsToModel() {
		Collection<Review> allreviews = Arrays.asList(review, anotherReview);
		when(reviewRepo.findAll()).thenReturn(allreviews);
		
		underTest.findAllReviews(model);
		verify(model).addAttribute("reviews", allreviews);
	}
	
	@Test
	public void shouldAddSingleTagToModel() throws TagNotFoundException {
		long arbitraryTagId = 1;
		when(tagRepo.findById(arbitraryTagId)).thenReturn(Optional.of(tag));
		
		underTest.findOneTag(arbitraryTagId, model);
		verify(model).addAttribute("tags", tag);
	}
	
	@Test
	public void shouldAddAllTagsModel() {
		Collection<Tag> allTags = Arrays.asList(tag, anotherTag);
		when(tagRepo.findAll()).thenReturn(allTags);
		
		underTest.findAllTags(model);
		verify(model).addAttribute("tags", allTags);
	}
	
	@Test
	public void shouldAddNewTagToFoodtruck() {
		when(tagRepo.findByType("TagType")).thenReturn(null);
		when(foodTruckRepo.findById(1L)).thenReturn(Optional.of(foodTruck));
		underTest.addTagToFoodTruck("TagType",1L);
		verify(foodTruckRepo).save(foodTruck);
		
	}
	
	@Test
	public void shouldAddSingleCommentToModel() throws CommentNotFoundException {
		long arbitraryCommentId = 1;
		when(commentRepo.findById(arbitraryCommentId)).thenReturn(Optional.of(comment));
		
		underTest.findOneComment(arbitraryCommentId, model);
		verify(model).addAttribute("comments", comment);
	}
	
	@Test
	public void shouldAddAllCommentsToModel() {
		Collection<Comment> allComments = Arrays.asList(comment, anotherComment);
		when(commentRepo.findAll()).thenReturn(allComments);
		
		underTest.findAllComments(model);
		verify(model).addAttribute("comments", allComments);
	}
}

