package com.tanaguru.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tanaguru.domain.constant.EAuditType;
import com.tanaguru.domain.entity.audit.Audit;
import com.tanaguru.domain.entity.audit.Page;
import com.tanaguru.domain.entity.membership.Act;
import com.tanaguru.domain.entity.membership.project.Project;
import com.tanaguru.domain.entity.membership.project.ProjectAppUser;
import com.tanaguru.repository.AuditRepository;
import com.tanaguru.repository.ContractRepository;
import com.tanaguru.repository.PageRepository;
import com.tanaguru.repository.ProjectRepository;
import com.tanaguru.repository.StatusResultRepository;
import com.tanaguru.repository.UserRepository;
import com.tanaguru.service.AuditService;
import com.tanaguru.service.StatsService;

@Service
@Transactional
public class StatsServiceImpl implements StatsService{

	private final ProjectRepository projectRepository;
	private final UserRepository userRepository;
	private final StatusResultRepository statusResultRepository;
	private final AuditRepository auditRepository;
	private final AuditService auditService;
	private final ContractRepository contractRepository;
	private final PageRepository pageRepository;

	@Autowired
	public StatsServiceImpl(ProjectRepository projectRepository,
			UserRepository userRepository,
			StatusResultRepository statusResultRepository,
			AuditRepository auditRepository,
			AuditService auditService,
			ContractRepository contractRepository,
			PageRepository pageRepository) {
		this.projectRepository = projectRepository;
		this.userRepository = userRepository;
		this.statusResultRepository = statusResultRepository;
		this.auditRepository = auditRepository;
		this.auditService = auditService;
		this.contractRepository = contractRepository;
		this.pageRepository = pageRepository;
	}
	
	@Override
	public JSONObject createStats() {
		JSONObject jsonStatsObject = new JSONObject();
		jsonStatsObject.put("nbProjects", this.projectRepository.count());
		jsonStatsObject.put("nbUsers", this.userRepository.count());
		jsonStatsObject.put("nbAudits", this.auditRepository.count());
		jsonStatsObject.put("nbContracts", this.contractRepository.count());
		Optional<Double> avgNbOfErrorsByPage = this.statusResultRepository.getAverageNumberOfErrorsByPage();
		if(avgNbOfErrorsByPage.isPresent()) {
			jsonStatsObject.put("meanNbErrorsPage", avgNbOfErrorsByPage.get());
		}else {
			jsonStatsObject.put("meanNbErrorsPage", 0);
		}
		jsonStatsObject.put("meanNbErrorsAudit", this.getAverageNbErrorsByAudit());
		jsonStatsObject.put("meanNbErrorsProject", this.getAverageNbErrorsByProject());
		for(EAuditType type : EAuditType.values()) {
			jsonStatsObject.put("nb"+type.toString()+"Audit", this.auditRepository.numberOfAuditByType(type));
		}
		jsonStatsObject.put("meanNbUsersByProject", this.getAverageNbUsersByProject());
		jsonStatsObject.put("meanNbAuditsByProject", this.getAverageNbAuditsByProject());
		return jsonStatsObject;
	}
	
	/**
	 * Return the average of number of users per project
	 * @return the average of number of users per project
	 */
	private Double getAverageNbUsersByProject() {
		Collection<Project> projects = this.projectRepository.findAll();
		double nbUsers = 0.0;
		for(Project project: projects) {
			Collection<ProjectAppUser> projectAppUsers = project.getProjectAppUsers();
			nbUsers += projectAppUsers.size();
		}
		return (double) (nbUsers/projects.size());
	}
	
	/**
	 * Return the average of number of audits per project
	 * @return the average of number of audits per project
	 */
	private Double getAverageNbAuditsByProject() {
		Collection<Project> projects = this.projectRepository.findAll();
		double nbAudits = 0.0;
		for(Project project: projects) {
			Collection<Act> acts = project.getActs();
			nbAudits += acts.size();
		}
		return (double) (nbAudits/projects.size());
	}
	
	/***
	 * Returns the average number of errors per audit
	 * @return the average number of errors per audit
	 */
	private Double getAverageNbErrorsByAudit() {
		List<Audit> audits = this.auditRepository.findAll();
		List<Long> pagesId = new ArrayList<>();
		List<Integer> auditErrors = new ArrayList<>();
		for(Audit audit : audits) {
			Collection<Page> pages = audit.getPages();
			for(Page page : pages) {
				pagesId.add(page.getId());
			}
			Optional<Integer> error = this.statusResultRepository.getSumNumberOfErrorsForPages(pagesId);
			if(error.isPresent()) {
				auditErrors.add(error.get());
			}
			pagesId.clear();
		}
	    Double average = auditErrors.stream().mapToInt(val -> val).average().orElse(0.0);
	    return average;
	}
	
	/**
	 * Return the average number of errors per project
	 * @return the average number of errors per project
	 */
	private double getAverageNbErrorsByProject() {
		List<Project> projects = this.projectRepository.findAll();
		List<Integer> projectErrors = new ArrayList<>();
		for(Project project : projects) {
			Collection<Audit> audits = this.auditService.findAllByProject(project);
			List<Long> pagesId = new ArrayList<>();
			List<Integer> auditErrors = new ArrayList<>();
			for(Audit audit : audits) {
				Collection<Page> pages = audit.getPages();
				for(Page page : pages) {
					pagesId.add(page.getId());
				}
				Optional<Integer> error = this.statusResultRepository.getSumNumberOfErrorsForPages(pagesId);
				if(error.isPresent()) {
					auditErrors.add(error.get());
				}
				pagesId.clear();
			}
			projectErrors.add(auditErrors.stream().reduce(0, Integer::sum));
		}
	    Double average = projectErrors.stream().mapToInt(val -> val).average().orElse(0.0);
	    return average;
	}

	@Override
	public Integer getNbPageAuditedByPeriod(Date startDate, Date endDate) {
		Collection<Page> pages = this.pageRepository.findAll();
		int cpt = 0;
		for(Page page: pages) {
			Audit audit = page.getAudit();
			if(audit.getDateStart().after(startDate) && audit.getDateStart().before(endDate)) {
				cpt += 1;
			}
		}
		return cpt;
	}
	
	@Override
	public Integer getNbSiteAuditedByPeriod(Date startDate, Date endDate) {
		return this.auditRepository.numberOfAuditByTypeAndPeriod(EAuditType.SITE, startDate, endDate);
	}

	@Override
	public Integer getNbScenarioAuditedByPeriod(Date startDate, Date endDate) {
		return this.auditRepository.numberOfAuditByTypeAndPeriod(EAuditType.SCENARIO, startDate, endDate);
	}
	
	@Override
	public Integer getNbFileAuditedByPeriod(Date startDate, Date endDate) {
		return this.auditRepository.numberOfAuditByTypeAndPeriod(EAuditType.UPLOAD, startDate, endDate);
	}

	@Override
	public Double getAverageNbErrorsForPageByPeriod(Date startDate, Date endDate) {
		Double avg = 0.0;
		Collection<Page> pages = this.pageRepository.findAll();
		List<Long> pagesId = new ArrayList<>();
		for(Page page: pages) {
			Audit audit = page.getAudit();
			if(audit.getDateStart().after(startDate) && audit.getDateStart().before(endDate)) {
				pagesId.add(page.getId());
			}
		}
		Optional<Integer> errorSum = this.statusResultRepository.getSumNumberOfErrorsForPages(pagesId);
		if(errorSum.isPresent()) {
			avg = (double) errorSum.get()/pagesId.size();
		}
		return avg;
	}
}
