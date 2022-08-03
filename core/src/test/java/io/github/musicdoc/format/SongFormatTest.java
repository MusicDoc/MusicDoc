package io.github.musicdoc.format;

import java.io.InputStream;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.musicdoc.bar.BarLine;
import io.github.musicdoc.bar.BarLineType;
import io.github.musicdoc.decoration.SlurDecoration;
import io.github.musicdoc.harmony.chord.Chord;
import io.github.musicdoc.harmony.chord.ChordContainer;
import io.github.musicdoc.harmony.key.MusicalKey;
import io.github.musicdoc.note.Note;
import io.github.musicdoc.rythm.beat.Beat;
import io.github.musicdoc.rythm.tempo.Tempo;
import io.github.musicdoc.rythm.value.ValuedItem;
import io.github.musicdoc.score.Score;
import io.github.musicdoc.score.ScoreRow;
import io.github.musicdoc.score.cell.ScoreCell;
import io.github.musicdoc.score.line.ScoreLine;
import io.github.musicdoc.score.line.ScoreVoiceLine;
import io.github.musicdoc.score.section.ScoreSection;
import io.github.musicdoc.song.Song;
import io.github.musicdoc.stave.StaveChange;
import io.github.musicdoc.stave.voice.StaveVoice;
import io.github.musicdoc.tone.Tone;
import io.github.musicdoc.tone.pitch.TonePitchEnglish;

/**
 * Abstract test case for {@link SongFormat}s.
 */
public abstract class SongFormatTest extends Assertions {

  /** @return the {@link SongFormat} to test. */
  protected abstract SongFormat getFormat();

