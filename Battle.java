package com.intellekta.generics.middleearth;

import com.intellekta.generics.middleearth.unittypes.*;
import com.intellekta.generics.middleearth.unittypes.Cavalry;
import com.intellekta.generics.middleearth.unittypes.Unit;
import com.intellekta.generics.middleearth.unittypes.middleearthunits.*;
import com.intellekta.generics.middleearth.unittypes.mordorunits.*;

import java.util.ArrayList;

public class Battle {
    // задаем допустимые пределы размеров армии для случайной генерации
    // !!!(строго не меньше 2, чтоб влезли хотя бы один кавалерист и пехотинец)
    private static final int minArmy1Size = 12;
    private static final int maxArmy1Size = 15;
    private static final double armySizeDifference = 0.2;

    // генерация случайных имен для средиземья и мордора
    private static String generateRandomMEName() {
        String[] names = {"Lucius", "Centaurus", "Johny", "Maximus", "Dima", "Andrey",
                "Eminem", "Vladimir", "Snezhana", "Victorius"};
        return names[(int) (Math.random() * names.length)];
    }

    private static String generateRandomMordorName() {
        String[] names = {"Goga", "Guga", "Gega", "Zum-zum", "Hi-Ru", "Oo-ookus",
                "Joka", "Vladimir", "Terry", "Justin"};
        return names[(int) (Math.random() * names.length)];
    }

    // генерируем две армии (средиземья и мордора) и запускаем перегруженный метод
    public static void fight() {
        // генерируем случайные размеры армий в пределах костант
        int army1Size = (int) (Math.random() * (maxArmy1Size - minArmy1Size + 1)) + minArmy1Size;
        int minArmy2Size = (int) Math.ceil(army1Size * (1 - armySizeDifference));
        int maxArmy2Size = (int) (army1Size * (1 + armySizeDifference));
        int army2Size = (int) (Math.random() * (maxArmy2Size - minArmy2Size + 1)) + minArmy2Size;

        Army<MiddleEarthUnit> army1 = new Army<>();
        Army<MordorUnit> army2 = new Army<>();

        // каждой армии необходимо иметь хотя бы по одному юниту кавалерии и пехоты
        // добавляем средиземью волшебника с вероятностью в 50%
        boolean hasWizard = Math.random() > 0.5;
        if (hasWizard) {
            Wizard wizard = new Wizard("Gandalf");
            army1.recruit(wizard);
            // а если волшебника нет, добавляем случайного кавалериста
        } else recruitRandomMECavalry(army1);
        recruitRandomMEInfantry(army1);
        recruitRandomMordorCavalry(army2);
        recruitRandomMordorInfantry(army2);

        // Теперь, когда условие наличия кавалерий и пехоты выполнено,
        // заполняем армии случайными юнитами.
        // Шанс расписал на свое усмотрение после тестов на армиях в 10-100 юнитов :)
        for (int i = 2; i < army1Size; i++) {
            boolean recruitCavalry = Math.random() < 0.23;
            if (recruitCavalry) recruitRandomMECavalry(army1);
            else recruitRandomMEInfantry(army1);
        }
        for (int i = 2; i < army2Size; i++) {
            boolean recruitCavalry = Math.random() < 0.2;
            if (recruitCavalry) recruitRandomMordorCavalry(army2);
            else recruitRandomMordorInfantry(army2);
        }

        // запускаем перегруженный метод со свежесгенерированными армиями
        fight(army1, army2);
    }

    // Основной метод класса. Симуляция боя в три фазы:
    // между кавалериями, пехотой и выжившими армиями, если такие остались
    public static <T1 extends Unit, T2 extends Unit> void fight(Army<T1> a1, Army<T2> a2) {
        // переносим всех юнитов в локальные копии армий,
        // чтобы не менять внешние объекты
        Army<T1> army1 = new Army<>();
        Army<T2> army2 = new Army<>();
        for (T1 unit : a1.getArmy()) {
            army1.recruit(unit);
        }
        for (T2 unit : a2.getArmy()) {
            army2.recruit(unit);
        }

        printArmyStatus(army1);
        printArmyStatus(army2);

        round(army1.getCavalry(), army2.getCavalry(), 1, false);
        round(army1.getInfantry(), army2.getInfantry(), 2, false);
        // Проверяем, нужна ли третья фаза. Если одна из армий пуста, просто выводим итоги битвы
        if (army1.getArmy().isEmpty()) printRoundWinners(army2.getArmy(), 2, true);
        else if (army2.getArmy().isEmpty()) printRoundWinners(army1.getArmy(), 2, true);
        // если третья фаза нужна, проводим ее между двумя армиями целиком и выводим итоги битвы
        else round(army1.getArmy(), army2.getArmy(), 3, true);
    }

