package io.github.musicdoc.song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Model with the {@link Song}s.
 *
 * @deprecated replaced by {@link SongRepository} and {@link Songs}.
 */
@Deprecated
public class SongModel {

  /** {@link Comparator} to sort {@link Song}s by {@link Song#Title title}. */
  public static final Comparator<Song> SORT_BY_TITLE = (s1, s2) -> s1.Title().get().compareTo(s2.Title().get());

  /** {@link Comparator} to sort {@link Song}s by {@link Song#Artist artist}. */
  public static final Comparator<Song> SORT_BY_ARTIST = (s1, s2) -> {
    int result = s1.Artist().get().getTarget().Title().get().compareTo(s2.Artist().get().getTarget().Title().get());
    if (result == 0) {
      result = s1.Title().get().compareTo(s2.Title().get());
    }
    return result;
  };

  private final List<Song> songs;

  private final Set<String> tags;

  private Song selectedSong;

  /**
   * The constructor.
   */
  public SongModel() {

    this.songs = new ArrayList<>();
    this.tags = new HashSet<>();
  }

  /**
   * @return the currently selected {@link Song} may be {@code null} if none is selected.
   */
  public Song getSelectedSong() {

    return this.selectedSong;
  }

  /**
   * @param selectedSong new value of {@link #getSelectedSong()}.
   */
  public void setSelectedSong(Song selectedSong) {

    this.selectedSong = selectedSong;
  }

  /**
   * @param song the new {@link Song} to add to this model.
   */
  public void add(Song song) {

    this.songs.add(song);
    this.tags.addAll(song.Tags().getAsSet());
  }

  /**
   * @param song the {@link Song} to remove from this model.
   * @return {@code true} if the given {@link Song} has been removed successfully, {@code false} otherwise (it is not
   *         part of this model and was already removed or has never been {@link #add(Song) added}).
   */
  public boolean removeSong(Song song) {

    return this.songs.remove(song);
  }

  /**
   * @return the {@link Set} with all tags.
   */
  public Set<String> getTags() {

    return this.tags;
  }

  /**
   * Renames a tag from {@code oldTag} to {@code newTag} in all {@link Song}s.
   *
   * @param oldTag the old tag.
   * @param newTag the new tag.
   */
  public void renameTag(String oldTag, String newTag) {

    if (!this.tags.contains(oldTag)) {
      return;
    }
    if (this.tags.contains(newTag)) {
      throw new IllegalArgumentException(
          "Cannot rename tag from '" + oldTag + "' to '" + newTag + "' because the new tag is already in use!");
    }
    for (Song song : this.songs) {
      if (song.Tags().getAsSet().contains(oldTag)) {
        song.Tags().add(newTag);
        song.Tags().remove(oldTag);
      }
    }
    this.tags.add(newTag);
    this.tags.remove(oldTag);
  }

  /**
   * @param filter the {@link SongFilter}.
   * @param comparator the sort {@link Comparator}.
   * @return the resulting {@link List} of {@link Song}s.
   */
  public List<Song> getSongs(SongFilter filter, Comparator<Song> comparator) {

    List<Song> result = new ArrayList<>(this.songs.size());
    for (Song song : this.songs) {
      if ((filter == null) || filter.accept(song)) {
        result.add(song);
      }
    }
    Collections.sort(result, comparator);
    return result;
  }

}
