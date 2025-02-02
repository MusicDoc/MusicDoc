/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.note;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.decoration.MusicalDecoration;
import io.github.musicdoc.glyphs.MusicalGlyphsContext;
import io.github.musicdoc.rhythm.item.ValuedItem;
import io.github.musicdoc.rhythm.punctuation.Punctuation;
import io.github.musicdoc.rhythm.value.MusicalValue;
import io.github.musicdoc.tab.TabTone;
import io.github.musicdoc.tone.Tone;
import io.github.musicdoc.transpose.TransposeContext;

/**
 * A {@link Note} is a musical object that has {@link #getTone() one} or {@link #getTone(int) multiple} {@link Tone}s
 * with a {@link #getValue() value} and optional {@link #getDecorations() decorations}.<br>
 * So e.g. the {@link Tone#C4} played with the length of a {@link MusicalValue#_1_4 quarter} can be expressed
 * accordingly. Also chords with {@link #getToneCount() multiple} {@link Tone}s of the same {@link #getValue() value}
 * (length) are possible. Even the same {@link Tone} can be contained twice what is called a <em>unison</em>.<br>
 * {@link #getDecorations() Decorations} apply to all {@link Tone}s in this {@link Note}. However, a {@link NoteTone}
 * may have {@link NoteTone#getDecoration(int) additional decorations} specific only for that {@link Tone}. This only
 * makes sense for {@link MusicalDecoration} specific for an individual {@link Tone}. So e.g. a
 * {@link io.github.musicdoc.decoration.FretboardDecoration#SLIDE slide} applies to a single {@link Tone} while any
 * decoration with {@link MusicalDecoration#getPosition() position}
 * {@link io.github.musicdoc.decoration.MusicalDecorationPosition#TOP} or
 * {@link io.github.musicdoc.decoration.MusicalDecorationPosition#BOTTOM} always applies to all {@link Tone}s and
 * therefore shall never be added to an individual {@link Tone}.
 */
public class Note extends ValuedItem<Note> {

  private NoteTone tone;

  private List<NoteTone> tones;

  /**
   * The constructor.
   *
   * @param tone - the {@link #getTone() tone}.
   * @param value - the {@link #getValue() value}.
   */
  public Note(Tone tone, MusicalValue value) {

    this(tone, value, new ArrayList<>());
  }

  /**
   * The constructor.
   *
   * @param tone - the {@link #getTone() tone}.
   * @param value - the {@link #getValue() value}.
   * @param decoration - the {@link #getDecorations() decorations}.
   */
  public Note(Tone tone, MusicalValue value, MusicalDecoration decoration) {

    this(tone, value, asList(decoration));
    Objects.requireNonNull(decoration);
  }

  /**
   * The constructor.
   *
   * @param tone - the {@link #getTone() tone}.
   * @param value - the {@link #getValue() value}.
   * @param decorations - the {@link #getDecorations() decorations}.
   */
  public Note(Tone tone, MusicalValue value, MusicalDecoration... decorations) {

    this(tone, value, asList(decorations));
  }

  /**
   * The constructor.
   *
   * @param tone - the {@link #getTone() tone}.
   * @param value - the {@link #getValue() value}.
   * @param decorations - the {@link #getDecorations() decorations}.
   */
  public Note(Tone tone, MusicalValue value, List<MusicalDecoration> decorations) {

    this(tone, value, decorations, null);
  }

  Note(Tone tone, MusicalValue value, List<MusicalDecoration> decorations, List<NoteTone> tones) {

    super(value, decorations);
    Objects.requireNonNull(tone, "tone");
    this.tone = new NoteTone(this, tone);
    if (tones != null) {
      for (NoteTone noteTone : tones) {
        noteTone.setNote(this);
      }
    }
    this.tones = tones;
  }

  Note(NoteTone tone, MusicalValue value, List<MusicalDecoration> decorations, List<NoteTone> tones) {

    super(value, decorations);
    Objects.requireNonNull(tone, "tone");
    tone.setNote(this);
    this.tone = tone;
    if (tones != null) {
      for (NoteTone noteTone : tones) {
        noteTone.setNote(this);
      }
    }
    this.tones = tones;
  }

  private Note(Note note, MutableObjecteCopier copier) {

    super(note, copier);
    this.tone = note.tone;
    if (note.tones == null) {
      this.tones = null;
    } else {
      this.tones = new ArrayList<>(note.tones.size());
      for (NoteTone t : note.tones) {
        this.tones.add(new NoteTone(this, t.getTone(), t.getTab()));
      }
    }
  }

