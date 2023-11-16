package com.id.kusumakazu.repository;

import com.id.kusumakazu.domain.ItemObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the ItemObject entity.
 */
@Repository
public interface ItemObjectRepository extends JpaRepository<ItemObject, Long> {

    @Query(value = "select distinct itemObject from ItemObject itemObject left join fetch itemObject.weapons left join fetch itemObject.armors",
        countQuery = "select count(distinct itemObject) from ItemObject itemObject")
    Page<ItemObject> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct itemObject from ItemObject itemObject left join fetch itemObject.weapons left join fetch itemObject.armors")
    List<ItemObject> findAllWithEagerRelationships();

    @Query("select itemObject from ItemObject itemObject left join fetch itemObject.weapons left join fetch itemObject.armors where itemObject.id =:id")
    Optional<ItemObject> findOneWithEagerRelationships(@Param("id") Long id);
}
