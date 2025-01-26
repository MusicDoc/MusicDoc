package io.github.musicdoc.genre;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.mmm.base.exception.ReadOnlyException;

/**
 * Test of {@link MusicalGenres}.
 */
public class MusicalGenresTest extends Assertions {

  /**
   * @return the {@link GenreRepository} to test.
   */
  protected GenreRepository getRepository() {

    return MusicalGenres.get();
  }

  /**
   * @return {@code true} if the {@link #getRepository() repository} is mutable, {@code false} otherwise (read-ony).
   */
  protected boolean isMutable() {

    return false;
  }

  /** Test of {@link MusicalGenres#get(String)}. */
  @Test
  public void testGet() {

    checkName("Audio");
    checkMusic();
    checkSpeech();
  }

  private void checkSpeech() {

    checkName("Speech", "Audio");
    checkName("Audiobook", "Speech");
    checkName("Spoken Word", "Speech");
    checkName("Audio Theatre", "Speech");
    checkName("Comedy", "Speech");
    checkName("Podcast", "Speech");
  }

  private void checkMusic() {

    checkName("Music", "Audio");
    checkFolk();
    checkClassical();
  }

  private void checkClassical() {

    checkName("Orchestral", "Music");
    checkName("Classical", "Orchestral");
    checkName("Classic", "Classical");
    checkName("Romantic", "Classical");
    checkName("Medival", "Classical");
    checkName("Renaissance", "Classical");
    checkName("Opera", "Classical");
    checkName("Chamber", "Classical");
    checkName("Sonata", "Classical");
    checkName("Symphony", "Classical");
    checkName("Baroque", "Classical");
  }

  private void checkFolk() {

    checkName("Folk", "Music");
    checkName("West Music", "Folk");
    checkName("American", "West Music");
    checkName("Blues", "American");
    checkName("Rhythm and Blues", "Blues");
  }

  private MusicalGenre checkName(String name, String... parents) {

    return checkAlias(name, name, parents);
  }

  private MusicalGenre checkAlias(String alias, String name, String... parents) {

    GenreRepository repository = getRepository();
    MusicalGenre genre = repository.get(alias);
    assertThat(genre).as(alias).isNotNull();
    assertThat(genre.getName()).isEqualTo(name);
    String[] actualParents = new String[genre.getParentCount()];
    for (int i = 0; i < actualParents.length; i++) {
      actualParents[i] = genre.getParent(i).getName();
    }
    assertThat(actualParents).as("Parents of " + name).containsExactly(parents);
    return genre;
  }

  /** Test of {@link MusicalGenres#get(String)}. */
  @Test
  public void testGetByAlias() {

    checkAliases(MusicalGenres.RHYTHM_AND_BLUES, "RNB", "Rhythm&Blues", "Rhythm and Blues", "rhythm'n'blues", "R&B");
    checkAliases(MusicalGenres.NU_BREAKZ, "Nu Skool Breaks", "Nu Breakz");
    checkAliases(MusicalGenres.EBM, "Electronic Body Music", "Electronic-Body");
    checkAliases(MusicalGenres.IDM, "Intelligent Dance Music", "Intelligent_Dance");
    checkAliases(MusicalGenres.AUDIO_THEATRE, "Audio-Theatre", "Radio-Drama");
    checkAliases(MusicalGenres.PSYBIENT, "Psy-Bient", "Psychedelic Ambient", "Ambient-PSY");
    checkAliases(MusicalGenres.COLORED_PUNK, "Negerpunk");
  }

  private void checkAliases(MusicalGenre genre, String... aliases) {

    GenreRepository repository = getRepository();
    assertThat(aliases).isNotEmpty();
    for (String alias : aliases) {
      MusicalGenre nextGenre = repository.get(alias);
      assertThat(nextGenre).as("Genre for alias " + alias).isSameAs(genre);
    }
  }

