package io.github.musicdoc.view.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A single page of a {@link ViewDocument} or the entire {@link io.github.musicdoc.song.Song} as a single page to
 * display with vertical scrolling. Besides {@link #getHeader() header} and {@link #getFooter() footer} it has any
 * number of {@link #getRows() rows} for the {@link io.github.musicdoc.stave.system.StaveSystem} where one row fills the
 * page from the left to the right and the rows fill the space between {@link #getHeader() header} and
 * {@link #getFooter() footer} from the top to the bottom.
 */
public class ViewPage {

  private final int number;

  private ViewBlock header;

  private final List<ViewRow> rows;

  private ViewBlock footer;

  /**
   * The constructor.
   *
   * @param number the {@link #getNumber() page number}.
   */
  public ViewPage(int number) {

    super();
    this.number = number;
    this.rows = new ArrayList<>();
  }

  /**
   * @return the page number starting from {@code 1} for the first page.
   */
  public int getNumber() {

    return this.number;
  }

  /**
   * @return the {@link ViewBlock} with the header items.
   */
  public ViewBlock getHeader() {

    return this.header;
  }

  /**
   * @param header new value of {@link #getHeader()}.
   */
  public void setHeader(ViewBlock header) {

    this.header = header;
  }

  /**
   * @return rows
   */
  public List<ViewRow> getRows() {

    return this.rows;
  }

  /**
   * @return footer
   */
  public ViewBlock getFooter() {

    return this.footer;
  }

  /**
   * @param footer new value of {@link #getFooter()}.
   */
  public void setFooter(ViewBlock footer) {

    this.footer = footer;
  }

}
