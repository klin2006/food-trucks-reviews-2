package foodTruckReviews;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

=======
import javax.persistence.Lob;


import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;


import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Review {


	@Id
	@GeneratedValue
	private long id;
	
	@Lob
	private String review;
	
	@JsonIgnore
	@OneToMany(mappedBy ="review")
	private Collection<Comment> comments;
	
	public Review() {
	}
	
	@JsonIgnore
	@ManyToOne
	private Foodtruck foodtruck;

	public Review(String review, Foodtruck foodtruck) {
		this.review = review;
		this.foodtruck = foodtruck;
		
		
	
	}
	
	public String getReview() {
		return review;
	}
	
	public String getName() {
		return review.toString();
	}
	
	public long getId() {
		return id;
	}
	
	public Collection<Comment> getComments() {
		
		return comments;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		return true;
	}


}
