package io.github.musicdoc.rhythm.item;

import io.github.musicdoc.decoration.MusicalDecoration;
import io.github.musicdoc.decoration.SlurDecoration;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.note.Note;
import io.github.musicdoc.rhythm.rest.Rest;
import io.github.musicdoc.rhythm.tuplet.TupletContext;
import io.github.musicdoc.rhythm.tuplet.TupletMapperBase;

/**
 * Abstract base implementation of {@link ValuedItemMapper}.
 */
public abstract class ValuedItemMapperBase extends ValuedItemMapper {

  @Override
  public ValuedItem<?> read(MusicInputStream in, SongFormatContext context) {

    MusicalDecoration decoration = readItemPrefix(in, context);
    ValuedItem<?> item = getNoteMapper().read(in, context);
    if (item == null) {
      item = getRestMapper().read(in, context);
    }
    if (item == null) {
      assert (decoration == null);
    } else {
      if (decoration != null) {
        item.getDecorations().add(decoration);
      }
      item = readItemSuffix(item, in, context);
    }
    return item;
  }

  // TODO support multiple slur start/end

  /**
   * @param in the {@link MusicInputStream} to read from.
   * @param context the {@link SongFormatContext}.
   * @return the {@link MusicalDecoration} to add or {@code null} for none.
   */
  protected MusicalDecoration readItemPrefix(MusicInputStream in, SongFormatContext context) {

    if (in.expect('(')) {
      // "(abc)" annotates a slur for the notes a b c.
      // in "(3a(bc)" the first ( is not a slur but annotates a triplet for the following 3 notes.
      // such tuplets are not closed via ")" in ABC what can cause slight confusions for the reader.
      TupletContext tc = TupletMapperBase.readTupletContext(in, context, true);
      if (tc == null) {
        return SlurDecoration.SLUR_START;
      }
      context.setTupletContext(tc);
    }
    return null;
  }

  /**
   * @param item the {@link ValuedItem} that has been parsed so far and may be decorated.
   * @param in the {@link MusicInputStream} to read from.
   * @param context the {@link SongFormatContext}.
   * @return the modified {@link ValuedItem}.
   */
  protected ValuedItem<?> readItemSuffix(ValuedItem<?> item, MusicInputStream in, SongFormatContext context) {

    if (in.expect(')')) {
      item.getDecorations().add(SlurDecoration.SLUR_END);
    }
    return item;
  }

  @Override
  public void write(ValuedItem<?> item, MusicOutputStream out, SongFormatContext context) {

    if (item instanceof Note) {
      getNoteMapper().write((Note) item, out, context);
    } else if (item instanceof Rest) {
      getRestMapper().write((Rest) item, out, context);
    }
  }
}
