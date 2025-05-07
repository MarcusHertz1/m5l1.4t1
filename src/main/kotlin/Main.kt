package ru.netology

import kotlin.math.roundToInt

fun calculateCommission(
    cardType: String = "Мир",
    previousMonthlyTransfers: Int = 0,
    currentTransfer: Int
): String {
    val dailyLimitNotVK = 150_000
    val monthlyLimitNotVK = 600_000
    val dailyLimitVK = 15_000
    val monthlyLimitVK = 40_000

    var commission = 0.0


    when (cardType) {
        "Mastercard", "Maestro" -> {
            if (currentTransfer > dailyLimitNotVK) {
                return "Превышен суточный лимит перевода."
            } else if (previousMonthlyTransfers + currentTransfer > monthlyLimitNotVK) {
                return "Превышен месячный лимит перевода."
            } else {
                val monthlyLimitNoFee = 75_000
                if (previousMonthlyTransfers >= monthlyLimitNoFee) {
                    commission = currentTransfer * 0.006 + 20
                } else {
                    val remainingNoFee = monthlyLimitNoFee - previousMonthlyTransfers
                    if (currentTransfer > remainingNoFee) {
                        val amountWithFee = currentTransfer - remainingNoFee
                        commission = amountWithFee * 0.006 + 20
                    }
                }
            }
        }


        "Visa", "Мир" -> {
            if (currentTransfer > dailyLimitNotVK) {
                return "Превышен суточный лимит перевода."


            } else if (previousMonthlyTransfers + currentTransfer > monthlyLimitNotVK) {
                return "Превышен месячный лимит перевода."
            } else {
                commission = currentTransfer * 0.0075
                if (commission < 35) {
                    commission = 35.0
                }
            }
        }


        "VK Pay" -> {
            if (currentTransfer > dailyLimitVK) {
                return "Превышен суточный лимит перевода."
            } else if (previousMonthlyTransfers + currentTransfer > monthlyLimitVK) {
                return "Превышен месячный лимит перевода."
            } else commission = 0.0
        }


        else -> {
            return "Неизвестный тип карты."
        }
    }


    commission = (commission * 100).roundToInt() / 100.0


    return "Комиссия за перевод составит: $commission руб."
}


fun main() {
    println(calculateCommission("Mastercard", 50_000, 100_000))
    println(calculateCommission("Vis", 0, 10_000))
    println(calculateCommission("Мир", 0, 150_000))
    println(calculateCommission("Visa", 590_000, 20_000))
}
