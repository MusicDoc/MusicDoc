package io.github.musicdoc.music.clef;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatAbc;
import io.github.musicdoc.music.format.SongFormatContext;
import io.github.musicdoc.music.harmony.TonalSystem;
import io.github.musicdoc.music.interval.DiatonicInterval;
import io.github.musicdoc.music.interval.ToneInterval;

/**
 * {@link ClefMapper} for {@link SongFormatAbc}.
 */
public class ClefMapperAbc extends ClefMapper {

  private static final String PROPERTY_STAFF_LINES = "stafflines";

  private static final String PROPERTY_OCTAVE = "octave";

  private static final String PROPERTY_TRANSPOSE = "transpose";

  private static final String PROPERTY_MIDDLE = "middle";

  /** The singleton instance. */
  public static final ClefMapperAbc INSTANCE = new ClefMapperAbc();

  private final Map<String, Clef> clefMap;

  /**
   * The constructor.
   */
  protected ClefMapperAbc() {

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
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

  @Override
  public Clef read(MusicInputStream in, SongFormatContext context) {

    Clef clef = null;
    for (Entry<String, Clef> entry : this.clefMap.entrySet()) {
      if (in.expect(entry.getKey(), true)) {
        clef = entry.getValue();
        break;
      }
    }
    if (clef == null) {
      return null;
    }
    Integer lineNumber = in.readInteger(1, false);
    if (lineNumber != null) {
      int defaultLineNumber = -1;
      if (clef != null) {
        defaultLineNumber = clef.getSymbol().getLineNumber();
      }
      if (defaultLineNumber < 1) {
        in.addWarning("Misplaced line number (" + lineNumber + ").");
      } else {
        int delta = defaultLineNumber - lineNumber.intValue();
        clef = clef.setShiftAdd(DiatonicInterval.of(delta * 2));
      }
    }
    char c = in.peek();
    if ((c == '+') || (c == '-')) {
      int diatonicShift = in.readInteger(2, true).intValue();
      clef = clef.setShiftAdd(DiatonicInterval.of(diatonicShift));
    }
    return clef;
  }

  @Override
  public void write(Clef clef, MusicOutputStream out, SongFormatContext context) {

    // out.append("clef=");
    String clefName = clef.getName();
    if (!this.clefMap.containsKey(clefName)) {
      // TODO
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
