package io.github.musicdoc.stave.voice;

import java.util.Objects;

import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.format.SongFormatMusicDoc;
import io.github.musicdoc.instrument.Instrument;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * {@link StaveVoiceMapper} for {@link SongFormatMusicDoc}.
 */
public class StaveVoiceMapperMusicDoc extends StaveVoiceMapper {

  /** The singleton instance. */
  public static final StaveVoiceMapperMusicDoc INSTANCE = new StaveVoiceMapperMusicDoc();

  /**
   * The constructor.
   */
  protected StaveVoiceMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

  @Override
  public StaveVoice read(MusicInputStream in, SongFormatContext context) {

    in.skipWhile(' ');
    String id = in.readWhile(ListCharFilter.LETTERS_AND_DIGITS);
    assert ((id != null) && (id.length() > 0));
    in.skipWhile(' ');
    StaveVoice voice;
    if (in.expect('=')) {
      in.skipWhile(' ');
      String name = in.readWhile(ListCharFilter.LETTERS_AND_DIGITS);
      in.skipWhile(' ');
      String abbreviation;
      if (in.expect('=')) {
        in.skipWhile(' ');
        abbreviation = in.readWhile(ListCharFilter.LETTERS_AND_DIGITS);
      } else {
        if (name.isEmpty()) {
          abbreviation = name;
        } else {
          abbreviation = id;
        }
      }
      voice = new StaveVoice(id, name, abbreviation);
    } else {
      voice = StaveVoice.get(id);
    }
    Instrument instrument = null;
    Integer octaveShift = null;
    Integer transpose = null;
    boolean progress = true;
    while (progress) {
      in.skipWhile(' ');
      progress = false;
      if (instrument == null) {
        if (in.expect(':')) {
          in.skipWhile(' ');
          instrument = getInstrumentMapper().read(in, context);
        }
        if (instrument != null) {
          voice = voice.setInstrument(instrument);
          progress = true;
        }
      }
      if (octaveShift == null) {
        if (in.expect('o')) {
          octaveShift = in.readInteger();
          if (octaveShift == null) {
            in.addError("Octave (o) has to be followed by a number.");
          }
        }
        if (octaveShift != null) {
          voice = voice.setOctaveShift(octaveShift.intValue());
          progress = true;
        }
      }
      if (transpose == null) {
        if (in.expect('t')) {
          transpose = in.readInteger();
          if (transpose == null) {
            in.addError("Transpose (t) has to be followed by a number.");
          }
        }
        if (transpose != null) {
          voice = voice.setTranspose(transpose.intValue());
          progress = true;
        }
      }
    }

    return voice;
  }

  @Override
  public void write(StaveVoice voice, MusicOutputStream out, SongFormatContext context) {

    if (voice == null) {
      return;
    }
    String id = voice.getId();
    out.write(id);
    String name = voice.getName();
    String abbreviation = voice.getAbbreviation();
    StaveVoice buildIn = StaveVoice.get(id);
    if ((buildIn == null) || !Objects.equals(name, buildIn.getName()) || !Objects.equals(abbreviation, buildIn.getAbbreviation())) {
      out.write("=");
      out.write(name);
      if (!id.equals(abbreviation)) {
        out.write("=");
        out.write(abbreviation);
      }
    }
    Instrument instrument = voice.getInstrument();
    if (instrument != null) {
      out.write(":");
      getInstrumentMapper().write(instrument, out, context);
    }
    int octaveShift = voice.getOctaveShift();
    if (octaveShift != 0) {
      out.write("o");
      out.write(Integer.toString(octaveShift));
    }
    int transpose = voice.getTranspose();
    if (transpose != 0) {
      out.write("t");
      out.write(Integer.toString(transpose));
    }
  }

}
