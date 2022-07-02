/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.music.tone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.musicdoc.music.glyphs.unicode.UnicodeGlyphsAccidentals;
import io.github.musicdoc.music.harmony.EnharmonicStyle;
import io.github.musicdoc.music.harmony.MusicalKey;
import io.github.musicdoc.music.interval.ChromaticStep;

/**
 * Test of {@link TonePitch}.
 */
public class TonePitchTest extends Assertions {

  /**
   * Test of {@link TonePitch#isNormal()} and {@link TonePitch#getNormalForm()}.
   */
  @Test
  public void testNormal() {

    Set<TonePitchEnglish> normalPitches = new HashSet<>(Arrays.asList(TonePitchEnglish.C, TonePitchEnglish.C_SHARP,
        TonePitchEnglish.D, TonePitchEnglish.D_SHARP, TonePitchEnglish.E, TonePitchEnglish.F, TonePitchEnglish.F_SHARP,
        TonePitchEnglish.G, TonePitchEnglish.G_SHARP, TonePitchEnglish.A, TonePitchEnglish.B_FLAT, TonePitchEnglish.B));
    for (int i = 0; i < 12; i++) {
      TonePitch pitch = TonePitchEnglish.STYLE.pitch(ChromaticStep.of(i));
      if (normalPitches.contains(pitch)) {
        assertThat(pitch.isNormal()).as(pitch.toString()).isTrue();
        assertThat(pitch.getNormalForm()).as(pitch.toString()).isSameAs(pitch);
      } else {
        assertThat(pitch.isNormal()).as(pitch.toString()).isFalse();
        assertThat(pitch.getNormalForm().isNormal()).as(pitch.toString()).isTrue();
        assertThat(pitch.getNormalForm().getStep()).as(pitch.toString()).isEqualTo(pitch.getStep());
      }
    }

    assertThat(TonePitchEnglish.A_SHARP.getNormalForm()).isSameAs(TonePitchEnglish.B_FLAT);
    assertThat(TonePitchGerman.AIS.getNormalForm()).isSameAs(TonePitchGerman.B);
    assertThat(TonePitchEnglish.A_DOUBLE_SHARP.getNormalForm()).isSameAs(TonePitchEnglish.B);
    assertThat(TonePitchGerman.AISIS.getNormalForm()).isSameAs(TonePitchGerman.H);
    assertThat(TonePitchGerman.AS.getNormalForm()).isSameAs(TonePitchGerman.GIS);
    assertThat(TonePitchGerman.ASES.getNormalForm()).isSameAs(TonePitchGerman.G);
    assertThat(TonePitchGerman.CES.getNormalForm()).isSameAs(TonePitchGerman.H);
    assertThat(TonePitchEnglish.C_DOUBLE_FLAT.getNormalForm()).isSameAs(TonePitchEnglish.B_FLAT);
    assertThat(TonePitchGerman.CESES.getNormalForm()).isSameAs(TonePitchGerman.B);
    assertThat(TonePitchGerman.CISIS.getNormalForm()).isSameAs(TonePitchGerman.D);
    assertThat(TonePitchGerman.DES.getNormalForm()).isSameAs(TonePitchGerman.CIS);
    assertThat(TonePitchGerman.DESES.getNormalForm()).isSameAs(TonePitchGerman.C);
    assertThat(TonePitchEnglish.E_FLAT.getNormalForm()).isSameAs(TonePitchEnglish.D_SHARP);
    assertThat(TonePitchGerman.ES.getNormalForm()).isSameAs(TonePitchGerman.DIS);
    assertThat(TonePitchGerman.DISIS.getNormalForm()).isSameAs(TonePitchGerman.E);
    assertThat(TonePitchGerman.EIS.getNormalForm()).isSameAs(TonePitchGerman.F);
    assertThat(TonePitchGerman.EISIS.getNormalForm()).isSameAs(TonePitchGerman.FIS);
    assertThat(TonePitchGerman.ESES.getNormalForm()).isSameAs(TonePitchGerman.D);
    assertThat(TonePitchGerman.FES.getNormalForm()).isSameAs(TonePitchGerman.E);
    assertThat(TonePitchEnglish.F_DOUBLE_FLAT.getNormalForm()).isSameAs(TonePitchEnglish.D_SHARP);
    assertThat(TonePitchGerman.FESES.getNormalForm()).isSameAs(TonePitchGerman.DIS);
    assertThat(TonePitchGerman.FISIS.getNormalForm()).isSameAs(TonePitchGerman.G);
    assertThat(TonePitchGerman.GES.getNormalForm()).isSameAs(TonePitchGerman.FIS);
    assertThat(TonePitchGerman.GESES.getNormalForm()).isSameAs(TonePitchGerman.F);
    assertThat(TonePitchGerman.GISIS.getNormalForm()).isSameAs(TonePitchGerman.A);
    assertThat(TonePitchGerman.HESES.getNormalForm()).isSameAs(TonePitchGerman.A);
    assertThat(TonePitchDutch.BIS.getNormalForm()).isSameAs(TonePitchDutch.C);
    assertThat(TonePitchDutch.BISIS.getNormalForm()).isSameAs(TonePitchDutch.CIS);
  }

