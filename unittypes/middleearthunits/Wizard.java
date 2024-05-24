package com.intellekta.generics.middleearth.unittypes.middleearthunits;

import com.intellekta.generics.middleearth.unittypes.*;

public class Wizard extends AbstractCavalry implements MiddleEarthUnit {
    public final int minPower() { return 20; }
    public final int maxPower() { return 20; }
    public Wizard(String name) {
        super(name);
    }
}
