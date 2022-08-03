package io.github.musicdoc.interval;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.musicdoc.interval.ChromaticInterval;
import io.github.musicdoc.interval.ToneInterval;

/**
 * Test of {@link ChromaticInterval}.
 */
public class ChromaticIntervalTest extends Assertions {

  private void check(ChromaticInterval interval, int chromatic, int diatonic) {

    check(interval, chromatic, diatonic, true, 0);
  }

  private void check(ChromaticInterval interval, int chromatic, int diatonic, boolean reference, int octaves) {

    assertThat(interval.getChromaticSteps(null)).isEqualTo(chromatic);
    assertThat(interval.getDiatonicSteps(null)).isEqualTo(diatonic);
    assertThat(interval.getOctaves()).isEqualTo(octaves);
    assertThat(interval.isEmpty()).isEqualTo(chromatic == 0);
    ChromaticInterval copy = ChromaticInterval.of(chromatic);
    if (reference) {
      assertThat(copy).isSameAs(interval);
    } else {
      assertThat(copy).isNotEqualTo(interval);
    }
    if (chromatic > 0) {
      ToneInterval inverted = interval.invert();
      assertThat(inverted.getChromaticSteps(null)).isEqualTo(-chromatic);
      assertThat(inverted.getOctaves()).isEqualTo(-octaves);
      assertThat(inverted.getDiatonicSteps(null)).isEqualTo(-diatonic);
      if (reference) {
        assertThat(inverted.invert()).isSameAs(interval);
      }
    }
  }

  @Test
  public void testConstants() {

    check(ChromaticInterval.PERFECT_UNISON, 0, 0);
    check(ChromaticInterval.MINOR_SECOND, 1, 1);
    check(ChromaticInterval.MAJOR_SECOND, 2, 1);
    check(ChromaticInterval.MINOR_THIRD, 3, 2);
    check(ChromaticInterval.MAJOR_THIRD, 4, 2);
    check(ChromaticInterval.PERFECT_FOURTH, 5, 3);
    check(ChromaticInterval.AUGMENTED_FOURTH, 6, 3, false, 0);
    check(ChromaticInterval.DIMINISHED_FIFTH, 6, 4);
    check(ChromaticInterval.PERFECT_FIFTH, 7, 4);
    check(ChromaticInterval.DIMINISHED_SIXT, 7, 5, false, 0);
    check(ChromaticInterval.MINOR_SIXT, 8, 5);
    check(ChromaticInterval.MAJOR_SIXT, 9, 5);
    check(ChromaticInterval.AUGMENTED_SIXT, 10, 5, false, 0);
    check(ChromaticInterval.DIMINISHED_SEVENTH, 9, 6, false, 0);
    check(ChromaticInterval.MINOR_SEVENTH, 10, 6);
    check(ChromaticInterval.MAJOR_SEVENTH, 11, 6);
    check(ChromaticInterval.PERFECT_OCTAVE, 12, 7, true, 1);
    check(ChromaticInterval.MINOR_NINTH, 13, 8, true, 1);
    check(ChromaticInterval.MAJOR_NINTH, 14, 8, true, 1);
    check(ChromaticInterval.MINOR_TENTH, 15, 9, true, 1);
    check(ChromaticInterval.MAJOR_TENTH, 16, 9, true, 1);
    check(ChromaticInterval.PERFECT_ELEVENTH, 17, 10, true, 1);
    check(ChromaticInterval.DIMINISHED_TWELVE, 18, 11, true, 1);
    check(ChromaticInterval.PERFECT_TWELVE, 19, 11, true, 1);
    check(ChromaticInterval.MINOR_THIRTEENTH, 20, 12, true, 1);
    check(ChromaticInterval.MAJOR_THIRTEENTH, 21, 12, true, 1);
  }

  public void testNegative() {

    ChromaticInterval interval = ChromaticInterval.of(-3);
    assertThat(interval.getChromaticSteps(null)).isEqualTo(-3);
    assertThat(interval.getDiatonicSteps(null)).isEqualTo(-2);
    assertThat(interval.getOctaves()).isEqualTo(0);
    assertThat(interval.invert()).isSameAs(ChromaticInterval.MINOR_THIRD);
    // negative octaves
    assertThat(ChromaticInterval.of(-11).getOctaves()).isEqualTo(0);
    assertThat(ChromaticInterval.of(-12).getOctaves()).isEqualTo(-1);
    assertThat(ChromaticInterval.of(-23).getOctaves()).isEqualTo(-1);
    assertThat(ChromaticInterval.of(-24).getOctaves()).isEqualTo(-2);
  }

}
