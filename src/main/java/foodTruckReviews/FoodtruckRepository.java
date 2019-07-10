package foodTruckReviews;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface FoodtruckRepository extends CrudRepository<Foodtruck, Long> {

	Collection<Foodtruck> findByCuisinesContains(Cuisine cuisine);

	Collection<Foodtruck> findByCuisinesId(Long id);

}
