/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.music.harmony.chord;

import org.junit.jupiter.api.Test;

import io.github.musicdoc.AbstractTest;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.music.format.SongFormatOptions;
import io.github.musicdoc.music.harmony.MusicalKey;
import io.github.musicdoc.music.harmony.TonalSystem;
import io.github.musicdoc.music.interval.ChromaticInterval;
import io.github.musicdoc.music.interval.DiatonicInterval;
import io.github.musicdoc.music.tone.ToneNameCase;
import io.github.musicdoc.music.tone.TonePitch;
import io.github.musicdoc.music.tone.TonePitchEnglish;
import io.github.musicdoc.music.tone.TonePitchGerman;
import io.github.musicdoc.music.tone.TonePitchInternational;
import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * Test of {@link Chord}.
 */
public class ChordTest extends AbstractTest {

  /** Test of {@link ChordMapper#parse(MusicInputStream, SongFormatOptions)} (parsing). */
  @Test
  public void testParse() {

    ChordMapper mapper = ChordMapperMusicDoc.INSTANCE;
    checkEqualsAndHashCode(mapper.parse("C"), new Chord(TonePitchEnglish.C, TonalSystem.MAJOR_EMPTY), true);
    checkEqualsAndHashCode(mapper.parse("C"), mapper.parse("Cm"), false);
    checkEqualsAndHashCode(mapper.parse("C"), mapper.parse("C/B"), false);
    checkEqualsAndHashCode(mapper.parse("C"), mapper.parse("C7"), false);
    checkEqualsAndHashCode(mapper.parse("C"), mapper.parse("Cis"), false);
    assertThat(mapper.parse("C").getTonalSystem()).isSameAs(TonalSystem.MAJOR_EMPTY);
    assertThat(mapper.parse("Cm")).isEqualTo(new Chord(TonePitchEnglish.C, TonalSystem.MINOR_M));
    assertThat(mapper.parse("CMi")).isEqualTo(mapper.parse("CmI"));
    assertThat(mapper.parse("C#maj7/E")).isEqualTo(
        new Chord(TonePitchEnglish.C_SHARP, TonalSystem.MAJOR_EMPTY, TonePitchEnglish.E, ChordExtension.MAJ_7));
    Chord AsSus4Add9OverFeses = mapper.parse("a\u266Dsus4add9/F\uD834\uDD2B");
    assertThat(AsSus4Add9OverFeses).isEqualTo(new Chord(TonePitchInternational.A_FLAT.with(ToneNameCase.LOWER_CASE),
        null, TonePitchInternational.F_DOUBLE_FLAT, ChordExtension.SUS_4, ChordExtension.ADD_9));
    assertThat(AsSus4Add9OverFeses.getFundamental())
        .isSameAs(TonePitchInternational.A_FLAT.with(ToneNameCase.LOWER_CASE));
    assertThat(AsSus4Add9OverFeses.getTonalSystem()).isNull();
    assertThat(AsSus4Add9OverFeses.getBase()).isSameAs(TonePitchInternational.F_DOUBLE_FLAT);
    assertThat(AsSus4Add9OverFeses.getExtensions()).containsExactly(ChordExtension.SUS_4, ChordExtension.ADD_9);
    assertThat(mapper.parse("Bb")).isEqualTo(new Chord(TonePitchEnglish.B_FLAT, TonalSystem.MAJOR_EMPTY));
    assertThat(mapper.parse("B")).isEqualTo(new Chord(TonePitchEnglish.B, TonalSystem.MAJOR_EMPTY));
    // accords with additional clutter
    assertThat(mapper.parse("F/")).isEqualTo(new Chord(TonePitchEnglish.F, TonalSystem.MAJOR_EMPTY));
    Chord d6sus4SupertrampEvenInTheQuitesMoments = mapper.parse("D6sus4(xx0787)");
    assertThat(d6sus4SupertrampEvenInTheQuitesMoments)
        .isEqualTo(new Chord(TonePitchEnglish.D, TonalSystem.MAJOR_EMPTY, ChordExtension._6, ChordExtension.SUS_4));
    assertThat(d6sus4SupertrampEvenInTheQuitesMoments.getName()).isEqualTo("D6sus4");
    // negative tests (invalid chords)
    assertThat(mapper.parse("X")).isNull();
    assertThat(mapper.parse("W")).isNull();
    assertThat(mapper.parse("N.C.")).isNull();
  }

