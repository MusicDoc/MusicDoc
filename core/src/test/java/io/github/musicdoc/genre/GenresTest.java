package io.github.musicdoc.genre;

import java.nio.file.Paths;
import java.util.Locale;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.mmm.entity.id.Id;
import io.github.mmm.entity.id.PkIdLong;
import io.github.mmm.entity.link.Link;

/**
 * Test of {@link Genres}.
 */
public class GenresTest extends Assertions {

  private Genres genres;

  /**
   * Test of all {@link Genres} from JSON.
   */
  @Test
  public void testGenres() {

    // arrange
    this.genres = new Genres();
    // act
    this.genres.read(Paths.get("src/main/resources/genres.json"));
    // assert
    verifyBlues(this.genres.findById3(Genre.PK_BLUES));
    verifyClassicRock(this.genres.findById3(Genre.PK_CLASSIC_ROCK));
    verifyCountry(this.genres.findById3(Genre.PK_COUNTRY));
    verifyDisco(this.genres.findById3(Genre.PK_DISCO));
    verifyFunk(this.genres.findById3(Genre.PK_FUNK));
    verifyGrunge(this.genres.findById3(Genre.PK_GRUNGE));
    verifyJazz(this.genres.findById3(Genre.PK_JAZZ));
    verifyMetal(this.genres.findById3(Genre.PK_METAL));
    verifyNewAge(this.genres.findById3(Genre.PK_NEW_AGE));
    verifyOldies(this.genres.findById3(Genre.PK_OLDIES));
    verifyOther(this.genres.findById3(Genre.PK_OTHER));
    verifyPop(this.genres.findById3(Genre.PK_POP));
    verifyRnB(this.genres.findById3(Genre.PK_RNB));
    verifyReggae(this.genres.findById3(Genre.PK_REGGAE));
    verifyIndustrial(this.genres.findById3(Genre.PK_INDUSTRIAL));
    verifyAlternative(this.genres.findById3(Genre.PK_ALTERNATIVE));
    verifySka(this.genres.findById3(Genre.PK_SKA));
    verifyDeathMetal(this.genres.findById3(Genre.PK_DEATH_METAL));
    verifyPranks(this.genres.findById3(Genre.PK_PRANKS));
    verifySoundtrack(this.genres.findById3(Genre.PK_SOUNDTRACK));
    verifyEuroTechno(this.genres.findById3(Genre.PK_EURO_TECHNO));
    verifyAmbient(this.genres.findById3(Genre.PK_AMBIENT));
    verifyTripHop(this.genres.findById3(Genre.PK_TRIP_HOP));
    verifyVocal(this.genres.findById3(Genre.PK_VOCAL));
    verifyJazzAndFunk(this.genres.findById3(Genre.PK_JAZZ_AND_FUNK));
    verifyFusion(this.genres.findById3(Genre.PK_FUSION));
    verifyClassical(this.genres.findById3(Genre.PK_CLASSICAL));
    verifyInstrumental(this.genres.findById3(Genre.PK_INSTRUMENTAL));
    verifyAcid(this.genres.findById3(Genre.PK_ACID));
    verifyHouse(this.genres.findById3(Genre.PK_HOUSE));
    verifyGame(this.genres.findById3(Genre.PK_GAME));
    verifySoundClip(this.genres.findById3(Genre.PK_SOUND_CLIP));
    verifyGospel(this.genres.findById3(Genre.PK_GOSPEL));
    verifyNoise(this.genres.findById3(Genre.PK_NOISE));
    verifyBass(this.genres.findById3(Genre.PK_BASS));
    verifySoul(this.genres.findById3(Genre.PK_SOUL));
    verifyPunk(this.genres.findById3(Genre.PK_PUNK));
    verifySpace(this.genres.findById3(Genre.PK_SPACE));
    verifyMeditative(this.genres.findById3(Genre.PK_MEDITATIVE));
    verifyInstrumentalPop(this.genres.findById3(Genre.PK_INSTRUMENTAL_POP));
    verifyInstrumentalRock(this.genres.findById3(Genre.PK_INSTRUMENTAL_ROCK));
    verifyEthnic(this.genres.findById3(Genre.PK_ETHNIC));
    verifyGothic(this.genres.findById3(Genre.PK_GOTHIC));
    verifyDarkWave(this.genres.findById3(Genre.PK_DARKWAVE));
    verifyTechnoIndustrial(this.genres.findById3(Genre.PK_TECHNO_INDUSTRIAL));
    verifyElectronic(this.genres.findById3(Genre.PK_ELECTRONIC));
    verifyPopFolk(this.genres.findById3(Genre.PK_POP_FOLK));
    verifyEuroDance(this.genres.findById3(Genre.PK_EURO_DANCE));
    verifyDream(this.genres.findById3(Genre.PK_DREAM));
    verifySouthernRock(this.genres.findById3(Genre.PK_SOUTHERN_ROCK));
    verifyComedy(this.genres.findById3(Genre.PK_COMEDY));
    verifyCult(this.genres.findById3(Genre.PK_CULT));
    verifyGangsta(this.genres.findById3(Genre.PK_GANGSTA));
    verifyTop40(this.genres.findById3(Genre.PK_TOP_40));
    verifyChristianRap(this.genres.findById3(Genre.PK_CHRISTIAN_RAP));
    verifyPopFunk(this.genres.findById3(Genre.PK_POP_FUNK));
    verifyJungle(this.genres.findById3(Genre.PK_JUNGLE));
    verifyNativeUs(this.genres.findById3(Genre.PK_NATIVE_US));
    verifyCabaret(this.genres.findById3(Genre.PK_CABARET));
    verifyPsychedelic(this.genres.findById3(Genre.PK_PSYCHEDELIC));
    verifyRave(this.genres.findById3(Genre.PK_RAVE));
    verifyShowtunes(this.genres.findById3(Genre.PK_SHOWTUNES));
    verifyTrailer(this.genres.findById3(Genre.PK_TRAILER));
    verifyLoFi(this.genres.findById3(Genre.PK_LO_FI));
    verifyTribal(this.genres.findById3(Genre.PK_TRIBAL));
    verifyAcidPunk(this.genres.findById3(Genre.PK_ACID_PUNK));
    verifyAcidJazz(this.genres.findById3(Genre.PK_ACID_JAZZ));
    verifyPolka(this.genres.findById3(Genre.PK_POLKA));
    verifyRetro(this.genres.findById3(Genre.PK_RETRO));
    verifyMusical(this.genres.findById3(Genre.PK_MUSICAL));
    verifyRockAndRoll(this.genres.findById3(Genre.PK_ROCK_AND_ROLL));
    verifyHardRock(this.genres.findById3(Genre.PK_HARD_ROCK));
    verifyFolkRock(this.genres.findById3(Genre.PK_FOLK_ROCK));
    verifyNationalFolk(this.genres.findById3(Genre.PK_NATIONAL_FOLK));
    verifyFastFusion(this.genres.findById3(Genre.PK_FAST_FUSION));
    verifyBebop(this.genres.findById3(Genre.PK_BEBOP));
    verifyLatin(this.genres.findById3(Genre.PK_LATIN));
    verifyRevival(this.genres.findById3(Genre.PK_REVIVAL));
    verifyCeltic(this.genres.findById3(Genre.PK_CELTIC));
    verifyBluegrass(this.genres.findById3(Genre.PK_BLUEGRASS));
    verifyAvantgarde(this.genres.findById3(Genre.PK_AVANTGARDE));
    verifyGothicRock(this.genres.findById3(Genre.PK_GOTHIC_ROCK));
    verifyProgressiveRock(this.genres.findById3(Genre.PK_PROGRESSIVE_ROCK));
    verifyPsychedelicRock(this.genres.findById3(Genre.PK_PSYCHEDELIC_ROCK));
    verifySymphonicRock(this.genres.findById3(Genre.PK_SYMPHONIC_ROCK));
    verifySlowRock(this.genres.findById3(Genre.PK_SLOW_ROCK));
    verifyBigBand(this.genres.findById3(Genre.PK_BIG_BAND));
    verifyChorus(this.genres.findById3(Genre.PK_CHORUS));
    verifyEasyListening(this.genres.findById3(Genre.PK_EASY_LISTENING));
    verifyAcoustic(this.genres.findById3(Genre.PK_ACOUSTIC));
    verifyHumour(this.genres.findById3(Genre.PK_HUMOUR));
    verifyChanson(this.genres.findById3(Genre.PK_CHANSON));
    verifyOpera(this.genres.findById3(Genre.PK_OPERA));
    verifyChamber(this.genres.findById3(Genre.PK_CHAMBER));
    verifySonata(this.genres.findById3(Genre.PK_SONATA));
    verifySymphony(this.genres.findById3(Genre.PK_SYMPHONY));
    verifyBootyBass(this.genres.findById3(Genre.PK_BOOTY_BASS));
    verifyPrimus(this.genres.findById3(Genre.PK_PRIMUS));
    verifyPornGroove(this.genres.findById3(Genre.PK_PORN_GROOVE));
    verifySatire(this.genres.findById3(Genre.PK_SATIRE));
    verifySlowJam(this.genres.findById3(Genre.PK_SLOW_JAM));

    verifyPsybient(this.genres.findById3(Genre.PK_PSYBIENT));
    verifyQuickstep(this.genres.findById(id(241)));
    verifySlowfox(this.genres.findById(id(242)));
  }

