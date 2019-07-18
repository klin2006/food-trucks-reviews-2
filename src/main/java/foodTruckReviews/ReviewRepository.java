package foodTruckReviews;


import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	Collection<Review> findByCommentsContains(Comment comment);

	Collection<Review> findByCommentsId(long commentId);

	Review findByReview(Review reviewReview);

	

	Review findByReviewName(String reviewName);



	

}
