package io.github.musicdoc.io;

import java.io.InputStream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.musicdoc.song.Song;

/**
 * Test of {@link XmlMusicInputStream}.
 */
public class XmlMusicInputStreamTest extends Assertions {

  @Test
  public void test() {

    InputStream inputStream = Song.class.getResourceAsStream("greensleeves.opensong");
    XmlMusicInputStream in = XmlMusicInputStream.of(inputStream);
    assertThat(in.readPropertyStart()).isEqualTo("title");
    assertThat(in.readPropertyValue()).isEqualTo("Greensleeves");
    assertThat(in.readPropertyStart()).isEqualTo("author");
    assertThat(in.readPropertyValue()).isEqualTo("Traditional");
    assertThat(in.readPropertyStart()).isEqualTo("key");
    assertThat(in.readPropertyValue()).isEqualTo("C");
    assertThat(in.readPropertyStart()).isEqualTo("timesig");
    assertThat(in.readPropertyValue()).isEqualTo("3/4");
    assertThat(in.readPropertyStart()).isEqualTo("tempo");
    assertThat(in.readPropertyValue()).isEqualTo("1/4=100");
    assertThat(in.readPropertyStart()).isEqualTo("lyrics");
    assertThat(in.getScanner().readLine()).isEqualTo("[V1]");
    assertThat(in.getScanner().readLine())
        .isEqualTo(".   Am       D7          G      Em          F                  E");
    assertThat(in.getScanner().readLine())
        .isEqualTo(" A-|las, my |lo-ve, you |do me |wro-ng, to |cast me |o-ff dis-|cour-teous-|ly. And|");
    assertThat(in.getScanner().readLine())
        .isEqualTo(".Am      D7        G       Em         F            E7         Am");
    assertThat(in.getScanner().readLine())
        .isEqualTo(" I have |lo-ved _ |you so |lo-ng, de-|ligh-_ ting |in _ your |com-pa-|ny.||");
    assertThat(in.getScanner().readLine()).isEqualTo("[C]");
    assertThat(in.getScanner().readLine()).isEqualTo(".C                    Bm      Em        Am      F             E");
    assertThat(in.getScanner().readLine())
        .isEqualTo(" Green-|slee-ves was |all my |joy, _ _ |Gre-en-|sle-eves was |my de-|light,|");
    assertThat(in.getScanner().readLine())
        .isEqualTo(".C                      G         Em          F           E7       Am");
    assertThat(in.getScanner().readLine())
        .isEqualTo(" Green-|sleeves was my |heart of |go-ld, and |who but my |la-dy _ |Green-|sleeves.||*");
    assertThat(in.readPropertyValue()).isEqualTo("");
    assertThat(in.getScanner().hasNext()).isFalse();
  }

}
