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
import org.sakaiproject.nakamura.trusted.AbstractAuthenticationHandler;

import java.security.Principal;

import javax.jcr.Credentials;
import javax.jcr.RepositoryException;
import javax.jcr.SimpleCredentials;

public final class OpenSsoAuthenticationPlugin implements AuthenticationPlugin {
  private final Principal principal;

  public OpenSsoAuthenticationPlugin(Principal principal, Credentials creds) {
    if (canHandle(creds)) {
      this.principal = principal;
      return;
    }
    throw new IllegalArgumentException("Creadentials are not trusted ");
  }

  public static boolean canHandle(Credentials cred) {
    boolean hasAttribute = false;
    if (cred != null && cred instanceof SimpleCredentials) {
      Object attr = ((SimpleCredentials) cred)
          .getAttribute(AbstractAuthenticationHandler.AUTHENTICATION_OBJECT);
      hasAttribute = (attr instanceof OpenSsoAuthentication);
    }
    return hasAttribute;
  }
  
  public boolean authenticate(Credentials credentials) throws RepositoryException {
    boolean auth = false;
    if (credentials instanceof SimpleCredentials) {
      SimpleCredentials sc = (SimpleCredentials) credentials;
      if (principal.getName().equals(sc.getUserID())) {
        auth = true;
      }
    }
    return auth;
  }
}
