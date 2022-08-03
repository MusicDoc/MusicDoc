package io.github.musicdoc.format;

import org.junit.jupiter.api.Test;

import io.github.musicdoc.harmony.TonalSystem;
import io.github.musicdoc.harmony.chord.Chord;
import io.github.musicdoc.harmony.chord.ChordExtension;
import io.github.musicdoc.score.Score;
import io.github.musicdoc.score.ScoreRow;
import io.github.musicdoc.score.cell.ScoreCell;
import io.github.musicdoc.score.line.ScoreLine;
import io.github.musicdoc.score.line.ScoreVoiceLine;
import io.github.musicdoc.score.section.ScoreSection;
import io.github.musicdoc.score.section.ScoreSectionName;
import io.github.musicdoc.score.section.ScoreSectionType;
import io.github.musicdoc.song.Song;
import io.github.musicdoc.tone.pitch.TonePitchEnglish;

/**
 * Test of {@link SongFormatOpenSong}.
 */
public class SongFormatOpenSongTest extends SongFormatTest {

  private static final String LYRICS_WITH_CHORDS = "<?xml version=\"1.0\" ?><song><lyrics>" //
      + "[Chorus 1]\n" //
      + ".A Bm7 Cadd9        D\n" //
      + " a b   c the caT is  dead.\n" //
      + "</lyrics></song>";

  @Override
  protected SongFormat getFormat() {

    return SongFormatOpenSong.INSTANCE;
  }

  /**
   * Test of {@link SongFormatOpenSong#read(String)}.
   */
  @Test
  public void testParseLyricsWithChords() {

    // given
    String lyrics = LYRICS_WITH_CHORDS;

    // when
    Song song = SongFormatOpenSong.INSTANCE.getSongMapper().read(lyrics);
    Score score = song.score.getValue();

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
    ScoreLine line = row.getLine(0);
    assertThat(line).isInstanceOf(ScoreVoiceLine.class);
    ScoreVoiceLine voiceLine = (ScoreVoiceLine) line;
    assertThat(voiceLine.getCells()).hasSize(4);
    ScoreCell cell = voiceLine.getCell(0);
    assertThat(cell).isNotNull();
    assertThat(cell.getStaveChange()).isNull();
    assertThat(cell.getItem()).isNull();
    assertThat(cell.getChord()).isEqualTo(new Chord(TonePitchEnglish.A, TonalSystem.MAJOR_EMPTY));
    assertThat(cell.getLyric()).isEqualTo("a ");
    cell = voiceLine.getCell(1);
    assertThat(cell).isNotNull();
    assertThat(cell.getStaveChange()).isNull();
    assertThat(cell.getItem()).isNull();
    assertThat(cell.getChord()).isEqualTo(new Chord(TonePitchEnglish.B, TonalSystem.of("m"), ChordExtension._7));
    assertThat(cell.getLyric()).isEqualTo("b   ");
    cell = voiceLine.getCell(2);
    assertThat(cell).isNotNull();
    assertThat(cell.getStaveChange()).isNull();
    assertThat(cell.getItem()).isNull();
    assertThat(cell.getChord()).isEqualTo(new Chord(TonePitchEnglish.C, TonalSystem.MAJOR_EMPTY, ChordExtension.ADD_9));
    assertThat(cell.getLyric()).isEqualTo("c the caT is ");
    cell = voiceLine.getCell(3);
    assertThat(cell).isNotNull();
    assertThat(cell.getStaveChange()).isNull();
    assertThat(cell.getItem()).isNull();
    assertThat(cell.getChord()).isEqualTo(new Chord(TonePitchEnglish.D, TonalSystem.MAJOR_EMPTY));
    assertThat(cell.getLyric()).isEqualTo(" dead.");
  }

  /**
   * Test of {@link SongFormatOpenSong#write(Song, java.io.OutputStream)}.
   */
  @Test
  public void writeWithChords() {

    // given
    Score score = new Score();
    ScoreSectionName name = new ScoreSectionName("Chorus 1");
    ScoreSection section = new ScoreSection(name);
    ScoreRow row = new ScoreRow();
    ScoreVoiceLine line = new ScoreVoiceLine();
    line.add(new ScoreCell(new Chord(TonePitchEnglish.A, TonalSystem.MAJOR_EMPTY), "a "));
    line.add(new ScoreCell(new Chord(TonePitchEnglish.B, TonalSystem.MINOR_M, ChordExtension._7), "b "));
    line.add(
        new ScoreCell(new Chord(TonePitchEnglish.C, TonalSystem.MAJOR_EMPTY, ChordExtension.ADD_9), "c the caT is "));
    line.add(new ScoreCell(new Chord(TonePitchEnglish.D, TonalSystem.MAJOR_EMPTY), " dead."));
    row.addLine(line);
    section.getRows().add(row);
    score.getSections().add(section);
    Song song = new Song();
    song.score.setValue(score);

    // when
    String lyrics = SongFormatOpenSong.INSTANCE.getSongMapper().write(song);

    // then
    assertThat(lyrics).isEqualTo(LYRICS_WITH_CHORDS);
  }
}
