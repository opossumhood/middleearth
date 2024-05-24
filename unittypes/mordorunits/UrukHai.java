package com.intellekta.generics.middleearth.unittypes.mordorunits;

import com.intellekta.generics.middleearth.unittypes.*;

public class UrukHai extends AbstractUnit implements Infantry, Orc {
    public int minPower() { return 10; }
    public int maxPower() { return 12; }
    public UrukHai(String name) { super(name); }
}
