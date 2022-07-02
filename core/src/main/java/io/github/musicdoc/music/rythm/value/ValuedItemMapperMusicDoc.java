package io.github.musicdoc.music.rythm.value;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;
import io.github.musicdoc.music.format.SongFormatOptions;
import io.github.musicdoc.music.note.NoteMapper;
import io.github.musicdoc.music.note.NoteMapperMusicDoc;
import io.github.musicdoc.music.rythm.rest.RestMapper;
import io.github.musicdoc.music.rythm.rest.RestMapperMusicDoc;

/**
 * {@link ValuedItemMapper} for {@link SongFormatMusicDoc}.
 */
public class ValuedItemMapperMusicDoc extends ValuedItemMapper {

  /** The singleton instance. */
  public static final ValuedItemMapperMusicDoc INSTANCE = new ValuedItemMapperMusicDoc();

  /**
   * The constructor.
   */
  protected ValuedItemMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

  @Override
  protected NoteMapper getNoteMapper() {

    return NoteMapperMusicDoc.INSTANCE;
  }

  @Override
  protected RestMapper getRestMapper() {

    return RestMapperMusicDoc.INSTANCE;
  }

  @Override
  public ValuedItem<?> parse(MusicInputStream chars, SongFormatOptions options) {

    ValuedItem<?> item = null;
    if (chars.expect(ITEM_START)) {
      item = super.parse(chars, options);
      chars.expect(ITEM_END, true);
    }
    return item;
  }

  @Override
  public void format(ValuedItem<?> item, MusicOutputStream out, SongFormatOptions options) {

    if (item == null) {
      return;
    }
    out.append(ITEM_START);
    super.format(item, out, options);
    out.append(ITEM_END);
  }
}
