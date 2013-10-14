/*
 * ixtlan_gettext - helper to use fast_gettext with datamapper/ixtlan
 * Copyright (C) 2012 Christian Meier
 *
 * This file is part of ixtlan_gettext.
 *
 * ixtlan_gettext is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * ixtlan_gettext is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with ixtlan_gettext.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.mkristian.ixtlan.gwt.client.domains;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gwt.event.shared.EventBus;

import de.mkristian.ixtlan.gwt.readonly.ReadonlyFactory;
import de.mkristian.ixtlan.gwt.readonly.ReadonlyRemoteAdapter;
import de.mkristian.ixtlan.gwt.utils.NetworkIndicator;

@Singleton
public class DomainRemoteReadOnly extends ReadonlyRemoteAdapter<Domain> {

    private final DomainRestService restService;
    
    @Inject
    protected DomainRemoteReadOnly( NetworkIndicator networkindicator, 
            EventBus eventBus, 
            DomainRestService restService,
            ReadonlyFactory<Domain, ?> factory ) {
        super( eventBus, networkindicator, factory );
        this.restService = restService;
    }

    @Override
    public void retrieveAll() {
        networkIndicator.loading();
        restService.index(newRetrieveAllCallback());
    }

    @Override
    public void retrieve(int id) {
        networkIndicator.loading();
        restService.show(id, newRetrieveCallback());
    }
}