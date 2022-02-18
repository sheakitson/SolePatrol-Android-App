package com.DigitalTransformation.SpringProj.crime;

import com.DigitalTransformation.SpringProj.MapItem;
import net.minidev.json.JSONObject;

/**
 * Map Items is a long and lat object. Here we bundle the category in with the corresponding long and lat.
 */
public class CrimeMapItem extends MapItem {

    private String category;

    public CrimeMapItem() {
    }

    public CrimeMapItem(double longitude, double latitude, String category) {
        super(longitude, latitude);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * The method below states a score for each crime.
     * The ethical issues associated with this can be found in the SolePatrol Project Document.
     */
    public static int calculateCrimeRating(String crimeType)
    {
       int crimeScore = 0;
       if(crimeType.equals(CrimeEnum.returnStringValue(CrimeEnum.VIOLENT_CRIME)))
       {
         crimeScore = 5;
       }
       else if(crimeType.equals(CrimeEnum.returnStringValue(CrimeEnum.OTHER_CRIME)))
       {
         crimeScore = 4;
       }
       else if(crimeType.equals(CrimeEnum.returnStringValue(CrimeEnum.DRUGS)))
       {
         crimeScore = 3;
       }
       else if(crimeType.equals(CrimeEnum.returnStringValue(CrimeEnum.ANTI_SOCIAL_BEHAVIOUR)))
       {
         crimeScore = 2;
       }
       else if(crimeType.equals(CrimeEnum.returnStringValue(CrimeEnum.ARSON)))
       {
          crimeScore = 1;
       }
       return crimeScore;
    }

}
