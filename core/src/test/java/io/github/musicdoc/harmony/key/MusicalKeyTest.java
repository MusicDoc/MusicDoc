/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.harmony.key;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.musicdoc.harmony.EnharmonicStyle;
import io.github.musicdoc.harmony.TonalSystem;
import io.github.musicdoc.interval.Solmization;
import io.github.musicdoc.tone.TonePitchEnglish;

/**
 * Test of {@link MusicalKey}.
 */
public class MusicalKeyTest extends Assertions {

  /**
   * Test of {@link MusicalKey#C_MAJOR}.
   */
  @Test
  public void testCMajor() {

    MusicalKey key = MusicalKey.C_MAJOR;
    assertThat(key.getName()).isEqualTo("C");
    assertThat(key.getSystem()).isSameAs(TonalSystem.MAJOR);
    assertThat(key.getTonika()).isSameAs(TonePitchEnglish.C);
    assertThat(key.getEnharmonicStyle()).isSameAs(EnharmonicStyle.NORMAL);
    assertThat(key.getDiatonicScale()).containsExactly(TonePitchEnglish.C, TonePitchEnglish.D, TonePitchEnglish.E,
        TonePitchEnglish.F, TonePitchEnglish.G, TonePitchEnglish.A, TonePitchEnglish.B);
    assertThat(key.getChromaticScale()).containsExactly(TonePitchEnglish.C, TonePitchEnglish.C_SHARP,
        TonePitchEnglish.D, TonePitchEnglish.D_SHARP, TonePitchEnglish.E, TonePitchEnglish.F, TonePitchEnglish.F_SHARP,
        TonePitchEnglish.G, TonePitchEnglish.G_SHARP, TonePitchEnglish.A, TonePitchEnglish.B_FLAT, TonePitchEnglish.B);
    assertThat(key.getChromaticSignTones()).isEmpty();
  }

  /**
   * Test of {@link MusicalKey#C_SHARP_MAJOR}.
   */
  @Test
  public void testCSharpMajor() {

    MusicalKey key = MusicalKey.C_SHARP_MAJOR;
    assertThat(key.getName()).isEqualTo("C#");
    assertThat(key.getSystem()).isSameAs(TonalSystem.MAJOR);
    assertThat(key.getTonika()).isSameAs(TonePitchEnglish.C_SHARP);
    assertThat(key.getEnharmonicStyle()).isSameAs(EnharmonicStyle.SHARP);
    assertThat(key.getDiatonicScale()).containsExactly(TonePitchEnglish.C_SHARP, TonePitchEnglish.D_SHARP,
        TonePitchEnglish.E_SHARP, TonePitchEnglish.F_SHARP, TonePitchEnglish.G_SHARP, TonePitchEnglish.A_SHARP,
        TonePitchEnglish.B_SHARP);
    assertThat(key.getChromaticScale()).containsExactly(TonePitchEnglish.C_SHARP, TonePitchEnglish.D,
        TonePitchEnglish.D_SHARP, TonePitchEnglish.E, TonePitchEnglish.E_SHARP, TonePitchEnglish.F_SHARP,
        TonePitchEnglish.G, TonePitchEnglish.G_SHARP, TonePitchEnglish.A, TonePitchEnglish.A_SHARP, TonePitchEnglish.B,
        TonePitchEnglish.B_SHARP);
    assertThat(key.getChromaticSignTones()).containsExactly(TonePitchEnglish.F_SHARP, TonePitchEnglish.C_SHARP,
        TonePitchEnglish.G_SHARP, TonePitchEnglish.D_SHARP, TonePitchEnglish.A_SHARP, TonePitchEnglish.E_SHARP,
        TonePitchEnglish.B_SHARP);
  }

  /**
   * Test of {@link MusicalKey#D_FLAT_MAJOR}.
   */
  @Test
  public void testDFlatMajor() {

    MusicalKey key = MusicalKey.D_FLAT_MAJOR;
    assertThat(key.getName()).isEqualTo("Db");
    assertThat(key.getSystem()).isSameAs(TonalSystem.MAJOR);
    assertThat(key.getTonika()).isSameAs(TonePitchEnglish.D_FLAT);
    assertThat(key.getEnharmonicStyle()).isSameAs(EnharmonicStyle.FLAT);
    assertThat(key.getDiatonicScale()).containsExactly(TonePitchEnglish.D_FLAT, TonePitchEnglish.E_FLAT,
        TonePitchEnglish.F, TonePitchEnglish.G_FLAT, TonePitchEnglish.A_FLAT, TonePitchEnglish.B_FLAT,
        TonePitchEnglish.C);
    assertThat(key.getChromaticScale()).containsExactly(TonePitchEnglish.D_FLAT, TonePitchEnglish.D,
        TonePitchEnglish.E_FLAT, TonePitchEnglish.E, TonePitchEnglish.F, TonePitchEnglish.G_FLAT, TonePitchEnglish.G,
        TonePitchEnglish.A_FLAT, TonePitchEnglish.A, TonePitchEnglish.B_FLAT, TonePitchEnglish.B, TonePitchEnglish.C);
    assertThat(key.getChromaticSignTones()).containsExactly(TonePitchEnglish.B_FLAT, TonePitchEnglish.E_FLAT,
        TonePitchEnglish.A_FLAT, TonePitchEnglish.D_FLAT, TonePitchEnglish.G_FLAT);
  }

