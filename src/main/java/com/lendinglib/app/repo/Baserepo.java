package com.lendinglib.app.repo;

import com.lendinglib.app.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface Baserepo<T extends BaseEntity, I>
    extends JpaRepository<T, I>
{}
