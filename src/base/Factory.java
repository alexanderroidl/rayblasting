package base;

import java.util.ArrayList;

public class Factory<T> {
	private ArrayList<T> collection;
	
	public Factory() {
		collection = new ArrayList<T>();
	}
	
	public ArrayList<T> getCollection() {
		return collection;
	}
	
	public int add(T object) {
		collection.add(object);
		return collection.indexOf(object);
	}
	
	public int add(ArrayList<T> objects) {
		for(T object : objects) {
			collection.add(object);
		}
		return objects.size();
	}
	
	public boolean remove(T object) {
		return collection.remove(object);
	}
	
	public T get(int index) {
		return collection.get(index);
	}
	
	public int indexOf(T object) {
		return collection.indexOf(object);
	}
}
