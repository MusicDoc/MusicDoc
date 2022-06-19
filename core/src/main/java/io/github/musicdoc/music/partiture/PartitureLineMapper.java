package io.github.musicdoc.music.partiture;

import java.io.IOException;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.MusicFormatOptions;
import io.github.musicdoc.music.partiture.comment.PartitureCommentLine;
import io.github.musicdoc.music.partiture.comment.PartitureCommentLineMapper;
import io.github.musicdoc.music.partiture.voice.PartitureVoiceLine;
import io.github.musicdoc.music.partiture.voice.PartitureVoiceLineMapper;
import io.github.musicdoc.parser.CharStream;

/**
 * {@link AbstractMapper Mapper} for {@link PartitureLine}.
 */
public abstract class PartitureLineMapper extends AbstractMapper<PartitureLine<?, ?>> {

  /** {@link PartitureLineMapper} for {@link io.github.musicdoc.format.SongFormatMusicDoc}. */
  public static final PartitureLineMapper MUSIC_DOC = PartitureLineMapperMusicDoc.INSTANCE;

  /** {@link PartitureLineMapper} for {@link io.github.musicdoc.format.SongFormatOpenSong}. */
  public static final PartitureLineMapper OPEN_SONG = PartitureLineMapperOpenSong.INSTANCE;

  @Override
  public PartitureLine<?, ?> parse(CharStream chars) {

    PartitureCommentLine comment = PartitureCommentLineMapper.INSTANCE.parse(chars);
    if (comment != null) {
      return comment;
    }
    if (chars.skipNewline()) {
      return PartitureEmptyLine.INSTANCE;
    }
    return getVoiceLineMapper().parse(chars);
  }

  /**
   * @return the {@link PartitureVoiceLineMapper}.
   */
  protected abstract PartitureVoiceLineMapper getVoiceLineMapper();

  @Override
  public void format(PartitureLine<?, ?> line, Appendable buffer, MusicFormatOptions options) throws IOException {

    if (line instanceof PartitureVoiceLine) {
      getVoiceLineMapper().format((PartitureVoiceLine) line, buffer, options);
    } else if (line instanceof PartitureCommentLine) {
      PartitureCommentLineMapper.INSTANCE.format((PartitureCommentLine) line, buffer, options);
    } else if (line instanceof PartitureEmptyLine) {
      // nothing to do
      buffer.append(NEWLINE);
    } else {
      throw new IllegalStateException(line.getClass().getName());
    }
  }

}
