package io.github.musicdoc.bar;

import io.github.mmm.base.filter.ListCharFilter;
import io.github.mmm.scanner.CharStreamScanner;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * Basic implementation of {@link BarLineTypeMapper}.
 */
public abstract class BarLineTypeMapperBase extends BarLineTypeMapper {

  private final BarLineTypeNode startNode;

  private BarLineTypeNode endNode;

  private ListCharFilter startFilter;

  /**
   * The constructor.
   */
  public BarLineTypeMapperBase() {

    super();
    this.startNode = new BarLineTypeNode();
    this.endNode = this.startNode;
    this.startFilter = new ListCharFilter(new char[0]);
    registerMappings();
  }

  @Override
  public ListCharFilter getStartFilter() {

    return this.startFilter;
  }

  /**
   * @param syntax the syntax (typically {@link BarLineType#getSyntax()}).
   * @param type the according {@link BarLineType}.
   */
  protected final void addMapping(String syntax, BarLineType type) {

    if ((syntax == null) || (syntax.isEmpty())) {
      throw new IllegalArgumentException(syntax);
    }
    assert (this.startNode.verify(syntax));
    this.startFilter = this.startFilter.join(syntax.charAt(0));
    this.endNode = this.endNode.append(syntax, type);
  }

  /**
   * Registers the mappings via {@link #addMapping(String, BarLineType)}.
   */
  protected void registerMappings() {

    for (BarLineType type : BarLineType.values()) {
      addMapping(type.getSyntax(), type);
    }
  }

  @Override
  public BarLineType read(MusicInputStream in, SongFormatContext context) {

    CharStreamScanner scanner = in.getScanner();
    char c = scanner.peek();
    if (this.startFilter.accept(c)) {
      BarLineTypeNode node = this.startNode.next;
      while (node != null) {
        if ((node.syntax.charAt(0) == c) && scanner.expect(node.syntax, false)) {
          return node.type;
        }
        node = node.next;
      }
    }
    return null;
  }

  @Override
  public void write(BarLineType barType, MusicOutputStream out, SongFormatContext context) {

    if (barType == null) {
      return;
    }
    String syntax = this.startNode.getSyntax(barType);
    if (syntax == null) {
      syntax = barType.getSyntax();
    }
    out.write(syntax);
  }

  private static class BarLineTypeNode {

    private final String syntax;

    private final BarLineType type;

    private BarLineTypeNode next;

    private BarLineTypeNode() {

      this(null, null);
    }

    private String getSyntax(BarLineType barType) {

      if (this.type == barType) {
        return this.syntax;
      } else if (this.next != null) {
        return this.next.getSyntax(barType);
      }
      return null;
    }

    private boolean verify(String syntax2) {

      if ((this.syntax != null) && syntax2.startsWith(this.syntax)) {
        throw new IllegalStateException("Illegal mapping ('" + this.syntax + "', '" + syntax2 + "').");
      } else if (this.next != null) {
        return this.next.verify(syntax2);
      }
      return true;
    }

    private BarLineTypeNode append(String syntax2, BarLineType type2) {

      this.next = new BarLineTypeNode(syntax2, type2);
      return this.next;
    }

    private BarLineTypeNode(String syntax, BarLineType type) {

      super();
      this.syntax = syntax;
      this.type = type;
    }

  }
}
