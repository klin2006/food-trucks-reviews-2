package foodTruckReviews;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue; 
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Comment {

	
	@GeneratedValue
	@Id
	private long id;
	
	private String comment;
	
	@ManyToOne
	private Review review;

	

	public Comment(String comment, Review review) {
		this.comment = comment;
		this.review = review;
	}
	
	public Comment() {
		
	}

	public long getId() {
		
		return id;
	}

	public String getComment() {
		
		return comment;
	}
	
	public Review getReview() {
		return review;
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
		Comment other = (Comment) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
