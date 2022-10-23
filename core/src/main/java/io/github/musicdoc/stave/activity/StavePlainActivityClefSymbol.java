package io.github.musicdoc.stave.activity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import io.github.musicdoc.clef.Clef;
import io.github.musicdoc.clef.ClefSymbol;
import io.github.musicdoc.stave.Stave;

/**
 * Implementation of {@link StavePlainActivity} by {@link #isWhiteList() whitelist or blacklist}.
 */
public class StavePlainActivityClefSymbol extends ActivityListFilter<ClefSymbol> implements StavePlainActivity {

  /**
   * The constructor.
   *
   * @param whiteList the {@link #isWhiteList() whitelist flag}.
   * @param symbols the {@link ClefSymbol}s to use as whitelist or blacklist.
   */
  public StavePlainActivityClefSymbol(boolean whiteList, Set<ClefSymbol> symbols) {

    super(whiteList, symbols);
  }

  /**
   * The constructor.
   *
   * @param whiteList the {@link #isWhiteList() whitelist flag}.
   * @param symbols the {@link ClefSymbol}s to use as whitelist or blacklist.
   */
  public StavePlainActivityClefSymbol(boolean whiteList, ClefSymbol... symbols) {

    this(whiteList, new HashSet<>(Arrays.asList(symbols)));
  }

  @Override
  public boolean isActive(Stave stave) {

    if (stave == null) {
      return false;
    }
    Clef clef = stave.getClef();
    ClefSymbol symbol = null;
    if (clef != null) {
      symbol = clef.getSymbol();
    }
    return isActive(symbol);
  }

}
