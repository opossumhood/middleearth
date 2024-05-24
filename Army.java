package com.intellekta.generics.middleearth;

import com.intellekta.generics.middleearth.unittypes.*;

import java.util.ArrayList;

public class Army<T extends Unit> {
    private final ArrayList<T> cavalry = new ArrayList<>();
    private final ArrayList<T> infantry = new ArrayList<>();

    public ArrayList<T> getCavalry() {
        return cavalry;
    }

    public ArrayList<T> getInfantry() {
        return infantry;
    }

    public ArrayList<T> getArmy() {
        ArrayList<T> myArmy = new ArrayList<>();
        myArmy.addAll(cavalry);
        myArmy.addAll(infantry);
        return myArmy;
    }

    public boolean recruit(T unit) {
        if (unit instanceof Cavalry) return cavalry.add(unit);
        else if (unit instanceof Infantry) return infantry.add(unit);
        else return false;
    }

    public void print() {
        ArrayList<T> army = getArmy();
        for (T unit : army) System.out.println(unit.toString());
        System.out.println();
    }

    public boolean release(T unit) {
        if (unit instanceof Cavalry) return cavalry.remove(unit);
        else if (unit instanceof Infantry) return infantry.remove(unit);
        else return false;
    }

    public T getRandomUnit() {
        if (getArmy().isEmpty()) return null;
        int i = (int) (Math.random() * getArmy().size());
        return getArmy().get(i);
    }

    public T getRandomUnit(T unit) {
        if (unit instanceof Cavalry) {
                if (cavalry.isEmpty()) return null;
                int i = (int) (Math.random() * cavalry.size());
                return cavalry.get(i);
            } else if (unit instanceof Infantry) {
                if (infantry.isEmpty()) return null;
                int i = (int) (Math.random() * infantry.size());
                return infantry.get(i);
            } else return null;
    }
}
