package io.github.musicdoc.music.stave;

import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.clef.Clef;
import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatContext;
import io.github.musicdoc.music.format.SongFormatMusicDoc;
import io.github.musicdoc.music.harmony.key.MusicalKey;
import io.github.musicdoc.music.rythm.beat.Beat;

/**
 * {@link AbstractStaveMapper} for {@link SongFormatMusicDoc}.
 */
public abstract class AbstractStaveMapperMusicDoc<T extends AbstractStave<T>> extends AbstractStaveMapper<T> {

  private static final ListCharFilter STOP_FILTER = ListCharFilter.NEWLINE.join(STAVE_END);

  /**
   * The constructor.
   */
  protected AbstractStaveMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

  @Override
  public T read(MusicInputStream in, SongFormatContext context) {

    Clef clef = getClefMapper().read(in, context);
    // TODO separator for key on parse and format?
    MusicalKey key = getKeyMapper().read(in, context);
    // TODO separator for beat on parse and format?
    Beat beat = getBeatMapper().read(in, context);
    if ((clef == null) && (key == null) && (beat == null)) {
      return null;
    }
    if (clef == null) {
      clef = context.getClef();
    }
    if (key == null) {
      key = context.getKey();
    }
    if (beat == null) {
      beat = context.getBeat();
    }
    return createStave(clef, key, beat);
  }

  @Override
  public void write(T stave, MusicOutputStream out, SongFormatContext context) {

    if (stave == null) {
      return;
    }
    Clef clef = stave.getClef();
    if (clef != context.getClef()) {
      getClefMapper().write(clef, out, context);
    }
    MusicalKey key = stave.getKey();
    if (key != context.getKey()) {
      getKeyMapper().write(key, out, context);
    }
    Beat beat = stave.getBeat();
    if (beat != context.getBeat()) {
      getBeatMapper().write(beat, out, context);
    }
    context.setStave(stave);
  }
}