  /**
   * Test of {@link TonePitches#of(String)}.
   */
  @Test
  public void testFromString() {

    assertThat(TonePitches.of("c")).isSameAs(TonePitchEnglish.C.with(ToneNameCase.LOWER_CASE));
    assertThat(TonePitches.of("C")).isSameAs(TonePitchEnglish.C);
    assertThat(TonePitches.of("ciS")).isSameAs(TonePitchGerman.CIS.with(ToneNameCase.LOWER_CASE));
    assertThat(TonePitches.of("c#")).isSameAs(TonePitchEnglish.C_SHARP.with(ToneNameCase.LOWER_CASE));
    assertThat(TonePitches.of("c\u266F")).isSameAs(TonePitchInternational.C_SHARP.with(ToneNameCase.LOWER_CASE));
    assertThat(TonePitches.of("bb")).isSameAs(TonePitchEnglish.B_FLAT.with(ToneNameCase.LOWER_CASE));
    assertThat(TonePitches.of("b")).isSameAs(TonePitchEnglish.B.with(ToneNameCase.LOWER_CASE));
    assertThat(TonePitches.of("b\u266D")).isSameAs(TonePitchInternational.B_FLAT.with(ToneNameCase.LOWER_CASE));
    assertThat(TonePitches.of("h")).isSameAs(TonePitchGerman.H.with(ToneNameCase.LOWER_CASE));
    assertThat(TonePitches.of("b\u266E")).isSameAs(TonePitchInternational.B_NEUTRAL.with(ToneNameCase.LOWER_CASE));
    assertThat(TonePitches.of("f" + UnicodeGlyphsAccidentals.FLAT_2))
        .isSameAs(TonePitchInternational.F_DOUBLE_FLAT.with(ToneNameCase.LOWER_CASE));
    assertThat(TonePitches.of("fbb")).isSameAs(TonePitchEnglish.F_DOUBLE_FLAT.with(ToneNameCase.LOWER_CASE));
    assertThat(TonePitches.of("fEseS")).isSameAs(TonePitchGerman.FESES.with(ToneNameCase.LOWER_CASE));
    assertThat(TonePitches.of("B" + UnicodeGlyphsAccidentals.FLAT_2)).isSameAs(TonePitchInternational.B_DOUBLE_FLAT);
    assertThat(TonePitches.of("B##")).isSameAs(TonePitchEnglish.B_DOUBLE_SHARP);
    assertThat(TonePitches.of("hISis")).isSameAs(TonePitchGerman.HISIS.with(ToneNameCase.LOWER_CASE));
    assertThat(TonePitches.of("BISis")).isSameAs(TonePitchDutch.BISIS);
  }

  /**
   * Test of {@link TonePitchMapper#parse(String)}.
   */
  @Test
  public void testGetTonePrefix() {

    TonePitchMapper mapper = TonePitchMapperMusicDoc.INSTANCE;
    assertThat(mapper.parse("c").getName()).isEqualTo("c");
    assertThat(mapper.parse("ce").getName()).isEqualTo("c");
    assertThat(mapper.parse("ceS").getName()).isEqualTo("ces");
    assertThat(mapper.parse("cbe").getName()).isEqualTo("cb");
    assertThat(mapper.parse("c#9").getName()).isEqualTo("c#");
    assertThat(mapper.parse("hesesisas").getName()).isEqualTo("heses");
    assertThat(mapper.parse("B" + UnicodeGlyphsAccidentals.FLAT_2).getName())
        .isEqualTo("B" + UnicodeGlyphsAccidentals.FLAT_2);
    assertThat(mapper.parse("Bbb").getName()).isEqualTo("Bbb");
    assertThat(mapper.parse("Bub").getName()).isEqualTo("B");
  }