  /** Test of {@link Chord#Chord(TonePitch, TonalSystem, TonePitch)}. */
  @Test
  public void testNew() {

    ChordMapper mapper = ChordMapperMusicDoc.INSTANCE;
    assertThat(new Chord(TonePitchGerman.FIS, TonalSystem.MAJOR_EMPTY, TonePitchEnglish.E))
        .isEqualTo(mapper.parse("Fis/E"));
    assertThat(new Chord(TonePitchEnglish.F_SHARP, TonalSystem.MAJOR_EMPTY, TonePitchEnglish.E))
        .isEqualTo(mapper.parse("F#/E"));
    assertThat(new Chord(TonePitchInternational.F_SHARP, TonalSystem.MAJOR_EMPTY, TonePitchEnglish.E))
        .isEqualTo(mapper.parse("F\u266F/E"));
    assertThat(new Chord(TonePitchGerman.ES, TonalSystem.MINOR_M, ChordExtension._7)).isEqualTo(mapper.parse("Esm7"));
    assertThat(new Chord(TonePitchEnglish.E_FLAT, TonalSystem.MINOR_M, ChordExtension._7))
        .isEqualTo(mapper.parse("Ebm7"));
    assertThat(
        new Chord(TonePitchInternational.E_FLAT.with(ToneNameCase.LOWER_CASE), TonalSystem.MINOR_M, ChordExtension._7))
            .isEqualTo(mapper.parse("e\u266Dm7"));
  }

  /** Test of {@link Chord#transposeChromatic(int)}. */
  @Test
  public void testTransposeChromatic() {

    ChordMapper mapper = ChordMapperMusicDoc.INSTANCE;
    assertThat(mapper.parse("C").transposeChromatic(1).getName()).isEqualTo("C#");
    assertThat(mapper.parse("ebmadd9/A").transposeChromatic(-1).getName()).isEqualTo("dmadd9/G#");
    assertThat(mapper.parse("E\u266Dmadd9/A").transposeChromatic(-1).getName()).isEqualTo("Dmadd9/G\u266F");
    assertThat(mapper.parse("Cis4add9no5/A").transposeChromatic(-1).toString()).isEqualTo("C4add9no5/Gis");
  }

  /** Test of {@link Chord#transpose(io.github.musicdoc.music.interval.ToneInterval, TransposeContext)}. */
  @Test
  public void testTransposeInterval() {

    // chromatic
    ChordMapper mapper = ChordMapperMusicDoc.INSTANCE;
    assertThat(mapper.parse("C\u266F7/B\u266D").transpose(ChromaticInterval.PERFECT_FOURTH,
        new TransposeContext(
            MusicalKey.C_SHARP_MAJOR.transposeChromatic(ChromaticInterval.PERFECT_FOURTH.getChromaticSteps()))))
                .isEqualTo(mapper.parse("F\u266F7/D\u266F"));

    assertThat(mapper.parse("ebmadd9/A").transposeChromatic(-1).getName()).isEqualTo("dmadd9/G#");
    assertThat(mapper.parse("E\u266Dmadd9/A").transposeChromatic(-1).getName()).isEqualTo("Dmadd9/G\u266F");
    assertThat(mapper.parse("Cis4add9no5/A").transposeChromatic(-1).getName()).isEqualTo("C4add9no5/Gis");

    // diatonic
    TransposeContext context = new TransposeContext(MusicalKey.C_SHARP_MAJOR);

    assertThat(mapper.parse("C\u266F7/B\u266D").transpose(DiatonicInterval.THIRD, context))
        .isEqualTo(mapper.parse("E\u266F7/D")); // MuseScore3 is buggy and transposes to E#/D#
                                                // instead
    context.setNormalizeChords(true);
    assertThat(mapper.parse("C\u266F7/Bb").transpose(DiatonicInterval.THIRD, context))
        .isEqualTo(mapper.parse("E\u266F7/D"));
  }

}
