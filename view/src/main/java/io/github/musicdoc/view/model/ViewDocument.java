package io.github.musicdoc.view.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The view for a {@link io.github.musicdoc.song.Song} with its {@link io.github.musicdoc.score.Score} as a document
 * (e.g. PDF) with {@link #getPages() pages}.
 */
public class ViewDocument {

  private final List<ViewPage> pages;

  /**
   * The constructor.
   */
  public ViewDocument() {

    super();
    this.pages = new ArrayList<>();
  }

  /**
   * @return pages
   */
  public List<ViewPage> getPages() {

    return this.pages;
  }

}
