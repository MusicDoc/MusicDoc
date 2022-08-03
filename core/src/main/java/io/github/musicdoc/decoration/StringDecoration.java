package io.github.musicdoc.decoration;

import io.github.musicdoc.PeriodType;
import io.github.musicdoc.glyphs.smufl.SmuflGlyphsString;
import io.github.musicdoc.glyphs.unicode.UnicodeGlyphsInstrumentation;

/**
 * {@link MusicalDecoration} for techniques of string instruments.
 */
public final class StringDecoration extends MusicalDecoration {

  /** Bow up. */
  public static final StringDecoration UP_BOW = create("u", UnicodeGlyphsInstrumentation.COMBINING_UP_BOW,
      SmuflGlyphsString.UP_BOW, "upbow");

  /** Bow down. */
  public static final StringDecoration DOWN_BOW = create("v", UnicodeGlyphsInstrumentation.COMBINING_DOWN_BOW,
      SmuflGlyphsString.DOWN_BOW, "downbow");

  private StringDecoration(String name, PeriodType period, String unicode, String smufl, MusicalDecoration reference) {

    super(name, period, unicode, smufl, reference);
  }

  @Override
  public MusicalDecorationPosition getPosition() {

    return MusicalDecorationPosition.TOP;
  }

  @Override
  protected StringDecoration create(String newName) {

    return new StringDecoration(newName, this.period, this.glyphsUnicode, this.glyphsSmufl, this.reference);
  }

  private static StringDecoration create(String name, String unicode, String smufl, String... altNames) {

    StringDecoration decoration = new StringDecoration(name, null, unicode, smufl, null);
    decoration.alias(altNames);
    return decoration;
  }

  static void load() {

  }
}