package io.github.musicdoc.music.note;

import java.io.IOException;
import java.util.List;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.MusicFormatOptions;
import io.github.musicdoc.music.decoration.MusicalDecoration;
import io.github.musicdoc.music.rythm.value.AbstractValuedItemMapper;
import io.github.musicdoc.music.rythm.value.MusicalValue;
import io.github.musicdoc.music.rythm.value.MusicalValueMapper;
import io.github.musicdoc.music.tone.Tone;
import io.github.musicdoc.music.tone.ToneMapper;
import io.github.musicdoc.parser.CharStream;

/**
 * {@link AbstractMapper Mapper} for {@link Note}.
 */
public class NoteMapper extends AbstractValuedItemMapper<Note> {

  public static final NoteMapper INSTANCE = new NoteMapper();

  @Override
  protected Note parseItem(CharStream chars, List<MusicalDecoration> decorations) {

    Tone tone = ToneMapper.INSTANCE.parse(chars);
    if (tone == null) {
      return null;
    }
    MusicalValue value = MusicalValueMapper.INSTANCE.parse(chars);
    if (value == null) {
      value = MusicalValue._1_4;
    } else if (value == MusicalValue._1_1) {
      value = MusicalValue._4_4;
    }
    return new Note(tone, value, decorations);
  }

  @Override
  protected void formatItem(Note note, Appendable buffer, MusicFormatOptions options) throws IOException {

    ToneMapper.INSTANCE.format(note.getTone(), buffer, options);
  }
}
