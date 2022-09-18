package io.github.musicdoc.rhythm.tuplet;

import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.rhythm.metre.Metre;

/**
 * Abstract base implementation of {@link TupletMapper}.
 */
public abstract class TupletMapperBase extends TupletMapper {

  @Override
  public Tuplet read(MusicInputStream in, SongFormatContext context) {

    // in ABC tuplets are annotated as prefix before the item and apply to the following n items
    // therefore ValuedItemMapper[Base] parses them and puts them into the context.
    TupletContext tc = context.getTupletContext();
    if (tc != null) {
      int noteCount = tc.getItemCount();
      if (noteCount > 0) {
        tc.decrementNoteCount();
        return tc.getTuplet();
      } else {
        context.setTupletContext(null);
      }
    }
    return null;
  }

  /**
   * Reads {@link TupletContext} and has to be called after tuplet indicator ('(' or 'T') has already been consumed.
   *
   * @param in the {@link MusicInputStream}.
   * @param context the {@link SongFormatContext}.
   * @param supportNoteCount - {@code true} if {@link TupletContext#getItemCount()} shall be supported, {@code false}
   *        otherwise.
   * @return the parse {@link TupletContext}. Will only be a wrapper for {@link Tuplet} if {@code supportNoteCount} is
   *         {@code false}.
   */
  public static TupletContext readTupletContext(MusicInputStream in, SongFormatContext context,
      boolean supportNoteCount) {

    // "(abc)" annotates a slur for the notes a b c.
    // in "(3a(bc)" the first ( is not a slur but annotates a triplet for the following 3 notes.
    // such tuplets are not closed via ")" in ABC what can cause slight confusions for the reader.
    Integer unitInteger = in.readInteger(2, false);
    if (unitInteger == null) {
      return null;
    }
    int unit = unitInteger.intValue();
    Integer beatsInteger = null;
    int itemCount = unit;
    if (in.expect(':')) {
      beatsInteger = in.readInteger(2, false);
      if (supportNoteCount && in.expect(':')) {
        Integer tonesInteger = in.readInteger(2, false);
        if (tonesInteger != null) {
          itemCount = tonesInteger.intValue();
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
    return new TupletContext(tuplet, itemCount);
  }

  @Override
  public void write(Tuplet tuplet, MusicOutputStream out, SongFormatContext context) {

  }

  /**
   * @param tc the {@link TupletContext} to write.
   * @param tupletIndicator the character to mark the start of the tuplet (e.g. '(' or 'T').
   * @param out the {@link MusicOutputStream} to write to.
   * @param context the {@link SongFormatContext}.
   */
  public static void writeTupletContext(TupletContext tc, char tupletIndicator, MusicOutputStream out,
      SongFormatContext context) {

    if (tc == null) {
      return;
    }
    writeTuplet(tc.getTuplet(), tc.getItemCount(), tupletIndicator, out, context);
  }

  /**
   * @param tuplet the {@link Tuplet} to write.
   * @param itemCount the {@link TupletContext#getItemCount() itemCount} or {@code 0} to omit.
   * @param tupletIndicator the character to mark the start of the tuplet (e.g. '(' or 'T').
   * @param out the {@link MusicOutputStream} to write to.
   * @param context the {@link SongFormatContext}.
   */
  public static void writeTuplet(Tuplet tuplet, int itemCount, char tupletIndicator, MusicOutputStream out,
      SongFormatContext context) {

    if (tuplet == null) {
      return;
    }
    out.write(tupletIndicator);
    int unit = tuplet.getUnit();
    out.write(unit);
    boolean writeBeats = !tuplet.hasDefaultBeats(context.getMetre());
    if (writeBeats) {
      out.write(':');
      out.write(tuplet.getBeats());
    }
    if ((itemCount > 0) && (itemCount != unit)) {
      if (!writeBeats) {
        out.write(':');
      }
      out.write(':');
      out.write(tuplet.getBeats());
    }
  }

}
