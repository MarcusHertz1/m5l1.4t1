import org.junit.Assert.*
import org.junit.Test
import ru.netology.calculateCommission

class MainTest {
    // ==== Mastercard/Maestro ====


    @Test
    fun mastercard_noFee_whenBelowMonthlyLimit() {
        val result = calculateCommission("Mastercard", 10_000, 20_000)
        assertEquals("Комиссия за перевод составит: 0.0 руб.", result)
    }


    @Test
    fun mastercard_fullFee_whenAboveMonthlyNoFee() {
        val result = calculateCommission("Mastercard", 80_000, 10_000)
        assertEquals("Комиссия за перевод составит: 8.0 руб.", result)
    }


    @Test
    fun mastercard_partialFee_whenCrossingNoFeeThreshold() {
        val result = calculateCommission("Maestro", 70_000, 10_000)
        assertEquals("Комиссия за перевод составит: 50.0 руб.", result)
    }


    @Test
    fun mastercard_dailyLimitExceeded() {
        val result = calculateCommission("Mastercard", 0, 200_000)
        assertEquals("Превышен суточный лимит перевода.", result)
    }


    @Test
    fun mastercard_monthlyLimitExceeded() {
        val result = calculateCommission("Maestro", 590_000, 20_000)
        assertEquals("Превышен месячный лимит перевода.", result)
    }


    // ==== Visa/Мир ====


    @Test
    fun visa_minimumCommissionEnforced() {
        val low = calculateCommission("Visa", 0, 1_000)
        assertEquals("Комиссия за перевод составит: 35.0 руб.", low)
        val normal = calculateCommission("Мир", 0, 10_000)
        assertEquals("Комиссия за перевод составит: 75.0 руб.", normal)
    }


    @Test
    fun visa_dailyLimitExceeded() {
        val result = calculateCommission("Visa", 0, 200_000)
        assertEquals("Превышен суточный лимит перевода.", result)
    }


    @Test
    fun visa_monthlyLimitExceeded() {
        val result = calculateCommission("Мир", 595_000, 10_000)
        assertEquals("Превышен месячный лимит перевода.", result)
    }


    // ==== VK Pay ====


    @Test
    fun vkPay_noCommissionWithinLimits() {
        val result = calculateCommission("VK Pay", 10_000, 5_000)
        assertEquals("Комиссия за перевод составит: 0.0 руб.", result)
    }


    @Test
    fun vkPay_dailyLimitExceeded() {
        val result = calculateCommission("VK Pay", 0, 20_000)
        assertEquals("Превышен суточный лимит перевода.", result)
    }


    @Test
    fun vkPay_monthlyLimitExceeded() {
        val result = calculateCommission("VK Pay", 39_000, 2_000)
        assertEquals("Превышен месячный лимит перевода.", result)
    }


    // ==== Unknown ====


    @Test
    fun unknownCardType() {
        val result = calculateCommission("Vis", 0, 1_000)
        assertEquals("Неизвестный тип карты.", result)
    }


}
