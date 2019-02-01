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
 *     Michael Fiedler      - implementation for Bugzilla adapter
 *     Jad El-khoury        - initial implementation of code generator (https://bugs.eclipse.org/bugs/show_bug.cgi?id=422448)
 *
 * This file is generated by org.eclipse.lyo.oslc4j.codegenerator
 *******************************************************************************/
// End of user code

package com.ibm.oslc.adaptor.iotp.services;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ibm.oslc.adaptor.iotp.resources.CustomServiceProvider;
import org.eclipse.lyo.oslc4j.core.annotation.OslcDialog;
import org.eclipse.lyo.oslc4j.core.annotation.OslcQueryCapability;
import org.eclipse.lyo.oslc4j.core.annotation.OslcService;
import org.eclipse.lyo.oslc4j.core.model.Compact;
import org.eclipse.lyo.oslc4j.core.model.OslcConstants;
import org.eclipse.lyo.oslc4j.core.model.OslcMediaType;
import org.eclipse.lyo.oslc4j.core.model.Service;
import org.eclipse.lyo.oslc4j.core.model.ServiceProvider;

import com.ibm.oslc.adaptor.iotp.CE4IoTConnectorManager;
import com.ibm.oslc.adaptor.iotp.servlet.ServiceProviderCatalogSingleton;

// Start of user code imports
// End of user code

@OslcService(OslcConstants.OSLC_CORE_DOMAIN)
@Path("iotp")
public class IotpServiceProviderService
{
    @Context private HttpServletRequest httpServletRequest;
    @Context private HttpServletResponse httpServletResponse;

    /**
     * RDF/XML, XML and JSON representations of an OSLC Service Provider collection
     * @return
     */
    @OslcDialog
    (
         title = "Service Provider Selection Dialog",
         label = "Service Provider Selection Dialog",
         uri = "",
         hintWidth = "1000px",
         hintHeight = "600px",
         resourceTypes = {OslcConstants.TYPE_SERVICE_PROVIDER},
         usages = {OslcConstants.OSLC_USAGE_DEFAULT}
    )
    @OslcQueryCapability
    (
         title = "Service Provider Query Capability",
         label = "Service Provider Query",
         resourceShape = OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_SERVICE_PROVIDER,
         resourceTypes = {OslcConstants.TYPE_SERVICE_PROVIDER},
         usages = {OslcConstants.OSLC_USAGE_DEFAULT}
    )
    @GET
    
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.APPLICATION_JSON, OslcMediaType.TEXT_TURTLE})
    public CustomServiceProvider[] getServiceProviders()
    {
        httpServletResponse.addHeader("Oslc-Core-Version","2.0");
        return ServiceProviderCatalogSingleton.getServiceProviders(httpServletRequest);
    }

    /**
     * RDF/XML, XML and JSON representations of a single OSLC Service Provider
     *
     * @param iotId
     * @return
     */
    @GET
    @Path("{iotId}")
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.APPLICATION_JSON, OslcMediaType.TEXT_TURTLE})
    public Response getServiceProvider(@PathParam("iotId") final String iotId)
    {
        //httpServletResponse.addHeader("Oslc-Core-Version","2.0");
        //return ServiceProviderCatalogSingleton.getIotpServiceProvider(httpServletRequest, iotId);

        StringBuilder builder = new StringBuilder();
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<rdf:RDF\n" +
                "    xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\n" +
                "    xmlns:dcterms=\"http://purl.org/dc/terms/\"\n" +
                "    xmlns:oslc=\"http://open-services.net/ns/core#\"\n" +


//                "    xmlns:oslc_bmx=\"http://jazz.net/ns/bmx#\"\n" +
//                "    xmlns:oslc_data=\"http://open-services.net/ns/servicemanagement/1.0/\"\n" +
//                "    xmlns:jfs=\"http://jazz.net/xmlns/foundation/1.0/\"\n" +
//                "    xmlns:foaf=\"http://xmlns.com/foaf/0.1/#\"\n" +
//                "    xmlns:oslc_am=\"http://open-services.net/ns/am#\"\n" +
//                "    xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"\n" +
//                "    xmlns:rmTypes=\"http://www.ibm.com/xmlns/rdm/types/\"\n" +
//                "    xmlns:rmWorkflow=\"http://www.ibm.com/xmlns/rdm/workflow/\"\n" +
//                "    xmlns:dc=\"http://purl.org/dc/elements/1.1/\"\n" +
//                "    xmlns:h=\"http://www.w3.org/TR/REC-html40\"\n" +
//                "    xmlns:xs=\"http://schema.w3.org/xs/\"\n" +


                //"    xmlns:oslc_cm=\"http://open-services.net/ns/cm#\"\n" +


//                "    xmlns:oslc_iot=\"http://jazz.net/ns/iot#\"\n" +
//                "    xmlns:oslc_rm=\"http://open-services.net/ns/rm#\"\n" +
//                "    xmlns:rm=\"http://www.ibm.com/xmlns/rdm/rdf/\"\n" +
                "    xmlns:jp=\"http://jazz.net/xmlns/prod/jazz/process/1.0/\"" +
                ">\n" +
                "  <oslc:ServiceProvider rdf:about=\"http://192.168.1.91:8080/iotp/services/iotp/qqaaql\">\n" +
                "    <dcterms:title rdf:parseType=\"Literal\">Service Provider 'qqaaql'</dcterms:title>\n" +
                "    <dcterms:description rdf:parseType=\"Literal\">Service Provider for the IoT Platform Organization services (id: /qqaaql; kind: IoT Platform Service Provider)</dcterms:description>\n" +
                "    <dcterms:created rdf:datatype=\"http://www.w3.org/2001/XMLSchema#dateTime\">2019-01-31T07:22:29.744Z</dcterms:created>\n" +

//                "    <oslc:prefixDefinition>\n" +
//                "      <oslc:PrefixDefinition>\n" +
//                "        <oslc:prefix rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">foaf</oslc:prefix>\n" +
//                "        <oslc:prefixBase rdf:resource=\"http://xmlns.com/foaf/0.1/#\"/>\n" +
//                "      </oslc:PrefixDefinition>\n" +
//                "    </oslc:prefixDefinition>\n" +
//                "    <oslc:prefixDefinition>\n" +
//                "      <oslc:PrefixDefinition>\n" +
//                "        <oslc:prefix rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">dcterms</oslc:prefix>\n" +
//                "        <oslc:prefixBase rdf:resource=\"http://purl.org/dc/terms/\"/>\n" +
//                "      </oslc:PrefixDefinition>\n" +
//                "    </oslc:prefixDefinition>\n" +
//                "    <oslc:prefixDefinition>\n" +
//                "      <oslc:PrefixDefinition>\n" +
//                "        <oslc:prefix rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">oslc</oslc:prefix>\n" +
//                "        <oslc:prefixBase rdf:resource=\"http://open-services.net/ns/core#\"/>\n" +
//                "      </oslc:PrefixDefinition>\n" +
//                "    </oslc:prefixDefinition>\n" +


//                "    <oslc:prefixDefinition>\n" +
//                "      <oslc:PrefixDefinition>\n" +
//                "        <oslc:prefix rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">oslc_cm</oslc:prefix>\n" +
//                "        <oslc:prefixBase rdf:resource=\"http://open-services.net/ns/cm#\"/>\n" +
//                "      </oslc:PrefixDefinition>\n" +
//                "    </oslc:prefixDefinition>\n" +


//                "    <oslc:prefixDefinition>\n" +
//                "      <oslc:PrefixDefinition>\n" +
//                "        <oslc:prefix rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">rdf</oslc:prefix>\n" +
//                "        <oslc:prefixBase rdf:resource=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"/>\n" +
//                "      </oslc:PrefixDefinition>\n" +
//                "    </oslc:prefixDefinition>\n" +
//                "    <oslc:prefixDefinition>\n" +
//                "      <oslc:PrefixDefinition>\n" +
//                "        <oslc:prefix rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">rdfs</oslc:prefix>\n" +
//                "        <oslc:prefixBase rdf:resource=\"http://www.w3.org/2000/01/rdf-schema#\"/>\n" +
//                "      </oslc:PrefixDefinition>\n" +
//                "    </oslc:prefixDefinition>\n" +
//                "    <oslc:prefixDefinition>\n" +
//                "      <oslc:PrefixDefinition>\n" +
//                "        <oslc:prefix rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">oslc_data</oslc:prefix>\n" +
//                "        <oslc:prefixBase rdf:resource=\"http://open-services.net/ns/servicemanagement/1.0/\"/>\n" +
//                "      </oslc:PrefixDefinition>\n" +
//                "    </oslc:prefixDefinition>\n" +
//                "    <dcterms:identifier rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">/qqaaql</dcterms:identifier>\n" +
//                "    <oslc:prefixDefinition>\n" +
//                "      <oslc:PrefixDefinition>\n" +
//                "        <oslc:prefix rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">oslc</oslc:prefix>\n" +
//                "        <oslc:prefixBase rdf:resource=\"http://open-services.net/ns/core#\"/>\n" +
//                "      </oslc:PrefixDefinition>\n" +
//                "    </oslc:prefixDefinition>\n" +
//                "    <oslc:prefixDefinition>\n" +
//                "      <oslc:PrefixDefinition>\n" +
//                "        <oslc:prefix rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">oslc_am</oslc:prefix>\n" +
//                "        <oslc:prefixBase rdf:resource=\"http://open-services.net/ns/am#\"/>\n" +
//                "      </oslc:PrefixDefinition>\n" +
//                "    </oslc:prefixDefinition>\n" +
//                "    <oslc:details rdf:resource=\"http://192.168.1.91:8080/iotp/services/iotp/qqaaql\"/>\n" +
//                "    <oslc:prefixDefinition>\n" +
//                "      <oslc:PrefixDefinition>\n" +
//                "        <oslc:prefix rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">oslc_rm</oslc:prefix>\n" +
//                "        <oslc:prefixBase rdf:resource=\"http://open-services.net/ns/rm#\"/>\n" +
//                "      </oslc:PrefixDefinition>\n" +
//                "    </oslc:prefixDefinition>\n" +
//                "    <oslc:prefixDefinition>\n" +
//                "      <oslc:PrefixDefinition>\n" +
//                "        <oslc:prefix rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">rdf</oslc:prefix>\n" +
//                "        <oslc:prefixBase rdf:resource=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"/>\n" +
//                "      </oslc:PrefixDefinition>\n" +
//                "    </oslc:prefixDefinition>\n" +
//                "    <oslc:prefixDefinition>\n" +
//                "      <oslc:PrefixDefinition>\n" +
//                "        <oslc:prefix rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">oslc_iot</oslc:prefix>\n" +
//                "        <oslc:prefixBase rdf:resource=\"http://jazz.net/ns/iot#\"/>\n" +
//                "      </oslc:PrefixDefinition>\n" +
//                "    </oslc:prefixDefinition>\n" +
//                "    <oslc:prefixDefinition>\n" +
//                "      <oslc:PrefixDefinition>\n" +
//                "        <oslc:prefix rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">dcterms</oslc:prefix>\n" +
//                "        <oslc:prefixBase rdf:resource=\"http://purl.org/dc/terms/\"/>\n" +
//                "      </oslc:PrefixDefinition>\n" +
//                "    </oslc:prefixDefinition>\n" +



                "    <oslc:service>\n" +
                "      <oslc:Service>\n" +
//                "        <oslc:queryCapability>\n" +
//                "          <oslc:QueryCapability>\n" +
//                "            <oslc:label rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">Schema Query Capability</oslc:label>\n" +
//                "            <oslc:queryBase rdf:resource=\"http://192.168.1.91:8080/iotp/services/iotp/qqaaql/resources/schema\"/>\n" +
//                "            <oslc:resourceType rdf:resource=\"http://jazz.net/ns/iot#Schema\"/>\n" +
//                "            <oslc:resourceShape rdf:resource=\"http://192.168.1.91:8080/iotp/services/resourceShapes/schema\"/>\n" +
//                "            <dcterms:title rdf:parseType=\"Literal\">SchemaQueryCapability</dcterms:title>\n" +
//                "          </oslc:QueryCapability>\n" +
//                "        </oslc:queryCapability>\n" +
//                "        <oslc:queryCapability>\n" +
//                "          <oslc:QueryCapability>\n" +
//                "            <oslc:label rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">Device Type Query Capability</oslc:label>\n" +
//                "            <oslc:queryBase rdf:resource=\"http://192.168.1.91:8080/iotp/services/iotp/qqaaql/resources/devicetype\"/>\n" +
//                "            <oslc:resourceType rdf:resource=\"http://jazz.net/ns/iot#DeviceType\"/>\n" +
//                "            <oslc:resourceShape rdf:resource=\"http://192.168.1.91:8080/iotp/services/resourceShapes/deviceType\"/>\n" +
//                "            <dcterms:title rdf:parseType=\"Literal\">DeviceTypeQueryCapability</dcterms:title>\n" +
//                "          </oslc:QueryCapability>\n" +
//                "        </oslc:queryCapability>\n" +
//                "        <oslc:creationFactory>\n" +
//                "          <oslc:CreationFactory>\n" +
//                "            <oslc:label rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">Device Type Creation Factory</oslc:label>\n" +
//                "            <oslc:resourceType rdf:resource=\"http://jazz.net/ns/iot#DeviceType\"/>\n" +
//                "            <oslc:resourceShape rdf:resource=\"http://192.168.1.91:8080/iotp/services/resourceShapes/deviceType\"/>\n" +
//                "            <oslc:creation rdf:resource=\"http://192.168.1.91:8080/iotp/services/iotp/qqaaql/resources/devicetype\"/>\n" +
//                "            <dcterms:title rdf:parseType=\"Literal\">DeviceTypeCreationFactory</dcterms:title>\n" +
//                "          </oslc:CreationFactory>\n" +
//                "        </oslc:creationFactory>\n" +
//                "        <oslc:creationFactory>\n" +
//                "          <oslc:CreationFactory>\n" +
//                "            <oslc:label rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">ThingCreationFactory</oslc:label>\n" +
//                "            <oslc:resourceType rdf:resource=\"http://jazz.net/ns/iot#Thing\"/>\n" +
//                "            <oslc:resourceShape rdf:resource=\"http://192.168.1.91:8080/iotp/services/resourceShapes/thing\"/>\n" +
//                "            <oslc:creation rdf:resource=\"http://192.168.1.91:8080/iotp/services/iotp/qqaaql/resources/thing\"/>\n" +
//                "            <dcterms:title rdf:parseType=\"Literal\">ThingCreationFactory</dcterms:title>\n" +
//                "          </oslc:CreationFactory>\n" +
//                "        </oslc:creationFactory>\n" +
//                "        <oslc:creationFactory>\n" +
//                "          <oslc:CreationFactory>\n" +
//                "            <oslc:label rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">Thing Type Creation Factory</oslc:label>\n" +
//                "            <oslc:resourceType rdf:resource=\"http://jazz.net/ns/iot#ThingType\"/>\n" +
//                "            <oslc:resourceShape rdf:resource=\"http://192.168.1.91:8080/iotp/services/resourceShapes/thingType\"/>\n" +
//                "            <oslc:creation rdf:resource=\"http://192.168.1.91:8080/iotp/services/iotp/qqaaql/resources/thingtype\"/>\n" +
//                "            <dcterms:title rdf:parseType=\"Literal\">ThingTypeCreationFactory</dcterms:title>\n" +
//                "          </oslc:CreationFactory>\n" +
//                "        </oslc:creationFactory>\n" +
//                "        <oslc:creationFactory>\n" +
//                "          <oslc:CreationFactory>\n" +
//                "            <oslc:label rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">Physical Interface Creation Factory</oslc:label>\n" +
//                "            <oslc:resourceType rdf:resource=\"http://jazz.net/ns/iot#PhysicalInterface\"/>\n" +
//                "            <oslc:resourceShape rdf:resource=\"http://192.168.1.91:8080/iotp/services/resourceShapes/physicalInterface\"/>\n" +
//                "            <oslc:creation rdf:resource=\"http://192.168.1.91:8080/iotp/services/iotp/qqaaql/resources/physicalinterface\"/>\n" +
//                "            <dcterms:title rdf:parseType=\"Literal\">PhysicalInterfaceCreationFactory</dcterms:title>\n" +
//                "          </oslc:CreationFactory>\n" +
//                "        </oslc:creationFactory>\n" +
//                "        <oslc:queryCapability>\n" +
//                "          <oslc:QueryCapability>\n" +
//                "            <oslc:label rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">Thing Type Query Capability</oslc:label>\n" +
//                "            <oslc:queryBase rdf:resource=\"http://192.168.1.91:8080/iotp/services/iotp/qqaaql/resources/thingtype\"/>\n" +
//                "            <oslc:resourceType rdf:resource=\"http://jazz.net/ns/iot#ThingType\"/>\n" +
//                "            <oslc:resourceShape rdf:resource=\"http://192.168.1.91:8080/iotp/services/resourceShapes/thingType\"/>\n" +
//                "            <dcterms:title rdf:parseType=\"Literal\">ThingTypeQueryCapability</dcterms:title>\n" +
//                "          </oslc:QueryCapability>\n" +
//                "        </oslc:queryCapability>\n" +
//                "        <oslc:queryCapability>\n" +
//                "          <oslc:QueryCapability>\n" +
//                "            <oslc:label rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">Physical Inteface Query Capability</oslc:label>\n" +
//                "            <oslc:queryBase rdf:resource=\"http://192.168.1.91:8080/iotp/services/iotp/qqaaql/resources/physicalinterface\"/>\n" +
//                "            <oslc:resourceType rdf:resource=\"http://jazz.net/ns/iot#PhysicalInterface\"/>\n" +
//                "            <oslc:resourceShape rdf:resource=\"http://192.168.1.91:8080/iotp/services/resourceShapes/physicalInterface\"/>\n" +
//                "            <dcterms:title rdf:parseType=\"Literal\">PhysicalIntefaceQueryCapability</dcterms:title>\n" +
//                "          </oslc:QueryCapability>\n" +
//                "        </oslc:queryCapability>\n" +
//                "        <oslc:creationFactory>\n" +
//                "          <oslc:CreationFactory>\n" +
//                "            <oslc:label rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">Rule Creation Factory</oslc:label>\n" +
//                "            <oslc:resourceType rdf:resource=\"http://jazz.net/ns/iot#Rule\"/>\n" +
//                "            <oslc:resourceShape rdf:resource=\"http://192.168.1.91:8080/iotp/services/resourceShapes/rule\"/>\n" +
//                "            <oslc:creation rdf:resource=\"http://192.168.1.91:8080/iotp/services/iotp/qqaaql/resources/logicalInterfaceId/rule\"/>\n" +
//                "            <dcterms:title rdf:parseType=\"Literal\">RuleCreationFactory</dcterms:title>\n" +
//                "          </oslc:CreationFactory>\n" +
//                "        </oslc:creationFactory>\n" +
//                "        <oslc:creationFactory>\n" +
//                "          <oslc:CreationFactory>\n" +
//                "            <oslc:label rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">Event Type CreationFactory</oslc:label>\n" +
//                "            <oslc:resourceType rdf:resource=\"http://jazz.net/ns/iot#EventType\"/>\n" +
//                "            <oslc:resourceShape rdf:resource=\"http://192.168.1.91:8080/iotp/services/resourceShapes/eventType\"/>\n" +
//                "            <oslc:creation rdf:resource=\"http://192.168.1.91:8080/iotp/services/iotp/qqaaql/resources/eventtype\"/>\n" +
//                "            <dcterms:title rdf:parseType=\"Literal\">EventTypeCreationFactory</dcterms:title>\n" +
//                "          </oslc:CreationFactory>\n" +
//                "        </oslc:creationFactory>\n" +
//                "        <oslc:queryCapability>\n" +
//                "          <oslc:QueryCapability>\n" +
//                "            <oslc:label rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">DeviceQueryCapability</oslc:label>\n" +
//                "            <oslc:queryBase rdf:resource=\"http://192.168.1.91:8080/iotp/services/iotp/qqaaql/resources/device\"/>\n" +
//                "            <oslc:resourceType rdf:resource=\"http://jazz.net/ns/iot#Device\"/>\n" +
//                "            <oslc:resourceShape rdf:resource=\"http://192.168.1.91:8080/iotp/services/resourceShapes/device\"/>\n" +
//                "            <dcterms:title rdf:parseType=\"Literal\">DeviceQueryCapability</dcterms:title>\n" +
//                "          </oslc:QueryCapability>\n" +
//                "        </oslc:queryCapability>\n" +
                "        <oslc:selectionDialog>\n" +
                "          <oslc:Dialog>\n" +
                "            <oslc:hintHeight rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">325px</oslc:hintHeight>\n" +
                "            <oslc:hintWidth rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">525px</oslc:hintWidth>\n" +
                "            <oslc:resourceType rdf:resource=\"http://open-services.net/ns/am#Resource\"/>\n" +
                "            <dcterms:title rdf:parseType=\"Literal\">IotSelectionDialog</dcterms:title>\n" +
                "            <oslc:usage rdf:resource=\"http://open-services.net/ns/am#IoTPSelectionDialog\"/>\n" +
                "            <oslc:resourceType rdf:resource=\"http://open-services.net/ns/rm#Requirement\"/>\n" +
                "            <oslc:dialog rdf:resource=\"http://192.168.1.91:8080/iotp/services/iotp/qqaaql/resources/selector\"/>\n" +
                "            <oslc:label rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">IoT Platform Selection Dialog</oslc:label>\n" +
                "            <oslc:resourceType rdf:resource=\"http://open-services.net/ns/cm#ChangeRequest\"/>\n" +
                "          </oslc:Dialog>\n" +
                "        </oslc:selectionDialog>\n" +
//                "        <oslc:creationFactory>\n" +
//                "          <oslc:CreationFactory>\n" +
//                "            <oslc:label rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">Logical Interface Creation Factory</oslc:label>\n" +
//                "            <oslc:resourceType rdf:resource=\"http://jazz.net/ns/iot#LogicalInterface\"/>\n" +
//                "            <oslc:resourceShape rdf:resource=\"http://192.168.1.91:8080/iotp/services/resourceShapes/logicalInterface\"/>\n" +
//                "            <oslc:creation rdf:resource=\"http://192.168.1.91:8080/iotp/services/iotp/qqaaql/resources/logicalinterface\"/>\n" +
//                "            <dcterms:title rdf:parseType=\"Literal\">LogicalInterfaceCreationFactory</dcterms:title>\n" +
//                "          </oslc:CreationFactory>\n" +
//                "        </oslc:creationFactory>\n" +
//                "        <oslc:queryCapability>\n" +
//                "          <oslc:QueryCapability>\n" +
//                "            <oslc:label rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">ThingQueryCapability</oslc:label>\n" +
//                "            <oslc:queryBase rdf:resource=\"http://192.168.1.91:8080/iotp/services/iotp/qqaaql/resources/thing\"/>\n" +
//                "            <oslc:resourceType rdf:resource=\"http://jazz.net/ns/iot#Thing\"/>\n" +
//                "            <oslc:resourceShape rdf:resource=\"http://192.168.1.91:8080/iotp/services/resourceShapes/thing\"/>\n" +
//                "            <dcterms:title rdf:parseType=\"Literal\">ThingQueryCapability</dcterms:title>\n" +
//                "          </oslc:QueryCapability>\n" +
//                "        </oslc:queryCapability>\n" +
//                "        <oslc:creationFactory>\n" +
//                "          <oslc:CreationFactory>\n" +
//                "            <oslc:label rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">Schema Creation Factory</oslc:label>\n" +
//                "            <oslc:resourceType rdf:resource=\"http://jazz.net/ns/iot#Schema\"/>\n" +
//                "            <oslc:resourceShape rdf:resource=\"http://192.168.1.91:8080/iotp/services/resourceShapes/schema\"/>\n" +
//                "            <oslc:creation rdf:resource=\"http://192.168.1.91:8080/iotp/services/iotp/qqaaql/resources/schema\"/>\n" +
//                "            <dcterms:title rdf:parseType=\"Literal\">SchemaCreationFactory</dcterms:title>\n" +
//                "          </oslc:CreationFactory>\n" +
//                "        </oslc:creationFactory>\n" +
//                "        <oslc:queryCapability>\n" +
//                "          <oslc:QueryCapability>\n" +
//                "            <oslc:label rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">Event Type Query Capability</oslc:label>\n" +
//                "            <oslc:queryBase rdf:resource=\"http://192.168.1.91:8080/iotp/services/iotp/qqaaql/resources/eventtype\"/>\n" +
//                "            <oslc:resourceType rdf:resource=\"http://jazz.net/ns/iot#EventType\"/>\n" +
//                "            <oslc:resourceShape rdf:resource=\"http://192.168.1.91:8080/iotp/services/resourceShapes/eventType\"/>\n" +
//                "            <dcterms:title rdf:parseType=\"Literal\">EventTypeQueryCapability</dcterms:title>\n" +
//                "          </oslc:QueryCapability>\n" +
//                "        </oslc:queryCapability>\n" +
//                "        <oslc:queryCapability>\n" +
//                "          <oslc:QueryCapability>\n" +
//                "            <oslc:label rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">Rule Query Capability</oslc:label>\n" +
//                "            <oslc:queryBase rdf:resource=\"http://192.168.1.91:8080/iotp/services/iotp/qqaaql/resources/rule\"/>\n" +
//                "            <oslc:resourceType rdf:resource=\"http://jazz.net/ns/iot#Rule\"/>\n" +
//                "            <oslc:resourceShape rdf:resource=\"http://192.168.1.91:8080/iotp/services/resourceShapes/rule\"/>\n" +
//                "            <dcterms:title rdf:parseType=\"Literal\">RuleQueryCapability</dcterms:title>\n" +
//                "          </oslc:QueryCapability>\n" +
//                "        </oslc:queryCapability>\n" +
//                "        <oslc:creationFactory>\n" +
//                "          <oslc:CreationFactory>\n" +
//                "            <oslc:label rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">Device Creation Factory</oslc:label>\n" +
//                "            <oslc:resourceType rdf:resource=\"http://jazz.net/ns/iot#Device\"/>\n" +
//                "            <oslc:resourceShape rdf:resource=\"http://192.168.1.91:8080/iotp/services/resourceShapes/device\"/>\n" +
//                "            <oslc:creation rdf:resource=\"http://192.168.1.91:8080/iotp/services/iotp/qqaaql/resources/typeId/create\"/>\n" +
//                "            <dcterms:title rdf:parseType=\"Literal\">DeviceCreationFactory</dcterms:title>\n" +
//                "          </oslc:CreationFactory>\n" +
//                "        </oslc:creationFactory>\n" +
//                "        <oslc:queryCapability>\n" +
//                "          <oslc:QueryCapability>\n" +
//                "            <oslc:label rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">Logical Interface Query Capability</oslc:label>\n" +
//                "            <oslc:queryBase rdf:resource=\"http://192.168.1.91:8080/iotp/services/iotp/qqaaql/resources/logicalinterface\"/>\n" +
//                "            <oslc:resourceType rdf:resource=\"http://jazz.net/ns/iot#LogicalInterface\"/>\n" +
//                "            <oslc:resourceShape rdf:resource=\"http://192.168.1.91:8080/iotp/services/resourceShapes/logicalInterface\"/>\n" +
//                "            <dcterms:title rdf:parseType=\"Literal\">LogicalInterfaceQueryCapability</dcterms:title>\n" +
//                "          </oslc:QueryCapability>\n" +
//                "        </oslc:queryCapability>\n" +
                "        <oslc:domain rdf:resource=\"http://jazz.net/ns/iot#\"/>\n" +
                "        <oslc:domain rdf:resource=\"http://open-services.net/ns/rm#\"/>\n" +
                "        <oslc:creationDialog>\n" +
                "          <oslc:Dialog>\n" +
                "            <oslc:usage rdf:resource=\"http://open-services.net/ns/am#IoTPCreationDialog\"/>\n" +
                "            <oslc:resourceType rdf:resource=\"http://open-services.net/ns/am#Resource\"/>\n" +
                "            <oslc:hintWidth rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">525px</oslc:hintWidth>\n" +
                "            <oslc:resourceType rdf:resource=\"http://open-services.net/ns/rm#Requirement\"/>\n" +
                "            <oslc:label rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">Resource Creation Dialog</oslc:label>\n" +
                "            <oslc:hintHeight rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">225px</oslc:hintHeight>\n" +
                "            <oslc:resourceType rdf:resource=\"http://open-services.net/ns/cm#ChangeRequest\"/>\n" +
                "            <oslc:dialog rdf:resource=\"http://192.168.1.91:8080/iotp/services/iotp/qqaaql/resources/creator\"/>\n" +
                "            <dcterms:title rdf:parseType=\"Literal\">ResourceCreationDialog</dcterms:title>\n" +
                "          </oslc:Dialog>\n" +
                "        </oslc:creationDialog>\n" +
                "      </oslc:Service>\n" +
                "    </oslc:service>\n" +



                "\n" +
                "    <jp:globalConfigurationAware>compatible</jp:globalConfigurationAware>\n" +
//                "    <jp:supportOSLCSimpleQuery>true</jp:supportOSLCSimpleQuery>\n" +
//                "    <jp:supportContributionsToLinkIndexProvider>true</jp:supportContributionsToLinkIndexProvider>\n" +
//                "    <jp:supportLinkDiscoveryViaLinkIndexProvider>true</jp:supportLinkDiscoveryViaLinkIndexProvider>\n" +
//                "    <jp:consumerRegistry rdf:resource=\"http://192.168.1.91:8080/iotp/services/iotp/qqaaql\"/>\n" +

                "  </oslc:ServiceProvider>\n" +
                "</rdf:RDF>\n");

        String responseBody = builder.toString();

        return Response.ok().entity(responseBody).header("max-age", 0).header("pragma", "no-cache")
                .header("Cache-Control", "no-cache").header("OSLC-Core-Version", 2.0)
                .header("Content-Length", responseBody.getBytes().length).type(OslcMediaType.APPLICATION_RDF_XML)
                .build();

    }

    /**
     * HTML representation of a single OSLC Service Provider
     *
     * Forwards to serviceprovider_html.jsp to create the html document
     *
     * @param iotId
     */
    @GET
    @Path("{iotId}")
    @Produces(MediaType.TEXT_HTML)
    public void getHtmlServiceProvider(@PathParam("iotId") final String iotId)
    {
        ServiceProvider serviceProvider = ServiceProviderCatalogSingleton.getIotpServiceProvider(httpServletRequest, iotId);
        Service [] services = serviceProvider.getServices();

        httpServletRequest.setAttribute("serviceProvider", serviceProvider);
        httpServletRequest.setAttribute("services", services);
        // Start of user code getHtmlServiceProvider_setAttributes
        // End of user code

        RequestDispatcher rd = httpServletRequest.getRequestDispatcher("/com/ibm/oslc/adaptor/iotp/iotpserviceprovider.jsp");
        try {
            rd.forward(httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            e.printStackTrace();
            throw new WebApplicationException(e);
        }
    }
}

