package org.example;

import java.util.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        List<String> names = new ArrayList<>();
        names.add("johnny");
        names.add("sally");
        names.add("teddy");
        names.add("molly");
        System.out.println(names);
        String[] friends = new String[5];
        friends[0] = "johnny";
        friends[1] = "sally";
        System.out.println(names.get(0));
        System.out.println(friends[0]);

        Map<String,String> states = new HashMap<>();
        states.put("GA","Georgia");
        states.put("ME", "Maine");
        states.put("AK", "Alaska");
        states.put("MS", "Mississippi");
        states.put("HI", "Hawaii");
        System.out.println( states.get("AK") );
        System.out.println( states.get("GA") );
        System.out.println( states.get("KR") );
        states.put("GA", "The great commonwealth of Georgia");
        System.out.println( states.get("GA") );
        // give me a hashmap where the keys are your 9000 number (int)
        // and the value is the name of the student (string)
        // insert three (k,v) pair to the map
        HashMap<Integer,String> ggc_students = new HashMap<>();
        ggc_students.put(900012345,"Cool Person");
        ggc_students.put(900023232,"Awesome Person");
        ggc_students.put(900087234,"Legendary Person");
        System.out.println(ggc_students.containsKey(9000));
        System.out.println(ggc_students.get(900012345));
        // syntax is not very friendly
        for (Map.Entry<Integer,String> entry: ggc_students.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        Set<String> heroes = new HashSet<>();
        heroes.add("batman");
        heroes.add("superman");
        heroes.add("wonderwoman");
        heroes.add("cyborg");
        heroes.add("batman");
        System.out.println(heroes);
        // syntax is friendlier
        for(int key: ggc_students.keySet()) {
            System.out.println(key + " " + ggc_students.get(key));
        }

    }


}
