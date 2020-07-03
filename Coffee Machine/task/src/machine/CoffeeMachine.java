package machine;

import java.util.Scanner;

enum CoffeeType {
    ESPRESSO {
        @Override
        public int getWaterNeeded() {
            return 250;
        }

        @Override
        public int getMilkNeeded() {
            return 0;
        }

        @Override
        public int getCoffeeNeeded() {
            return 16;
        }

        @Override
        public int getPrice() {
            return 4;
        }

    }, LATTE {
        @Override
        public int getWaterNeeded() {
            return 350;
        }

        @Override
        public int getMilkNeeded() {
            return 75;
        }

        @Override
        public int getCoffeeNeeded() {
            return 20;
        }

        @Override
        public int getPrice() {
            return 7;
        }

    }, CAPPUCCINO {
        @Override
        public int getWaterNeeded() {
            return 200;
        }

        @Override
        public int getMilkNeeded() {
            return 100;
        }

        @Override
        public int getCoffeeNeeded() {
            return 12;
        }

        @Override
        public int getPrice() {
            return 6;
        }
    };

    public abstract int getWaterNeeded();

    public abstract int getMilkNeeded();

    public abstract int getCoffeeNeeded();

    public abstract int getPrice();
}

public class CoffeeMachine {

    private static final String BUY = "buy";
    private static final String FILL = "fill";
    private static final String TAKE = "take";
    private static final String EXIT = "exit";
    private static final String REMAINING = "remaining";
    private static final String BACK = "back";
    private int waterReserves;
    private int milkReserves;
    private int coffeeReserves;
    private int cupReserves;
    private int moneyReserves;
    private boolean turnedOn = true;

    public CoffeeMachine(int waterReserves, int milkReserves, int coffeeReserves, int cupReserves, int moneyReserves) {
        this.waterReserves = waterReserves;
        this.milkReserves = milkReserves;
        this.coffeeReserves = coffeeReserves;
        this.cupReserves = cupReserves;
        this.moneyReserves = moneyReserves;

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachine machine = new CoffeeMachine(400, 540, 120, 9, 550);

        while (machine.turnedOn) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            machine.processAction(scanner);
        }
    }

    private void processAction(Scanner scanner) {
        switch (scanner.nextLine()) {
            case BUY:
                processBuyAction(scanner);
                break;
            case FILL:
                processFillAction(scanner);
                break;
            case TAKE:
                processTakeAction(scanner);
                break;
            case REMAINING:
                processRemainingAction();
                break;
            case EXIT:
                turnOffMachine();
                break;
        }
    }

    private void turnOffMachine() {
        this.turnedOn = false;
    }

    private void processRemainingAction() {
        printStatus();
    }

    private void processTakeAction(Scanner scanner) {
        System.out.println("I gave you " + this.moneyReserves);
        moneyReserves = 0;
    }

    private void prepareCoffee(CoffeeType coffeeType) {
        if (isEnoughResources(coffeeType)) {
            this.waterReserves -= coffeeType.getWaterNeeded();
            this.milkReserves -= coffeeType.getMilkNeeded();
            this.coffeeReserves -= coffeeType.getCoffeeNeeded();
            this.cupReserves -= 1;
            this.moneyReserves += coffeeType.getPrice();
        } else {
            System.out.println("Sorry, not enough resources!");
        }

    }

    private boolean isEnoughResources(CoffeeType coffeeType) {
        return this.waterReserves  >=  coffeeType.getWaterNeeded()  &&
               this.milkReserves   >=  coffeeType.getMilkNeeded()   &&
               this.coffeeReserves >=  coffeeType.getCoffeeNeeded() &&
               this.cupReserves    >=  1;
    }


    private void processFillAction(Scanner scanner) {
        System.out.println("Write how many ml of water do you want to add: ");
        this.waterReserves += scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add: ");
        this.milkReserves += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add: ");
        this.coffeeReserves += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add: ");
        this.cupReserves += scanner.nextInt();
    }

    private void processBuyAction(Scanner scanner) {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String coffeeChoice = scanner.next();
        switch (coffeeChoice) {
            case "1":
                prepareCoffee(CoffeeType.ESPRESSO);
                break;
            case "2":
                prepareCoffee(CoffeeType.LATTE);
                break;
            case "3":
                prepareCoffee(CoffeeType.CAPPUCCINO);
                break;
            case "back":
                break;
        }
    }

    private void printStatus() {
        System.out.println("The coffee machine has:");
        System.out.println(waterReserves + " of water");
        System.out.println(milkReserves + " of milk");
        System.out.println(coffeeReserves + " of coffee beans");
        System.out.println(cupReserves + " of disposable cups");
        System.out.println(moneyReserves + " of money");
    }
}
