package io.github.musicdoc.genre;

import io.github.mmm.bean.BeanFactory;
import io.github.mmm.entity.bean.TreeNode;
import io.github.mmm.entity.bean.property.EntityWithName;
import io.github.mmm.entity.link.Link;
import io.github.mmm.entity.property.link.LinkProperty;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.booleans.BooleanProperty;
import io.github.mmm.property.container.set.SetProperty;
import io.github.mmm.property.string.StringSetProperty;

/**
 * Genre of a {@link io.github.musicdoc.song.Song}. Please note that while it is common practice to associate songs with
 * one or multiple genres, this approach and its taxonomy is historically grown and rather poor. This can be easily seen
 * when looking at the official <a href="https://en.wikipedia.org/wiki/List_of_ID3v1_genres">ID3 genre standard</a>:
 * <br>
 * A lot of meta-information has been put into genres that is totally misplaced there. E.g. genres such as
 * "instrumental" or "soundtrack"/"trailer" represent meta-information about the song that are independent of its actual
 * style expressed by other genres. Even worse it became practice to define genres as combinations of such aspects such
 * as "Instrumental pop" or "Instrumental rock" while the same could have been done for all other genres building such
 * combinations. Other stupid examples of this anti-pattern are "Christian rap" and "Christian gangsta rap". This
 * clearly indicates that a tagging of aspects such as "Soundtrack", "Christian", "Live", etc. independent of the real
 * genre is much smarter than building all possible combinations of such aspects and styles into a flat list or a tree
 * of genres.<br>
 * Further, there are many fusion genres that combine other genres like "Techno-industrial". This lead to genres like
 * "Acid jazz" that in the end have rather nothing to do with "Acid" or "Jazz" at all. Also the genre "Other" indicates
 * the non-sense of this taxonomy as this is as good as having no genre at all. Also the genre "Top 40" shows this
 * non-sense as it says nothing about the style of the song but only that is once has been very popular what should be
 * expressed by a tag rather than a genre. Finally, there are genres like "Oldies" that are somewhat relative so that
 * the association of this genre might change from one generation to the other (a tagging with "1980s" or "1990s" would
 * be smarter).<br>
 * If you ask two people independently whether a specific song is "Pop", "Rock", "Soft Rock", "Pop Rock", "Folk Rock",
 * or whatever, you will most likely get two different answers. If you find all the genre information about songs,
 * albums, and bands it looks like a solid science but it is just some kind of vague guessing that approximates some
 * agreement after people got tired. A very nice and quite scientific work about musical genres can be found on
 * <a href="https://musicmap.info/">musicmap.info</a>.
 *
 * @see io.github.musicdoc.song.Song#Genre
 */
public interface Genre extends TreeNode<Genre>, EntityWithName {

  /** Primary key of the {@link Genre} "Blues" from ID3v1. */
  int PK_BLUES = 0;

  /** Primary key of the {@link Genre} "Classic Rock" from ID3v1. */
  int PK_CLASSIC_ROCK = 1;

  /** Primary key of the {@link Genre} "Country" from ID3v1. */
  int PK_COUNTRY = 2;

  /** Primary key of the {@link Genre} "Dance" from ID3v1. */
  int PK_DANCE = 3;

  /** Primary key of the {@link Genre} "Disco" from ID3v1. */
  int PK_DISCO = 4;

  /** Primary key of the {@link Genre} "Funk" from ID3v1. */
  int PK_FUNK = 5;

  /** Primary key of the {@link Genre} "Grunge" from ID3v1. */
  int PK_GRUNGE = 6;

  /** Primary key of the {@link Genre} "Hip-Hop" from ID3v1. */
  int PK_HIP_HOP = 7;

  /** Primary key of the {@link Genre} "Jazz" from ID3v1. */
  int PK_JAZZ = 8;

  /** Primary key of the {@link Genre} "Metal" from ID3v1. */
  int PK_METAL = 9;

  /** Primary key of the {@link Genre} "New Age" from ID3v1. */
  int PK_NEW_AGE = 10;

