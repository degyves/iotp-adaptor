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
 *     Michael Fiedler      - adapted for Bugzilla service provider
 *     Jad El-khoury        - initial implementation of code generator (422448)
 *     Matthieu Helleboid   - initialize each service provider separately
 *     Anass Radouani       - initialize each service provider separately
 *
 * This file is generated by org.eclipse.lyo.oslc4j.codegenerator
 *******************************************************************************/
// End of user code

package com.ibm.oslc.adaptor.iotp.servlet;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import com.ibm.oslc.adaptor.iotp.resources.CustomServiceProvider;
import org.eclipse.lyo.oslc4j.core.model.Publisher;
import org.eclipse.lyo.oslc4j.core.model.Service;
import org.eclipse.lyo.oslc4j.core.model.ServiceProviderCatalog;
import org.eclipse.lyo.oslc4j.core.OSLC4JUtils;

import com.ibm.oslc.adaptor.iotp.CE4IoTConnectorManager;
import com.ibm.oslc.adaptor.iotp.IotpServiceProviderInfo;
import com.ibm.oslc.adaptor.iotp.BmxServiceProviderInfo;

// Start of user code imports
// End of user code

/**
 * This is the OSLC service provider catalog for the Bugzilla adapter.  Service providers are
 * not registered with the catalog until a request comes in to access either the catalog or a
 * specific service provider.   This request could be from an external consumer or an internal
 * request triggered by a consumer accessing a change request.
 *
 * The service providers are created and registered in the initServiceProvidersFromProducts()
 * method.  A list of accessible products is retrieved from Bugzilla and a ServiceProvider is
 * created and registered for each using the Bugzilla productId as the identifier.
 *
 * The registered service providers are refreshed on each catalog or service provider collection
 * request.
 */
public class ServiceProviderCatalogSingleton
{
    private static final ServiceProviderCatalog serviceProviderCatalog;
    private static final SortedMap<String, CustomServiceProvider> serviceProviders = new TreeMap<String, CustomServiceProvider>();

    static {
        serviceProviderCatalog = new ServiceProviderCatalog();
        URI catalogUri = UriBuilder.fromUri(OSLC4JUtils.getServletURI()).path("/catalog/singleton").build();
        serviceProviderCatalog.setAbout(catalogUri);
        serviceProviderCatalog.setTitle("IoT Platform Service Provider Catalog");
        serviceProviderCatalog.setDescription("A Service Provider Catalog describing service providers for IoT Platform Organizations");
    }

    private ServiceProviderCatalogSingleton()
    {
        super();
    }


    public static URI getUri()
    {
        return serviceProviderCatalog.getAbout();
    }

    public static ServiceProviderCatalog getServiceProviderCatalog(HttpServletRequest httpServletRequest)
    {
        initServiceProviders(httpServletRequest);
        return serviceProviderCatalog;
    }

    public static CustomServiceProvider [] getServiceProviders(HttpServletRequest httpServletRequest)
    {
        synchronized(serviceProviders)
        {
            initServiceProviders(httpServletRequest);
            return serviceProviders.values().toArray(new CustomServiceProvider[ serviceProviders.size()]);
        }
    }


    private static URI constructIotpServiceProviderURI(final String iotId)
    {
        String basePath = OSLC4JUtils.getServletURI();
        Map<String, Object> pathParameters = new HashMap<String, Object>();
        pathParameters.put("iotId", iotId);
        String instanceURI = "iotp/{iotId}";

        final UriBuilder builder = UriBuilder.fromUri(basePath);
        return builder.path(instanceURI).buildFromMap(pathParameters);
    }

    private static String iotpServiceProviderIdentifier(final String iotId)
    {
        String identifier = "/" + iotId;
        return identifier;
    }

    public static CustomServiceProvider getIotpServiceProvider(HttpServletRequest httpServletRequest, final String iotId)
    {
        CustomServiceProvider serviceProvider;

        synchronized(serviceProviders)
        {
            String identifier = iotpServiceProviderIdentifier(iotId);
            serviceProvider = serviceProviders.get(identifier);

            //One retry refreshing the service providers
            if (serviceProvider == null)
            {
                getServiceProviders(httpServletRequest);
                serviceProvider = serviceProviders.get(identifier);
            }
        }

        if (serviceProvider != null)
        {
            return serviceProvider;
        }

        throw new WebApplicationException(Status.NOT_FOUND);
    }

    public static CustomServiceProvider registerIotpServiceProvider(final HttpServletRequest httpServletRequest,
                                                          final CustomServiceProvider serviceProvider,
                                                          final String iotId)
                                                throws URISyntaxException
    {
        synchronized(serviceProviders)
        {
            final URI serviceProviderURI = constructIotpServiceProviderURI(iotId);
            return registerIotpServiceProviderNoSync(serviceProviderURI,
                                                 serviceProvider,
                                                 iotId);
        }
    }

