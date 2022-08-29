package io.github.musicdoc.format;

import org.junit.jupiter.api.Test;

import io.github.musicdoc.clef.Clef;
import io.github.musicdoc.harmony.TonalSystem;
import io.github.musicdoc.harmony.chord.Chord;
import io.github.musicdoc.harmony.chord.ChordExtension;
import io.github.musicdoc.note.Note;
import io.github.musicdoc.rythm.rest.Rest;
import io.github.musicdoc.rythm.value.MusicalValue;
import io.github.musicdoc.score.Score;
import io.github.musicdoc.score.ScoreRow;
import io.github.musicdoc.score.cell.ScoreCell;
import io.github.musicdoc.score.line.ScoreLine;
import io.github.musicdoc.score.line.ScoreVoiceLine;
import io.github.musicdoc.score.section.ScoreSection;
import io.github.musicdoc.score.section.ScoreSectionName;
import io.github.musicdoc.score.section.ScoreSectionType;
import io.github.musicdoc.song.Song;
import io.github.musicdoc.stave.Stave;
import io.github.musicdoc.stave.voice.StaveVoice;
import io.github.musicdoc.tone.Tone;
import io.github.musicdoc.tone.pitch.TonePitchEnglish;

/**
 * Test of {@link SongFormatMusicDoc}.
 */
public class SongFormatMusicDocTest extends SongFormatTest {

  // Note: the "song" tested here is pure non-sense. It only serves the purpose of a technical test.
  // To avoid copyright violations no real song is used for testing.
  // Background: this crazy song is kind of a joke to a German children winter song.

  private static final String LYRICS_WITH_CHORDS = "#[Chorus 1]\n" + "[A]a [Bm7]b [Cadd9]c the caT is [D] dead.\n";

  private static final String LYRICS_WITH_SCORE = "T:Dead cat\n" //
      + "C:Insane Composer\n" //
      + "L:1/4\n" //
      + "#[C1]\n" //
      // + "$:[G(S=Sporano)(A=Alto)F(T=Tenor)(B=Bass)]-{G(P1=}(P2=)}\n" //
      + "$:[G(S=Soprano)(A=Alto)F(T=Tenor)(B=Bass)]\n" //
      + "$S {A2}[A]a [Bm7]{B2}b [Cadd9]{c}c {d}the {c}caT {B}is [D]{A2} dead.{z2}\n" //
      + "$A {F2}{G2}{A}{B}{A}{G}{F2}{z2}\n" //
      + "$T {C2}{D2}{E}{F}{E}{D}{C2}{z2}\n" //
      + "$B {A,2}{B,2}{C}{D}{C}{B,}{A,2}{z2}";

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

