package app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.entities.Item;
import app.entities.Property;

import app.entities.ServiceRequest;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceRequest, Long> {
    @Modifying
    @Query("DELETE FROM ServiceRequest sr WHERE sr.inventoryItem.id = :itemId")
    void deleteByInventoryItemId(@Param("itemId") Long itemId);

    @Modifying
    @Query("DELETE FROM ServiceRequest sr WHERE sr.property = :property")
    void deleteByProperty(Property property);


    @Modifying
    @Query("DELETE FROM ServiceRequest sr WHERE sr.property.id = :propertyId")
    void deleteByPropertyId(@Param("propertyId") Long propertyId);



}


