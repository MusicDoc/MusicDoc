/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.music.rythm.value;

import java.util.List;

import io.github.musicdoc.ObjectHelper;
import io.github.musicdoc.music.decoration.MusicalDecoration;
import io.github.musicdoc.music.glyphs.MusicalGlyphs;
import io.github.musicdoc.music.note.Note;
import io.github.musicdoc.music.rythm.rest.Rest;
import io.github.musicdoc.music.tone.Tone;
import io.github.musicdoc.music.transpose.AbstractTransposable;

/**
 * A valued item is a single item of a musical notation system such as {@link io.github.musicdoc.music.stave.Stave}. It
 * always has a {@link #getValue() value} defining its duration.
 *
 * @param <SELF> type of the class itself.
 */
public abstract class ValuedItem<SELF extends ValuedItem<SELF>> extends AbstractTransposable<SELF>
    implements MusicalGlyphs {

  /** @see #getValue() */
  protected final MusicalValue value;

  private final List<MusicalDecoration> decorations;

  /**
   * The constructor.
   *
   * @param value - the {@link #getValue() value}.
   * @param decorations - {@link #getDecorations() decorations}.
   */
  public ValuedItem(MusicalValue value, List<MusicalDecoration> decorations) {

    super();
    ObjectHelper.requireNonNull(value, "value");
    this.value = value;
    this.decorations = decorations;
  }

  /**
   * @return the {@link Tone} of this item or <code>null</code> if this item does not have a tone (e.g. {@link Rest}).
   * @see Note
   */
  public Tone getTone() {

    return null;
  }

  /**
   * @return the {@link MusicalValue} that defines the duration of this item.
   */
  public MusicalValue getValue() {

    return this.value;
  }

  /**
   * @return the {@link List} of {@link MusicalDecoration}s.
   */
  public List<MusicalDecoration> getDecorations() {

    return this.decorations;
  }

  @Override
  public int hashCode() {

    return ObjectHelper.hash(this.value, getTone());
  }

  @Override
  public boolean equals(Object obj) {

    if (obj == this) {
      return true;
    } else if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }
    ValuedItem<?> other = (ValuedItem<?>) obj;
    if (!ObjectHelper.equals(this.value, other.value) || !ObjectHelper.equals(getTone(), other.getTone())
        || !ObjectHelper.equals(this.decorations, other.decorations)) {
      return false;
    }
    return true;
  }

}
