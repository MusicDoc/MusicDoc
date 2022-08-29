package io.github.musicdoc.stave;

import io.github.musicdoc.clef.Clef;
import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.format.SongFormatMusicDoc;
import io.github.musicdoc.harmony.key.MusicalKey;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.rythm.beat.Beat;

/**
 * {@link AbstractStaveMapper} for {@link SongFormatMusicDoc}.
 */
public abstract class AbstractStaveMapperMusicDoc<T extends AbstractStave<T>> extends AbstractStaveMapper<T> {

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
