package io.github.musicdoc.music.note;

import java.util.List;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.decoration.MusicalDecoration;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatOptions;
import io.github.musicdoc.music.rythm.value.AbstractValuedItemMapper;
import io.github.musicdoc.music.rythm.value.MusicalValue;
import io.github.musicdoc.music.tone.Tone;
import io.github.musicdoc.music.tone.ToneMapper;

/**
 * {@link AbstractMapper Mapper} for {@link Note}.
 */
public abstract class NoteMapper extends AbstractValuedItemMapper<Note> {

  @Override
  protected Note parseItem(MusicInputStream chars, SongFormatOptions options, List<MusicalDecoration> decorations) {

    Tone tone = getToneMapper().parse(chars, options);
    if (tone == null) {
      return null;
    }
    // TODO consider unit note length
    MusicalValue value = getValueMapper().parse(chars, options);
    if (value == null) {
      value = MusicalValue._1_4;
    } else if (value == MusicalValue._1_1) {
      value = MusicalValue._4_4;
    }
    return new Note(tone, value, decorations);
  }

  /**
   * @return the {@link ToneMapper}.
   */
  protected abstract ToneMapper getToneMapper();

  @Override
  protected void formatItem(Note note, MusicOutputStream out, SongFormatOptions options) {

    getToneMapper().format(note.getTone(), out, options);
  }
}
