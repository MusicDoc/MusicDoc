package io.github.musicdoc.rythm.value;

import org.junit.jupiter.api.Test;

import io.github.musicdoc.AbstractTest;

/**
 * Test of {@link MusicalValueMapper}.
 */
public class MusicalValueMapperTest extends AbstractTest {

  /**
   * Test of {@link MusicalValueMapper#usic(String)}.
   */
  @Test
  public void testParse() {

    MusicalValueMapper mapper = MusicalValueMapperMusicDoc.INSTANCE;
    assertThat(mapper.read("1/1")).isEqualTo(MusicalValue._1_1);
    assertThat(mapper.read("4/4")).isEqualTo(MusicalValue._4_4);
    assertThat(mapper.read("1/2")).isEqualTo(MusicalValue._1_2);
    assertThat(mapper.read("1/4")).isEqualTo(MusicalValue._1_4);
    assertThat(mapper.read("1/8")).isEqualTo(MusicalValue._1_8);
    assertThat(mapper.read("1")).isEqualTo(MusicalValue._1_1);
    assertThat(mapper.read("/2")).isEqualTo(MusicalValue._1_2);
    mapper = MusicalValueMapperAbc.INSTANCE;
    assertThat(mapper.read("1")).isEqualTo(MusicalValue._1_8);
    assertThat(mapper.read("2")).isEqualTo(MusicalValue._1_4);
    assertThat(mapper.read("4")).isEqualTo(MusicalValue._1_2);
    assertThat(mapper.read("/2")).isEqualTo(MusicalValue._1_16);
  }

}
