package io.github.musicdoc.property;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;

/**
 * Utility class to {@link #normalize(String) normalize} {@link String}s.
 *
 * @see #normalize(String)
 */
public final class StringNormalizer implements Function<String, String> {

  private static final StringNormalizer INSTANCE = new StringNormalizer();

  private static final String _AND_ = " and ";

  private static final String AND = "and";

  private static final Map<Character, String> MAPPINGS = new HashMap<>();

  static {
    //
    map('_', " ");
    map('-', " ");
    map('&', _AND_);
    map('+', _AND_);
    // German umlauts
    map('ß', "ss");
    map('ä', "ae");
    map('ö', "oe");
    map('ü', "ue");
    // Greek
    map('α', "a"); // alpha
    map('β', "b"); // beta
    map('γ', "g"); // gamma
    map('δ', "d"); // delta
    map('ε', "e"); // epsilon
    map('ζ', "z"); // zeta
    map('η', "e"); // eta
    map('ή', "e"); // eta with tonos
    map('θ', "th"); // theta
    map('ι', "i"); // iota
    map('ϊ', "i"); // iota with dialytika
    map('ί', "i"); // iota with tonos
    map('κ', "k"); // kappa
    map('λ', "l"); // lamda
    map('μ', "m"); // mu
    map('ν', "n"); // nu
    map('ξ', "x"); // xi
    map('ο', "o"); // omicron
    map('π', "p"); // pi
    map('ρ', "r"); // rho
    map('σ', "s"); // sigma
    map('ς', "s"); // final sigma
    map('τ', "t"); // tau
    map('υ', "y"); // upsion
    map('ϋ', "y"); // upsion with dialytika
    map('φ', "f"); // phi
    map('χ', "ch"); // chi
    map('ψ', "ps"); // psi
    map('ω', "o"); // omega
    map('ϝ', "f"); // digamma
    map('ͱ', "h"); // heta
    map('ϟ', "q"); // koppa
    map('ϻ', "s"); // san
    map('ϡ', "ss"); // sampi
  }

  private static void map(char c, String mapping) {

    MAPPINGS.put(Character.valueOf(c), mapping);
  }

  /**
   * @param value the value to normalize.
   * @return the normalized value. Will be transliterated to only contain Latin {@link Character#isLowerCase(char) lower
   *         case} letters and regular digits (0-9). Also includes stemming of the words "a" and "the" so "The Beatles"
   *         becomes "beatles" and "A Hard Day's Night" becomes "hard days night".
   */
  public static String normalize(String value) {

    return normalize(value, false);
  }

  /**
   * @param value the value to normalize.
   * @param removeSpaces - {@code true} to also remove whitespaces, {@code false} otherwise.
   * @return the normalized value. Will be transliterated to only contain Latin {@link Character#isLowerCase(char) lower
   *         case} letters and regular digits (0-9). Also includes stemming of the words "a" and "the" so "The Beatles"
   *         becomes "beatles" and "A Hard Day's Night" becomes "hard days night".
   */
  public static String normalize(String value, boolean removeSpaces) {

    if (value == null) {
      return null;
    }
    String key = value.toLowerCase(Locale.ROOT);
    String and = _AND_;
    if (removeSpaces) {
      and = AND;
    }
    key = key.replace("'n'", and).replace("&", and).replace("+", and).trim();
    int length = key.length();
    StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      char c = key.charAt(i);
      if (((c >= 'a') && (c <= 'z')) || ((c >= '0') && (c <= '9')) || (c == ' ')) {
        sb.append(c);
      } else {
        String mapping = MAPPINGS.get(Character.valueOf(c));
        if (mapping != null) {
          sb.append(mapping);
        }
      }
    }
    key = sb.toString();
    key = key.replaceAll("(^| )(a|the) ", "$1");
    if (removeSpaces) {
      key = key.replace(" ", "");
    } else {
      key = key.replaceAll(" +", " ");
    }
    return key;
  }

  @Override
  public String apply(String t) {

    return normalize(t);
  }

  /**
   * @return the singleton instance of {@link StringNormalizer}.
   */
  public static StringNormalizer get() {

    return INSTANCE;
  }
}