    /**
    * Register a service provider with the OSLC catalog
    *
    * @param serviceProviderURI
    * @param serviceProvider
    * @param iotId
    * @return
    */
    private static CustomServiceProvider registerIotpServiceProviderNoSync(final URI serviceProviderURI,
                                                                 final CustomServiceProvider serviceProvider
                                                                 , final String iotId)
    {
        final SortedSet<URI> serviceProviderDomains = getServiceProviderDomains(serviceProvider);

        String identifier = iotpServiceProviderIdentifier(iotId);
        serviceProvider.setAbout(serviceProviderURI);
        serviceProvider.setIdentifier(identifier);
        serviceProvider.setCreated(new Date());
        serviceProvider.setDetails(new URI[] {serviceProviderURI});

        serviceProviderCatalog.addServiceProvider(serviceProvider);
        serviceProviderCatalog.addDomains(serviceProviderDomains);

        serviceProviders.put(identifier, serviceProvider);

        return serviceProvider;
    }

    // This version is for self-registration and thus package-protected
    static CustomServiceProvider registerIotpServiceProvider(final CustomServiceProvider serviceProvider, final String iotId)
                                            throws URISyntaxException
    {
        synchronized(serviceProviders)
        {
            final URI serviceProviderURI = constructIotpServiceProviderURI(iotId);

            return registerIotpServiceProviderNoSync(serviceProviderURI, serviceProvider, iotId);
        }
    }

    public static void deregisterIotpServiceProvider(final String iotId)
    {
        synchronized(serviceProviders)
        {
            final CustomServiceProvider deregisteredServiceProvider =
                serviceProviders.remove(iotpServiceProviderIdentifier(iotId));

            if (deregisteredServiceProvider != null)
            {
                final SortedSet<URI> remainingDomains = new TreeSet<URI>();

                for (final CustomServiceProvider remainingServiceProvider : serviceProviders.values())
                {
                    remainingDomains.addAll(getServiceProviderDomains(remainingServiceProvider));
                }

                final SortedSet<URI> removedServiceProviderDomains = getServiceProviderDomains(deregisteredServiceProvider);

                removedServiceProviderDomains.removeAll(remainingDomains);
                serviceProviderCatalog.removeDomains(removedServiceProviderDomains);
                serviceProviderCatalog.removeServiceProvider(deregisteredServiceProvider);
            }
            else
            {
                throw new WebApplicationException(Status.NOT_FOUND);
            }
        }
    }

    private static URI constructBmxServiceProviderURI(final String bmxId)
    {
        String basePath = OSLC4JUtils.getServletURI();
        Map<String, Object> pathParameters = new HashMap<String, Object>();
        pathParameters.put("bmxId", bmxId);
        String instanceURI = "bmx/{bmxId}";

        final UriBuilder builder = UriBuilder.fromUri(basePath);
        return builder.path(instanceURI).buildFromMap(pathParameters);
    }

    private static String bmxServiceProviderIdentifier(final String bmxId)
    {
        String identifier = "/" + bmxId;
        return identifier;
    }

    public static CustomServiceProvider getBmxServiceProvider(HttpServletRequest httpServletRequest, final String bmxId)
    {
        CustomServiceProvider serviceProvider;

        synchronized(serviceProviders)
        {
            String identifier = bmxServiceProviderIdentifier(bmxId);
            serviceProvider = serviceProviders.get(identifier);

            //One retry refreshing the service providers
            if (serviceProvider == null)
            {
                getServiceProviders(httpServletRequest);
                serviceProvider = serviceProviders.get(identifier);
            }
        }

        if (serviceProvider != null)
        {
            return serviceProvider;
        }

        throw new WebApplicationException(Status.NOT_FOUND);
    }

    public static CustomServiceProvider registerBmxServiceProvider(final HttpServletRequest httpServletRequest,
                                                          final CustomServiceProvider serviceProvider,
                                                          final String bmxId)
                                                throws URISyntaxException
    {
        synchronized(serviceProviders)
        {
            final URI serviceProviderURI = constructBmxServiceProviderURI(bmxId);
            return registerBmxServiceProviderNoSync(serviceProviderURI,
                                                 serviceProvider,
                                                 bmxId);
        }
    }

    /**
    * Register a service provider with the OSLC catalog
    *
    * @param serviceProviderURI
    * @param serviceProvider
    * @param bmxId
    * @return
    */
    private static CustomServiceProvider registerBmxServiceProviderNoSync(final URI serviceProviderURI,
                                                                 final CustomServiceProvider serviceProvider
                                                                 , final String bmxId)
    {
        final SortedSet<URI> serviceProviderDomains = getServiceProviderDomains(serviceProvider);

        String identifier = bmxServiceProviderIdentifier(bmxId);
        serviceProvider.setAbout(serviceProviderURI);
        serviceProvider.setIdentifier(identifier);
        serviceProvider.setCreated(new Date());
        serviceProvider.setDetails(new URI[] {serviceProviderURI});

        serviceProviderCatalog.addServiceProvider(serviceProvider);
        serviceProviderCatalog.addDomains(serviceProviderDomains);

        serviceProviders.put(identifier, serviceProvider);

        return serviceProvider;
    }

