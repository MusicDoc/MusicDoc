/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.rhythm.item;

import java.util.List;
import java.util.Objects;

import io.github.musicdoc.MutableObject;
import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.MutableObjecteHelper;
import io.github.musicdoc.decoration.DecoratedItem;
import io.github.musicdoc.decoration.MusicalDecoration;
import io.github.musicdoc.glyphs.MusicalGlyphs;
import io.github.musicdoc.note.NoteTone;
import io.github.musicdoc.rhythm.value.MusicalValue;
import io.github.musicdoc.tone.Tone;
import io.github.musicdoc.transpose.AbstractTransposable;

/**
 * A valued item is a single item of a musical notation system such as {@link io.github.musicdoc.stave.Stave}. It always
 * has a {@link #getValue() value} defining its duration.
 *
 * @param <SELF> type of the class itself.
 */
public abstract class ValuedItem<SELF extends ValuedItem<SELF>> extends AbstractTransposable<SELF>
    implements MusicalGlyphs, DecoratedItem, MutableObject<SELF> {

  /** @see #getValue() */
  protected MusicalValue value;

  private List<MusicalDecoration> decorations;

  private boolean immutable;

  /**
   * The constructor.
   *
   * @param value - the {@link #getValue() value}.
   * @param decorations - {@link #getDecorations() decorations}.
   */
  public ValuedItem(MusicalValue value, List<MusicalDecoration> decorations) {

    super();
    Objects.requireNonNull(value, "value");
    this.value = value;
    this.decorations = decorations;
  }

  /**
   * The {@link #copy()} constructor.
   *
   * @param item the {@link ValuedItem} to copy.
   * @param copier the {@link MutableObjecteCopier}.
   */
  protected ValuedItem(ValuedItem<SELF> item, MutableObjecteCopier copier) {

    super();
    this.value = item.value.copy();
    this.decorations = copier.copyListFlat(item.decorations);
  }

  @Override
  public boolean isImmutable() {

    return this.immutable;
  }

  @Override
  public SELF makeImmutable() {

    if (!this.immutable) {
      this.decorations = MutableObjecteHelper.makeImmutableFlat(this.decorations);
      this.immutable = true;
    }
    return self();
  }

  /**
   * @return the {@link Tone} of this item or <code>null</code> if this item does not have a tone (e.g.
   *         {@link io.github.musicdoc.rhythm.rest.Rest}).
   * @see io.github.musicdoc.note.Note
   */
  public Tone getTone() {

    return null;
  }

  /**
   * @return the number of {@link #getTone(int) tones} contained in this item. Typically {@code 1} but may be higher in
   *         case of a chord or unison. Will always be {@code 0} in case of a
   *         {@link io.github.musicdoc.rhythm.rest.Rest}.
   */
  public int getToneCount() {

    return 0;
  }

  /**
   * @param i the index of the requested {@link Tone}.
   * @return the {@link Tone} at the given index or {@code null} if no such tone exists.
   */
  public Tone getTone(int i) {

    return null;
  }

  /**
   * @param i the index of the requested {@link NoteTone}.
   * @return the {@link NoteTone} at the given index or {@code null} if no such {@link NoteTone} exists.
   */
  public NoteTone getNoteTone(int i) {

    return null;
  }

  /**
   * @return the {@link MusicalValue} that defines the duration of this item.
   */
  public MusicalValue getValue() {

    return this.value;
  }

  /**
   * @param value new value of {@link #getValue()}.
   * @return an {@link ValuedItem item} with the given {@link #getValue() value} and all other properties like
   *         {@code this} one. Will be a {@link #copy() copy} if {@link #isImmutable() immutable}.
   */
  public SELF setValue(MusicalValue value) {

    if (this.value == value) {
      return self();
    }
    SELF item = makeMutable();
    item.value = value;
    return item;
  }

  /**
   * @return the {@link List} of {@link MusicalDecoration}s.
   */
  public List<MusicalDecoration> getDecorations() {

    return this.decorations;
  }

  @Override
  public int getDecorationCount() {

    return this.decorations.size();
  }

  @Override
  public MusicalDecoration getDecoration(int i) {

    if ((i >= 0) && (i < this.decorations.size())) {
      return this.decorations.get(i);
    }
    return null;
  }

  /**
   * @param decoration the {@link MusicalDecoration} to add to {@link #getDecorations() decorations}.
   * @return an item with the given {@link MusicalDecoration} added and all other properties like {@code this} one. Will
   *         be a {@link #copy() copy} if {@link #isImmutable() immutable}.
   */
  public SELF addDecoration(MusicalDecoration decoration) {

    SELF item = makeMutable();
    item.getDecorations().add(decoration);
    return item;
  }

  @Override
  public int hashCode() {

    return Objects.hash(this.value, getTone());
  }

  @Override
  public boolean equals(Object obj) {

    if (obj == this) {
      return true;
    } else if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }
    ValuedItem<?> other = (ValuedItem<?>) obj;
    if (!Objects.equals(this.value, other.value)) {
      return false;
    } else if (!Objects.equals(this.decorations, other.decorations)) {
      return false;
    } else {
      int tc = getToneCount();
      int otc = other.getToneCount();
      if (tc != otc) {
        return false;
      }
      for (int i = 0; i < tc; i++) {
        Tone t = getTone(i);
        Tone ot = other.getTone(i);
        if (!Objects.equals(t, ot)) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public final void toString(StringBuilder sb) {

    for (MusicalDecoration decoration : this.decorations) {
      if (!decoration.isItemSuffix()) {
        decoration.toString(sb);
      }
    }
    toStringItem(sb);
    for (MusicalDecoration decoration : this.decorations) {
      if (decoration.isItemSuffix()) {
        decoration.toString(sb);
      }
    }
  }

  /**
   * Called from {@link #toString(StringBuilder)} to format the raw item excluding the {@link #getDecorations()
   * decorations} for simplified extension.
   *
   * @param sb the {@link StringBuilder} to append to.
   */
  protected void toStringItem(StringBuilder sb) {

    this.value.toString(sb);
  }

}
