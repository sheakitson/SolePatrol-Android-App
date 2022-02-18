package com.DigitalTransformation.SpringProj.crime;

import com.DigitalTransformation.SpringProj.JSONToJava.pojo.Crime;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The crime controller handles all REST requests associated with the crime data.
 * Crime API used: https://data.police.uk/
 *
 */

@RestController
@RequestMapping("/crime")
public class CrimeService {

    //We search for crime over the last available 4 months, change this to increase/decress timespan in months
    static final int NUMBER_OF_MONTHS = 4;

    @Autowired
    RestTemplate restTemplate;

    /**
     * This method returns a list of crime based on polygon areas.
     */
    @GetMapping(value = "/{corner1Lat},{corner1Lng},{corner2Lat},{corner2Lng},{corner3Lat},{corner3Lng},{corner4Lat},{corner4Lng}")
    public ArrayList<CrimeMapItem> getCrimeList(@PathVariable double corner1Lat,
                                     @PathVariable double corner1Lng,
                                     @PathVariable double corner2Lat,
                                     @PathVariable double corner2Lng,
                                     @PathVariable double corner3Lat,
                                     @PathVariable double corner3Lng,
                                     @PathVariable double corner4Lat,
                                     @PathVariable double corner4Lng) throws JsonProcessingException {

        //Setup HTTP variables to get and hold response
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        //HttpEntity <String> entity = new HttpEntity<String>(headers);
        ObjectMapper objectMapper = new ObjectMapper();

        //HTTP response will be broken down into separate crimes and stored in this list
        ArrayList<Crime> routeStepCrimes = new ArrayList<>();

        //We search for crime on a month-to-month basis
        //iterate though the past N number of months and return crime data for each month
        for(int i=1; i<NUMBER_OF_MONTHS; i++)
        {
            int currentMonth = i;

            //build url for crime API query
            //in format lat long
            String url = String.format("https://data.police.uk/api/crimes-street/all-crime?poly=%f,%f:%f,%f:%f,%f:%f,%f&date=2020-%d",
                    corner1Lat, corner1Lng, corner2Lat, corner2Lng, corner3Lat, corner3Lng, corner4Lat, corner4Lng, currentMonth) ;
            //hit the API and store the result in response
            String response = restTemplate.getForObject(url, String.class);
            //store the crimes for the month we are currently searching
            ArrayList<Crime> thisMonthsCrimes = objectMapper.readValue(response, new TypeReference<ArrayList<Crime>>() {});

            //add all the crimes from the month we are currently searching to routeStepCrimes
            addCurrentMonthCrimesToList(routeStepCrimes, thisMonthsCrimes);
        }
        //Convert JSONtoPOJO class to crime map items
        ArrayList<CrimeMapItem> routeStepCrimeMapItems = convertCrimesToCrimeMapItem(routeStepCrimes);

        //Return all the crimes found in this route step
        return routeStepCrimeMapItems;
    }
    /**
    *converts the large Crime object from the JSONtoPOJO service to a nicely packaged CrimeMapItem object
    *which only contains information we need and/or want to store
     */
    private ArrayList<CrimeMapItem> convertCrimesToCrimeMapItem(ArrayList<Crime> crimes)
    {
        ArrayList<CrimeMapItem> crimeMapItems = new ArrayList<>();
        for (Crime c : crimes) {
            String category = c.getCategory();
            String lat = c.getLocation().getLatitude();
            String lng = c.getLocation().getLongitude();
            CrimeMapItem crimeMapItem = new CrimeMapItem(Double.parseDouble(lng), Double.parseDouble(lat), category);
            crimeMapItems.add(crimeMapItem);
        }
        return crimeMapItems;
    }

    /**
     *Add the months selected number of crimes to the array list
     */
    private ArrayList<Crime> addCurrentMonthCrimesToList(ArrayList<Crime> totalCrimes, ArrayList<Crime> thisMonthsCrimes)
    {//iterate though all the crimes in this month, and add them to the list of crimes in this route step
        for (Crime crime : thisMonthsCrimes)
        {
            totalCrimes.add(crime);
        }
        return totalCrimes;
    }

    /**
     * Calculate and return a crime scrore for a given category.
     */
    @GetMapping(value = "/crimeScore/{category}")
    public  int calculateCrimeScoreForCrime(@PathVariable String category)
    {
        int crimeScore = CrimeMapItem.calculateCrimeRating(category);
        return crimeScore;
    }


}