  /**
   * Test of {@link MusicalKey#D_MAJOR}.
   */
  @Test
  public void testDMajor() {

    MusicalKey key = MusicalKey.D_MAJOR;
    assertThat(key.getName()).isEqualTo("D");
    assertThat(key.getSystem()).isSameAs(TonalSystem.MAJOR);
    assertThat(key.getTonika()).isSameAs(TonePitchEnglish.D);
    assertThat(key.getEnharmonicStyle()).isSameAs(EnharmonicStyle.SHARP);
    assertThat(key.getDiatonicScale()).containsExactly(TonePitchEnglish.D, TonePitchEnglish.E, TonePitchEnglish.F_SHARP,
        TonePitchEnglish.G, TonePitchEnglish.A, TonePitchEnglish.B, TonePitchEnglish.C_SHARP);
    assertThat(key.getChromaticScale()).containsExactly(TonePitchEnglish.D, TonePitchEnglish.D_SHARP,
        TonePitchEnglish.E, TonePitchEnglish.F, TonePitchEnglish.F_SHARP, TonePitchEnglish.G, TonePitchEnglish.G_SHARP,
        TonePitchEnglish.A, TonePitchEnglish.A_SHARP, TonePitchEnglish.B, TonePitchEnglish.C, TonePitchEnglish.C_SHARP);
    assertThat(key.getChromaticSignTones()).containsExactly(TonePitchEnglish.F_SHARP, TonePitchEnglish.C_SHARP);
  }

  /**
   * Test of {@link MusicalKey#E_FLAT_MAJOR}.
   */
  @Test
  public void testEFlatMajor() {

    MusicalKey key = MusicalKey.E_FLAT_MAJOR;
    assertThat(key.getName()).isEqualTo("Eb");
    assertThat(key.getSystem()).isSameAs(TonalSystem.MAJOR);
    assertThat(key.getTonika()).isSameAs(TonePitchEnglish.E_FLAT);
    assertThat(key.getEnharmonicStyle()).isSameAs(EnharmonicStyle.FLAT);
    assertThat(key.getDiatonicScale()).containsExactly(TonePitchEnglish.E_FLAT, TonePitchEnglish.F, TonePitchEnglish.G,
        TonePitchEnglish.A_FLAT, TonePitchEnglish.B_FLAT, TonePitchEnglish.C, TonePitchEnglish.D);
    assertThat(key.getChromaticScale()).containsExactly(TonePitchEnglish.E_FLAT, TonePitchEnglish.E, TonePitchEnglish.F,
        TonePitchEnglish.G_FLAT, TonePitchEnglish.G, TonePitchEnglish.A_FLAT, TonePitchEnglish.A,
        TonePitchEnglish.B_FLAT, TonePitchEnglish.B, TonePitchEnglish.C, TonePitchEnglish.D_FLAT, TonePitchEnglish.D);
    assertThat(key.getChromaticSignTones()).containsExactly(TonePitchEnglish.B_FLAT, TonePitchEnglish.E_FLAT,
        TonePitchEnglish.A_FLAT);
  }

  /**
   * Test of {@link MusicalKey#E_MAJOR}.
   */
  @Test
  public void testEMajor() {

    MusicalKey key = MusicalKey.E_MAJOR;
    assertThat(key.getName()).isEqualTo("E");
    assertThat(key.getSystem()).isSameAs(TonalSystem.MAJOR);
    assertThat(key.getTonika()).isSameAs(TonePitchEnglish.E);
    assertThat(key.getEnharmonicStyle()).isSameAs(EnharmonicStyle.SHARP);
    assertThat(key.getDiatonicScale()).containsExactly(TonePitchEnglish.E, TonePitchEnglish.F_SHARP,
        TonePitchEnglish.G_SHARP, TonePitchEnglish.A, TonePitchEnglish.B, TonePitchEnglish.C_SHARP,
        TonePitchEnglish.D_SHARP);
    assertThat(key.getChromaticScale()).containsExactly(TonePitchEnglish.E, TonePitchEnglish.F,
        TonePitchEnglish.F_SHARP, TonePitchEnglish.G, TonePitchEnglish.G_SHARP, TonePitchEnglish.A,
        TonePitchEnglish.A_SHARP, TonePitchEnglish.B, TonePitchEnglish.C, TonePitchEnglish.C_SHARP, TonePitchEnglish.D,
        TonePitchEnglish.D_SHARP);
    assertThat(key.getChromaticSignTones()).containsExactly(TonePitchEnglish.F_SHARP, TonePitchEnglish.C_SHARP,
        TonePitchEnglish.G_SHARP, TonePitchEnglish.D_SHARP);
  }

  /**
   * Test of {@link MusicalKey#F_MAJOR}.
   */
  @Test
  public void testFMajor() {

    MusicalKey key = MusicalKey.F_MAJOR;
    assertThat(key.getName()).isEqualTo("F");
    assertThat(key.getSystem()).isSameAs(TonalSystem.MAJOR);
    assertThat(key.getTonika()).isSameAs(TonePitchEnglish.F);
    assertThat(key.getEnharmonicStyle()).isSameAs(EnharmonicStyle.FLAT);
    assertThat(key.getDiatonicScale()).containsExactly(TonePitchEnglish.F, TonePitchEnglish.G, TonePitchEnglish.A,
        TonePitchEnglish.B_FLAT, TonePitchEnglish.C, TonePitchEnglish.D, TonePitchEnglish.E);
    assertThat(key.getChromaticScale()).containsExactly(TonePitchEnglish.F, TonePitchEnglish.G_FLAT, TonePitchEnglish.G,
        TonePitchEnglish.A_FLAT, TonePitchEnglish.A, TonePitchEnglish.B_FLAT, TonePitchEnglish.B, TonePitchEnglish.C,
        TonePitchEnglish.D_FLAT, TonePitchEnglish.D, TonePitchEnglish.E_FLAT, TonePitchEnglish.E);
    assertThat(key.getChromaticSignTones()).containsExactly(TonePitchEnglish.B_FLAT);
  }

