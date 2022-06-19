package io.github.musicdoc.music.partiture;

import io.github.musicdoc.music.partiture.section.PartitureSectionNameMapper;

/**
 * {@link PartitureMapper} for {@link io.github.musicdoc.format.SongFormatMusicDoc ChordPro format}.
 */
public class PartitureMapperOpenSong extends PartitureMapper {

  /** The singleton instance. */
  public static final PartitureMapperOpenSong INSTANCE = new PartitureMapperOpenSong();

  @Override
  protected PartitureLineMapper getLineMapper() {

    return PartitureLineMapper.OPEN_SONG;
  }

  @Override
  protected PartitureSectionNameMapper getSectionNameMapper() {

    return PartitureSectionNameMapper.OPEN_SONG;
  }
}
