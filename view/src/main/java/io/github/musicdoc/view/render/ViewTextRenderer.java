package io.github.musicdoc.view.render;

import io.github.musicdoc.glyphs.MusicalGlyphs;
import io.github.musicdoc.stave.voice.StaveVoice;
import io.github.musicdoc.view.ViewContext;
import io.github.musicdoc.view.layout.ViewPlacement;
import io.github.musicdoc.view.layout.ViewTextAlignment;
import io.github.musicdoc.view.layout.ViewTextProgression;
import io.github.musicdoc.view.layout.ViewTextType;
import io.github.musicdoc.view.model.ViewItemText;

/**
 * Interface to render text from {@link String} to {@link ViewItemText}.
 */
public interface ViewTextRenderer {

  /**
   * Renders the given {@link MusicalGlyphs}.
   *
   * @param glyphs the {@link MusicalGlyphs} to render.
   * @param textType the {@link ViewTextType} to use.
   * @return the rendered {@link ViewItemText}.
   */
  default ViewItemText renderText(MusicalGlyphs glyphs) {

    return renderText(glyphs, ViewTextType.MUSIC_CONTENT);
  }

  /**
   * Renders the given {@link MusicalGlyphs}.
   *
   * @param glyphs the {@link MusicalGlyphs} to render.
   * @param textType the {@link ViewTextType} to use.
   * @return the rendered {@link ViewItemText}.
   */
  default ViewItemText renderText(MusicalGlyphs glyphs, ViewTextType textType) {

    return renderText(glyphs, textType, textType.asTextAlignment());
  }

  /**
   * Renders the given {@link MusicalGlyphs}.
   *
   * @param glyphs the {@link MusicalGlyphs} to render.
   * @param textType the {@link ViewTextType} to use.
   * @param alignment the {@link ViewTextAlignment} to use.
   * @return the rendered {@link ViewItemText}.
   */
  default ViewItemText renderText(MusicalGlyphs glyphs, ViewTextType textType, ViewTextAlignment alignment) {

    String text = glyphs.getGlyphs(getContext().getGlyphsContext());
    return renderText(text, textType, alignment);
  }

  /**
   * Renders the given {@code text}.
   *
   * @param text the text to render.
   * @param textType the {@link ViewTextType} to use.
   * @return the rendered {@link ViewItemText}.
   */
  default ViewItemText renderText(String text, ViewTextType textType) {

    return renderText(text, textType, textType.asTextAlignment());
  }

  /**
   * Renders the given {@code text}.
   *
   * @param text the text to render.
   * @param textType the {@link ViewTextType} to use.
   * @param alignment the {@link ViewTextAlignment} to use.
   * @return the rendered {@link ViewItemText}.
   */
  ViewItemText renderText(String text, ViewTextType textType, ViewTextAlignment alignment);

  /**
   * Renders the given {@code text}.
   *
   * @param text the text to render.
   * @param textType the {@link ViewTextType} to use.
   * @param voice the {@link StaveVoice} to use.
   * @param placement the {@link ViewPlacement} to use.
   * @return the rendered {@link ViewItemText}.
   */
  default ViewItemText renderText(String text, ViewTextType textType, StaveVoice voice) {

    return renderText(text, textType, ViewTextAlignment.LEFT, ViewTextProgression.ABSOLUTE, voice);
  }

  /**
   * Renders the given {@code text}.
   *
   * @param text the text to render.
   * @param textType the {@link ViewTextType} to use.
   * @param alignment the {@link ViewTextAlignment} to use.
   * @param progression the {@link ViewTextProgression} to use.
   * @return the rendered {@link ViewItemText}.
   */
  default ViewItemText renderText(String text, ViewTextType textType, ViewTextAlignment alignment,
      ViewTextProgression progression) {

    return renderText(text, textType, alignment, progression, null);
  }

  /**
   * Renders the given {@code text}.
   *
   * @param text the text to render.
   * @param textType the {@link ViewTextType} to use.
   * @param alignment the {@link ViewTextAlignment} to use.
   * @param progression the {@link ViewTextProgression} to use.
   * @param voice the {@link StaveVoice} to use.
   * @return the rendered {@link ViewItemText}.
   */
  ViewItemText renderText(String text, ViewTextType textType, ViewTextAlignment alignment,
      ViewTextProgression progression, StaveVoice voice);

  /**
   * @return the current {@link ViewContext}.
   */
  ViewContext getContext();

}
