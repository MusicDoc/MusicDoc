package io.github.musicdoc.music.partiture;

import io.github.musicdoc.music.partiture.section.PartitureSectionNameMapper;

/**
 * {@link PartitureMapper} for {@link io.github.musicdoc.format.SongFormatMusicDoc MusicDoc format}.
 */
public class PartitureMapperMusicDoc extends PartitureMapper {

  /** The singleton instance. */
  public static final PartitureMapperMusicDoc INSTANCE = new PartitureMapperMusicDoc();

  @Override
  protected PartitureLineMapper getLineMapper() {

    return PartitureLineMapper.MUSIC_DOC;
  }

  @Override
  protected PartitureSectionNameMapper getSectionNameMapper() {

    return PartitureSectionNameMapper.MUSIC_DOC;
  }
}
