package io.github.musicdoc.rhythm.tempo;

import java.util.ArrayList;
import java.util.List;

import io.github.mmm.base.filter.CharFilter;
import io.github.mmm.scanner.CharStreamScanner;
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

  private static final CharFilter STOP_FILTER = CharFilter.LATIN_DIGIT.compose(CharFilter.NEWLINE);

  /** Character to indicate start of {@link Tempo#getBpm() BPM} value after {@link Tempo#getFraction(int) fractions}. */
  protected static final char BPM_ASSIGN_CHAR = '=';

  @Override
  public Tempo read(MusicInputStream in, SongFormatContext context) {

    CharStreamScanner scanner = in.getScanner();
    scanner.skipWhile(' ');
    String prefix = parseString(scanner);
    List<Fraction> fractions = new ArrayList<>();
    boolean todo = true;
    while (todo) {
      Fraction fraction = getFractionMapper().read(in, context);
      if (fraction == null) {
        todo = false;
      } else {
        fractions.add(fraction);
        todo = scanner.expectOne(' ');
      }
    }
    if (fractions.size() == 0) {
      scanner.addError("Missing fraction for tempo.");
      fractions.add(Metre.of(1, 4));
    }
    int bpm = 80;
    if (scanner.expectOne(BPM_ASSIGN_CHAR)) {
      scanner.skipWhile(' ');
      Integer i = in.readInteger(3, false);
      if (i == null) {
        scanner.addError("Missing bpm value for tempo.");
      } else {
        bpm = i.intValue();
      }
    } else {
      scanner.addError("Missing '=' for tempo.");
    }
    scanner.skipWhile(' ');
    String suffix = parseString(scanner);
    return new Tempo(prefix, bpm, suffix, fractions.toArray(new Fraction[fractions.size()]));
  }

  private String parseString(CharStreamScanner scanner) {

    String string;
    if (scanner.expectOne('"')) {
      string = scanner.readUntil('"', false);
      if (string == null) {
        scanner.addError("Unterminated quoted string.");
      }
    } else {
      string = scanner.readUntil(STOP_FILTER, false);
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
