package io.github.musicdoc.io;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.musicdoc.song.Song;

/**
 * Test of {@link TextMusicInputStream}.
 */
public class TextMusicInputStreamTest extends Assertions {

  @Test
  public void testReadGreensleeves() {

    InputStream inputStream = Song.class.getResourceAsStream("greensleeves.abc");
    TextMusicInputStream in = TextMusicInputStream.of(inputStream);
    assertThat(in.readPropertyStart()).isEqualTo("X");
    assertThat(in.readPropertyValue()).isEqualTo("1");
    assertThat(in.readPropertyStart()).isEqualTo("T");
    assertThat(in.readPropertyValue()).isEqualTo("Greensleeves");
    assertThat(in.readPropertyStart()).isEqualTo("C");
    assertThat(in.readPropertyValue()).isEqualTo("Traditional");
    assertThat(in.readPropertyStart()).isEqualTo("K");
    assertThat(in.readPropertyValue()).isEqualTo("C");
    assertThat(in.readPropertyStart()).isEqualTo("M");
    assertThat(in.readPropertyValue()).isEqualTo("3/4");
    assertThat(in.readPropertyStart()).isEqualTo("Q");
    assertThat(in.readPropertyValue()).isEqualTo("1/4=100");
    assertThat(in.readPropertyStart()).isEqualTo("L");
    assertThat(in.readPropertyValue()).isEqualTo("1/4");
    assertThat(in.getScanner().readLine()).isEqualTo("%%text V1");
    assertThat(in.getScanner().readLine()).isEqualTo(
        "A | \"Am\"c2 d | \"D7\"(e>f)e | \"G\"d2 B | \"Em\"(G>A)B | \"F\"c2 A | (A>^G)A | \"E\"B2 ^G | E2 A |");
    assertThat(in.readPropertyStart()).isEqualTo("w");
    assertThat(in.readPropertyValue())
        .isEqualTo("A-las, my lo-ve, you do me wro-ng, to cast me o-ff dis-cour-teous-ly. And");
    assertThat(in.getScanner().readLine()).isEqualTo(
        "\"Am\"c2 d | \"D7\"(e>f e) | \"G\"d2 B | \"Em\"(G>A)B | \"F\"(c>B)A | \"E7\"(^G>^F)G | \"Am\"A2 A | A3 ||");
    assertThat(in.readPropertyStart()).isEqualTo("w");
    assertThat(in.readPropertyValue()).isEqualTo("I have lo-ved _ you so lo-ng, de-ligh-_ ting in _ your com-pa-ny.");
    assertThat(in.getScanner().readLine()).isEqualTo("%%text C");
    assertThat(in.getScanner().readLine())
        .isEqualTo("\"C\"g3 | (g>f)e | \"Bm\"d2 B | \"Em\"(G A B) | \"Am\"(c2 A) | \"F\"(A>^G)A | \"E\"B2 ^G | E3 |");
    assertThat(in.readPropertyStart()).isEqualTo("w");
    assertThat(in.readPropertyValue()).isEqualTo("Green-slee-ves was all my joy, _ _ Gre-en-sle-eves was my de-light,");
    assertThat(in.getScanner().readLine())
        .isEqualTo("\"C\"g3 | (g>f)e | \"G\"d2 B | \"Em\"(G>A)B | \"F\"c B A | \"E7\"(^G ^F G) | \"Am\"A3 | A2 |]");
    assertThat(in.readPropertyStart()).isEqualTo("w");
    assertThat(in.readPropertyValue())
        .isEqualTo("Green-sleeves was my heart of go-ld, and who but my la-dy _ Green-sleeves.");
    assertThat(in.getScanner().hasNext()).isFalse();
  }

  @Test
  public void testReadContinuation() {

    String payload = "X:1\n" + //
        "+:\" \\\"2\"\r\n" + //
        "+:3\"";
    byte[] bytes = payload.getBytes(StandardCharsets.UTF_8);
    InputStream inputStream = new ByteArrayInputStream(bytes);
    TextMusicInputStream in = TextMusicInputStream.of(inputStream, PropertyState.of("+:"));
    assertThat(in.readPropertyStart()).isEqualTo("X");
    assertThat(in.readPropertyValue()).isEqualTo("1 \"23\"");
  }
}
