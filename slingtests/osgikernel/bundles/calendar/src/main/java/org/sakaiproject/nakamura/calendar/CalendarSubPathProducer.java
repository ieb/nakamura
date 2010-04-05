/*
 * Licensed to the Sakai Foundation (SF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The SF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.sakaiproject.nakamura.calendar;

import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.component.CalendarComponent;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.component.VFreeBusy;
import net.fortuna.ical4j.model.component.VJournal;
import net.fortuna.ical4j.model.component.VTimeZone;
import net.fortuna.ical4j.model.component.VToDo;
import net.fortuna.ical4j.model.property.DateProperty;

import org.sakaiproject.nakamura.api.resource.SubPathProducer;
import org.sakaiproject.nakamura.util.DateUtils;

/**
 *
 */
public class CalendarSubPathProducer implements SubPathProducer {

  private String subPath;
  private String name;
  private String type;

  public CalendarSubPathProducer(CalendarComponent c) {
    if (c instanceof VEvent) {
      name = ((VEvent) c).getUid().getValue();
      type = VEvent.VEVENT;
    } else if (c instanceof VFreeBusy) {
      DateProperty p = ((VFreeBusy) c).getStartDate();
      name = DateUtils.rfc2445(p.getDate());
      type = Component.VFREEBUSY;
    } else if (c instanceof VJournal) {
      DateProperty p = ((VJournal) c).getStartDate();
      name = DateUtils.rfc2445(p.getDate());
      type = Component.VJOURNAL;
    } else if (c instanceof VTimeZone) {
      name = ((VTimeZone) c).getTimeZoneId().getValue();
      type = Component.VTIMEZONE;
    } else if (c instanceof VToDo) {
      DateProperty p = ((VToDo) c).getStartDate();
      name = DateUtils.rfc2445(p.getDate());
      type = Component.VTODO;
    } else {
      name = "" + c.hashCode();
    }
    
    subPath = name;
  }

  /**
   * {@inheritDoc}
   * 
   * @see org.sakaiproject.nakamura.api.resource.SubPathProducer#getSubPath()
   */
  public String getSubPath() {
    return subPath;
  }
  
  public String getName() {
    return name;
  }

  /**
   * @return
   */
  public String getType() {
    return type.toLowerCase();
  }
}