  @Override
  public Note copy(MutableObjecteCopier copier) {

    return new Note(this, copier);
  }

  @Override
  public Tone getTone() {

    return this.tone.getTone();
  }

  /**
   * @param tone new value of {@link #getTone() tone}.
   * @return a {@link Note} with the given {@link #getTone() tone} and all other properties like {@code this} one. Will
   *         be a {@link #copy() copy} if {@link #isImmutable() immutable}.
   */
  public Note setTone(Tone tone) {

    if (this.tone.getTone() == tone) {
      return this;
    }
    Note note = makeMutable();
    note.tone = new NoteTone(this, tone);
    return note;
  }

  @Override
  public int getToneCount() {

    int count = 1;
    if (this.tones != null) {
      count += this.tones.size();
    }
    return count;
  }

  @Override
  public NoteTone getNoteTone(int i) {

    if (i < 0) {
      return null;
    } else if (i == 0) {
      return this.tone;
    } else if ((this.tones == null) || (i >= this.tones.size())) {
      return null;
    } else {
      return this.tones.get(i - 1);
    }
  }

  @Override
  public Tone getTone(int i) {

    NoteTone noteTone = getNoteTone(i);
    if (noteTone == null) {
      return null;
    }
    return noteTone.getTone();
  }

  /**
   * @param i the index of the {@link NoteTone}.
   * @return the {@link TabTone} at the given index or {@code null} if no such {@link TabTone} exists.
   */
  public TabTone getTab(int i) {

    NoteTone noteTone = getNoteTone(i);
    if (noteTone == null) {
      return null;
    }
    return noteTone.getTab();
  }

  /**
   * @param i the index of the {@link NoteTone}. Has to be in the range from {@code 0} to {@link #getToneCount()
   *        toneCount}-1.
   * @param tab the {@link NoteTone#getTab() tab} to set.
   * @return this {@link Note} if already mutable or a {@link #makeMutable() mutable copy}.
   */
  public Note setTab(int i, TabTone tab) {

    Note note = makeMutable();
    NoteTone noteTone = getNoteTone(i);
    if (noteTone == null) {
      throw new IndexOutOfBoundsException(Integer.toString(i));
    }
    noteTone.setTab(tab);
    return note;
  }

  /**
   * @param i the index of the {@link NoteTone}. Has to be in the range from {@code 0} to {@link #getToneCount()
   *        toneCount}-1.
   * @param decoration the {@link MusicalDecoration} to add.
   * @return this {@link Note} if already mutable or a {@link #makeMutable() mutable copy}.
   * @see NoteTone#getDecoration(int)
   */
  public Note addDecoration(int i, MusicalDecoration decoration) {

    Note note = makeMutable();
    NoteTone noteTone = getNoteTone(i);
    if (noteTone == null) {
      throw new IndexOutOfBoundsException(Integer.toString(i));
    }
    noteTone.addDecoration(decoration);
    return note;
  }

  /**
   * @param i the index of the {@link NoteTone}. Has to be in the range from {@code 0} to {@link #getToneCount()
   *        toneCount}-1.
   * @param decoration the {@link MusicalDecoration} to remove.
   * @return this {@link Note} if already mutable or a {@link #makeMutable() mutable copy}.
   * @see NoteTone#getDecoration(int)
   */
  public Note removeDecoration(int i, MusicalDecoration decoration) {

    Note note = makeMutable();
    NoteTone noteTone = getNoteTone(i);
    if (noteTone == null) {
      throw new IndexOutOfBoundsException(Integer.toString(i));
    }
    noteTone.removeDecoration(decoration);
    return note;
  }

