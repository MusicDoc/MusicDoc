package io.github.musicdoc.music.tone;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import io.github.musicdoc.music.glyphs.unicode.UnicodeGlyphsAccidentals;
import io.github.musicdoc.music.interval.ChromaticStep;

/**
 * {@link TonePitch} with international {@link ToneNameStyle}. This is similar to {@link TonePitchEnglish} but uses
 * unicode signs (e.g. "C&#9839;" or "B&#9838;"). It is the preferred style to display (if unicode is supported) as it
 * avoids confusion (see {@link #B_NEUTRAL} for further details).
 */
public class TonePitchInternational extends TonePitch {

  /**
   * {@link #getNameStyle() Name style} for {@link TonePitchEnglish}.
   */
  public static final ToneNameStyleInternational STYLE = new ToneNameStyleInternational();

  private static final Map<String, TonePitchInternational> NAME2PITCH_MAP = new HashMap<>();

  private static final Collection<TonePitchInternational> PITCHES = Collections
      .unmodifiableCollection(NAME2PITCH_MAP.values());

  private static final TonePitchInternational[] PITCHES_NORMAL = new TonePitchInternational[12];

  private static final TonePitchInternational[][] PITCHES_BY_TYPE_AND_STEP = new TonePitchInternational[5][12];

  /**
   * {@code C} is the {@link io.github.musicdoc.music.harmony.MusicalKey#getTonika() tonika} of the common
   * {@link io.github.musicdoc.music.harmony.MusicalKey#C_MAJOR} key.
   */
  public static final TonePitchInternational C = create("C", 0);

  /**
   * {@code C&#9839;} is one semitones higher than the pitch {@link #C}.
   */
  public static final TonePitchInternational C_SHARP = create("C" + UnicodeGlyphsAccidentals.SHARP_1, 1);

  /**
   * {@code D} is two semitones higher than the pitch {@link #C}.
   */
  public static final TonePitchInternational D = create("D", 2);

  /**
   * {@code D&#9839;} is three semitones higher than the pitch {@link #C}.
   */
  public static final TonePitchInternational D_SHARP = create("D" + UnicodeGlyphsAccidentals.SHARP_1, 3);

  /**
   * {@code E} is four semitones higher than the pitch {@link #C}.
   */
  public static final TonePitchInternational E = create("E", 4);

  /**
   * {@code F} is five semitones higher than the pitch {@link #C}.
   */
  public static final TonePitchInternational F = create("F", 5);

  /**
   * {@code F&#9839;} is six semitones higher than the pitch {@link #C}.
   */
  public static final TonePitchInternational F_SHARP = create("F" + UnicodeGlyphsAccidentals.SHARP_1, 6);

  /**
   * {@code G} is seven semitones higher than the pitch {@link #C}.
   */
  public static final TonePitchInternational G = create("G", 7);

  /**
   * {@code G&#9839;} is eight semitones higher than the pitch {@link #C}.
   */
  public static final TonePitchInternational G_SHARP = create("G" + UnicodeGlyphsAccidentals.SHARP_1, 8);

  /**
   * {@code A} is nine semitones higher than the pitch {@link #C}. The middle a (a<sup>1</sup>, Concert A reference) is
   * normalized to 440Hz.
   */
  public static final TonePitchInternational A = create("A", 9);

  /**
   * {@code B&#9837;} is ten semitones higher than the pitch {@link #C}. In German {@link #getNameStyle() name style}
   * this pitch is simply called {@link TonePitchGerman#B} what can cause confusion with {@link TonePitchEnglish#B}. See
   * {@link #B_NEUTRAL} for further details.
   */
  public static final TonePitchInternational B_FLAT = create("B" + UnicodeGlyphsAccidentals.FLAT_1, 10);

  /**
   * {@code B&#9838;} is the international form of the English form {@link TonePitchEnglish#B B}. However, in German
   * {@link #getNameStyle() name style} this pitch is called {@link TonePitchGerman#H H} where {@link #B_FLAT} is called
   * {@link TonePitchGerman#B B}. The international notation therefore suggests the following notation to avoid
   * confusion:
   * <table border="1">
   * <tr>
   * <th>{@link TonePitchInternational International}</th>
   * <th>{@link TonePitchEnglish English}</th>
   * <th>{@link TonePitchGerman German}</th>
   * <th>{@link TonePitchDutch Dutch}</th>
   * </tr>
   * <tr>
   * <td>{@link #B_NEUTRAL B&#9838;}</td>
   * <td>{@link TonePitchEnglish#B B}</td>
   * <td>{@link TonePitchGerman#H H}</td>
   * <td>{@link TonePitchDutch#B B}</td>
   * </tr>
   * <tr>
   * <td>{@link #B_FLAT B&#9837;}</td>
   * <td>{@link TonePitchEnglish#B_FLAT Bb}</td>
   * <td>{@link TonePitchGerman#B B}</td>
   * <td>{@link TonePitchDutch#BES Bes}</td>
   * </tr>
   * </table>
   */
  public static final TonePitchInternational B_NEUTRAL = create("B" + UnicodeGlyphsAccidentals.NEUTRAL, 11);