    // This version is for self-registration and thus package-protected
    static CustomServiceProvider registerBmxServiceProvider(final CustomServiceProvider serviceProvider, final String bmxId)
                                            throws URISyntaxException
    {
        synchronized(serviceProviders)
        {
            final URI serviceProviderURI = constructBmxServiceProviderURI(bmxId);

            return registerBmxServiceProviderNoSync(serviceProviderURI, serviceProvider, bmxId);
        }
    }

    public static void deregisterBmxServiceProvider(final String bmxId)
    {
        synchronized(serviceProviders)
        {
            final CustomServiceProvider deregisteredServiceProvider =
                serviceProviders.remove(bmxServiceProviderIdentifier(bmxId));

            if (deregisteredServiceProvider != null)
            {
                final SortedSet<URI> remainingDomains = new TreeSet<URI>();

                for (final CustomServiceProvider remainingServiceProvider : serviceProviders.values())
                {
                    remainingDomains.addAll(getServiceProviderDomains(remainingServiceProvider));
                }

                final SortedSet<URI> removedServiceProviderDomains = getServiceProviderDomains(deregisteredServiceProvider);

                removedServiceProviderDomains.removeAll(remainingDomains);
                serviceProviderCatalog.removeDomains(removedServiceProviderDomains);
                serviceProviderCatalog.removeServiceProvider(deregisteredServiceProvider);
            }
            else
            {
                throw new WebApplicationException(Status.NOT_FOUND);
            }
        }
    }

    private static SortedSet<URI> getServiceProviderDomains(final CustomServiceProvider serviceProvider)
    {
        final SortedSet<URI> domains = new TreeSet<URI>();

        if (serviceProvider!=null) {
            final Service [] services = serviceProvider.getServices();
            for (final Service service : services)
            {
                final URI domain = service.getDomain();

                domains.add(domain);
            }
        }

        domains.add(URI.create("http://jazz.net/xmlns/prod/jazz/process/1.0/"));

        return domains;
    }

    /**
     * Retrieve a list of products from Bugzilla and construct a service provider for each.
     *
     * Each product ID is added to the parameter map which will be used during service provider
     * creation to create unique URI paths for each Bugzilla product.  See @Path definition at
     * the top of BugzillaChangeRequestService.
     *
     * @param httpServletRequest
     */
    protected static void initServiceProviders (HttpServletRequest httpServletRequest)
    {
        try {
            // Start of user code initServiceProviders
            // End of user code

            String basePath = OSLC4JUtils.getServletURI();

            IotpServiceProviderInfo [] iotpServiceProviderInfos = CE4IoTConnectorManager.getIotpServiceProviderInfos(httpServletRequest);
            //Register each service provider
            for (IotpServiceProviderInfo serviceProviderInfo : iotpServiceProviderInfos) {
                String identifier = iotpServiceProviderIdentifier(serviceProviderInfo.iotId);
                if (!serviceProviders.containsKey(identifier)) {
                    String serviceProviderName = serviceProviderInfo.name;
                    String title = String.format("Service Provider '%s'", serviceProviderName);
                    String description = String.format("%s (id: %s; kind: %s)",
                        "Service Provider for the IoT Platform Organization services",
                        identifier,
                        "IoT Platform Service Provider");
                    Publisher publisher = null;
                    Map<String, Object> parameterMap = new HashMap<String, Object>();
                    parameterMap.put("iotId", serviceProviderInfo.iotId);
                    final CustomServiceProvider aServiceProvider = IotpServiceProvidersFactory.createServiceProvider(basePath, title, description, publisher, parameterMap);
                    registerIotpServiceProvider(aServiceProvider, serviceProviderInfo.iotId);
                }
            }

            BmxServiceProviderInfo [] bmxServiceProviderInfos = CE4IoTConnectorManager.getBmxServiceProviderInfos(httpServletRequest);
            //Register each service provider
            for (BmxServiceProviderInfo serviceProviderInfo : bmxServiceProviderInfos) {
                String identifier = bmxServiceProviderIdentifier(serviceProviderInfo.bmxId);
                if (!serviceProviders.containsKey(identifier)) {
                    String serviceProviderName = serviceProviderInfo.name;
                    String title = String.format("Service Provider '%s'", serviceProviderName);
                    String description = String.format("%s (id: %s; kind: %s)",
                        "Bluemix Service Provider",
                        identifier,
                        "Bluemix Service Provider");
                    Publisher publisher = null;
                    Map<String, Object> parameterMap = new HashMap<String, Object>();
                    parameterMap.put("bmxId", serviceProviderInfo.bmxId);
                    final CustomServiceProvider aServiceProvider = BmxServiceProvidersFactory.createServiceProvider(basePath, title, description, publisher, parameterMap);
                    registerBmxServiceProvider(aServiceProvider, serviceProviderInfo.bmxId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new WebApplicationException(e,Status.INTERNAL_SERVER_ERROR);
        }
    }
}

