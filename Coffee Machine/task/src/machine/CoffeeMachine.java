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

enum ActionType {
    BUY {
        @Override
        public void performAction(CoffeeMachine machine, Scanner scanner) {
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
            String coffeeChoice = scanner.next();
            switch (coffeeChoice) {
                case "1":
                    machine.prepareCoffee(CoffeeType.ESPRESSO);
                    break;
                case "2":
                    machine.prepareCoffee(CoffeeType.LATTE);
                    break;
                case "3":
                    machine.prepareCoffee(CoffeeType.CAPPUCCINO);
                    break;
                case "back":
                    break;
            }
        }
    }, FILL {
        @Override
        public void performAction(CoffeeMachine machine, Scanner scanner) {
            System.out.println("Write how many ml of water do you want to add: ");
            machine.setWaterReserves(machine.getWaterReserves() + scanner.nextInt());
            System.out.println("Write how many ml of milk do you want to add: ");
            machine.setMilkReserves(machine.getMilkReserves() + scanner.nextInt());
            System.out.println("Write how many grams of coffee beans do you want to add: ");
            machine.setCoffeeReserves(machine.getCoffeeReserves() + scanner.nextInt());
            System.out.println("Write how many disposable cups of coffee do you want to add: ");
            machine.setCupReserves(machine.getCupReserves() + scanner.nextInt());
        }
    }, TAKE {
        @Override
        public void performAction(CoffeeMachine machine, Scanner scanner) {
            System.out.println("I gave you " + machine.getMoneyReserves());
            machine.setMoneyReserves(0);
        }
    }, EXIT {
        @Override
        public void performAction(CoffeeMachine machine, Scanner scanner) {
            machine.turnOffMachine();
        }
    }, REMAINING {
        @Override
        public void performAction(CoffeeMachine machine, Scanner scanner) {
            System.out.println("The coffee machine has:");
            System.out.println(machine.getWaterReserves() + " of water");
            System.out.println(machine.getMilkReserves() + " of milk");
            System.out.println(machine.getCoffeeReserves() + " of coffee beans");
            System.out.println(machine.getCupReserves() + " of disposable cups");
            System.out.println(machine.getMoneyReserves() + " of money");
        }
    };

    public abstract void performAction(CoffeeMachine machine, Scanner scanner);
}

public class CoffeeMachine {

    private static final String BUY = "buy";
    private static final String FILL = "fill";
    private static final String TAKE = "take";
    private static final String EXIT = "exit";
    private static final String REMAINING = "remaining";

    private int waterReserves;
    private int milkReserves;
    private int coffeeReserves;
    private int cupReserves;
    private int moneyReserves;
    private boolean turnedOn = true;

    public void setWaterReserves(int waterReserves) {
        this.waterReserves = waterReserves;
    }

    public void setMilkReserves(int milkReserves) {
        this.milkReserves = milkReserves;
    }

    public void setCoffeeReserves(int coffeeReserves) {
        this.coffeeReserves = coffeeReserves;
    }

    public void setCupReserves(int cupReserves) {
        this.cupReserves = cupReserves;
    }

    public void setMoneyReserves(int moneyReserves) {
        this.moneyReserves = moneyReserves;
    }
    
    public int getWaterReserves() {
        return waterReserves;
    }

    public int getMilkReserves() {
        return milkReserves;
    }

    public int getCoffeeReserves() {
        return coffeeReserves;
    }

    public int getCupReserves() {
        return cupReserves;
    }

    public int getMoneyReserves() {
        return moneyReserves;
    }

    public boolean isTurnedOn() {
        return turnedOn;
    }

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

        while (machine.isTurnedOn()) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            machine.processAction(scanner);
        }
    }

    private void processAction(Scanner scanner) {
        switch (scanner.nextLine()) {
            case BUY:
                ActionType.BUY.performAction(this, scanner);
                break;
            case FILL:
                ActionType.FILL.performAction(this, scanner);
                break;
            case TAKE:
                ActionType.TAKE.performAction(this, scanner);
                break;
            case REMAINING:
                ActionType.REMAINING.performAction(this, scanner);
                break;
            case EXIT:
                ActionType.EXIT.performAction(this, scanner);
                break;
        }
    }

    public void turnOffMachine() {
        this.turnedOn = false;
    }

    public void prepareCoffee(CoffeeType coffeeType) {
        if (hasEnoughResources(coffeeType)) {
            this.waterReserves -= coffeeType.getWaterNeeded();
            this.milkReserves -= coffeeType.getMilkNeeded();
            this.coffeeReserves -= coffeeType.getCoffeeNeeded();
            this.cupReserves -= 1;
            this.moneyReserves += coffeeType.getPrice();
        } else {
            System.out.println("Sorry, not enough resources!");
        }
    }

    private boolean hasEnoughResources(CoffeeType coffeeType) {
        return this.waterReserves >= coffeeType.getWaterNeeded() &&
                this.milkReserves >= coffeeType.getMilkNeeded() &&
                this.coffeeReserves >= coffeeType.getCoffeeNeeded() &&
                this.cupReserves >= 1;
    }

}
