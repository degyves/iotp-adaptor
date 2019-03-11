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
 *     Michael Fiedler     - initial API and implementation for Bugzilla adapter
 *     Jad El-khoury       - initial implementation of code generator (https://bugs.eclipse.org/bugs/show_bug.cgi?id=422448)
 *     Matthieu Helleboid  - Support for multiple Service Providers.
 *     Anass Radouani      - Support for multiple Service Providers.
 *
 * This file is generated by org.eclipse.lyo.oslc4j.codegenerator
 *******************************************************************************/
// End of user code

package com.ibm.oslc.adaptor.iotp.servlet;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


import com.ibm.oslc.adaptor.iotp.services.GlobalConfigurationService;
import org.eclipse.lyo.oslc4j.application.OslcWinkApplication;
import org.eclipse.lyo.oslc4j.core.exception.OslcCoreApplicationException;
import org.eclipse.lyo.oslc4j.core.model.AllowedValues;
import org.eclipse.lyo.oslc4j.core.model.Compact;
import org.eclipse.lyo.oslc4j.core.model.CreationFactory;
import org.eclipse.lyo.oslc4j.core.model.Dialog;
import org.eclipse.lyo.oslc4j.core.model.Error;
import org.eclipse.lyo.oslc4j.core.model.ExtendedError;
import org.eclipse.lyo.oslc4j.core.model.OAuthConfiguration;
import org.eclipse.lyo.oslc4j.core.model.OslcConstants;
import org.eclipse.lyo.oslc4j.core.model.PrefixDefinition;
import org.eclipse.lyo.oslc4j.core.model.Preview;
import org.eclipse.lyo.oslc4j.core.model.Property;
import org.eclipse.lyo.oslc4j.core.model.Publisher;
import org.eclipse.lyo.oslc4j.core.model.QueryCapability;
import org.eclipse.lyo.oslc4j.core.model.ResourceShape;
import org.eclipse.lyo.oslc4j.core.model.Service;
import org.eclipse.lyo.oslc4j.core.model.ServiceProvider;
import org.eclipse.lyo.oslc4j.core.model.ServiceProviderCatalog;
import org.eclipse.lyo.oslc4j.provider.jena.JenaProvidersRegistry;
import org.eclipse.lyo.oslc4j.provider.json4j.Json4JProvidersRegistry;

import com.ibm.oslc.adaptor.iotp.services.ServiceProviderCatalogService;
import com.ibm.oslc.adaptor.iotp.services.BmxServiceProviderService;
import com.ibm.oslc.adaptor.iotp.services.IotpServiceProviderService;
import com.ibm.oslc.adaptor.iotp.services.ResourceShapeService;

import com.ibm.oslc.adaptor.iotp.resources.App;
import com.ibm.oslc.adaptor.iotp.resources.CFService;
import com.ibm.oslc.adaptor.iotp.resources.ChangeRequest;
import com.ibm.oslc.adaptor.iotp.resources.Device;
import com.ibm.oslc.adaptor.iotp.resources.DeviceInfo;
import com.ibm.oslc.adaptor.iotp.resources.DeviceType;
import com.ibm.oslc.adaptor.iotp.resources.DeviceTypeMapping;
import com.ibm.oslc.adaptor.iotp.resources.Discussion;
import com.ibm.oslc.adaptor.iotp.resources.EventType;
import com.ibm.oslc.adaptor.iotp.resources.Flow;
import com.ibm.oslc.adaptor.iotp.resources.LogicalInterface;
import com.ibm.oslc.adaptor.iotp.resources.MetaData;
import com.ibm.oslc.adaptor.iotp.resources.MetaProperty;
import com.ibm.oslc.adaptor.iotp.resources.NodeREDApp;
import com.ibm.oslc.adaptor.iotp.resources.Person;
import com.ibm.oslc.adaptor.iotp.resources.PhysicalInterface;
import com.ibm.oslc.adaptor.iotp.resources.Requirement;
import com.ibm.oslc.adaptor.iotp.resources.Resource;
import com.ibm.oslc.adaptor.iotp.resources.Rule;
import com.ibm.oslc.adaptor.iotp.resources.Schema;
import com.ibm.oslc.adaptor.iotp.resources.Space;
import com.ibm.oslc.adaptor.iotp.resources.Thing;
import com.ibm.oslc.adaptor.iotp.resources.ThingType;
import com.ibm.oslc.adaptor.iotp.resources.ThingTypeMapping;
import com.ibm.oslc.adaptor.iotp.resources.Oslc_amDomainConstants;
import com.ibm.oslc.adaptor.iotp.resources.Oslc_bmxDomainConstants;
import com.ibm.oslc.adaptor.iotp.resources.Oslc_cmDomainConstants;
import com.ibm.oslc.adaptor.iotp.resources.FoafDomainConstants;
import com.ibm.oslc.adaptor.iotp.resources.Oslc_iotDomainConstants;
import com.ibm.oslc.adaptor.iotp.resources.OslcDomainConstants;
import com.ibm.oslc.adaptor.iotp.resources.Oslc_rmDomainConstants;
import com.ibm.oslc.adaptor.iotp.services.IoTPlatformService;
import com.ibm.oslc.adaptor.iotp.services.BluemixService;

// Start of user code imports
import com.ibm.oslc.adaptor.iotp.trs.TrackedResourceSetService;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.impl.RDFWriterFImpl;
// End of user code

// Start of user code pre_class_code
// End of user code

public class Application extends OslcWinkApplication {

    private static final Set<Class<?>>         RESOURCE_CLASSES                          = new HashSet<Class<?>>();
    private static final Map<String, Class<?>> RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP = new HashMap<String, Class<?>>();

    // Start of user code class_attributes
    // End of user code

    // Start of user code class_methods
    // End of user code

    static
    {
        RESOURCE_CLASSES.addAll(JenaProvidersRegistry.getProviders());
        RESOURCE_CLASSES.addAll(Json4JProvidersRegistry.getProviders());
        RESOURCE_CLASSES.add(IoTPlatformService.class);
        RESOURCE_CLASSES.add(BluemixService.class);

        // Catalog resources
        RESOURCE_CLASSES.add(ServiceProviderCatalogService.class);
        RESOURCE_CLASSES.add(BmxServiceProviderService.class);
        RESOURCE_CLASSES.add(GlobalConfigurationService.class);
        RESOURCE_CLASSES.add(IotpServiceProviderService.class);
        RESOURCE_CLASSES.add(ResourceShapeService.class);

        // Start of user code Custom Resource Classes
        // OAuth service and Swagger.io service
        try {
			RESOURCE_CLASSES.add(Class.forName("org.eclipse.lyo.server.oauth.webapp.services.ConsumersService"));
	        RESOURCE_CLASSES.add(Class.forName("org.eclipse.lyo.server.oauth.webapp.services.OAuthService"));
	        RESOURCE_CLASSES.add(io.swagger.jaxrs.listing.ApiListingResource.class);
	        RESOURCE_CLASSES.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // TRS service	
        RESOURCE_CLASSES.add(TrackedResourceSetService.class);
        
         // trigger Jena init
         ModelFactory.createDefaultModel();
         // force plain XML writer
         RDFWriterFImpl.alternative(null);
        // End of user code

        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_ALLOWED_VALUES,           AllowedValues.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_COMPACT,                  Compact.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_CREATION_FACTORY,         CreationFactory.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_DIALOG,                   Dialog.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_ERROR,                    Error.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_EXTENDED_ERROR,           ExtendedError.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_OAUTH_CONFIGURATION,      OAuthConfiguration.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_PREFIX_DEFINITION,        PrefixDefinition.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_PREVIEW,                  Preview.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_PROPERTY,                 Property.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_PUBLISHER,                Publisher.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_QUERY_CAPABILITY,         QueryCapability.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_RESOURCE_SHAPE,           ResourceShape.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_SERVICE,                  Service.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_SERVICE_PROVIDER,         ServiceProvider.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_SERVICE_PROVIDER_CATALOG, ServiceProviderCatalog.class);

        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(Oslc_bmxDomainConstants.APP_PATH, App.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(Oslc_bmxDomainConstants.CFSERVICE_PATH, CFService.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(Oslc_cmDomainConstants.CHANGEREQUEST_PATH, ChangeRequest.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(Oslc_iotDomainConstants.DEVICE_PATH, Device.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(Oslc_iotDomainConstants.DEVICEINFO_PATH, DeviceInfo.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(Oslc_iotDomainConstants.DEVICETYPE_PATH, DeviceType.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(Oslc_iotDomainConstants.DEVICETYPEMAPPING_PATH, DeviceTypeMapping.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcDomainConstants.DISCUSSION_PATH, Discussion.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(Oslc_iotDomainConstants.EVENTTYPE_PATH, EventType.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(Oslc_bmxDomainConstants.FLOW_PATH, Flow.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(Oslc_iotDomainConstants.LOGICALINTERFACE_PATH, LogicalInterface.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(Oslc_iotDomainConstants.METADATA_PATH, MetaData.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(Oslc_iotDomainConstants.METAPROPERTY_PATH, MetaProperty.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(Oslc_bmxDomainConstants.NODEREDAPP_PATH, NodeREDApp.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(FoafDomainConstants.PERSON_PATH, Person.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(Oslc_iotDomainConstants.PHYSICALINTERFACE_PATH, PhysicalInterface.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(Oslc_rmDomainConstants.REQUIREMENT_PATH, Requirement.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(Oslc_amDomainConstants.RESOURCE_PATH, Resource.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(Oslc_iotDomainConstants.RULE_PATH, Rule.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(Oslc_iotDomainConstants.SCHEMA_PATH, Schema.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(Oslc_bmxDomainConstants.SPACE_PATH, Space.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(Oslc_iotDomainConstants.THING_PATH, Thing.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(Oslc_iotDomainConstants.THINGTYPE_PATH, ThingType.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(Oslc_iotDomainConstants.THINGTYPEMAPPING_PATH, ThingTypeMapping.class);
    }

    public Application()
           throws OslcCoreApplicationException,
                  URISyntaxException
    {
        super(RESOURCE_CLASSES,
              OslcConstants.PATH_RESOURCE_SHAPES,
              RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP);
    }

    public static Map<String, Class<?>> getResourceShapePathToResourceClassMap() {
        return RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP;
    }
}
