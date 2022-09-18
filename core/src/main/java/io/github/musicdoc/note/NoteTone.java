package io.github.musicdoc.note;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.github.musicdoc.decoration.DecoratedItem;
import io.github.musicdoc.decoration.MusicalDecoration;
import io.github.musicdoc.interval.ChromaticInterval;
import io.github.musicdoc.tab.TabTone;
import io.github.musicdoc.tone.Tone;
import io.github.musicdoc.transpose.AbstractTransposable;
import io.github.musicdoc.transpose.TransposeContext;

/**
 * Container for {@link Tone} and optional {@link TabTone} to support tablature.
 *
 * @see Note
 */
public class NoteTone extends AbstractTransposable<NoteTone> implements DecoratedItem {

  private Tone tone;

  private TabTone tab;

  private List<MusicalDecoration> decorations;

  /**
   * The constructor.
   *
   * @param tone the {@link #getTone() tone}.
   */
  public NoteTone(Tone tone) {

    this(tone, null);
  }

  /**
   * The constructor.
   *
   * @param tone the {@link #getTone() tone}.
   * @param tab the {@link #getTab() tab}.
   */
  public NoteTone(Tone tone, TabTone tab) {

    super();
    Objects.requireNonNull(tone);
    this.tone = tone;
    this.tab = tab;
  }

  /**
   * @return tone
   */
  public Tone getTone() {

    return this.tone;
  }

  /**
   * @return tab
   */
  public TabTone getTab() {

    return this.tab;
  }

  /**
   * @param tone new value of {@link #getTone()}.
   */
  void setTone(Tone tone) {

    this.tone = tone;
  }

  /**
   * @param tab new value of {@link #getTab()}.
   */
  void setTab(TabTone tab) {

    this.tab = tab;
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
    return new NoteTone(newTone, newTab);
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
