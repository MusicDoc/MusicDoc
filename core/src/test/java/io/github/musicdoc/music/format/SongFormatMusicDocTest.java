package io.github.musicdoc.music.format;

import org.junit.jupiter.api.Test;

import io.github.musicdoc.music.clef.Clef;
import io.github.musicdoc.music.harmony.MusicalKey;
import io.github.musicdoc.music.harmony.TonalSystem;
import io.github.musicdoc.music.harmony.chord.Chord;
import io.github.musicdoc.music.harmony.chord.ChordExtension;
import io.github.musicdoc.music.harmony.chord.ChordMapperMusicDoc;
import io.github.musicdoc.music.note.Note;
import io.github.musicdoc.music.rythm.beat.Beat;
import io.github.musicdoc.music.rythm.rest.Rest;
import io.github.musicdoc.music.rythm.value.MusicalValue;
import io.github.musicdoc.music.score.Score;
import io.github.musicdoc.music.score.ScoreLine;
import io.github.musicdoc.music.score.ScoreRow;
import io.github.musicdoc.music.score.section.ScoreSection;
import io.github.musicdoc.music.score.section.ScoreSectionName;
import io.github.musicdoc.music.score.section.ScoreSectionType;
import io.github.musicdoc.music.score.voice.ScoreVoiceCell;
import io.github.musicdoc.music.score.voice.ScoreVoiceLine;
import io.github.musicdoc.music.score.voice.ScoreVoiceLineContinuation;
import io.github.musicdoc.music.song.Song;
import io.github.musicdoc.music.stave.Stave;
import io.github.musicdoc.music.stave.StaveBracket;
import io.github.musicdoc.music.stave.StaveVoice;
import io.github.musicdoc.music.tone.Tone;
import io.github.musicdoc.music.tone.TonePitchEnglish;

/**
 * Test of {@link SongFormatMusicDoc}.
 */
public class SongFormatMusicDocTest extends SongFormatTest {

  // Note: the "song" tested here is pure non-sense. It only serves the purpose of a technical test.
  // To avoid copyright violations no real song is used for testing.
  // Background: this crazy song is kind of a joke to a German children winter song.

  private static final String LYRICS_WITH_CHORDS = "#[Chorus 1]\n" + "[A]a [Bm7]b [Cadd9]c the caT is [D] dead.\n";

  private static final String LYRICS_WITH_SCORE = "#[C1]\n" //
      + "<[]K:C,M:4/4,Clef:G,V:Soprano;S>{A2}[A]a [Bm7]{B2}b [Cadd9]{c}c {d}the {c}caT {B}is [D]{A2} dead.{z2}\n" //
      + "+<V:Alto;A>{F2}{G2}{A}{B}{A}{G}{F2}{z2}\n" //
      + "-<[]Clef:F,V:Tenor;T>{C2}{D2}{E}{F}{E}{D}{C2}{z2}\n" //
      + "+<V:Bass;B>{A,2}{B,2}{C}{D}{C}{B,}{A,2}{z2}";