  // ------------------------------ enharmonic changes (single sharp) ------------------------------

  /**
   * {@code E&#9839;} is an enharmonic change of {@link #F}.
   */
  public static final TonePitchInternational E_SHARP = create("E" + UnicodeGlyphsAccidentals.SHARP_1, F);

  /**
   * {@code A&#9839;} is an enharmonic change of {@link #B_FLAT}.
   */
  public static final TonePitchInternational A_SHARP = create("A" + UnicodeGlyphsAccidentals.SHARP_1, B_FLAT);

  /**
   * {@code B&#9839;} is an enharmonic change of {@link #C}.
   */
  public static final TonePitchInternational B_SHARP = create("B" + UnicodeGlyphsAccidentals.SHARP_1, C);

  // ------------------------------ enharmonic changes (single flat) ------------------------------

  /**
   * {@code C&#9837;} is an enharmonic change of {@link #B_NEUTRAL}.
   */
  public static final TonePitchInternational C_FLAT = create("C" + UnicodeGlyphsAccidentals.FLAT_1, B_NEUTRAL);

  /**
   * {@code D&#9837;} is an enharmonic change of {@link #C_SHARP}.
   */
  public static final TonePitchInternational D_FLAT = create("D" + UnicodeGlyphsAccidentals.FLAT_1, C_SHARP);

  /**
   * {@code E&#9837;} is an enharmonic change of {@link #D_SHARP}.
   */
  public static final TonePitchInternational E_FLAT = create("E" + UnicodeGlyphsAccidentals.FLAT_1, D_SHARP);

  /**
   * {@code F&#9837;} is an enharmonic change of {@link #E}.
   */
  public static final TonePitchInternational F_FLAT = create("F" + UnicodeGlyphsAccidentals.FLAT_1, E);

  /**
   * {@code G&#9837;} is an enharmonic change {@link #F_SHARP}.
   */
  public static final TonePitchInternational G_FLAT = create("G" + UnicodeGlyphsAccidentals.FLAT_1, F_SHARP);

  /**
   * {@code A&#9837;} is an enharmonic change {@link #G_SHARP}.
   */
  public static final TonePitchInternational A_FLAT = create("A" + UnicodeGlyphsAccidentals.FLAT_1, G_SHARP);

  // ------------------------------ enharmonic changes (double flat) ------------------------------

  /**
   * {@code C&#119083;} is an enharmonic change {@link #B_FLAT}.
   */
  public static final TonePitchInternational C_DOUBLE_FLAT = create("C" + UnicodeGlyphsAccidentals.FLAT_2, B_FLAT);

  /**
   * {@code D&#119083;} is an enharmonic change {@link #C}.
   */
  public static final TonePitchInternational D_DOUBLE_FLAT = create("D" + UnicodeGlyphsAccidentals.FLAT_2, C);

  /**
   * {@code E&#119083;} is an enharmonic change {@link #D}.
   */
  public static final TonePitchInternational E_DOUBLE_FLAT = create("E" + UnicodeGlyphsAccidentals.FLAT_2, D);

  /**
   * {@code F&#119083;} is an enharmonic change {@link #D_SHARP}.
   */
  public static final TonePitchInternational F_DOUBLE_FLAT = create("F" + UnicodeGlyphsAccidentals.FLAT_2, D_SHARP);

  /**
   * {@code G&#119083;} is an enharmonic change {@link #F}.
   */
  public static final TonePitchInternational G_DOUBLE_FLAT = create("G" + UnicodeGlyphsAccidentals.FLAT_2, F);

  /**
   * {@code A&#119083;} is an enharmonic change {@link #G}.
   */
  public static final TonePitchInternational A_DOUBLE_FLAT = create("A" + UnicodeGlyphsAccidentals.FLAT_2, G);

  /**
   * {@code B&#119083;} is an enharmonic change {@link #A}.
   */
  public static final TonePitchInternational B_DOUBLE_FLAT = create("B" + UnicodeGlyphsAccidentals.FLAT_2, A);

  // ------------------------------ enharmonic changes (double sharp) ------------------------------

  /**
   * {@code C&#119082;} is an enharmonic change {@link #D}.
   */
  public static final TonePitchInternational C_DOUBLE_SHARP = create("C" + UnicodeGlyphsAccidentals.SHARP_2, D);

  /**
   * {@code D&#119082;} is an enharmonic change {@link #E}.
   */
  public static final TonePitchInternational D_DOUBLE_SHARP = create("D" + UnicodeGlyphsAccidentals.SHARP_2, E);

