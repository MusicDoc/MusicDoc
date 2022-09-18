package io.github.musicdoc.instrument.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.musicdoc.AbstractMusicalObject;
import io.github.musicdoc.MutableObject;
import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.tone.Tone;

/**
 * Implementation of {@link StringTuning} as {@link MutableObject}.
 */
public class SimpleStringTuning extends AbstractMusicalObject
    implements StringTuning, MutableObject<SimpleStringTuning> {

  private List<Tone> strings;

  private boolean immutable;

  /**
   * The constructor.
   */
  public SimpleStringTuning() {

    super();
    this.strings = new ArrayList<>();
  }

  /**
   * The constructor.
   *
   * @param strings the {@link #getStrings() string tones}.
   */
  public SimpleStringTuning(Tone... strings) {

    super();
    this.strings = new ArrayList<>(strings.length);
    for (Tone string : strings) {
      this.strings.add(string);
    }
  }

  /**
   * The copy-constructor.
   *
   * @param tuning the {@link StringTuning} to copy.
   */
  public SimpleStringTuning(StringTuning tuning) {

    super();
    this.strings = new ArrayList<>(tuning.getStrings());
  }

  @Override
  public SimpleStringTuning copy(MutableObjecteCopier copier) {

    return new SimpleStringTuning(this);
  }

  @Override
  public List<Tone> getStrings() {

    return this.strings;
  }

  @Override
  public boolean isImmutable() {

    return this.immutable;
  }

  @Override
  public SimpleStringTuning makeImmutable() {

    if (!this.immutable) {
      this.strings = Collections.unmodifiableList(this.strings);
      this.immutable = true;
    }
    return this;
  }

  @Override
  public void toString(StringBuilder sb) {

    sb.append('<');
    String infix = "";
    for (Tone tone : this.strings) {
      sb.append(infix);
      tone.toString(sb);
      infix = "|";
    }
    sb.append('>');
  }

}
