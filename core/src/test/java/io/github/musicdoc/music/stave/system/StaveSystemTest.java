package io.github.musicdoc.music.stave.system;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.musicdoc.MutableObject;
import io.github.musicdoc.music.clef.Clef;
import io.github.musicdoc.music.harmony.key.MusicalKey;
import io.github.musicdoc.music.rythm.beat.Beat;
import io.github.musicdoc.music.stave.Stave;
import io.github.musicdoc.music.stave.StaveBracket;
import io.github.musicdoc.music.stave.voice.StaveVoice;
import io.github.musicdoc.music.stave.voice.StaveVoiceContainer;

/**
 * Test of {@link StaveSystem}.
 */
public class StaveSystemTest extends Assertions {

  @Test
  public void test() {

    // given + when
    StaveSystem root = StaveSystemMultiple.of(StaveBracket.NONE);
    StaveSystem choir = root.addChild(StaveBracket.SQUARE);
    MusicalKey key = MusicalKey.C_MAJOR;
    Beat beat = Beat.COMMON_TIME;
    Stave treble = new Stave(Clef.TREBLE, key, beat);
    StaveVoice voiceS = StaveVoice.SOPRANO.makeMutable();
    treble.addVoice(voiceS);
    StaveVoice voiceA = StaveVoice.ALTO.makeMutable();
    treble.addVoice(voiceA);
    StaveSystemSingle trebleSystem = choir.addChild(treble);
    Stave bass = new Stave(Clef.BASS, key, beat);
    StaveVoice voiceT = StaveVoice.TENOR.makeMutable();
    bass.addVoice(voiceT);
    StaveVoice voiceB = StaveVoice.BASS.makeMutable();
    bass.addVoice(voiceB);
    StaveSystemSingle bassSystem = choir.addChild(bass);
    StaveSystem piano = root.addChild(StaveBracket.CURLY);
    Stave pianoG = new Stave(Clef.G, key, beat);
    StaveVoice voiceP1 = new StaveVoice("P1", "Piano1", "P1");
    pianoG.addVoice(voiceP1);
    StaveSystemSingle pianoGSystem = piano.addChild(pianoG);
    Stave pianoF = new Stave(Clef.F, key, beat);
    StaveVoice voiceP2 = new StaveVoice("P2", "Piano2", "P2");
    pianoF.addVoice(voiceP2);
    StaveSystemSingle pianoFSystem = piano.addChild(pianoF);

    // then
    assertThat(root.getBracket()).isSameAs(StaveBracket.NONE);
    assertThat(root.getChildren()).containsExactly(choir, piano);
    assertThat(choir.getChildren()).containsExactly(trebleSystem, bassSystem);
    assertThat(piano.getChildren()).containsExactly(pianoGSystem, pianoFSystem);
    check(root, voiceS, voiceA, voiceT, voiceB, voiceP1, voiceP2);
    check(choir, voiceS, voiceA, voiceT, voiceB);
    check(piano, voiceP1, voiceP2);
    root.makeImmutable();

  }

  private void checkImmutableObject(MutableObject<?> object, boolean immutable) {

    if (object == null) {
      return;
    }
    assertThat(object.isImmutable()).as(immutable ? "IsImmutable " : "isMutable " + object.toString()).isEqualTo(immutable)
        .isEqualTo(!object.isMutable());
    if (!immutable) {
      assertThat(object.makeMutable()).isSameAs(object);
    }
  }

  private void checkImmutable(StaveSystem system, boolean immutable) {

    checkImmutableObject(system, immutable);
    for (StaveSystem child : system.getChildren()) {
      checkImmutable(child, immutable);
    }
    checkImmutable(system.getStave(), immutable);
  }

  private void checkImmutable(Stave stave, boolean immutable) {

    if (stave == null) {
      return;
    }
    checkImmutableObject(stave, immutable);
    checkImmutableObject(stave.getClef(), immutable);
    // checkImmutableObject(stave.getKey(), immutable);
    // checkImmutableObject(stave.getBeat(), immutable);
    for (StaveVoice voice : stave.getVoices()) {
      checkImmutable(voice, immutable);
    }
  }

  private void checkImmutable(StaveVoice voice, boolean immutable) {

    checkImmutableObject(voice, immutable);
    // checkImmutableObject(voice.getInstrument(), immutable);

  }

  private void check(StaveVoiceContainer container, StaveVoice... voices) {

    for (int i = 0; i < voices.length; i++) {
      assertThat(container.getVoice(i)).isSameAs(voices[i]);
      assertThat(container.getVoice(voices[i].getId())).isSameAs(voices[i]);
      assertThat(container.indexOf(voices[i].getId())).isEqualTo(i);
    }
    assertThat(container.getVoice(-1)).isNull();
    assertThat(container.getVoice(voices.length)).isNull();
    assertThat(container.indexOf("<undefinedId>")).isEqualTo(-1);
  }

}
