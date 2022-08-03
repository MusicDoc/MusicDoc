package io.github.musicdoc.music.rythm.value;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.decoration.MusicalDecoration;
import io.github.musicdoc.music.decoration.SlurDecoration;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatContext;
import io.github.musicdoc.music.note.Note;
import io.github.musicdoc.music.rythm.Fraction;
import io.github.musicdoc.music.rythm.rest.Rest;

/**
 * {@link AbstractMapper Mapper} for {@link ValuedItem}.
 */
public abstract class ValuedItemMapperBase extends ValuedItemMapper {

  @Override
  public ValuedItem<?> read(MusicInputStream in, SongFormatContext context) {

    MusicalDecoration decoration = readItemPrefix(in, context);
    Fraction variation = readValuePrefix(in, context);
    ValuedItem<?> item = getNoteMapper().read(in, context);
    if (item == null) {
      item = getRestMapper().read(in, context);
    }
    if (item == null) {
      assert (decoration == null);
      assert (variation == null);
    } else {
      if (decoration != null) {
        item.getDecorations().add(decoration);
      }
      if (variation != null) {
        MusicalValue value = item.getValue();
        if (variation instanceof MusicalValueVariation) {
          assert (value.getVariation() == MusicalValueVariation.NONE);
          value = value.setVariation((MusicalValueVariation) variation);
        } else if (variation.getBeats() == 1) {
          value = value.setFraction(value.getFraction() * variation.getFraction());
        }
        item = item.setValue(value);
      }
      item = readItemSuffix(item, in, context);
    }
    return item;
  }

  /**
   * @param in the {@link MusicInputStream} to read from.
   * @param context the {@link SongFormatContext}.
   * @return the {@link MusicalDecoration} to add or {@code null} for none.
   */
  protected Fraction readValuePrefix(MusicInputStream in, SongFormatContext context) {

    return null;
  }

  /**
   * @param in the {@link MusicInputStream} to read from.
   * @param context the {@link SongFormatContext}.
   * @return the {@link MusicalDecoration} to add or {@code null} for none.
   */
  protected MusicalDecoration readItemPrefix(MusicInputStream in, SongFormatContext context) {

    if (in.expect('(')) {
      return SlurDecoration.SLUR_START;
    }
    return null;
  }

  /**
   * @param item the {@link ValuedItem} that has been parsed so far and may be decorated.
   * @param in the {@link MusicInputStream} to read from.
   * @param context the {@link SongFormatContext}.
   * @return the modified {@link ValuedItem}.
   */
  protected ValuedItem<?> readItemSuffix(ValuedItem<?> item, MusicInputStream in, SongFormatContext context) {

    if (in.expect(')')) {
      item.getDecorations().add(SlurDecoration.SLUR_END);
    }
    return item;
  }

  @Override
  public void write(ValuedItem<?> item, MusicOutputStream out, SongFormatContext context) {

    if (item instanceof Note) {
      getNoteMapper().write((Note) item, out, context);
    } else if (item instanceof Rest) {
      getRestMapper().write((Rest) item, out, context);
    }
  }
}