package io.github.musicdoc.music.format;

import org.junit.jupiter.api.Test;

import io.github.musicdoc.music.harmony.TonalSystem;
import io.github.musicdoc.music.harmony.chord.Chord;
import io.github.musicdoc.music.harmony.chord.ChordExtension;
import io.github.musicdoc.music.harmony.chord.ChordMapperOpenSong;
import io.github.musicdoc.music.score.Score;
import io.github.musicdoc.music.score.ScoreLine;
import io.github.musicdoc.music.score.ScoreRow;
import io.github.musicdoc.music.score.section.ScoreSection;
import io.github.musicdoc.music.score.section.ScoreSectionName;
import io.github.musicdoc.music.score.section.ScoreSectionType;
import io.github.musicdoc.music.score.voice.ScoreVoiceCell;
import io.github.musicdoc.music.score.voice.ScoreVoiceLine;
import io.github.musicdoc.music.song.Song;
import io.github.musicdoc.music.tone.TonePitchEnglish;

/**
 * Test of {@link SongFormatOpenSong}.
 */
public class SongFormatOpenSongTest extends SongFormatTest {

  private static final String LYRICS_WITH_CHORDS = "[Chorus 1]\n" + ".A Bm7 Cadd9        D\n"
      + " a b   c the caT is  dead.\n";

  /**
   * Test of {@link SongFormatOpenSong#parse(String)}.
   */
  @Test
  public void testParseLyricsWithChords() {

    // given
    String lyrics = LYRICS_WITH_CHORDS;

    // when
    Score score = SongFormatOpenSong.INSTANCE.getScoreMapper().parse(lyrics);

    // then
    assertThat(score).isNotNull();
    assertThat(score.getSections()).hasSize(1);
    ScoreSection section = score.getSections().get(0);
    assertThat(section).isNotNull();
    ScoreSectionName name = section.getName();
    assertThat(name.getName()).isEqualTo("Chorus 1");
    assertThat(name.getType()).isSameAs(ScoreSectionType.CHORUS);
    assertThat(name.getSuffix()).isEqualTo(" 1");
    assertThat(section.getRows()).hasSize(1);
    ScoreRow row = section.getRows().get(0);
    assertThat(row).isNotNull();
    assertThat(row.getColumnCount()).isEqualTo(4);
    assertThat(row.getLines()).hasSize(1);
    ScoreLine<?, ?> line = row.getLine(0);
    assertThat(line).isInstanceOf(ScoreVoiceLine.class);
    ScoreVoiceLine voiceLine = (ScoreVoiceLine) line;
    assertThat(voiceLine.getCells()).hasSize(4);
    ScoreVoiceCell cell = voiceLine.getCell(0);
    assertThat(cell).isNotNull();
    assertThat(cell.getStave()).isNull();
    assertThat(cell.getItem()).isNull();
    assertThat(cell.getChord()).isEqualTo(new Chord(TonePitchEnglish.A, TonalSystem.MAJOR_EMPTY));
    assertThat(cell.getLyric()).isEqualTo("a ");
    cell = voiceLine.getCell(1);
    assertThat(cell).isNotNull();
    assertThat(cell.getStave()).isNull();
    assertThat(cell.getItem()).isNull();
    assertThat(cell.getChord()).isEqualTo(new Chord(TonePitchEnglish.B, TonalSystem.of("m"), ChordExtension._7));
    assertThat(cell.getLyric()).isEqualTo("b   ");
    cell = voiceLine.getCell(2);
    assertThat(cell).isNotNull();
    assertThat(cell.getStave()).isNull();
    assertThat(cell.getItem()).isNull();
    assertThat(cell.getChord()).isEqualTo(new Chord(TonePitchEnglish.C, TonalSystem.MAJOR_EMPTY, ChordExtension.ADD_9));
    assertThat(cell.getLyric()).isEqualTo("c the caT is ");
    cell = voiceLine.getCell(3);
    assertThat(cell).isNotNull();
    assertThat(cell.getStave()).isNull();
    assertThat(cell.getItem()).isNull();
    assertThat(cell.getChord()).isEqualTo(new Chord(TonePitchEnglish.D, TonalSystem.MAJOR_EMPTY));
    assertThat(cell.getLyric()).isEqualTo(" dead.");
  }

  /**
   * Test of {@link SongFormatOpenSong#format(Song)}.
   */
  @Test
  public void formatLyricsWithChords() {

    // given
    Score score = new Score();
    ScoreSectionName name = new ScoreSectionName("Chorus 1");
    ScoreSection section = new ScoreSection(name);
    ScoreRow row = new ScoreRow();
    ScoreVoiceLine line = new ScoreVoiceLine();
    line.addCell(new ScoreVoiceCell(new Chord(TonePitchEnglish.A, TonalSystem.MAJOR_EMPTY), "a "));
    line.addCell(new ScoreVoiceCell(ChordMapperOpenSong.INSTANCE.parse("Bm7"), "b "));
    line.addCell(new ScoreVoiceCell(new Chord(TonePitchEnglish.C, TonalSystem.MAJOR_EMPTY, ChordExtension.ADD_9),
        "c the caT is "));
    line.addCell(new ScoreVoiceCell(new Chord(TonePitchEnglish.D, TonalSystem.MAJOR_EMPTY), " dead."));
    row.addLine(line);
    section.getRows().add(row);
    score.getSections().add(section);

    // when
    String lyrics = SongFormatOpenSong.INSTANCE.getScoreMapper().format(score);

    // then
    assertThat(lyrics).isEqualTo(LYRICS_WITH_CHORDS);
  }
}
