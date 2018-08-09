package org.pengfei.Lesson3_Classes

trait TaxiDriver {
  /* can have fields, but can't have constructor parameters*/
  val carType="Taxi"
  /* Normal interface method declaration*/
  def drive():Unit
  /* Implementation of method, very special concept of scala trait*/
  def whichCarICanDrive():String={
    return carType
  }
}
