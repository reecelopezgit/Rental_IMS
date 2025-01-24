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

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByProperty(Property property);

    @Modifying
    @Query("DELETE FROM Item i WHERE i.property = :property")
    void deleteByProperty(Property property);

    @Modifying
    @Query("DELETE FROM Item i WHERE i.property.id = :propertyId")
    void deleteByPropertyId(@Param("propertyId") Long propertyId);
}