  /**
   * Test of {@link TonePitch#getName()} with {@link TonePitchInternational}.
   */
  @Test
  public void testUnicode() {

    Map<String, String> unicode2asciiMap = new HashMap<>();
    unicode2asciiMap.put(UnicodeGlyphsAccidentals.SHARP_1, "#");
    unicode2asciiMap.put(UnicodeGlyphsAccidentals.FLAT_1, "b");
    unicode2asciiMap.put(UnicodeGlyphsAccidentals.SHARP_2, "##");
    unicode2asciiMap.put(UnicodeGlyphsAccidentals.FLAT_2, "bb");
    unicode2asciiMap.put(UnicodeGlyphsAccidentals.NEUTRAL, "");
    for (TonePitch pitch : getEnglishValues()) {
      String unicode = pitch.with(TonePitchInternational.STYLE).getName();
      String ascii = unicode;
      for (String sign : unicode2asciiMap.keySet()) {
        if (unicode.endsWith(sign)) {
          ascii = unicode.substring(0, unicode.length() - sign.length()) + unicode2asciiMap.get(sign);
          break;
        }
      }
      assertThat(pitch.getName()).as(pitch.toString()).isEqualTo(ascii);
    }
  }

  private Iterable<TonePitchEnglish> getEnglishValues() {

    Collection<TonePitchEnglish> list = new ArrayList<>(50);
    for (int step = 0; step < 12; step++) {
      EnharmonicType type = EnharmonicType.DOUBLE_FLAT;
      while (type != null) {
        ToneNameCase nameCase = ToneNameCase.CAPITAL_CASE;
        while (nameCase != null) {
          TonePitchEnglish pitch = TonePitchEnglish.STYLE.pitch(ChromaticStep.of(step), type, nameCase);
          if (pitch != null) {
            list.add(pitch);
          }
          if (nameCase == ToneNameCase.CAPITAL_CASE) {
            nameCase = ToneNameCase.LOWER_CASE;
          } else {
            nameCase = null;
          }
        }
        if (type == EnharmonicType.DOUBLE_FLAT) {
          type = EnharmonicType.SINGLE_FLAT;
        } else if (type == EnharmonicType.SINGLE_FLAT) {
          type = EnharmonicType.NORMAL;
        } else if (type == EnharmonicType.NORMAL) {
          type = EnharmonicType.SINGLE_SHARP;
        } else if (type == EnharmonicType.SINGLE_SHARP) {
          type = EnharmonicType.DOUBLE_SHARP;
        } else {
          type = null;
        }
      }
    }
    return list;
  }

