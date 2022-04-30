package io.github.musicdoc.music.note;

import java.io.IOException;
import java.util.List;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatOptions;
import io.github.musicdoc.music.rythm.AbstractValuedItemMapper;
import io.github.musicdoc.music.rythm.MusicalValue;
import io.github.musicdoc.music.rythm.MusicalValueMapper;
import io.github.musicdoc.music.rythm.ValuedItemDecoration;
import io.github.musicdoc.music.tone.Tone;
import io.github.musicdoc.music.tone.ToneMapper;
import io.github.musicdoc.parser.CharStream;

/**
 * {@link AbstractMapper Mapper} for {@link Note}.
 */
public class NoteMapper extends AbstractValuedItemMapper<Note> {

    public static final NoteMapper INSTANCE = new NoteMapper();

    @Override
    protected Note parseContent(CharStream chars, List<ValuedItemDecoration> decorations) {
        Tone tone = ToneMapper.INSTANCE.parse(chars);
        if (tone == null) {
            return null;
        }
        MusicalValue value = MusicalValueMapper.INSTANCE.parse(chars);
        if ((value == null) || (value == MusicalValue._1_1)) {
            value = MusicalValue._4_4;
        }
        return new Note(tone, value, decorations);
    }

    @Override
    protected void formatContent(Note note, Appendable buffer, SongFormatOptions options) throws IOException {
        ToneMapper.INSTANCE.format(note.getTone(), buffer, options);
    }
}
