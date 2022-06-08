package io.github.musicdoc.music.tone;

import java.io.IOException;

import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatOptions;
import io.github.musicdoc.parser.CharStream;

/**
 * {@link AbstractMapper Mapper} for {@link Tone}.
 */
public class ToneMapper extends AbstractMapper<Tone> {

  /** The singleton instance. */
  public static final ToneMapper INSTANCE = new ToneMapper();

  @Override
  public Tone parse(CharStream chars) {

    return parse(chars, 4);
  }

  /**
   * @param chars the {@link CharStream} to parse.
   * @param clefOctave the clef octave - see {@link Tone#getNameAbc(ToneNameStyle, int)}.
   * @return the parsed {@link Tone}
   * @see #parse(CharStream)
   */
  public Tone parse(CharStream chars, int clefOctave) {

    TonePitch pitch = TonePitchMapper.INSTANCE.parse(chars);
    if (pitch == null) {
      return null;
    }
    int octave = clefOctave;
    if (pitch.isLowercase()) {
      octave++;
    }
    while (true) {
      char c = chars.peek();
      if (c == Tone.OCTAVE_UP) {
        octave++;
      } else if (c == Tone.OCTAVE_DOWN) {
        octave--;
      } else {
        break;
      }
      chars.next();
    }
    return Tone.of(pitch, octave);
  }

  @Override
  public void format(Tone tone, Appendable buffer, SongFormatOptions options) throws IOException {

    buffer.append(tone.getName());
  }
}