  private void verifyBlues(Genre blues) {

    Genre parent = verify(blues, Genre.PK_BLUES, "Blues");
    verifyAmerican(parent);
  }

  private void verifyClassicRock(Genre classicRock) {

    Genre parent = verify(classicRock, Genre.PK_CLASSIC_ROCK, "Classic Rock");
    verifyRock(parent);
  }

  private void verifyCountry(Genre country) {

    Genre parent = verify(country, Genre.PK_COUNTRY, "Country");
    verifyAmerican(parent);
  }

  private void verifyDance(Genre dance) {

    Genre parent = verify(dance, Genre.PK_DANCE, "Dance");
    verifyWestMusic(parent);
  }

  private void verifyDisco(Genre disco) {

    Genre parent = verify(disco, Genre.PK_DISCO, "Disco");
    verifyAmerican(parent);
  }

  private void verifyFunk(Genre funk) {

    Genre parent = verify(funk, Genre.PK_FUNK, "Funk");
    verifyAmerican(parent);
  }

  private void verifyGrunge(Genre grunge) {

    Genre parent = verify(grunge, Genre.PK_GRUNGE, "Grunge");
    verifyAlternativeRock(parent);
  }

  private void verifyHipHop(Genre hipHop) {

    Genre parent = verify(hipHop, Genre.PK_HIP_HOP, "Hip-Hop");
    verifyRap(parent);
  }

