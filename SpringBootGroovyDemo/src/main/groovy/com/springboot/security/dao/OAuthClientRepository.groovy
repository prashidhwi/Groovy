package com.springboot.security.dao

import org.springframework.data.jpa.repository.JpaRepository

import com.springboot.security.bean.OAuthClientEntity

interface OAuthClientRepository extends JpaRepository<OAuthClientEntity, String> {
    OAuthClientEntity findByClientId(String clientId)
}
