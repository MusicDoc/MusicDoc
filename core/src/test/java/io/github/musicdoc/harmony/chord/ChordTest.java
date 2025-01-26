/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.harmony.chord;

import org.junit.jupiter.api.Test;

import io.github.musicdoc.AbstractTest;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.harmony.TonalSystem;
import io.github.musicdoc.harmony.key.MusicalKey;
import io.github.musicdoc.interval.ChromaticInterval;
import io.github.musicdoc.interval.DiatonicInterval;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.tone.ToneNameCase;
import io.github.musicdoc.tone.pitch.TonePitch;
import io.github.musicdoc.tone.pitch.TonePitchEnglish;
import io.github.musicdoc.tone.pitch.TonePitchGerman;
import io.github.musicdoc.tone.pitch.TonePitchInternational;
import io.github.musicdoc.transpose.TransposeContext;

/**
 * Test of {@link ChordSymbol}.
 */
public class ChordTest extends AbstractTest {

  /** Test of {@link ChordSymbolMapper#read(MusicInputStream, SongFormatContext)} (parsing). */
  @Test
  public void testParse() {

    ChordSymbolMapper mapper = ChordSymbolMapperMusicDoc.INSTANCE;
    checkEqualsAndHashCode(mapper.read("C"), new ChordSymbol(TonePitchEnglish.C, TonalSystem.MAJOR_EMPTY), true);
    checkEqualsAndHashCode(mapper.read("C"), mapper.read("Cm"), false);
    checkEqualsAndHashCode(mapper.read("C"), mapper.read("C/B"), false);
    checkEqualsAndHashCode(mapper.read("C"), mapper.read("C7"), false);
    checkEqualsAndHashCode(mapper.read("C"), mapper.read("Cis"), false);
    assertThat(mapper.read("C").getTonalSystem()).isSameAs(TonalSystem.MAJOR_EMPTY);
    assertThat(mapper.read("Cm")).isEqualTo(new ChordSymbol(TonePitchEnglish.C, TonalSystem.MINOR_M));
    assertThat(mapper.read("CMi")).isEqualTo(mapper.read("CmI"));
    assertThat(mapper.read("C#maj7/E")).isEqualTo(
        new ChordSymbol(TonePitchEnglish.C_SHARP, TonalSystem.MAJOR_EMPTY, TonePitchEnglish.E, ChordExtension.MAJ_7));
    ChordSymbol AsSus4Add9OverFeses = mapper.read("a\u266Dsus4add9/F\uD834\uDD2B");
    assertThat(AsSus4Add9OverFeses)
        .isEqualTo(new ChordSymbol(TonePitchInternational.A_FLAT.with(ToneNameCase.LOWER_CASE), null,
            TonePitchInternational.F_DOUBLE_FLAT, ChordExtension.SUS_4, ChordExtension.ADD_9));
    assertThat(AsSus4Add9OverFeses.getFundamental())
        .isSameAs(TonePitchInternational.A_FLAT.with(ToneNameCase.LOWER_CASE));
    assertThat(AsSus4Add9OverFeses.getTonalSystem()).isNull();
    assertThat(AsSus4Add9OverFeses.getBase()).isSameAs(TonePitchInternational.F_DOUBLE_FLAT);
    assertThat(AsSus4Add9OverFeses.getExtensions()).containsExactly(ChordExtension.SUS_4, ChordExtension.ADD_9);
    assertThat(mapper.read("Bb")).isEqualTo(new ChordSymbol(TonePitchEnglish.B_FLAT, TonalSystem.MAJOR_EMPTY));
    assertThat(mapper.read("B")).isEqualTo(new ChordSymbol(TonePitchEnglish.B, TonalSystem.MAJOR_EMPTY));
    // accords with additional clutter
    assertThat(mapper.read("F/")).isEqualTo(new ChordSymbol(TonePitchEnglish.F, TonalSystem.MAJOR_EMPTY));
    ChordSymbol d6sus4SupertrampEvenInTheQuitesMoments = mapper.read("D6sus4(xx0787)");
    assertThat(d6sus4SupertrampEvenInTheQuitesMoments).isEqualTo(
        new ChordSymbol(TonePitchEnglish.D, TonalSystem.MAJOR_EMPTY, ChordExtension._6, ChordExtension.SUS_4));
    assertThat(d6sus4SupertrampEvenInTheQuitesMoments.getName()).isEqualTo("D6sus4");
    // negative tests (invalid chords)
    assertThat(mapper.read("X")).isNull();
    assertThat(mapper.read("W")).isNull();
    assertThat(mapper.read("N.C.")).isNull();
  }

