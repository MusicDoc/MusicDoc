/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.music.tone;

import java.util.Locale;

import io.github.musicdoc.music.clef.Clef;
import io.github.musicdoc.music.harmony.key.MusicalKey;
import io.github.musicdoc.music.interval.ChromaticInterval;
import io.github.musicdoc.music.interval.ToneInterval;
import io.github.musicdoc.music.transpose.AbstractTransposable;
import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * A {@link Tone} is an absolute tone as the combination of a {@link TonePitch} with an {@link #getOctave() octave}.
 * While a {@link TonePitch} only identifies a tone within an octave a {@link Tone} can identify every absolute tone
 * from any octave. For string representation the Helmholtz pitch notation is used.
 */
public class Tone extends AbstractTransposable<Tone> implements Comparable<Tone> {

  /** Character to transpose one octave up. */
  public static final char OCTAVE_UP = '\'';

  /** Character to transpose one octave down. */
  public static final char OCTAVE_DOWN = ',';

  /** {@link TonePitchEnglish#C} with {@link #getOctave() octave} {@code 0}. */
  public static final Tone C0 = new Tone(TonePitchEnglish.C, 0);

  /** {@link TonePitchEnglish#C_SHARP} with {@link #getOctave() octave} {@code 0}. */
  public static final Tone CS0 = new Tone(TonePitchEnglish.C_SHARP, 0);

  /** {@link TonePitchEnglish#D} with {@link #getOctave() octave} {@code 0}. */
  public static final Tone D0 = new Tone(TonePitchEnglish.D, 0);

  /** {@link TonePitchEnglish#D_SHARP} with {@link #getOctave() octave} {@code 0}. */
  public static final Tone DS0 = new Tone(TonePitchEnglish.D_SHARP, 0);

  /** {@link TonePitchEnglish#E} with {@link #getOctave() octave} {@code 0}. */
  public static final Tone E0 = new Tone(TonePitchEnglish.E, 0);

  /** {@link TonePitchEnglish#F} with {@link #getOctave() octave} {@code 0}. */
  public static final Tone F0 = new Tone(TonePitchEnglish.F, 0);

  /** {@link TonePitchEnglish#F_SHARP} with {@link #getOctave() octave} {@code 0}. */
  public static final Tone FS0 = new Tone(TonePitchEnglish.F_SHARP, 0);

  /** {@link TonePitchEnglish#G} with {@link #getOctave() octave} {@code 0}. */
  public static final Tone G0 = new Tone(TonePitchEnglish.G, 0);

  /** {@link TonePitchEnglish#G_SHARP} with {@link #getOctave() octave} {@code 0}. */
  public static final Tone GS0 = new Tone(TonePitchEnglish.G_SHARP, 0);

  /** {@link TonePitchEnglish#A} with {@link #getOctave() octave} {@code 0}. */
  public static final Tone A0 = new Tone(TonePitchEnglish.A, 0);

  /** {@link TonePitchEnglish#B_FLAT} with {@link #getOctave() octave} {@code 0}. */
  public static final Tone BF0 = new Tone(TonePitchEnglish.B_FLAT, 0);

  /** {@link TonePitchEnglish#B} with {@link #getOctave() octave} {@code 0}. */
  public static final Tone B0 = new Tone(TonePitchEnglish.B, 0);

  /** {@link TonePitchEnglish#C} with {@link #getOctave() octave} {@code 1}. */
  public static final Tone C1 = new Tone(TonePitchEnglish.C, 1);

  /** {@link TonePitchEnglish#C_SHARP} with {@link #getOctave() octave} {@code 1}. */
  public static final Tone CS1 = new Tone(TonePitchEnglish.C_SHARP, 1);

  /** {@link TonePitchEnglish#D} with {@link #getOctave() octave} {@code 1}. */
  public static final Tone D1 = new Tone(TonePitchEnglish.D, 1);

  /** {@link TonePitchEnglish#D_SHARP} with {@link #getOctave() octave} {@code 1}. */
  public static final Tone DS1 = new Tone(TonePitchEnglish.D_SHARP, 1);

  /** {@link TonePitchEnglish#E} with {@link #getOctave() octave} {@code 1}. */
  public static final Tone E1 = new Tone(TonePitchEnglish.E, 1);

  /** {@link TonePitchEnglish#F} with {@link #getOctave() octave} {@code 1}. */
  public static final Tone F1 = new Tone(TonePitchEnglish.F, 1);

  /** {@link TonePitchEnglish#F_SHARP} with {@link #getOctave() octave} {@code 1}. */
  public static final Tone FS1 = new Tone(TonePitchEnglish.F_SHARP, 1);

  /** {@link TonePitchEnglish#G} with {@link #getOctave() octave} {@code 1}. */
  public static final Tone G1 = new Tone(TonePitchEnglish.G, 1);

  /** {@link TonePitchEnglish#G_SHARP} with {@link #getOctave() octave} {@code 1}. */
  public static final Tone GS1 = new Tone(TonePitchEnglish.G_SHARP, 1);

  /** {@link TonePitchEnglish#A} with {@link #getOctave() octave} {@code 1}. */
  public static final Tone A1 = new Tone(TonePitchEnglish.A, 1);

  /** {@link TonePitchEnglish#B_FLAT} with {@link #getOctave() octave} {@code 1}. */
  public static final Tone BF1 = new Tone(TonePitchEnglish.B_FLAT, 1);

  /** {@link TonePitchEnglish#B} with {@link #getOctave() octave} {@code 1}. */
  public static final Tone B1 = new Tone(TonePitchEnglish.B, 1);

  /** {@link TonePitchEnglish#C} with {@link #getOctave() octave} {@code 2}. */
  public static final Tone C2 = new Tone(TonePitchEnglish.C, 2);

  /** {@link TonePitchEnglish#C_SHARP} with {@link #getOctave() octave} {@code 2}. */
  public static final Tone CS2 = new Tone(TonePitchEnglish.C_SHARP, 2);

  /** {@link TonePitchEnglish#D} with {@link #getOctave() octave} {@code 2}. */
  public static final Tone D2 = new Tone(TonePitchEnglish.D, 2);

  /** {@link TonePitchEnglish#D_SHARP} with {@link #getOctave() octave} {@code 2}. */
  public static final Tone DS2 = new Tone(TonePitchEnglish.D_SHARP, 2);

  /** {@link TonePitchEnglish#E} with {@link #getOctave() octave} {@code 2}. */
  public static final Tone E2 = new Tone(TonePitchEnglish.E, 2);

  /** {@link TonePitchEnglish#F} with {@link #getOctave() octave} {@code 2}. */
  public static final Tone F2 = new Tone(TonePitchEnglish.F, 2);

  /** {@link TonePitchEnglish#F_SHARP} with {@link #getOctave() octave} {@code 2}. */
  public static final Tone FS2 = new Tone(TonePitchEnglish.F_SHARP, 2);

  /** {@link TonePitchEnglish#G} with {@link #getOctave() octave} {@code 2}. */
  public static final Tone G2 = new Tone(TonePitchEnglish.G, 2);

  /** {@link TonePitchEnglish#G_SHARP} with {@link #getOctave() octave} {@code 2}. */
  public static final Tone GS2 = new Tone(TonePitchEnglish.G_SHARP, 2);

  /** {@link TonePitchEnglish#A} with {@link #getOctave() octave} {@code 2}. */
  public static final Tone A2 = new Tone(TonePitchEnglish.A, 2);

  /** {@link TonePitchEnglish#B_FLAT} with {@link #getOctave() octave} {@code 2}. */
  public static final Tone BF2 = new Tone(TonePitchEnglish.B_FLAT, 2);

  /** {@link TonePitchEnglish#B} with {@link #getOctave() octave} {@code 2}. */
  public static final Tone B2 = new Tone(TonePitchEnglish.B, 2);

  /** {@link TonePitchEnglish#C} with {@link #getOctave() octave} {@code 3}. */
  public static final Tone C3 = new Tone(TonePitchEnglish.C, 3);

  /** {@link TonePitchEnglish#C_SHARP} with {@link #getOctave() octave} {@code 3}. */
  public static final Tone CS3 = new Tone(TonePitchEnglish.C_SHARP, 3);

  /** {@link TonePitchEnglish#D} with {@link #getOctave() octave} {@code 3}. */
  public static final Tone D3 = new Tone(TonePitchEnglish.D, 3);

  /** {@link TonePitchEnglish#D_SHARP} with {@link #getOctave() octave} {@code 3}. */
  public static final Tone DS3 = new Tone(TonePitchEnglish.D_SHARP, 3);

  /** {@link TonePitchEnglish#E} with {@link #getOctave() octave} {@code 3}. */
  public static final Tone E3 = new Tone(TonePitchEnglish.E, 3);

  /** {@link TonePitchEnglish#F} with {@link #getOctave() octave} {@code 3}. */
  public static final Tone F3 = new Tone(TonePitchEnglish.F, 3);

  /** {@link TonePitchEnglish#F_SHARP} with {@link #getOctave() octave} {@code 3}. */
  public static final Tone FS3 = new Tone(TonePitchEnglish.F_SHARP, 3);

  /** {@link TonePitchEnglish#G} with {@link #getOctave() octave} {@code 3}. */
  public static final Tone G3 = new Tone(TonePitchEnglish.G, 3);

  /** {@link TonePitchEnglish#G_SHARP} with {@link #getOctave() octave} {@code 3}. */
  public static final Tone GS3 = new Tone(TonePitchEnglish.G_SHARP, 3);

  /** {@link TonePitchEnglish#A} with {@link #getOctave() octave} {@code 3}. */
  public static final Tone A3 = new Tone(TonePitchEnglish.A, 3);

  /** {@link TonePitchEnglish#B_FLAT} with {@link #getOctave() octave} {@code 3}. */
  public static final Tone BF3 = new Tone(TonePitchEnglish.B_FLAT, 3);

  /** {@link TonePitchEnglish#B} with {@link #getOctave() octave} {@code 3}. */
  public static final Tone B3 = new Tone(TonePitchEnglish.B, 3);

  /** {@link TonePitchEnglish#C} with {@link #getOctave() octave} {@code 4}. */
  public static final Tone C4 = new Tone(TonePitchEnglish.C, 4);

  /** {@link TonePitchEnglish#C_SHARP} with {@link #getOctave() octave} {@code 4}. */
  public static final Tone CS4 = new Tone(TonePitchEnglish.C_SHARP, 4);

  /** {@link TonePitchEnglish#D} with {@link #getOctave() octave} {@code 4}. */
  public static final Tone D4 = new Tone(TonePitchEnglish.D, 4);

  /** {@link TonePitchEnglish#D_SHARP} with {@link #getOctave() octave} {@code 4}. */
  public static final Tone DS4 = new Tone(TonePitchEnglish.D_SHARP, 4);

  /** {@link TonePitchEnglish#E} with {@link #getOctave() octave} {@code 4}. */
  public static final Tone E4 = new Tone(TonePitchEnglish.E, 4);

  /** {@link TonePitchEnglish#F} with {@link #getOctave() octave} {@code 4}. */
  public static final Tone F4 = new Tone(TonePitchEnglish.F, 4);

  /** {@link TonePitchEnglish#F_SHARP} with {@link #getOctave() octave} {@code 4}. */
  public static final Tone FS4 = new Tone(TonePitchEnglish.F_SHARP, 4);

  /** {@link TonePitchEnglish#G} with {@link #getOctave() octave} {@code 4}. */
  public static final Tone G4 = new Tone(TonePitchEnglish.G, 4);

  /** {@link TonePitchEnglish#G_SHARP} with {@link #getOctave() octave} {@code 4}. */
  public static final Tone GS4 = new Tone(TonePitchEnglish.G_SHARP, 4);

  /** {@link TonePitchEnglish#A} with {@link #getOctave() octave} {@code 4}. */
  public static final Tone A4 = new Tone(TonePitchEnglish.A, 4);

  /** {@link TonePitchEnglish#B_FLAT} with {@link #getOctave() octave} {@code 4}. */
  public static final Tone BF4 = new Tone(TonePitchEnglish.B_FLAT, 4);

  /** {@link TonePitchEnglish#B} with {@link #getOctave() octave} {@code 4}. */
  public static final Tone B4 = new Tone(TonePitchEnglish.B, 4);

  /** {@link TonePitchEnglish#C} with {@link #getOctave() octave} {@code 5}. */
  public static final Tone C5 = new Tone(TonePitchEnglish.C, 5);

  /** {@link TonePitchEnglish#C_SHARP} with {@link #getOctave() octave} {@code 5}. */
  public static final Tone CS5 = new Tone(TonePitchEnglish.C_SHARP, 5);

  /** {@link TonePitchEnglish#D} with {@link #getOctave() octave} {@code 5}. */
  public static final Tone D5 = new Tone(TonePitchEnglish.D, 5);

  /** {@link TonePitchEnglish#D_SHARP} with {@link #getOctave() octave} {@code 5}. */
  public static final Tone DS5 = new Tone(TonePitchEnglish.D_SHARP, 5);

  /** {@link TonePitchEnglish#E} with {@link #getOctave() octave} {@code 5}. */
  public static final Tone E5 = new Tone(TonePitchEnglish.E, 5);

  /** {@link TonePitchEnglish#F} with {@link #getOctave() octave} {@code 5}. */
  public static final Tone F5 = new Tone(TonePitchEnglish.F, 5);

  /** {@link TonePitchEnglish#F_SHARP} with {@link #getOctave() octave} {@code 5}. */
  public static final Tone FS5 = new Tone(TonePitchEnglish.F_SHARP, 5);

  /** {@link TonePitchEnglish#G} with {@link #getOctave() octave} {@code 5}. */
  public static final Tone G5 = new Tone(TonePitchEnglish.G, 5);

  /** {@link TonePitchEnglish#G_SHARP} with {@link #getOctave() octave} {@code 5}. */
  public static final Tone GS5 = new Tone(TonePitchEnglish.G_SHARP, 5);

  /** {@link TonePitchEnglish#A} with {@link #getOctave() octave} {@code 5}. */
  public static final Tone A5 = new Tone(TonePitchEnglish.A, 5);

  /** {@link TonePitchEnglish#B_FLAT} with {@link #getOctave() octave} {@code 5}. */
  public static final Tone BF5 = new Tone(TonePitchEnglish.B_FLAT, 5);

  /** {@link TonePitchEnglish#B} with {@link #getOctave() octave} {@code 5}. */
  public static final Tone B5 = new Tone(TonePitchEnglish.B, 5);

  /** {@link TonePitchEnglish#C} with {@link #getOctave() octave} {@code 6}. */
  public static final Tone C6 = new Tone(TonePitchEnglish.C, 6);

  /** {@link TonePitchEnglish#C_SHARP} with {@link #getOctave() octave} {@code 6}. */
  public static final Tone CS6 = new Tone(TonePitchEnglish.C_SHARP, 6);

  /** {@link TonePitchEnglish#D} with {@link #getOctave() octave} {@code 6}. */
  public static final Tone D6 = new Tone(TonePitchEnglish.D, 6);

  /** {@link TonePitchEnglish#D_SHARP} with {@link #getOctave() octave} {@code 6}. */
  public static final Tone DS6 = new Tone(TonePitchEnglish.D_SHARP, 6);

  /** {@link TonePitchEnglish#E} with {@link #getOctave() octave} {@code 6}. */
  public static final Tone E6 = new Tone(TonePitchEnglish.E, 6);

  /** {@link TonePitchEnglish#F} with {@link #getOctave() octave} {@code 6}. */
  public static final Tone F6 = new Tone(TonePitchEnglish.F, 6);

  /** {@link TonePitchEnglish#F_SHARP} with {@link #getOctave() octave} {@code 6}. */
  public static final Tone FS6 = new Tone(TonePitchEnglish.F_SHARP, 6);

  /** {@link TonePitchEnglish#G} with {@link #getOctave() octave} {@code 6}. */
  public static final Tone G6 = new Tone(TonePitchEnglish.G, 6);

  /** {@link TonePitchEnglish#G_SHARP} with {@link #getOctave() octave} {@code 6}. */
  public static final Tone GS6 = new Tone(TonePitchEnglish.G_SHARP, 6);

  /** {@link TonePitchEnglish#A} with {@link #getOctave() octave} {@code 6}. */
  public static final Tone A6 = new Tone(TonePitchEnglish.A, 6);

  /** {@link TonePitchEnglish#B_FLAT} with {@link #getOctave() octave} {@code 6}. */
  public static final Tone BF6 = new Tone(TonePitchEnglish.B_FLAT, 6);

  /** {@link TonePitchEnglish#B} with {@link #getOctave() octave} {@code 6}. */
  public static final Tone B6 = new Tone(TonePitchEnglish.B, 6);

  /** {@link TonePitchEnglish#C} with {@link #getOctave() octave} {@code 7}. */
  public static final Tone C7 = new Tone(TonePitchEnglish.C, 7);

  /** {@link TonePitchEnglish#C_SHARP} with {@link #getOctave() octave} {@code 7}. */
  public static final Tone CS7 = new Tone(TonePitchEnglish.C_SHARP, 7);

  /** {@link TonePitchEnglish#D} with {@link #getOctave() octave} {@code 7}. */
  public static final Tone D7 = new Tone(TonePitchEnglish.D, 7);

  /** {@link TonePitchEnglish#D_SHARP} with {@link #getOctave() octave} {@code 7}. */
  public static final Tone DS7 = new Tone(TonePitchEnglish.D_SHARP, 7);

  /** {@link TonePitchEnglish#E} with {@link #getOctave() octave} {@code 7}. */
  public static final Tone E7 = new Tone(TonePitchEnglish.E, 7);

  /** {@link TonePitchEnglish#F} with {@link #getOctave() octave} {@code 7}. */
  public static final Tone F7 = new Tone(TonePitchEnglish.F, 7);

  /** {@link TonePitchEnglish#F_SHARP} with {@link #getOctave() octave} {@code 7}. */
  public static final Tone FS7 = new Tone(TonePitchEnglish.F_SHARP, 7);

  /** {@link TonePitchEnglish#G} with {@link #getOctave() octave} {@code 7}. */
  public static final Tone G7 = new Tone(TonePitchEnglish.G, 7);

  /** {@link TonePitchEnglish#G_SHARP} with {@link #getOctave() octave} {@code 7}. */
  public static final Tone GS7 = new Tone(TonePitchEnglish.G_SHARP, 7);

  /** {@link TonePitchEnglish#A} with {@link #getOctave() octave} {@code 7}. */
  public static final Tone A7 = new Tone(TonePitchEnglish.A, 7);

  /** {@link TonePitchEnglish#B_FLAT} with {@link #getOctave() octave} {@code 7}. */
  public static final Tone BF7 = new Tone(TonePitchEnglish.B_FLAT, 7);

  /** {@link TonePitchEnglish#B} with {@link #getOctave() octave} {@code 7}. */
  public static final Tone B7 = new Tone(TonePitchEnglish.B, 7);

  /** {@link TonePitchEnglish#C} with {@link #getOctave() octave} {@code 8}. */
  public static final Tone C8 = new Tone(TonePitchEnglish.C, 8);

  /** {@link TonePitchEnglish#C_SHARP} with {@link #getOctave() octave} {@code 8}. */
  public static final Tone CS8 = new Tone(TonePitchEnglish.C_SHARP, 8);

  /** {@link TonePitchEnglish#D} with {@link #getOctave() octave} {@code 8}. */
  public static final Tone D8 = new Tone(TonePitchEnglish.D, 8);

  /** {@link TonePitchEnglish#D_SHARP} with {@link #getOctave() octave} {@code 8}. */
  public static final Tone DS8 = new Tone(TonePitchEnglish.D_SHARP, 8);

  /** {@link TonePitchEnglish#E} with {@link #getOctave() octave} {@code 8}. */
  public static final Tone E8 = new Tone(TonePitchEnglish.E, 8);

  /** {@link TonePitchEnglish#F} with {@link #getOctave() octave} {@code 8}. */
  public static final Tone F8 = new Tone(TonePitchEnglish.F, 8);

  /** {@link TonePitchEnglish#F_SHARP} with {@link #getOctave() octave} {@code 8}. */
  public static final Tone FS8 = new Tone(TonePitchEnglish.F_SHARP, 8);

  /** {@link TonePitchEnglish#G} with {@link #getOctave() octave} {@code 8}. */
  public static final Tone G8 = new Tone(TonePitchEnglish.G, 8);

  /** {@link TonePitchEnglish#G_SHARP} with {@link #getOctave() octave} {@code 8}. */
  public static final Tone GS8 = new Tone(TonePitchEnglish.G_SHARP, 8);

  /** {@link TonePitchEnglish#A} with {@link #getOctave() octave} {@code 8}. */
  public static final Tone A8 = new Tone(TonePitchEnglish.A, 8);

  /** {@link TonePitchEnglish#B_FLAT} with {@link #getOctave() octave} {@code 8}. */
  public static final Tone BF8 = new Tone(TonePitchEnglish.B_FLAT, 8);

  /** {@link TonePitchEnglish#B} with {@link #getOctave() octave} {@code 8}. */
  public static final Tone B8 = new Tone(TonePitchEnglish.B, 8);

  private static final Tone[] TONES = new Tone[] { C0, CS0, D0, DS0, E0, F0, FS0, G0, GS0, A0, BF0, B0, C1, CS1, D1,
  DS1, E1, F1, FS1, G1, GS1, A1, BF1, B1, C2, CS2, D2, DS2, E2, F2, FS2, G2, GS2, A2, BF2, B2, C3, CS3, D3, DS3, E3, F3,
  FS3, G3, GS3, A3, BF3, B3, C4, CS4, D4, DS4, E4, F4, FS4, G4, GS4, A4, BF4, B4, C5, CS5, D5, DS5, E5, F5, FS5, G5,
  GS5, A5, BF5, B5, C6, CS6, D6, DS6, E6, F6, FS6, G6, GS6, A6, BF6, B6, C7, CS7, D7, DS7, E7, F7, FS7, G7, GS7, A7,
  BF7, B7, C8, CS8, D8, DS8, E8, F8, FS8, G8, GS8, A8, BF8, B8 };

  private final TonePitch pitch;

  private final int octave;

  private final String name;

  private final boolean absolute;

  /**
   * The constructor.
   *
   * @param pitch - see {@link #getPitch()}.
   * @param octave - see {@link #getOctave()}.
   */
  private Tone(TonePitch pitch, int octave) {

    this(pitch, octave, false);
  }

  /**
   * The constructor.
   *
   * @param pitch - see {@link #getPitch()}.
   * @param octave - see {@link #getOctave()}.
   */
  private Tone(TonePitch pitch, int octave, boolean absolute) {

    super();
    this.pitch = pitch;
    this.octave = octave;
    this.absolute = absolute;
    this.name = getName(TonePitchEnglish.STYLE);
  }

  /**
   * @return the {@link TonePitch} within the {@link #getOctave() octave}.
   */
  public TonePitch getPitch() {

    return this.pitch;
  }

  /**
   * @return the octave the {@link #getPitch() pitch} is located. A value of {@code 0} is the lowest octave on a piano
   *         (starting with {@link #A0}). The regular octave in {@link Clef#TREBLE treble-clef} is {@code 4} starting
   *         with the {@link #C4 low C} (below the scale) and ending with {@link #B4 B♯4} in the middle of the scale. A
   *         higher value is used to go up an octave and a lower value is used to go down (e.g. {@code 3} and {@code 2}
   *         for {@link Clef#BASS bass-clef}).
   */
  public int getOctave() {

    return this.octave;
  }

  /**
   * @return the number of semitone steps upwards from {@code C0}.
   * @see TonePitch#getStep()
   */
  public int getStep() {

    return (this.octave * 12) + this.pitch.getStep().get();
  }

  /**
   * @return {@code true} if the {@link #getOctave() octave} was parsed and shall be formatted absolute, {@code false}
   *         otherwise (default).
   */
  public boolean isAbsolute() {

    return this.absolute;
  }

  @Override
  public int compareTo(Tone tone) {

    return getStep() - tone.getStep();
  }

  /**
   * @param other the {@link Tone} to compare with.
   * @return {@code true} if this {@link Tone} is lower than the other given {@link Tone}.
   */
  public boolean isLower(Tone other) {

    if (this.octave < other.octave) {
      return true;
    } else if (this.octave == other.octave) {
      return this.pitch.getStep().get() < other.pitch.getStep().get();
    } else {
      return false;
    }
  }

  /**
   * @param other the {@link Tone} to compare with.
   * @return {@code true} if this {@link Tone} is lower than or equal to the other given {@link Tone}.
   */
  public boolean isLowerOrEqual(Tone other) {

    if (this.octave < other.octave) {
      return true;
    } else if (this.octave == other.octave) {
      return this.pitch.getStep().get() <= other.pitch.getStep().get();
    } else {
      return false;
    }
  }

  /**
   * @param other the {@link Tone} to compare with.
   * @return {@code true} if this {@link Tone} is higher than the other given {@link Tone}.
   */
  public boolean isHigher(Tone other) {

    if (this.octave > other.octave) {
      return true;
    } else if (this.octave == other.octave) {
      return this.pitch.getStep().get() > other.pitch.getStep().get();
    } else {
      return false;
    }
  }

  /**
   * @param other the {@link Tone} to compare with.
   * @return {@code true} if this {@link Tone} is higher than or equal to the other given {@link Tone}.
   */
  public boolean isHigherOrEqual(Tone other) {

    if (this.octave > other.octave) {
      return true;
    } else if (this.octave == other.octave) {
      return this.pitch.getStep().get() >= other.pitch.getStep().get();
    } else {
      return false;
    }
  }

  /**
   * Computes the {@link ChromaticInterval} from this {@link Tone} to the given {@link Tone} ({@code targetTpne}. Unlike
   * {@link TonePitch#getInterval(TonePitch)} this method will return a directional {@link ChromaticInterval} that will
   * be negative in case this {@link Tone} {@link #isHigher(Tone) is higher} than the given {@link Tone}. Also, it will
   * cover all steps and can exceed one or multiple octaves.
   *
   * @param targetTone is the target {@link TonePitch}.
   * @return the {@link ChromaticInterval} to {@link #transpose(ToneInterval, TransposeContext) get} from this
   *         {@link TonePitch} to the given {@code targetTone}.
   */
  public ChromaticInterval getInterval(Tone targetTone) {

    int chromaticSteps = targetTone.getStep() - getStep();
    return ChromaticInterval.of(chromaticSteps);
  }

  @Override
  public Tone transpose(int steps, boolean diatonic, TransposeContext context) {

    TonePitch transposedPitch = this.pitch.transpose(steps, diatonic, context);
    MusicalKey key = context.getKey();
    if (diatonic) {
      if (key == null) {
        int chromaticSteps = getChromaticSteps(this.pitch, transposedPitch, steps);
        return transposeOctaveChromatic(transposedPitch, chromaticSteps);
      } else {
        // TODO this is so deadly wrong
        int targetStep = (this.pitch.getStep().get() + steps - key.getTonika().getStep().get()) % 8;
        int octaveStep = targetStep / 8;
        int resultOctave = this.octave + octaveStep;
        return new Tone(transposedPitch, resultOctave, this.absolute);
      }
    } else {
      return transposeOctaveChromatic(transposedPitch, steps);
    }
  }

  private Tone transposeOctaveChromatic(TonePitch resultPitch, int chromaticSteps) {

    int pitchSteps = resultPitch.getStep().get() - this.pitch.getStep().get();
    int octaveSteps;
    if (chromaticSteps < 0) {
      octaveSteps = chromaticSteps / 12;
      if (pitchSteps > 0) {
        // extra octave step when stepping below C
        octaveSteps--;
      }
    } else {
      octaveSteps = chromaticSteps / 12;
      if (pitchSteps < 0) {
        // extra octave step when stepping above B/H
        octaveSteps++;
      }
    }
    int resultOctave = this.octave + octaveSteps;
    return of(resultPitch, resultOctave);
  }

  /**
   * @param style the {@link ToneNameStyle}.
   * @return the name of this {@link Tone} using the given {@link ToneNameStyle}.
   */
  public String getName(ToneNameStyle<?> style) {

    return this.pitch.with(style).getName() + this.octave;
  }

  /**
   * @param style the {@link ToneNameStyle}.
   * @return the name of this {@link Tone} using the given {@link ToneNameStyle}.
   */
  public String getNameAbc(ToneNameStyle<?> style) {

    return getNameAbc(style, 4);
  }

  /**
   * @param style the {@link ToneNameStyle}.
   * @param clef the {@link Clef} the name should be relative to.
   * @return the name of this {@link Tone} using the given {@link ToneNameStyle} and {@link Clef}.
   */
  public String getNameAbc(ToneNameStyle<?> style, Clef clef) {

    return getNameAbc(style, clef.getMiddleTone().octave);
  }

  /**
   * @param style the {@link ToneNameStyle}.
   * @param clefOctave the octave of the stave {@link io.github.musicdoc.music.clef.Clef}. After this octave the
   *        {@link Tone} case changes ("B" -> "c"). For {@link Clef#TREBLE treble clef} this value is {@code 4} ( C" =
   *        "C4" and "c" = "C5").
   * @return the name of this {@link Tone} using the given {@link ToneNameStyle} and {@code clefOctave}
   */
  public String getNameAbc(ToneNameStyle<?> style, int clefOctave) {

    String result = this.pitch.with(style).getName();
    if (this.octave > clefOctave) {
      result = result.toLowerCase(Locale.US);
      for (int i = clefOctave + 1; i < this.octave; i++) {
        result = result + OCTAVE_UP;
      }
    } else {
      for (int i = this.octave; i < clefOctave; i++) {
        result = result + OCTAVE_DOWN;
      }
    }
    return result;
  }

  /**
   * @return the regular display name of this {@link Tone}.
   */
  public String getName() {

    return this.name;
  }

  @Override
  public int hashCode() {

    final int prime = 31;
    int result = 1;
    result = prime * result + this.octave;
    result = prime * result + ((this.pitch == null) ? 0 : this.pitch.hashCode());
    return result;
  }

  /**
   * @param other the other {@link Tone} to compare.
   * @return {@code true} if this {@link Tone} and the given {@link Tone} are equal (have the same {@link #getStep()
   *         step}).
   */
  public boolean isEqualTo(Tone other) {

    if (other == null) {
      return false;
    } else if (other == this) {
      return true;
    }
    if ((this.octave != other.octave) || !this.pitch.isEqualTo(other.pitch)) {
      return false;
    }
    return true;
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }
    if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }
    return isEqualTo((Tone) obj);
  }

  @Override
  public String toString() {

    return this.name;
  }

  @Override
  public void toString(StringBuilder sb) {

    sb.append(this.name);
  }

  /**
   * @param pitch the {@link #getPitch() pitch}.
   * @param octave the {@link #getOctave() octave}.
   * @return the specified {@link Tone}.
   */
  public static Tone of(TonePitch pitch, int octave) {

    return of(pitch, octave, false);
  }

  /**
   * @param pitch the {@link #getPitch() pitch}.
   * @param octave the {@link #getOctave() octave}.
   * @param absolute the {@link #isAbsolute() absolute} flag.
   * @return the specified {@link Tone}.
   */
  public static Tone of(TonePitch pitch, int octave, boolean absolute) {

    if ((octave >= 0) && (octave <= 8) && pitch.isNormal() && !absolute) {
      int step = (octave * 12) + pitch.getStep().get();
      return TONES[step];
    }
    return new Tone(pitch, octave, absolute);
  }

}
