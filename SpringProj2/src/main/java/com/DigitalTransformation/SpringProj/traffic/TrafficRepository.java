package com.DigitalTransformation.SpringProj.traffic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface TrafficRepository extends JpaRepository<Traffic, Integer> {
    /**
     * Returns a sum of all the values in the database where the Longitude and Latitude on each route match
     */
    @Query("select t.TrafficRating from Traffic t where t.Latitude = ?1 and t.Longitude = ?2")
    Integer crossRefAndCountInstancesTraffic(double Latitude, double Longitude);
}