  private void verifyJazz(Genre jazz) {

    Genre parent = verify(jazz, Genre.PK_JAZZ, "Jazz");
    verifyAmerican(parent);
  }

  private void verifyMetal(Genre metal) {

    Genre parent = verify(metal, Genre.PK_METAL, "Metal");
    verifyRock(parent);
  }

  private void verifyNewAge(Genre newAge) {

    Genre parent = verify(newAge, Genre.PK_NEW_AGE, "New Age");
    verifyWestMusic(parent);
  }

  private void verifyOldies(Genre oldies) {

    Genre parent = verify(oldies, Genre.PK_OLDIES, "Oldies");
    verifyWestMusic(parent);
  }

  private void verifyOther(Genre other) {

    Genre parent = verify(other, Genre.PK_OTHER, "Other");
    verifyMusic(parent);
  }

  private void verifyPop(Genre pop) {

    Genre parent = verify(pop, Genre.PK_POP, "Pop");
    verifyWestMusic(parent);
  }

  private void verifyRnB(Genre rnb) {

    Genre parent = verify(rnb, Genre.PK_RNB, "Rhythm and Blues", longs(), "R&B", "RNB");
    verifyBlues(parent);
  }

  private void verifyRap(Genre rap) {

    Genre parent = verify(rap, Genre.PK_RAP, "Rap");
    verifyWestMusic(parent);
  }

  private void verifyReggae(Genre reggae) {

    Genre parent = verify(reggae, Genre.PK_REGGAE, "Reggae");
    verifyAmerican(parent);
  }

  private void verifyRock(Genre rock) {

    Genre parent = verify(rock, Genre.PK_ROCK, "Rock");
    verifyWestMusic(parent);
  }

  private void verifyTechno(Genre techno) {

    Genre parent = verify(techno, Genre.PK_TECHNO, "Techno");
    verifyWestMusic(parent);
  }

  private void verifyIndustrial(Genre industrial) {

    Genre parent = verify(industrial, Genre.PK_INDUSTRIAL, "Industrial");
    verifyWestMusic(parent);
  }

  private void verifyAlternative(Genre alternative) {

    Genre parent = verify(alternative, Genre.PK_ALTERNATIVE, "Alternative");
    verifyWestMusic(parent);
  }

  private void verifySka(Genre ska) {

    Genre parent = verify(ska, Genre.PK_SKA, "Ska");
    verifyAmerican(parent);
  }

  private void verifyDeathMetal(Genre deathMetal) {

    Genre parent = verify(deathMetal, Genre.PK_DEATH_METAL, "Death Metal");
    verifyMetal(parent);
  }

  private void verifyPranks(Genre pranks) {

    Genre parent = verify(pranks, Genre.PK_PRANKS, "Pranks");
    verifyWestMusic(parent);
  }

