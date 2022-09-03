package io.github.musicdoc.song;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * {@link SongMapper} for {@link SongFormatMusicDoc}.
 */
public class SongMapperMusicDoc extends SongMapper {

  /** The singleton instance. */
  public static final SongMapperMusicDoc INSTANCE = new SongMapperMusicDoc();

  /**
   * The constructor.
   */
  protected SongMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
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
    } else if (TEMPLATE.composer.getName().equals(propertyName)) {
      return PROPERTY_COMPOSER;
    } else if (TEMPLATE.album.getName().equals(propertyName)) {
      return PROPERTY_DISCOGRAPHY;
    } else if (TEMPLATE.origin.getName().equals(propertyName)) {
      return PROPERTY_ORIGIN;
    } else if (TEMPLATE.capo.getName().equals(propertyName)) {
      return PROPERTY_CAPO;
    }
    return null;
  }
}
