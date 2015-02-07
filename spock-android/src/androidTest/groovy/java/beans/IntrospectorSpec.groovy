package java.beans

import spock.lang.Specification
import spock.lang.Unroll;

class IntrospectorSpec extends Specification {
  @Unroll
  def "should lowercase first letter of a string unless multiple capitalized letters"() {
    expect:
    Introspector.decapitalize(value) == expected

    where:
    // from decapitalize javadoc
    value <<    [null, "", "  ", "FooBah", "X", "URL"]
    expected << [null, "", "  ", "fooBah", "x", "URL"]
  }
}
