package io.github.musicdoc.song;

import java.nio.file.Paths;
import java.time.Year;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.mmm.entity.link.Link;
import io.github.musicdoc.album.Album;
import io.github.musicdoc.album.Albums;
import io.github.musicdoc.artist.Artist;
import io.github.musicdoc.artist.Artists;
import io.github.musicdoc.genre.Genre;
import io.github.musicdoc.genre.Genres;
import io.github.musicdoc.search.SearchQuery;
import io.github.musicdoc.search.SearchQueryMode;
import io.github.musicdoc.search.StringQuery;

/**
 * Test of {@link Songs}.
 */
public class SongsTest extends Assertions {

  private static final int PK_SOFT_ROCK = 21;

  private static final int PK_PROGRESSIVE_POP = 305;

  private static final int PK_HEAVY_METAL_BALLAD = 303;

  private final Genres genres;

  private final Songs songs;

  private final Artists artists;

  private final Albums albums;

  /**
   * The constructor.
   */
  public SongsTest() {

    super();
    this.genres = new Genres();
    this.genres.read(Paths.get("src/main/resources/genres.json"));
    this.songs = new Songs();
    this.artists = new Artists();
    this.albums = new Albums();
    Artist beatles = artist("The Beatles", Genre.PK_POP);
    Album whiteAlbum = album("The Beatles", beatles, 1968); // White Album
    song("While my Guitar gently wheeps", whiteAlbum, "licks");
    single("Hey Jude", beatles, "hit");
    single("Let it be", beatles, Genre.PK_ROCK, "hit");
    Album abbeyRoad = album("Abbey Road", beatles, Genre.PK_ROCK, 1969);
    song("Here comes the sun", abbeyRoad, Genre.PK_POP_FOLK, "picking");

    Artist abba = artist("ABBA", Genre.PK_POP);
    Album voulezVous = album("Voulez-Vous", abba, 1979);
    song("Chiquitita", voulezVous, Genre.PK_POP);
    song("I have a dream", voulezVous, Genre.PK_POP, "slow");
    Album superTrouper = album("Super Trouper", abba, 1980);
    song("Super Trouper", superTrouper, "hit");
    song("The winner takes it all", superTrouper, "hit");
    single("Waterloo", abba, Genre.PK_POP);

    Artist aHa = artist("a-ha", Genre.PK_SYNTH_POP);
    single("Take on me", aHa, "hit", "high", "tricky");

    Artist caroleKing = artist("Carole King", Genre.PK_POP);
    song("Crying in the rain", caroleKing);

    Artist supertramp = artist("Supertramp", Genre.PK_PROGRESSIVE_ROCK);
    Album breakfastInAmerica = album("Breakfast in America", supertramp, 1979);
    song("Breakfast in America", breakfastInAmerica);
    Album crimeOfTheCentury = album("Crime of the Century", supertramp, Genre.PK_ART_ROCK, 1974);
    song("Dreamer", crimeOfTheCentury, Genre.PK_POP);
    Album crisisWhatCrisis = album("Crisis? What Crisis?", supertramp, 1975);
    song("Easy does it", crisisWhatCrisis, Genre.PK_POP);
    Album evenInTheQuitestMoments = album("Even in the Quietest Moments...", supertramp, 1977);
    song("Even in the Quietest Moments", evenInTheQuitestMoments, Genre.PK_BALLAD, "licks", "picking");
    song("Hide in your Shell", crimeOfTheCentury);
    song("If everyone was listening", crimeOfTheCentury);
    song("Lady", crisisWhatCrisis, "tricky");
    song("Logical Song", breakfastInAmerica, PK_PROGRESSIVE_POP);
    song("Lord is it mine", breakfastInAmerica, PK_PROGRESSIVE_POP);
    song("School", crimeOfTheCentury, "licks", "tricky");
    song("Sister Moonshine", crisisWhatCrisis, "tricky");
    Album supertrampAlbum = album("Supertramp", supertramp, PK_PROGRESSIVE_POP, 1970);
    song("Surely", supertrampAlbum);

    Artist pinkFloyd = artist("Pink Floyd", Genre.PK_PROGRESSIVE_ROCK);
    Album wishYouWereHere = album("Wish You Were Here", pinkFloyd, 1975);
    song("Wish you were here", wishYouWereHere);

    Artist tenSharp = artist("Ten Sharp", Genre.PK_SYNTH_POP);
    Album tenSharpSingle = album("Ten Sharp", tenSharp, 1991);

    song("You", tenSharpSingle, Genre.PK_POP);

    Artist uriahHeep = artist("Uriah Heep", Genre.PK_HARD_ROCK);
    Album salisbury = album("Salisbury", uriahHeep, Genre.PK_HEAVY_METAL, 1971);
    song("Lady in Black", salisbury, Genre.PK_PROGRESSIVE_ROCK, "easy");

    Artist shockingBlue = artist("Shocking Blue", Genre.PK_PSYCHEDELIC_ROCK);
    single("Venus", shockingBlue, PK_SOFT_ROCK, 1969); // whyever wikipedia thinks venus is country rock?

    Artist alphaville = artist("Alphaville", Genre.PK_SYNTH_POP);
    Album foreverYoung = album("Forever Young", alphaville, 1984);
    song("Forever Young", foreverYoung, "high");
    song("Big in Japan", foreverYoung);

    Artist johnLegend = artist("John Legend", Genre.PK_RNB);
    Album loveInTheFuture = album("Love in the Future", johnLegend, 2013);
    song("All of me", loveInTheFuture, "high");

    Artist hooters = artist("The Hooters", Genre.PK_ROCK);
    Album amore = album("Amore", hooters, 1983);
    song("All you Zombies", amore, Genre.PK_SYNTH_POP);

    Artist cranberries = artist("The Cranberries", Genre.PK_ALTERNATIVE_ROCK);
    Album noNeedToArgue = album("No Need to Argue", cranberries, 1994);
    song("Zombie", noNeedToArgue, Genre.PK_GRUNGE, 4);
    song("The Icicle melts", noNeedToArgue, Genre.PK_POP, 7);

    Artist rollingStones = artist("The Rolling Stones", Genre.PK_ROCK);
    Album goatsHeadSoup = album("Goats Head Soup", rollingStones, 1973);
    song("Angie", goatsHeadSoup, PK_SOFT_ROCK);
    Album aftermathUs = album("Aftermath", rollingStones, Genre.PK_HARD_ROCK, 1966);
    song("Paint it black", aftermathUs, Genre.PK_PSYCHEDELIC_ROCK);

    Artist georgeHarrison = artist("George Harrison", Genre.PK_ROCK);
    Album allThingsMustPass = album("All Things Must Pass", georgeHarrison, 1970);
    song("My sweet Lord", allThingsMustPass, Genre.PK_FOLK_ROCK);

    Artist frankSinatra = artist("Frank Sinatra", Genre.PK_JAZZ);
    Album myWay = album("My Way", frankSinatra, Genre.PK_JAZZ, 1969);
    song("My Way", myWay, "Jacques Revaux", Genre.PK_POP, "high");

    Artist eltonJohn = artist("Elton John", Genre.PK_POP);
    Album emptySky = album("Empty Sky", eltonJohn, Genre.PK_PSYCHEDELIC_ROCK, 1969);
    song("Skyline Pigeon", emptySky, PK_SOFT_ROCK);

    Artist ericClapton = artist("Eric Clapton", Genre.PK_ROCK);
    Album rush = album("Rush", ericClapton, Genre.PK_BLUES, 1992);
    song("Tears in Heaven", rush, PK_SOFT_ROCK, "licks");

    Artist simonAndGarfunkel = artist("Simon & Garfunkel", Genre.PK_FOLK_ROCK);
    Album bridgeOverTroubledWater = album("Bridge Over Troubled Water", simonAndGarfunkel, 1970);
    song("Bridge over troubled Water", bridgeOverTroubledWater, Genre.PK_POP);
    Album wednesdayMorning3AM = album("Wednesday Morning, 3 A.M.", simonAndGarfunkel, Genre.PK_FOLK, 1964);
    // MusicalAlbum soundsOfSilence = album("Sounds of Silence", simonAndGarfunkel, 1966);
    song("The Sound of Silence", wednesdayMorning3AM, "Paul Simon", Genre.PK_FOLK_ROCK);

    Artist madonna = artist("Madonna", Genre.PK_POP);
    Album trueBlue = album("True Blue", madonna, 1986);
    song("La Isla bonita", trueBlue, Genre.PK_POP);
    Album likeAPrayer = album("Like a Prayer", madonna, 1989);
    song("Like a Prayer", likeAPrayer, Genre.PK_POP);

    Artist beachBoys = artist("The Beach Boys", Genre.PK_ROCK);
    Album petSounds = album("Pet Sounds", beachBoys, PK_PROGRESSIVE_POP, 1966);
    song("Wouldn't It Be Nice", petSounds, Genre.PK_ROCK);
    song("God only knows", petSounds, Genre.PK_BALLAD);
    single("Komoko", beachBoys, Genre.PK_POP, 1988);

    Artist crashTestDummies = artist("Crash Test Dummies", Genre.PK_ALTERNATIVE_ROCK, 1988);
    Album godShuffledHisFeet = album("God Shuffled His Feet", crashTestDummies, 1993);
    song("Mmm Mmm Mmm Mmm", godShuffledHisFeet);

    Artist blondie = artist("Blondie", Genre.PK_NEW_WAVE, 1974);
    Album noExit = album("No Exit", blondie, Genre.PK_ALTERNATIVE_ROCK, 1999);
    song("Maria", noExit, Genre.PK_POP);

    Artist metallica = artist("Metallica", Genre.PK_HEAVY_METAL, 1981);
    Album metallicaBlackAlbum = album("Metallica", metallica, 1991);
    song("Nothing else matters", metallicaBlackAlbum, PK_HEAVY_METAL_BALLAD, "licks", "solo", "tricky");

    Artist mikeOldfield = artist("Mike Oldfield", Genre.PK_PROGRESSIVE_ROCK, 1967);
    Album crises = album("Crises", mikeOldfield, 1983);
    song("Moonlight Shadow", crises, PK_SOFT_ROCK);

    Artist catStevens = artist("Cat Stevens", Genre.PK_FOLK_ROCK, 1965);
    Album teaserAndTheFirecat = album("Teaser and the Firecat", catStevens, 1971);
    song("Moonshadow", teaserAndTheFirecat);

    Artist direStraits = artist("Dire Straits", PK_SOFT_ROCK, 1977);
    Album makingMovies = album("Making Movies", direStraits, 1980);
    song("Romeo and Juliet", makingMovies, Genre.PK_ROCK, "licks");

    Artist switchfoot = artist("Switchfoot", Genre.PK_ALTERNATIVE_ROCK, 1996);
    Album newWayToBeHuman = album("New Way to Be Human", switchfoot, 1999);
    song("Only Hope", newWayToBeHuman, "slow");

    Artist billyJoel = artist("Billy Joel", Genre.PK_ROCK, 1964);
    Album stormFront = album("Storm Front", billyJoel, Genre.PK_ART_ROCK, 1989);
    song("Leningrad", stormFront);

    Artist richardSanderson = artist("Richard Sanderson", Genre.PK_POP, 1968);
    Album laBoom = album("La Boom", null, Genre.PK_SOUNDTRACK, 1980);
    song("Reality", laBoom, richardSanderson, "Vladimir Cosma", Genre.PK_BALLAD);

    Artist joshuaKadison = artist("Joshua Kadison", Genre.PK_POP, 0);
    Album paintedDesertSerenade = album("Painted Desert Serenade", joshuaKadison, PK_SOFT_ROCK, 1993);
    song("Jessie", paintedDesertSerenade);

    Artist judasPriest = artist("Judas Priest", Genre.PK_HEAVY_METAL, 1969);
    Album killingMachine = album("Killing Machine", judasPriest, 1978);
    song("Running Wild", killingMachine);

    Artist runningWild = artist("Running Wild", Genre.PK_HEAVY_METAL, 1976);
    Album pileOfSkulls = album("Pile of Skulls", runningWild, 1992);
    song("Pile of Skulls", pileOfSkulls);

  }

