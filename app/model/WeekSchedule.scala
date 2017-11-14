package model

import java.text.SimpleDateFormat
import java.util.Date

import scala.collection.mutable.ListBuffer

class WeekSchedule {
  protected val daySchedules = new Array[Schedule](Day.values.size)
  def getSchedule(day: Day.Value): Schedule = daySchedules(day.id)
}

class Schedule(protected val slots: ListBuffer[TimeSlot]) {
  def addSlot(slot: TimeSlot): Unit = {
    // TODO:
  }
  def getSlots(): List[TimeSlot] = slots.toList
  def covers(time: Date): Boolean = {
    val timeFormat = new SimpleDateFormat("HH:mm")
    val formattedTime = timeFormat.format(time)
    slots.exists { s => timeFormat.format(s.from) <= formattedTime && timeFormat.format(s.to) >= formattedTime }
  }
}

case class TimeSlot(from: Date, to: Date) {

}

object Day extends Enumeration {
  val Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday = Value
}