  /** Test of {@link SongFormatMusicDoc#read(java.io.InputStream)} with just lyrics and chords. */
  @Test
  public void testReadLyricsWithChords() {

    // given
    String lyrics = LYRICS_WITH_CHORDS;

    // when
    Song song = SongFormatMusicDoc.INSTANCE.getSongMapper().read(lyrics);
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
    assertThat(cell.getLyric()).isEqualTo("b ");
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

  /** Test of {@link SongFormatMusicDoc} writing a simple {@link Score} with only lyrics and chords. */
  @Test
  public void testWriteLyricsWithChords() {

    // given
    Score score = new Score();
    ScoreSectionName name = new ScoreSectionName("Chorus 1");
    ScoreSection section = new ScoreSection(name);
    ScoreRow row = new ScoreRow();
    ScoreVoiceLine line = new ScoreVoiceLine();
    line.add(new ScoreCell(Chord.ofMajor(TonePitchEnglish.A), "a "));
    line.add(new ScoreCell(Chord.ofMinor(TonePitchEnglish.B, ChordExtension._7), "b "));
    line.add(new ScoreCell(Chord.ofMajor(TonePitchEnglish.C, ChordExtension.ADD_9), "c the caT is "));
    line.add(new ScoreCell(Chord.ofMajor(TonePitchEnglish.D), " dead."));
    row.addLine(line);
    section.getRows().add(row);
    score.getSections().add(section);

    // when
    String lyrics = SongFormatMusicDoc.INSTANCE.getScoreMapper().write(score);

    // then
    assertThat(lyrics).isEqualTo(LYRICS_WITH_CHORDS);
  }

  /** Test of {@link SongFormatMusicDoc#read(java.io.InputStream)} of {@link Song} with full score. */
  @Test
  public void testReadLyricsWithScore() {

    // given
    String lyrics = LYRICS_WITH_SCORE;

    // when
    Song song = SongFormatMusicDoc.INSTANCE.getSongMapper().read(lyrics);
    Score score = song.score.getValue();

    // then
    assertThat(song.title.getValue()).isEqualTo("Dead cat");
    assertThat(song.composer.getValue()).isEqualTo("Insane Composer");
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
    ScoreLine line = row.getLine(lineIndex++);
    assertThat(line).isInstanceOf(ScoreVoiceLine.class);
    ScoreVoiceLine voiceLine = (ScoreVoiceLine) line;
    StaveVoice voice = voiceLine.getVoice();
    assertThat(voice.getId()).isEqualTo("S");
    assertThat(voice.getName()).isEqualTo("Soprano");
    assertThat(voice.getAbbreviation()).isEqualTo("S");
    Stave stave = voice.getStave();
    assertThat(stave.getLines()).isEqualTo(5);
    assertThat(stave.getClef()).isSameAs(Clef.G);

    assertThat(voiceLine.getCells()).hasSize(8);
    int cellIndex = 0;
    checkCell(voiceLine.getCell(cellIndex++), "a ", Chord.ofMajor(TonePitchEnglish.A),
        new Note(Tone.A4, MusicalValue._1_2));
    checkCell(voiceLine.getCell(cellIndex++), "b ", Chord.ofMinor(TonePitchEnglish.B, ChordExtension._7),
        new Note(Tone.B4, MusicalValue._1_2));
    checkCell(voiceLine.getCell(cellIndex++), "c ",
        new Chord(TonePitchEnglish.C, TonalSystem.MAJOR_EMPTY, ChordExtension.ADD_9),
        new Note(Tone.C5, MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), "the ", new Note(Tone.D5, MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), "caT ", new Note(Tone.C5, MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), "is ", new Note(Tone.B4, MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), " dead.", Chord.ofMajor(TonePitchEnglish.D),
        new Note(Tone.A4, MusicalValue._1_2));
    checkCell(voiceLine.getCell(cellIndex++), Rest._1_2);
    // second line
    line = row.getLine(lineIndex++);
    assertThat(line).isInstanceOf(ScoreVoiceLine.class);
    voiceLine = (ScoreVoiceLine) line;
    voice = voiceLine.getVoice();
    assertThat(voice.getId()).isEqualTo("A");
    assertThat(voice.getName()).isEqualTo("Alto");
    assertThat(voice.getAbbreviation()).isEqualTo("A");
    assertThat(voice.getStave()).isSameAs(stave);

    assertThat(voiceLine.getCells()).hasSize(8);
    cellIndex = 0;
    checkCell(voiceLine.getCell(cellIndex++), "", null, new Note(Tone.F4, MusicalValue._1_2));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.G4, MusicalValue._1_2));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.A4, MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.B4, MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.A4, MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.G4, MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.F4, MusicalValue._1_2));
    checkCell(voiceLine.getCell(cellIndex++), Rest._1_2);
    // third line
    line = row.getLine(lineIndex++);
    assertThat(line).isInstanceOf(ScoreVoiceLine.class);
    voiceLine = (ScoreVoiceLine) line;
    voice = voiceLine.getVoice();
    assertThat(voice.getId()).isEqualTo("T");
    assertThat(voice.getName()).isEqualTo("Tenor");
    assertThat(voice.getAbbreviation()).isEqualTo("T");
    stave = voice.getStave();
    assertThat(stave.getLines()).isEqualTo(5);
    assertThat(stave.getClef()).isSameAs(Clef.F);
    assertThat(voiceLine.getCells()).hasSize(8);
    cellIndex = 0;
    checkCell(voiceLine.getCell(cellIndex++), "", null, new Note(Tone.C4, MusicalValue._1_2));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.D4, MusicalValue._1_2));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.E4, MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.F4, MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.E4, MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.D4, MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.C4, MusicalValue._1_2));
    checkCell(voiceLine.getCell(cellIndex++), Rest._1_2);
    // fourth line
    line = row.getLine(lineIndex++);
    assertThat(line).isInstanceOf(ScoreVoiceLine.class);
    voiceLine = (ScoreVoiceLine) line;
    voice = voiceLine.getVoice();
    assertThat(voice.getId()).isEqualTo("B");
    assertThat(voice.getName()).isEqualTo("Bass");
    assertThat(voice.getAbbreviation()).isEqualTo("B");
    assertThat(voiceLine.getCells()).hasSize(8);
    assertThat(voice.getStave()).isSameAs(stave);
    cellIndex = 0;
    checkCell(voiceLine.getCell(cellIndex++), "", null, new Note(Tone.A3, MusicalValue._1_2));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.B3, MusicalValue._1_2));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.C4, MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.D4, MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.C4, MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.B3, MusicalValue._1_4));
    checkCell(voiceLine.getCell(cellIndex++), new Note(Tone.A3, MusicalValue._1_2));
    checkCell(voiceLine.getCell(cellIndex++), Rest._1_2);
  }

}
