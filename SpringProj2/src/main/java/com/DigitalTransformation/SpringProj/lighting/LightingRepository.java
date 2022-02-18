package com.DigitalTransformation.SpringProj.lighting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface LightingRepository extends JpaRepository<Lighting, Integer> {
    /**
     * Returns a sum of all the values in the database
     */
    @Query("select sum(l.LightingRating) from Lighting l")
    long countInstances();

    /**
     * Returns a sum of all the values in the database where the Longitude and Latitude on each route match
     */
    @Query("select l.LightingRating from Lighting l where l.Latitude = ?1 and l.Longitude = ?2")
    Integer crossRefAndCountInstances(double Latitude, double Longitude);
}
