package io.github.musicdoc.music.rythm.value;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatContext;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

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
  public ValuedItem<?> read(MusicInputStream in, SongFormatContext context) {

    ValuedItem<?> item = null;
    if (in.expect(ITEM_START)) {
      item = super.read(in, context);
      in.expect(ITEM_END, true);
    }
    return item;
  }

  @Override
  public void write(ValuedItem<?> item, MusicOutputStream out, SongFormatContext context) {

    if (item == null) {
      return;
    }
    out.write(ITEM_START);
    super.write(item, out, context);
    out.write(ITEM_END);
  }
}
