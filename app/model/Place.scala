package model

import java.util.{Calendar, Date}

case class Place(name: String,
                 location: LatLng,
                 description: String,
                 category: PlaceCategory.Value,
                 status: PlaceStatus.Value,
                 creationDate: Date,
                 schedules: WeekSchedule) {
  def isOpen(): Boolean = {
    status match {
      case PlaceStatus.Open | PlaceStatus.Closing =>
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DATE)
        schedules.getSchedule(Day(day)).covers(calendar.getTime)
      case PlaceStatus.UnderConstruction | PlaceStatus.Closed => false
    }
  }
}

case class LatLng(latitude: Double, longitude: Double)

object PlaceCategory extends Enumeration {
  val Food, Store, Clothes = Value
}

object PlaceStatus extends Enumeration {
  val Open, UnderConstruction, Closing, Closed = Value
}