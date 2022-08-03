package io.github.musicdoc.stave;

import io.github.musicdoc.glyphs.MusicalGlyphs;
import io.github.musicdoc.glyphs.MusicalGlyphsContext;
import io.github.musicdoc.glyphs.smufl.SmuflGlyphsStaffBrackets;
import io.github.musicdoc.glyphs.unicode.UnicodeGlyphsStaffBrackets;

/**
 * The bracket (type) of one or multiple {@link Stave}s. Please note that in English there is no real neutral term for
 * what we call <em>bracket</em> here:
 * <ul>
 * <li>{@link #SQUARE} is the (square) bracket: []</li>
 * <li>{@link #CURLY} is the (curly) brace: {}</li>
 * <li>{@link #NONE} is the (round) parenthesis: ()</li>
 * </ul>
 *
 * @see io.github.musicdoc.stave.system.StaveSystem#getBracket()
 */
public enum StaveBracket implements MusicalGlyphs {

  /** A sequare bracket (e.g. to connect {@link Stave}s of choir voices). */
  SQUARE('[', ']', UnicodeGlyphsStaffBrackets.BRACKET, SmuflGlyphsStaffBrackets.BRACKET),

  /** A curly bracket (e.g. to connect treble and bass {@link Stave} of piano). */
  CURLY('{', '}', UnicodeGlyphsStaffBrackets.BRACE, SmuflGlyphsStaffBrackets.BRACE),

  /** No bracket (just join multiple {@link Stave#getVoices() voices} without bracket). */
  NONE('(', ')', "", "");

  private final char start;

  private final char end;

  private final String syntax;

  private final String unicode;

  private final String smufl;

  private StaveBracket(char start, char end, String unicode, String smufl) {

    this.start = start;
    this.end = end;
    this.syntax = "" + start + end;
    this.unicode = unicode;
    this.smufl = smufl;
  }

  /**
   * @return the character to start the bracket (left/opening like '[', '{', or '(').
   */
  public char getStart() {

    return this.start;
  }

  /**
   * @return the character to end the bracket (right/closing like ']', '}', or ')').
   */
  public char getEnd() {

    return this.end;
  }

  /**
   * @return the syntax as combination for {@link #getStart() start} and {@link #getEnd() end}.
   */
  public String getSyntax() {

    return this.syntax;
  }

  /**
   * @return the {@link io.github.musicdoc.glyphs.unicode.UnicodeGlyphs musical unicode glyph} for the
   *         {@link #getStart() opening} bracket of this type when rendering a
   *         {@link io.github.musicdoc.stave.system.StaveSystem}.
   */
  public String getUnicode() {

    return this.unicode;
  }

  /**
   * @return the {@link io.github.musicdoc.glyphs.smufl.SmuflGlyphs SMuFL glyph} for the {@link #getStart()
   *         opening} bracket of this type when rendering a {@link io.github.musicdoc.stave.system.StaveSystem}.
   */
  public String getSmufl() {

    return this.smufl;
  }

  @Override
  public String getGlyphs(MusicalGlyphsContext context) {

    if (context.isEnforceUnicode()) {
      return this.unicode;
    }
    return this.smufl;
  }

  @Override
  public String toString() {

    return this.syntax;
  }

  @Override
  public void toString(StringBuilder sb) {

    sb.append(this.syntax);
  }

  /**
   * @param c the potential {@link #getStart() left bracket character}.
   * @return the {@link StaveBracket} having {@code c} as {@link #getStart() start character} or {@code null} if no such
   *         {@link StaveBracket} exists.
   */
  public static StaveBracket ofStart(char c) {

    for (StaveBracket bracket : values()) {
      if (bracket.start == c) {
        return bracket;
      }
    }
    return null;
  }

  /**
   * @param c the potential {@link #getEnd() right bracket character}.
   * @return the {@link StaveBracket} having {@code c} as {@link #getEnd() end character} or {@code null} if no such
   *         {@link StaveBracket} exists.
   */
  public static StaveBracket ofEnd(char c) {

    for (StaveBracket bracket : values()) {
      if (bracket.end == c) {
        return bracket;
      }
    }
    return null;
  }

  /**
   * @param syntax the potential {@link #getSyntax() syntax}.
   * @return the {@link StaveBracket} having the given {@link #getSyntax() syntax} or {@code null} if no such
   *         {@link StaveBracket} exists.
   */
  public static StaveBracket ofSyntax(String syntax) {

    for (StaveBracket bracket : values()) {
      if (bracket.syntax.equals(syntax)) {
        return bracket;
      }
    }
    return null;
  }
}
