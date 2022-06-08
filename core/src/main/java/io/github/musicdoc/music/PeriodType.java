package io.github.musicdoc.music;

/**
 * A {@link PeriodType} is used for objects that may range for a period, what means the range over
 * multiple columns of a {@link io.github.musicdoc.music.partiture.Partiture}.
 *
 * @see io.github.musicdoc.music.decoration.MusicalDecoration#getPeriod()
 */
public enum PeriodType {

    /** Indicates the start of something. */
    START,

    /** Indicates the end of something. */
    END

}
