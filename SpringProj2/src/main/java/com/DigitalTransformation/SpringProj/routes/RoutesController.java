package com.DigitalTransformation.SpringProj.routes;

import com.DigitalTransformation.SpringProj.JSONToJava.pojo.Crime;
import com.DigitalTransformation.SpringProj.JSONToJava.pojo.Route;
import com.DigitalTransformation.SpringProj.MapItem;
import com.DigitalTransformation.SpringProj.crime.CrimeMapItem;
import com.DigitalTransformation.SpringProj.crime.CrimeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.*;

//get crime scores for routes: http://localhost:9192/routes/crimeScores/54.581702,-5.937518,54.605536,-5.910661
//get only routes: http://localhost:9192/routes/getRoutes/54.581702,-5.937518,54.605536,-5.910661


//get crime scores for routes: http://localhost:9192/routes/crimeScores/54.581702,-5.937518,54.6011143,-5.9189936
//get only routes: http://localhost:9192/routes/getRoutes/54.581702,-5.937518,54.6011143,-5.9189936

@Controller
@RequestMapping(path="/routes")
public class RoutesController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/getRoutes/{Startlatitude},{Startlongitude},{Endlatitude},{Endlongitude}")
    @ResponseBody
    public ArrayList<ArrayList<MapItem>> getRoutes(@PathVariable Double Startlatitude, @PathVariable Double Startlongitude,@PathVariable Double Endlatitude, @PathVariable Double Endlongitude) {
        /*This method will get the routes from  the google directions API, then extract only the information we need,
        ie. The steps and polyline of each route
         */

        //Here we extract the information from the JSON object into an Arraylist
        //Note that a lot of the data in routesHashMap is completely irrelevant to us
        ArrayList<LinkedHashMap> routesHashMap = getRouteHashMapFromGoogle(Startlatitude, Startlongitude, Endlatitude, Endlongitude);

        //Initialise data structures that will hold the relevant data
        //routes will hold all of the steps for each route that is returned ie. the parent route structure
        ArrayList<ArrayList<MapItem>> routes = new ArrayList<>();

        //Iterate through each route that is retuned
        for (LinkedHashMap route : routesHashMap) {
            //Extract each leg from a route - all of our routes will only ever have 1 leg
            ArrayList<LinkedHashMap> legs = (ArrayList<LinkedHashMap>) route.get("legs");

            //routeSteps will contain a list of steps for each route
            ArrayList<MapItem> routeSteps = new ArrayList<>();
            for (LinkedHashMap leg : legs) {
                //iterate through each leg in a route - all of our routes will only ever have 1 leg
                ArrayList<LinkedHashMap> steps = (ArrayList<LinkedHashMap>) leg.get("steps");
                //Extract the steps from each leg - each leg has an arbritrary number of steps
                for (LinkedHashMap step : steps) {
                    //iterate through each step in the leg
                    LinkedHashMap start = (LinkedHashMap) step.get("start_location");

                    //create 1 mapitem to hold the start position of every step
                    MapItem stepStart = new MapItem();
                    stepStart.setLatitude((double) start.get("lat"));
                    stepStart.setLongitude((double) start.get("lng"));

                    //add our mapItem to our "tidy" data structure
                    routeSteps.add(stepStart);
                }
                //once all the steps along a route have been added, add the final step, which is the destination
                MapItem dest = new MapItem();
                dest.setLongitude(Endlongitude);
                dest.setLatitude(Endlatitude);
                routeSteps.add(dest);
            }
            //Add an entire route to our parent data structure
            routes.add(routeSteps);
        }

        return routes;
    }

    @GetMapping("/getPoly/{RouteNumber},{Startlatitude},{Startlongitude},{Endlatitude},{Endlongitude}")
    @ResponseBody
    public String getPolyLine(@PathVariable int RouteNumber, @PathVariable Double Startlatitude, @PathVariable Double Startlongitude,@PathVariable Double Endlatitude, @PathVariable Double Endlongitude) {

        ArrayList<LinkedHashMap> routesHashMap = getRouteHashMapFromGoogle(Startlatitude, Startlongitude, Endlatitude, Endlongitude);
        ArrayList<String> polylines = new ArrayList<>();

        //Iterate through each route that is retuned
        for (LinkedHashMap route : routesHashMap) {
            //add the polyline of each route to polylines
            //a polyline is used to draw the route on a map
            LinkedHashMap polyline = (LinkedHashMap) route.get("overview_polyline");
            polylines.add((String) polyline.get("points"));
        }

        return polylines.get(RouteNumber);
    }

    private ArrayList<LinkedHashMap> getRouteHashMapFromGoogle(double startLat, double startLng, double endLat, double endLng){
        //Connect to google directions API
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ObjectMapper objectMapper = new ObjectMapper();
        String buildURL = "https://maps.googleapis.com/maps/api/directions/json?origin="+startLat+"," +startLng+ "&destination="+endLat+","+endLng+"&alternatives=true&mode=walking&key=AIzaSyAEBhSjiix5jMy6dKArYoLo8BODJXHeQH8";
        //JSON object contains the response from google directions API
        JSONObject object = restTemplate.getForObject(buildURL, JSONObject.class);

        //Here we extract the information from the JSON object into an Arraylist
        //Note that a lot of the data in routesHashMap is completely irrelevant to us
        ArrayList<LinkedHashMap> routesFromGoogle = (ArrayList<LinkedHashMap>) object.get("routes");
        return routesFromGoogle;

    }


    @GetMapping("/crimeScores/{Startlatitude},{Startlongitude},{Endlatitude},{Endlongitude}")
    @ResponseBody
    public ArrayList<Integer> getCrimeScoreForAllRoutes(@PathVariable Double Startlatitude, @PathVariable Double Startlongitude,
                                                        @PathVariable Double Endlatitude, @PathVariable Double Endlongitude)
    {
        ArrayList<ArrayList<MapItem>> routes = getRoutes(Startlatitude, Startlongitude, Endlatitude, Endlongitude);

        //will store a crime "score" for each route, indicating how dangerous each route is
        //higher crime score is more dangerous, and ideal route has 0 crime score
        ArrayList<Integer> routeCrimeScores = new ArrayList<>();
        for (ArrayList<MapItem> route : routes)
        {
            ArrayList<CrimeMapItem> routeCrime = getAllCrimeForARoute(route);
            int routeCrimeScore = getCrimeScoreForRoute(routeCrime);
            routeCrimeScores.add(routeCrimeScore);
        }

        return routeCrimeScores;
    }

    public ArrayList<CrimeMapItem> getAllCrimeForARoute(ArrayList<MapItem> route) {
        ArrayList<CrimeMapItem> routeCrime = new ArrayList<>();
        for (int i = 0; i < route.size()-1; i++)
        {
            //use the 2 mapItems we have as our first 2 corners of the polygon
            //labelled as corner 1 and 3 as they are diagnol corners of the polygon
            MapItem corner1 = route.get(i);
            MapItem corner3 = route.get(i+1);
            //0.0001 = 10m
            double latMidpoint = (corner1.getLatitude() + corner3.getLatitude())/2;
            double lngMidpoint = (corner1.getLongitude() + corner3.getLongitude())/2;
            MapItem midpoint = new MapItem(lngMidpoint, latMidpoint);


            //generate the final 2 corners using an offset of 10m from the midpoint
            double tenMeters = 0.0001;
            MapItem corner2 = new MapItem(midpoint.getLongitude()+tenMeters, midpoint.getLatitude()+tenMeters);
            MapItem corner4 = new MapItem(midpoint.getLongitude()-tenMeters, midpoint.getLatitude()-tenMeters);

            ArrayList<CrimeMapItem> polyCrime = doHTTPrequestToCrimeService(corner1, corner2, corner3, corner4);
            //polycrime returning as ArrayList of linkedHashMap instead of ArrayList of Crime
            for (CrimeMapItem c : polyCrime) {
                routeCrime.add(c);
            }
        }
        return routeCrime;
    }


    public ArrayList<CrimeMapItem> doHTTPrequestToCrimeService(MapItem corner1, MapItem corner2, MapItem corner3, MapItem corner4) {

        String url = String.format("http://localhost:9192/crime/%f,%f,%f,%f,%f,%f,%f,%f",
                corner1.getLatitude(), corner1.getLongitude(), corner2.getLatitude(), corner2.getLongitude(),
                corner3.getLatitude(), corner3.getLongitude(), corner4.getLatitude(), corner4.getLongitude());

        //tell spring that we are expecting an rrayList<CrimeMapItem> to be returned, then do the request
        ParameterizedTypeReference<ArrayList<CrimeMapItem>> responseType = new ParameterizedTypeReference<ArrayList<CrimeMapItem> >() {};
        RequestEntity<Void> request = RequestEntity.get(url).accept(MediaType.APPLICATION_JSON).build();

        //hit the local spring method getCrimeList in the CrimeService and return the response
        ArrayList<CrimeMapItem>   response = restTemplate.exchange(request, responseType).getBody();
        return response;
    }

    public int getCrimeScoreForRoute(ArrayList<CrimeMapItem> route) {
        //call calculateCrimeScoreForRoute from Crimeservice
        //HttpHeaders headers = new HttpHeaders();
        //headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        int routeScore = 0;
        for (int i = 0; i < route.size(); i++)
        {
           String url = String.format("http://localhost:9192/crime/crimeScore/%s", route.get(i).getCategory());
            //String url = String.format("http://localhost:9192/crime/crimeScore/drugs", route.get(i).getCategory());
            //RequestEntity<Void> request = RequestEntity.get(url).accept(MediaType.APPLICATION_JSON).build();
            ParameterizedTypeReference<Integer> responseType = new ParameterizedTypeReference<Integer>() {};
            RequestEntity<Void> request = RequestEntity.get(url).accept(MediaType.APPLICATION_JSON).build();

            //int crimeScore = restTemplate.getForObject(url, Integer.class);
            int   crimeScore = restTemplate.exchange(request, Integer.class).getBody();
            routeScore += crimeScore;


        }
        return routeScore;
    }



}
