package io.github.musicdoc.stave.activity;

import java.util.Set;

import io.github.musicdoc.clef.ClefSymbol;

/**
 * Abstract base class for implementations of {@link StavePlainActivity} or {@link StaveVoiceAcitvity} based on a
 * {@link #isWhiteList() whitelist or blacklist} as {@link Set}.
 *
 * @param <T> type of the {@link #isWhiteList() whitelist or blacklist} values contained in the {@link Set}.
 */
public abstract class ActivityListFilter<T> {

  private final Set<T> set;

  private final boolean whiteList;

  /**
   * The constructor.
   *
   * @param whiteList the {@link #isWhiteList() whitelist flag}.
   * @param set the {@link Set} to use as whitelist or blacklist.
   */
  public ActivityListFilter(boolean whiteList, Set<T> set) {

    super();
    this.whiteList = whiteList;
    this.set = set;
  }

  /**
   * @param value the value to match.
   * @return {@code true} if active, {@code false} otherwise.
   */
  protected boolean isActive(T value) {

    // if whitelist symbol needs to be contained, otherwise not contained
    return this.set.contains(value) == this.whiteList;
  }

  /**
   * @return {@code true} if {@link ClefSymbol}s are used as whitelist (only specified {@link ClefSymbol}s will be
   *         active), {@code false} otherwise (blacklist with {@link ClefSymbol}s that are inactive).
   */
  public boolean isWhiteList() {

    return this.whiteList;
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();
    String prefix;
    if (this.whiteList) {
      prefix = "+[";
    } else {
      prefix = "-[";
    }
    for (T value : this.set) {
      sb.append(prefix);
      sb.append(value);
      prefix = ",";
    }
    sb.append(']');
    return sb.toString();
  }

}
