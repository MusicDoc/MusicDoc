package io.github.musicdoc.bar;

import io.github.mmm.base.filter.ListCharFilter;
import io.github.musicdoc.format.AbstractMapper;

/**
 * {@link AbstractMapper Mapper} for {@link BarLineType}.
 */
public abstract class BarLineTypeMapper extends AbstractMapper<BarLineType> {

  /**
   * @return the {@link ListCharFilter} {@link ListCharFilter#accept(char) accepting} the characters to start a
   *         bar-line.
   */
  public abstract ListCharFilter getStartFilter();

}