  private void verifySoundtrack(Genre soundtrack) {

    Genre parent = verify(soundtrack, Genre.PK_SOUNDTRACK, "Soundtrack");
    verifyMusic(parent);
  }

  private void verifyEuroTechno(Genre euroTechno) {

    Genre parent = verify(euroTechno, Genre.PK_EURO_TECHNO, "Euro-Techno");
    verifyTechno(parent);
  }

  private void verifyAmbient(Genre ambient) {

    Genre parent = verify(ambient, Genre.PK_AMBIENT, "Ambient");
    verifyWestMusic(parent);
  }

  private void verifyTripHop(Genre tripHop) {

    Genre parent = verify(tripHop, Genre.PK_TRIP_HOP, "Trip-Hop");
    verifyHipHop(parent);
  }

  private void verifyVocal(Genre vocal) {

    Genre parent = verify(vocal, Genre.PK_VOCAL, "Vocal");
    verifyWestMusic(parent);
  }

  private void verifyJazzAndFunk(Genre jazzAndFunk) {

    Genre parent = verify(jazzAndFunk, Genre.PK_JAZZ_AND_FUNK, "Jazz & Funk", Genre.PK_FUNK);
    verifyJazz(parent);
  }

  private void verifyFusion(Genre fusion) {

    Genre parent = verify(fusion, Genre.PK_FUSION, "Fusion");
    verifyMusic(parent);
  }

  private void verifyTrance(Genre trance) {

    Genre parent = verify(trance, Genre.PK_TRANCE, "Trance");
    verifyDance(parent);
  }

  private void verifyClassical(Genre classical) {

    Genre parent = verify(classical, Genre.PK_CLASSICAL, "Classical");
    verifyOrchestral(parent);
  }

  private void verifyInstrumental(Genre instrumental) {

    Genre parent = verify(instrumental, Genre.PK_INSTRUMENTAL, "Instrumental");
    verifyMusic(parent);
  }

  private void verifyAcid(Genre acid) {

    Genre parent = verify(acid, Genre.PK_ACID, "Acid");
    verifyTechno(parent);
  }

  private void verifyHouse(Genre house) {

    Genre parent = verify(house, Genre.PK_HOUSE, "House");
    verifyAmerican(parent);
  }

  private void verifyGame(Genre game) {

    Genre parent = verify(game, Genre.PK_GAME, "Game", longs(), "Video Game Music", "VGM");
    verifyMusic(parent);
  }

  private void verifySoundClip(Genre soundClip) {

    Genre parent = verify(soundClip, Genre.PK_SOUND_CLIP, "Sound Clip");
    verifyMusic(parent);
  }

  private void verifyGospel(Genre gospel) {

    Genre parent = verify(gospel, Genre.PK_GOSPEL, "Gospel");
    verifyChristian(parent);
  }

  private void verifyNoise(Genre noise) {

    Genre parent = verify(noise, Genre.PK_NOISE, "Noise");
    verifyMusic(parent);
  }

  private void verifyAlternativeRock(Genre alternativeRock) {

    Genre parent = verify(alternativeRock, Genre.PK_ALTERNATIVE_ROCK, "Alternative Rock");
    verifyRock(parent);
  }

  private void verifyBass(Genre bass) {

    Genre parent = verify(bass, Genre.PK_BASS, "Bass");
    verifyDance(parent);
  }

  private void verifySoul(Genre soul) {

    Genre parent = verify(soul, Genre.PK_SOUL, "Soul");
    verifyAmerican(parent);
  }

  private void verifyPunk(Genre punk) {

    Genre parent = verify(punk, Genre.PK_PUNK, "Punk");
    verifyRock(parent);
  }

  private void verifySpace(Genre space) {

    Genre parent = verify(space, Genre.PK_SPACE, "Space", Genre.PK_AMBIENT);
    verifyNewAge(parent);
  }

  private void verifyMeditative(Genre meditative) {

    Genre parent = verify(meditative, Genre.PK_MEDITATIVE, "Meditative");
    verifyMusic(parent);
  }

  private void verifyInstrumentalPop(Genre instrumentalPop) {

    Genre parent = verify(instrumentalPop, Genre.PK_INSTRUMENTAL_POP, "Instrumental Pop", Genre.PK_INSTRUMENTAL);
    verifyPop(parent);
  }

  private void verifyInstrumentalRock(Genre instrumentalRock) {

    Genre parent = verify(instrumentalRock, Genre.PK_INSTRUMENTAL_ROCK, "Instrumental Rock", Genre.PK_INSTRUMENTAL);
    verifyRock(parent);
  }

