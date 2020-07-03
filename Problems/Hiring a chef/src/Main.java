//put imports you need here

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String first = scanner.next();
        String second = scanner.next();
        String third = scanner.next();
        String fourth = scanner.next();
        String fifth = scanner.next();

        System.out.printf("The form for %s is completed. We will contact you if we need a chef that cooks %s dishes.", first, fifth);

    }
}