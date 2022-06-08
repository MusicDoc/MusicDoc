package io.github.musicdoc.config;

import io.github.musicdoc.bean.Bean;
import io.github.musicdoc.property.BooleanProperty;

/**
 * {@link Bean} for Chords and Capo.
 */
public class ChordConfig extends Config {

  public final BooleanProperty showChords;

  public final BooleanProperty showCapoChords;

  public final BooleanProperty showNativeAndCapoChords;

  public final BooleanProperty showCapoAsNumerals;

  public ChordConfig() {

    super();
    this.showChords = register(new BooleanProperty("showChords", true));
    this.showCapoChords = register(new BooleanProperty("showCapoChords"));
    this.showNativeAndCapoChords = register(new BooleanProperty("showNativeAndCapoChords"));
    this.showCapoAsNumerals = register(new BooleanProperty("showCapoAsNumerals"));
  }

}