  /**
   * Test of the score for "Greensleeves" (old enough that it can be used royalty free for testing without copyright
   * infringements).
   */
  @Test
  public void testGreensleeves() {

    // given
    SongFormat format = getFormat();
    InputStream inputStream = Song.class.getResourceAsStream("greensleeves." + format.getExtension());

    // when
    Song song = getFormat().read(inputStream);
    assertThat(song).isNotNull();
    assertThat(song.title.getValue()).isEqualTo("Greensleeves");
    assertThat(song.composer.getValue()).isEqualTo("Traditional");
    assertThat(song.beat.getValue()).isEqualTo(Beat._3_4);
    if (format != SongFormatOpenSong.INSTANCE) {
      assertThat(song.unitNoteLength.getValue()).isEqualTo(Beat.of(1, 4));
      assertThat(song.tempo.getValue()).isEqualTo(new Tempo("", 100, "", Beat.of(1, 4)));
    }
    assertThat(song.key.getValue()).isSameAs(MusicalKey.C_MAJOR);
    Score score = song.score.getValue();
    StaveVoice voice = StaveVoice.EMPTY;
    List<ScoreSection> sections = score.getSections();
    if (format.isSupportSection()) {
      assertThat(sections).hasSize(2);
    }
    // first section (1. verse)
    ScoreSection section = sections.get(0);
    if (format.isSupportSection()) {
      assertThat(section.getName().getName()).isEqualTo("V1");
      assertThat(section.getStaveSystem()).isNull();
    }
    List<ScoreRow> rows = section.getRows();
    assertThat(rows).hasSize(2);
    int rowIndex = 0;
    ScoreRow row = rows.get(rowIndex++);
    List<ScoreLine> lines = row.getLines();
    assertThat(lines).hasSize(1);
    checkLine(lines.get(0), new ScoreVoiceLine() //
        .setVoice(voice) //
        .add(Note.of1_4(Tone.A4), "A-", BarLineType.SINGLE) //
        .add(Chord.ofMinor(TonePitchEnglish.A), Note.of1_2(Tone.C5), "las, ") //
        .add(Note.of1_4(Tone.D5), "my ", BarLineType.SINGLE) //
        .add(Chord.ofMajorWith7(TonePitchEnglish.D), Note.of1_4p(Tone.E5).add(SlurDecoration.SLUR_START), "lo-") //
        .add(Note.of1_8(Tone.F5).add(SlurDecoration.SLUR_END), "ve, ") //
        .add(Note.of1_4(Tone.E5), "you ", BarLineType.SINGLE) //
        .add(Chord.ofMajor(TonePitchEnglish.G), Note.of1_2(Tone.D5), "do ") //
        .add(Note.of1_4(Tone.B4), "me ", BarLineType.SINGLE) //
        .add(Chord.ofMinor(TonePitchEnglish.E), Note.of1_4p(Tone.G4).add(SlurDecoration.SLUR_START), "wro-") //
        .add(Note.of1_8(Tone.A4).add(SlurDecoration.SLUR_END), "ng, ") //
        .add(Note.of1_4(Tone.B4), "to ", BarLineType.SINGLE) //
        .add(Chord.ofMajor(TonePitchEnglish.F), Note.of1_2(Tone.C5), "cast ") //
        .add(Note.of1_4(Tone.A4), "me ", BarLineType.SINGLE) //
        .add(Note.of1_4p(Tone.A4).add(SlurDecoration.SLUR_START), "o-") //
        .add(Note.of1_8(Tone.GS4).add(SlurDecoration.SLUR_END), "ff ") //
        .add(Note.of1_4(Tone.A4), "dis-", BarLineType.SINGLE) //
        .add(Chord.ofMajor(TonePitchEnglish.E), Note.of1_2(Tone.B4), "cour-") //
        .add(Note.of1_4(Tone.GS4), "teous-", BarLineType.SINGLE) //
        .add(Note.of1_2(Tone.E4), "ly. ") //
        .add(Note.of1_4(Tone.A4), "And", BarLineType.SINGLE) //
    );
    row = rows.get(rowIndex++);
    lines = row.getLines();
    assertThat(lines).hasSize(1);
    checkLine(lines.get(0), new ScoreVoiceLine() //
        .setVoice(voice) //
        .add(Chord.ofMinor(TonePitchEnglish.A), Note.of1_2(Tone.C5), "I ") //
        .add(Note.of1_4(Tone.D5), "have ", BarLineType.SINGLE) //
        .add(Chord.ofMajorWith7(TonePitchEnglish.D), Note.of1_4p(Tone.E5).add(SlurDecoration.SLUR_START), "lo-") //
        .add(Note.of1_8(Tone.F5), "ved") //
        .add(Note.of1_4(Tone.E5).add(SlurDecoration.SLUR_END), "_ ", BarLineType.SINGLE) //
        .add(Chord.ofMajor(TonePitchEnglish.G), Note.of1_2(Tone.D5), "you ") //
        .add(Note.of1_4(Tone.B4), "so ", BarLineType.SINGLE) //
        .add(Chord.ofMinor(TonePitchEnglish.E), Note.of1_4p(Tone.G4).add(SlurDecoration.SLUR_START), "lo-") //
        .add(Note.of1_8(Tone.A4).add(SlurDecoration.SLUR_END), "ng, ") //
        .add(Note.of1_4(Tone.B4), "de-", BarLineType.SINGLE) //
        .add(Chord.ofMajor(TonePitchEnglish.F), Note.of1_4p(Tone.C5).add(SlurDecoration.SLUR_START), "ligh-") //
        .add(Note.of1_8(Tone.B4).add(SlurDecoration.SLUR_END), "_ ") //
        .add(Note.of1_4(Tone.A4), "ting ", BarLineType.SINGLE) //
        .add(Chord.ofMajorWith7(TonePitchEnglish.E), Note.of1_4p(Tone.GS4).add(SlurDecoration.SLUR_START), "in") //
        .add(Note.of1_8(Tone.FS4).add(SlurDecoration.SLUR_END), "_ ") //
        .add(Note.of1_4(Tone.GS4), "your ", BarLineType.SINGLE) //
        .add(Chord.ofMinor(TonePitchEnglish.A), Note.of1_2(Tone.A4), "com-") //
        .add(Note.of1_4(Tone.A4), "pa-", BarLineType.SINGLE) //
        .add(Note.of1_2p(Tone.A4), "ny.", BarLineType.DOUBLE) //
    );

    // second section (chorus)
    section = sections.get(1);
    assertThat(section.getName().getName()).isEqualTo("C");
    assertThat(section.getStaveSystem()).isNull();

  }

