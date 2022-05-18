package com.bobocode.homework5;

import org.apache.commons.lang3.ClassUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A generic comparator that is comparing a random field of the given class. The field is either primitive or
 * {@link Comparable}. It is chosen during comparator instance creation and is used for all comparisons.
 * <p>
 * By default it compares only accessible fields, but this can be configured via a constructor property. If no field is
 * available to compare, the constructor throws {@link IllegalArgumentException}
 *
 * @param <T> the type of the objects that may be compared by this comparator
 */
public class RandomFieldComparator<T> implements Comparator<T> {

    private final boolean compareOnlyAccessibleFields;
    private final Class<T> targetType;
    private final Field comparinger;

    public RandomFieldComparator(Class<T> targetType) {
        this(targetType, true);
    }

    /**
     * A constructor that accepts a class and a property indicating which fields can be used for comparison. If property
     * value is true, then only public fields or fields with public getters can be used.
     *
     * @param targetType                  a type of objects that may be compared
     * @param compareOnlyAccessibleFields config property indicating if only publicly accessible fields can be used
     */
    public RandomFieldComparator(Class<T> targetType, boolean compareOnlyAccessibleFields) {
        this.targetType = targetType;
        this.compareOnlyAccessibleFields = compareOnlyAccessibleFields;

        List<Field> usefulFields = Arrays.stream(targetType.getDeclaredFields()).filter(f ->
                isPrimitiveOrComparable(f.getType()) || hasGetterPrimitiveOrComparable(f)).toList();

        if (compareOnlyAccessibleFields) {
            usefulFields = usefulFields.stream().filter(f ->
                    Modifier.isPublic(f.getModifiers()) || (hasGetter(f) && isPublicGetter(f))).toList();
        }
        if (usefulFields.isEmpty()) {
            throw new IllegalArgumentException();
        }

        comparinger = usefulFields.get(ThreadLocalRandom.current().nextInt(usefulFields.size()));
    }

    private boolean isPrimitiveOrComparable(Class<?> type) {
        return ClassUtils.isPrimitiveOrWrapper(type)
                || Comparable.class.isAssignableFrom(type);
    }

    private boolean hasGetter(Field field) {
        try {
            return new PropertyDescriptor(field.getName(), targetType).getReadMethod() != null;
        } catch (IntrospectionException e) {
            return false;
        }
    }

    private boolean hasGetterPrimitiveOrComparable(Field field) {
        try {
            Method readMethod = new PropertyDescriptor(field.getName(), targetType).getReadMethod();
            return isPrimitiveOrComparable(readMethod.getReturnType());
        } catch (IntrospectionException e) {
            return false;
        }
    }

    private boolean isPublicGetter(Field field) {
        try {
            return Modifier.isPublic(new PropertyDescriptor(field.getName(), targetType).getReadMethod().getModifiers());
        } catch (IntrospectionException e) {
            return false;
        }
    }

    public Comparable invokeGetter(String variableName, Object obj)
    {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(variableName, targetType);
            Method getter = pd.getReadMethod();
            return (Comparable) getter.invoke(obj);
        } catch (IllegalAccessException | IllegalArgumentException | IntrospectionException | InvocationTargetException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Compares two objects of the class T by the value of the field that was randomly chosen. It allows null values
     * for the fields, and it treats null value grater than a non-null value (nulls last).
     */
    @Override
    public int compare(T o1, T o2) {
        try {
            if (!compareOnlyAccessibleFields && !comparinger.canAccess(o1)) {
                comparinger.setAccessible(true);
            }
            if (comparinger.canAccess(o1)) {
                if (comparinger.get(o1) == null){
                    return 1;
                }
                if (comparinger.get(o2) == null){
                    return -1;
                }
                return ((Comparable) comparinger.get(o1)).compareTo(comparinger.get(o2));
            } else {
                if (invokeGetter(comparinger.getName(), o1) == null){
                    return 1;
                }
                if (invokeGetter(comparinger.getName(), o2) == null){
                    return -1;
                }
                return invokeGetter(comparinger.getName(), o1).compareTo(invokeGetter(comparinger.getName(), o2));
            }
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
        return String.format("Random field comparator of class '%s' is comparing '%s'", targetType, comparinger);
    }
}
