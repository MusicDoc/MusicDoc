package io.github.musicdoc.config;

import java.io.Reader;
import java.io.StringReader;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.github.musicdoc.bean.BeanXmlLegacyMapper;

public class ConfigXmlLegacyMapperTest extends Assertions {

  public static final String PROFILE_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" //
      + "<myprofile>\n" //
      + "  <showChords>false</showChords>\n" //
      + "  <showCapoChords>true</showCapoChords>\n" //
      + "  <showCapoAsNumerals>true</showCapoAsNumerals>\n" //
      + "  <showNativeAndCapoChords>true</showNativeAndCapoChords>\n" //
      + "</myprofile>\n";

  @Test
  public void testAsXmlProfile() {

    // given
    AppConfig config = new AppConfig();
    ChordConfig chordConfig = config.getChord();
    chordConfig.showChords.set(false);
    chordConfig.showCapoChords.set(true);
    chordConfig.showNativeAndCapoChords.set(true);
    chordConfig.showCapoAsNumerals.set(true);
    BeanXmlLegacyMapper mapper = new BeanXmlLegacyMapper(config);

    // when
    String xml = mapper.saveXmlAsString();

    // then
    // this is actually not a good test strategy.
    // If the order of the keySet of the property map changes (it is unspecified and might change with JDK or OS)
    // the test will break and fail
    assertThat(xml).isEqualTo(ConfigXmlStaxMapperTest.PROFILE_XML);
  }

  @Test
  @Disabled("XPP is android internal and can not be tested by regular unit test, requires integration test.")
  public void testLoadXmlXpp() throws Exception {

    // given
    AppConfig config = new AppConfig();
    ChordConfig chordConfig = config.getChord();
    assertThat(chordConfig.showCapoAsNumerals.get()).isFalse();

    Reader reader = new StringReader(ConfigXmlStaxMapperTest.PROFILE_XML);
    try {
      BeanXmlLegacyMapper mapper = new BeanXmlLegacyMapper(config);

      // when
      mapper.loadXml(reader);
    } finally {
      reader.close();
    }

    // then
    assertThat(chordConfig.showChords.get()).isFalse();
    assertThat(chordConfig.showCapoChords.get()).isTrue();
    assertThat(chordConfig.showCapoAsNumerals.get()).isTrue();
    assertThat(chordConfig.showNativeAndCapoChords.get()).isTrue();
  }

}
