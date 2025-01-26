package io.github.musicdoc.bean;

import java.io.Reader;
import java.io.StringReader;

import javax.xml.stream.XMLInputFactory;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.musicdoc.config.AppConfig;
import io.github.musicdoc.config.ChordConfig;

/**
 * Test of {@link BeanXmlStaxMapper}.
 */
public class BeanXmlStaxMapperTest extends Assertions {

  public static final String PROFILE_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" //
      + "<myprofile>\n" //
      + "  <showCapoAsNumerals>true</showCapoAsNumerals>\n" //
      + "  <showCapoChords>true</showCapoChords>\n" //
      + "  <showChords>false</showChords>\n" //
      + "  <showNativeAndCapoChords>true</showNativeAndCapoChords>\n" //
      + "</myprofile>\n";

  @Test
  public void testLoadXmlStax() throws Exception {

    // given
    AppConfig config = new AppConfig();
    ChordConfig chordConfig = config.getChord();
    assertThat(chordConfig.showCapoAsNumerals.getValue()).isFalse();

    XMLInputFactory factory = XMLInputFactory.newInstance();
    Reader reader = new StringReader(PROFILE_XML);

    // when
    new BeanXmlStaxMapper(config).loadXml(reader);

    // then
    assertThat(chordConfig.showChords.get()).isFalse();
    assertThat(chordConfig.showCapoChords.get()).isTrue();
    assertThat(chordConfig.showCapoAsNumerals.get()).isTrue();
    assertThat(chordConfig.showNativeAndCapoChords.get()).isTrue();
  }

}
