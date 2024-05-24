package com.intellekta.generics.middleearth.unittypes.mordorunits;

import com.intellekta.generics.middleearth.unittypes.*;

public class OrcCavalry extends AbstractCavalry implements Orc {
    public int minPower() { return Orc.minPower; }
    public int maxPower() { return Orc.maxPower; }
    public OrcCavalry(String name) { super(name); }
}
