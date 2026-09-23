package Task2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KeyMapCounter {

    public <T> Map<T, Integer> keyCount(T[] elements) {
        if (elements == null) {
            return new HashMap<>();
        }

        HashMap<T, Integer> myHashMap = new HashMap<>();
        for (T element : elements) {
            myHashMap.merge(element, 1, Integer::sum);
        }
        return myHashMap;
    }
}