  /** Primary key of the {@link Genre} "Oldies" from ID3v1. */
  int PK_OLDIES = 11;

  /** Primary key of the {@link Genre} "Other" from ID3v1. */
  int PK_OTHER = 12;

  /** Primary key of the {@link Genre} "Pop" from ID3v1. */
  int PK_POP = 13;

  /** Primary key of the {@link Genre} "Rhythm and Blues" from ID3v1. */
  int PK_RNB = 14;

  /** Primary key of the {@link Genre} "Rap" from ID3v1. */
  int PK_RAP = 15;

  /** Primary key of the {@link Genre} "Raggae" from ID3v1. */
  int PK_REGGAE = 16;

  /** Primary key of the {@link Genre} "Rock" from ID3v1. */
  int PK_ROCK = 17;

  /** Primary key of the {@link Genre} "Techno" from ID3v1. */
  int PK_TECHNO = 18;

  /** Primary key of the {@link Genre} "Industrial" from ID3v1. */
  int PK_INDUSTRIAL = 19;

  /** Primary key of the {@link Genre} "Alternative" from ID3v1. */
  int PK_ALTERNATIVE = 20;

  /** Primary key of the {@link Genre} "Ska" from ID3v1. */
  int PK_SKA = 21;

  /** Primary key of the {@link Genre} "Death-Metal" from ID3v1. */
  int PK_DEATH_METAL = 22;

  /** Primary key of the {@link Genre} "Pranks" from ID3v1. */
  int PK_PRANKS = 23;

  /** Primary key of the {@link Genre} "Soundtrack" from ID3v1. */
  int PK_SOUNDTRACK = 24;

  /** Primary key of the {@link Genre} "Euro-Techno" from ID3v1. */
  int PK_EURO_TECHNO = 25;

  /** Primary key of the {@link Genre} "Ambient" from ID3v1. */
  int PK_AMBIENT = 26;

  /** Primary key of the {@link Genre} "Trip-Hop" from ID3v1. */
  int PK_TRIP_HOP = 27;

  /** Primary key of the {@link Genre} "Vocal" from ID3v1. */
  int PK_VOCAL = 28;

  /** Primary key of the {@link Genre} "Jazz & Funk" from ID3v1. */
  int PK_JAZZ_AND_FUNK = 29;

  /** Primary key of the {@link Genre} "Fusion" from ID3v1. */
  int PK_FUSION = 30;

  /** Primary key of the {@link Genre} "Trance" from ID3v1. */
  int PK_TRANCE = 31;

  /** Primary key of the {@link Genre} "Classical" from ID3v1. */
  int PK_CLASSICAL = 32;

  /** Primary key of the {@link Genre} "Instrumental" from ID3v1. */
  int PK_INSTRUMENTAL = 33;

  /** Primary key of the {@link Genre} "Acid" from ID3v1. */
  int PK_ACID = 34;

  /** Primary key of the {@link Genre} "House" from ID3v1. */
  int PK_HOUSE = 35;

  /** Primary key of the {@link Genre} "Game" from ID3v1. */
  int PK_GAME = 36;

  /** Primary key of the {@link Genre} "Sound Clip" from ID3v1. */
  int PK_SOUND_CLIP = 37;

  /** Primary key of the {@link Genre} "Gospel" from ID3v1. */
  int PK_GOSPEL = 38;

  /** Primary key of the {@link Genre} "Noise" from ID3v1. */
  int PK_NOISE = 39;

  /** Primary key of the {@link Genre} "Alternative Rock" from ID3v1. */
  int PK_ALTERNATIVE_ROCK = 40;

  /** Primary key of the {@link Genre} "Bass" from ID3v1. */
  int PK_BASS = 41;

  /** Primary key of the {@link Genre} "Soul" from ID3v1. */
  int PK_SOUL = 42;

  /** Primary key of the {@link Genre} "Punk" from ID3v1. */
  int PK_PUNK = 43;

  /** Primary key of the {@link Genre} "Space" from ID3v1. */
  int PK_SPACE = 44;

