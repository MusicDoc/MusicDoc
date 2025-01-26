package io.github.musicdoc.score;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.object.SimpleProperty;

/**
 * {@link SimpleProperty} with {@link #get() value} of {@link #getValueClass() type} {@link Score}.
 */
public final class ScoreProperty extends SimpleProperty<Score> {

  private Score value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   * @param value the {@link #get() property value}.
   */
  public ScoreProperty(String name, Score value) {

    super(name);
    this.value = value;
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public ScoreProperty(String name, PropertyMetadata<Score> metadata) {

    super(name, metadata);
  }

  @Override
  public Class<Score> getValueClass() {

    return Score.class;
  }

  @Override
  public Score doGet() {

    return this.value;
  }

  @Override
  protected void doSet(Score newValue) {

    this.value = newValue;
  }

  @Override
  public Score parse(String valueAsString) {

    throw new UnsupportedOperationException();
  }

  @Override
  public Score getFallbackSafeValue() {

    return new Score();
  }

  @Override
  public Score getSafe() {

    Score score = get();
    if (score == null) {
      score = new Score();
      set(score);
    }
    return score;
  }
}
