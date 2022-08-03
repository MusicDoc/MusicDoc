package io.github.musicdoc.music.decoration;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import io.github.musicdoc.music.PeriodType;
import io.github.musicdoc.music.format.FormatConstants;
import io.github.musicdoc.music.glyphs.MusicalGlyphs;
import io.github.musicdoc.music.glyphs.MusicalGlyphsContext;
import io.github.musicdoc.music.rythm.value.ValuedItem;

/**
 * Decoration of a {@link ValuedItem}.
 *
 * @see ValuedItem#getDecorations()
 */
public abstract class MusicalDecoration implements MusicalGlyphs {

  private static final Map<String, MusicalDecoration> NAME_MAP = new HashMap<>();

  /** @see #getName() */
  protected final String name;

  /** @see #getGlyphs() */
  protected final String glyphsUnicode;

  /** @see #getGlyphs() */
  protected final String glyphsSmufl;

  /** @see #getPeriod() */
  protected final PeriodType period;

  /** @see #getReference() */
  protected final MusicalDecoration reference;

  /** @see #hashCode() */
  protected final int hash;

  static {
    ArticulationDecoration.load();
    DynamicDecoration.load();
    JumpDecoration.load();
    MarkerDecoration.load();
    PedalDecoration.load();
    PluckedDecoration.load();
    SlurDecoration.load();
    StringDecoration.load();
    TieDecoration.load();
    TrillDecoration.load();
  }

  MusicalDecoration(String name, PeriodType period, String unicode, String smufl, MusicalDecoration reference) {

    super();
    this.name = name;
    this.period = period;
    this.glyphsUnicode = unicode;
    this.glyphsSmufl = smufl;
    if (reference == null) {
      this.reference = this;
      this.hash = this.name.hashCode();
    } else {
      this.reference = reference;
      this.hash = reference.hash;
    }
    if (!name.isEmpty()) {
      MusicalDecoration duplicate = NAME_MAP.put(getKey(name), this);
      if (duplicate != null) {
        throw new IllegalStateException(name);
      }
    }
  }

  /**
   * @return the name.
   */
  public String getName() {

    return this.name;
  }

  /**
   * @return the {@link MusicalDecorationPosition position} of this decoration relative to the
   *         {@link ValuedItem#getDecorations() owning} {@link ValuedItem item}.
   */
  public abstract MusicalDecorationPosition getPosition();

  /**
   * @return the {@link PeriodType} or {@code null} for none.
   */
  public PeriodType getPeriod() {

    return this.period;
  }

  @Override
  public String getGlyphs(MusicalGlyphsContext context) {

    if (context.isEnforceUnicode()) {
      return this.glyphsUnicode;
    } else {
      return this.glyphsSmufl;
    }
  }

  /**
   * @return {@code true} if this decoration is used as suffix after the actual value, {@code false} otherwise (for
   *         prefix). By default decorations have to be used as prefix. Only special decorations like
   *         {@link SlurDecoration#SLUR_END} are used as suffix.
   */
  public boolean isItemSuffix() {

    return false;
  }

  /**
   * @return the {@link MusicalDecoration} with the short {@link #getName() name} form. There can be multiple
   *         {@link MusicalDecoration decorations} that are semantically equal but has different {@link #getName()
   *         names}. The will all share the same reference with the short {@link #getName() name}. All other
   *         {@link #getName() names} are called alias.
   */
  public MusicalDecoration getReference() {

    return this.reference;
  }

  /**
   * @param altNames the alternative alias names.
   */
  protected void alias(String... altNames) {

    if (altNames == null) {
      return;
    }
    for (String alias : altNames) {
      create(alias);
    }
  }

  /**
   * @param newName the new {@link #getName() name}.
   * @return a copy of this {@link MarkerDecoration} with the given {@link #getName() name}.
   */
  protected abstract MusicalDecoration create(String newName);

  @Override
  public int hashCode() {

    return this.hash;
  }

  @Override
  public boolean equals(Object obj) {

    if (obj == this) {
      return true;
    }
    if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }
    MusicalDecoration other = (MusicalDecoration) obj;
    if ((this.reference != other.reference) || !Objects.equals(this.period, other.period)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {

    if (this.name.length() == 1) {
      return this.name;
    }
    return FormatConstants.DECORATION_START + this.name + FormatConstants.DECORATION_END;
  }

  @Override
  public void toString(StringBuilder sb) {

    if (this.name.length() == 1) {
      sb.append(this.name);
    } else {
      sb.append(FormatConstants.DECORATION_START);
      sb.append(this.name);
      sb.append(FormatConstants.DECORATION_END);
    }
  }

  /**
   * @param name the {@link #getName() name}.
   * @return the {@link MarkerDecoration} with the given {@link #getName() name}.
   */
  public static MusicalDecoration of(String name) {

    if ((name == null) || name.isEmpty()) {
      return null;
    }
    return NAME_MAP.get(getKey(name));
  }

  private static String getKey(String name) {

    if (name.length() > 1) {
      name = name.toLowerCase(Locale.US);
    }
    return name;
  }
}
