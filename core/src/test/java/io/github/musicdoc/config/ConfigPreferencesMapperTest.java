package io.github.musicdoc.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.musicdoc.bean.BeanPreferencesMapper;
import io.github.musicdoc.property.Property;

public class ConfigPreferencesMapperTest extends Assertions {

  @Test
  public void testSavePreferences() {

    // given
    AppConfig config = new AppConfig();
    ChordConfig chordConfig = config.getChord();
    chordConfig.showCapoAsNumerals.set(true);
    Map<String, Object> map = new HashMap<>();
    DummyPreferences dummyPreferences = new DummyPreferences(map);
    Collection<Property<?>> props = config.getProperties(true, false);
    BeanPreferencesMapper preferences = new BeanPreferencesMapper(config, dummyPreferences);

    // when
    preferences.save();

    // then
    assertThat(map).hasSize(props.size());
    for (Property<?> p : props) {
      Object value = dummyPreferences.get(p.getName(), null);
      assertThat(value).as(p.getName()).isEqualTo(p.getValueOrDefault());
    }
  }

  @Test
  public void testLoadPreferences() {

    // given
    AppConfig originalConfig = new AppConfig();
    ChordConfig chordConfig = originalConfig.getChord();
    chordConfig.showChords.set(false);
    chordConfig.showCapoChords.set(true);
    chordConfig.showNativeAndCapoChords.set(true);
    chordConfig.showCapoAsNumerals.set(true);
    Map<String, Object> map = new HashMap<>();
    Collection<Property<?>> originalProperties = originalConfig.getProperties(true, false);
    for (Property<?> p : originalProperties) {
      map.put(p.getName(), p.getValue());
    }
    DummyPreferences dummyPreferences = new DummyPreferences(map);
    AppConfig config = new AppConfig();
    BeanPreferencesMapper preferences = new BeanPreferencesMapper(config, dummyPreferences);

    // when
    preferences.load();

    // then
    for (Property<?> originalProperty : originalProperties) {
      Property<?> property = config.getProperty(originalProperty.getName(), true);
      assertThat(property.getValue()).isEqualTo(originalProperty.getValue());
    }
  }
}
