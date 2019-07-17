package foodTruckReviews;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface FoodtruckRepository extends CrudRepository<Foodtruck, Long> {

	Collection<Foodtruck> findByTagsContains(Tag tags);

	Collection<Foodtruck> findByTagsId(Long id);

	Foodtruck findByName(String foodtruckName);



}
