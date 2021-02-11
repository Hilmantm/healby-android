package com.healby.repository

import com.healby.service.LocalService
import javax.inject.Inject

class LocalRepository @Inject constructor(private val localService: LocalService) {
}