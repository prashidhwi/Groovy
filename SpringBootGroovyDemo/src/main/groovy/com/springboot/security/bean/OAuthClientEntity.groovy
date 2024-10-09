package com.springboot.security.bean

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.Instant

@Entity
class OAuthClientEntity {

    @Id
    String id

    String clientId
    Instant clientIdIssuedAt
    String clientSecret
    Instant clientSecretExpiresAt
    String clientName
    String clientAuthenticationMethods
    String authorizationGrantTypes
    String redirectUris
    String scopes
    String clientSettings
    String tokenSettings
}
