package io.github.musicdoc.music.harmony.chord;

import java.util.Locale;

import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.AbstractMapper;
import io.github.musicdoc.music.format.SongFormatOptions;

/**
 * {@link AbstractMapper Mapper} for {@link ChordExtension}.
 */
public abstract class ChordExtensionMapper extends AbstractMapper<ChordExtension> {

  private static final ListCharFilter EXT_FILTER = ListCharFilter.allOfAnyCase("MAJORINDSU0123456789+Δ°");

  @Override
  public ChordExtension parse(MusicInputStream chars, SongFormatOptions options) {

    String lookahead = chars.peekWhile(EXT_FILTER, 6);
    lookahead = lookahead.toLowerCase(Locale.US);
    for (int i = lookahead.length(); i > 0; i--) {
      String name = lookahead.substring(0, i);
      ChordExtension ext = ChordExtension.of(name);
      if (ext != null) {
        chars.skip(i);
        return ext;
      }
    }
    return null;
  }

  @Override
  public void format(ChordExtension extension, MusicOutputStream out, SongFormatOptions options) {

    if (extension == null) {
      return;
    }
    if (options.isNormalizeChordExtensions()) {
      extension = extension.getReference();
    }
    out.append(extension.getName());
  }
}
