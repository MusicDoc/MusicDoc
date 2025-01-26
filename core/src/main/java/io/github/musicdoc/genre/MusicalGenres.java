package io.github.musicdoc.genre;

import java.util.HashMap;
import java.util.Map;

import io.github.musicdoc.entity.MapEntityRepository;

/**
 * Default implementation of {@link GenreRepository}.
 */
public class MusicalGenres extends MapEntityRepository<MusicalGenre> implements GenreRepository {

  private static final MusicalGenres INSTANCE = new MusicalGenres();

  private static final Map<Integer, MusicalGenre> ID3 = new HashMap<>();

  /** {@link MusicalGenre} "Audio" that is the {@link MusicalGenre#getParent(int) root genre}. */
  public static final MusicalGenre AUDIO = genre("Audio", true);

  /** {@link MusicalGenre} "Music" for any kind of music. */
  public static final MusicalGenre MUSIC = genre("Music", true, AUDIO);

  /** {@link MusicalGenre} "Partner Dance" for of any music to dance with a partner as a couple. */
  public static final MusicalGenre PARTNER_DANCE = genre("Partner Dance", true, MUSIC);

  /** {@link MusicalGenre} "Utility". */
  public static final MusicalGenre UTILITY = genre("Utility", MUSIC);

  /** {@link MusicalGenre} "Religious". */
  public static final MusicalGenre RELIGIOUS = genre("Religious", UTILITY);

  /** {@link MusicalGenre} "Christian". */
  public static final MusicalGenre CHRISTIAN = genre("Christian", RELIGIOUS);

  /** {@link MusicalGenre} "Psalm". */
  public static final MusicalGenre PSALM = genre("Psalm", CHRISTIAN);

  /** {@link MusicalGenre} "Military". */
  public static final MusicalGenre MILITARY = genre("Military", UTILITY);

  /** {@link MusicalGenre} "Entertainment". */
  public static final MusicalGenre ENTERTAINMENT = genre("Entertainment", UTILITY);

  /** {@link MusicalGenre} "Folk". */
  public static final MusicalGenre FOLK = genre("Folk", 80, MUSIC);

  /** {@link MusicalGenre} "Latin". */
  public static final MusicalGenre LATIN = genre("Latin", 86, FOLK);

  /** {@link MusicalGenre} "Latin Dance". */
  public static final MusicalGenre LATIN_DANCE = genre("Latin Dance", LATIN, PARTNER_DANCE);

  /** {@link MusicalGenre} "Satin". */
  public static final MusicalGenre SALSA = genre("Salsa", 143, LATIN_DANCE);

  /** {@link MusicalGenre} "Bachata". */
  public static final MusicalGenre BACHATA = genre("Bachata", LATIN_DANCE);

  /** {@link MusicalGenre} "Merengue". */
  public static final MusicalGenre MERENGUE = genre("Merengue", 142, LATIN_DANCE);

  /** {@link MusicalGenre} "Rumba". */
  public static final MusicalGenre RUMBA = genre("Rumba", LATIN_DANCE);

  /** {@link MusicalGenre} "ChaChaCha". */
  public static final MusicalGenre CHACHACHA = genre("ChaChaCha", LATIN_DANCE);

  /** {@link MusicalGenre} "Mambo". */
  public static final MusicalGenre MAMBO = genre("Mambo", LATIN_DANCE);

  /** {@link MusicalGenre} "Zouk". */
  public static final MusicalGenre ZOUK = genre("Zouk", LATIN_DANCE);

  /** {@link MusicalGenre} "Lambada". */
  public static final MusicalGenre LAMBADA = genre("Lambada", LATIN_DANCE);

  /** {@link MusicalGenre} "Tango Dance". */
  public static final MusicalGenre TANGO_DANCE = genre("Tango Dance", true, LATIN_DANCE);

  /** {@link MusicalGenre} "Tango". */
  public static final MusicalGenre TANGO = genre("Tango", 113, TANGO_DANCE);

  /** {@link MusicalGenre} "Tango Argentino". */
  public static final MusicalGenre TANGO_ARGENTINO = genre("Tango Argentino", TANGO_DANCE);

  /** {@link MusicalGenre} "Brazilian". */
  public static final MusicalGenre BRAZILIAN_DANCE = genre("Brazilian Dance", LATIN_DANCE);

  /** {@link MusicalGenre} "Samba". */
  public static final MusicalGenre SAMBA = genre("Samba", 114, BRAZILIAN_DANCE);

  /** {@link MusicalGenre} "Bossa Nova". */
  public static final MusicalGenre BOSSA_NOVA = genre("Bossa Nova", BRAZILIAN_DANCE);

  /** {@link MusicalGenre} "Mexican". */
  public static final MusicalGenre MEXICAN_DANCE = genre("Mexican Dance", LATIN_DANCE);

  /** {@link MusicalGenre} "Mariachi". */
  public static final MusicalGenre MARIACHI = genre("Mariachi", MEXICAN_DANCE);

  /** {@link MusicalGenre} "Norteño". */
  public static final MusicalGenre NORTENO = genre("Norteño", MEXICAN_DANCE);

  /** {@link MusicalGenre} "African Dance". */
  public static final MusicalGenre AFRICAN_DANCE = genre("African Dance", FOLK, PARTNER_DANCE);

  /** {@link MusicalGenre} "Kizomba". */
  public static final MusicalGenre KIZOMBA = genre("Kizomba", AFRICAN_DANCE);

  /**
   * {@link MusicalGenre} "West Music" (Music of the western world such as Europe or Amercia with twelve-tone system and
   * mainly major/minor scales).
   */
  public static final MusicalGenre WEST_MUSIC = genre("West Music", true, FOLK);

