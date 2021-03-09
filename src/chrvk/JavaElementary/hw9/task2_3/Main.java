//2*)Реализовать интерфейс MyMap и его имплементацию MyTreeMap - аналог Map<String, String>
//3*) Вместо 2* сделать MyMap<K,V>
package chrvk.JavaElementary.hw9.task2_3;

public class Main {
    public static void main(String[] args) {
        MyMap<String, Integer> map = new MyHashMap<>();
        map.put("Smith", 30);
        map.put("Anderson", 31);
        map.put("Lewis", 29);
        map.put("Cook", 29);
        map.put("Tom", 21);
        map.put("Mark", 21);
        map.put("Smith", 65);
        map.put("William", 21);

        System.out.println("Entries in map:" + map);

        System.out.println("The age of Lewis is " +
                map.get("Lewis"));

        System.out.println("The age of William is " +
                map.get("William"));

        System.out.println("Is Smith in the map? " +
                map.containsKey("Smith"));

        System.out.println("Is Jay in the map? " +
                map.containsKey("Jay"));

        System.out.println("Is age 33 in the map? " +
                map.containsValue(33));

        System.out.println("Is age 31 in the map? " +
                map.containsValue(31));

        System.out.println("Keys in map: ");
        for (String key : map.keySet()) {
            System.out.println(key + " ");
        }
        System.out.println();

        System.out.println("Values in map: ");
        for (int value : map.values()) {
            System.out.println(value + " ");
        }
        System.out.println();

        map.remove("Smith");
        System.out.println("Entries in map" + map);

        map.clear();
        System.out.println("Entries in map" + map);
    }
}
