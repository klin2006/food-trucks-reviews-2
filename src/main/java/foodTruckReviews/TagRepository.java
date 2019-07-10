package foodTruckReviews;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long>{

	Collection<Tag> findByFoodTruckContains(Foodtruck foodTruck);

	Collection<Tag> findByFoodTruckIdContains(long id);

}
