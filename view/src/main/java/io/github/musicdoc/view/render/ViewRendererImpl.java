package io.github.musicdoc.view.render;

import io.github.musicdoc.song.Song;
import io.github.musicdoc.view.ViewContext;
import io.github.musicdoc.view.model.ViewDocument;
import io.github.musicdoc.view.model.ViewPage;
import io.github.musicdoc.view.model.ViewRow;

/**
 * Implementation of {@link ViewRenderer} based on {@link ViewRenderAdapter} as call-back-interface to abstract from
 * concrete rendering technology.
 */
public class ViewRendererImpl implements ViewRenderer {

  /** The {@link ViewRenderAdapter}. */
  protected final ViewRenderAdapter adapter;

  /**
   * The constructor.
   *
   * @param adapter the {@link ViewRenderAdapter}.
   */
  public ViewRendererImpl(ViewRenderAdapter adapter) {

    super();
    this.adapter = adapter;
  }

  @Override
  public ViewPage renderPage(Song song, ViewContext context) {

    ViewRendererStateful renderer = new ViewRendererStateful(this.adapter, context, false);
    return renderer.renderPages(song, null, null);
  }

  @Override
  public ViewDocument renderDocument(Song song, ViewContext context) {

    ViewDocument document = new ViewDocument();
    ViewRendererStateful renderer = new ViewRendererStateful(this.adapter, context, false);
    renderer.renderPages(song, document, null);
    return document;
  }

  @Override
  public ViewRow renderRow(Song song, ViewContext context) {

    ViewRow row = new ViewRow();
    ViewRendererStateful renderer = new ViewRendererStateful(this.adapter, context, true);
    renderer.renderPages(song, null, row);
    return row;
  }

}
