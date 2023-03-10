package com.tanaguru.repository;

import com.tanaguru.domain.entity.audit.Audit;
import com.tanaguru.domain.entity.audit.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Stream;


/**
 * @author rcharre
 */
@Repository
public interface PageRepository extends JpaRepository<Page, Long> {
    /**
     * Find all page for a given audit id
     *
     * @param auditId The audit Id
     * @return The audit's pages list
     */
    Collection<Page> findAllByAudit_Id(long auditId);

    /**
     * Find all page for a given audit id
     *
     * @param auditId The audit Id
     * @return The audit's pages list
     */
    org.springframework.data.domain.Page<Page> findAllByAudit_Id(long auditId, Pageable pageable);

    org.springframework.data.domain.Page<Page> findByNameContainingIgnoreCaseAndAudit_Id(String name, long auditId, Pageable pageable);

    void deleteAllByAudit(Audit audit);
    
    @Query("select p from Page p")
    Stream<Page> getAll();
    
    /**
     * Find all page for audit between dates
     *
     * @param date start and date end
     * @return all page
     */
    Collection<Page> findAllByAuditDateStartLessThanEqualAndAuditDateEndGreaterThanEqual(Date endDate, Date startDate);

}
