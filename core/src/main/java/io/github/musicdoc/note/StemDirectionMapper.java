package io.github.musicdoc.note;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import io.github.mmm.scanner.CharStreamScanner;
import io.github.musicdoc.format.AbstractMapper;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * {@link AbstractMapper} for {@link StemDirection}.
 */
public abstract class StemDirectionMapper extends AbstractMapper<StemDirection> {

  private final Map<StemDirection, String> map;

  /**
   * The constructor.
   */
  public StemDirectionMapper() {

    super();
    this.map = new HashMap<>();
    addMappings();
  }

  /**
   * @param stemDirection tje {@link StemDirection} to register.
   * @param key the format key to associate.
   */
  protected void add(StemDirection stemDirection, String key) {

    this.map.put(stemDirection, key);
  }

  /**
   * Method that {@link #add(StemDirection, String) adds the mappings}. Can be overridden to extend or replace.
   */
  protected void addMappings() {

    add(StemDirection.UP, "up");
    add(StemDirection.DOWN, "down");
    add(StemDirection.AUTO, "auto");
  }

  /**
   * @param value the {@link StemDirection} as string.
   * @param context the {@link SongFormatContext}.
   * @return the parsed {@link StemDirection}.
   */
  public StemDirection parseValue(String value, SongFormatContext context) {

    for (Entry<StemDirection, String> entry : this.map.entrySet()) {
      if (entry.getValue().equals(value)) {
        return entry.getKey();
      }
    }
    return null;
  }

  @Override
  public StemDirection read(MusicInputStream in, SongFormatContext context) {

    CharStreamScanner scanner = in.getScanner();
    for (Entry<StemDirection, String> entry : this.map.entrySet()) {
      if (scanner.expect(entry.getValue(), false)) {
        return entry.getKey();
      }
    }
    in.addWarning("Undefined steam direction.");
    return null;
  }

  /**
   * @param stemDirection the {@link StemDirection} to format.
   * @param context the {@link SongFormatContext}.
   * @return the formatted value.
   */
  public String formatValue(StemDirection stemDirection, SongFormatContext context) {

    return this.map.get(stemDirection);
  }

  @Override
  public void write(StemDirection stemDirection, MusicOutputStream out, SongFormatContext context) {

    String value = formatValue(stemDirection, context);
    if (value != null) {
      out.write(value);
    }
  }

}