  /** Primary key of the {@link Genre} "Meditative" from ID3v1. */
  int PK_MEDITATIVE = 45;

  /** Primary key of the {@link Genre} "Instrumental Pop" from ID3v1. */
  int PK_INSTRUMENTAL_POP = 46;

  /** Primary key of the {@link Genre} "Instrumental Rock" from ID3v1. */
  int PK_INSTRUMENTAL_ROCK = 47;

  /** Primary key of the {@link Genre} "Ethnic" from ID3v1. */
  int PK_ETHNIC = 48;

  /** Primary key of the {@link Genre} "Gothic" from ID3v1. */
  int PK_GOTHIC = 49;

  /** Primary key of the {@link Genre} "Darkwave" from ID3v1. */
  int PK_DARKWAVE = 50;

  /** Primary key of the {@link Genre} "Techno-Industrial" from ID3v1. */
  int PK_TECHNO_INDUSTRIAL = 51;

  /** Primary key of the {@link Genre} "Electronic" from ID3v1. */
  int PK_ELECTRONIC = 52;

  /** Primary key of the {@link Genre} "Pop-Folk" from ID3v1. */
  int PK_POP_FOLK = 53;

  /** Primary key of the {@link Genre} "Eurodance" from ID3v1. */
  int PK_EURO_DANCE = 54;

  /** Primary key of the {@link Genre} "Dream" from ID3v1. */
  int PK_DREAM = 55;

  /** Primary key of the {@link Genre} "Southern Rock" from ID3v1. */
  int PK_SOUTHERN_ROCK = 56;

  /** Primary key of the {@link Genre} "Comedy" from ID3v1. */
  int PK_COMEDY = 57;

  /** Primary key of the {@link Genre} "Cult" from ID3v1. */
  int PK_CULT = 58;

  /** Primary key of the {@link Genre} "Gangsta" from ID3v1. */
  int PK_GANGSTA = 59;

  /** Primary key of the {@link Genre} "Top 40" from ID3v1. */
  int PK_TOP_40 = 60;

  /** Primary key of the {@link Genre} "Christian Rap" from ID3v1. */
  int PK_CHRISTIAN_RAP = 61;

  /** Primary key of the {@link Genre} "Pop/Funk" from ID3v1. */
  int PK_POP_FUNK = 62;

  /** Primary key of the {@link Genre} "Jungle" from ID3v1. */
  int PK_JUNGLE = 63;

  /** Primary key of the {@link Genre} "Native US" from ID3v1. */
  int PK_NATIVE_US = 64;

  /** Primary key of the {@link Genre} "Cabaret" from ID3v1. */
  int PK_CABARET = 65;

  /** Primary key of the {@link Genre} "New Wave" from ID3v1. */
  int PK_NEW_WAVE = 66;

  /** Primary key of the {@link Genre} "Psychedelic" from ID3v1. */
  int PK_PSYCHEDELIC = 67;

  /** Primary key of the {@link Genre} "Rave" from ID3v1. */
  int PK_RAVE = 68;

  /** Primary key of the {@link Genre} "Showtunes" from ID3v1. */
  int PK_SHOWTUNES = 69;

  /** Primary key of the {@link Genre} "Trailer" from ID3v1. */
  int PK_TRAILER = 70;

  /** Primary key of the {@link Genre} "Lo-Fi" from ID3v1. */
  int PK_LO_FI = 71;

  /** Primary key of the {@link Genre} "Tribal" from ID3v1. */
  int PK_TRIBAL = 72;

  /** Primary key of the {@link Genre} "Acid Punk" from ID3v1. */
  int PK_ACID_PUNK = 73;

  /** Primary key of the {@link Genre} "Acid Jazz" from ID3v1. */
  int PK_ACID_JAZZ = 74;

  /** Primary key of the {@link Genre} "Polka" from ID3v1. */
  int PK_POLKA = 75;

  /** Primary key of the {@link Genre} "Retro" from ID3v1. */
  int PK_RETRO = 76;

  /** Primary key of the {@link Genre} "Musical" from ID3v1. */
  int PK_MUSICAL = 77;

