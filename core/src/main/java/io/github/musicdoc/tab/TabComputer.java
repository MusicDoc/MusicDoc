package io.github.musicdoc.tab;

import io.github.musicdoc.decoration.FretboardDecoration;
import io.github.musicdoc.instrument.string.StringTuning;
import io.github.musicdoc.interval.ChromaticInterval;
import io.github.musicdoc.note.Note;
import io.github.musicdoc.rhythm.item.ValuedItem;
import io.github.musicdoc.score.cell.ScoreCell;
import io.github.musicdoc.score.line.ScoreVoiceLine;
import io.github.musicdoc.tone.Tone;

/**
 * Type to {@link #computeTab(ScoreVoiceLine, StringTuning, int) compute tablature}. It will compute the
 * {@link TabTone}s for a given {@link ScoreVoiceLine}.
 *
 * @see Note#getTab(int)
 */
public class TabComputer {

  private static final TabComputer INSTANCE = new TabComputer();

  /**
   * @param voice the {@link ScoreVoiceLine} to computer the tablature for.
   * @param tuning the {@link StringTuning}.
   */
  public void computeTab(ScoreVoiceLine voice, StringTuning tuning) {

    computeTab(voice, tuning, 0);
  }

  /**
   * @param voice the {@link ScoreVoiceLine} to computer the tablature for.
   * @param tuning the {@link StringTuning}.
   * @param fretLocation the preferred {@link TabTone#getFret() fret} location. Use {@code 0} for easy playing with open
   *        strings included or e.g. {@code 5} if you prefer to start playing from the fifth fret upwards.
   */
  public void computeTab(ScoreVoiceLine voice, StringTuning tuning, int fretLocation) {

    TabTone[] previousTabs = new TabTone[tuning.getStringCount()];
    int previousToneCount = 0;
    int maxFret = tuning.getMaxFret();
    assert (maxFret >= 12);
    for (ScoreCell cell : voice.getCells()) {
      ValuedItem<?> item = cell.getItem();
      if (item instanceof Note) {
        Note note = (Note) item;
        int toneCount = note.getToneCount();
        for (int i = 0; i < toneCount; i++) {
          Tone tone = note.getTone(i);
          TabTone previousTab = null;
          if (i < previousToneCount) {
            previousTab = previousTabs[i];
          }
          int forceString = -1;
          if (note.getDecorations().contains(FretboardDecoration.SLIDE) && (previousTab != null)) {
            forceString = previousTab.getString();
          }
          TabTone tab = computeTab(tone, tuning, forceString, fretLocation);
          note.setTab(i, tab);
          if (i < previousTabs.length) {
            previousTabs[i] = tab;
          }
        }
        previousToneCount = toneCount;
        if (previousToneCount > previousTabs.length) {
          previousToneCount = previousTabs.length;
        }
      }
    }
  }

  private TabTone computeTab(Tone tone, StringTuning tuning, int forceString, int fretLocation) {

    TabTone tab = null;
    if (forceString >= 0) {
      Tone stringTone = tuning.getString(forceString);
      if (stringTone != null) {
        ChromaticInterval interval = stringTone.getInterval(stringTone);
        int fret = interval.getChromaticSteps().intValue();
        if (fret >= 0) {
          tab = new TabTone(forceString, fret);
        }
      }
    }
    int maxFret = tuning.getMaxFret();
    if (tab == null) {
      int size = tuning.getStringCount();
      for (int stringIndex = 0; stringIndex < size; stringIndex++) {
        Tone stringTone = tuning.getString(stringIndex);
        ChromaticInterval interval = stringTone.getInterval(stringTone);
        int fret = interval.getChromaticSteps().intValue();
        if (fret >= 0) {
          TabTone newTab = new TabTone(stringIndex, fret);
          if (tab == null) {
            tab = newTab;
          } else {
            tab = getPreferred(tab, newTab, maxFret, fretLocation);
          }
        }
      }
    }
    return tab;
  }

  private TabTone getPreferred(TabTone tab1, TabTone tab2, int maxFret, int fretLocation) {

    int fret1 = tab1.getFret();
    int fret2 = tab2.getFret();
    // ensure tab1.fret <= tab2.fret
    if (fret1 > fret2) {
      TabTone tab = tab1;
      tab1 = tab2;
      tab2 = tab;
      int fret = fret1;
      fret1 = fret2;
      fret2 = fret;
    }
    if (fret2 > maxFret) {
      return tab1;
    }
    int delta1 = fret1 - fretLocation;
    if (delta1 >= 0) {
      return tab1;
    }
    if (delta1 < -1) {
      return tab2;
    }
    int delta2 = fret2 - fretLocation;
    if ((delta2 >= 0) && (delta2 <= 5)) {
      return tab2;
    }
    return tab1;
  }

  /**
   * @return the instance of {@link TabComputer}.
   */
  public static TabComputer get() {

    return INSTANCE;
  }

}