  /**
   * {@code E&#119082;} is an enharmonic change {@link #F_SHARP}.
   */
  public static final TonePitchInternational E_DOUBLE_SHARP = create("E" + UnicodeGlyphsAccidentals.SHARP_2, F_SHARP);

  /**
   * {@code F&#119082;} is an enharmonic change {@link #G}.
   */
  public static final TonePitchInternational F_DOUBLE_SHARP = create("F" + UnicodeGlyphsAccidentals.SHARP_2, G);

  /**
   * {@code G&#119082;} is an enharmonic change {@link #A}.
   */
  public static final TonePitchInternational G_DOUBLE_SHARP = create("G" + UnicodeGlyphsAccidentals.SHARP_2, A);

  /**
   * {@code A&#119082;} is an enharmonic change {@link #B_NEUTRAL}.
   */
  public static final TonePitchInternational A_DOUBLE_SHARP = create("A" + UnicodeGlyphsAccidentals.SHARP_2, B_NEUTRAL);

  /**
   * {@code B&#119082;} is an enharmonic change {@link #C_SHARP}.
   */
  public static final TonePitchInternational B_DOUBLE_SHARP = create("B" + UnicodeGlyphsAccidentals.SHARP_2, C_SHARP);

  private final TonePitchInternational otherCase;

  private TonePitchInternational(String name, ChromaticStep step, EnharmonicType enharmonicType,
      TonePitchInternational otherCase) {

    super(name, step, (otherCase == null) ? ToneNameCase.CAPITAL_CASE : ToneNameCase.LOWER_CASE, enharmonicType);
    if (otherCase == null) {
      String lowercaseName = name.toLowerCase(Locale.US);
      assert (!lowercaseName.equals(name));
      this.otherCase = new TonePitchInternational(lowercaseName, step, enharmonicType, this);
    } else {
      this.otherCase = otherCase;
    }
    TonePitchInternational duplicate = NAME2PITCH_MAP.put(name, this);
    assert (duplicate == null);
  }

  @Override
  public ToneNameStyleInternational getNameStyle() {

    return STYLE;
  }

  @Override
  public TonePitchInternational getReference() {

    return PITCHES_NORMAL[this.step.get()];
  }

  @Override
  public TonePitchInternational with(ToneNameCase newCase) {

    if (this.nameCase == newCase) {
      return this;
    }
    return this.otherCase;
  }

  private static TonePitchInternational create(String name, int step) {

    TonePitchInternational pitch = create(name, ChromaticStep.of(step));
    assert (PITCHES_NORMAL[step] == null);
    PITCHES_NORMAL[step] = pitch;
    return pitch;
  }

  private static TonePitchInternational create(String name, TonePitchInternational reference) {

    TonePitchInternational pitch = create(name, reference.getStep());
    return pitch;
  }

  private static TonePitchInternational create(String name, ChromaticStep step) {

    EnharmonicType type = STYLE.getType(name);
    TonePitchInternational pitch = new TonePitchInternational(name, step, type, null);
    int typeIndex = type.getSignOffset() + 2;
    assert (PITCHES_BY_TYPE_AND_STEP[typeIndex][step.get()] == null);
    PITCHES_BY_TYPE_AND_STEP[typeIndex][step.get()] = pitch;
    return pitch;
  }

  /**
   * {@link ToneNameStyle} for {@link TonePitchInternational}.
   */
  public static final class ToneNameStyleInternational extends ToneNameStyle<TonePitchInternational> {

    private ToneNameStyleInternational() {

      super();
    }

    @Override
    public String getName() {

      return "International";
    }

    @Override
    public String getSingleSharpSign() {

      return UnicodeGlyphsAccidentals.SHARP_1;
    }

    @Override
    public String getDoubleSharpSign() {

      return UnicodeGlyphsAccidentals.SHARP_2;
    }

    @Override
    public String getSingleFlatSign() {

      return UnicodeGlyphsAccidentals.FLAT_1;
    }

    @Override
    public String getDoubleFlatSign() {

      return UnicodeGlyphsAccidentals.FLAT_2;
    }

    @Override
    public String getNeutralSign() {

      return UnicodeGlyphsAccidentals.NEUTRAL;
    }

    @Override
    public TonePitchInternational pitch(String name) {

      return NAME2PITCH_MAP.get(name);
    }

    @Override
    public Collection<TonePitchInternational> values() {

      return PITCHES;
    }

    @Override
    public TonePitchInternational pitch(ChromaticStep step, EnharmonicType type, ToneNameCase nameCase) {

      TonePitchInternational result;
      if (type == null) {
        result = PITCHES_NORMAL[step.get()];
      } else {
        int typeIndex = type.getSignOffset() + 2;
        result = PITCHES_BY_TYPE_AND_STEP[typeIndex][step.get()];
      }
      if (result == null) {
        return null;
      }
      return result.with(nameCase);
    }
  }

}
