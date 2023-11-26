package org.cu.adta.group21.provenancedetection.utility;

import org.cu.adta.group21.provenancedetection.model.Products;
import org.cu.adta.group21.provenancedetection.model.Regions;
import org.cu.adta.group21.provenancedetection.model.Routes;
import org.cu.adta.group21.provenancedetection.model.Suppliers;

import java.lang.reflect.Field;
import java.util.*;

public class Util {
   public static<T,U> Map<String, String> performJoin(List<T> list1, List<U> list2, String column1, String column2, List<String> columNames) {
       Map<String, String> provenanceMap = new LinkedHashMap<>();
       StringBuilder keyBuilder = new StringBuilder();
       for (int i = 0; i < list1.size(); i++) {
           T obj1 = list1.get(i);
           StringBuilder valueBuilder = new StringBuilder();
           for (int j = 0; j < list2.size(); j++) {
               U obj2 = list2.get(j);
               try {
                   Field field1 = obj1.getClass().getDeclaredField(column1);
                   Field field2 = obj2.getClass().getDeclaredField(column2);


                   field1.setAccessible(true);
                   field2.setAccessible(true);

                   if (field1.get(obj1).equals(field2.get(obj2))) {
                       keyBuilder = new StringBuilder();
                       for (String s : columNames) {
                           Field f = Arrays.stream(obj1.getClass().getFields()).anyMatch(fields->fields.getName().equals(s)) ? obj1.getClass().getDeclaredField(s)
                                   : obj2.getClass().getDeclaredField(s.trim());
                           if(f!=null) {
                               f.setAccessible(true);
                               keyBuilder.append(f.get(obj1)).append(" ");
                           }
                       }
                       keyBuilder.toString().trim();
                       Field ann1 = obj1.getClass().getField("ann");
                       Field ann2 = obj2.getClass().getField("ann");
                       valueBuilder.append(ann1.get(obj1)).append(" + ").append(ann2.get(obj2)).append(" ");

                       if (provenanceMap.containsKey(keyBuilder.toString()) ) {
                           provenanceMap.put(keyBuilder.toString(), provenanceMap.get(keyBuilder.toString()) + " . " + valueBuilder.toString().trim());
                       } else {
                           provenanceMap.put(keyBuilder.toString(), valueBuilder.toString());
                       }


                   }

               } catch (NoSuchFieldException e) {
                   throw new RuntimeException(e);
               } catch (IllegalAccessException e) {
                   throw new RuntimeException(e);
               }
           }

       }
       return provenanceMap;
   }
}
