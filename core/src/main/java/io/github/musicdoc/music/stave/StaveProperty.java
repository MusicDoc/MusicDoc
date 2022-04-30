package io.github.musicdoc.music.stave;

import io.github.musicdoc.property.PropertyAccessor;

abstract class StaveProperty<T> implements PropertyAccessor<Stave, T> {

    private final String name;

    public StaveProperty(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