  /**
   * Test of {@link MusicalKey#F_SHARP_MAJOR}.
   */
  @Test
  public void testFSharpMajor() {

    MusicalKey key = MusicalKey.F_SHARP_MAJOR;
    assertThat(key.getName()).isEqualTo("F#");
    assertThat(key.getSystem()).isSameAs(TonalSystem.MAJOR);
    assertThat(key.getTonika()).isSameAs(TonePitchEnglish.F_SHARP);
    assertThat(key.getEnharmonicStyle()).isSameAs(EnharmonicStyle.SHARP);
    assertThat(key.getDiatonicScale()).containsExactly(TonePitchEnglish.F_SHARP, TonePitchEnglish.G_SHARP,
        TonePitchEnglish.A_SHARP, TonePitchEnglish.B, TonePitchEnglish.C_SHARP, TonePitchEnglish.D_SHARP,
        TonePitchEnglish.E_SHARP);
    assertThat(key.getChromaticScale()).containsExactly(TonePitchEnglish.F_SHARP, TonePitchEnglish.G,
        TonePitchEnglish.G_SHARP, TonePitchEnglish.A, TonePitchEnglish.A_SHARP, TonePitchEnglish.B, TonePitchEnglish.C,
        TonePitchEnglish.C_SHARP, TonePitchEnglish.D, TonePitchEnglish.D_SHARP, TonePitchEnglish.E,
        TonePitchEnglish.E_SHARP);
    assertThat(key.getChromaticSignTones()).containsExactly(TonePitchEnglish.F_SHARP, TonePitchEnglish.C_SHARP,
        TonePitchEnglish.G_SHARP, TonePitchEnglish.D_SHARP, TonePitchEnglish.A_SHARP, TonePitchEnglish.E_SHARP);
  }

  /**
   * Test of {@link MusicalKey#G_FLAT_MAJOR}.
   */
  @Test
  public void testGFlatMajor() {

    MusicalKey key = MusicalKey.G_FLAT_MAJOR;
    assertThat(key.getName()).isEqualTo("Gb");
    assertThat(key.getSystem()).isSameAs(TonalSystem.MAJOR);
    assertThat(key.getTonika()).isSameAs(TonePitchEnglish.G_FLAT);
    assertThat(key.getEnharmonicStyle()).isSameAs(EnharmonicStyle.FLAT);
    assertThat(key.getDiatonicScale()).containsExactly(TonePitchEnglish.G_FLAT, TonePitchEnglish.A_FLAT,
        TonePitchEnglish.B_FLAT, TonePitchEnglish.C_FLAT, TonePitchEnglish.D_FLAT, TonePitchEnglish.E_FLAT,
        TonePitchEnglish.F);
    assertThat(key.getChromaticScale()).containsExactly(TonePitchEnglish.G_FLAT, TonePitchEnglish.G,
        TonePitchEnglish.A_FLAT, TonePitchEnglish.A, TonePitchEnglish.B_FLAT, TonePitchEnglish.C_FLAT,
        TonePitchEnglish.C, TonePitchEnglish.D_FLAT, TonePitchEnglish.D, TonePitchEnglish.E_FLAT, TonePitchEnglish.E,
        TonePitchEnglish.F);
    assertThat(key.getChromaticSignTones()).containsExactly(TonePitchEnglish.B_FLAT, TonePitchEnglish.E_FLAT,
        TonePitchEnglish.A_FLAT, TonePitchEnglish.D_FLAT, TonePitchEnglish.G_FLAT, TonePitchEnglish.C_FLAT);
  }

  /**
   * Test of {@link MusicalKey#G_MAJOR}.
   */
  @Test
  public void testGMajor() {

    MusicalKey key = MusicalKey.G_MAJOR;
    assertThat(key.getName()).isEqualTo("G");
    assertThat(key.getSystem()).isSameAs(TonalSystem.MAJOR);
    assertThat(key.getTonika()).isSameAs(TonePitchEnglish.G);
    assertThat(key.getEnharmonicStyle()).isSameAs(EnharmonicStyle.SHARP);
    assertThat(key.getDiatonicScale()).containsExactly(TonePitchEnglish.G, TonePitchEnglish.A, TonePitchEnglish.B,
        TonePitchEnglish.C, TonePitchEnglish.D, TonePitchEnglish.E, TonePitchEnglish.F_SHARP);
    assertThat(key.getChromaticScale()).containsExactly(TonePitchEnglish.G, TonePitchEnglish.G_SHARP,
        TonePitchEnglish.A, TonePitchEnglish.A_SHARP, TonePitchEnglish.B, TonePitchEnglish.C, TonePitchEnglish.C_SHARP,
        TonePitchEnglish.D, TonePitchEnglish.D_SHARP, TonePitchEnglish.E, TonePitchEnglish.F, TonePitchEnglish.F_SHARP);
    assertThat(key.getChromaticSignTones()).containsExactly(TonePitchEnglish.F_SHARP);
  }

  /**
   * Test of {@link MusicalKey#A_FLAT_MAJOR}.
   */
  @Test
  public void testAFlatMajor() {

    MusicalKey key = MusicalKey.A_FLAT_MAJOR;
    assertThat(key.getName()).isEqualTo("Ab");
    assertThat(key.getSystem()).isSameAs(TonalSystem.MAJOR);
    assertThat(key.getTonika()).isSameAs(TonePitchEnglish.A_FLAT);
    assertThat(key.getEnharmonicStyle()).isSameAs(EnharmonicStyle.FLAT);
    assertThat(key.getDiatonicScale()).containsExactly(TonePitchEnglish.A_FLAT, TonePitchEnglish.B_FLAT,
        TonePitchEnglish.C, TonePitchEnglish.D_FLAT, TonePitchEnglish.E_FLAT, TonePitchEnglish.F, TonePitchEnglish.G);
    assertThat(key.getChromaticScale()).containsExactly(TonePitchEnglish.A_FLAT, TonePitchEnglish.A,
        TonePitchEnglish.B_FLAT, TonePitchEnglish.B, TonePitchEnglish.C, TonePitchEnglish.D_FLAT, TonePitchEnglish.D,
        TonePitchEnglish.E_FLAT, TonePitchEnglish.E, TonePitchEnglish.F, TonePitchEnglish.G_FLAT, TonePitchEnglish.G);
    assertThat(key.getChromaticSignTones()).containsExactly(TonePitchEnglish.B_FLAT, TonePitchEnglish.E_FLAT,
        TonePitchEnglish.A_FLAT, TonePitchEnglish.D_FLAT);
  }

