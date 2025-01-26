package io.github.musicdoc.format;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.musicdoc.bar.BarLine;
import io.github.musicdoc.bar.BarLineType;
import io.github.musicdoc.decoration.SlurDecoration;
import io.github.musicdoc.harmony.chord.ChordContainer;
import io.github.musicdoc.harmony.chord.ChordSymbol;
import io.github.musicdoc.harmony.key.MusicalKey;
import io.github.musicdoc.note.Note;
import io.github.musicdoc.rhythm.fraction.PlainFraction;
import io.github.musicdoc.rhythm.item.ValuedItem;
import io.github.musicdoc.rhythm.metre.Metre;
import io.github.musicdoc.rhythm.tempo.Tempo;
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
    assertThat(song.Title().get()).isEqualTo("Greensleeves");
    assertThat(song.Artist().get()).isEqualTo("Traditional");
    assertThat(song.Metre().get()).isEqualTo(Metre._3_4);
    if (format != SongFormatOpenSong.INSTANCE) {
      assertThat(song.UnitNoteLength().get()).isEqualTo(PlainFraction._1_4);
      assertThat(song.Tempo().get()).isEqualTo(new Tempo("", 100, "", Metre.of(1, 4)));
    }
    assertThat(song.Key().get()).isEqualTo(MusicalKey.C_MAJOR);
    Score score = song.Score().get();
    StaveVoice voice = StaveVoice.EMPTY;
    List<ScoreSection> sections = score.getSections();
    if (format.isSupportSection()) {
      assertThat(sections).hasSize(2);
    }
    // first section (1. verse)
    ScoreSection section = sections.get(0);
    assertThat(section.getName().getName()).isEqualTo("V1");
    assertThat(section.getStaveSystem()).isNull();
    List<ScoreRow> rows = section.getRows();
    assertThat(rows).hasSize(2);
    int rowIndex = 0;
    ScoreRow row = rows.get(rowIndex++);
    List<ScoreLine> lines = row.getLines();
    assertThat(lines).hasSize(1);
    checkLine(lines.get(0), new ScoreVoiceLine() //
        .setVoice(voice) //
        .add(Note.of1_4(Tone.A4), "A-", BarLineType.THIN) //
        .add(ChordSymbol.ofMinor(TonePitchEnglish.A), Note.of1_2(Tone.C5), "las, ") //
        .add(Note.of1_4(Tone.D5), "my ", BarLineType.THIN) //
        .add(ChordSymbol.ofMajorWith7(TonePitchEnglish.D),
            Note.of1_4p(Tone.E5).addDecoration(SlurDecoration.SLUR_START), "lo-") //
        .add(Note.of1_8(Tone.F5).addDecoration(SlurDecoration.SLUR_END), "ve, ") //
        .add(Note.of1_4(Tone.E5), "you ", BarLineType.THIN) //
        .add(ChordSymbol.ofMajor(TonePitchEnglish.G), Note.of1_2(Tone.D5), "do ") //
        .add(Note.of1_4(Tone.B4), "me ", BarLineType.THIN) //
        .add(ChordSymbol.ofMinor(TonePitchEnglish.E), Note.of1_4p(Tone.G4).addDecoration(SlurDecoration.SLUR_START),
            "wro-") //
        .add(Note.of1_8(Tone.A4).addDecoration(SlurDecoration.SLUR_END), "ng, ") //
        .add(Note.of1_4(Tone.B4), "to ", BarLineType.THIN) //
        .add(ChordSymbol.ofMajor(TonePitchEnglish.F), Note.of1_2(Tone.C5), "cast ") //
        .add(Note.of1_4(Tone.A4), "me ", BarLineType.THIN) //
        .add(Note.of1_4p(Tone.A4).addDecoration(SlurDecoration.SLUR_START), "o-") //
        .add(Note.of1_8(Tone.GS4).addDecoration(SlurDecoration.SLUR_END), "ff ") //
        .add(Note.of1_4(Tone.A4), "dis-", BarLineType.THIN) //
        .add(ChordSymbol.ofMajor(TonePitchEnglish.E), Note.of1_2(Tone.B4), "cour-") //
        .add(Note.of1_4(Tone.GS4), "teous-", BarLineType.THIN) //
        .add(Note.of1_2(Tone.E4), "ly. ") //
        .add(Note.of1_4(Tone.A4), "And", BarLineType.THIN) //
    );
    row = rows.get(rowIndex++);
    lines = row.getLines();
    assertThat(lines).hasSize(1);
    checkLine(lines.get(0), new ScoreVoiceLine() //
        .setVoice(voice) //
        .add(ChordSymbol.ofMinor(TonePitchEnglish.A), Note.of1_2(Tone.C5), "I ") //
        .add(Note.of1_4(Tone.D5), "have ", BarLineType.THIN) //
        .add(ChordSymbol.ofMajorWith7(TonePitchEnglish.D),
            Note.of1_4p(Tone.E5).addDecoration(SlurDecoration.SLUR_START), "lo-") //
        .add(Note.of1_8(Tone.F5), "ved ") //
        .add(Note.of1_4(Tone.E5).addDecoration(SlurDecoration.SLUR_END), "_ ", BarLineType.THIN) //
        .add(ChordSymbol.ofMajor(TonePitchEnglish.G), Note.of1_2(Tone.D5), "you ") //
        .add(Note.of1_4(Tone.B4), "so ", BarLineType.THIN) //
        .add(ChordSymbol.ofMinor(TonePitchEnglish.E), Note.of1_4p(Tone.G4).addDecoration(SlurDecoration.SLUR_START),
            "lo-") //
        .add(Note.of1_8(Tone.A4).addDecoration(SlurDecoration.SLUR_END), "ng, ") //
        .add(Note.of1_4(Tone.B4), "de-", BarLineType.THIN) //
        .add(ChordSymbol.ofMajor(TonePitchEnglish.F), Note.of1_4p(Tone.C5).addDecoration(SlurDecoration.SLUR_START),
            "ligh-") //
        .add(Note.of1_8(Tone.B4).addDecoration(SlurDecoration.SLUR_END), "_ ") //
        .add(Note.of1_4(Tone.A4), "ting ", BarLineType.THIN) //
        .add(ChordSymbol.ofMajorWith7(TonePitchEnglish.E),
            Note.of1_4p(Tone.GS4).addDecoration(SlurDecoration.SLUR_START), "in ") //
        .add(Note.of1_8(Tone.FS4).addDecoration(SlurDecoration.SLUR_END), "_ ") //
        .add(Note.of1_4(Tone.GS4), "your ", BarLineType.THIN) //
        .add(ChordSymbol.ofMinor(TonePitchEnglish.A), Note.of1_2(Tone.A4), "com-") //
        .add(Note.of1_4(Tone.A4), "pa-", BarLineType.THIN) //
        .add(Note.of1_2p(Tone.A4), "ny.", BarLineType.THIN_THIN) //
    );

    // second section (chorus)
    section = sections.get(1);
    assertThat(section.getName().getName()).isEqualTo("C");
    assertThat(section.getStaveSystem()).isNull();
    rows = section.getRows();
    assertThat(rows).hasSize(2);
    rowIndex = 0;
    row = rows.get(rowIndex++);
    lines = row.getLines();
    assertThat(lines).hasSize(1);
    checkLine(lines.get(0), new ScoreVoiceLine() //
        .setVoice(voice) //
        .add(ChordSymbol.ofMajor(TonePitchEnglish.C), Note.of1_2p(Tone.G5), "Green-", BarLineType.THIN) //
        .add(Note.of1_4p(Tone.G5).addDecoration(SlurDecoration.SLUR_START), "slee-") //
        .add(Note.of1_8(Tone.F5).addDecoration(SlurDecoration.SLUR_END), "ves ") //
        .add(Note.of1_4(Tone.E5), "was ", BarLineType.THIN) //
        .add(ChordSymbol.ofMinor(TonePitchEnglish.B), Note.of1_2(Tone.D5), "all ") //
        .add(Note.of1_4(Tone.B4), "my ", BarLineType.THIN) //
        .add(ChordSymbol.ofMinor(TonePitchEnglish.E), Note.of1_4(Tone.G4).addDecoration(SlurDecoration.SLUR_START),
            "joy, ") //
        .add(Note.of1_4(Tone.A4), "_ ") //
        .add(Note.of1_4(Tone.B4).addDecoration(SlurDecoration.SLUR_END), "_ ", BarLineType.THIN) //
        .add(ChordSymbol.ofMinor(TonePitchEnglish.A), Note.of1_2(Tone.C5).addDecoration(SlurDecoration.SLUR_START),
            "Gre-") //
        .add(Note.of1_4(Tone.A4).addDecoration(SlurDecoration.SLUR_END), "en-", BarLineType.THIN) //
        .add(ChordSymbol.ofMajor(TonePitchEnglish.F), Note.of1_4p(Tone.A4).addDecoration(SlurDecoration.SLUR_START),
            "sle-") //
        .add(Note.of1_8(Tone.GS4).addDecoration(SlurDecoration.SLUR_END), "eves ") //
        .add(Note.of1_4(Tone.A4), "was ", BarLineType.THIN) //
        .add(ChordSymbol.ofMajor(TonePitchEnglish.E), Note.of1_2(Tone.B4), "my ") //
        .add(Note.of1_4(Tone.GS4), "de-", BarLineType.THIN) //
        .add(Note.of1_2p(Tone.E4), "light,", BarLineType.THIN) //
    );
    row = rows.get(rowIndex++);
    lines = row.getLines();
    assertThat(lines).hasSize(1);
    checkLine(lines.get(0), new ScoreVoiceLine() //
        .setVoice(voice) //
        .add(ChordSymbol.ofMajor(TonePitchEnglish.C), Note.of1_2p(Tone.G5), "Green-", BarLineType.THIN) //
        .add(Note.of1_4p(Tone.G5).addDecoration(SlurDecoration.SLUR_START), "sleeves ") //
        .add(Note.of1_8(Tone.F5).addDecoration(SlurDecoration.SLUR_END), "was ") //
        .add(Note.of1_4(Tone.E5), "my ", BarLineType.THIN) //
        .add(ChordSymbol.ofMajor(TonePitchEnglish.G), Note.of1_2(Tone.D5), "heart ") //
        .add(Note.of1_4(Tone.B4), "of ", BarLineType.THIN) //
        .add(ChordSymbol.ofMinor(TonePitchEnglish.E), Note.of1_4p(Tone.G4).addDecoration(SlurDecoration.SLUR_START),
            "go-") //
        .add(Note.of1_8(Tone.A4).addDecoration(SlurDecoration.SLUR_END), "ld, ") //
        .add(Note.of1_4(Tone.B4), "and ", BarLineType.THIN) //
        .add(ChordSymbol.ofMajor(TonePitchEnglish.F), Note.of1_4(Tone.C5), "who ") //
        .add(Note.of1_4(Tone.B4), "but ") //
        .add(Note.of1_4(Tone.A4), "my ", BarLineType.THIN) //
        .add(ChordSymbol.ofMajorWith7(TonePitchEnglish.E),
            Note.of1_4(Tone.GS4).addDecoration(SlurDecoration.SLUR_START), "la-") //
        .add(Note.of1_4(Tone.FS4), "dy ") //
        .add(Note.of1_4(Tone.GS4).addDecoration(SlurDecoration.SLUR_END), "_ ", BarLineType.THIN) //
        .add(ChordSymbol.ofMinor(TonePitchEnglish.A), Note.of1_2p(Tone.A4), "Green-", BarLineType.THIN) //
        .add(Note.of1_2(Tone.A4), "sleeves.", BarLineType.THIN_THICK) //
    );

    // and when
    String data = format.write(song);
    inputStream = Song.class.getResourceAsStream("greensleeves." + format.getExtension());
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
      inputStream.transferTo(baos);
      String expectedData = baos.toString(StandardCharsets.UTF_8);
      assertThat(normalize(data)).isEqualTo(normalize(expectedData));
    } catch (IOException e) {
      org.junit.jupiter.api.Assertions.fail(e);
    }
  }

  private String normalize(String data) {

    return data.replace("\r\n", "\n").replaceAll(" *<", "<").replaceAll("><", ">\n<");
  }

  /**
   * @param line the {@link ScoreLine} to check.
   * @param expectedLine the expected {@link ScoreLine} to compare with.
   */
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

  /**
   * @param cell the {@link ScoreCell} to check.
   * @param item the expected {@link ScoreCell#getItem() item}.
   */
  protected void checkCell(ScoreCell cell, ValuedItem<?> item) {

    checkCell(cell, "", null, item);
  }

  /**
   * @param cell the {@link ScoreCell} to check.
   * @param lyric the expected {@link ScoreCell#getLyric() lyric}.
   * @param item the expected {@link ScoreCell#getItem() item}.
   */
  protected void checkCell(ScoreCell cell, String lyric, ValuedItem<?> item) {

    checkCell(cell, lyric, null, item);
  }

  /**
   * @param cell the {@link ScoreCell} to check.
   * @param lyric the expected {@link ScoreCell#getLyric() lyric}.
   * @param chord the expected {@link ScoreCell#getChord() chord}.
   */
  protected void checkCell(ScoreCell cell, String lyric, ChordSymbol chord) {

    checkCell(cell, lyric, chord, null);
  }

  /**
   * @param cell the {@link ScoreCell} to check.
   * @param lyric the expected {@link ScoreCell#getLyric() lyric}.
   * @param chord the expected {@link ScoreCell#getChord() chord}.
   * @param item the expected {@link ScoreCell#getItem() item}.
   */
  protected void checkCell(ScoreCell cell, String lyric, ChordSymbol chord, ValuedItem<?> item) {

    checkCell(cell, lyric, chord, item, (BarLine) null);
  }

  /**
   * @param cell the {@link ScoreCell} to check.
   * @param lyric the expected {@link ScoreCell#getLyric() lyric}.
   * @param chord the expected {@link ScoreCell#getChord() chord}.
   * @param item the expected {@link ScoreCell#getItem() item}.
   * @param bar the expected {@link ScoreCell#getBar() bar}.
   */
  protected void checkCell(ScoreCell cell, String lyric, ChordSymbol chord, ValuedItem<?> item, BarLine bar) {

    checkCell(cell, lyric, chord, item, bar, null);
  }

  /**
   * @param cell the {@link ScoreCell} to check.
   * @param lyric the expected {@link ScoreCell#getLyric() lyric}.
   * @param chord the expected {@link ScoreCell#getChord() chord}.
   * @param item the expected {@link ScoreCell#getItem() item}.
   * @param barType the expected {@link ScoreCell#getBar() bar} {@link BarLine#getType() type}.
   */
  protected void checkCell(ScoreCell cell, String lyric, ChordSymbol chord, ValuedItem<?> item, BarLineType barType) {

    checkCell(cell, lyric, chord, item, new BarLine(barType), null);
  }

  /**
   * @param cell the {@link ScoreCell} to check.
   * @param lyric the expected {@link ScoreCell#getLyric() lyric}.
   * @param chord the expected {@link ScoreCell#getChord() chord}.
   * @param item the expected {@link ScoreCell#getItem() item}.
   * @param barType the expected {@link ScoreCell#getBar() bar} {@link BarLine#getType() type}.
   * @param staveChange the expected {@link ScoreCell#getStaveChange() stave change}.
   */
  protected void checkCell(ScoreCell cell, String lyric, ChordSymbol chord, ValuedItem<?> item, BarLineType barType,
      StaveChange staveChange) {

    checkCell(cell, lyric, chord, item, new BarLine(barType), staveChange);
  }

  /**
   * @param cell the {@link ScoreCell} to check.
   * @param lyric the expected {@link ScoreCell#getLyric() lyric}.
   * @param chord the expected {@link ScoreCell#getChord() chord}.
   * @param item the expected {@link ScoreCell#getItem() item}.
   * @param bar the expected {@link ScoreCell#getBar() bar}.
   * @param staveChange the expected {@link ScoreCell#getStaveChange() stave change}.
   */
  protected void checkCell(ScoreCell cell, String lyric, ChordSymbol chord, ValuedItem<?> item, BarLine bar,
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
