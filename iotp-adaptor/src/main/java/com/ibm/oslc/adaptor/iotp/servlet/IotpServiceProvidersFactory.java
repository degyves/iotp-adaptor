// Start of user code Copyright
/*******************************************************************************
 * Copyright (c) 2012 IBM Corporation and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompanies this distribution.
 *  
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *
 *     Russell Boykin       - initial API and implementation
 *     Alberto Giammaria    - initial API and implementation
 *     Chris Peters         - initial API and implementation
 *     Gianluca Bernardini  - initial API and implementation
 *     Michael Fiedler      - Bugzilla adapter implementation
 *     Jad El-khoury        - initial implementation of code generator (https://bugs.eclipse.org/bugs/show_bug.cgi?id=422448)
 *     Matthieu Helleboid   - Allow Service Provider Factory class to be specific for each defined ServiceProvider
 *     Anass Radouani       - Allow Service Provider Factory class to be specific for each defined ServiceProvider
 *
 * This file is generated by org.eclipse.lyo.oslc4j.codegenerator
 *******************************************************************************/
// End of user code

package com.ibm.oslc.adaptor.iotp.servlet;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.eclipse.lyo.oslc4j.client.ServiceProviderRegistryURIs;
import org.eclipse.lyo.oslc4j.core.exception.OslcCoreApplicationException;
import org.eclipse.lyo.oslc4j.core.model.OslcConstants;
import org.eclipse.lyo.oslc4j.core.model.PrefixDefinition;
import org.eclipse.lyo.oslc4j.core.model.Publisher;
import org.eclipse.lyo.oslc4j.core.model.ServiceProvider;
import org.eclipse.lyo.oslc4j.core.model.ServiceProviderFactory;

import com.ibm.oslc.adaptor.iotp.resources.Oslc_amDomainConstants;
import com.ibm.oslc.adaptor.iotp.resources.Oslc_cmDomainConstants;
import com.ibm.oslc.adaptor.iotp.resources.DctermsDomainConstants;
import com.ibm.oslc.adaptor.iotp.resources.FoafDomainConstants;
import com.ibm.oslc.adaptor.iotp.resources.Oslc_iotDomainConstants;
import com.ibm.oslc.adaptor.iotp.resources.OslcDomainConstants;
import com.ibm.oslc.adaptor.iotp.resources.RdfDomainConstants;
import com.ibm.oslc.adaptor.iotp.resources.Oslc_rmDomainConstants;
import com.ibm.oslc.adaptor.iotp.services.IoTPlatformService;

// Start of user code imports
// End of user code

public class IotpServiceProvidersFactory
{
    private static Class<?>[] RESOURCE_CLASSES =
    {
        IoTPlatformService.class
    };

    private IotpServiceProvidersFactory()
    {
        super();
    }

    public static ServiceProvider createServiceProvider(final String baseURI, final String title, final String description, final Publisher publisher, final Map<String,Object> parameterValueMap)
           throws OslcCoreApplicationException, URISyntaxException
    {
        final ServiceProvider serviceProvider = ServiceProviderFactory.createServiceProvider(baseURI,
                                                    ServiceProviderRegistryURIs.getUIURI(),
                                                    title,
                                                    description,
                                                    publisher,
                                                    RESOURCE_CLASSES,
                                                    parameterValueMap);
        URI detailsURIs[] = {new URI(baseURI)};
        serviceProvider.setDetails(detailsURIs);

        final PrefixDefinition[] prefixDefinitions =
        {
            new PrefixDefinition(OslcConstants.DCTERMS_NAMESPACE_PREFIX, new URI(OslcConstants.DCTERMS_NAMESPACE)),
            new PrefixDefinition(OslcConstants.OSLC_CORE_NAMESPACE_PREFIX, new URI(OslcConstants.OSLC_CORE_NAMESPACE)),
            new PrefixDefinition(OslcConstants.OSLC_DATA_NAMESPACE_PREFIX, new URI(OslcConstants.OSLC_DATA_NAMESPACE)),
            new PrefixDefinition(OslcConstants.RDF_NAMESPACE_PREFIX, new URI(OslcConstants.RDF_NAMESPACE)),
            new PrefixDefinition(OslcConstants.RDFS_NAMESPACE_PREFIX, new URI(OslcConstants.RDFS_NAMESPACE)),
            new PrefixDefinition(Oslc_amDomainConstants.ARCHITECTURE_MANAGEMENT_NAMSPACE_PREFIX, new URI(Oslc_amDomainConstants.ARCHITECTURE_MANAGEMENT_NAMSPACE))
,
            new PrefixDefinition(Oslc_cmDomainConstants.CHANGE_MANAGEMENT_NAMSPACE_PREFIX, new URI(Oslc_cmDomainConstants.CHANGE_MANAGEMENT_NAMSPACE))
,
            new PrefixDefinition(DctermsDomainConstants.DUBLIN_CORE_NAMSPACE_PREFIX, new URI(DctermsDomainConstants.DUBLIN_CORE_NAMSPACE))
,
            new PrefixDefinition(FoafDomainConstants.FOAF_NAMSPACE_PREFIX, new URI(FoafDomainConstants.FOAF_NAMSPACE))
,
            new PrefixDefinition(Oslc_iotDomainConstants.IOT_PLATFORM_NAMSPACE_PREFIX, new URI(Oslc_iotDomainConstants.IOT_PLATFORM_NAMSPACE))
,
            new PrefixDefinition(OslcDomainConstants.OSLC_NAMSPACE_PREFIX, new URI(OslcDomainConstants.OSLC_NAMSPACE))
,
            new PrefixDefinition(RdfDomainConstants.RDF_NAMSPACE_PREFIX, new URI(RdfDomainConstants.RDF_NAMSPACE))
,
            new PrefixDefinition(Oslc_rmDomainConstants.REQUIREMENTS_MANAGEMENT_NAMSPACE_PREFIX, new URI(Oslc_rmDomainConstants.REQUIREMENTS_MANAGEMENT_NAMSPACE))
        };

        serviceProvider.setPrefixDefinitions(prefixDefinitions);

        return serviceProvider;
    }
}
