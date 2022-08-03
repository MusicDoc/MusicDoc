package io.github.musicdoc.decoration;

import io.github.musicdoc.PeriodType;
import io.github.musicdoc.glyphs.smufl.SmuflGlyphsBeams;

/**
 * A tie is a curved line above or below two notes of the same pitch indicating to play them as a single note.
 */
public final class TieDecoration extends MusicalDecoration {

  /**
   * Tie (-). This decoration is annotated to the starting
   * {@link android.provider.ContactsContract.CommonDataKinds.Note} and automatically ends at the next
   * {@link android.provider.ContactsContract.CommonDataKinds.Note} that has to have the same
   * {@link io.github.musicdoc.tone.Tone}. To connect multiple
   * {@link android.provider.ContactsContract.CommonDataKinds.Note}s, simply add a tie to the next
   * {@link android.provider.ContactsContract.CommonDataKinds.Note} as well.
   */
  public static final TieDecoration TIE = create("-", SmuflGlyphsBeams.TIE_START + SmuflGlyphsBeams.TIE_END,
      SmuflGlyphsBeams.TIE_START + SmuflGlyphsBeams.TIE_END, new String[0]);

  private TieDecoration(String name, PeriodType period, String unicode, String smufl, MusicalDecoration reference) {

    super(name, period, unicode, smufl, reference);
  }

  @Override
  public MusicalDecorationPosition getPosition() {

    return MusicalDecorationPosition.NOTEHEAD;
  }

  @Override
  protected TieDecoration create(String newName) {

    return new TieDecoration(newName, this.period, this.glyphsUnicode, this.glyphsSmufl, this.reference);
  }

  private static TieDecoration create(String name, String unicode, String smufl, String... altNames) {

    TieDecoration decoration = new TieDecoration(name, null, unicode, smufl, null);
    decoration.alias(altNames);
    return decoration;
  }

  static void load() {

  }
}