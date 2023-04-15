import com.github.polomarcus.utils.ClimateService
import com.github.polomarcus.model.CO2Record
import org.scalatest.funsuite.AnyFunSuite

//@See https://www.scalatest.org/scaladoc/3.1.2/org/scalatest/funsuite/AnyFunSuite.html
class ClimateServiceTest extends AnyFunSuite {

  /** TEST OF isClimateRelated() */

  /* test returning false */
  test("containsWordGlobalWarming - non climate related words should return false") {
    /* Test that the function correctly detects the presence of the word "global warming" in a sentence */
    assert( ClimateService.isClimateRelated("pizza") == false)
    /* Test that the function handles edge cases like an empty string input */
    assert( ClimateService.isClimateRelated("") == false)
  }

  /* test returning true */
  test("isClimateRelated - climate related words should return true") {
    /* Test that the function correctly detects the presence of the phrase "climate change" in a sentence */
    assert(ClimateService.isClimateRelated("climate change") == true)
    /* Test that the function correctly detects the presence of the word "IPCC" in a sentence */
    assert(ClimateService.isClimateRelated("IPCC"))
    /* Test that the function correctly detects the presence of a climate-related word when it is in uppercase */
    assert(ClimateService.isClimateRelated("Global Warming") == true)
  }

  /** TEST OF parseRawData() */

  /* test if the function returns the 2 records when both ppm are positives */
  test("parseRawData") {
    // our inputs
    val firstRecord = (2003, 1, 355.2)     //help: to acces 2003 of this tuple, you can do firstRecord._1
    val secondRecord = (2004, 1, 375.2)
    val list1 = List(firstRecord, secondRecord)

    // our output of our method "parseRawData"
    val co2RecordWithType = CO2Record(firstRecord._1, firstRecord._2, firstRecord._3)
    val co2RecordWithType2 = CO2Record(secondRecord._1, secondRecord._2, secondRecord._3)
    val output = List(Some(co2RecordWithType), Some(co2RecordWithType2))

    // we call our function here to test our input and output
    assert(ClimateService.parseRawData(list1) == output)
  }

  /* test if the function returns one None and one record when the first ppm is negative */
  test("parseRawData - 1 negative ppm") {
    // our inputs
    val firstRecord = (2003, 1, -355.2) //help: to acces 2003 of this tuple, you can do firstRecord._1
    val secondRecord = (2004, 1, 375.2)
    val list1 = List(firstRecord, secondRecord)

    // our output of our method "parseRawData"
    val co2RecordWithType2 = CO2Record(secondRecord._1, secondRecord._2, secondRecord._3)
    val output = List(None, Some(co2RecordWithType2))

    // we call our function here to test our input and output
    assert(ClimateService.parseRawData(list1) == output)
  }

  /* test if the function returns a list of None when the ppm are negatives */
  test("parseRawData - 2 negative ppm") {
    // our inputs
    val firstRecord = (2003, 1, -355.2) //help: to acces 2003 of this tuple, you can do firstRecord._1
    val secondRecord = (2004, 1, -375.2)
    val list1 = List(firstRecord, secondRecord)

    // our output of our method "parseRawData"
    val output = List(None, None)

    // we call our function here to test our input and output
    assert(ClimateService.parseRawData(list1) == output)
  }

  /* test if the function returns the records when both input ppm are = 0 */
  test("parseRawData - ppms == 0") {
    // our inputs
    val firstRecord = (2003, 1, 0.0) //help: to acces 2003 of this tuple, you can do firstRecord._1
    val secondRecord = (2004, 1, 0.0)
    val list1 = List(firstRecord, secondRecord)

    // our output of our method "parseRawData"
    val output = List(None, None)

    // we call our function here to test our input and output
  assert(ClimateService.parseRawData(list1) == output)
  }

  /* test if the function returns en empty list when the param list is empty*/
  test("parseRawData - empty list") {
    assert(ClimateService.parseRawData(List.empty) == List.empty)
  }

  //@TODO
  test("filterDecemberData") {
    assert(true == false)
  }
}