  private Artist artist(String name, long genre) {

    return artist(name, genre, 0);
  }

  private Artist artist(String title, long genreId, int year) {

    Artist artist = this.artists.findByTitle(title);
    if (artist == null) {
      artist = Artist.of();
      Genre genre = this.genres.findByPk(genreId);
      artist.Genre().set(Link.of(genre));
      artist.Year().set(Year.of(year));
    }
    return artist;
  }

  private Song song(String title, Artist artist, String... tags) {

    return song(title, null, artist, null, -1, tags);
  }

  private Song song(String title, Album album, String... tags) {

    return song(title, album, -1, tags);
  }

  private Song song(String title, Album album, int genreId, String... tags) {

    return song(title, album, genreId, 0, tags);
  }

  private Song song(String title, Album album, int genreId, int trackNo, String... tags) {

    return song(title, album, null, genreId, tags);
  }

  private Song song(String title, Album album, String composer, int genreId, String... tags) {

    return song(title, album, album.Artist().getEntity(), composer, genreId, tags);
  }

  private Song song(String title, Album album, Artist artist, String composer, int genreId, String... tags) {

    Song song = Song.of();
    song.Title().set(title);
    song.Album().set(Link.of(album));
    song.Artist().set(Link.of(artist));

    Genre genre;
    if (genreId == -1) {
      genre = album.Genre().getEntity();
    } else {
      genre = this.genres.findByPk(genreId);
    }
    song.Genre().set(Link.of(genre));
    if (composer != null) {
      song.Composer().set(composer);
    }
    for (String tag : tags) {
      song.Tags().add(tag);
    }
    this.songs.save(song);
    return song;
  }

