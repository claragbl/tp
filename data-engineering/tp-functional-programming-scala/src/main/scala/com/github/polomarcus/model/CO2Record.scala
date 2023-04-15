package com.github.polomarcus.model

case class CO2Record (year: Int,
                      month: Int,
                      ppm: Double
                ) {

  def show() : String = {
    s"""
       |CO2Record from $year/$month :
       |$ppm ppm
       |""".stripMargin
  }

  def isValidPpmValue: Boolean = {
    /**
     * function that test if the ppm value is correct or not
     * @return : true if ppm > 0, false otherwise
     */
    ppm > 0
  }
}