package io.github.musicdoc.format;

import io.github.musicdoc.music.harmony.Chord;
import io.github.musicdoc.music.partiture.voice.PartitureVoiceCell;
import io.github.musicdoc.music.rythm.value.MusicalValue;
import io.github.musicdoc.music.rythm.value.ValuedItem;
import io.github.musicdoc.music.stave.Stave;

import org.assertj.core.api.Assertions;

public abstract class SongFormatTest extends Assertions {

    protected void checkCell(PartitureVoiceCell cell, ValuedItem<?> item) {
        checkCell(cell, "", null, item);
    }

    protected void checkCell(PartitureVoiceCell cell, String lyric, ValuedItem<?> item) {
        checkCell(cell, lyric, null, item);
    }

    protected void checkCell(PartitureVoiceCell cell, String lyric, Chord chord) {
        checkCell(cell, lyric, chord, null);
    }

    protected void checkCell(PartitureVoiceCell cell, String lyric, Chord chord, ValuedItem<?> item) {
        checkCell(cell, lyric, chord, item, null);
    }

    protected void checkCell(PartitureVoiceCell cell, String lyric, Chord chord, ValuedItem<?> item, Stave stave) {
        assertThat(cell).isNotNull();
        assertThat(cell.getLyric()).isEqualTo(lyric);
        assertThat(cell.getChord()).isEqualTo(chord);
        assertThat(cell.getItem()).isEqualTo(item);
        assertThat(cell.getStave()).isEqualTo(stave);
    }
}