  /**
   * Test of {@link MusicalKey#A_MAJOR}.
   */
  @Test
  public void testAMajor() {

    MusicalKey key = MusicalKey.A_MAJOR;
    assertThat(key.getName()).isEqualTo("A");
    assertThat(key.getSystem()).isSameAs(TonalSystem.MAJOR);
    assertThat(key.getTonika()).isSameAs(TonePitchEnglish.A);
    assertThat(key.getEnharmonicStyle()).isSameAs(EnharmonicStyle.SHARP);
    assertThat(key.getDiatonicScale()).containsExactly(TonePitchEnglish.A, TonePitchEnglish.B, TonePitchEnglish.C_SHARP,
        TonePitchEnglish.D, TonePitchEnglish.E, TonePitchEnglish.F_SHARP, TonePitchEnglish.G_SHARP);
    assertThat(key.getChromaticScale()).containsExactly(TonePitchEnglish.A, TonePitchEnglish.A_SHARP,
        TonePitchEnglish.B, TonePitchEnglish.C, TonePitchEnglish.C_SHARP, TonePitchEnglish.D, TonePitchEnglish.D_SHARP,
        TonePitchEnglish.E, TonePitchEnglish.F, TonePitchEnglish.F_SHARP, TonePitchEnglish.G, TonePitchEnglish.G_SHARP);
    assertThat(key.getChromaticSignTones()).containsExactly(TonePitchEnglish.F_SHARP, TonePitchEnglish.C_SHARP,
        TonePitchEnglish.G_SHARP);
  }

  /**
   * Test of {@link MusicalKey#B_FLAT_MAJOR}.
   */
  @Test
  public void testBFlatMajor() {

    MusicalKey key = MusicalKey.B_FLAT_MAJOR;
    assertThat(key.getName()).isEqualTo("Bb");
    assertThat(key.getSystem()).isSameAs(TonalSystem.MAJOR);
    assertThat(key.getTonika()).isSameAs(TonePitchEnglish.B_FLAT);
    assertThat(key.getEnharmonicStyle()).isSameAs(EnharmonicStyle.FLAT);
    assertThat(key.getDiatonicScale()).containsExactly(TonePitchEnglish.B_FLAT, TonePitchEnglish.C, TonePitchEnglish.D,
        TonePitchEnglish.E_FLAT, TonePitchEnglish.F, TonePitchEnglish.G, TonePitchEnglish.A);
    assertThat(key.getChromaticScale()).containsExactly(TonePitchEnglish.B_FLAT, TonePitchEnglish.B, TonePitchEnglish.C,
        TonePitchEnglish.D_FLAT, TonePitchEnglish.D, TonePitchEnglish.E_FLAT, TonePitchEnglish.E, TonePitchEnglish.F,
        TonePitchEnglish.G_FLAT, TonePitchEnglish.G, TonePitchEnglish.A_FLAT, TonePitchEnglish.A);
    assertThat(key.getChromaticSignTones()).containsExactly(TonePitchEnglish.B_FLAT, TonePitchEnglish.E_FLAT);
  }

  /**
   * Test of {@link MusicalKey#B_MAJOR}.
   */
  @Test
  public void testBMajor() {

    MusicalKey key = MusicalKey.B_MAJOR;
    assertThat(key.getName()).isEqualTo("B");
    assertThat(key.getSystem()).isSameAs(TonalSystem.MAJOR);
    assertThat(key.getTonika()).isSameAs(TonePitchEnglish.B);
    assertThat(key.getEnharmonicStyle()).isSameAs(EnharmonicStyle.SHARP);
    assertThat(key.getDiatonicScale()).containsExactly(TonePitchEnglish.B, TonePitchEnglish.C_SHARP,
        TonePitchEnglish.D_SHARP, TonePitchEnglish.E, TonePitchEnglish.F_SHARP, TonePitchEnglish.G_SHARP,
        TonePitchEnglish.A_SHARP);
    assertThat(key.getChromaticScale()).containsExactly(TonePitchEnglish.B, TonePitchEnglish.C,
        TonePitchEnglish.C_SHARP, TonePitchEnglish.D, TonePitchEnglish.D_SHARP, TonePitchEnglish.E, TonePitchEnglish.F,
        TonePitchEnglish.F_SHARP, TonePitchEnglish.G, TonePitchEnglish.G_SHARP, TonePitchEnglish.A,
        TonePitchEnglish.A_SHARP);
    assertThat(key.getChromaticSignTones()).containsExactly(TonePitchEnglish.F_SHARP, TonePitchEnglish.C_SHARP,
        TonePitchEnglish.G_SHARP, TonePitchEnglish.D_SHARP, TonePitchEnglish.A_SHARP);
  }

