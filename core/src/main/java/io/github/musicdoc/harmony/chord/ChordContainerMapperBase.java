package io.github.musicdoc.harmony.chord;

import io.github.mmm.base.filter.ListCharFilter;
import io.github.mmm.scanner.CharStreamScanner;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.tone.pitch.TonePitchMapperBase;

/**
 * Basic implementation of {@link ChordContainerMapper}.
 */
public abstract class ChordContainerMapperBase extends ChordContainerMapper {

  private final char chordStart;

  private final char chordEnd;

  private final ListCharFilter stopFilter;

  /**
   * The constructor.
   *
   * @param chordStart the character indicating the start of a chord.
   * @param chordEnd the character indicating the end of a chord.
   */
  public ChordContainerMapperBase(char chordStart, char chordEnd) {

    super();
    this.chordStart = chordStart;
    this.chordEnd = chordEnd;
    ListCharFilter filter = TonePitchMapperBase.FILTER_TONE_START;
    if (chordStart == chordEnd) {
      this.stopFilter = filter.join(NEWLINE_CHAR, chordStart);
    } else {
      this.stopFilter = filter.join(NEWLINE_CHAR, chordStart, chordEnd);
    }
  }

  @Override
  public ChordContainer read(MusicInputStream in, SongFormatContext context) {

    CharStreamScanner scanner = in.getScanner();
    ChordContainer result = null;
    if ((this.chordStart == 0) || scanner.expectOne(this.chordStart)) {
      result = readChord(in, context);
      if (result == null) {
        if ((this.chordStart != 0) && (this.chordEnd != 0)) {
          result = ChordContainer.EMPTY;
          in.expect(this.chordEnd, true);
        }
      } else {
        if (this.chordEnd != 0) {
          ChordContainer current = result;
          while (!scanner.expectOne(this.chordEnd)) {
            ChordContainer next = readChord(in, context);
            if (next == null) {
              in.addError("Chord not terminated.");
              break;
            } else {
              current.setNext(next);
              current = next;
            }
          }
        }
      }
    }
    return result;
  }

  private ChordContainer readChord(MusicInputStream in, SongFormatContext context) {

    ChordSymbol chord = getChordSymbolMapper().read(in, context);
    String suffix = "";
    if (this.chordEnd != 0) {
      suffix = in.getScanner().readUntil(this.stopFilter, true);
    }
    if (suffix.isEmpty() && (chord == null)) {
      return null;
    }
    return new ChordContainer(chord, suffix);
  }

  @Override
  public void write(ChordContainer chordContainer, MusicOutputStream out, SongFormatContext context) {

    if (chordContainer == null) {
      return;
    }
    if (this.chordStart != 0) {
      out.write(this.chordStart);
    }
    out.write(chordContainer.toString());
    if (this.chordEnd != 0) {
      out.write(this.chordEnd);
    }
  }
}
