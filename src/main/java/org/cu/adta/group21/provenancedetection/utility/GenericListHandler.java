package org.cu.adta.group21.provenancedetection.utility;

import org.cu.adta.group21.provenancedetection.model.Suppliers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GenericListHandler<T, E> {
    private List<T> list1;
    private List<E> list2;

    private String colName1;

    private String colName2;

    public GenericListHandler(List<T> list1, List<E> list2, String colName1, String colName2) {
        this.list1 = list1;
        this.list2 = list2;
        this.colName1 = colName1;
        this.colName2 = colName2;
    }

    public List<Object[]> performJoin() {
        List<Object[]> result = new ArrayList<>();
        for (T obj1 : list1) {
            for (E obj2 : list2) {
                try {
                    Field field1 = obj1.getClass().getDeclaredField(colName1);
                    field1.setAccessible(true);
                    Object value1 = field1.get(obj1);

                    Field field2 = obj2.getClass().getDeclaredField(colName2);
                    field2.setAccessible(true);
                    Object value2 = field2.get(obj2);

                    if (value1.equals(value2)) {
                        Object[] joinedResult = {obj1, obj2};
                        result.add(joinedResult);
                    }

                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }

}
