package com.intellekta.generics.middleearth.unittypes;

public interface Cavalry {
    class Horse extends AbstractUnit {
        public final int minPower() { return 4; }
        public final int maxPower() { return 5; }
        Horse() {
            super(null);
        }
    }

    class Warg extends AbstractUnit {

        public final int minPower() { return 4; }
        public final int maxPower() { return 7; }
        Warg() {
            super(null);
        }
    }
}
