package io.github.musicdoc.rhythm.value.variation;

import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.rhythm.metre.Metre;

/**
 * Abstract base implementation of {@link MusicalValueVariationMapper}.
 */
public abstract class MusicalValueVariationMapperBase extends MusicalValueVariationMapper {

  @Override
  public AbstractFractionVariation<?> read(MusicInputStream in, SongFormatContext context) {

    AbstractFractionVariation<?> variation = MusicalValueVariation.NONE;
    TupletContext tc = context.getTupletContext();
    // TODO abc vs. MusicDoc
    // tuplets are annotated as prefix before the item and apply to the following n items
    // therefore ValuedItemMapper[Base] parses them and puts them into the context.
    if (tc != null) {
      int noteCount = tc.getNoteCount();
      if (noteCount > 0) {
        variation = tc.getTuplet();
        tc.decrementNoteCount();
      }
    }
    if (isSupportPunctuation() && in.expect('.')) {
      int dots = 1;
      if (in.expect('.')) {
        dots++;
        if (in.expect('.')) {
          dots++;
        }
      }
      MusicalValueVariation punctuation = Punctuation.of(dots);
      if (variation == MusicalValueVariation.NONE) {
        variation = punctuation;
      } else {
        variation = new ComposedVariation(punctuation, variation);
      }
    }
    if (isSupportTupletSuffix() && in.expect('T')) {
      tc = readTuplet(in, context, false);
      if (tc == null) {
        in.addError("Invalid tuplet");
      } else {
        variation = variation.append(tc.getTuplet());
      }
    }
    return variation;
  }

  /**
   * @param in the {@link MusicInputStream}.
   * @param context the {@link SongFormatContext}.
   * @param supportNoteCount - {@code true} if {@link TupletContext#getNoteCount()} shall be supported, {@code false}
   *        otherwise.
   * @return the parse {@link TupletContext}. Will only be a wrapper for {@link Tuplet} if {@code supportNoteCount} is
   *         {@code false}.
   */
  public static TupletContext readTuplet(MusicInputStream in, SongFormatContext context, boolean supportNoteCount) {

    // "(abc)" annotates a slur for the notes a b c.
    // in "(3a(bc)" the first ( is not a slur but annotates a triplet for the following 3 notes.
    // such tuplets are not closed via ")" in ABC what can cause slight confusions for the reader.
    Integer unitInteger = in.readInteger(2, false);
    if (unitInteger == null) {
      return null;
    }
    int unit = unitInteger.intValue();
    Integer beatsInteger = null;
    int tones = unit;
    if (in.expect(':')) {
      beatsInteger = in.readInteger(2, false);
      if (supportNoteCount && in.expect(':')) {
        Integer tonesInteger = in.readInteger(2, false);
        if (tonesInteger != null) {
          tones = tonesInteger.intValue();
        }
      }
    }
    Tuplet tuplet;
    if (beatsInteger == null) {
      Metre metre = context.getMetre();
      tuplet = Tuplet.of(metre, unit);
    } else {
      tuplet = Tuplet.of(beatsInteger.intValue(), unit);
    }
    return new TupletContext(tuplet, tones);
  }

  @Override
  public void write(AbstractFractionVariation<?> variation, MusicOutputStream out, SongFormatContext context) {

    if (variation instanceof MusicalValueVariation) {
      writePlain((MusicalValueVariation) variation, out, context);
    } else {
      writePlain(variation.getPlain(), out, context);
      write(variation.getVariation(), out, context);
    }
  }

  /**
   * @param variation the {@link AbstractFractionVariation#getPlain() plain} {@link MusicalValueVariation}.
   * @param out the {@link MusicOutputStream}.
   * @param context the {@link SongFormatContext}.
   */
  protected void writePlain(MusicalValueVariation variation, MusicOutputStream out, SongFormatContext context) {

    if ((variation == null) || (variation == MusicalValueVariation.NONE)) {
      return;
    }
    if (!isSupportPunctuation() && (variation instanceof Punctuation)) {
      return;
    }
    out.write(variation.toString());
  }

  /**
   * @return {@code true} if {@link Punctuation}s are supported, {@code false} otherwise.
   */
  protected boolean isSupportPunctuation() {

    return true;
  }

  /**
   * @return {@code true} if {@link Tuplet}s are also supported as repeatable suffix (e.g. "{aT3}{bT3}{cT3}" instead of
   *         "{(3a}{b}{c})" in MusicDoc).
   */
  protected boolean isSupportTupletSuffix() {

    return true;
  }
}
