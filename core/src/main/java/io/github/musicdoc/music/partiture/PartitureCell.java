package io.github.musicdoc.music.partiture;

import io.github.musicdoc.music.harmony.Chord;
import io.github.musicdoc.music.harmony.ChordContainer;
import io.github.musicdoc.music.transpose.AbstractTransposable;

public abstract class PartitureCell<SELF extends PartitureCell<SELF>> extends AbstractTransposable<SELF> {

    protected ChordContainer chordContainer;

    public ChordContainer getChordContainer() {
        return this.chordContainer;
    }

    public void setChordContainer(ChordContainer chordContainer) {
        this.chordContainer = chordContainer;
    }

    /**
     * @return {@link ChordContainer#getChord()}
     */
    public Chord getChord() {
        if (this.chordContainer == null) {
            return null;
        }
        return this.chordContainer.getChord();
    }

    public void setChord(Chord chord) {
        this.chordContainer = new ChordContainer(chord);
    }

    /**
     * @return {@link ChordContainer#toString()}
     */
    public String getChordString() {
        if (this.chordContainer == null) {
            return "";
        }
        return this.chordContainer.toString();
    }
}
