package io.github.musicdoc.music.harmony.chord;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatOpenSong;
import io.github.musicdoc.music.harmony.TonalSystemMapper;
import io.github.musicdoc.music.harmony.TonalSystemMapperOpenSong;
import io.github.musicdoc.music.tone.TonePitchMapper;
import io.github.musicdoc.music.tone.TonePitchMapperOpenSong;

/**
 * {@link ChordMapper} for {@link SongFormatOpenSong}.
 */
public class ChordMapperOpenSong extends ChordMapper {

  /** The singleton instance. */
  public static final ChordMapperOpenSong INSTANCE = new ChordMapperOpenSong();

  /**
   * The constructor.
   */
  protected ChordMapperOpenSong() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatOpenSong.INSTANCE;
  }

  @Override
  protected TonalSystemMapper getTonalSystemMapper() {

    return TonalSystemMapperOpenSong.INSTANCE;
  }

  @Override
  protected ChordExtensionMapper getChordExtensionMapper() {

    return ChordExtensionMapperOpenSong.INSTANCE;
  }

  @Override
  protected TonePitchMapper getTonePitchMapper() {

    return TonePitchMapperOpenSong.INSTANCE;
  }

}