  /** Primary key of the {@link Genre} "Rock & Roll" from ID3v1. */
  int PK_ROCK_AND_ROLL = 78;

  /** Primary key of the {@link Genre} "Hard Rock" from ID3v1. */
  int PK_HARD_ROCK = 79;

  /** Primary key of the {@link Genre} "Folk" from ID3v1. */
  int PK_FOLK = 80;

  /** Primary key of the {@link Genre} "Folk Rock" from ID3v1. */
  int PK_FOLK_ROCK = 81;

  /** Primary key of the {@link Genre} "National Folk" from ID3v1. */
  int PK_NATIONAL_FOLK = 82;

  /** Primary key of the {@link Genre} "Swing" from ID3v1. */
  int PK_SWING = 83;

  /** Primary key of the {@link Genre} "Fast Fusion" from ID3v1. */
  int PK_FAST_FUSION = 84;

  /** Primary key of the {@link Genre} "Bebop" from ID3v1. */
  int PK_BEBOP = 85;

  /** Primary key of the {@link Genre} "Latin" from ID3v1. */
  int PK_LATIN = 86;

  /** Primary key of the {@link Genre} "Revival" from ID3v1. */
  int PK_REVIVAL = 87;

  /** Primary key of the {@link Genre} "Celtic" from ID3v1. */
  int PK_CELTIC = 88;

  /** Primary key of the {@link Genre} "Bluegrass" from ID3v1. */
  int PK_BLUEGRASS = 89;

  /** Primary key of the {@link Genre} "Avantgarde" from ID3v1. */
  int PK_AVANTGARDE = 90;

  /** Primary key of the {@link Genre} "Gothic Rock" from ID3v1. */
  int PK_GOTHIC_ROCK = 91;

  /** Primary key of the {@link Genre} "Progressive Rock" from ID3v1. */
  int PK_PROGRESSIVE_ROCK = 92;

  /** Primary key of the {@link Genre} "Psychedelic Rock" from ID3v1. */
  int PK_PSYCHEDELIC_ROCK = 93;

  /** Primary key of the {@link Genre} "Symphonic Rock" from ID3v1. */
  int PK_SYMPHONIC_ROCK = 94;

  /** Primary key of the {@link Genre} "Slow Rock" from ID3v1. */
  int PK_SLOW_ROCK = 95;

  /** Primary key of the {@link Genre} "Big Band" from ID3v1. */
  int PK_BIG_BAND = 96;

  /** Primary key of the {@link Genre} "Chorus" from ID3v1. */
  int PK_CHORUS = 97;

  /** Primary key of the {@link Genre} "Easy Listening" from ID3v1. */
  int PK_EASY_LISTENING = 98;

  /** Primary key of the {@link Genre} "Acoustic" from ID3v1. */
  int PK_ACOUSTIC = 99;

  /** Primary key of the {@link Genre} "Humour" from ID3v1. */
  int PK_HUMOUR = 100;

  /** Primary key of the {@link Genre} "Speech" from ID3v1. */
  int PK_SPEECH = 101;

  /** Primary key of the {@link Genre} "Chanson" from ID3v1. */
  int PK_CHANSON = 102;

  /** Primary key of the {@link Genre} "Opera" from ID3v1. */
  int PK_OPERA = 103;

  /** Primary key of the {@link Genre} "Chamber" (Music) from ID3v1. */
  int PK_CHAMBER = 104;

  /** Primary key of the {@link Genre} "Sonata" from ID3v1. */
  int PK_SONATA = 105;

  /** Primary key of the {@link Genre} "Symphony" from ID3v1. */
  int PK_SYMPHONY = 106;

  /** Primary key of the {@link Genre} "Booty Bass" from ID3v1. */
  int PK_BOOTY_BASS = 107;

  /** Primary key of the {@link Genre} "Primus" from ID3v1. */
  int PK_PRIMUS = 108;

  /** Primary key of the {@link Genre} "Porn Groove" from ID3v1. */
  int PK_PORN_GROOVE = 109;

