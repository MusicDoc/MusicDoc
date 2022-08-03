package io.github.musicdoc.rythm.value;

import io.github.musicdoc.decoration.MusicalDecoration;
import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.rythm.Fraction;

/**
 * {@link ValuedItemMapper} for {@link SongFormatAbc}.
 */
public class ValuedItemMapperAbc extends ValuedItemMapperBase {

  /** The singleton instance. */
  public static final ValuedItemMapperAbc INSTANCE = new ValuedItemMapperAbc();

  /**
   * The constructor.
   */
  protected ValuedItemMapperAbc() {

    super();
  }

  @Override
  protected Fraction readValuePrefix(MusicInputStream in, SongFormatContext context) {

    if (in.expect('>')) {
      return MusicalValue._1_2;
    }
    return super.readValuePrefix(in, context);
  }

  @Override
  protected MusicalDecoration readItemPrefix(MusicInputStream in, SongFormatContext context) {

    return super.readItemPrefix(in, context);
  }

  @Override
  protected ValuedItem<?> readItemSuffix(ValuedItem<?> item, MusicInputStream in, SongFormatContext context) {

    item = super.readItemSuffix(item, in, context);
    if (in.peek() == '>') {
      assert (item.getValue().getVariation() == MusicalValueVariation.NONE);
      item = item.setValueVariation(MusicalValueVariation.PUNCTURED);

    }
    return item;
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
