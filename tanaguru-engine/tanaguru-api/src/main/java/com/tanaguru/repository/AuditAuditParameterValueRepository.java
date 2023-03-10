package com.tanaguru.repository;

import com.tanaguru.domain.entity.audit.Audit;
import com.tanaguru.domain.entity.audit.parameter.AuditAuditParameterValue;
import com.tanaguru.domain.entity.audit.parameter.AuditParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface AuditAuditParameterValueRepository extends JpaRepository<AuditAuditParameterValue, Long>  {
    @Transactional
    void deleteAllByAudit(Audit audit);
    Collection<AuditAuditParameterValue> findAllByAudit(Audit audit);

    Optional<AuditAuditParameterValue> findByAuditAndAuditParameterValue_AuditParameter(Audit audit, AuditParameter auditParameter);
}
