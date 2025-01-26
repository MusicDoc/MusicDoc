package io.github.musicdoc.format;

import io.github.mmm.entity.link.Link;
import io.github.musicdoc.entity.MusicalEntity;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * {@link AbstractMapper Mapper} for the {@link MusicalEntity#Title() title} of a {@link MusicalEntity} associated via a
 * {@link Link}.
 *
 * @param <E> type of the {@link MusicalEntity} to map.
 */
public abstract class TitleMapper<E extends MusicalEntity> extends AbstractMapper<Link<E>> {

  @Override
  public Link<E> read(MusicInputStream in, SongFormatContext context) {

    // String value = in.getScanner().readUntil(NEWLINE_CHAR, true);
    String value = in.getScanner().readUntil(c -> c == NEWLINE_CHAR, true);
    E entity = getByTitle(value);
    Link<E> link = Link.of(entity);
    return link;
  }

  /**
   * @param title the {@link MusicalEntity#Title() title} value.
   * @return the according entity.
   */
  protected abstract E getByTitle(String title);

  @Override
  public void write(Link<E> value, MusicOutputStream out, SongFormatContext context) {

    if (value != null) {
      E target = value.getTarget();
      if (target != null) {
        out.write(target.Title().get());
      }
    }
  }

  @Override
  protected SongFormat getFormat() {

    return null;
  }

}
