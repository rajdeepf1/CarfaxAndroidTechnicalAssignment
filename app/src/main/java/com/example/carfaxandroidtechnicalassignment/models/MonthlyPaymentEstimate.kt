package com.example.carfaxandroidtechnicalassignment.models

data class MonthlyPaymentEstimate(
    val downPaymentAmount: Double,
    val downPaymentPercent: Int,
    val interestRate: Int,
    val loanAmount: Double,
    val monthlyPayment: Double,
    val price: Int,
    val termInMonths: Int
)