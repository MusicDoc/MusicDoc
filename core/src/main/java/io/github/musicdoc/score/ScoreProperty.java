package io.github.musicdoc.score;

import io.github.musicdoc.property.AbstractProperty;
import io.github.musicdoc.property.Property;

/**
 * Implementation of {@link Property} with {@link #getValue() value} of type {@link Score}.
 */
public class ScoreProperty extends AbstractProperty<Score> {

  private Score value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   */
  public ScoreProperty(String name) {

    this(name, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   * @param value the {@link #getValue() property value}.
   */
  public ScoreProperty(String name, Score value) {

    super(name);
    this.value = value;
  }

  @Override
  public Class<Score> getType() {

    return Score.class;
  }

  @Override
  public Score getValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(Score newValue) {

    this.value = newValue;
  }

  @Override
  protected Score parseValue(String valueAsString) {

    return ScoreMapperMusicDoc.INSTANCE.read(valueAsString);
  }
}
