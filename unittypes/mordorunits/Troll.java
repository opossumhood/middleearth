package com.intellekta.generics.middleearth.unittypes.mordorunits;

import com.intellekta.generics.middleearth.unittypes.*;

public class Troll extends AbstractUnit implements Infantry, MordorUnit {
    public int minPower() { return 11; }
    public int maxPower() { return 15; }
    public Troll(String name) { super(name); }
}