  /**
   * Test of {@link MusicalKey#C_MINOR}.
   */
  @Test
  public void testCMinor() {

    MusicalKey key = MusicalKey.C_MINOR;
    assertThat(key.getName()).isEqualTo("c");
    assertThat(key.getSystem()).isSameAs(TonalSystem.MINOR);
    assertThat(key.getTonika()).isSameAs(TonePitchEnglish.C);
    assertThat(key.getEnharmonicStyle()).isSameAs(EnharmonicStyle.FLAT);
    assertThat(key.getDiatonicScale()).containsExactly(TonePitchEnglish.C, TonePitchEnglish.D, TonePitchEnglish.E_FLAT,
        TonePitchEnglish.F, TonePitchEnglish.G, TonePitchEnglish.A_FLAT, TonePitchEnglish.B_FLAT);
    assertThat(key.getChromaticScale()).containsExactly(TonePitchEnglish.C, TonePitchEnglish.D_FLAT, TonePitchEnglish.D,
        TonePitchEnglish.E_FLAT, TonePitchEnglish.E, TonePitchEnglish.F, TonePitchEnglish.G_FLAT, TonePitchEnglish.G,
        TonePitchEnglish.A_FLAT, TonePitchEnglish.A, TonePitchEnglish.B_FLAT, TonePitchEnglish.B);
    assertThat(key.getChromaticSignTones()).containsExactly(TonePitchEnglish.B_FLAT, TonePitchEnglish.E_FLAT,
        TonePitchEnglish.A_FLAT);
  }

  /**
   * Test of {@link MusicalKey#C_SHARP_MINOR}.
   */
  @Test
  public void testCSharpMinor() {

    MusicalKey key = MusicalKey.C_SHARP_MINOR;
    assertThat(key.getName()).isEqualTo("c#");
    assertThat(key.getSystem()).isSameAs(TonalSystem.MINOR);
    assertThat(key.getTonika()).isSameAs(TonePitchEnglish.C_SHARP);
    assertThat(key.getEnharmonicStyle()).isSameAs(EnharmonicStyle.SHARP);
    assertThat(key.getDiatonicScale()).containsExactly(TonePitchEnglish.C_SHARP, TonePitchEnglish.D_SHARP,
        TonePitchEnglish.E, TonePitchEnglish.F_SHARP, TonePitchEnglish.G_SHARP, TonePitchEnglish.A, TonePitchEnglish.B);
    assertThat(key.getChromaticScale()).containsExactly(TonePitchEnglish.C_SHARP, TonePitchEnglish.D,
        TonePitchEnglish.D_SHARP, TonePitchEnglish.E, TonePitchEnglish.F, TonePitchEnglish.F_SHARP, TonePitchEnglish.G,
        TonePitchEnglish.G_SHARP, TonePitchEnglish.A, TonePitchEnglish.A_SHARP, TonePitchEnglish.B, TonePitchEnglish.C);
    assertThat(key.getChromaticSignTones()).containsExactly(TonePitchEnglish.F_SHARP, TonePitchEnglish.C_SHARP,
        TonePitchEnglish.G_SHARP, TonePitchEnglish.D_SHARP);
  }

  /**
   * Test of {@link MusicalKey#D_MINOR}.
   */
  @Test
  public void testDMinor() {

    MusicalKey key = MusicalKey.D_MINOR;
    assertThat(key.getName()).isEqualTo("d");
    assertThat(key.getSystem()).isSameAs(TonalSystem.MINOR);
    assertThat(key.getTonika()).isSameAs(TonePitchEnglish.D);
    assertThat(key.getEnharmonicStyle()).isSameAs(EnharmonicStyle.FLAT);
    assertThat(key.getDiatonicScale()).containsExactly(TonePitchEnglish.D, TonePitchEnglish.E, TonePitchEnglish.F,
        TonePitchEnglish.G, TonePitchEnglish.A, TonePitchEnglish.B_FLAT, TonePitchEnglish.C);
    assertThat(key.getChromaticScale()).containsExactly(TonePitchEnglish.D, TonePitchEnglish.E_FLAT, TonePitchEnglish.E,
        TonePitchEnglish.F, TonePitchEnglish.G_FLAT, TonePitchEnglish.G, TonePitchEnglish.A_FLAT, TonePitchEnglish.A,
        TonePitchEnglish.B_FLAT, TonePitchEnglish.B, TonePitchEnglish.C, TonePitchEnglish.D_FLAT);
    assertThat(key.getChromaticSignTones()).containsExactly(TonePitchEnglish.B_FLAT);
  }

  /**
   * Test of {@link MusicalKey#D_SHARP_MINOR}.
   */
  @Test
  public void testDSharpMinor() {

    MusicalKey key = MusicalKey.D_SHARP_MINOR;
    assertThat(key.getName()).isEqualTo("d#");
    assertThat(key.getSystem()).isSameAs(TonalSystem.MINOR);
    assertThat(key.getTonika()).isSameAs(TonePitchEnglish.D_SHARP);
    assertThat(key.getEnharmonicStyle()).isSameAs(EnharmonicStyle.SHARP);
    assertThat(key.getDiatonicScale()).containsExactly(TonePitchEnglish.D_SHARP, TonePitchEnglish.E_SHARP,
        TonePitchEnglish.F_SHARP, TonePitchEnglish.G_SHARP, TonePitchEnglish.A_SHARP, TonePitchEnglish.B,
        TonePitchEnglish.C_SHARP);
    assertThat(key.getChromaticScale()).containsExactly(TonePitchEnglish.D_SHARP, TonePitchEnglish.E,
        TonePitchEnglish.E_SHARP, TonePitchEnglish.F_SHARP, TonePitchEnglish.G, TonePitchEnglish.G_SHARP,
        TonePitchEnglish.A, TonePitchEnglish.A_SHARP, TonePitchEnglish.B, TonePitchEnglish.C, TonePitchEnglish.C_SHARP,
        TonePitchEnglish.D);
    assertThat(key.getChromaticSignTones()).containsExactly(TonePitchEnglish.F_SHARP, TonePitchEnglish.C_SHARP,
        TonePitchEnglish.G_SHARP, TonePitchEnglish.D_SHARP, TonePitchEnglish.A_SHARP, TonePitchEnglish.E_SHARP);
  }

