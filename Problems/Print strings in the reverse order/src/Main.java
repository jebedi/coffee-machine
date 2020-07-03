//put imports you need here

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        List<String> coll = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            coll.add(scanner.nextLine());
        }

        for (int i = 2; i >= 0; i--) {
            System.out.println(coll.get(i));;
        }
    }
}