  /** Primary key of the {@link Genre} "Satire" from ID3v1. */
  int PK_SATIRE = 110;

  /** Primary key of the {@link Genre} "Slow Jam" from ID3v1. */
  int PK_SLOW_JAM = 111;

  /** Primary key of the {@link Genre} "Club" from ID3v1. */
  int PK_CLUB = 112;

  /** Primary key of the {@link Genre} "Tango" from ID3v1. */
  int PK_TANGO = 113;

  /** Primary key of the {@link Genre} "Samba" from ID3v1. */
  int PK_SAMBA = 114;

  /** Primary key of the {@link Genre} "Folklore" from ID3v1. */
  int PK_FOLKLORE = 115;

  /** Primary key of the {@link Genre} "Ballad" from ID3v1. */
  int PK_BALLAD = 116;

  /** Primary key of the {@link Genre} "Power Ballad" from ID3v1. */
  int PK_POWER_BALLAD = 117;

  /** Primary key of the {@link Genre} "Rhythmic Soul" from ID3v1. */
  int PK_RHYTHMIC_SOUL = 118;

  /** Primary key of the {@link Genre} "Freestyle" from ID3v1. */
  int PK_FREESTYLE = 119;

  /** Primary key of the {@link Genre} "Duet" from ID3v1. */
  int PK_DUET = 120;

  /** Primary key of the {@link Genre} "Punk Rock" from ID3v1. */
  int PK_PUNK_ROCK = 121;

  /** Primary key of the {@link Genre} "Drum Solo" from ID3v1. */
  int PK_DRUM_SOLO = 122;

  /** Primary key of the {@link Genre} "A Capella" from ID3v1. */
  int PK_A_CAPELLA = 123;

  /** Primary key of the {@link Genre} "Euro-House" from ID3v1. */
  int PK_EURO_HOUSE = 125;

  /** Primary key of the {@link Genre} "Goa" (Music) from ID3v1. */
  int PK_GOA = 126;

  /** Primary key of the {@link Genre} "Drum & Bass" from ID3v1. */
  int PK_DRUM_AND_BASS = 127;

  /** Primary key of the {@link Genre} "Club House" from ID3v1. */
  int PK_CLUB_HOUSE = 128;

  /** Primary key of the {@link Genre} "Hardcore Techno" from ID3v1. */
  int PK_HARDCORE_TECHNO = 129;

  /** Primary key of the {@link Genre} "Terror" from ID3v1. */
  int PK_TERROR = 130;

  /** Primary key of the {@link Genre} "Indie" from ID3v1. */
  int PK_INDIE = 131;

  /** Primary key of the {@link Genre} "Britpop" from ID3v1. */
  int PK_BRIT_POP = 132;

  /** Primary key of the {@link Genre} "Coloured Punk" from ID3v1. */
  int PK_COLORED_PUNK = 133;

  /** Primary key of the {@link Genre} "Polsk Punk" from ID3v1. */
  int PK_POLSK_PUNK = 134;

  /** Primary key of the {@link Genre} "Beat" from ID3v1. */
  int PK_BEAT = 135;

  /** Primary key of the {@link Genre} "Christian Gangsta Rap" from ID3v1. */
  int PK_CHRISTIAN_GANGSTA_RAP = 136;

  /** Primary key of the {@link Genre} "Heavy Metal" from ID3v1. */
  int PK_HEAVY_METAL = 137;

  /** Primary key of the {@link Genre} "Black Metal" from ID3v1. */
  int PK_BLACK_METAL = 138;

  /** Primary key of the {@link Genre} "Crossover" from ID3v1. */
  int PK_CROSSOVER = 139;

  /** Primary key of the {@link Genre} "Contemporary Christian" from ID3v1. */
  int PK_CONTEMPORARY_CHRISTIAN = 140;

  /** Primary key of the {@link Genre} "Christian Rock" from ID3v1. */
  int PK_CHRISTIAN_ROCK = 141;

  /** Primary key of the {@link Genre} "Merengue" from ID3v1 (Winamp). */
  int PK_MERENGUE = 142;

