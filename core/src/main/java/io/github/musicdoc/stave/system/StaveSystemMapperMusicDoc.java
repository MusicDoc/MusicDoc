package io.github.musicdoc.stave.system;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.format.SongFormatMusicDoc;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.stave.Stave;
import io.github.musicdoc.stave.StaveBracket;

/**
 * {@link StaveSystemMapper} for {@link SongFormatAbc}.
 */
public class StaveSystemMapperMusicDoc extends StaveSystemMapper {

  private static final String PREFIX_STAVE_SYSTEM = "$:";

  /** The singleton instance. */
  public static final StaveSystemMapperMusicDoc INSTANCE = new StaveSystemMapperMusicDoc();

  @Override
  public StaveSystem read(MusicInputStream in, SongFormatContext context) {

    if (!in.expect(PREFIX_STAVE_SYSTEM, false)) {
      return null;
    }
    StaveSystemState state = new StaveSystemState(false);
    char c;
    while (in.hasNext()) {
      in.skipWhile(' ');
      c = in.peek();
      if ((c == '{') || (c == '[')) {
        in.next();
        state.start(StaveBracket.ofStart(c), in);
        if (in.expect('"')) {
          String name = in.readUntil('"', false);
          if (in.expect('"', true)) {
            state.getSystem().setName(name);
          }
        }

      } else if ((c == ']') || (c == '}')) {
        in.next();
        state.end(StaveBracket.ofEnd(c), in);
      } else if (c == NEWLINE_CHAR) {
        in.next();
        break;
      } else {
        Stave stave = getStaveMapper().read(in, context);
        if (stave != null) {
          state.addStave(stave);
        }
      }
    }
    StaveSystem system = state.getTopSystem(in);
    assert (system != null);
    system.makeImmutable();
    context.setStaveSystem(system);
    return system;
  }

  @Override
  public void write(StaveSystem system, MusicOutputStream out, SongFormatContext context) {

    if (system == null) {
      return;
    }
    out.write(PREFIX_STAVE_SYSTEM);
    writeRecursive(system, out, context);
  }

  private void writeRecursive(StaveSystem system, MusicOutputStream out, SongFormatContext context) {

    StaveBracket bracket = system.getBracket();
    if ((bracket != null) && (bracket != StaveBracket.NONE)) {
      out.write(bracket.getStart());
    }
    if (system.isMultiple()) {
      for (StaveSystem child : system.getChildren()) {
        writeRecursive(child, out, context);
      }
    } else {
      Stave stave = system.getStave();
      assert (stave != null);
      getStaveMapper().write(stave, out, context);
    }
    if ((bracket != null) && (bracket != StaveBracket.NONE)) {
      out.write(bracket.getEnd());
    }
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
