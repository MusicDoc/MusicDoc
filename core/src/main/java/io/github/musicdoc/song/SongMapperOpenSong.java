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
    if (TEMPLATE.referenceNumber.getName().equals(propertyName)) {
      return "hymn_number";
    } else if (TEMPLATE.key.getName().equals(propertyName)) {
      return "key";
    } else if (TEMPLATE.metre.getName().equals(propertyName)) {
      return "timesig";
    } else if (TEMPLATE.tempo.getName().equals(propertyName)) {
      return "tempo";
    } else if (TEMPLATE.unitNoteLength.getName().equals(propertyName)) {
      return PROPERTY_UNIT_NOTE_LENGTH;
    } else if (TEMPLATE.title.getName().equals(propertyName)) {
      return "title";
    } else if (TEMPLATE.artist.getName().equals(propertyName)) {
      return "author";
    } else if (TEMPLATE.capo.getName().equals(propertyName)) {
      return "capo";
    } else if (TEMPLATE.score.getName().equals(propertyName)) {
      return "lyrics";
    }
    return null;
  }
}