  /** Primary key of the {@link Genre} "Salsa" from ID3v1 (Winamp). */
  int PK_SALSA = 143;

  /** Primary key of the {@link Genre} "Trash Metal" from ID3v1 (Winamp). */
  int PK_TRASH_METAL = 144;

  /** Primary key of the {@link Genre} "Anime" from ID3v1 (Winamp). */
  int PK_ANIME = 145;

  /** Primary key of the {@link Genre} "Jpop" from ID3v1 (Winamp). */
  int PK_JPOP = 146;

  /** Primary key of the {@link Genre} "Synthpop" from ID3v1 (Winamp). */
  int PK_SYNTH_POP = 147;

  /** Primary key of the {@link Genre} "Christmas" from ID3v1 (Winamp). */
  int PK_CHRISTMAS = 148;

  /** Primary key of the {@link Genre} "Art Rock" from ID3v1 (Winamp). */
  int PK_ART_ROCK = 149;

  /** Primary key of the {@link Genre} "Baroque" from ID3v1 (Winamp). */
  int PK_BAROQUE = 150;

  /** Primary key of the {@link Genre} "Bhangra" from ID3v1 (Winamp). */
  int PK_BHANGRA = 151;

  /** Primary key of the {@link Genre} "Big Beat" from ID3v1 (Winamp). */
  int PK_BIG_BEAT = 152;

  /** Primary key of the {@link Genre} "Breakbeat" from ID3v1 (Winamp). */
  int PK_BREAK_BEAT = 153;

  /** Primary key of the {@link Genre} "Chillout" from ID3v1 (Winamp). */
  int PK_CHILLOUT = 154;

  /** Primary key of the {@link Genre} "Downtempo" from ID3v1 (Winamp). */
  int PK_DOWN_TEMPO = 155;

  /** Primary key of the {@link Genre} "Dub" from ID3v1 (Winamp). */
  int PK_DUB = 156;

  /** Primary key of the {@link Genre} "EBM" (Electronic Body Music) from ID3v1 (Winamp). */
  int PK_EBM = 157;

  /** Primary key of the {@link Genre} "Eclectic" from ID3v1 (Winamp). */
  int PK_ECLECTIC = 158;

  /** Primary key of the {@link Genre} "Electro" from ID3v1 (Winamp). */
  int PK_ELECTRO = 159;

  /** Primary key of the {@link Genre} "Electroclash" from ID3v1 (Winamp). */
  int PK_ELECTRO_CLASH = 160;

  /** Primary key of the {@link Genre} "Emo" from ID3v1 (Winamp). */
  int PK_EMO = 161;

  /** Primary key of the {@link Genre} "Experimental" from ID3v1 (Winamp). */
  int PK_EXPERIMENTAL = 162;

  /** Primary key of the {@link Genre} "Garage" from ID3v1 (Winamp). */
  int PK_GARAGE = 163;

  /** Primary key of the {@link Genre} "Global" from ID3v1 (Winamp). */
  int PK_GLOBAL = 164;

  /** Primary key of the {@link Genre} "IDM" (Intelligent Dance Music) from ID3v1 (Winamp). */
  int PK_IDM = 165;

  /** Primary key of the {@link Genre} "Illbient" from ID3v1 (Winamp). */
  int PK_ILLBIENT = 166;

  /** Primary key of the {@link Genre} "Industro-Goth" from ID3v1 (Winamp). */
  int PK_INDUSTRO_GOTH = 167;

  /** Primary key of the {@link Genre} "Jam Band" from ID3v1 (Winamp). */
  int PK_JAM_BAND = 168;

  /** Primary key of the {@link Genre} "Krautrock" from ID3v1 (Winamp). */
  int PK_KRAUT_ROCK = 169;

  /** Primary key of the {@link Genre} "Leftfield" from ID3v1 (Winamp). */
  int PK_LEFTFIELD = 170;

  /** Primary key of the {@link Genre} "Lounge" from ID3v1 (Winamp). */
  int PK_LOUNGE = 171;

  /** Primary key of the {@link Genre} "Math Rock" from ID3v1 (Winamp). */
  int PK_MATH_ROCK = 172;

