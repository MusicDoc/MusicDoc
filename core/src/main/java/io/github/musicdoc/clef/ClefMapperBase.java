package io.github.musicdoc.clef;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import io.github.mmm.scanner.CharStreamScanner;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.harmony.TonalSystem;
import io.github.musicdoc.interval.DiatonicInterval;
import io.github.musicdoc.interval.ToneInterval;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * Abstract base implementation of {@link ClefMapper}.
 */
public abstract class ClefMapperBase extends ClefMapper {

  private final Map<String, Clef> clefMap;

  /**
   * The constructor.
   */
  protected ClefMapperBase() {

    super();
    this.clefMap = new HashMap<>();
    registerClefs();
  }

  /**
   * @param name the {@link Clef#getName() name}.
   * @param clef the {@link Clef} to register.
   */
  protected void addClef(Clef clef, String name) {

    Clef duplicate = this.clefMap.put(name, clef);
    if (duplicate != null) {
      throw new IllegalStateException("Duplicate clef for name '" + name + "': " + duplicate + " and " + clef);
    }
  }

  /**
   * @param clef the {@link Clef} to register.
   */
  protected void addClef(Clef clef) {

    addClef(clef, clef.getName());
  }

  /**
   * Registers the clefs. May be overridden to add (or even remove) default mappings.
   */
  protected void registerClefs() {

    addClef(Clef.TREBLE);
    addClef(Clef.ALTO);
    addClef(Clef.TENOR);
    addClef(Clef.BASS);
    addClef(Clef.PERCUSSION_1);
    addClef(Clef.NONE);
  }

  @Override
  public Clef read(MusicInputStream in, SongFormatContext context) {

    CharStreamScanner scanner = in.getScanner();
    Clef clef = null;
    for (Entry<String, Clef> entry : this.clefMap.entrySet()) {
      if (scanner.expect(entry.getKey(), true)) {
        clef = entry.getValue();
        break;
      }
    }
    if (clef == null) {
      return null;
    }
    int lineNumber = scanner.readDigit();
    if (lineNumber >= 0) {
      int defaultLineNumber = -1;
      if (clef != null) {
        defaultLineNumber = clef.getSymbol().getLineNumber();
      }
      if (defaultLineNumber < 1) {
        in.addWarning("Misplaced line number (" + lineNumber + ").");
      } else {
        int delta = defaultLineNumber - lineNumber;
        clef = clef.setShiftAdd(DiatonicInterval.of(delta * 2));
      }
    }
    char c = scanner.peek();
    if ((c == '+') || (c == '-')) {
      int diatonicShift = in.readInteger(2, true).intValue();
      clef = clef.setShiftAdd(DiatonicInterval.of(diatonicShift));
    }
    return clef;
  }

  @Override
  public void write(Clef clef, MusicOutputStream out, SongFormatContext context) {

    String clefName = clef.getName();
    if (!this.clefMap.containsKey(clefName)) {
      out.addWarning("Unknow clef " + clefName);
    }
    out.write(clefName);
    ToneInterval shift = clef.getShift();
    if (!shift.isEmpty()) {
      int diatonicSteps = shift.getDiatonicSteps(TonalSystem.MAJOR) % 7;
      if (diatonicSteps != 0) {
        int lineNumber = clef.getSymbol().getLineNumber();
        lineNumber += (diatonicSteps / 2);
        if (lineNumber >= 0) {
          out.write(Integer.toString(lineNumber));
        }
      }
      int octaves = shift.getOctaves();
      if (octaves != 0) {
        int offset = (octaves * 7) + 1;
        if (offset > 0) {
          out.write("+");
        }
        out.write(Integer.toString(offset));
      }
    }
  }

}
