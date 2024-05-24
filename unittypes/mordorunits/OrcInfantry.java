package com.intellekta.generics.middleearth.unittypes.mordorunits;

import com.intellekta.generics.middleearth.unittypes.*;

public class OrcInfantry extends AbstractUnit implements Infantry, Orc {
    public int minPower() { return Orc.minPower; }
    public int maxPower() { return Orc.maxPower; }
    public OrcInfantry(String name) { super(name); }
}