  /** Primary key of the {@link Genre} "New Romantic" from ID3v1 (Winamp). */
  int PK_NEW_ROMANTIC = 173;

  /** Primary key of the {@link Genre} "Nu-Breakz" from ID3v1 (Winamp). */
  int PK_NU_BREAKZ = 174;

  /** Primary key of the {@link Genre} "Post-Punk" from ID3v1 (Winamp). */
  int PK_POST_PUNK = 175;

  /** Primary key of the {@link Genre} "Post-Rock" from ID3v1 (Winamp). */
  int PK_POST_ROCK = 176;

  /** Primary key of the {@link Genre} "Psytrance" from ID3v1 (Winamp). */
  int PK_PSYTRANCE = 177;

  /** Primary key of the {@link Genre} "Shoegaze" from ID3v1 (Winamp). */
  int PK_SHOEGAZE = 178;

  /** Primary key of the {@link Genre} "Space Rock" from ID3v1 (Winamp). */
  int PK_SPACE_ROCK = 179;

  /** Primary key of the {@link Genre} "Trop Rock" from ID3v1 (Winamp). */
  int PK_TROP_ROCK = 180;

  /** Primary key of the {@link Genre} "World" (Music) from ID3v1 (Winamp). */
  int PK_WORLD = 181;

  /** Primary key of the {@link Genre} "Neoclassical" from ID3v1 (Winamp). */
  int PK_NEO_CLASSICAL = 182;

  /** Primary key of the {@link Genre} "Audiobook" from ID3v1 (Winamp). */
  int PK_AUDIOBOOK = 183;

  /** Primary key of the {@link Genre} "Audio Theatre" from ID3v1 (Winamp). */
  int PK_AUDIO_THEATRE = 184;

  /** Primary key of the {@link Genre} "Neue Deutsche Welle" from ID3v1 (Winamp). */
  int PK_NEUE_DEUTSCHE_WELLE = 185;

  /** Primary key of the {@link Genre} "Podcast" from ID3v1 (Winamp). */
  int PK_PODCAST = 186;

  /** Primary key of the {@link Genre} "Indie Rock" from ID3v1 (Winamp). */
  int PK_INDIE_ROCK = 187;

  /** Primary key of the {@link Genre} "G-Funk" from ID3v1 (Winamp). */
  int PK_G_FUNK = 188;

  /** Primary key of the {@link Genre} "Dubstep" from ID3v1 (Winamp). */
  int PK_DUBSTEP = 189;

  /** Primary key of the {@link Genre} "Garage Rock" from ID3v1 (Winamp). */
  int PK_GARAGE_ROCK = 190;

  /** Primary key of the {@link Genre} "Psybient" from ID3v1 (Winamp). */
  int PK_PSYBIENT = 191;

  /** Primary key of the {@link Genre} "Music". */
  int PK_MUSIC = 210;

  /** Primary key of the {@link Genre} "Audio". */
  int PK_AUDIO = 200;

  /** Primary key of the {@link Genre} "Americana". */
  int PK_AMERICANA = 223;

  /** Primary key of the {@link Genre} "Christian". */
  int PK_CHRISTIAN = 911;

  @Override
  LinkProperty<Genre> Parent();

  /**
   * @return the optional relatives (additional secondary parents).
   */
  default SetProperty<Link<Genre>> Relatives() {

    LinkProperty<Genre> linkProperty = new LinkProperty<>("Genre", Genre.class, PropertyMetadata.of(this));
    return new SetProperty<>("Relatives", linkProperty, PropertyMetadata.of(this));
  }

  /**
   * @return the optional synonyms (aliases for the {@link #Title() title}).
   */
  StringSetProperty Synonyms();

  /**
   * @return {@code true} for an abstract {@link Genre} (that cannot be assigned to a song), {@code false} otherwise.
   */
  BooleanProperty Abstract();

  /**
   * @return a new instance of {@link Genre}.
   */
  static Genre of() {

    return BeanFactory.get().create(Genre.class);
  }

}
