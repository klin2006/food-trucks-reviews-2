package foodTruckReviews;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag {
	
	@GeneratedValue
	@Id
	private long id;
	
	private String tag;

	@ManyToMany
	private Collection<Foodtruck> foodTruck;

	public Tag(String tag, Foodtruck...foodTruck) {
		this.tag = tag;
		this.foodTruck = new HashSet<>(Arrays.asList(foodTruck));
	}


	public long getId() {
		
		return id;
	}

	public Object getTag() {
		
		return tag;
	}

	public Tag() {
		
	}
	
	public Collection<Foodtruck> getFoodTruck(){
		return foodTruck;
	}
	
	public void addFoodTruckToTag(Foodtruck truckToAdd) {
		foodTruck.add(truckToAdd);
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