  /** {@link MusicalGenre} (North) "American". */
  public static final MusicalGenre AMERICAN = genre("American", WEST_MUSIC);

  /** {@link MusicalGenre} "Americana". */
  public static final MusicalGenre AMERICANA = genre("Americana", AMERICAN);

  /** {@link MusicalGenre} "American Dance". */
  public static final MusicalGenre AMERICAN_DANCE = genre("American Dance", AMERICAN, PARTNER_DANCE);

  /** {@link MusicalGenre} "Foxtrott". */
  public static final MusicalGenre FOXTROTT = genre("Foxtrott", AMERICAN_DANCE);

  /** {@link MusicalGenre} "Discofox". */
  public static final MusicalGenre DISCOFOX = genre("Discofox", FOXTROTT);

  /** {@link MusicalGenre} "Quickstep". */
  public static final MusicalGenre QUICKSTEP = genre("Quickstep", FOXTROTT);

  /** {@link MusicalGenre} "Slowfox". */
  public static final MusicalGenre SLOWFOX = genre("Slowfox", FOXTROTT);

  /** {@link MusicalGenre} "Blues". */
  public static final MusicalGenre BLUES = genre("Blues", 0, AMERICAN);

  /** {@link MusicalGenre} "Country". */
  public static final MusicalGenre COUNTRY = genre("Country", 2, AMERICAN);

  /** {@link MusicalGenre} "Dance". */
  public static final MusicalGenre DANCE = genre("Dance", 3, WEST_MUSIC);

  /** {@link MusicalGenre} "Dance". */
  public static final MusicalGenre DISCO = genre("Disco", 4, AMERICAN);

  /** {@link MusicalGenre} "Funk". */
  public static final MusicalGenre FUNK = genre("Funk", 5, AMERICAN);

  /** {@link MusicalGenre} "Rap". */
  public static final MusicalGenre RAP = genre("Rap", 15, WEST_MUSIC);

  /** {@link MusicalGenre} "Hip-Hop". */
  public static final MusicalGenre HIP_HOP = genre("Hip-Hop", 7, RAP);

  /** {@link MusicalGenre} "Jazz". */
  public static final MusicalGenre JAZZ = genre("Jazz", 8, AMERICAN);

  /** {@link MusicalGenre} "Vocal-Jazz". */
  public static final MusicalGenre VOCAL_JAZZ = genre("Vocal-Jazz", JAZZ);

  /** {@link MusicalGenre} "New Age". */
  public static final MusicalGenre NEW_AGE = genre("New Age", 10, WEST_MUSIC);

  /** {@link MusicalGenre} "Oldies". */
  public static final MusicalGenre OLDIES = genre("Oldies", 11, WEST_MUSIC);

  /** {@link MusicalGenre} "Other". */
  public static final MusicalGenre OTHER = genre("Other", true, 12, MUSIC);

  /** {@link MusicalGenre} "Pop". */
  public static final MusicalGenre POP = genre("Pop", 13, WEST_MUSIC);

  /** {@link MusicalGenre} "Traditional Pop". */
  public static final MusicalGenre TRADITIONAL_POP = genre("Traditional Pop", WEST_MUSIC);

  /** {@link MusicalGenre} "Folk-Pop". */
  public static final MusicalGenre FOLK_POP = genre("Folk-Pop", POP, FOLK);

  /** {@link MusicalGenre} "Euro-Pop". */
  public static final MusicalGenre EURO_POP = genre("Euro-Pop", POP);

  /** {@link MusicalGenre} "Progressive-Pop". */
  public static final MusicalGenre PROGRESSIVE_POP = genre("Progressive-Pop", POP);

  /** {@link MusicalGenre} "Latin-Pop". */
  public static final MusicalGenre LATIN_POP = genre("Latin-Pop", POP, LATIN);

  /** {@link MusicalGenre} "Dance-Pop". */
  public static final MusicalGenre DANCE_POP = genre("Dance-Pop", POP, DANCE);

  /** {@link MusicalGenre} "Rhythm and blues". */
  public static final MusicalGenre RHYTHM_AND_BLUES = genre("Rhythm and Blues", 14, BLUES);

  /** {@link MusicalGenre} "Reggae". */
  public static final MusicalGenre REGGAE = genre("Reggae", 16, AMERICAN);

  /** {@link MusicalGenre} "Rock". */
  public static final MusicalGenre ROCK = genre("Rock", 17, WEST_MUSIC);

  /** {@link MusicalGenre} "Classic Rock". */
  public static final MusicalGenre CLASSIC_ROCK = genre("Classic Rock", 1, ROCK);

  /** {@link MusicalGenre} "Pop-Rock". */
  public static final MusicalGenre POP_ROCK = genre("Pop-Rock", ROCK, POP);

  /** {@link MusicalGenre} "Baroque-Rock". */
  public static final MusicalGenre BAROQUE_ROCK = genre("Baroque-Rock", ROCK);

  /** {@link MusicalGenre} "Power-Pop". */
  public static final MusicalGenre POWER_POP = genre("Power-Pop", ROCK, POP);

  /** {@link MusicalGenre} "Metal". */
  public static final MusicalGenre METAL = genre("Metal", 9, ROCK);

  /** {@link MusicalGenre} "Techno". */
  public static final MusicalGenre TECHNO = genre("Techno", 18, WEST_MUSIC);

  /** {@link MusicalGenre} "Industrial". */
  public static final MusicalGenre INDUSTRIAL = genre("Industrial", 19, WEST_MUSIC);