  /**
   * @param extraTone the {@link Tone} to add.
   * @return this {@link Note} or a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public Note addTone(Tone extraTone) {

    Note note = makeMutable();
    if (note.tones == null) {
      note.tones = new ArrayList<>();
    }
    note.tones.add(new NoteTone(this, extraTone));
    return note;
  }

  @Override
  public Note transpose(int steps, boolean diatonic, TransposeContext context) {

    NoteTone newTone = this.tone.transpose(steps, diatonic, context);
    List<NoteTone> extraTones = null;
    if (this.tones != null) {
      extraTones = new ArrayList<>(this.tones.size());
      for (NoteTone t : this.tones) {
        extraTones.add(t.transpose(steps, diatonic, context));
      }
    }
    return new Note(newTone, getValue(), new ArrayList<>(getDecorations()), extraTones);
  }

  @Override
  public String getGlyphs(MusicalGlyphsContext context) {

    return this.tone.getGlyphs(context);
  }

  @Override
  protected void toStringItem(StringBuilder sb) {

    this.tone.getTone().toString(sb);
    if (this.tones != null) {
      for (NoteTone t : this.tones) {
        t.getTone().toString(sb);
      }
    }
    sb.append(':');
    super.toStringItem(sb);
  }

  /**
   * @param tone the {@link #getTone() tone}.
   * @param value the {@link #getValue() value}.
   * @return the {@link Note} with the specified values.
   */
  public static Note of(Tone tone, MusicalValue value) {

    return new Note(tone, value);
  }

  /**
   * @param tone the {@link #getTone() tone}.
   * @param value the {@link #getValue() value}.
   * @param decorations the {@link #getDecorations() decorations}.
   * @return the {@link Note} with the specified values.
   */
  public static Note of(Tone tone, MusicalValue value, MusicalDecoration... decorations) {

    return new Note(tone, value, decorations);
  }

  /**
   * @param tone the {@link #getTone() tone}.
   * @return the {@link Note} with the specified {@link #getTone() tone} and a {@link #getValue() value} of
   *         {@link MusicalValue#_1_1 1/1}.
   */
  public static Note of1_1(Tone tone) {

    return of(tone, MusicalValue._1_1);
  }

  /**
   * @param tone the {@link #getTone() tone}.
   * @return the {@link Note} with the specified {@link #getTone() tone} and a {@link #getValue() value} of
   *         {@link MusicalValue#_4_4 4/4}.
   */
  public static Note of4_4(Tone tone) {

    return of(tone, MusicalValue._4_4);
  }

  /**
   * @param tone the {@link #getTone() tone}.
   * @return the {@link Note} with the specified {@link #getTone() tone} and a {@link #getValue() value} of
   *         {@link MusicalValue#_1_2 1/2}.
   */
  public static Note of1_2(Tone tone) {

    return of(tone, MusicalValue._1_2);
  }

  /**
   * @param tone the {@link #getTone() tone}.
   * @return the {@link Note} with the specified {@link #getTone() tone} and a {@link #getValue() value} of
   *         {@link MusicalValue#_1_2 1/2} and {@link Punctuation#P1 punctuation}.
   */
  public static Note of1_2p(Tone tone) {

    return of(tone, new MusicalValue(1, 2, Punctuation.P1));
  }

  /**
   * @param tone the {@link #getTone() tone}.
   * @return the {@link Note} with the specified {@link #getTone() tone} and a {@link #getValue() value} of
   *         {@link MusicalValue#_1_4 1/4}.
   */
  public static Note of1_4(Tone tone) {

    return of(tone, MusicalValue._1_4);
  }

  /**
   * @param tone the {@link #getTone() tone}.
   * @return the {@link Note} with the specified {@link #getTone() tone} and a {@link #getValue() value} of
   *         {@link MusicalValue#_1_4 1/4}.
   */
  public static Note of3_4(Tone tone) {

    return of(tone, MusicalValue._3_4);
  }

  /**
   * @param tone the {@link #getTone() tone}.
   * @return the {@link Note} with the specified {@link #getTone() tone} and a {@link #getValue() value} of
   *         {@link MusicalValue#_1_4 1/4} and {@link Punctuation#P1 punctuation}.
   */
  public static Note of1_4p(Tone tone) {

    return of(tone, new MusicalValue(1, 4, Punctuation.P1));
  }

  /**
   * @param tone the {@link #getTone() tone}.
   * @return the {@link Note} with the specified {@link #getTone() tone} and a {@link #getValue() value} of
   *         {@link MusicalValue#_1_8 1/8}.
   */
  public static Note of1_8(Tone tone) {

    return of(tone, MusicalValue._1_8);
  }

  /**
   * @param tone the {@link #getTone() tone}.
   * @return the {@link Note} with the specified {@link #getTone() tone} and a {@link #getValue() value} of
   *         {@link MusicalValue#_1_16 1/16}.
   */
  public static Note of1_16(Tone tone) {

    return of(tone, MusicalValue._1_16);
  }

}
