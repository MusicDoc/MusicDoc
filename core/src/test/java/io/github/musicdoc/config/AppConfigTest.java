package io.github.musicdoc.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppConfigTest extends Assertions {

    @Test
    public void testDefaults() {

        // given + when
        AppConfig config = new AppConfig();
        ChordConfig chordConfig = config.getChord();

        // then
        assertThat(chordConfig.showChords.get()).isTrue();
        assertThat(chordConfig.showCapoChords.get()).isFalse();
        assertThat(chordConfig.showNativeAndCapoChords.get()).isFalse();
        assertThat(chordConfig.showCapoAsNumerals.get()).isFalse();
    }

}
