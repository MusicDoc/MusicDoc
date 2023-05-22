package io.github.musicdoc.stave.voice;

import java.util.Objects;

import io.github.mmm.base.filter.CharFilter;
import io.github.mmm.scanner.CharStreamScanner;
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

    CharStreamScanner scanner = in.getScanner();
    scanner.skipWhile(' ');
    String id = scanner.readWhile(CharFilter.LATIN_LETTER_OR_DIGIT);
    assert ((id != null) && (id.length() > 0));
    scanner.skipWhile(' ');
    StaveVoice voice;
    if (scanner.expectOne('=')) {
      scanner.skipWhile(' ');
      String name = scanner.readWhile(CharFilter.LATIN_LETTER_OR_DIGIT);
      scanner.skipWhile(' ');
      String abbreviation;
      if (scanner.expectOne('=')) {
        scanner.skipWhile(' ');
        abbreviation = scanner.readWhile(CharFilter.LATIN_LETTER_OR_DIGIT);
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
      scanner.skipWhile(' ');
      progress = false;
      if (instrument == null) {
        if (scanner.expectOne(':')) {
          scanner.skipWhile(' ');
          instrument = getInstrumentMapper().read(in, context);
        }
        if (instrument != null) {
          voice = voice.setInstrument(instrument);
          progress = true;
        }
      }
      if (octaveShift == null) {
        if (scanner.expectOne('o')) {
          octaveShift = scanner.readInteger();
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
        if (scanner.expectOne('t')) {
          transpose = scanner.readInteger();
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
    if ((buildIn == null) || !Objects.equals(name, buildIn.getName())
        || !Objects.equals(abbreviation, buildIn.getAbbreviation())) {
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
