package io.github.musicdoc.view.render;

import io.github.musicdoc.song.Song;
import io.github.musicdoc.view.ViewContext;
import io.github.musicdoc.view.model.ViewCell;
import io.github.musicdoc.view.model.ViewDocument;
import io.github.musicdoc.view.model.ViewPage;
import io.github.musicdoc.view.model.ViewRow;

/**
 * Interface to render a {@link Song} to its according view. This is an abstraction of specific UI or rendering
 * techniques like PDF, Android, JavaFx, HTML, SVG, or others. The approach allows to re-use the generic rendering and
 * layout engine for different of the listed technologies.
 */
public interface ViewRenderer {

  /**
   * Renders a {@link Song} as {@link ViewDocument} for printing (e.g. as PDF).
   *
   * @param song the {@link Song} to render.
   * @param context the {@link ViewContext}.
   * @return the {@link ViewDocument} with {@link ViewPage}s layout.
   */
  ViewDocument renderDocument(Song song, ViewContext context);

  /**
   * Renders a {@link Song} as single {@link ViewPage} for online views with vertical scrolling.
   *
   * @param song the {@link Song} to render.
   * @param context the {@link ViewContext}.
   * @return the {@link ViewPage}.
   */
  ViewPage renderPage(Song song, ViewContext context);

  /**
   * Renders a {@link Song} as single {@link ViewRow} for online views with horizontal live scrolling. The entire score
   * will be rendered as one {@link io.github.musicdoc.stave.system.StaveSystem} without any line-breaks. Unlike other
   * render methods here {@link io.github.musicdoc.score.section.ScoreSectionName section names} will be rendered as
   * part of a {@link ViewCell} above the {@link io.github.musicdoc.stave.Stave}(s).
   *
   * @param song the {@link Song} to render.
   * @param context the {@link ViewContext}.
   * @return the {@link ViewPage}.
   */
  ViewRow renderRow(Song song, ViewContext context);

}
