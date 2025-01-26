package io.github.musicdoc.note;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.github.musicdoc.clef.Clef;
import io.github.musicdoc.decoration.DecoratedItem;
import io.github.musicdoc.decoration.MusicalDecoration;
import io.github.musicdoc.glyphs.MusicalGlyphs;
import io.github.musicdoc.glyphs.MusicalGlyphsContext;
import io.github.musicdoc.glyphs.smufl.SmuflGlyphsNote;
import io.github.musicdoc.glyphs.unicode.UnicodeGlyphsNotes;
import io.github.musicdoc.interval.ChromaticInterval;
import io.github.musicdoc.rhythm.fraction.PlainFraction;
import io.github.musicdoc.rhythm.punctuation.Punctuation;
import io.github.musicdoc.rhythm.value.MusicalValue;
import io.github.musicdoc.tab.TabTone;
import io.github.musicdoc.tone.Tone;
import io.github.musicdoc.transpose.AbstractTransposable;
import io.github.musicdoc.transpose.TransposeContext;

/**
 * Container for {@link Tone} and optional {@link TabTone} to support tablature.
 *
 * @see Note
 */
public class NoteTone extends AbstractTransposable<NoteTone> implements DecoratedItem, MusicalGlyphs {

  private Note note;

  private Tone tone;

  private TabTone tab;

  private List<MusicalDecoration> decorations;

  /**
   * The constructor.
   *
   * @param note the owning {@link Note}.
   * @param tone the {@link #getTone() tone}.
   */
  public NoteTone(Note note, Tone tone) {

    this(note, tone, null);
  }

  /**
   * The constructor.
   *
   * @param note the owning {@link Note}.
   * @param tone the {@link #getTone() tone}.
   * @param tab the {@link #getTab() tab}.
   */
  NoteTone(Note note, Tone tone, TabTone tab) {

    super();
    Objects.requireNonNull(tone);
    this.note = note;
    this.tone = tone;
    this.tab = tab;
  }

  /**
   * @return the actual {@link Tone}.
   */
  public Tone getTone() {

    return this.tone;
  }

  /**
   * @param tone new value of {@link #getTone()}.
   */
  void setTone(Tone tone) {

    this.tone = tone;
  }

  /**
   * @return the {@link TabTone} or {@code null} if undefined.
   */
  public TabTone getTab() {

    return this.tab;
  }

  /**
   * @param tab new value of {@link #getTab()}.
   */
  void setTab(TabTone tab) {

    this.tab = tab;
  }

  /**
   * @return the owning {@link Note}.
   */
  Note getNote() {

    return this.note;
  }

  /**
   * @param note new value of {@link #getNote()}.
   */
  void setNote(Note note) {

    assert (this.note == null);
    assert (note != null);
    this.note = note;
  }

  @Override
  public int getDecorationCount() {

    if (this.decorations == null) {
      return 0;
    }
    return this.decorations.size();
  }

  @Override
  public MusicalDecoration getDecoration(int i) {

    if ((i >= 0) && (i < getDecorationCount())) {
      return this.decorations.get(i);
    }
    return null;
  }

  void addDecoration(MusicalDecoration decoration) {

    if (this.decorations == null) {
      this.decorations = new ArrayList<>();
    }
    this.decorations.add(decoration);
  }

  boolean removeDecoration(MusicalDecoration decoration) {

    if (this.decorations == null) {
      return false;
    }
    return this.decorations.remove(decoration);
  }

  @Override
  public String getGlyphs(MusicalGlyphsContext context) {

    return getGlyphs(context, context.getStemDirection());
  }

  /**
   * @param context the {@link MusicalGlyphsContext}.
   * @param stemDirection the already computed {@link StemDirection} or {@link StemDirection#isAuto(StemDirection) auto}
   *        to compute.
   * @return the glyphs for this {@link NoteTone} with the given {@link StemDirection}.
   */
  public String getGlyphs(MusicalGlyphsContext context, StemDirection stemDirection) {

    String glyphs = null;
    if (StemDirection.isAuto(stemDirection)) {
      Clef clef = context.getClef();
      if (clef == null) {
        clef = Clef.G;
      }
      // TODO for beams this is incorrect and needs to be computed for entire sequences of notes.
      Tone middleTone = clef.getMiddleTone();
      if (this.tone.isLower(middleTone)) {
        stemDirection = StemDirection.DOWN;
      } else {
        stemDirection = StemDirection.UP;
      }
    }
    boolean down = StemDirection.DOWN == stemDirection;
    MusicalValue value = this.note.getValue();
    PlainFraction plain = value.getPlain();
    if (context.isEnforceUnicode()) {
      glyphs = UnicodeGlyphsNotes.get(plain, down);
    } else {
      glyphs = SmuflGlyphsNote.get(plain, down);
    }
    Punctuation punctuation = value.getPunctuation();
    if (punctuation != null) {
      String pGlyphs = punctuation.getGlyphs(context);
      if (pGlyphs == null) {
        glyphs = null;
      } else {
        glyphs = glyphs + pGlyphs;
      }
    }
    if (glyphs == null) {
      throw new IllegalStateException("Not implemented/supported");
    }
    return glyphs;
  }

  @Override
  public NoteTone transpose(int steps, boolean diatonic, TransposeContext context) {

    Tone newTone = this.tone.transpose(steps, diatonic, context);
    TabTone newTab = this.tab;
    if ((this.tab != null) && context.isChangeTab()) {
      int chromaticSteps = steps;
      if (diatonic) {
        ChromaticInterval interval = newTone.getInterval(this.tone);
        chromaticSteps = interval.getChromaticSteps().intValue();
      }
      int fret = this.tab.getFret() + chromaticSteps;
      if (fret >= 0) {
        newTab = new TabTone(this.tab.getString(), fret);
      }
    }
    return new NoteTone(null, newTone, newTab);
  }

  @Override
  public int hashCode() {

    return this.tone.hashCode();
  }

  @Override
  public boolean equals(Object obj) {

    if (obj == this) {
      return true;
    } else if ((obj == null) || !(obj instanceof NoteTone)) {
      return false;
    }
    NoteTone other = (NoteTone) obj;
    if (!Objects.equals(this.tone, other.tone)) {
      return false;
    } else if (!Objects.equals(this.decorations, other.decorations)) {
      return false;
    }
    return true;
  }

  @Override
  public void toString(StringBuilder sb) {

    this.tone.toString(sb);
    if (this.tab != null) {
      sb.append('<');
      sb.append(this.tab.toString());
      sb.append('>');
    }
  }

}
