package com.bobocode;

import org.apache.commons.lang3.NotImplementedException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A generic comparator that is comparing a random field of the given class. The field is either primitive or
 * {@link Comparable}. It is chosen during comparator instance creation and is used for all comparisons.
 * <p>
 * If no field is available to compare, the constructor throws {@link IllegalArgumentException}
 *
 * @param <T> the type of the objects that may be compared by this comparator
 */
public class RandomFieldComparator<T> implements Comparator<T> {

    private Field comparableField;

    public RandomFieldComparator(Class<T> targetType) {
        //throw new IllegalArgumentException();
        var collect = Arrays.stream(targetType.getFields()).filter(f -> {
            boolean b = f.getType().isPrimitive() || f.getType().isNestmateOf(Comparable.class);
            return b;
        }).collect(Collectors.toList());

        int rand = (int)Math.random()*collect.size();

        Field field = collect.get(rand);
    }

    /**
     * Compares two objects of the class T by the value of the field that was randomly chosen. It allows null values
     * for the fields, and it treats null value grater than a non-null value.
     *
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(T o1, T o2) {
        try {
            Object field1 = o1.getClass().getField(comparableField.getName()).get(o1);
            Object field2 = o2.getClass().getField(comparableField.getName()).get(o2);

 //           if (field1 == 0)
            throw new NotImplementedException();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns a statement "Random field comparator of class '%s' is comparing '%s'" where the first param is the name
     * of the type T, and the second parameter is the comparing field name.
     *
     * @return a predefined statement
     */
    @Override
    public String toString() {
        throw new UnsupportedOperationException("This method should be implemented"); // todo:
    }
}
