package com.intellekta.generics.middleearth.unittypes.middleearthunits;

import com.intellekta.generics.middleearth.unittypes.*;

public class WoodenElf extends AbstractUnit implements Infantry, MiddleEarthUnit {
    public final int minPower() { return  6; }
    public final int maxPower() { return 6; }
    public WoodenElf(String name) {
        super(name);
    }
}
