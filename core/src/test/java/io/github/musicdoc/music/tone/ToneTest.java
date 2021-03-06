/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.music.tone;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.musicdoc.music.harmony.EnharmonicStyle;
import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * Test of {@link Tone}.
 */
public class ToneTest extends Assertions {

  /**
   * Test of {@link Tone#getName(ToneNameStyle)}, {@link ToneMapper#parse(String)}, etc.
   */
  @Test
  public void testName() {

    checkToneByAsciiName("c#", TonePitchEnglish.C_SHARP, 5);
    checkToneByAsciiName("c#'", TonePitchEnglish.C_SHARP, 6);
    checkToneByAsciiName("c#''", TonePitchEnglish.C_SHARP, 7);
    checkToneByAsciiName("C#", TonePitchEnglish.C_SHARP, 4);
    checkToneByAsciiName("C#,", TonePitchEnglish.C_SHARP, 3);
    checkToneByAsciiName("C#,,", TonePitchEnglish.C_SHARP, 2);
    checkToneByAsciiName("bb", TonePitchEnglish.B_FLAT, 5);
    checkToneByAsciiName("Bb", TonePitchEnglish.B_FLAT, 4);
    checkToneByAsciiName("B", TonePitchEnglish.B, 4);
    ToneMapper mapper = ToneMapperMusicDoc.INSTANCE;
    assertThat(mapper.parse("Cis','")).isEqualTo(mapper.parse("c#"));
    assertThat(mapper.parse("Deses','").isEqualTo(mapper.parse("dbb"))).isTrue();
  }

  private void checkToneByAsciiName(String ascii, TonePitch pitch, int octave) {

    ToneMapper mapper = ToneMapperMusicDoc.INSTANCE;
    Tone tone = mapper.parse(ascii);
    assertThat(tone.getNameAbc(TonePitchEnglish.STYLE)).isEqualTo(ascii);
    assertThat(tone.getPitch()).isSameAs(pitch);
    assertThat(tone.getOctave()).isEqualTo(octave);
  }

  /**
   * Test of {@link Tone#transpose(int, boolean, TransposeContext) transpose} chromatic.
   */
  @Test
  public void testTransposeChromaticStyle() {

    // doTranspose up
    // same octave
    checkTransposeChromaticNormalStyle(TonePitchEnglish.B_FLAT, 1, TonePitchEnglish.B, 0);
    checkTransposeChromaticNormalStyle(TonePitchEnglish.C, 11, TonePitchEnglish.B, 0);
    // next octave
    checkTransposeChromaticNormalStyle(TonePitchEnglish.B, 1, TonePitchEnglish.C, 1);
    checkTransposeChromaticNormalStyle(TonePitchEnglish.C, 12, TonePitchEnglish.C, 1);
    checkTransposeChromaticNormalStyle(TonePitchEnglish.C, 13, TonePitchEnglish.C_SHARP, 1);
    checkTransposeChromaticNormalStyle(TonePitchEnglish.C_SHARP, 22, TonePitchEnglish.B, 1);
    // multiple octaves
    checkTransposeChromaticNormalStyle(TonePitchEnglish.C_SHARP, 23, TonePitchEnglish.C, 2);
    checkTransposeChromaticNormalStyle(TonePitchEnglish.B, 13, TonePitchEnglish.C, 2);
    checkTransposeChromaticNormalStyle(TonePitchEnglish.C, 24, TonePitchEnglish.C, 2);

    // doTranspose down
    // same octave
    checkTransposeChromaticNormalStyle(TonePitchEnglish.B, -1, TonePitchEnglish.B_FLAT, 0);
    checkTransposeChromaticNormalStyle(TonePitchEnglish.B, -11, TonePitchEnglish.C, 0);
    // previous octave
    checkTransposeChromaticNormalStyle(TonePitchEnglish.B_FLAT, -11, TonePitchEnglish.B, -1);
    checkTransposeChromaticNormalStyle(TonePitchEnglish.C, -1, TonePitchEnglish.B, -1);
    checkTransposeChromaticNormalStyle(TonePitchEnglish.C, -12, TonePitchEnglish.C, -1);
    // multiple octaves
  }

  private void checkTransposeChromaticNormalStyle(TonePitch pitch, int step, TonePitch newPitch, int octaveStep) {

    checkTransposeChromaticNormalStyle(0, pitch, step, newPitch, octaveStep);
  }

  private void checkTransposeChromaticNormalStyle(int octave, TonePitch pitch, int step, TonePitch newPitch,
      int octaveStep) {

    // given
    Tone tone = Tone.of(pitch, octave);
    // when
    Tone transposed = tone.transpose(step, false, new TransposeContext(EnharmonicStyle.NORMAL));
    // then
    assertThat(transposed.getPitch()).isSameAs(newPitch);
    assertThat(transposed.getOctave()).isEqualTo(octave + octaveStep);
  }

}
