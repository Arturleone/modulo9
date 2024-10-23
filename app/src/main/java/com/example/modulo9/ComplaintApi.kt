package com.example.modulo9

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ComplaintApi {
    @POST("api/Reclamacoes") // Endpoint corrigido
    suspend fun submitComplaint(@Body complaint: Complaint): Response<Complaint>
}
