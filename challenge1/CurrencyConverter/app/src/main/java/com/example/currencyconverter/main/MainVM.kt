package com.example.currencyconverter.main

import android.util.Log
import android.window.OnBackInvokedDispatcher
//import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.models.Rates
import com.example.currencyconverter.util.DispatcherProvider
import com.example.currencyconverter.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.round
import java.text.NumberFormat
import java.util.Locale

@HiltViewModel
class MainVM @Inject constructor(
    private val repository: MainRepo,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    // Declares different states of currency conversion events
    sealed class CurrencyEvent {
        class Success(val resultText: String) : CurrencyEvent()
        class Failure(val errorText: String) : CurrencyEvent()
        object Loading : CurrencyEvent()
        object Empty : CurrencyEvent()
    }

    // Holds the current state of currency conversion
    private val _conversion = MutableStateFlow<CurrencyEvent>(CurrencyEvent.Empty)
    val conversion: StateFlow<CurrencyEvent> = _conversion

    // Method to convert amount from EUR to another currency
    fun convert(
        amountStr: String,
        toCurrency: String
    ) {
        val fromAmount = amountStr.toFloatOrNull()
        if (fromAmount == null) {
            _conversion.value = CurrencyEvent.Failure("Not a valid amount") // Handle invalid input
            return
        }

        // Execute API call on IO thread
        viewModelScope.launch(dispatchers.io) {
            _conversion.value = CurrencyEvent.Loading
            when (val ratesResponse = repository.getRates()) {
                is Resource.Error -> _conversion.value = CurrencyEvent.Failure(ratesResponse.message!!)
                is Resource.Success -> {
                    val rates = ratesResponse.data!!.rates
                    val rate = getRateForCurrency(toCurrency, rates)
                    if (rate == null) {
                        _conversion.value = CurrencyEvent.Failure("Unexpected error")
                    } else {
                        // Calculate converted amount
                        val convertedCurrency = round(fromAmount * rate * 100) / 100
                        // Format the result with commas or dots
                        val formattedCurrency = NumberFormat.getNumberInstance(Locale.GERMANY).format(convertedCurrency)
                        _conversion.value = CurrencyEvent.Success(
                            "$fromAmount EUR = $formattedCurrency $toCurrency"
                        )
                    }
                }
            }
        }
    }


    // Helper function to get exchange rate based on currency code
    private fun getRateForCurrency(currency: String, rates: Rates) = when (currency) {
        "AED" -> rates.aed
        "AFN" -> rates.afn
        "ALL" -> rates.all
        "AMD" -> rates.amd
        "ANG" -> rates.ang
        "AOA" -> rates.aoa
        "ARS" -> rates.ars
        "AUD" -> rates.aud
        "AWG" -> rates.awg
        "AZN" -> rates.azn
        "BAM" -> rates.bam
        "BBD" -> rates.bbd
        "BDT" -> rates.bdt
        "BGN" -> rates.bgn
        "BHD" -> rates.bhd
        "BIF" -> rates.bif
        "BMD" -> rates.bmd
        "BND" -> rates.bnd
        "BOB" -> rates.bob
        "BRL" -> rates.brl
        "BSD" -> rates.bsd
        "BTC" -> rates.btc
        "BTN" -> rates.btn
        "BWP" -> rates.bwp
        "BYN" -> rates.byn
        "BYR" -> rates.byr
        "BZD" -> rates.bzd
        "CAD" -> rates.cad
        "CDF" -> rates.cdf
        "CHF" -> rates.chf
        "CLF" -> rates.clf
        "CLP" -> rates.clp
        "CNH" -> rates.cnh
        "CNY" -> rates.cny
        "COP" -> rates.cop
        "CRC" -> rates.crc
        "CUC" -> rates.cuc
        "CUP" -> rates.cup
        "CVE" -> rates.cve
        "CZK" -> rates.czk
        "DJF" -> rates.djf
        "DKK" -> rates.dkk
        "DOP" -> rates.dop
        "DZD" -> rates.dzd
        "EGP" -> rates.egp
        "ERN" -> rates.ern
        "ETB" -> rates.etb
        "EUR" -> rates.eur
        "FJD" -> rates.fjd
        "FKP" -> rates.fkp
        "GBP" -> rates.gbp
        "GEL" -> rates.gel
        "GGP" -> rates.ggp
        "GHS" -> rates.ghs
        "GIP" -> rates.gip
        "GMD" -> rates.gmd
        "GNF" -> rates.gnf
        "GTQ" -> rates.gtq
        "GYD" -> rates.gyd
        "HKD" -> rates.hkd
        "HNL" -> rates.hnl
        "HRK" -> rates.hrk
        "HTG" -> rates.htg
        "HUF" -> rates.huf
        "IDR" -> rates.idr
        "ILS" -> rates.ils
        "IMP" -> rates.imp
        "INR" -> rates.inr
//        "IQD" -> rates.iqd
//        "IRR" -> rates.irr
//        "ISK" -> rates.isk
//        "JEP" -> rates.jep
//        "JMD" -> rates.jmd
//        "JOD" -> rates.jod
//        "JPY" -> rates.jpy
//        "KES" -> rates.kes
//        "KGS" -> rates.kgs
//        "KHR" -> rates.khr
//        "KMF" -> rates.kmf
//        "KPW" -> rates.kpw
//        "KRW" -> rates.krw
//        "KWD" -> rates.kwd
//        "KYD" -> rates.kyd
//        "KZT" -> rates.kzt
//        "LAK" -> rates.lak
//        "LBP" -> rates.lbp
//        "LKR" -> rates.lkr
//        "LRD" -> rates.lrd
//        "LSL" -> rates.lsl
//        "LTL" -> rates.ltl
//        "LVL" -> rates.lvl
//        "LYD" -> rates.lyd
//        "MAD" -> rates.mad
//        "MDL" -> rates.mdl
//        "MGA" -> rates.mga
//        "MKD" -> rates.mkd
//        "MMK" -> rates.mmk
//        "MNT" -> rates.mnt
//        "MOP" -> rates.mop
//        "MRU" -> rates.mru
//        "MUR" -> rates.mur
//        "MVR" -> rates.mvr
//        "MWK" -> rates.mwk
//        "MXN" -> rates.mxn
//        "MYR" -> rates.myr
//        "MZN" -> rates.mzn
//        "NAD" -> rates.nad
//        "NGN" -> rates.ngn
//        "NIO" -> rates.nio
//        "NOK" -> rates.nok
//        "NPR" -> rates.npr
//        "NZD" -> rates.nzd
        "OMR" -> rates.omr
        "PAB" -> rates.pab
        "PEN" -> rates.pen
        "PGK" -> rates.pgk
        "PHP" -> rates.php
        "PKR" -> rates.pkr
        "PLN" -> rates.pln
        "PYG" -> rates.pyg
        "QAR" -> rates.qar
        "RON" -> rates.ron
        "RSD" -> rates.rsd
        "RUB" -> rates.rub
        "RWF" -> rates.rwf
        "SAR" -> rates.sar
        "SBD" -> rates.sbd
        "SCR" -> rates.scr
        "SDG" -> rates.sdg
        "SEK" -> rates.sek
        "SGD" -> rates.sgd
        "SHP" -> rates.shp
        "SLE" -> rates.sle
        "SLL" -> rates.sll
        "SOS" -> rates.sos
        "SRD" -> rates.srd
        "STD" -> rates.std
        "SVC" -> rates.svc
        "SYP" -> rates.syp
        "SZL" -> rates.szl
        "THB" -> rates.thb
        "TJS" -> rates.tjs
        "TMT" -> rates.tmt
        "TND" -> rates.tnd
        "TOP" -> rates.top
        "TRY" -> rates.tRY
        "TTD" -> rates.ttd
        "TWD" -> rates.twd
        "TZS" -> rates.tzs
        "UAH" -> rates.uah
        "UGX" -> rates.ugx
        "USD" -> rates.usd
        "UYU" -> rates.uyu
        "UZS" -> rates.uzs
        "VEF" -> rates.vef
        "VES" -> rates.ves
        "VND" -> rates.vnd
        "VUV" -> rates.vuv
        "WST" -> rates.wst
        "XAF" -> rates.xaf
        "XAG" -> rates.xag
        "XAU" -> rates.xau
        "XCD" -> rates.xcd
        "XDR" -> rates.xdr
        "XOF" -> rates.xof
        "XPF" -> rates.xpf
        "YER" -> rates.yer
        "ZAR" -> rates.zar
        "ZMK" -> rates.zmk
        "ZMW" -> rates.zmw
        "ZWL" -> rates.zwl
        else -> null
    }



}