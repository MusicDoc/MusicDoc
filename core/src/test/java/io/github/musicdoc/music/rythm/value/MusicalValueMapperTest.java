package io.github.musicdoc.music.rythm.value;

import org.junit.jupiter.api.Test;

import io.github.musicdoc.AbstractTest;

/**
 * Test of {@link MusicalValueMapper}.
 */
public class MusicalValueMapperTest extends AbstractTest {

  /**
   * Test of {@link MusicalValueMapper#parse(String)}.
   */
  @Test
  public void testParse() {

    MusicalValueMapper mapper = MusicalValueMapper.INSTANCE;
    assertThat(mapper.parse("1/1")).isEqualTo(MusicalValue._1_1);
    assertThat(mapper.parse("4/4")).isEqualTo(MusicalValue._4_4);
    assertThat(mapper.parse("1/2")).isEqualTo(MusicalValue._1_2);
    assertThat(mapper.parse("1/4")).isEqualTo(MusicalValue._1_4);
    assertThat(mapper.parse("1/8")).isEqualTo(MusicalValue._1_8);
    assertThat(mapper.parse("4")).isEqualTo(MusicalValue._4_4);
    assertThat(mapper.parse("2")).isEqualTo(MusicalValue._1_2);
    assertThat(mapper.parse("/2")).isEqualTo(MusicalValue._1_8);
  }

}
