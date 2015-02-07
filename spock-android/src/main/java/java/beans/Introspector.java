package java.beans;

/**
 * Android does not have this class, and Spock needs it for mocking.
 *
 * @author Andrew Reitz
 * @since 1.1
 */
public final class Introspector {

  /**
   * See http://docs.oracle.com/javase/7/docs/api/java/beans/Introspector.html#decapitalize(java.lang.String)
   */
  public static String decapitalize(String value) {
    if (value == null || value.trim().isEmpty()) {
      return value;
    }

    char first = value.charAt(0);
    char lowerFirst = Character.toLowerCase(first);

    // Single character lower case and return
    if (value.length() == 1) {
      return String.valueOf(lowerFirst);
    }

    // Two or more capitolized characters, return un-touched
    if (Character.isUpperCase(first) && Character.isUpperCase(value.charAt(1))) {
      return value;
    }

    return lowerFirst + value.substring(1, value.length());
  }

  private Introspector() {
    // No Instances
  }
}
