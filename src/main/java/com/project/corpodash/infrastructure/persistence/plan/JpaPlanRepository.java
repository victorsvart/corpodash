package com.project.corpodash.infrastructure.persistence.plan;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.corpodash.domain.plan.Plan;
import com.project.corpodash.domain.plan.interfaces.PlanRepository;

public interface JpaPlanRepository extends JpaRepository<Plan, Long>, PlanRepository {
}
