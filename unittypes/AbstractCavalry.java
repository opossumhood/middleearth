package com.intellekta.generics.middleearth.unittypes;

import com.intellekta.generics.middleearth.unittypes.middleearthunits.MiddleEarthUnit;

public abstract class AbstractCavalry extends AbstractUnit implements Cavalry {
    private final AbstractUnit rideable;

    @Override
    protected int getPower() {
        return super.getPower() + rideable.getPower();
    }

    @Override
    protected void takeDamage(int strikePower) {
        if (rideable.isAlive()) rideable.takeDamage(strikePower);
        else super.takeDamage(strikePower);
    }

    protected AbstractCavalry(String name) {
        super(name);
        if (this instanceof MiddleEarthUnit) rideable = new Horse();
        else rideable = new Warg();
    }
}
