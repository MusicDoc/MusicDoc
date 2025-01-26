package io.github.musicdoc.config;

import io.github.mmm.bean.Bean;
import io.github.mmm.bean.property.BeanProperty;

/**
 * {@link Bean} of the entire app.
 */
public class AppConfig extends Config {

  public final BeanProperty<ChordConfig> chordProperty;

  public final BeanProperty<PadConfig> padProperty;

  public final BeanProperty<MetronomeConfig> metronomeProperty;

  public final BeanProperty<MenuConfig> menuProperty;

  public AppConfig() {

    super();
    this.chordProperty = add(new BeanProperty<>("chord", ChordConfig.class));
    this.chordProperty.set(new ChordConfig());
    this.padProperty = add(new BeanProperty<>("pad", PadConfig.class));
    this.padProperty.set(new PadConfig());
    this.metronomeProperty = add(new BeanProperty<>("metronome", MetronomeConfig.class));
    this.metronomeProperty.set(new MetronomeConfig());
    this.menuProperty = add(new BeanProperty<>("menu", MenuConfig.class));
    this.menuProperty.set(new MenuConfig());
  }

  public ChordConfig getChord() {

    return this.chordProperty.getValue();
  }

  public PadConfig getPad() {

    return this.padProperty.getValue();
  }

  public MetronomeConfig getMetronome() {

    return this.metronomeProperty.getValue();
  }

  public MenuConfig getMenu() {

    return this.menuProperty.getValue();
  }
}