  /** {@link MusicalGenre} "Alternative". */
  public static final MusicalGenre ALTERNATIVE = genre("Alternative", 20, WEST_MUSIC);

  /** {@link MusicalGenre} "Ska". */
  public static final MusicalGenre SKA = genre("Ska", 21, AMERICAN);

  /** {@link MusicalGenre} "Death Metal". */
  public static final MusicalGenre DEATH_METAL = genre("Death Metal", 22, METAL);

  /** {@link MusicalGenre} "Pranks". */
  public static final MusicalGenre PRANKS = genre("Pranks", 23, WEST_MUSIC);

  /** {@link MusicalGenre} "Soundtrack". */
  public static final MusicalGenre SOUNDTRACK = genre("Soundtrack", 24, WEST_MUSIC);

  /** {@link MusicalGenre} "Euro-Techno". */
  public static final MusicalGenre EURO_TECHNO = genre("Euro-Techno", 25, TECHNO);

  /** {@link MusicalGenre} "Ambient". */
  public static final MusicalGenre AMBIENT = genre("Ambient", 26, WEST_MUSIC);

  /** {@link MusicalGenre} "Trip-Hop". */
  public static final MusicalGenre TRIP_HOP = genre("Trip-Hop", 27, HIP_HOP);

  /** {@link MusicalGenre} "Vocal". */
  public static final MusicalGenre VOCAL = genre("Vocal", 28, WEST_MUSIC);

  /** {@link MusicalGenre} "Jazz & Funk". */
  public static final MusicalGenre JAZZ_N_FUNK = genre("Jazz & Funk", 29, JAZZ, FUNK);

  /** {@link MusicalGenre} "Fusion". */
  public static final MusicalGenre FUSION = genre("Fusion", 30, MUSIC);

  /** {@link MusicalGenre} "Trance". */
  public static final MusicalGenre TRANCE = genre("Trance", 31, DANCE);

  /** {@link MusicalGenre} "Orchestral". */
  public static final MusicalGenre ORCHESTRAL = genre("Orchestral", true, MUSIC);

  /** {@link MusicalGenre} "Classical". */
  public static final MusicalGenre CLASSICAL = genre("Classical", 32, ORCHESTRAL);

  /** {@link MusicalGenre} "Classic". */
  public static final MusicalGenre CLASSIC = genre("Classic", CLASSICAL);

  /** {@link MusicalGenre} "Romantic". */
  public static final MusicalGenre ROMANTIC = genre("Romantic", CLASSICAL);

  /** {@link MusicalGenre} "Medival". */
  public static final MusicalGenre MEDIVAL = genre("Medival", CLASSICAL);

  /** {@link MusicalGenre} "Renaissance". */
  public static final MusicalGenre RENAISSANCE = genre("Renaissance", CLASSICAL);

  /** {@link MusicalGenre} "Opera". */
  public static final MusicalGenre OPERA = genre("Opera", 103, CLASSICAL);

  /** {@link MusicalGenre} "Chamber". */
  public static final MusicalGenre CHAMBER = genre("Chamber", 104, CLASSICAL);

  /** {@link MusicalGenre} "Sonata". */
  public static final MusicalGenre SONATA = genre("Sonata", 105, CLASSICAL);

  /** {@link MusicalGenre} "Symphony". */
  public static final MusicalGenre SYMPHONY = genre("Symphony", 106, CLASSICAL);

  /** {@link MusicalGenre} "Baroque". */
  public static final MusicalGenre BAROQUE = genre("Baroque", 150, CLASSICAL);

  /** {@link MusicalGenre} "Instrumental" (actually better used as tag than as genre). */
  public static final MusicalGenre INSTRUMENTAL = genre("Instrumental", 33, MUSIC);

  /** {@link MusicalGenre} "Acid". */
  public static final MusicalGenre ACID = genre("Acid", 34, TECHNO);

  /** {@link MusicalGenre} "House". */
  public static final MusicalGenre HOUSE = genre("House", 35, AMERICAN);

  /** {@link MusicalGenre} "Game". */
  public static final MusicalGenre GAME = genre("Game", 36, WEST_MUSIC);

  /** {@link MusicalGenre} "Sound Clip". */
  public static final MusicalGenre SOUND_CLIP = genre("Sound Clip", 37, MUSIC);

  /** {@link MusicalGenre} "Gospel". */
  public static final MusicalGenre GOSPEL = genre("Gospel", 38, CHRISTIAN);

  /** {@link MusicalGenre} "Noise". */
  public static final MusicalGenre Noise = genre("Noise", 39, MUSIC);

  /** {@link MusicalGenre} "Alternative Rock". */
  public static final MusicalGenre ALTERNATIVE_ROCK = genre("Alternative Rock", 40, ROCK);

  /** {@link MusicalGenre} "Grunge". */
  public static final MusicalGenre GRUNGE = genre("Grunge", 6, ALTERNATIVE_ROCK);

  /** {@link MusicalGenre} "Bass". */
  public static final MusicalGenre BASS = genre("Bass", 41, DANCE);

  /** {@link MusicalGenre} "Soul". */
  public static final MusicalGenre SOUL = genre("Soul", 42, AMERICAN);

  /** {@link MusicalGenre} "Punk". */
  public static final MusicalGenre PUNK = genre("Punk", 43, ROCK);

  /** {@link MusicalGenre} "Space". */
  public static final MusicalGenre SPACE = genre("Space", 44, NEW_AGE, AMBIENT);

  /** {@link MusicalGenre} "Meditative". */
  public static final MusicalGenre MEDITATIVE = genre("Meditative", 45, NEW_AGE);

  /** {@link MusicalGenre} "Instrumental Pop". */
  public static final MusicalGenre INSTRUMENTAL_POP = genre("Instrumental Pop", 46, POP, INSTRUMENTAL);

