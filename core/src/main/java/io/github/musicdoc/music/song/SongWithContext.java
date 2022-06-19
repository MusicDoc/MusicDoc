package io.github.musicdoc.music.song;

import io.github.musicdoc.AppContext;
import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;
import io.github.musicdoc.music.partiture.Partiture;
import io.github.musicdoc.property.Property;
import io.github.musicdoc.property.listener.PropertyChangeListener;

public class SongWithContext extends Song {

  private final AppContext context;

  private Partiture partiture;

  public SongWithContext(AppContext context) {

    super();
    this.context = context;
    init();
  }

  public SongWithContext(AppContext context, Song song) {

    super(song);
    this.context = context;
    init();
  }

  private void init() {

    PropertyChangeListener<Object> listener = new PropertyChangeListener<>() {
      @Override
      public void onChange(Property<? extends Object> property, Object oldValue, Object newValue) {

        SongWithContext.this.partiture = null;
      }
    };
    this.lyrics.addListener(listener);
  }

  public AppContext getContext() {

    return this.context;
  }

  public Partiture getPartiture() {

    if (this.partiture == null) {
      String lyrics = this.lyrics.getValue();
      if (lyrics == null) {
        return null;
      }
      SongFormat format = SongFormatMusicDoc.INSTANCE;
      this.partiture = format.parse(lyrics);
    }
    return this.partiture;
  }
}
