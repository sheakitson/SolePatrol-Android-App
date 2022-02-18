package com.DigitalTransformation.SpringProj.crime;

/**
 * Created for Crime Stats - All crimes have an associated reference number in the crimeMapItem
 * This enum is called when a crime is encountered on our route to check the score and impact factor.
  */
 enum CrimeEnum {
    ANTI_SOCIAL_BEHAVIOUR,
    DRUGS,
    VIOLENT_CRIME,
     ARSON,
    OTHER_CRIME;
     public static String returnStringValue(CrimeEnum enumValue)
     {
         String crime="";
         switch(enumValue) {

             case ANTI_SOCIAL_BEHAVIOUR:
                 crime = "anti-social-behaviour";
                 break;
             case DRUGS:
                 crime = "drugs";
                 break;
             case VIOLENT_CRIME:
                 crime = "violent-crime";
                 break;
             case ARSON:
                 crime = "criminal-damage-arson";
                 break;
             case OTHER_CRIME:
                 crime = "other-crime";
                 break;
         }
         return crime;
     }
}