  /**
   * Test of {@link TonePitch#transposeChromatic(int, EnharmonicStyle)}.
   */
  @Test
  public void testTransposeNormalized() {

    for (TonePitch pitch : getEnglishValues()) {
      String s = pitch.toString();
      TonePitch normal = pitch.getNormalForm().getReference().with(pitch.getCase());
      assertThat(pitch.transposeChromatic(0, EnharmonicStyle.NORMAL)).as(s).isSameAs(normal);
      assertThat(pitch.transposeChromatic(12, EnharmonicStyle.NORMAL)).as(s).isSameAs(normal);
      assertThat(pitch.transposeChromatic(24, EnharmonicStyle.NORMAL)).as(s).isSameAs(normal);
      assertThat(pitch.transposeChromatic(-12, EnharmonicStyle.NORMAL)).as(s).isSameAs(normal);
      assertThat(pitch.transposeChromatic(-24, EnharmonicStyle.NORMAL)).as(s).isSameAs(normal);
    }
    // normalized
    assertThat(TonePitchEnglish.C.transposeChromatic(1, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.C_SHARP);
    assertThat(TonePitchEnglish.C.transposeChromatic(2, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.D);
    assertThat(TonePitchEnglish.C.transposeChromatic(-1, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.B);
  }

  /**
   * Test of {@link TonePitch#transposeChromatic(int, MusicalKey)}.
   */
  @Test
  public void testTransposeChromaticWithKey() {

    // C up one semitone...
    assertThat(TonePitchEnglish.C.transposeChromatic(1, MusicalKey.C_MAJOR)).isSameAs(TonePitchEnglish.C_SHARP);
    assertThat(TonePitchEnglish.C.transposeChromatic(1, MusicalKey.G_MAJOR)).isSameAs(TonePitchEnglish.C_SHARP);
    assertThat(TonePitchEnglish.C.transposeChromatic(1, MusicalKey.F_MAJOR)).isSameAs(TonePitchEnglish.D_FLAT);
    assertThat(TonePitchEnglish.C.transposeChromatic(1, MusicalKey.A_FLAT_MAJOR)).isSameAs(TonePitchEnglish.D_FLAT);

    // H up one semitone...
    assertThat(TonePitchEnglish.B.transposeChromatic(1, MusicalKey.C_MAJOR)).isSameAs(TonePitchEnglish.C);
    assertThat(TonePitchEnglish.B.transposeChromatic(1, MusicalKey.D_SHARP_MINOR)).isSameAs(TonePitchEnglish.C);
    assertThat(TonePitchEnglish.B.transposeChromatic(1, MusicalKey.A_SHARP_MINOR)).isSameAs(TonePitchEnglish.B_SHARP);

    // H down one semitone...
    assertThat(TonePitchEnglish.B.transposeChromatic(-1, MusicalKey.C_MAJOR)).isSameAs(TonePitchEnglish.B_FLAT);
    assertThat(TonePitchEnglish.B.transposeChromatic(-1, MusicalKey.D_MINOR)).isSameAs(TonePitchEnglish.B_FLAT);
    assertThat(TonePitchEnglish.B.transposeChromatic(-1, MusicalKey.A_SHARP_MINOR)).isSameAs(TonePitchEnglish.A_SHARP);
    assertThat(TonePitchEnglish.B.transposeChromatic(-1, MusicalKey.B_MAJOR)).isSameAs(TonePitchEnglish.A_SHARP);

    // F down one semitone...
    assertThat(TonePitchEnglish.F.transposeChromatic(-1, MusicalKey.C_MAJOR)).isSameAs(TonePitchEnglish.E);
    assertThat(TonePitchEnglish.F.transposeChromatic(-1, MusicalKey.C_FLAT_MAJOR)).isSameAs(TonePitchEnglish.F_FLAT);
  }

  /**
   * Test of {@link TonePitch#transposeChromatic(int, EnharmonicStyle)}.
   */
  @Test
  public void testTransposeChromaticWithStyle() {

    assertThat(TonePitchEnglish.C.transposeChromatic(0, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.C);
    assertThat(TonePitchEnglish.C.transposeChromatic(12, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.C);
    assertThat(TonePitchEnglish.C.transposeChromatic(-12, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.C);
    assertThat(TonePitchEnglish.C.transposeChromatic(240, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.C);
    assertThat(TonePitchEnglish.C.transposeChromatic(-240, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.C);
    assertThat(TonePitchEnglish.C.transposeChromatic(1, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.C_SHARP);
    assertThat(TonePitchEnglish.C.transposeChromatic(2, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.D);
    assertThat(TonePitchEnglish.C.transposeChromatic(3, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.D_SHARP);
    assertThat(TonePitchEnglish.C.transposeChromatic(4, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.E);
    assertThat(TonePitchEnglish.C.transposeChromatic(5, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.F);
    assertThat(TonePitchEnglish.C.transposeChromatic(6, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.F_SHARP);
    assertThat(TonePitchEnglish.C.transposeChromatic(7, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.G);
    assertThat(TonePitchEnglish.C.transposeChromatic(8, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.G_SHARP);
    assertThat(TonePitchEnglish.C.transposeChromatic(9, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.A);
    assertThat(TonePitchEnglish.C.transposeChromatic(10, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.B_FLAT);
    assertThat(TonePitchEnglish.C.transposeChromatic(11, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.B);

    assertThat(TonePitchEnglish.C.transposeChromatic(-1, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.B);
    assertThat(TonePitchEnglish.C.transposeChromatic(-2, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.B_FLAT);
    assertThat(TonePitchEnglish.C.transposeChromatic(-3, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.A);
    assertThat(TonePitchEnglish.C.transposeChromatic(-4, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.G_SHARP);
    assertThat(TonePitchEnglish.C.transposeChromatic(-5, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.G);
    assertThat(TonePitchEnglish.C.transposeChromatic(-6, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.F_SHARP);
    assertThat(TonePitchEnglish.C.transposeChromatic(-7, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.F);
    assertThat(TonePitchEnglish.C.transposeChromatic(-8, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.E);
    assertThat(TonePitchEnglish.C.transposeChromatic(-9, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.D_SHARP);
    assertThat(TonePitchEnglish.C.transposeChromatic(-10, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.D);
    assertThat(TonePitchEnglish.C.transposeChromatic(-11, EnharmonicStyle.NORMAL)).isSameAs(TonePitchEnglish.C_SHARP);
  }
}
