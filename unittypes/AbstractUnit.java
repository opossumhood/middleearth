package com.intellekta.generics.middleearth.unittypes;

public abstract class AbstractUnit implements Unit {
    private final String name;
    private int power;

    protected int getPower() {
        return power;
    }

    protected AbstractUnit(String name) {
        if (name == null || name.isEmpty()) this.name = super.toString();
        else this.name = name;
        this.power = (int) (Math.random() * (maxPower() - minPower() + 1)) + minPower();
    }

    public <T extends AbstractUnit> void strike(T unit) {
        if (unit != null) {
            unit.takeDamage(getPower());
        }
    }

    protected void takeDamage(int strikePower) {
        if (strikePower <= 0) return;
        power -= strikePower;
        power = Math.max(power, 0);
    }

    public boolean isAlive() {
        return power > 0;
    }

    public String toString() {
        return String.format("%s %s has power %d", this.getClass().getSimpleName(), name, getPower());
    }
}
