package uchidb;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author aelmore
 */
public class HW0Runner {
	private static class SingletonContainersImpl implements Containers<Integer, String> {
		private Map<String, Integer> map = null;

		// Prevents other classes from instantiating this one.
		private SingletonContainersImpl() {}

		public Set<Integer> initSet(Integer[] tArray) {
			return new HashSet<Integer>(Arrays.asList(tArray));
		}

		public List<Integer> initList(Integer[] tArray) {
			return Arrays.asList(tArray);
		}

		public Map<String, Integer> initEmptyMap() {
			return new HashMap<String, Integer>();
		}

		public void storeMap(Map<String, Integer> mapToStoreInClass) {
			map = mapToStoreInClass;
		}

		public boolean addToMap(String key, Integer value, boolean overwriteExistingKey) {
			assert (map != null) : "Internal map was not initialized with storeMap";
			if (overwriteExistingKey || !map.containsKey(key)) {
				map.put(key, value);
				return true;
			}
			return false;
		}

		// If the key is not present in the map, this function will return null.
		public Integer getValueFromMap(String key) {
			assert (map != null) : "Internal map was not initialized with storeMap";
			return map.get(key);
		}

		public Integer getValueFromMap(String key, Integer defaultValue) {
			assert (map != null) : "Internal map was not initialized with storeMap";
			// Since some Map implementations can contain null keys, we cannot use the
			// get() function, since it returns null if a key is not present in the map
			// or if the key is present but has a null value. Instead we need to
			// explicitly look up whether the key is contained in the map.
			return map.containsKey(key) ? map.get(key) : defaultValue;
		}
	}

	private static SingletonContainersImpl instance = null;

	// This class is a factory for a singleton containers class.
	// https://www.tutorialspoint.com/java/java_using_singleton.htm
	public static Containers<Integer, String> getContainers() {
		if (instance == null) {
			instance = new SingletonContainersImpl();
		}
		return instance;
	}

	public static void main(String[] args) {}
}