  /** Test of {@link ChordSymbol#ChordSymbol(TonePitch, TonalSystem, TonePitch)}. */
  @Test
  public void testNew() {

    ChordSymbolMapper mapper = ChordSymbolMapperMusicDoc.INSTANCE;
    assertThat(new ChordSymbol(TonePitchGerman.FIS, TonalSystem.MAJOR_EMPTY, TonePitchEnglish.E))
        .isEqualTo(mapper.read("Fis/E"));
    assertThat(new ChordSymbol(TonePitchEnglish.F_SHARP, TonalSystem.MAJOR_EMPTY, TonePitchEnglish.E))
        .isEqualTo(mapper.read("F#/E"));
    assertThat(new ChordSymbol(TonePitchInternational.F_SHARP, TonalSystem.MAJOR_EMPTY, TonePitchEnglish.E))
        .isEqualTo(mapper.read("F\u266F/E"));
    assertThat(new ChordSymbol(TonePitchGerman.ES, TonalSystem.MINOR_M, ChordExtension._7))
        .isEqualTo(mapper.read("Esm7"));
    assertThat(new ChordSymbol(TonePitchEnglish.E_FLAT, TonalSystem.MINOR_M, ChordExtension._7))
        .isEqualTo(mapper.read("Ebm7"));
    assertThat(new ChordSymbol(TonePitchInternational.E_FLAT.with(ToneNameCase.LOWER_CASE), TonalSystem.MINOR_M,
        ChordExtension._7)).isEqualTo(mapper.read("e\u266Dm7"));
  }

  /** Test of {@link ChordSymbol#transposeChromatic(int)}. */
  @Test
  public void testTransposeChromatic() {

    ChordSymbolMapper mapper = ChordSymbolMapperMusicDoc.INSTANCE;
    assertThat(mapper.read("C").transposeChromatic(1).getName()).isEqualTo("C#");
    assertThat(mapper.read("ebmadd9/A").transposeChromatic(-1).getName()).isEqualTo("dmadd9/G#");
    assertThat(mapper.read("E\u266Dmadd9/A").transposeChromatic(-1).getName()).isEqualTo("Dmadd9/G\u266F");
    assertThat(mapper.read("Cis4add9no5/A").transposeChromatic(-1).toString()).isEqualTo("C4add9no5/Gis");
  }

  /** Test of {@link ChordSymbol#transpose(io.github.musicdoc.interval.ToneInterval, TransposeContext)}. */
  @Test
  public void testTransposeInterval() {

    // chromatic
    ChordSymbolMapper mapper = ChordSymbolMapperMusicDoc.INSTANCE;
    assertThat(mapper.read("C\u266F7/B\u266D").transpose(ChromaticInterval.PERFECT_FOURTH,
        new TransposeContext(
            MusicalKey.C_SHARP_MAJOR.transposeChromatic(ChromaticInterval.PERFECT_FOURTH.getChromaticSteps()))))
                .isEqualTo(mapper.read("F\u266F7/D\u266F"));

    assertThat(mapper.read("ebmadd9/A").transposeChromatic(-1).getName()).isEqualTo("dmadd9/G#");
    assertThat(mapper.read("E\u266Dmadd9/A").transposeChromatic(-1).getName()).isEqualTo("Dmadd9/G\u266F");
    assertThat(mapper.read("Cis4add9no5/A").transposeChromatic(-1).getName()).isEqualTo("C4add9no5/Gis");

    // diatonic
    TransposeContext context = new TransposeContext(MusicalKey.C_SHARP_MAJOR);

    assertThat(mapper.read("C\u266F7/B\u266D").transpose(DiatonicInterval.THIRD, context))
        .isEqualTo(mapper.read("E\u266F7/D")); // MuseScore3
                                               // is
                                               // buggy
                                               // and
                                               // transposes
                                               // to
                                               // E#/D#
                                               // instead
    context.setNormalizeChords(true);
    assertThat(mapper.read("C\u266F7/Bb").transpose(DiatonicInterval.THIRD, context))
        .isEqualTo(mapper.read("E\u266F7/D"));
  }

}
