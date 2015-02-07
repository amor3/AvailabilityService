/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import client.AuthorizationServiceService;
import com.sun.xml.fastinfoset.util.StringArray;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author AMore
 */
@WebService(serviceName = "Availability")
@Stateless()
public class Availability {

    @WebServiceRef(wsdlLocation = "http://localhost:8080/AuthorizationServiceService/AuthorizationService?wsdl")
    private AuthorizationServiceService service;

    private static final List<List<String>> AVAILABLE_ROUTES = new ArrayList<>();

    static {
        // Source, Dest, TravelDate
        AVAILABLE_ROUTES.add(Arrays.asList("TICKET_1", "Stockholm", "Oslo", "24"));
        AVAILABLE_ROUTES.add(Arrays.asList("TICKET_2", "Oslo", "Stockholm", "25"));
        AVAILABLE_ROUTES.add(Arrays.asList("TICKET_3", "Stockholm", "Berlin", "26"));
        AVAILABLE_ROUTES.add(Arrays.asList("TICKET_4", "Berlin", "Stockholm", "27"));
        AVAILABLE_ROUTES.add(Arrays.asList("TICKET_5", "Oslo", "Stockholm", "Berlin", "28"));
        AVAILABLE_ROUTES.add(Arrays.asList("TICKET_6", "Berlin", "Stockholm", "Oslo", "29"));
    }

    /**
     * Checking returns the availability for a given route and the prices for
     * each flight
     *
     * @param username for the specific user
     * @param password for the specific user
     * @param routes contains the given route for the users search
     * @param travelDate the date the user wants to travel
     * @return
     */
    @WebMethod(operationName = "checking")
    public StringArray checking(
            @WebParam(name = "username") String username,
            @WebParam(name = "password") String password,
            @WebParam(name = "routes") String routes,
            @WebParam(name = "travelDate") String travelDate) {

            String[] parsedRoutes = routes.split(":");
            
        if (authorizationServiceOperation(username, password)) {
            return getAvailabilityAndPrice(parsedRoutes, travelDate);
        }

        return null;
    }

    private StringArray getAvailabilityAndPrice(String[] wantedRoute, String date) {
        
        List<String> returnList = new ArrayList<>();
        
        for (List<String> route : AVAILABLE_ROUTES) {
                        
            if (route.get(1).equalsIgnoreCase(wantedRoute[0])
                    && route.get(route.size() - 2).equalsIgnoreCase(wantedRoute[wantedRoute.length - 1])
                    && route.get(route.size() - 1).equalsIgnoreCase(date)) {

                returnList.addAll(route);
                returnList.add(getPrice(route.get(0)));

                return asStringArray(returnList);
            }
        }
        
        returnList.add("Something went wrong when searching for availability...");
        return asStringArray(returnList);
    }

    private String getPrice(String ticketID) {
        return "AnropaPriceCheck123";
    }

    private StringArray asStringArray(List<String> route) {
        StringArray stringArray = new StringArray(route.size(), 5, true);

        for (String city : route) {
            stringArray.add(city);
        }

        return stringArray;
    }

    private boolean authorizationServiceOperation(java.lang.String username, java.lang.String password) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        client.AuthorizationServicePortType port = service.getAuthorizationServicePort();
        return port.authorizationServiceOperation(username, password);
    }

}
