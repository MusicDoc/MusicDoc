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

    if (TEMPLATE.referenceNumber.getName().equals(propertyName)) {
      return PROPERTY_REFERENCE_NUMBER;
    } else if (TEMPLATE.key.getName().equals(propertyName)) {
      return PROPERTY_KEY;
    } else if (TEMPLATE.metre.getName().equals(propertyName)) {
      return PROPERTY_METRE;
    } else if (TEMPLATE.tempo.getName().equals(propertyName)) {
      return PROPERTY_TEMPO;
    } else if (TEMPLATE.unitNoteLength.getName().equals(propertyName)) {
      return PROPERTY_UNIT_NOTE_LENGTH;
    } else if (TEMPLATE.title.getName().equals(propertyName)) {
      return PROPERTY_TITLE;
    } else if (TEMPLATE.artist.getName().equals(propertyName)) {
      return PROPERTY_COMPOSER;
    } else if (TEMPLATE.album.getName().equals(propertyName)) {
      return PROPERTY_DISCOGRAPHY;
    } else if (TEMPLATE.origin.getName().equals(propertyName)) {
      return PROPERTY_ORIGIN;
    }
    return null;
  }

}