  /** Test of {@link MusicalGenres#getById3(int)}. */
  @Test
  public void testGetById3() {

    checkId3(0, "Blues");
    checkId3(1, "Classic Rock");
    checkId3(2, "Country");
    checkId3(3, "Dance");
    checkId3(4, "Disco");
    checkId3(5, "Funk");
    checkId3(6, "Grunge");
    checkId3(7, "Hip-Hop");
    checkId3(8, "Jazz");
    checkId3(9, "Metal");
    checkId3(10, "New Age");
    checkId3(11, "Oldies");
    checkId3(12, "Other");
    checkId3(13, "Pop");
    checkId3(14, "Rhythm and Blues");
    checkId3(15, "Rap");
    checkId3(16, "Reggae");
    checkId3(17, "Rock");
    checkId3(18, "Techno");
    checkId3(19, "Industrial");
    checkId3(20, "Alternative");
    checkId3(21, "Ska");
    checkId3(22, "Death Metal");
    checkId3(23, "Pranks");
    checkId3(24, "Soundtrack");
    checkId3(25, "Euro-Techno");
    checkId3(26, "Ambient");
    checkId3(27, "Trip-Hop");
    checkId3(28, "Vocal");
    checkId3(29, "Jazz & Funk");
    checkId3(30, "Fusion");
    checkId3(31, "Trance");
    checkId3(32, "Classical");
    checkId3(33, "Instrumental");
    checkId3(34, "Acid");
    checkId3(35, "House");
    checkId3(36, "Game");
    checkId3(37, "Sound Clip");
    checkId3(38, "Gospel");
    checkId3(39, "Noise");
    checkId3(40, "Alternative Rock");
    checkId3(41, "Bass");
    checkId3(42, "Soul");
    checkId3(43, "Punk");
    checkId3(44, "Space");
    checkId3(45, "Meditative");
    checkId3(46, "Instrumental Pop");
    checkId3(47, "Instrumental Rock");
    checkId3(48, "Ethnic");
    checkId3(49, "Gothic");
    checkId3(50, "Darkwave");
    checkId3(51, "Techno-Industrial");
    checkId3(52, "Electronic");
    checkId3(53, "Pop-Folk");
    checkId3(54, "Eurodance");
    checkId3(55, "Dream");
    checkId3(56, "Southern Rock");
    checkId3(57, "Comedy");
    checkId3(58, "Cult");
    checkId3(59, "Gangsta");
    checkId3(60, "Top 40");
    checkId3(61, "Christian Rap");
    checkId3(62, "Pop/Funk");
    checkId3(63, "Jungle");
    checkId3(64, "Native US");
    checkId3(65, "Cabaret");
    checkId3(66, "New Wave");
    checkId3(67, "Psychedelic");
    checkId3(68, "Rave");
    checkId3(69, "Showtunes");
    checkId3(70, "Trailer");
    checkId3(71, "Lo-Fi");
    checkId3(72, "Tribal");
    checkId3(73, "Acid Punk");
    checkId3(74, "Acid Jazz");
    checkId3(75, "Polka");
    checkId3(76, "Retro");
    checkId3(77, "Musical");
    checkId3(78, "Rock'n'Roll");
    checkId3(79, "Hard Rock");
    // Wisdexnamp
    checkId3(80, "Folk");
    checkId3(81, "Folk Rock");
    checkId3(82, "National Folk");
    checkId3(83, "Swing");
    checkId3(84, "Fast Fusion");
    checkId3(85, "Bebop");
    checkId3(86, "Latin");
    checkId3(87, "Revival");
    checkId3(88, "Celtic");
    checkId3(89, "Bluegrass");
    checkId3(90, "Avantgarde");
    checkId3(91, "Gothic Rock");
    checkId3(92, "Progressive Rock");
    checkId3(93, "Psychedelic Rock");
    checkId3(94, "Symphonic Rock");
    checkId3(95, "Slow Rock");
    checkId3(96, "Big Band");
    checkId3(97, "Chorus");
    checkId3(98, "Easy Listening");
    checkId3(99, "Acoustic");
    checkId3(100, "Humour");
    checkId3(101, "Speech");
    checkId3(102, "Chanson");
    checkId3(103, "Opera");
    checkId3(104, "Chamber");
    checkId3(105, "Sonata");
    checkId3(106, "Symphony");
    checkId3(107, "Booty Bass");
    checkId3(108, "Primus");
    checkId3(109, "Porn Groove");
    checkId3(110, "Satire");
    checkId3(111, "Slow Jam");
    checkId3(112, "Club");
    checkId3(113, "Tango");
    checkId3(114, "Samba");
    checkId3(115, "Folklore");
    checkId3(116, "Ballad");
    checkId3(117, "Power Ballad");
    checkId3(118, "Rhythmic Soul");
    checkId3(119, "Freestyle");
    checkId3(120, "Duet");
    checkId3(121, "Punk Rock");
    checkId3(122, "Drum Solo");
    checkId3(123, "A Cappella");
    checkId3(124, "Euro-House");
    checkId3(125, "Dance Hall");
    checkId3(126, "Goa");
    checkId3(127, "Drum & Bass");
    checkId3(128, "Club-House");
    checkId3(129, "Hardcore Techno");
    checkId3(130, "Terror");
    checkId3(131, "Indie");
    checkId3(132, "Britpop");
    checkId3(133, "Colored Punk");
    checkId3(134, "Polsk Punk");
    checkId3(135, "Beat");
    checkId3(136, "Christian Gangsta Rap");
    checkId3(137, "Heavy Metal");
    checkId3(138, "Black Metal");
    checkId3(139, "Crossover");
    checkId3(140, "Contemporary Christian");
    checkId3(141, "Christian Rock");
    // Winamp
    checkId3(142, "Merengue");
    checkId3(143, "Salsa");
    checkId3(144, "Thrash Metal");
    checkId3(145, "Anime");
    checkId3(146, "Jpop");
    checkId3(147, "Synthpop");
    checkId3(148, "Christmas");
    checkId3(149, "Art Rock");
    checkId3(150, "Baroque");
    checkId3(151, "Bhangra");
    checkId3(152, "Big Beat");
    checkId3(153, "Breakbeat");
    checkId3(154, "Chillout");
    checkId3(155, "Downtempo");
    checkId3(156, "Dub");
    checkId3(157, "EBM");
    checkId3(158, "Eclectic");
    checkId3(159, "Electro");
    checkId3(160, "Electroclash");
    checkId3(161, "Emo");
    checkId3(162, "Experimental");
    checkId3(163, "Garage");
    checkId3(164, "Global");
    checkId3(165, "IDM");
    checkId3(166, "Illbient");
    checkId3(167, "Industro-Goth");
    checkId3(168, "Jam Band");
    checkId3(169, "Krautrock");
    checkId3(170, "Leftfield"); // this is a band not a genre - what did the winamp devs smoke?
    checkId3(171, "Lounge");
    checkId3(172, "Math Rock");
    checkId3(173, "New Romantic");
    checkId3(174, "Nu-Breakz");
    checkId3(175, "Post-Punk");
    checkId3(176, "Post-Rock");
    checkId3(177, "Psytrance");
    checkId3(178, "Shoegaze");
    checkId3(179, "Space Rock");
    checkId3(180, "Trop Rock");
    checkId3(181, "World");
    checkId3(182, "Neoclassical");
    checkId3(183, "Audiobook");
    checkId3(184, "Audio Theatre");
    checkId3(185, "Neue Deutsche Welle");
    checkId3(186, "Podcast");
    checkId3(187, "Indie-Rock");
    checkId3(188, "G-Funk");
    checkId3(189, "Dubstep");
    checkId3(190, "Garage Rock");
    checkId3(191, "Psybient");
  }

