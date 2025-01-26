package io.github.musicdoc.property;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test of {@link StringNormalizer}.
 */
public class StringNormalizerTest extends Assertions {

  /** Test of {@link StringNormalizer#normalize(String)}. */
  @Test
  public void testNormalize() {

    check("ÄÖÜ-äöü_ß", "aeoeue aeoeue ss");
    check("The Beatles", "beatles");
    check("The Mamas & the Papas", "mamas and papas");
    check("A Hard Day's Night", "hard days night");
    check("I had a Dream", "i had dream");
  }

  private void check(String string, String normalized) {

    assertThat(StringNormalizer.normalize(string)).as(string).isEqualTo(normalized);
  }

}