  /** {@link MusicalGenre} "Instrumental Rock". */
  public static final MusicalGenre INSTRUMENTAL_ROCK = genre("Instrumental Rock", 47, ROCK, INSTRUMENTAL);

  /** {@link MusicalGenre} "Ethnic". */
  public static final MusicalGenre ETHNIC = genre("Ethnic", 48, FOLK);

  /** {@link MusicalGenre} "Gothic". */
  public static final MusicalGenre GOTHIC = genre("Gothic", 49, ROCK);

  /** {@link MusicalGenre} "Techno-industrial". */
  public static final MusicalGenre TECHNO_INDUSTRIAL = genre("Techno-Industrial", 51, TECHNO, INDUSTRIAL);

  /** {@link MusicalGenre} "Electronic". */
  public static final MusicalGenre ELECTRONIC = genre("Electronic", 52, FOLK);

  /** {@link MusicalGenre} "Pop-Folk". */
  public static final MusicalGenre POP_FOLK = genre("Pop-Folk", 53, POP);

  /** {@link MusicalGenre} "Eurodance". */
  public static final MusicalGenre EURODANCE = genre("Eurodance", 54, DANCE, ELECTRONIC);

  /** {@link MusicalGenre} "Dream". */
  public static final MusicalGenre DREAM = genre("Dream", 55, ALTERNATIVE_ROCK);

  /** {@link MusicalGenre} "Southern Rock". */
  public static final MusicalGenre SOUTHERN_ROCK = genre("Southern Rock", 56, ROCK, AMERICANA);

  /** {@link MusicalGenre} "Cult". */
  public static final MusicalGenre CULT = genre("Cult", 58, FOLK);

  /** {@link MusicalGenre} "Gangsta". */
  public static final MusicalGenre GANGSTA = genre("Gangsta", 59, HIP_HOP);

  /** {@link MusicalGenre} "Top 40". */
  public static final MusicalGenre TOP_40 = genre("Top 40", 60, FOLK);

  /** {@link MusicalGenre} "Christian Rap". */
  public static final MusicalGenre CHRISTIAN_RAP = genre("Christian Rap", 61, HIP_HOP, CHRISTIAN);

  /** {@link MusicalGenre} "Pop/Funk". */
  public static final MusicalGenre POP_FUNK = genre("Pop/Funk", 62, POP, FUNK);

  /** {@link MusicalGenre} "Jungle". */
  public static final MusicalGenre JUNGLE = genre("Jungle", 63, DANCE);

  /** {@link MusicalGenre} "Indigenous". */
  public static final MusicalGenre INDIGENOUS = genre("Indigenous", FOLK);

  /** {@link MusicalGenre} "Native US". */
  public static final MusicalGenre NATIVE_US = genre("Native US", 64, INDIGENOUS);

  /** {@link MusicalGenre} "Cabaret". */
  public static final MusicalGenre CABARET = genre("Cabaret", 65, ENTERTAINMENT);

  /** {@link MusicalGenre} "New Wave". */
  public static final MusicalGenre NEW_WAVE = genre("New Wave", 66, ROCK);

  /** {@link MusicalGenre} "Darkwave". */
  public static final MusicalGenre DARKWAVE = genre("Darkwave", 50, NEW_WAVE);

  /** {@link MusicalGenre} "Psychedelic". */
  public static final MusicalGenre PSYCHEDELIC = genre("Psychedelic", 67, POP);

  /** {@link MusicalGenre} "Rave". */
  public static final MusicalGenre RAVE = genre("Rave", 68, DANCE, ELECTRONIC);

  /** {@link MusicalGenre} "Showtunes". */
  public static final MusicalGenre SHOWTUNES = genre("Showtunes", 69, ENTERTAINMENT);

  /** {@link MusicalGenre} "Trailer". */
  public static final MusicalGenre TRAILER = genre("Trailer", 70, SOUNDTRACK);

  /** {@link MusicalGenre} "Lo-fi". */
  public static final MusicalGenre LOFI = genre("Lo-Fi", 71, FOLK);

  /** {@link MusicalGenre} "Tribal". */
  public static final MusicalGenre TRIBAL = genre("Tribal", 72, HOUSE);

  /** {@link MusicalGenre} "Acid Punk". */
  public static final MusicalGenre ACID_PUNK = genre("Acid Punk", 73, ACID, PUNK);

  /** {@link MusicalGenre} "Acid Jazz". */
  public static final MusicalGenre ACID_JAZZ = genre("Acid Jazz", 74, FUSION, RAP);

  /** {@link MusicalGenre} "Polka". */
  public static final MusicalGenre POLKA = genre("Polka", 75, PARTNER_DANCE);

  /** {@link MusicalGenre} "Retro". */
  public static final MusicalGenre RETRO = genre("Retro", 76, FOLK);

  /** {@link MusicalGenre} "Musical". */
  public static final MusicalGenre MUSICAL = genre("Musical", 77, ENTERTAINMENT);

  /** {@link MusicalGenre} "Hard Rock". */
  public static final MusicalGenre HARD_ROCK = genre("Hard Rock", 79, ROCK);

  /** {@link MusicalGenre} "Heavy Rock". */
  public static final MusicalGenre HEAVY_ROCK = genre("Heavy Rock", HARD_ROCK);

  /** {@link MusicalGenre} "Folk Rock". */
  public static final MusicalGenre FOLK_ROCK = genre("Folk Rock", 81, ROCK);

  /** {@link MusicalGenre} "National Folk". */
  public static final MusicalGenre NATIONAL_FOLK = genre("National Folk", 82, FOLK);

