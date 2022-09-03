package io.github.musicdoc.note;

import java.util.ArrayList;
import java.util.List;

import io.github.musicdoc.decoration.MusicalDecoration;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.rhythm.value.MusicalValue;
import io.github.musicdoc.tone.Tone;
import io.github.musicdoc.tone.ToneMapper;

/**
 * Abstract base implementation of {@link NoteMapper}.
 */
public abstract class NoteMapperBase extends NoteMapper {

  private final char chordStart;

  private final char chordEnd;

  /**
   * The constructor.
   *
   * @param chordStart the character to mark the start of a chord or {@code 0} for none.
   * @param chordEnd the character to mark the end of a chord or {@code 0} for none.
   */
  public NoteMapperBase(char chordStart, char chordEnd) {

    super();
    this.chordStart = chordStart;
    this.chordEnd = chordEnd;
  }

  @Override
  protected Note readItem(MusicInputStream in, SongFormatContext context, List<MusicalDecoration> decorations) {

    boolean chord = true;
    if (this.chordStart != '\0') {
      chord = in.expect(this.chordStart);
    }
    ToneMapper toneMapper = getToneMapper();
    Tone tone = toneMapper.read(in, context);
    if (tone == null) {
      return null;
    }
    List<Tone> tones = null;
    if (chord) {
      Tone t;
      do {
        t = toneMapper.read(in, context);
        if (t != null) {
          if (tones == null) {
            tones = new ArrayList<>();
          }
          tones.add(t);
        }
      } while (t != null);
      if (this.chordEnd != '\0') {
        in.expect(this.chordEnd, true);
      }
    }
    MusicalValue value = getValueMapper().read(in, context);
    if (value == null) {
      value = MusicalValue._1_4;
    } else if (value == MusicalValue._1_1) {
      value = MusicalValue._4_4;
    }
    return new Note(tone, value, decorations, tones);
  }

  @Override
  protected void writeItem(Note note, MusicOutputStream out, SongFormatContext context) {

    int toneCount = note.getToneCount();
    if ((toneCount > 1) && (this.chordStart != '\0')) {
      out.write(this.chordStart);
    }
    for (int i = 0; i < toneCount; i++) {
      getToneMapper().write(note.getTone(i), out, context);
    }
    if ((toneCount > 1) && (this.chordEnd != '\0')) {
      out.write(this.chordEnd);
    }
  }
}
