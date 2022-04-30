package io.github.musicdoc.music.stave;

import io.github.musicdoc.music.harmony.MusicalKey;

class StavePropertyKey extends StaveProperty<MusicalKey> {

    public static final StavePropertyKey INSTANCE = new StavePropertyKey();

    public StavePropertyKey() {
        super("key");
    }

    @Override
    public MusicalKey get(Stave stave) {
        if (stave == null) {
            return null;
        }
        return stave.getKey();
    }

    @Override
    public void set(Stave stave, MusicalKey key) {
        if (stave != null) {
            stave.setKey(key);
        }
    }
}
