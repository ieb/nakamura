/**
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
package org.sakaiproject.nakamura.api.activity;

/**
 * An abstract implementation of an {@link ActivityRoute}
 */
public abstract class AbstractActivityRoute implements ActivityRoute {

  private String[] readers;
  
  private String destination;

  public AbstractActivityRoute(String destination, String[] readers) {
    this.destination = destination;
    this.readers = readers;
  }

  /**
   * {@inheritDoc}
   * 
   * @see org.sakaiproject.nakamura.api.activity.ActivityRoute#getDestination()
   */
  public String getDestination() {
    return destination;
  }
  
  /**
   * {@inheritDoc}
   * @see org.sakaiproject.nakamura.api.activity.ActivityRoute#getReaders()
   */
  public String[] getReaders() {
    return readers;
  }

}
