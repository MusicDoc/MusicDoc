/*
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/**
 * Provides the MusicDoc core library.
 */
module io.github.musicdoc {

  requires transitive io.github.mmm.orm;

  requires transitive io.github.mmm.orm.spi;

  requires transitive io.github.mmm.orm.memory;

  // requires transitive io.github.mmm.entity.repository.memory;

  requires transitive java.xml;

  exports io.github.musicdoc;

  exports io.github.musicdoc.album;

  exports io.github.musicdoc.artist;

  exports io.github.musicdoc.bar;

  exports io.github.musicdoc.bean;

  exports io.github.musicdoc.clef;

  exports io.github.musicdoc.config;

  exports io.github.musicdoc.decoration;

  exports io.github.musicdoc.entity;

  exports io.github.musicdoc.format;

  exports io.github.musicdoc.genre;

  exports io.github.musicdoc.glyphs;

  exports io.github.musicdoc.glyphs.smufl;

  exports io.github.musicdoc.glyphs.unicode;

  exports io.github.musicdoc.harmony;

  exports io.github.musicdoc.harmony.chord;

  exports io.github.musicdoc.harmony.key;

  exports io.github.musicdoc.instrument;

  exports io.github.musicdoc.instrument.brass;

  exports io.github.musicdoc.instrument.keyboard;

  exports io.github.musicdoc.instrument.string;

  exports io.github.musicdoc.instrument.woodwind;

  exports io.github.musicdoc.interval;

  exports io.github.musicdoc.io;

  exports io.github.musicdoc.note;

  exports io.github.musicdoc.number;

  exports io.github.musicdoc.property;

  exports io.github.musicdoc.rhythm.fraction;

  exports io.github.musicdoc.rhythm.item;

  exports io.github.musicdoc.rhythm.metre;

  exports io.github.musicdoc.rhythm.punctuation;

  exports io.github.musicdoc.rhythm.rest;

  exports io.github.musicdoc.rhythm.tempo;

  exports io.github.musicdoc.rhythm.tuplet;

  exports io.github.musicdoc.rhythm.value;

  exports io.github.musicdoc.score;

  exports io.github.musicdoc.score.cell;

  exports io.github.musicdoc.score.line;

  exports io.github.musicdoc.score.section;

  exports io.github.musicdoc.search;

  exports io.github.musicdoc.song;

  exports io.github.musicdoc.stave;

  exports io.github.musicdoc.stave.activity;

  exports io.github.musicdoc.stave.system;

  exports io.github.musicdoc.stave.voice;

  exports io.github.musicdoc.tab;

  exports io.github.musicdoc.tone;

  exports io.github.musicdoc.tone.pitch;

  exports io.github.musicdoc.transpose;

  exports io.github.musicdoc.volta;

}
