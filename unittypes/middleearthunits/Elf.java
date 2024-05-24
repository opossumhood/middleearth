package com.intellekta.generics.middleearth.unittypes.middleearthunits;

import com.intellekta.generics.middleearth.unittypes.*;

public class Elf extends AbstractUnit implements Infantry, MiddleEarthUnit {
    public final int minPower() { return  4; }
    public final int maxPower() { return  7; }
    public Elf(String name) {
        super(name);
    }
}
