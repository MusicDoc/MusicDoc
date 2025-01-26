package io.github.musicdoc.song;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;

/**
 * {@link SongMapper} for {@link SongFormatAbc}.
 */
public class SongMapperAbc extends SongMapper {

  /** The singleton instance. */
  public static final SongMapperAbc INSTANCE = new SongMapperAbc();

  /**
   * The constructor.
   */
  protected SongMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

  @Override
  protected String getPropertyKey(String propertyName) {

    if (TEMPLATE.ReferenceNumber().getName().equals(propertyName)) {
      return PROPERTY_REFERENCE_NUMBER;
    } else if (TEMPLATE.Key().getName().equals(propertyName)) {
      return PROPERTY_KEY;
    } else if (TEMPLATE.Metre().getName().equals(propertyName)) {
      return PROPERTY_METRE;
    } else if (TEMPLATE.Tempo().getName().equals(propertyName)) {
      return PROPERTY_TEMPO;
    } else if (TEMPLATE.UnitNoteLength().getName().equals(propertyName)) {
      return PROPERTY_UNIT_NOTE_LENGTH;
    } else if (TEMPLATE.Title().getName().equals(propertyName)) {
      return PROPERTY_TITLE;
    } else if (TEMPLATE.Artist().getName().equals(propertyName)) {
      return PROPERTY_COMPOSER;
    } else if (TEMPLATE.Album().getName().equals(propertyName)) {
      return PROPERTY_DISCOGRAPHY;
    } else if (TEMPLATE.Origin().getName().equals(propertyName)) {
      return PROPERTY_ORIGIN;
    }
    return null;
  }

}
