package io.github.musicdoc.music.partiture;

import io.github.musicdoc.music.partiture.voice.PartitureVoiceLineMapper;
import io.github.musicdoc.music.partiture.voice.PartitureVoiceLineMapperMusicDoc;

/**
 * {@link PartitureLineMapper} for {@link io.github.musicdoc.format.SongFormatMusicDoc MusicDoc format}.
 */
public class PartitureLineMapperMusicDoc extends PartitureLineMapper {

  /** The singleton instance. */
  public static final PartitureLineMapperMusicDoc INSTANCE = new PartitureLineMapperMusicDoc();

  @Override
  protected PartitureVoiceLineMapper getVoiceLineMapper() {

    return PartitureVoiceLineMapperMusicDoc.INSTANCE;
  }
}
