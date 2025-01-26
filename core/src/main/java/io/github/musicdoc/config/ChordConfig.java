package io.github.musicdoc.config;

import io.github.mmm.property.booleans.BooleanProperty;

/**
 * {@link Config} for Chords and Capo.
 */
public class ChordConfig extends Config {

  public final BooleanProperty showChords;

  public final BooleanProperty showCapoChords;

  public final BooleanProperty showNativeAndCapoChords;

  public final BooleanProperty showCapoAsNumerals;

  public ChordConfig() {

    super();
    this.showChords = add(new BooleanProperty("showChords"));
    this.showCapoChords = add(new BooleanProperty("showCapoChords"));
    this.showNativeAndCapoChords = add(new BooleanProperty("showNativeAndCapoChords"));
    this.showCapoAsNumerals = add(new BooleanProperty("showCapoAsNumerals"));
    this.showChords.setValue(true);
  }

}
