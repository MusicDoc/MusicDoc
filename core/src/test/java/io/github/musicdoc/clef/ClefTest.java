package io.github.musicdoc.clef;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.musicdoc.glyphs.smufl.SmuflGlyphsClefs;
import io.github.musicdoc.interval.ChromaticInterval;
import io.github.musicdoc.interval.ToneInterval;
import io.github.musicdoc.tone.Tone;

/**
 * Test of {@link Clef}.
 */
public class ClefTest extends Assertions {

  /** Test of {@link Clef#TREBLE}. */
  @Test
  public void testTreble() {

    Clef clef = Clef.TREBLE;
    assertThat(clef.getSymbol()).isSameAs(ClefSymbol.G);
    assertThat(clef.getMiddleTone()).isSameAs(Tone.B4);
    assertThat(clef.getReferenceTone()).isSameAs(Tone.G4);
    assertThat(clef.getShift()).isSameAs(ChromaticInterval.PERFECT_UNISON);
    assertThat(clef.getGlyphs()).isSameAs(SmuflGlyphsClefs.G_CLEV);
  }

  /** Test of {@link Clef#BASS}. */
  @Test
  public void testBass() {

    Clef clef = Clef.BASS;
    assertThat(clef.getSymbol()).isSameAs(ClefSymbol.F);
    assertThat(clef.getMiddleTone()).isSameAs(Tone.D3);
    assertThat(clef.getReferenceTone()).isSameAs(Tone.F3);
    assertThat(clef.getShift()).isSameAs(ChromaticInterval.PERFECT_UNISON);
    assertThat(clef.getGlyphs()).isSameAs(SmuflGlyphsClefs.F_CLEV);
  }

  /** Test of {@link Clef#ALTO}. */
  @Test
  public void testAlto() {

    Clef clef = Clef.ALTO;
    assertThat(clef.getSymbol()).isSameAs(ClefSymbol.C);
    assertThat(clef.getMiddleTone()).isSameAs(Tone.C4);
    assertThat(clef.getReferenceTone()).isSameAs(Tone.C4);
    assertThat(clef.getShift()).isSameAs(ChromaticInterval.PERFECT_UNISON);
    assertThat(clef.getGlyphs()).isSameAs(SmuflGlyphsClefs.C_CLEV);
  }

  /** Test of {@link Clef#TENOR}. */
  @Test
  public void testTenor() {

    Clef clef = Clef.TENOR;
    ChromaticInterval interval = ChromaticInterval.of(-3);
    assertThat(clef.getSymbol()).isSameAs(ClefSymbol.C);
    assertThat(clef.getMiddleTone()).isSameAs(Tone.A3);
    assertThat(clef.getReferenceTone()).isSameAs(Tone.C4);
    assertThat(clef.getShift()).isEqualTo(interval);
    assertThat(clef.getGlyphs()).isSameAs(SmuflGlyphsClefs.C_CLEV);
    checkIsAltoShift(clef, interval);
  }

  /** Test of {@link Clef#SOPRANO}. */
  @Test
  public void testSoprano() {

    Clef clef = Clef.SOPRANO;
    ChromaticInterval interval = ChromaticInterval.PERFECT_FIFTH;
    assertThat(clef.getSymbol()).isSameAs(ClefSymbol.C);
    assertThat(clef.getMiddleTone()).isSameAs(Tone.G4);
    assertThat(clef.getReferenceTone()).isSameAs(Tone.C4);
    assertThat(clef.getShift()).isSameAs(interval);
    assertThat(clef.getGlyphs()).isSameAs(SmuflGlyphsClefs.C_CLEV);
    checkIsAltoShift(clef, interval);
  }

  /** Test of {@link Clef#BARITONE}. */
  @Test
  public void testBariton() {

    Clef clef = Clef.BARITONE;
    ChromaticInterval interval = ChromaticInterval.of(-7);
    assertThat(clef.getSymbol()).isSameAs(ClefSymbol.C);
    assertThat(clef.getMiddleTone()).isSameAs(Tone.F3);
    assertThat(clef.getReferenceTone()).isSameAs(Tone.C4);
    assertThat(clef.getShift()).isEqualTo(interval);
    assertThat(clef.getGlyphs()).isSameAs(SmuflGlyphsClefs.C_CLEV);
    checkIsAltoShift(clef, interval);
  }

  /** Test of {@link Clef#MEZZO_SOPRANO}. */
  @Test
  public void testMezzoSoprano() {

    Clef clef = Clef.MEZZO_SOPRANO;
    ChromaticInterval interval = ChromaticInterval.MAJOR_THIRD;
    assertThat(clef.getSymbol()).isSameAs(ClefSymbol.C);
    assertThat(clef.getMiddleTone()).isSameAs(Tone.E4);
    assertThat(clef.getReferenceTone()).isSameAs(Tone.C4);
    assertThat(clef.getShift()).isSameAs(interval);
    assertThat(clef.getGlyphs()).isSameAs(SmuflGlyphsClefs.C_CLEV);
    checkIsAltoShift(clef, interval);
  }

  private void checkIsAltoShift(Clef clef, ToneInterval shift) {

    assertThat(Clef.ALTO.setShiftAdd(shift).setName(clef.getName())).isEqualTo(clef);
  }

}
