package io.github.musicdoc.rhythm.tempo;

import java.util.ArrayList;
import java.util.List;

import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.rhythm.fraction.Fraction;
import io.github.musicdoc.rhythm.metre.Metre;

/**
 * {@link AbstractMapper Mapper} for {@link Tempo}.
 */
public abstract class TempoMapper extends AbstractMapper<Tempo> {

  private static final ListCharFilter STOP_FILTER = ListCharFilter.DIGITS.join(NEWLINE_CHAR);

  /** Character to indicate start of {@link Tempo#getBpm() BPM} value after {@link Tempo#getFraction(int) fractions}. */
  protected static final char BPM_ASSIGN_CHAR = '=';

  @Override
  public Tempo read(MusicInputStream in, SongFormatContext context) {

    in.skipWhile(' ');
    String prefix = parseString(in);
    List<Fraction> fractions = new ArrayList<>();
    boolean todo = true;
    while (todo) {
      Fraction fraction = getFractionMapper().read(in, context);
      if (fraction == null) {
        todo = false;
      } else {
        fractions.add(fraction);
        todo = in.expect(' ');
      }
    }
    if (fractions.size() == 0) {
      in.addError("Missing fraction for tempo.");
      fractions.add(Metre.of(1, 4));
    }
    int bpm = 80;
    if (in.expect(BPM_ASSIGN_CHAR)) {
      in.skipWhile(' ');
      Integer i = in.readInteger(3, false);
      if (i == null) {
        in.addError("Missing bpm value for tempo.");
      } else {
        bpm = i.intValue();
      }
    } else {
      in.addError("Missing '=' for tempo.");
    }
    in.skipWhile(' ');
    String suffix = parseString(in);
    return new Tempo(prefix, bpm, suffix, fractions.toArray(new Fraction[fractions.size()]));
  }

  private String parseString(MusicInputStream in) {

    String string;
    if (in.expect('"')) {
      string = in.readUntil('"', false);
      if (string == null) {
        in.addError("Unterminated quoted string.");
      }
    } else {
      string = in.readUntil(STOP_FILTER, false);
    }
    return string;
  }

  private AbstractMapper<? extends Fraction> getFractionMapper() {

    return getBeatMapper();
  }

  @Override
  public void write(Tempo tempo, MusicOutputStream out, SongFormatContext context) {

    if (tempo == null) {
      return;
    }
    formatString(tempo.getPrefix(), out, context, true);
    int len = tempo.getFractionCount();
    for (int i = 0; i < len; i++) {
      Fraction fraction = tempo.getFraction(i);
      if (i > 0) {
        out.write(' ');
      }
      out.write(fraction.toString());
    }
    out.write('=');
    out.write(Integer.toString(tempo.getBpm()));
    formatString(tempo.getSuffix(), out, context, false);
  }

  private void formatString(String string, MusicOutputStream out, SongFormatContext context, boolean prefix) {

    if ((string == null) || (string.isEmpty())) {
      return;
    }
    if (!prefix) {
      out.write(' ');
    }
    boolean useQuote = isUseQuote(string, context);
    if (useQuote) {
      out.write('"');
    }
    out.write(string);
    if (useQuote) {
      out.write('"');
    }
    if (prefix) {
      out.write(' ');
    }
  }

  /**
   * @param string the string to write as prefix or suffix.
   * @param context the {@link SongFormatContext}.
   * @return {@code true} if the given {@code string} shall be enclosed in quotes ('"'), {@code false} otherwise.
   */
  protected boolean isUseQuote(String string, SongFormatContext context) {

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
