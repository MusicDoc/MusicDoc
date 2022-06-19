package io.github.musicdoc.music.partiture;

import io.github.musicdoc.music.partiture.voice.PartitureVoiceLineMapper;
import io.github.musicdoc.music.partiture.voice.PartitureVoiceLineMapperOpenSong;

/**
 * {@link PartitureLineMapper} for {@link io.github.musicdoc.format.SongFormatOpenSong OpenSong format}.
 */
public class PartitureLineMapperOpenSong extends PartitureLineMapper {

  /** The singleton instance. */
  public static final PartitureLineMapperOpenSong INSTANCE = new PartitureLineMapperOpenSong();

  @Override
  protected PartitureVoiceLineMapper getVoiceLineMapper() {

    return PartitureVoiceLineMapperOpenSong.INSTANCE;
  }
}
