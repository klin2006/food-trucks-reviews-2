package foodTruckReviews;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity

public class Foodtruck {
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	private String map;
	
	@ManyToMany
	private Collection<Tag> tags;
	
	@OneToMany(mappedBy = "foodtruck")
	private Collection<Review> reviews;
	
	public Foodtruck() {
		
	}
	
	public Foodtruck(String name, String map, Tag...tags) {
		this.name = name;
		this.map = map;
		this.tags = new HashSet<>(Arrays.asList(tags));
	}

	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getMap() {
		return map;
	}
	
	public Collection<Tag> getTags(){
		
		return tags;
	}
	
	
	public Collection<Review> getReviews() {
		return reviews;
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
		Foodtruck other = (Foodtruck) obj;
		if (id != other.id)
			return false;
		return true;
	}


}
