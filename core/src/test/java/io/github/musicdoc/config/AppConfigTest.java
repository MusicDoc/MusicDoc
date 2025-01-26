package io.github.musicdoc.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test of {@link AppConfig}.
 */
public class AppConfigTest extends Assertions {

  @Test
  public void testDefaults() {

    // given + when
    AppConfig config = new AppConfig();
    ChordConfig chordConfig = config.getChord();

    // then
    assertThat(chordConfig.showChords.getValue()).isTrue();
    assertThat(chordConfig.showCapoChords.getValue()).isFalse();
    assertThat(chordConfig.showNativeAndCapoChords.getValue()).isFalse();
    assertThat(chordConfig.showCapoAsNumerals.getValue()).isFalse();
  }

}
