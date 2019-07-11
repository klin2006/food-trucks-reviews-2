package foodTruckReviews;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;



@Entity

public class Tag {
	@Id
	@GeneratedValue
	private long id;
	
	private String type;
	

	@ManyToMany(mappedBy = "tags")
	private Collection<Foodtruck>foodtrucks;

	
	public Tag() {
		
	}

	public Tag(String type) {
		this.type = type;
	}
	
	public String getName() {
		return type.toString();
	}
	
	public long getId() {
		return id;
	}
	
	public String getType(){
		return type;
	}

	public Collection<Foodtruck> getFoodtrucks(){
		return foodtrucks;
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
		Tag other = (Tag) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