  private void verifyEthnic(Genre ethnic) {

    Genre parent = verify(ethnic, Genre.PK_ETHNIC, "Ethnic");
    verifyFolk(parent);
  }

  private void verifyGothic(Genre gothic) {

    Genre parent = verify(gothic, Genre.PK_GOTHIC, "Gothic");
    verifyWestMusic(parent);
  }

  private void verifyDarkWave(Genre darkWave) {

    Genre parent = verify(darkWave, Genre.PK_DARKWAVE, "Darkwave");
    verifyNewWave(parent);
  }

  private void verifyTechnoIndustrial(Genre technoIndustrial) {

    Genre parent = verify(technoIndustrial, Genre.PK_TECHNO_INDUSTRIAL, "Techno-Industrial", Genre.PK_INDUSTRIAL);
    verifyTechno(parent);
  }

  private void verifyElectronic(Genre electronic) {

    Genre parent = verify(electronic, Genre.PK_ELECTRONIC, "Electronic");
    verifyWestMusic(parent);
  }

  private void verifyPopFolk(Genre popFolk) {

    Genre parent = verify(popFolk, Genre.PK_POP_FOLK, "Pop-Folk", Genre.PK_FOLK);
    verifyPop(parent);
  }

  private void verifyEuroDance(Genre euroDance) {

    Genre parent = verify(euroDance, Genre.PK_EURO_DANCE, "Eurodance", Genre.PK_ELECTRONIC);
    verifyDance(parent);
  }

  private void verifyDream(Genre dream) {

    Genre parent = verify(dream, Genre.PK_DREAM, "Dream");
    verifyAlternativeRock(parent);
  }

  private void verifySouthernRock(Genre southernRock) {

    Genre parent = verify(southernRock, Genre.PK_SOUTHERN_ROCK, "Southern Rock", Genre.PK_AMERICANA);
    verifyRock(parent);
  }

  private void verifyComedy(Genre comedy) {

    Genre parent = verify(comedy, Genre.PK_COMEDY, "Comedy");
    verifySpeech(parent);
  }

  private void verifyCult(Genre cult) {

    Genre parent = verify(cult, Genre.PK_CULT, "Cult");
    verifyFolk(parent);
  }

  private void verifyGangsta(Genre gangsta) {

    Genre parent = verify(gangsta, Genre.PK_GANGSTA, "Gangsta");
    verifyHipHop(parent);
  }

  private void verifyTop40(Genre top40) {

    Genre parent = verify(top40, Genre.PK_TOP_40, "Top 40");
    verifyPop(parent);
  }

  private void verifyChristianRap(Genre christianRap) {

    Genre parent = verify(christianRap, Genre.PK_CHRISTIAN_RAP, "Christian Rap", Genre.PK_CHRISTIAN);
    verifyHipHop(parent);
  }

  private void verifyPopFunk(Genre popFunk) {

    Genre parent = verify(popFunk, Genre.PK_POP_FUNK, "Pop/Funk", Genre.PK_FUNK);
    verifyPop(parent);
  }

  private void verifyJungle(Genre jungle) {

    Genre parent = verify(jungle, Genre.PK_JUNGLE, "Jungle");
    verifyDance(parent);
  }

  private void verifyNativeUs(Genre nativeUs) {

    Genre parent = verify(nativeUs, Genre.PK_NATIVE_US, "Native US");
    verifyIndigenous(parent);
  }

  private void verifyCabaret(Genre cabaret) {

    Genre parent = verify(cabaret, Genre.PK_CABARET, "Cabaret");
    verifyEntertainment(parent);
  }

  private void verifyNewWave(Genre newWave) {

    Genre parent = verify(newWave, Genre.PK_NEW_WAVE, "New Wave");
    verifyPop(parent);
  }

  private void verifyPsychedelic(Genre psychedelic) {

    Genre parent = verify(psychedelic, Genre.PK_PSYCHEDELIC, "Psychedelic");
    verifyPop(parent);
  }

  private void verifyRave(Genre rave) {

    Genre parent = verify(rave, Genre.PK_RAVE, "Rave", Genre.PK_ELECTRONIC);
    verifyDance(parent);
  }

  private void verifyShowtunes(Genre showtunes) {

    Genre parent = verify(showtunes, Genre.PK_SHOWTUNES, "Showtunes");
    verifyEntertainment(parent);
  }

  private void verifyTrailer(Genre trailer) {

    Genre parent = verify(trailer, Genre.PK_TRAILER, "Trailer");
    verifySoundtrack(parent);
  }

