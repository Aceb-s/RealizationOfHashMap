public class Main {
    public static void main(String[] args) {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();

        map.put("apple", 1);
        map.put("banana", 2);
        map.put("orange", 3);

        System.out.println("После добавления: " + map);
        {

            System.out.println("apple: " + map.get("apple"));
            System.out.println("banana: " + map.get("banana"));
            System.out.println("unknown: " + map.get("unknown"));

            map.put("apple", 10);
            System.out.println("После обновления apple: " + map);

            map.remove("banana");
            System.out.println("После удаления banana: " + map);
        }
    }
}