package io.github.musicdoc.decoration;

import io.github.musicdoc.PeriodType;

/**
 * {@link MusicalDecoration} for fret-board instruments like a {@link io.github.musicdoc.instrument.string.Guitar}.
 */
public class FretboardDecoration extends MusicalDecoration {

  /** Hammer on (push finger fast on the fret to let the string sound). */
  public static final FretboardDecoration HAMMER_ON = create("hammerOn", "H", "H"); // "h" conflicts with German pitch

  /** Pull off (pull the finger off the string at the fret to let the string sound). */
  public static final FretboardDecoration PULL_OFF = create("pullOff", "P", "P"); // "p" confusion with pedal

  /** Slide (move the finger from the previous fret to the new fret while the string sounds). */
  public static final FretboardDecoration SLIDE = create("slide", "S", "S"); // "/" confusion with fraction

  /**
   * Bend (while playing the previous tone move the string up to increase the pitch to the new tone). Unlike a
   * {@link #SLIDE} a bend gives a real glissando. However, a bend can only increase a tone by one or two semitones,
   * while a slide can do larger intervals and also go to a lower tone. Therefore, a bend will only lead to a
   * semantically correct score if strict rules apply to the previous tone and the tone annotated with the bend. In very
   * specific cases a full bend (bend by two semitones) can be followed by a half bend (bend by one semitone) so the
   * bend is decreasing the tone compared to the previous one.
   */
  public static final FretboardDecoration BEND = create("bend", "B", "B");

  private FretboardDecoration(String name, PeriodType period, String unicode, String smufl,
      MusicalDecoration reference) {

    super(name, period, unicode, smufl, reference);
  }

  @Override
  public MusicalDecorationPosition getPosition() {

    return MusicalDecorationPosition.LEFT;
  }

  @Override
  protected FretboardDecoration create(String newName) {

    return new FretboardDecoration(newName, this.period, this.glyphsUnicode, this.glyphsSmufl, this.reference);
  }

  private static FretboardDecoration create(String name, String unicode, String smufl, String... altNames) {

    return create(name, MusicalDecorationPosition.TOP, unicode, smufl, altNames);
  }

  private static FretboardDecoration create(String name, MusicalDecorationPosition position, String unicode,
      String smufl, String... altNames) {

    FretboardDecoration decoration = new FretboardDecoration(name, null, unicode, smufl, null);
    decoration.alias(altNames);
    return decoration;
  }

  static void load() {

  }
}