  private MusicalGenre checkId3(int id3, String name) {

    GenreRepository repository = getRepository();
    MusicalGenre genre = repository.getById3(id3);
    assertThat(genre).as("" + id3).isNotNull();
    assertThat(genre.getName()).isEqualTo(name);
    return genre;
  }

  /** Test of {@link GenreRepository#getOrCreate(String)}. */
  @Test
  public void testGetOrCreate() {

    // arrange
    String name = "New Undefined Genre";
    GenreRepository repository = getRepository();
    // act
    MusicalGenre genre = repository.getOrCreate(name);
    // assert
    assertThat(genre.getName()).isEqualTo(name);
    assertThat(genre.getParent(0)).isSameAs(MusicalGenres.MUSIC);
    if (isMutable()) {
      assertThat(repository.get(name)).isSameAs(genre);
      assertThat(repository.get(genre.getNormalizedName())).isSameAs(genre);
    }
  }

  /** Test of {@link GenreRepository#remove(MusicalGenre)}. */
  @Test
  public void testRemove() {

    // arrange
    MusicalGenre genre = MusicalGenres.CHRISTIAN_GANGSTA_RAP;
    GenreRepository repository = getRepository();
    // act
    try {
      repository.remove(genre);
      // assert
      if (isMutable()) {
        assertThat(repository.get(genre.getName())).isNull();
      } else {
        failBecauseExceptionWasNotThrown(ReadOnlyException.class);
      }
    } catch (ReadOnlyException e) {
      if (isMutable()) {
        throw e;
      } else {
        assertThat(e).hasMessageContaining("Failed to modify read-only ");
        assertThat(repository.get(genre.getName())).isSameAs(genre);
      }
    }
  }

  /** Test of {@link GenreRepository#add(MusicalGenre)}. */
  @Test
  public void testAdd() {

    // arrange
    MusicalGenre genre = new MusicalGenre("New Genre", false, null, MusicalGenres.CHRISTIAN_GANGSTA_RAP);
    GenreRepository repository = getRepository();
    // act
    try {
      repository.add(genre);
      // assert
      if (isMutable()) {
        assertThat(repository.get(genre.getName())).isSameAs(genre);
      } else {
        failBecauseExceptionWasNotThrown(ReadOnlyException.class);
      }
    } catch (ReadOnlyException e) {
      if (isMutable()) {
        throw e;
      } else {
        assertThat(e).hasMessageContaining("Failed to modify read-only ");
        assertThat(repository.get(genre.getName())).isNull();
      }
    }
  }
}
