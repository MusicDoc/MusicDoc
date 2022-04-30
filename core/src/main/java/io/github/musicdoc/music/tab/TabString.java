package io.github.musicdoc.music.tab;

import io.github.musicdoc.music.tone.Tone;
import io.github.musicdoc.music.tone.ToneNameStyle;

/**
 * Represents a single string of a musical instrument like a guitar in a {@link Tab}.
 *
 * @see Tab#getStrings()
 */
public class TabString {

    private final Tone tone;

    public TabString(Tone tone) {
        this.tone = tone;
    }

    public void format(StringBuilder sb, ToneNameStyle style) {

        sb.append(this.tone.getName(style));
        sb.append("|-");
    }

    /**
     * @return the base {@link Tone} of the string if no {@link TabNote#getFret() fret} is pressed.
     */
    public Tone getTone() {
        return this.tone;
    }

}
