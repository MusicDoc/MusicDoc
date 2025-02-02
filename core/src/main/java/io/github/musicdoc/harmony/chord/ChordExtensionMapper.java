package io.github.musicdoc.harmony.chord;

import java.util.Locale;

import io.github.mmm.base.filter.ListCharFilter;
import io.github.mmm.scanner.CharStreamScanner;
import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * {@link AbstractMapper Mapper} for {@link ChordExtension}.
 */
public abstract class ChordExtensionMapper extends AbstractMapper<ChordExtension> {

  private static final ListCharFilter EXT_FILTER = new ListCharFilter("majorindsuMAJORINDSU0123456789+Δ°");

  @Override
  public ChordExtension read(MusicInputStream in, SongFormatContext context) {

    CharStreamScanner scanner = in.getScanner();
    String lookahead = scanner.peekWhile(EXT_FILTER, 6);
    lookahead = lookahead.toLowerCase(Locale.US);
    for (int i = lookahead.length(); i > 0; i--) {
      String name = lookahead.substring(0, i);
      ChordExtension ext = ChordExtension.of(name);
      if (ext != null) {
        scanner.skip(i);
        return ext;
      }
    }
    return null;
  }

  @Override
  public void write(ChordExtension extension, MusicOutputStream out, SongFormatContext context) {

    if (extension == null) {
      return;
    }
    if (context.isNormalizeChordExtensions()) {
      extension = extension.getReference();
    }
    out.write(extension.getName());
  }
}