  private void verifyLoFi(Genre loFi) {

    Genre parent = verify(loFi, Genre.PK_LO_FI, "Lo-Fi");
    verifyFolk(parent);
  }

  private void verifyTribal(Genre tribal) {

    Genre parent = verify(tribal, Genre.PK_TRIBAL, "Tribal");
    verifyHouse(parent);
  }

  private void verifyAcidPunk(Genre acidPunk) {

    Genre parent = verify(acidPunk, Genre.PK_ACID_PUNK, "Acid Punk", longs(Genre.PK_PUNK), "Neo-Psychedelia");
    verifyAcid(parent);
  }

  private void verifyAcidJazz(Genre acidJazz) {

    Genre parent = verify(acidJazz, Genre.PK_ACID_JAZZ, "Acid Jazz", longs(Genre.PK_JAZZ), "Club Jazz",
        "Psychedelic Jazz", "Groove Jazz");
    verifyPsychedelic(parent);
  }

  private void verifyPolka(Genre polka) {

    Genre parent = verify(polka, Genre.PK_POLKA, "Polka");
    verifyFolkDance(parent);
  }

  private void verifyRetro(Genre retro) {

    Genre parent = verify(retro, Genre.PK_RETRO, "Retro");
    verifyFolk(parent);
  }

  private void verifyMusical(Genre musical) {

    Genre parent = verify(musical, Genre.PK_MUSICAL, "Musical");
    verifyEntertainment(parent);
  }

  private void verifyRockAndRoll(Genre rockAndRoll) {

    Genre parent = verify(rockAndRoll, Genre.PK_ROCK_AND_ROLL, "Rock & Roll", longs(), "RNR", "Rock 'n' Roll");
    verifySwingDance(parent);
  }

  private void verifyHardRock(Genre hardRock) {

    Genre parent = verify(hardRock, Genre.PK_HARD_ROCK, "Hard Rock");
    verifyRock(parent);
  }

  private void verifyFolk(Genre folk) {

    Genre parent = verify(folk, Genre.PK_FOLK, "Folk");
    verifyMusic(parent);
  }

  private void verifyFolkRock(Genre folk) {

    Genre parent = verify(folk, Genre.PK_FOLK_ROCK, "Folk Rock", Genre.PK_FOLK);
    verifyRock(parent);
  }

  private void verifyNationalFolk(Genre nationalFolk) {

    Genre parent = verify(nationalFolk, Genre.PK_NATIONAL_FOLK, "National Folk");
    verifyFolk(parent);
  }

  private void verifySwing(Genre swing) {

    Genre parent = verify(swing, Genre.PK_SWING, "Swing");
    verifyFolk(parent);
  }

  private void verifyFastFusion(Genre fastFusion) {

    Genre parent = verify(fastFusion, Genre.PK_FAST_FUSION, "Fast Fusion");
    verifyFusion(parent);
  }

  private void verifyBebop(Genre bebop) {

    Genre parent = verify(bebop, Genre.PK_BEBOP, "Bebop");
    verifyJazz(parent);
  }

  private void verifyLatin(Genre latin) {

    Genre parent = verify(latin, Genre.PK_LATIN, "Latin");
    verifyFolk(parent);
  }

  private void verifyRevival(Genre revival) {

    Genre parent = verify(revival, Genre.PK_REVIVAL, "Revival");
    verifyTraditional(parent);
  }

  private void verifyCeltic(Genre celtic) {

    Genre parent = verify(celtic, Genre.PK_CELTIC, "Celtic");
    verifyTraditional(parent);
  }

  private void verifyBluegrass(Genre bluegrass) {

    Genre parent = verify(bluegrass, Genre.PK_BLUEGRASS, "Bluegrass");
    verifyAmerican(parent);
  }

  private void verifyAvantgarde(Genre avantgarde) {

    Genre parent = verify(avantgarde, Genre.PK_AVANTGARDE, "Avantgarde");
    verifyMusic(parent);
  }

  private void verifyGothicRock(Genre gothicRock) {

    Genre parent = verify(gothicRock, Genre.PK_GOTHIC_ROCK, "Gothic Rock", Genre.PK_GOTHIC);
    verifyRock(parent);
  }

  private void verifyProgressiveRock(Genre progressiveRock) {

    Genre parent = verify(progressiveRock, Genre.PK_PROGRESSIVE_ROCK, "Progressive Rock");
    verifyRock(parent);
  }

  private void verifyPsychedelicRock(Genre psychedelicRock) {

    Genre parent = verify(psychedelicRock, Genre.PK_PSYCHEDELIC_ROCK, "Psychedelic Rock", Genre.PK_PSYCHEDELIC);
    verifyRock(parent);
  }

