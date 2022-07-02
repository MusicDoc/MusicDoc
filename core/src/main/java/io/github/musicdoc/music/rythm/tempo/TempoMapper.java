package io.github.musicdoc.music.rythm.tempo;

import java.util.ArrayList;
import java.util.List;

import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatOptions;
import io.github.musicdoc.music.rythm.Fraction;
import io.github.musicdoc.music.rythm.beat.Beat;

/**
 * {@link AbstractMapper Mapper} for {@link Tempo}.
 */
public abstract class TempoMapper extends AbstractMapper<Tempo> {

  /** Character to indicate start of {@link Tempo#getBpm() BPM} value after {@link Tempo#getFraction(int) fractions}. */
  protected static final char BPM_ASSIGN_CHAR = '=';

  @Override
  public Tempo parse(MusicInputStream chars, SongFormatOptions options) {

    chars.skipWhile(' ');
    String prefix = parseString(chars);
    List<Fraction> fractions = new ArrayList<>();
    boolean todo = true;
    while (todo) {
      Fraction fraction = getFractionMapper().parse(chars, options);
      if (fraction == null) {
        todo = false;
      } else {
        fractions.add(fraction);
        todo = chars.expect(' ');
      }
    }
    if (fractions.size() == 0) {
      chars.addError("Missing fraction for tempo.");
      fractions.add(Beat.of(1, 4));
    }
    int bpm = 80;
    if (chars.expect(BPM_ASSIGN_CHAR)) {
      chars.skipWhile(' ');
      Integer i = chars.readInteger(3, false);
      if (i == null) {
        chars.addError("Missing bpm value for tempo.");
      } else {
        bpm = i.intValue();
      }
    } else {
      chars.addError("Missing '=' for tempo.");
    }
    chars.skipWhile(' ');
    String suffix = parseString(chars);
    return new Tempo(prefix, bpm, suffix, fractions.toArray(new Fraction[fractions.size()]));
  }

  private String parseString(MusicInputStream chars) {

    String prefix;
    if (chars.expect('"')) {
      prefix = chars.readUntil('"', false);
      if (prefix == null) {
        chars.addError("Unterminated quoted string.");
      }
    } else {
      prefix = chars.readUntil(ListCharFilter.DIGITS, false);
    }
    return prefix;
  }

  /**
   * @return {@link AbstractMapper} to parse {@link Fraction}.
   */
  protected abstract AbstractMapper<? extends Fraction> getFractionMapper();

  @Override
  public void format(Tempo tempo, MusicOutputStream out, SongFormatOptions options) {

    if (tempo == null) {
      return;
    }
    formatString(tempo.getPrefix(), out, options, true);
    int len = tempo.getFractionCount();
    for (int i = 0; i < len; i++) {
      Fraction fraction = tempo.getFraction(i);
      if (i > 0) {
        out.append(' ');
      }
      out.append(fraction.toString());
    }
    out.append('=');
    out.append(Integer.toString(tempo.getBpm()));
    formatString(tempo.getSuffix(), out, options, false);
  }

  private void formatString(String string, MusicOutputStream out, SongFormatOptions options, boolean prefix) {

    if ((string == null) || (string.isEmpty())) {
      return;
    }
    if (!prefix) {
      out.append(' ');
    }
    boolean useQuote = isUseQuote(string, options);
    if (useQuote) {
      out.append('"');
    }
    out.append(string);
    if (useQuote) {
      out.append('"');
    }
    if (prefix) {
      out.append(' ');
    }
  }

  /**
   * @param string the string to write as prefix or suffix.
   * @param options the {@link SongFormatOptions}.
   * @return {@code true} if the given {@code string} shall be enclosed in quotes ('"'), {@code false} otherwise.
   */
  protected boolean isUseQuote(String string, SongFormatOptions options) {

    int len = string.length();
    for (int i = 0; i < len; i++) {
      char c = string.charAt(i);
      if ((c >= '0') && (c <= '9')) {
        return true;
      }
    }
    return false;
  }

}
