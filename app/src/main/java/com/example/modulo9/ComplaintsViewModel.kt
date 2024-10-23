package com.example.modulo9

import androidx.lifecycle.ViewModel

class ComplaintsViewModel : ViewModel() {
    private var complaints: List<Complaint> = emptyList()

    fun getComplaints(): List<Complaint> = complaints

    fun setComplaints(newComplaints: List<Complaint>) {
        complaints = newComplaints
    }
}