    // Одна фаза боя, между двумя коллекциями юнитов. Проводятся дуэли между
    // случайными юнитами до тех пор, пока одна из коллекций не опустеет
    private static <T extends Unit> void round(ArrayList<? extends T> division1, ArrayList<? extends T> division2,
                                               int roundNumber, boolean theEnd) {
        System.out.println("Phase " + roundNumber + ":");
        // пока хотя бы в одной коллекции остались юниты
        while (!division1.isEmpty() && !division2.isEmpty()) {
            // выбираем случайных юнитов
            int u1Number = (int) (Math.random() * division1.size());
            int u2Number = (int) (Math.random() * division2.size());
            AbstractUnit u1 = (AbstractUnit) division1.get(u1Number);
            AbstractUnit u2 = (AbstractUnit) division2.get(u2Number);

            duel(u1, u2);
            // если один из юнитов не пережил дуэль, удаляем его из коллекции
            if (!u1.isAlive()) division1.remove(u1Number);
            else if (!u2.isAlive()) division2.remove(u2Number);
        }
        // выводим в консоль информацию о победителе
        if (division1.isEmpty()) printRoundWinners(division2, roundNumber, theEnd);
        else printRoundWinners(division1, roundNumber, theEnd);
        System.out.println();
    }

    // два юнита обмениваются ударами
    private static void duel(AbstractUnit u1, AbstractUnit u2) {
        //выбираем первого атакующего с учетом преимущества для кавалерии
        boolean u1AttacksFirst;
        if (u1 instanceof Cavalry && !(u2 instanceof Cavalry)) u1AttacksFirst = true;
        else if (u2 instanceof Cavalry && !(u1 instanceof Cavalry)) u1AttacksFirst = false;
        else u1AttacksFirst = Math.random() > 0.5;

        if (u1AttacksFirst) {
            makeAStrike(u1, u2);
            if (u2.isAlive()) makeAStrike(u2, u1);
        } else {
            makeAStrike(u2, u1);
            if (u1.isAlive()) makeAStrike(u1, u2);
        }
    }

    // нанесение удара одним юнитом по другому и вывод в консоль информации об ударе
    private static void makeAStrike(AbstractUnit u1, AbstractUnit u2) {
        System.out.print(u1 + " strikes " + u2);
        u1.strike(u2);
        System.out.println(" and" + (u2.isAlive() ? " does not kill" : " kills") + " him");
    }

    // генерация и рекрутирование случайных юнитов различных типов и фракций
    private static void recruitRandomMECavalry(Army<MiddleEarthUnit> army) {
        int randomUnit = (int) (Math.random() * 2);
        MiddleEarthUnit unit;
        unit = switch (randomUnit) {
            case 0 -> new HumanCavalry(generateRandomMEName());
            case 1 -> new Rohhirim(generateRandomMEName());
            default -> null;
        };
        army.recruit(unit);
    }
    private static void recruitRandomMEInfantry(Army<MiddleEarthUnit> army) {
        int randomUnit = (int) (Math.random() * 3);
        MiddleEarthUnit unit;
        unit = switch (randomUnit) {
            case 0 -> new HumanInfantry(generateRandomMEName());
            case 1 -> new Elf(generateRandomMEName());
            case 2 -> new WoodenElf(generateRandomMEName());
            default -> null;
        };
        army.recruit(unit);
    }
    private static void recruitRandomMordorCavalry(Army<MordorUnit> army) {
        army.recruit(new OrcCavalry(generateRandomMordorName()));
    }
    private static void recruitRandomMordorInfantry(Army<MordorUnit> army) {
        int randomUnit = (int) (Math.random() * 4);
        MordorUnit unit;
        unit = switch (randomUnit) {
            case 0 -> new OrcInfantry(generateRandomMordorName());
            case 1 -> new Troll(generateRandomMordorName());
            case 2 -> new Goblin(generateRandomMordorName());
            case 3 -> new UrukHai(generateRandomMordorName());
            default -> null;
        };
        army.recruit(unit);
    }

    // вывод в консоль списка юнитов в армии
    private static <T extends Unit> void printArmyStatus(Army<T> army) {
        Unit u = army.getRandomUnit();
        System.out.println("Army of " +
                (u instanceof MiddleEarthUnit ? "Middle Earth" : "Mordor") + " consists of:");
        army.print();
    }

    // Вывод в консоль списка юнитов в коллекции
    // в формате итога фазы № roundNumber, если theEnd == false,
    // или в формате итогов битвы в целом, если theEnd == true
    private static <T extends Unit> void printRoundWinners(ArrayList<? extends T> list, int roundNumber, boolean theEnd) {
        Unit winner = list.get(0);
        System.out.println("\nArmy of " +
                (winner instanceof MiddleEarthUnit ? "Middle Earth" : "Mordor") +
                " has won the " + (theEnd ? "battle" : (roundNumber + " phase")) + ". The winners list:");
        for (T t : list) {
            System.out.println(t);
        }
    }
}
