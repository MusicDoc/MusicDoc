package io.github.musicdoc.rhythm.value.variation;

import io.github.musicdoc.glyphs.MusicalGlyphsContext;
import io.github.musicdoc.glyphs.smufl.SmuflGlyphsNote;
import io.github.musicdoc.rhythm.value.MusicalValue;

/**
 * {@link MusicalValueVariation} for a punctuation.
 */
public class Punctuation extends MusicalValueVariation {

  /**
   * Increases the {@link MusicalValue} by adding half of its value. Visualized as a single dot right to the musical
   * symbol.
   */
  public static final Punctuation P1 = new Punctuation(3, 2, ".");

  /**
   * Like {@link #P1} but additionally adding a quarter of the value. Visualized as a double dot right to the musical
   * symbol.
   */
  public static final Punctuation P2 = new Punctuation(7, 4, "..");

  /**
   * Like {@link #P2} but additionally adding an eight of the value. Visualized as a triple dot right to the musical
   * symbol.<br>
   * ATTENTION: This is really uncommon in regular music and should be avoided. Please use with care as many people do
   * not even know the semantics of a {@link #P2 double punctuation}.
   */
  public static final Punctuation P3 = new Punctuation(15, 8, "...");

  private Punctuation(int beats, int unit, String text) {

    super(beats, unit, text);
    makeImmutable();
  }

  @Override
  public String getGlyphs(MusicalGlyphsContext context) {

    if (context.isEnforceUnicode()) {
      return toString();
    } else if (this == P1) {
      return SmuflGlyphsNote.AUGMENTATION_DOT;
    } else if (this == P2) {
      return SmuflGlyphsNote.AUGMENTATION_DOT + SmuflGlyphsNote.AUGMENTATION_DOT;
    } else if (this == P3) {
      return SmuflGlyphsNote.AUGMENTATION_DOT + SmuflGlyphsNote.AUGMENTATION_DOT + SmuflGlyphsNote.AUGMENTATION_DOT;
    }
    throw new IllegalStateException();
  }

  /**
   * @return the number of punctiations (dots behind the notehead). Will return {@code 1} for {@link #P1}, {@code 2} for
   *         {@link #P2}, {@code 3} for {@link #P3}.
   */
  @Override
  public int getPunctuationCount() {

    if (this == P1) {
      return 1;
    } else if (this == P2) {
      return 2;
    } else if (this == P3) {
      return 3;
    }
    throw new IllegalStateException();
  }

  /**
   * @param punctuation the {@link #getPunctuationCount() punctuation count}.
   * @return the according {@link MusicalValueVariation} or {@code null} if no such variation exists.
   */
  public static MusicalValueVariation of(int punctuation) {

    switch (punctuation) {
      case 0:
        return NONE;
      case 1:
        return P1;
      case 2:
        return P2;
      case 3:
        return P3;
    }
    return null;
  }
}
