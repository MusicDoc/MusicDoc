package io.github.musicdoc.rythm;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.musicdoc.rhythm.fraction.PlainFraction;

/**
 * Test of {@link PlainFraction}.
 */
public class PlainFractionTest extends Assertions {

  /**
   * Test of {@link PlainFraction#normalize()}.
   */
  @Test
  public void testNormalize() {

    assertThat(PlainFraction.of(4, 2).normalize()).hasToString("2/1");
    assertThat(PlainFraction.of(6, 3).normalize()).hasToString("2/1");
    assertThat(PlainFraction.of(2, 4).normalize()).hasToString("1/2");
    assertThat(PlainFraction.of(3, 6).normalize()).hasToString("1/2");
    assertThat(PlainFraction.of(1, 4).normalize()).hasToString("1/4");
    assertThat(PlainFraction.of(3, 4).normalize()).hasToString("3/4");
    assertThat(PlainFraction.of(12, 8).normalize()).hasToString("3/2");
    assertThat(PlainFraction.of(6, 4).normalize()).hasToString("3/2");
  }

}