  /** Test of {@link SongFormatMusicDoc#parse(String)}. */
  @Test
  public void testParseLyricsWithChords() {

    // given
    String lyrics = LYRICS_WITH_CHORDS;

    // when
    Score score = SongFormatMusicDoc.INSTANCE.getScoreMapper().parse(lyrics);

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
    assertThat(cell.getLyric()).isEqualTo("b ");
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

  /** Test of {@link SongFormatMusicDoc#format(Song)}. */
  @Test
  public void formatLyricsWithChords() {

    // given
    Score score = new Score();
    ScoreSectionName name = new ScoreSectionName("Chorus 1");
    ScoreSection section = new ScoreSection(name);
    ScoreRow row = new ScoreRow();
    ScoreVoiceLine line = new ScoreVoiceLine();
    line.addCell(new ScoreVoiceCell(new Chord(TonePitchEnglish.A, TonalSystem.MAJOR_EMPTY), "a "));
    line.addCell(new ScoreVoiceCell(ChordMapperMusicDoc.INSTANCE.parse("Bm7"), "b "));
    line.addCell(new ScoreVoiceCell(new Chord(TonePitchEnglish.C, TonalSystem.MAJOR_EMPTY, ChordExtension.ADD_9),
        "c the caT is "));
    line.addCell(new ScoreVoiceCell(new Chord(TonePitchEnglish.D, TonalSystem.MAJOR_EMPTY), " dead."));
    row.addLine(line);
    section.getRows().add(row);
    score.getSections().add(section);

    // when
    String lyrics = SongFormatMusicDoc.INSTANCE.getScoreMapper().format(score);

    // then
    assertThat(lyrics).isEqualTo(LYRICS_WITH_CHORDS);
  }

  /** Test of {@link SongFormatMusicDoc#parse(String)} with full score. */
  @Test
  public void testParseLyricsWithScore() {

    // given
    String lyrics = LYRICS_WITH_SCORE;

    // when
    Score score = SongFormatMusicDoc.INSTANCE.getScoreMapper().parse(lyrics);

    // then
    assertThat(score).isNotNull();
    assertThat(score.getSections()).hasSize(1);
    ScoreSection section = score.getSections().get(0);
    assertThat(section).isNotNull();
    ScoreSectionName name = section.getName();
    assertThat(name.getName()).isEqualTo("C1");
    assertThat(name.getType()).isSameAs(ScoreSectionType.CHORUS);
    assertThat(name.getSuffix()).isEqualTo("1");
    assertThat(section.getRows()).hasSize(1);
    ScoreRow row = section.getRows().get(0);
    assertThat(row).isNotNull();
    assertThat(row.getColumnCount()).isEqualTo(8);
    assertThat(row.getLines()).hasSize(4);
    int lineIndex = 0;
    // first line
    ScoreLine<?, ?> line = row.getLine(lineIndex++);
    assertThat(line).isInstanceOf(ScoreVoiceLine.class);
    ScoreVoiceLine voiceLine = (ScoreVoiceLine) line;
    assertThat(voiceLine.getCells()).hasSize(8);
    assertThat(voiceLine.getContinuation()).isNull();
    Stave stave = new Stave(Clef.TREBLE, MusicalKey.C_MAJOR, Beat._4_4);
    stave.addVoice(StaveVoice.SOPRANO);
    stave.addVoice(StaveVoice.ALTO);
    stave.setBracket(StaveBracket.SQUARE);
    int cellIndex = 0;
    checkCell(voiceLine.getCell(cellIndex++), "a ", new Chord(TonePitchEnglish.A, TonalSystem.MAJOR_EMPTY),
        new Note(Tone.of(TonePitchEnglish.A, 4), MusicalValue._1_2), stave);
    checkCell(voiceLine.getCell(cellIndex++), "b ",
        new Chord(TonePitchEnglish.B, TonalSystem.of("m"), ChordExtension._7),
        new Note(Tone.of(TonePitchEnglish.B, 4), MusicalValue._1_2));
    checkCell(voiceLine.getCell(cellIndex++), "c ",
        new Chord(TonePitchEnglish.C, TonalSystem.MAJOR_EMPTY, ChordExtension.ADD_9),
        new Note(Tone.of(TonePitchEnglish.C, 5), MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), "the ", new Note(Tone.of(TonePitchEnglish.D, 5), MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), "caT ", new Note(Tone.of(TonePitchEnglish.C, 5), MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), "is ", new Note(Tone.of(TonePitchEnglish.B, 4), MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), " dead.", new Chord(TonePitchEnglish.D, TonalSystem.MAJOR_EMPTY),
        new Note(Tone.of(TonePitchEnglish.A, 4), MusicalValue._1_2));
    checkCell(voiceLine.getCell(cellIndex++), Rest._1_2);
    // second line
    line = row.getLine(lineIndex++);
    assertThat(line).isInstanceOf(ScoreVoiceLine.class);
    voiceLine = (ScoreVoiceLine) line;
    assertThat(voiceLine.getCells()).hasSize(8);
    assertThat(voiceLine.getContinuation()).isSameAs(ScoreVoiceLineContinuation.STAVE);
    cellIndex = 0;
    checkCell(voiceLine.getCell(cellIndex++), "", null, new Note(Tone.of(TonePitchEnglish.F, 4), MusicalValue._1_2),
        stave);
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.of(TonePitchEnglish.G, 4), MusicalValue._1_2));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.of(TonePitchEnglish.A, 4), MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.of(TonePitchEnglish.B, 4), MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.of(TonePitchEnglish.A, 4), MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.of(TonePitchEnglish.G, 4), MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.of(TonePitchEnglish.F, 4), MusicalValue._1_2));
    checkCell(voiceLine.getCell(cellIndex++), Rest._1_2);
    // third line
    line = row.getLine(lineIndex++);
    assertThat(line).isInstanceOf(ScoreVoiceLine.class);
    voiceLine = (ScoreVoiceLine) line;
    assertThat(voiceLine.getCells()).hasSize(8);
    assertThat(voiceLine.getContinuation()).isSameAs(ScoreVoiceLineContinuation.LINE);
    stave = new Stave(Clef.BASS, MusicalKey.C_MAJOR, Beat._4_4);
    stave.addVoice(StaveVoice.TENOR);
    stave.addVoice(StaveVoice.BASS);
    stave.setBracket(StaveBracket.SQUARE);
    cellIndex = 0;
    checkCell(voiceLine.getCell(cellIndex++), "", null, new Note(Tone.of(TonePitchEnglish.C, 4), MusicalValue._1_2),
        stave);
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.of(TonePitchEnglish.D, 4), MusicalValue._1_2));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.of(TonePitchEnglish.E, 4), MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.of(TonePitchEnglish.F, 4), MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.of(TonePitchEnglish.E, 4), MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.of(TonePitchEnglish.D, 4), MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.of(TonePitchEnglish.C, 4), MusicalValue._1_2));
    checkCell(voiceLine.getCell(cellIndex++), Rest._1_2);
    // fourth line
    line = row.getLine(lineIndex++);
    assertThat(line).isInstanceOf(ScoreVoiceLine.class);
    voiceLine = (ScoreVoiceLine) line;
    assertThat(voiceLine.getCells()).hasSize(8);
    assertThat(voiceLine.getContinuation()).isSameAs(ScoreVoiceLineContinuation.STAVE);
    cellIndex = 0;
    checkCell(voiceLine.getCell(cellIndex++), "", null, new Note(Tone.of(TonePitchEnglish.A, 3), MusicalValue._1_2),
        stave);
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.of(TonePitchEnglish.B, 3), MusicalValue._1_2));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.of(TonePitchEnglish.C, 4), MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.of(TonePitchEnglish.D, 4), MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.of(TonePitchEnglish.C, 4), MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.of(TonePitchEnglish.B, 3), MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.of(TonePitchEnglish.A, 3), MusicalValue._1_2));
    checkCell(voiceLine.getCell(cellIndex++), Rest._1_2);
  }
}
