package com.intellekta.generics.middleearth.unittypes.middleearthunits;

import com.intellekta.generics.middleearth.unittypes.*;

public class HumanInfantry extends AbstractUnit implements Infantry, Human {
    public final int minPower() { return Human.minPower; }
    public final int maxPower() { return Human.maxPower; }
    public HumanInfantry(String name) {
        super(name);
    }
}
