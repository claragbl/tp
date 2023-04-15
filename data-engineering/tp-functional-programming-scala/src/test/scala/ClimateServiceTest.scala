import com.github.polomarcus.utils.ClimateService
import com.github.polomarcus.model.CO2Record
import org.scalatest.funsuite.AnyFunSuite

import javax.crypto.spec.ChaCha20ParameterSpec

//@See https://www.scalatest.org/scaladoc/3.1.2/org/scalatest/funsuite/AnyFunSuite.html
class ClimateServiceTest extends AnyFunSuite {

  /** TEST OF isClimateRelated() */

  /** test returning false */
  test("containsWordGlobalWarming - non climate related words should return false") {
    /* Test that the function correctly detects the presence of the word "global warming" in a sentence */
    assert( ClimateService.isClimateRelated("pizza") == false)
    /* Test that the function handles edge cases like an empty string input */
    assert( ClimateService.isClimateRelated("") == false)
  }

  /** test returning true */
  test("isClimateRelated - climate related words should return true") {
    /* Test that the function correctly detects the presence of the phrase "climate change" in a sentence */
    assert(ClimateService.isClimateRelated("climate change") == true)
    /* Test that the function correctly detects the presence of the word "IPCC" in a sentence */
    assert(ClimateService.isClimateRelated("IPCC"))
    /* Test that the function correctly detects the presence of a climate-related word when it is in uppercase */
    assert(ClimateService.isClimateRelated("Global Warming") == true)
  }

  /** TEST OF parseRawData() */

  /** test if the function returns the 2 records when both ppm are positives */
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

  /** test if the function returns one None and one record when the first ppm is negative */
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

  /** test if the function returns a list of None when the ppm are negatives */
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

  /** test if the function returns the records when both input ppm are = 0 */
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

  /** TEST OF getMinMaxByYear() */

  /** test if the function returns correctly the min and the max  */
  test("getMinMaxByYear - return the min and max, random case") {
  // inputs
  val listCO2Record = List(CO2Record(2003, 5, 897.9), CO2Record(2006, 2, 408.8), CO2Record(2003, 9, 239.9), CO2Record(2003, 3, 100.8), CO2Record(2008, 3, 100.8))
  val year = 2003

  // output
  val output = Some(100.8, 897.9)

  // we call our function here to test our input and output
    assert(ClimateService.getMinMaxByYear(listCO2Record, year) == output)
  }

  /** test if the function returns None if the list in parameter is empty */
  test("getMinMaxByYear - empty list") {
    // inputs
    val listCO2Record = List.empty[CO2Record]
    val year = 2003

    // we call our function here to test our input and output
    assert(ClimateService.getMinMaxByYear(listCO2Record, year) == None)
  }

  /** test if the function returns None if year in input is not present in the list in input */
  test("getMinMaxByYear - year not present in the list") {
    // inputs
    val listCO2Record = List(CO2Record(2003, 5, 897.9), CO2Record(2006, 2, 408.8), CO2Record(2003, 9, 239.9), CO2Record(2003, 3, 100.8), CO2Record(2008, 3, 100.8))
    val year = 2009

    // we call our function here to test our input and output
    assert(ClimateService.getMinMaxByYear(listCO2Record, year) == None)
  }

  /** test if the function returns the same min and max when min == max  */
  test("getMinMaxByYear - return the same value for min and max, when min == max") {
    // inputs
    val listCO2Record = List(CO2Record(2003, 5, 100.8), CO2Record(2006, 2, 408.8), CO2Record(2003, 3, 100.8), CO2Record(2008, 3, 100.8))
    val year = 2003

    // output
    val output = Some(100.8, 100.8)

    // we call our function here to test our input and output
    assert(ClimateService.getMinMaxByYear(listCO2Record, year) == output)
  }

  /** TEST OF filterDecemberData() */

  test("filterDecemberData - normal case") {
    // inputs
    val listCO2Record = List(Some(CO2Record(2003, 12, 897.9)), Some(CO2Record(2006, 2, 408.8)), Some(CO2Record(2003, 12, 239.9)),
      Some(CO2Record(2003, 9, 100.8)), Some(CO2Record(2008, 11, 100.8)))

    // output
    val output = List(CO2Record(2006, 2, 408.8), CO2Record(2003, 9, 100.8), CO2Record(2008, 11, 100.8))

    // we call our function here to test our input and output
    assert(ClimateService.filterDecemberData(listCO2Record) == output)
  }

  /** test when there is not month of december in the data */
  test("filterDecemberData - no month of december") {
    // inputs
    val listCO2Record = List(Some(CO2Record(2003, 7, 897.9)), Some(CO2Record(2006, 2, 408.8)), Some(CO2Record(2003, 1, 239.9)),
      Some(CO2Record(2003, 9, 100.8)), Some(CO2Record(2008, 11, 100.8)))

    // output
    val output = List(CO2Record(2003, 7, 897.9), CO2Record(2006, 2, 408.8), CO2Record(2003, 1, 239.9),
      CO2Record(2003, 9, 100.8), CO2Record(2008, 11, 100.8))

    // we call our function here to test our input and output
    assert(ClimateService.filterDecemberData(listCO2Record) == output)
  }

}
