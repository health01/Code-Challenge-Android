package com.kst.testing.code_challenge_android.util

interface DomainMapper<T, DomainModel> {

    fun mapToDomainModel(entity: T): DomainModel

    fun mapFromDomainModel(domainModel: DomainModel): T
}