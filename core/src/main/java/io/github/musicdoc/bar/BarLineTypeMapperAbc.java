package io.github.musicdoc.bar;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;

/**
 * {@link BarLineTypeMapper} for {@link SongFormatAbc}.
 */
public class BarLineTypeMapperAbc extends BarLineTypeMapperBase {

  /** The singleton instance. */
  public static final BarLineTypeMapperAbc INSTANCE = new BarLineTypeMapperAbc();

  /**
   * The constructor.
   */
  protected BarLineTypeMapperAbc() {

    super();
  }

  @Override
  protected void registerMappings() {

    // addMapping("[]", BarLineType.THICK_THICK); // does not exist in spec.
    addMapping("|]", BarLineType.THIN_THICK);
    addMapping("[|", BarLineType.THICK_THIN);
    addMapping("::", BarLineType.REPEAT_END_START_0);
    addMapping(":|:", BarLineType.REPEAT_END_START_1);
    addMapping(":||:", BarLineType.REPEAT_END_START_2);
    addMapping("|:", BarLineType.REPEAT_START);
    addMapping(":|", BarLineType.REPEAT_END);
    addMapping("||", BarLineType.THIN_THIN);
    addMapping("|", BarLineType.THIN);
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