  /** {@link MusicalGenre} "Swing". */
  public static final MusicalGenre SWING = genre("Swing", 83, FOLK);

  /** {@link MusicalGenre} "Swing Dance". */
  public static final MusicalGenre SWING_DANCE = genre("Swing Dance", SWING, AMERICAN_DANCE);

  /** {@link MusicalGenre} "Boogie-Woogie". */
  public static final MusicalGenre BOOGIE_WOOGIE = genre("Boogie-Woogie", SWING_DANCE);

  /** {@link MusicalGenre} "Rock'n'Roll". */
  public static final MusicalGenre ROCK_N_ROLL = genre("Rock'n'Roll", 78, SWING_DANCE);

  /** {@link MusicalGenre} "Jive". */
  public static final MusicalGenre JIVE = genre("Jive", SWING_DANCE);

  /** {@link MusicalGenre} "Lindyhop". */
  public static final MusicalGenre LINDYHOP = genre("Lindyhop", SWING_DANCE);

  /** {@link MusicalGenre} "Eastcoast-Swing". */
  public static final MusicalGenre EASTCOAST_SWING = genre("Eastcoast-Swing", SWING_DANCE);

  /** {@link MusicalGenre} "Westcoast-Swing". */
  public static final MusicalGenre WESTCOAST_SWING = genre("Westcoast-Swing", SWING_DANCE);

  /** {@link MusicalGenre} "Fast Fusion". */
  public static final MusicalGenre FAST_FUSION = genre("Fast Fusion", 84, FOLK);

  /** {@link MusicalGenre} "Bebop". */
  public static final MusicalGenre BEBOP = genre("Bebop", 85, JAZZ);

  /** {@link MusicalGenre} "Traditional". */
  public static final MusicalGenre TRADITIONAL = genre("Traditional", FOLK);

  /** {@link MusicalGenre} "Revival". */
  public static final MusicalGenre REVIVAL = genre("Revival", 87, TRADITIONAL);

  /** {@link MusicalGenre} "Celtic". */
  public static final MusicalGenre CELTIC = genre("Celtic", 88, TRADITIONAL);

  /** {@link MusicalGenre} "Bluegrass". */
  public static final MusicalGenre BLUEGRASS = genre("Bluegrass", 89, AMERICAN);

  /** {@link MusicalGenre} "Avantgarde". */
  public static final MusicalGenre AVANTGARDE = genre("Avantgarde", 90, MUSIC);

  /** {@link MusicalGenre} "Gothic Rock". */
  public static final MusicalGenre GOTHIC_ROCK = genre("Gothic Rock", 91, GOTHIC);

  /** {@link MusicalGenre} "Progressive Rock". */
  public static final MusicalGenre PROGRESSIVE_ROCK = genre("Progressive Rock", 92, ROCK);

  /** {@link MusicalGenre} "Psychedelic Rock". */
  public static final MusicalGenre PSYCHEDELIC_ROCK = genre("Psychedelic Rock", 93, ROCK, PSYCHEDELIC);

  /** {@link MusicalGenre} "Symphonic Rock". */
  public static final MusicalGenre SYMPHONIC_ROCK = genre("Symphonic Rock", 94, ROCK);

  /** {@link MusicalGenre} "Slow Rock". */
  public static final MusicalGenre SLOW_ROCK = genre("Slow Rock", 95, ROCK);

  /** {@link MusicalGenre} "Soft Rock". */
  public static final MusicalGenre SOFT_ROCK = genre("Soft Rock", ROCK);

  /** {@link MusicalGenre} "Raga Rock". */
  public static final MusicalGenre RAGA_ROCK = genre("Raga Rock", ROCK);

  /** {@link MusicalGenre} "Country Rock". */
  public static final MusicalGenre COUNTRY_ROCK = genre("Country Rock", ROCK, COUNTRY);

  /** {@link MusicalGenre} "Roots Rock". */
  public static final MusicalGenre ROOTS_ROCK = genre("Roots Rock", ROCK);

  /** {@link MusicalGenre} "Pub Rock". */
  public static final MusicalGenre PUB_ROCK = genre("Pub Rock", ROCK);

  /** {@link MusicalGenre} "Blues Rock". */
  public static final MusicalGenre BLUES_ROCK = genre("Blues Rock", ROCK, BLUES);

  /** {@link MusicalGenre} "Big Band". */
  public static final MusicalGenre BIG_BAND = genre("Big Band", 96, JAZZ);

  /** {@link MusicalGenre} "Chorus". */
  public static final MusicalGenre CHORUS = genre("Chorus", 97, MUSIC);

  /** {@link MusicalGenre} "Easy Listening". */
  public static final MusicalGenre EASY_LISTENING = genre("Easy Listening", 98, NEW_AGE);

  /** {@link MusicalGenre} "Acoustic". */
  public static final MusicalGenre ACOUSTIC = genre("Acoustic", 99, MUSIC);

  /** {@link MusicalGenre} "Chanson". */
  public static final MusicalGenre CHANSON = genre("Chanson", 102, FOLK);

  /** {@link MusicalGenre} "Booty Bass". */
  public static final MusicalGenre BOOTY_BASS = genre("Booty Bass", 107, BASS);

  /** {@link MusicalGenre} "Primus". */
  public static final MusicalGenre PRIMUS = genre("Primus", 108, ROCK);

  /** {@link MusicalGenre} "Porn Groove". */
  public static final MusicalGenre PORN_GROOVE = genre("Porn Groove", 109, SOUNDTRACK);

  /** {@link MusicalGenre} "Satire". */
  public static final MusicalGenre SATIRE = genre("Satire", 110, AUDIO);

