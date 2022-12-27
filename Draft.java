import java.util.ArrayList;

public class Draft {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        names.add("A");
        names.add("B");
        names.add("C");
        System.out.println("Original list: ");
        for (String a : names) {
            System.out.println(a);
        }
        ArrayList<String> names2 = new ArrayList<>();
        names2 = names;
        System.out.println("Copied List: ");
        for (String a : names2) {
            System.out.println(a);
        }
    }
}