  private void verifySymphonicRock(Genre symphonicRock) {

    Genre parent = verify(symphonicRock, Genre.PK_SYMPHONIC_ROCK, "Symphonic Rock");
    verifyRock(parent);
  }

  private void verifySlowRock(Genre slowRock) {

    Genre parent = verify(slowRock, Genre.PK_SLOW_ROCK, "Slow Rock");
    verifyRock(parent);
  }

  private void verifyBigBand(Genre bigBand) {

    Genre parent = verify(bigBand, Genre.PK_BIG_BAND, "Big Band");
    verifyJazz(parent);
  }

  private void verifyChorus(Genre chorus) {

    Genre parent = verify(chorus, Genre.PK_CHORUS, "Chorus");
    verifyVocal(parent);
  }

  private void verifyEasyListening(Genre easyListening) {

    Genre parent = verify(easyListening, Genre.PK_EASY_LISTENING, "Easy Listening");
    verifyNewAge(parent);
  }

  private void verifyAcoustic(Genre acoustic) {

    Genre parent = verify(acoustic, Genre.PK_ACOUSTIC, "Acoustic");
    verifyMusic(parent);
  }

  private void verifyHumour(Genre humour) {

    Genre parent = verify(humour, Genre.PK_HUMOUR, "Humour");
    verifySpeech(parent);
  }

  private void verifySpeech(Genre speech) {

    Genre parent = verify(speech, Genre.PK_SPEECH, "Speech", longs(), "Spoken Word");
    verifyAudio(parent);
  }

  private void verifyChanson(Genre chanson) {

    Genre parent = verify(chanson, Genre.PK_CHANSON, "Chanson");
    verifyFolk(parent);
  }

  private void verifyOpera(Genre opera) {

    Genre parent = verify(opera, Genre.PK_OPERA, "Opera");
    verifyClassical(parent);
  }

  private void verifyChamber(Genre chamber) {

    Genre parent = verify(chamber, Genre.PK_CHAMBER, "Chamber");
    verifyClassical(parent);
  }

  private void verifySonata(Genre sonata) {

    Genre parent = verify(sonata, Genre.PK_SONATA, "Sonata");
    verifyClassical(parent);
  }

  private void verifySymphony(Genre symphony) {

    Genre parent = verify(symphony, Genre.PK_SYMPHONY, "Symphony");
    verifyClassical(parent);
  }

  private void verifyBootyBass(Genre bootyBass) {

    Genre parent = verify(bootyBass, Genre.PK_BOOTY_BASS, "Booty Bass");
    verifyBass(parent);
  }

  private void verifyPrimus(Genre primus) {

    Genre parent = verify(primus, Genre.PK_PRIMUS, "Primus");
    verifyRock(parent);
  }

  private void verifyPornGroove(Genre pornGroove) {

    Genre parent = verify(pornGroove, Genre.PK_PORN_GROOVE, "Porn Groove");
    verifySoundtrack(parent);
  }

  private void verifySatire(Genre satire) {

    Genre parent = verify(satire, Genre.PK_SATIRE, "Satire");
    verifySpeech(parent);
  }

  private void verifySlowJam(Genre slowJam) {

    Genre parent = verify(slowJam, Genre.PK_SLOW_JAM, "Slow Jam", Genre.PK_SOUL);
    verifyRnB(parent);
  }

  private void verifyAmerican(Genre american) {

    Genre parent = verify(american, 212, "American");
    verifyWestMusic(parent);
  }

  private void verifyWestMusic(Genre westMusic) {

    Genre parent = verify(westMusic, 211, "West Music");
    verifyFolk(parent);
  }

  private void verifyMusic(Genre music) {

    Genre parent = verify(music, Genre.PK_MUSIC, "Music", true);
    verifyAudio(parent);
  }

  private void verifyAudio(Genre audio) {

    Genre parent = verify(audio, Genre.PK_AUDIO, "Audio", true);
    assertThat(parent).isNull();
  }

  private void verifyPsybient(Genre psybient) {

    Genre parent = verify(psybient, Genre.PK_PSYBIENT, "Psybient", longs(Genre.PK_PSYCHEDELIC, Genre.PK_AMBIENT),
        "Psychedelic Ambient", "Ambient Psy");
    verifyTrance(parent);
  }

  private void verifyTraditional(Genre traditional) {

    Genre parent = verify(traditional, 300, "Traditional");
    verifyFolk(parent);
  }