  /**
   * Test of {@link MusicalKey#E_FLAT_MINOR}.
   */
  @Test
  public void testEFlatMinor() {

    MusicalKey key = MusicalKey.E_FLAT_MINOR;
    assertThat(key.getName()).isEqualTo("eb");
    assertThat(key.getSystem()).isSameAs(TonalSystem.MINOR);
    assertThat(key.getTonika()).isSameAs(TonePitchEnglish.E_FLAT);
    assertThat(key.getEnharmonicStyle()).isSameAs(EnharmonicStyle.FLAT);
    assertThat(key.getDiatonicScale()).containsExactly(TonePitchEnglish.E_FLAT, TonePitchEnglish.F,
        TonePitchEnglish.G_FLAT, TonePitchEnglish.A_FLAT, TonePitchEnglish.B_FLAT, TonePitchEnglish.C_FLAT,
        TonePitchEnglish.D_FLAT);
    assertThat(key.getChromaticScale()).containsExactly(TonePitchEnglish.E_FLAT, TonePitchEnglish.E, TonePitchEnglish.F,
        TonePitchEnglish.G_FLAT, TonePitchEnglish.G, TonePitchEnglish.A_FLAT, TonePitchEnglish.A,
        TonePitchEnglish.B_FLAT, TonePitchEnglish.C_FLAT, TonePitchEnglish.C, TonePitchEnglish.D_FLAT,
        TonePitchEnglish.D);
    assertThat(key.getChromaticSignTones()).containsExactly(TonePitchEnglish.B_FLAT, TonePitchEnglish.E_FLAT,
        TonePitchEnglish.A_FLAT, TonePitchEnglish.D_FLAT, TonePitchEnglish.G_FLAT, TonePitchEnglish.C_FLAT);
  }

  /**
   * Test of {@link MusicalKey#E_MINOR}.
   */
  @Test
  public void testEMinor() {

    MusicalKey key = MusicalKey.E_MINOR;
    assertThat(key.getName()).isEqualTo("e");
    assertThat(key.getSystem()).isSameAs(TonalSystem.MINOR);
    assertThat(key.getTonika()).isSameAs(TonePitchEnglish.E);
    assertThat(key.getEnharmonicStyle()).isSameAs(EnharmonicStyle.SHARP);
    assertThat(key.getDiatonicScale()).containsExactly(TonePitchEnglish.E, TonePitchEnglish.F_SHARP, TonePitchEnglish.G,
        TonePitchEnglish.A, TonePitchEnglish.B, TonePitchEnglish.C, TonePitchEnglish.D);
    assertThat(key.getChromaticScale()).containsExactly(TonePitchEnglish.E, TonePitchEnglish.F,
        TonePitchEnglish.F_SHARP, TonePitchEnglish.G, TonePitchEnglish.G_SHARP, TonePitchEnglish.A,
        TonePitchEnglish.A_SHARP, TonePitchEnglish.B, TonePitchEnglish.C, TonePitchEnglish.C_SHARP, TonePitchEnglish.D,
        TonePitchEnglish.D_SHARP);
    assertThat(key.getChromaticSignTones()).containsExactly(TonePitchEnglish.F_SHARP);
  }

  /**
   * Test of {@link MusicalKey#F_MINOR}.
   */
  @Test
  public void testFMinor() {

    MusicalKey key = MusicalKey.F_MINOR;
    assertThat(key.getName()).isEqualTo("f");
    assertThat(key.getSystem()).isSameAs(TonalSystem.MINOR);
    assertThat(key.getTonika()).isSameAs(TonePitchEnglish.F);
    assertThat(key.getEnharmonicStyle()).isSameAs(EnharmonicStyle.FLAT);
    assertThat(key.getDiatonicScale()).containsExactly(TonePitchEnglish.F, TonePitchEnglish.G, TonePitchEnglish.A_FLAT,
        TonePitchEnglish.B_FLAT, TonePitchEnglish.C, TonePitchEnglish.D_FLAT, TonePitchEnglish.E_FLAT);
    assertThat(key.getChromaticScale()).containsExactly(TonePitchEnglish.F, TonePitchEnglish.G_FLAT, TonePitchEnglish.G,
        TonePitchEnglish.A_FLAT, TonePitchEnglish.A, TonePitchEnglish.B_FLAT, TonePitchEnglish.B, TonePitchEnglish.C,
        TonePitchEnglish.D_FLAT, TonePitchEnglish.D, TonePitchEnglish.E_FLAT, TonePitchEnglish.E);
    assertThat(key.getChromaticSignTones()).containsExactly(TonePitchEnglish.B_FLAT, TonePitchEnglish.E_FLAT,
        TonePitchEnglish.A_FLAT, TonePitchEnglish.D_FLAT);
  }

  /**
   * Test of {@link MusicalKey#F_SHARP_MINOR}.
   */
  @Test
  public void testFSharpMinor() {

    MusicalKey key = MusicalKey.F_SHARP_MINOR;
    assertThat(key.getName()).isEqualTo("f#");
    assertThat(key.getSystem()).isSameAs(TonalSystem.MINOR);
    assertThat(key.getTonika()).isSameAs(TonePitchEnglish.F_SHARP);
    assertThat(key.getEnharmonicStyle()).isSameAs(EnharmonicStyle.SHARP);
    assertThat(key.getDiatonicScale()).containsExactly(TonePitchEnglish.F_SHARP, TonePitchEnglish.G_SHARP,
        TonePitchEnglish.A, TonePitchEnglish.B, TonePitchEnglish.C_SHARP, TonePitchEnglish.D, TonePitchEnglish.E);
    assertThat(key.getChromaticScale()).containsExactly(TonePitchEnglish.F_SHARP, TonePitchEnglish.G,
        TonePitchEnglish.G_SHARP, TonePitchEnglish.A, TonePitchEnglish.A_SHARP, TonePitchEnglish.B, TonePitchEnglish.C,
        TonePitchEnglish.C_SHARP, TonePitchEnglish.D, TonePitchEnglish.D_SHARP, TonePitchEnglish.E, TonePitchEnglish.F);
    assertThat(key.getChromaticSignTones()).containsExactly(TonePitchEnglish.F_SHARP, TonePitchEnglish.C_SHARP,
        TonePitchEnglish.G_SHARP);
  }