  /** {@link MusicalGenre} "Slow Jam". */
  public static final MusicalGenre SLOW_JAM = genre("Slow Jam", 111, RHYTHM_AND_BLUES);

  /** {@link MusicalGenre} "Club". */
  public static final MusicalGenre CLUB = genre("Club", 112, DANCE);

  /** {@link MusicalGenre} "Folklore". */
  public static final MusicalGenre FOLKLORE = genre("Folklore", 115, TRADITIONAL);

  /** {@link MusicalGenre} "Ballad". */
  public static final MusicalGenre BALLAD = genre("Ballad", 116, FOLK);

  /** {@link MusicalGenre} "Power Ballad". */
  public static final MusicalGenre POWER_BALLAD = genre("Power Ballad", 117, BALLAD);

  /** {@link MusicalGenre} "Sentimental Ballad". */
  public static final MusicalGenre SENTIMENTAL_BALLAD = genre("Sentimental Ballad", POWER_BALLAD);

  /** {@link MusicalGenre} "Rhythmic Soul". */
  public static final MusicalGenre RHYTHMIC_SOUL = genre("Rhythmic Soul", 118, SOUL);

  /** {@link MusicalGenre} "Freestyle". */
  public static final MusicalGenre FREESTYLE = genre("Freestyle", 119, MUSIC);

  /** {@link MusicalGenre} "Duet". */
  public static final MusicalGenre DUET = genre("Duet", 120, FOLK);

  /** {@link MusicalGenre} "Punk Rock". */
  public static final MusicalGenre PUNK_ROCK = genre("Punk Rock", 121, PUNK);

  /** {@link MusicalGenre} "Drum Solo". */
  public static final MusicalGenre DRUM_SOLO = genre("Drum Solo", 122, INSTRUMENTAL);

  /** {@link MusicalGenre} "A Cappella". */
  public static final MusicalGenre A_CAPPELLA = genre("A Cappella", 123, VOCAL);

  /** {@link MusicalGenre} "Euro-House". */
  public static final MusicalGenre EURO_HOUSE = genre("Euro-House", 124, HOUSE);

  /** {@link MusicalGenre} "Dance Hall". */
  public static final MusicalGenre DANCE_HALL = genre("Dance Hall", 125, PARTNER_DANCE);

  /** {@link MusicalGenre} "Goa". */
  public static final MusicalGenre GOA = genre("Goa", 126, DANCE);

  /** {@link MusicalGenre} "Drum & Bass". */
  public static final MusicalGenre DRUM_N_BASS = genre("Drum & Bass", 127, INSTRUMENTAL);

  /** {@link MusicalGenre} "Club-House". */
  public static final MusicalGenre CLUB_HOUSE = genre("Club-House", 128, HOUSE);

  /** {@link MusicalGenre} "Hardcore Techno". */
  public static final MusicalGenre HARDCORE_TECHNO = genre("Hardcore Techno", 129, TECHNO);

  /** {@link MusicalGenre} "Terror". */
  public static final MusicalGenre TERROR = genre("Terror", 130, MUSIC);

  /** {@link MusicalGenre} "Indie". */
  public static final MusicalGenre INDIE = genre("Indie", 131, WEST_MUSIC);

  /** {@link MusicalGenre} "Britpop". */
  public static final MusicalGenre BRITPOP = genre("Britpop", 132, POP);

  /** {@link MusicalGenre} "Colored Punk". */
  public static final MusicalGenre COLORED_PUNK = genre("Colored Punk", 133, PUNK);

  /** {@link MusicalGenre} "Polsk Punk". */
  public static final MusicalGenre POLSK_PUNK = genre("Polsk Punk", 134, PUNK);

  /** {@link MusicalGenre} "Beat". */
  public static final MusicalGenre BEAT = genre("Beat", 135, POP);

  /** {@link MusicalGenre} "Christian Gangsta Rap". */
  public static final MusicalGenre CHRISTIAN_GANGSTA_RAP = genre("Christian Gangsta Rap", 136, GANGSTA, CHRISTIAN);

  /** {@link MusicalGenre} "Heavy Metal". */
  public static final MusicalGenre HEAVY_METAL = genre("Heavy Metal", 137, METAL);

  /** {@link MusicalGenre} "Heavy Metal Ballad". */
  public static final MusicalGenre HEAVY_METAL_BALLAD = genre("Heavy Metal Ballad", HEAVY_METAL, BALLAD);

  /** {@link MusicalGenre} "Black Metal". */
  public static final MusicalGenre BLACK_METAL = genre("Black Metal", 138, METAL);

  /** {@link MusicalGenre} "Crossover". */
  public static final MusicalGenre CROSSOVER = genre("Crossover", 139, MUSIC);

  /** {@link MusicalGenre} "Contemporary Christian". */
  public static final MusicalGenre CONTEMPORARY_CHRISTIAN = genre("Contemporary Christian", 140, CHRISTIAN);

  /** {@link MusicalGenre} "Christian Rock". */
  public static final MusicalGenre CHRISTIAN_ROCK = genre("Christian Rock", 141, ROCK, CHRISTIAN);

  /** {@link MusicalGenre} "Thrash Metal". */
  public static final MusicalGenre THRASH_METAL = genre("Thrash Metal", 144, METAL);

  /** {@link MusicalGenre} "Anime". */
  public static final MusicalGenre ANIME = genre("Anime", 145, FOLK);

  /** {@link MusicalGenre} "Jpop". */
  public static final MusicalGenre JPOP = genre("Jpop", 146, POP);

