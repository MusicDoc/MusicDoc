package io.github.musicdoc.song;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatOpenSong;

/**
 * {@link SongMapper} for {@link SongFormatOpenSong}.
 */
public class SongMapperOpenSong extends SongMapper {

  /** The singleton instance. */
  public static final SongMapperOpenSong INSTANCE = new SongMapperOpenSong();

  /**
   * The constructor.
   */
  protected SongMapperOpenSong() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatOpenSong.INSTANCE;
  }

  @Override
  protected String getPropertyKey(String propertyName) {

    // TODO theme, copyright, ccli, copyright, key_line, presentation
    if (TEMPLATE.ReferenceNumber().getName().equals(propertyName)) {
      return "hymn_number";
    } else if (TEMPLATE.Key().getName().equals(propertyName)) {
      return "key";
    } else if (TEMPLATE.Metre().getName().equals(propertyName)) {
      return "timesig";
    } else if (TEMPLATE.Tempo().getName().equals(propertyName)) {
      return "tempo";
    } else if (TEMPLATE.UnitNoteLength().getName().equals(propertyName)) {
      return PROPERTY_UNIT_NOTE_LENGTH;
    } else if (TEMPLATE.Title().getName().equals(propertyName)) {
      return "title";
    } else if (TEMPLATE.Artist().getName().equals(propertyName)) {
      return "author";
    } else if (TEMPLATE.Capo().getName().equals(propertyName)) {
      return "capo";
    } else if (TEMPLATE.Score().getName().equals(propertyName)) {
      return "lyrics";
    }
    return null;
  }
}