  /**
   * Test of {@link MusicalKey#G_MINOR}.
   */
  @Test
  public void testGMinor() {

    MusicalKey key = MusicalKey.G_MINOR;
    assertThat(key.getName()).isEqualTo("g");
    assertThat(key.getSystem()).isSameAs(TonalSystem.MINOR);
    assertThat(key.getTonika()).isSameAs(TonePitchEnglish.G);
    assertThat(key.getEnharmonicStyle()).isSameAs(EnharmonicStyle.FLAT);
    assertThat(key.getDiatonicScale()).containsExactly(TonePitchEnglish.G, TonePitchEnglish.A, TonePitchEnglish.B_FLAT,
        TonePitchEnglish.C, TonePitchEnglish.D, TonePitchEnglish.E_FLAT, TonePitchEnglish.F);
    assertThat(key.getChromaticScale()).containsExactly(TonePitchEnglish.G, TonePitchEnglish.A_FLAT, TonePitchEnglish.A,
        TonePitchEnglish.B_FLAT, TonePitchEnglish.B, TonePitchEnglish.C, TonePitchEnglish.D_FLAT, TonePitchEnglish.D,
        TonePitchEnglish.E_FLAT, TonePitchEnglish.E, TonePitchEnglish.F, TonePitchEnglish.G_FLAT);
    assertThat(key.getChromaticSignTones()).containsExactly(TonePitchEnglish.B_FLAT, TonePitchEnglish.E_FLAT);
  }

  /**
   * Test of {@link MusicalKey#G_SHARP_MINOR}.
   */
  @Test
  public void testGSharpMinor() {

    MusicalKey key = MusicalKey.G_SHARP_MINOR;
    assertThat(key.getName()).isEqualTo("g#");
    assertThat(key.getSystem()).isSameAs(TonalSystem.MINOR);
    assertThat(key.getTonika()).isSameAs(TonePitchEnglish.G_SHARP);
    assertThat(key.getEnharmonicStyle()).isSameAs(EnharmonicStyle.SHARP);
    assertThat(key.getDiatonicScale()).containsExactly(TonePitchEnglish.G_SHARP, TonePitchEnglish.A_SHARP,
        TonePitchEnglish.B, TonePitchEnglish.C_SHARP, TonePitchEnglish.D_SHARP, TonePitchEnglish.E,
        TonePitchEnglish.F_SHARP);
    assertThat(key.getChromaticScale()).containsExactly(TonePitchEnglish.G_SHARP, TonePitchEnglish.A,
        TonePitchEnglish.A_SHARP, TonePitchEnglish.B, TonePitchEnglish.C, TonePitchEnglish.C_SHARP, TonePitchEnglish.D,
        TonePitchEnglish.D_SHARP, TonePitchEnglish.E, TonePitchEnglish.F, TonePitchEnglish.F_SHARP, TonePitchEnglish.G);
    assertThat(key.getChromaticSignTones()).containsExactly(TonePitchEnglish.F_SHARP, TonePitchEnglish.C_SHARP,
        TonePitchEnglish.G_SHARP, TonePitchEnglish.D_SHARP, TonePitchEnglish.A_SHARP);
  }

  /**
   * Test of {@link MusicalKey#A_FLAT_MINOR}.
   */
  @Test
  public void testAFlatMinor() {

    MusicalKey key = MusicalKey.A_FLAT_MINOR;
    assertThat(key.getName()).isEqualTo("ab");
    assertThat(key.getSystem()).isSameAs(TonalSystem.MINOR);
    assertThat(key.getTonika()).isSameAs(TonePitchEnglish.A_FLAT);
    assertThat(key.getEnharmonicStyle()).isSameAs(EnharmonicStyle.FLAT);
    assertThat(key.getDiatonicScale()).containsExactly(TonePitchEnglish.A_FLAT, TonePitchEnglish.B_FLAT,
        TonePitchEnglish.C_FLAT, TonePitchEnglish.D_FLAT, TonePitchEnglish.E_FLAT, TonePitchEnglish.F_FLAT,
        TonePitchEnglish.G_FLAT);
    assertThat(key.getChromaticScale()).containsExactly(TonePitchEnglish.A_FLAT, TonePitchEnglish.A,
        TonePitchEnglish.B_FLAT, TonePitchEnglish.C_FLAT, TonePitchEnglish.C, TonePitchEnglish.D_FLAT,
        TonePitchEnglish.D, TonePitchEnglish.E_FLAT, TonePitchEnglish.F_FLAT, TonePitchEnglish.F,
        TonePitchEnglish.G_FLAT, TonePitchEnglish.G);
    assertThat(key.getChromaticSignTones()).containsExactly(TonePitchEnglish.B_FLAT, TonePitchEnglish.E_FLAT,
        TonePitchEnglish.A_FLAT, TonePitchEnglish.D_FLAT, TonePitchEnglish.G_FLAT, TonePitchEnglish.C_FLAT,
        TonePitchEnglish.F_FLAT);
  }

