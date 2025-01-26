package io.github.musicdoc.song;

/**
 * Default implementation of {@link SongFilter}.
 */
public class SongFilterDefault implements SongFilter {

  private final String[] tokens;

  /**
   * The constructor.
   *
   * @param search the search input.
   */
  public SongFilterDefault(String search) {

    super();
    this.tokens = SongFilter.normalize(search).split(" ");
  }

  @Override
  public boolean accept(Song song) {

    String searchText = SongFilter.normalize(song.Title().get() + " " + song.Artist().get());
    for (int i = 0; i < this.tokens.length; i++) {
      String token = this.tokens[i];
      if (!searchText.contains(token)) {
        return false;
      }
    }
    return true;
  }

}