  protected void checkLine(ScoreLine line, ScoreLine expectedLine) {

    ScoreLine expected = expectedLine;
    if (!getFormat().isSupportItem()) {
      // no items (Tone/Rest) supported, so we have to merge the cells of the expected line together as lyrics only spit
      // by item have to be joined
      expected = new ScoreVoiceLine();
      expected.setVoice(expectedLine.getVoice());
      ScoreCell lastCell = null;
      for (ScoreCell cell : expectedLine.getCells()) {
        cell = cell.setItem(null);
        if (lastCell != null) {
          lastCell = mergeCell(expected, lastCell, cell);
        } else {
          lastCell = cell;
        }
      }
      if (lastCell != null) {
        expected.add(lastCell);
      }
    }
    assertThat(line).isEqualTo(expected);
  }

  private ScoreCell mergeCell(ScoreLine line, ScoreCell cell1, ScoreCell cell2) {

    BarLine bar1 = cell1.getBar();
    if ((cell1.getChordContainer() == null) && (bar1 == null) && (cell1.getStaveChange() == null)) {
      String lyric = cell1.getLyric();
      if (!lyric.isEmpty()) {
        cell2 = cell2.setLyric(lyric + cell2.getLyric());
      }
    } else {
      BarLine bar2 = cell2.getBar();
      if ((bar1 == null) && (cell2.getChordContainer() == null) && (cell2.getStaveChange() == null)) {
        String lyric = cell2.getLyric();
        if (!lyric.isEmpty()) {
          cell1 = cell1.setLyric(cell1.getLyric() + lyric);
        }
        cell1 = cell1.setBar(bar2);
        return cell1;
      } else {
        line.add(cell1);
      }
    }
    return cell2;
  }

  protected void checkCell(ScoreCell cell, ValuedItem<?> item) {

    checkCell(cell, "", null, item);
  }

  protected void checkCell(ScoreCell cell, String lyric, ValuedItem<?> item) {

    checkCell(cell, lyric, null, item);
  }

  protected void checkCell(ScoreCell cell, String lyric, Chord chord) {

    checkCell(cell, lyric, chord, null);
  }

  protected void checkCell(ScoreCell cell, String lyric, Chord chord, ValuedItem<?> item) {

    checkCell(cell, lyric, chord, item, (BarLine) null);
  }

  protected void checkCell(ScoreCell cell, String lyric, Chord chord, ValuedItem<?> item, BarLine bar) {

    checkCell(cell, lyric, chord, item, bar, null);
  }

  protected void checkCell(ScoreCell cell, String lyric, Chord chord, ValuedItem<?> item, BarLineType barType) {

    checkCell(cell, lyric, chord, item, new BarLine(barType), null);
  }

  protected void checkCell(ScoreCell cell, String lyric, Chord chord, ValuedItem<?> item, BarLineType barType,
      StaveChange staveChange) {

    checkCell(cell, lyric, chord, item, new BarLine(barType), staveChange);
  }

  protected void checkCell(ScoreCell cell, String lyric, Chord chord, ValuedItem<?> item, BarLine bar,
      StaveChange staveChange) {

    assertThat(cell).isEqualTo(new ScoreCell(ChordContainer.of(chord), item, lyric, bar));
    assertThat(cell).isNotNull();
    assertThat(cell.getLyric()).isEqualTo(lyric);
    assertThat(cell.getChord()).isEqualTo(chord);
    assertThat(cell.getItem()).isEqualTo(item);
    assertThat(cell.getBar()).isEqualTo(bar);
    assertThat(cell.getStaveChange()).isEqualTo(staveChange);
  }
}