  private void verifyFolkDance(Genre folkDance) {

    Genre parent = verify(folkDance, 229, "Folk Dance", true);
    verifyMusic(parent);
  }

  private void verifyBallroomDance(Genre ballroomDance) {

    Genre parent = verify(ballroomDance, 230, "Ballroom Dance", true);
    verifyFolkDance(parent);
  }

  private void verifyStandardDance(Genre standardDance) {

    Genre parent = verify(standardDance, 231, "Standard Dance", true);
    verifyBallroomDance(parent);
  }

  private void verifySwingDance(Genre swingDance) {

    Genre parent = verify(swingDance, 232, "Swing Dance", 231);
    verifySwing(parent);
  }

  private void verifyFoxtrott(Genre foxtrott) {

    Genre parent = verify(foxtrott, 239, "Foxtrott");
    verifyStandardDance(parent);
  }

  private void verifyQuickstep(Genre quickstep) {

    Genre parent = verify(quickstep, 241, "Quickstep");
    verifyFoxtrott(parent);
  }

  private void verifySlowfox(Genre slowfox) {

    Genre parent = verify(slowfox, 242, "Slowfox");
    verifyFoxtrott(parent);
  }

  private void verifyIndigenous(Genre indigenous) {

    Genre parent = verify(indigenous, 517, "Indigenous");
    verifyFolk(parent);
  }

  private void verifyOrchestral(Genre orchestral) {

    Genre parent = verify(orchestral, 600, "Orchestral", true);
    verifyWestMusic(parent);
  }

  private void verifyUtility(Genre utitliy) {

    Genre parent = verify(utitliy, 900, "Utility", true);
    verifyMusic(parent);
  }

  private void verifyReligious(Genre religious) {

    Genre parent = verify(religious, 910, "Religious");
    verifyUtility(parent);
  }

  private void verifyChristian(Genre christian) {

    Genre parent = verify(christian, 911, "Christian");
    verifyReligious(parent);
  }

  private void verifyEntertainment(Genre entertainment) {

    Genre parent = verify(entertainment, 930, "Entertainment");
    verifyUtility(parent);
  }

  private Id<Genre> id(long pk) {

    return new PkIdLong<>(Genre.class, pk);
  }

  private Long[] longs(int... ints) {

    Long[] longs = new Long[ints.length];
    for (int i = 0; i < ints.length; i++) {
      longs[i] = Long.valueOf(ints[i]);
    }
    return longs;
  }

  private Genre verify(Genre genre, int pk, String title, int... relativesIds) {

    return verify(genre, pk, title, false, relativesIds);
  }

  private Genre verify(Genre genre, int pk, String title, boolean isAbstract, int... relativesIds) {

    return verify(genre, pk, title, isAbstract, longs(relativesIds));
  }

  private Genre verify(Genre genre, long pk, String title, Long[] relativesIds, String... synonyms) {

    return verify(genre, pk, title, false, relativesIds, synonyms);
  }

  private Genre verify(Genre genre, long pk, String title, boolean isAbstract, Long[] relativesIds,
      String... synonyms) {

    assertThat(genre.Title().get()).isEqualTo(title);
    Id<?> id = genre.Id().get();
    assertThat(id.get()).as("Genre " + title + " has ID " + pk).isEqualTo(pk);
    assertThat(id.getEntityClass()).isSameAs(Genre.class);
    assertThat(genre.Abstract().getValue()).as("Genre " + title + " is " + (isAbstract ? "" : "not ") + "abstract")
        .isEqualTo(isAbstract);
    assertThat(genre.Synonyms().getAsSet()).containsExactlyInAnyOrder(synonyms);
    Set<Link<Genre>> relatives = genre.Relatives().get();
    if (relatives == null) {
      assertThat(relativesIds).isEmpty();
    } else {
      assertThat(relatives.stream().map(l -> l.getId().get())).containsExactlyInAnyOrder((Object[]) relativesIds);
    }
    verifySearch(title, genre);
    for (String synonym : synonyms) {
      verifySearch(synonym, genre);
    }
    Link<Genre> link = genre.Parent().get();
    if (link == null) {
      return null;
    }
    return link.getTarget();
  }

  private void verifySearch(String key, Genre expectedGenre) {

    assertThat(this.genres.findByTitleOrSynonym(key)).isSameAs(expectedGenre);
    assertThat(this.genres.findByTitleOrSynonym(key.toLowerCase(Locale.ROOT))).isSameAs(expectedGenre);
    assertThat(this.genres.findByTitleOrSynonym(key.toUpperCase(Locale.ROOT))).isSameAs(expectedGenre);
  }

}
