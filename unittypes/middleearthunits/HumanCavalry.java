package com.intellekta.generics.middleearth.unittypes.middleearthunits;

import com.intellekta.generics.middleearth.unittypes.AbstractCavalry;

public class HumanCavalry extends AbstractCavalry implements Human {
    public final int minPower() { return  Human.minPower; }
    public final int maxPower() { return Human.maxPower; }
    public HumanCavalry(String name) {
        super(name);
    }
}
