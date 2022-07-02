package io.github.musicdoc.music.stave;

import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.clef.ClefMapperMusicDoc;
import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;
import io.github.musicdoc.music.format.SongFormatOptions;
import io.github.musicdoc.music.harmony.MusicalKeyMapperMusicDoc;
import io.github.musicdoc.music.rythm.beat.BeatMapperMusicDoc;

/**
 * {@link StaveMapper} for {@link io.github.musicdoc.music.format.SongFormatMusicDoc}.
 */
public class StaveMapperMusicDoc extends StaveMapper {

  /** The singleton instance. */
  public static final StaveMapperMusicDoc INSTANCE = new StaveMapperMusicDoc();

  private static final ListCharFilter STOP_FILTER = ListCharFilter.NEWLINE.join(STAVE_END);

  /**
   * The constructor.
   */
  protected StaveMapperMusicDoc() {

    super(new StaveProperties(ClefMapperMusicDoc.INSTANCE, MusicalKeyMapperMusicDoc.INSTANCE,
        BeatMapperMusicDoc.INSTANCE, StaveVoiceMapperMusicDoc.INSTANCE));
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

  @Override
  protected StaveBracketMapper getBracketMapper() {

    return StaveBracketMapperMusicDoc.INSTANCE;
  }

  @Override
  public Stave parse(MusicInputStream chars, SongFormatOptions options) {

    Stave stave = null;
    if (chars.expect(STAVE_START)) {
      stave = super.parse(chars, options);
      if (!chars.expect(STAVE_END, true)) {
        String garbage = chars.readUntil(STOP_FILTER, true);
        chars.addError("Unexpected garbage at stave bracket '" + garbage + "'.");
        chars.expect(STAVE_END, true);
      }
    }
    return stave;
  }

  @Override
  public void format(Stave stave, MusicOutputStream out, SongFormatOptions options) {

    if (stave == null) {
      return;
    }
    out.append(STAVE_START);
    super.format(stave, out, options);
    out.append(STAVE_END);
  }
}
