package foodTruckReviews;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {

	Tag findByTypeIgnoreCaseLike(String tagType);



}