  /**
   * Test of {@link MusicalKey#A_MINOR}.
   */
  @Test
  public void testAMinor() {

    MusicalKey key = MusicalKey.A_MINOR;
    assertThat(key.getName()).isEqualTo("a");
    assertThat(key.getSystem()).isSameAs(TonalSystem.MINOR);
    assertThat(key.getTonika()).isSameAs(TonePitchEnglish.A);
    assertThat(key.getEnharmonicStyle()).isSameAs(EnharmonicStyle.NORMAL);
    assertThat(key.getDiatonicScale()).containsExactly(TonePitchEnglish.A, TonePitchEnglish.B, TonePitchEnglish.C,
        TonePitchEnglish.D, TonePitchEnglish.E, TonePitchEnglish.F, TonePitchEnglish.G);
    assertThat(key.getChromaticScale()).containsExactly(TonePitchEnglish.A, TonePitchEnglish.B_FLAT, TonePitchEnglish.B,
        TonePitchEnglish.C, TonePitchEnglish.C_SHARP, TonePitchEnglish.D, TonePitchEnglish.D_SHARP, TonePitchEnglish.E,
        TonePitchEnglish.F, TonePitchEnglish.F_SHARP, TonePitchEnglish.G, TonePitchEnglish.G_SHARP);
    assertThat(key.getChromaticSignTones()).isEmpty();
  }

  /**
   * Test of {@link MusicalKey#A_SHARP_MINOR}.
   */
  @Test
  public void testASharpMinor() {

    MusicalKey key = MusicalKey.A_SHARP_MINOR;
    assertThat(key.getName()).isEqualTo("a#");
    assertThat(key.getSystem()).isSameAs(TonalSystem.MINOR);
    assertThat(key.getTonika()).isSameAs(TonePitchEnglish.A_SHARP);
    assertThat(key.getEnharmonicStyle()).isSameAs(EnharmonicStyle.SHARP);
    assertThat(key.getDiatonicScale()).containsExactly(TonePitchEnglish.A_SHARP, TonePitchEnglish.B_SHARP,
        TonePitchEnglish.C_SHARP, TonePitchEnglish.D_SHARP, TonePitchEnglish.E_SHARP, TonePitchEnglish.F_SHARP,
        TonePitchEnglish.G_SHARP);
    assertThat(key.getChromaticScale()).containsExactly(TonePitchEnglish.A_SHARP, TonePitchEnglish.B,
        TonePitchEnglish.B_SHARP, TonePitchEnglish.C_SHARP, TonePitchEnglish.D, TonePitchEnglish.D_SHARP,
        TonePitchEnglish.E, TonePitchEnglish.E_SHARP, TonePitchEnglish.F_SHARP, TonePitchEnglish.G,
        TonePitchEnglish.G_SHARP, TonePitchEnglish.A);
    assertThat(key.getChromaticSignTones()).containsExactly(TonePitchEnglish.F_SHARP, TonePitchEnglish.C_SHARP,
        TonePitchEnglish.G_SHARP, TonePitchEnglish.D_SHARP, TonePitchEnglish.A_SHARP, TonePitchEnglish.E_SHARP,
        TonePitchEnglish.B_SHARP);
  }

  /**
   * Test of {@link MusicalKey#values() all} {@link MusicalKey}s with some generic asserts.
   */
  @Test
  public void testAll() {

    for (MusicalKey key : MusicalKey.values()) {
      String name = key.getName();
      assertThat(key.toString()).isEqualTo(name + "-" + key.getSystem());
      assertThat(MusicalKey.fromName(name)).isSameAs(key);
    }
  }

  /**
   * Test of {@link MusicalKey#getTone(io.github.musicdoc.interval.ToneInterval)}.
   */
  @Test
  public void testGetTone() {

    assertThat(MusicalKey.C_MAJOR.getTone(Solmization.DO)).isSameAs(TonePitchEnglish.C);
    assertThat(MusicalKey.C_MAJOR.getTone(Solmization.RE)).isSameAs(TonePitchEnglish.D);
    assertThat(MusicalKey.C_MAJOR.getTone(Solmization.MI)).isSameAs(TonePitchEnglish.E);
    assertThat(MusicalKey.C_MAJOR.getTone(Solmization.FA)).isSameAs(TonePitchEnglish.F);
    assertThat(MusicalKey.C_MAJOR.getTone(Solmization.SOL)).isSameAs(TonePitchEnglish.G);
    assertThat(MusicalKey.C_MAJOR.getTone(Solmization.LA)).isSameAs(TonePitchEnglish.A);
    assertThat(MusicalKey.C_MAJOR.getTone(Solmization.TI)).isSameAs(TonePitchEnglish.B);

    assertThat(MusicalKey.A_SHARP_MINOR.getTone(Solmization.LA)).isSameAs(TonePitchEnglish.A_SHARP);
    assertThat(MusicalKey.A_SHARP_MINOR.getTone(Solmization.TI)).isSameAs(TonePitchEnglish.B_SHARP);
    assertThat(MusicalKey.A_SHARP_MINOR.getTone(Solmization.DO)).isSameAs(TonePitchEnglish.C_SHARP);
    assertThat(MusicalKey.A_SHARP_MINOR.getTone(Solmization.RE)).isSameAs(TonePitchEnglish.D_SHARP);
    assertThat(MusicalKey.A_SHARP_MINOR.getTone(Solmization.MI)).isSameAs(TonePitchEnglish.E_SHARP);
    assertThat(MusicalKey.A_SHARP_MINOR.getTone(Solmization.FA)).isSameAs(TonePitchEnglish.F_SHARP);
    assertThat(MusicalKey.A_SHARP_MINOR.getTone(Solmization.SOL)).isSameAs(TonePitchEnglish.G_SHARP);
  }

}
