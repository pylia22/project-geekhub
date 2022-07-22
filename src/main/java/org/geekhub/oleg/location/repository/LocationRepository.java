package org.geekhub.oleg.location.repository;

import org.geekhub.oleg.event.model.Event;
import org.geekhub.oleg.location.model.Location;

import java.util.List;

public interface LocationRepository {
     List<Location> getCities();
     void save(Location location);
     void update(Event event, long id);
     Location getCityID(Event event);
}