  /** {@link MusicalGenre} "Synthpop". */
  public static final MusicalGenre SYNTH_POP = genre("Synthpop", 147, POP);

  /** {@link MusicalGenre} "Christmas". */
  public static final MusicalGenre CHRISTMAS = genre("Christmas", 148, FOLK);

  /** {@link MusicalGenre} "Art Rock". */
  public static final MusicalGenre ART_ROCK = genre("Art Rock", 149, ROCK);

  /** {@link MusicalGenre} "Bhangra". */
  public static final MusicalGenre BHANGRA = genre("Bhangra", 151, FOLK);

  /** {@link MusicalGenre} "Big Beat". */
  public static final MusicalGenre BIG_BEAT = genre("Big Beat", 152, ELECTRONIC);

  /** {@link MusicalGenre} "Breakbeat". */
  public static final MusicalGenre BREAKBEAT = genre("Breakbeat", 153, ELECTRONIC);

  /** {@link MusicalGenre} "Chillout". */
  public static final MusicalGenre CHILLOUT = genre("Chillout", 154, EASY_LISTENING);

  /** {@link MusicalGenre} "Downtempo". */
  public static final MusicalGenre DOWNTEMPO = genre("Downtempo", 155, ELECTRONIC);

  /** {@link MusicalGenre} "Dub". */
  public static final MusicalGenre DUB = genre("Dub", 156, REGGAE);

  /** {@link MusicalGenre} "EBM" (Electronic Body Music). */
  public static final MusicalGenre EBM = genre("EBM", 157, ELECTRONIC, DANCE);

  /** {@link MusicalGenre} "Eclectic" (diverse genres). */
  public static final MusicalGenre ECLECTIC = genre("Eclectic", 158, FOLK);

  /** {@link MusicalGenre} "Electro". */
  public static final MusicalGenre ELECTRO = genre("Electro", 159, ELECTRONIC);

  /** {@link MusicalGenre} "Electroclash". */
  public static final MusicalGenre ELECTROCLASH = genre("Electroclash", 160, ELECTRO, NEW_WAVE);

  /** {@link MusicalGenre} "Emo" (emotional rock). */
  public static final MusicalGenre EMO = genre("Emo", 161, ROCK);

  /** {@link MusicalGenre} "Experimental". */
  public static final MusicalGenre EXPERIMENTAL = genre("Experimental", 162, FOLK);

  /** {@link MusicalGenre} "Garage". */
  public static final MusicalGenre GARAGE = genre("Garage", 163, FOLK);

  /** {@link MusicalGenre} "Global". */
  public static final MusicalGenre GLOBAL = genre("Global", 164, FOLK);

  /** {@link MusicalGenre} "IDM" (Intelligent Dance Music). */
  public static final MusicalGenre IDM = genre("IDM", 165, DANCE);

  /** {@link MusicalGenre} "Illbient". */
  public static final MusicalGenre ILLBIENT = genre("Illbient", 166, HIP_HOP);

  /** {@link MusicalGenre} "Industro-Goth". */
  public static final MusicalGenre INDUSTRO_GOTH = genre("Industro-Goth", 167, GOTHIC);

  /** {@link MusicalGenre} "Jam Band". */
  public static final MusicalGenre JAM_BAND = genre("Jam Band", 168, FOLK);

  /** {@link MusicalGenre} "Krautrock". */
  public static final MusicalGenre KRAUTROCK = genre("Krautrock", 169, ROCK);

  /** {@link MusicalGenre} "Leftfield". */
  public static final MusicalGenre LEFTFIELD = genre("Leftfield", 170, ELECTRONIC);

  /** {@link MusicalGenre} "Lounge". */
  public static final MusicalGenre LOUNGE = genre("Lounge", 171, EASY_LISTENING);

  /** {@link MusicalGenre} "Math Rock". */
  public static final MusicalGenre MATH_ROCK = genre("Math Rock", 172, ROCK);

  /** {@link MusicalGenre} "New Romantic". */
  public static final MusicalGenre NEW_ROMANTIC = genre("New Romantic", 173, FOLK);

  /** {@link MusicalGenre} "Nu-Breakz" (Nu Skool Breaks). */
  public static final MusicalGenre NU_BREAKZ = genre("Nu-Breakz", 174, BREAKBEAT);

  /** {@link MusicalGenre} "Post-Punk". */
  public static final MusicalGenre POST_PUNK = genre("Post-Punk", 175, PUNK_ROCK);

  /** {@link MusicalGenre} "Post-Rock". */
  public static final MusicalGenre POST_ROCK = genre("Post-Rock", 176, ROCK);

  /** {@link MusicalGenre} "Psytrance". */
  public static final MusicalGenre PSYTRANCE = genre("Psytrance", 177, TRANCE);

  /** {@link MusicalGenre} "Shoegaze". */
  public static final MusicalGenre SHOEGAZE = genre("Shoegaze", 178, INDIE, ALTERNATIVE_ROCK);

  /** {@link MusicalGenre} "Space Rock". */
  public static final MusicalGenre SPACE_ROCK = genre("Space Rock", 179, ROCK);

  /** {@link MusicalGenre} "Trop Rock" (Tropical Rock). */
  public static final MusicalGenre TROP_ROCK = genre("Trop Rock", 180, ROCK);

  /** {@link MusicalGenre} "World". */
  public static final MusicalGenre WORLD = genre("World", 181, FOLK);

  /** {@link MusicalGenre} "New African World Beat". */
  public static final MusicalGenre NEW_AFRICAN_WORLD_BEAT = genre("New African World Beat", WORLD);

  /** {@link MusicalGenre} "Neoclassical". */
  public static final MusicalGenre NEOCLASSICAL = genre("Neoclassical", 182, FOLK);

