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
package org.sakaiproject.nakamura.opensso;

import org.apache.sling.jcr.jackrabbit.server.security.AuthenticationPlugin;
import org.sakaiproject.nakamura.opensso.OpenSsoAuthenticationHandler.OpenSsoAuthentication;
import org.sakaiproject.nakamura.trusted.AbstractLoginModule;

import java.security.Principal;

import javax.jcr.Credentials;
import javax.jcr.RepositoryException;

/**
 *
 */
public class OpenSsoLoginModule extends AbstractLoginModule {

  /**
   * {@inheritDoc}
   * @see org.apache.sling.jcr.jackrabbit.server.security.LoginModulePlugin#getAuthentication(java.security.Principal, javax.jcr.Credentials)
   */
  public AuthenticationPlugin getAuthentication(Principal principal, Credentials creds)
      throws RepositoryException {
    try {
      return new OpenSsoAuthenticationPlugin(principal, creds);
    } catch ( IllegalArgumentException e ) {
      return null;      
    }  
  }

  /**
   * {@inheritDoc}
   * @see org.sakaiproject.nakamura.trusted.AbstractLoginModule#isAuthenticationValid(java.lang.Object)
   */
  @Override
  protected boolean isAuthenticationValid(Object authObj) {
    if (authObj instanceof OpenSsoAuthentication) {
      OpenSsoAuthentication openSsoAuthentication = (OpenSsoAuthentication) authObj;
      return openSsoAuthentication.isValid();
    }
    return false;
  }

}
