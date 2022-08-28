package io.github.musicdoc.rythm.value;

import io.github.musicdoc.decoration.MusicalDecoration;
import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.rythm.fraction.Fraction;

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

    // TODO
    if (in.expect('>')) {
      return MusicalValue._1_2;
    } else if (in.expect('<')) {
      return MusicalValueVariation.PUNCTURED;
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
    char c = in.peek();
    if (c == '>') {
      String lookahead = in.peek(3);
      if (lookahead.equals(">>>")) {

      } else {
        lookahead = lookahead.substring(0, 2);
        if (lookahead.equals(">>>")) {

        }
      }
      assert (item.getValue().getVariation() == MusicalValueVariation.NONE);
      item = item.setValueVariation(MusicalValueVariation.PUNCTURED);
    } else if (c == '<') {

    }
    return item;
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