  /** {@link MusicalGenre} "Neue Deutsche Welle". */
  public static final MusicalGenre NEUE_DEUTSCHE_WELLE = genre("Neue Deutsche Welle", 185, NEW_WAVE);

  /** {@link MusicalGenre} "Indie-Rock". */
  public static final MusicalGenre INDIE_ROCK = genre("Indie-Rock", 187, ROCK, INDIE);

  /** {@link MusicalGenre} "G-Funk". */
  public static final MusicalGenre G_FUNK = genre("G-Funk", 188, GANGSTA, FUNK);

  /** {@link MusicalGenre} "Dubstep". */
  public static final MusicalGenre DUBSTEP = genre("Dubstep", 189, DANCE);

  /** {@link MusicalGenre} "Garage-Rock". */
  public static final MusicalGenre GARAGE_ROCK = genre("Garage Rock", 190, ROCK, GARAGE);

  /** {@link MusicalGenre} "Psybient". */
  public static final MusicalGenre PSYBIENT = genre("Psybient", 191, TRANCE);

  /**
   * {@link MusicalGenre} "Speech", the {@link MusicalGenre#getParent(int) parent} of any spoken audio that is not
   * {@link #MUSIC music} by design. Please note that even a track of an audio-book may contain audio that is not only
   * spoken words but may be considered as music but still this would be considered as speech.
   */
  public static final MusicalGenre SPEECH = genre("Speech", true, 101, AUDIO);

  /** {@link MusicalGenre} "Spoken Word" as a specific form of {@link #SPEECH}. */
  public static final MusicalGenre SPOKEN_WORD = genre("Spoken Word", SPEECH);

  /** {@link MusicalGenre} "Audiobook" as a specific form of {@link #SPEECH}. */
  public static final MusicalGenre AUDIO_BOOK = genre("Audiobook", 183, SPEECH);

  /**
   * {@link MusicalGenre} "Audio Theatre" (Radio Drama) as a specific form of {@link #SPEECH} with sound effects and
   * music.
   */
  public static final MusicalGenre AUDIO_THEATRE = genre("Audio Theatre", 184, SPEECH);

  /** {@link MusicalGenre} "Podcast" as a specific form of {@link #SPEECH}. */
  public static final MusicalGenre PODCAST = genre("Podcast", 186, SPEECH);

  /** {@link MusicalGenre} "Commedy" as a specific form of {@link #SPEECH}. */
  public static final MusicalGenre COMEDY = genre("Comedy", 57, SPEECH);

  /** {@link MusicalGenre} "Humour" as a specific form of {@link #SPEECH}. */
  public static final MusicalGenre HUMOUR = genre("Humour", 100, SPEECH);

  static {

    // Aliases (must be normalized "R'n'B" -> "rnb")
    INSTANCE.entityMap.put("rnb", RHYTHM_AND_BLUES);
    // "R&B" -> "randb"
    INSTANCE.entityMap.put("randb", RHYTHM_AND_BLUES);
    INSTANCE.entityMap.put("nuskoolbreaks", NU_BREAKZ);
    INSTANCE.entityMap.put("electronicbody", EBM);
    INSTANCE.entityMap.put("intelligentdance", IDM);
    INSTANCE.entityMap.put("psychedelicambient", PSYBIENT);
    INSTANCE.entityMap.put("ambientpsy", PSYBIENT);
    INSTANCE.entityMap.put("radiodrama", AUDIO_THEATRE);
    // the programmer rejected this discriminating term to be the official form (see
    // https://en.wikipedia.org/wiki/List_of_ID3v1_genres) and only support it as alias.
    // This allows importing an existing genre with this term and convert it accordingly.
    INSTANCE.entityMap.put("negerpunk", COLORED_PUNK);
  }

  private static MusicalGenre genre(String name, MusicalGenre... parents) {

    return genre(name, false, null, parents);
  }

  private static MusicalGenre genre(String name, int id3, MusicalGenre... parents) {

    return genre(name, false, Integer.valueOf(id3), parents);
  }

  private static MusicalGenre genre(String name, boolean abstractFlag, MusicalGenre... parents) {

    return genre(name, abstractFlag, null, parents);
  }

  private static MusicalGenre genre(String name, boolean abstractFlag, Integer id3, MusicalGenre... parents) {

    MusicalGenre genre = new MusicalGenre(name, abstractFlag, id3, parents);
    INSTANCE.entityMap.put(genre.getNormalizedName(), genre);
    if (id3 != null) {
      MusicalGenre duplicate = ID3.put(id3, genre);
      if (duplicate != null) {
        throw new IllegalStateException("Duplicate genre for ID3 code " + id3 + ": " + duplicate + " and " + genre);
      }
    }
    return genre;
  }

  private MusicalGenres() {

    super();
  }

  private MusicalGenres(MusicalGenres template) {

    super(new HashMap<>(template.entityMap));
  }

  @Override
  protected String normalize(String name) {

    return AUDIO.normalize(name);
  }

  @Override
  public MusicalGenre getById3(int id3Code) {

    return ID3.get(Integer.valueOf(id3Code));
  }

  @Override
  public MusicalGenre create(String name) {

    return new MusicalGenre(name, false, null, MUSIC);
  }

  @Override
  protected boolean isReadOnly() {

    return (this == INSTANCE);
  }

  /**
   * @return the immutable default instance of {@link GenreRepository}.
   */
  public static GenreRepository get() {

    return INSTANCE;
  }

  /**
   * @return a new mutable instance of {@link GenreRepository}.
   */
  public static GenreRepository of() {

    return new MusicalGenres(INSTANCE);
  }

}
