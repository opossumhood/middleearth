package com.intellekta.generics.middleearth.unittypes.mordorunits;

import com.intellekta.generics.middleearth.unittypes.*;

public class Goblin extends AbstractUnit implements Infantry, MordorUnit {
    public int minPower() { return 2; }
    public int maxPower() { return 5; }
    public Goblin(String name) { super(name); }
}