  private Album album(String title, Artist artist, int year) {

    return album(title, artist, null, year);
  }

  private Album album(String title, Artist artist, int genreId, int year) {

    Genre genre = this.genres.findByPk(genreId);
    return album(title, artist, genre, year);
  }

  private Album album(String title, Artist artist, Genre genre, int year) {

    Album album = Album.of();
    album.Title().set(title);
    album.Artist().set(Link.of(artist));
    if (genre != null) {
      album.Genre().set(Link.of(genre));
    }
    album.Year().set(Year.of(year));
    return album;
  }

  private void single(String title, Artist artist, String... tags) {

    single(title, artist, -1, tags);
  }

  private void single(String title, Artist artist, int genreId, String... tags) {

    single(title, artist, genreId, 0, tags);
  }

  private void single(String title, Artist artist, int genreId, int year, String... tags) {

    Genre genre;
    if (genreId == -1) {
      genre = artist.Genre().getEntity();
    } else {
      genre = this.genres.findByPk(genreId);
    }
    Album single = album(title, artist, genre, year);
    song(title, single, tags);
  }

  /** Test of {@link Songs#find(SearchQuery)} with {@link SearchQueryMode#CONTAINS_ALL}. */
  @Test
  public void testFindContainsAll() {

    // arrange
    SearchQuery query = new StringQuery("Super", SearchQueryMode.CONTAINS_ALL, null);
    // act
    List<Song> hits = this.songs.find(query);
    // assert
    // "Super Trouper" is first because a match in the title has a higher score than in the artist
    // songs from supertramp are in alphabetical order
    assertThat(hits.stream().map(s -> s.Title().get())).containsExactly("Super Trouper", "Breakfirst in America",
        "Dreamer", "Easy does it", "Even in the quitest Moments", "Hide in your Shell", "If everyone was listening",
        "Lady", "Logical Song", "Lord is it mine", "School", "Sister Moonshine", "Surely");
  }

  /** Test of {@link Songs#find(SearchQuery)} with {@link SearchQueryMode#EQUALS}. */
  @Test
  public void testFindEquals() {

    // arrange
    SearchQuery query = new StringQuery("Running Wild", SearchQueryMode.EQUALS, null);
    // act
    List<Song> hits = this.songs.find(query);
    // assert
    // "Super Trouper" is first because a match in the title has a higher score than in the artist
    // songs from supertramp are in alphabetical order
    assertThat(hits.stream().map(s -> s.Title().get())).containsExactly("Running Wild", "Pile of Skulls");
  }

}
