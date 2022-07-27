package io.github.musicdoc.io;

import java.io.InputStream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.musicdoc.music.song.Song;

/**
 * Test of {@link XmlMusicInputStream}.
 */
public class XmlMusicInputStreamTest extends Assertions {

  @Test
  public void test() {

    InputStream inputStream = Song.class.getResourceAsStream("greensleeves.opensong");
    XmlMusicInputStream in = XmlMusicInputStream.of(inputStream);
    assertThat(in.readPropertyStart()).isEqualTo("title");
    assertThat(in.readPropertyValue()).isEqualTo("Greensleves");
    assertThat(in.readPropertyStart()).isEqualTo("author");
    assertThat(in.readPropertyValue()).isEqualTo("Traditional");
    assertThat(in.readPropertyStart()).isEqualTo("tempo");
    assertThat(in.readPropertyValue()).isEqualTo("100");
    assertThat(in.readPropertyStart()).isEqualTo("timesig");
    assertThat(in.readPropertyValue()).isEqualTo("3/4");
    assertThat(in.readPropertyStart()).isEqualTo("key");
    assertThat(in.readPropertyValue()).isEqualTo("C");
    assertThat(in.readPropertyStart()).isEqualTo("lyrics");
    assertThat(in.readLine()).isEqualTo("[V1]");
    assertThat(in.readLine()).isEqualTo(".   Am      D7         G     Em         F                 E");
    assertThat(in.readLine())
        .isEqualTo(" A-|las, my|lo-ve, you|do me|wro-ng, to|cast me|o-ff dis-|cour-teous-|ly. And|");
    assertThat(in.readLine()).isEqualTo(".Am     D7      G      Em         F          E7       Am");
    assertThat(in.readLine()).isEqualTo(" I have|lo-ved_|you so|lo-ng, de-|ligh-_ting|in_ your|com-pa-|ny.||");
    assertThat(in.readLine()).isEqualTo("[C]");
    assertThat(in.readLine()).isEqualTo(".C                   Bm     Em     Am      F            E");
    assertThat(in.readLine()).isEqualTo(" Green-|slee-ves was|all my|joy,__|Gre-en-|sle-eves was|my de-|light,|");
    assertThat(in.readLine()).isEqualTo(".C                     G        Em         F          E7      Am");
    assertThat(in.readLine())
        .isEqualTo(" Green-|sleeves was my|heart of|go-ld, and|who but my|la-_dy_|Green-|sleeves.|]");
    assertThat(in.readPropertyValue()).isEqualTo("");
    assertThat(in.hasNext()).isFalse();
  }

}
