package com.nnapps.uctest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nnapps.uctest.domain.model.enums.DataCostModel
import com.nnapps.uctest.domain.model.ServiceData
import com.nnapps.uctest.utils.ServiceCostUtil
import com.usercentrics.sdk.UsercentricsCMPData
import com.usercentrics.sdk.UsercentricsConsentUserResponse

class MainViewModel : ViewModel() {

    private var totalCost = MutableLiveData<String>()
    val readTotalCost: LiveData<String> get() = totalCost
    private var consentHashMap: HashMap<String, ServiceData> =
        HashMap()


    fun serviceCostCalculator(
        userResponse: UsercentricsConsentUserResponse,
        cmpData: UsercentricsCMPData
    ) {
        var total = 0.0

        userResponse.consents.forEach {
            consentHashMap[it.templateId] = ServiceData(it.status, it.dataProcessor)
        }

        cmpData.services.forEach {
            var cost = 0.0
            if (consentHashMap.contains(it.templateId)) {
                val consentItem = consentHashMap[it.templateId]
                if (consentItem != null) {
                    if (consentItem.status) {
                        it.dataCollectedList.forEach { dataItem ->
                            for (item in DataCostModel.values()) {
                                if (dataItem == item.collectedData) {
                                    cost += item.cost
                                }
                            }
                        }

                        // Apply Rule 1 if applicable (Banking snoopy = Increase by 10%)
                        if (ServiceCostUtil().bankingSnoopy(it)) {
                            cost += (10.toFloat() * cost) / 100
                        }

                        // Apply Rule 2 if applicable (Why do you care = Increase by 27%)
                        if (ServiceCostUtil().whyDoYouCare(it)) {
                            cost += (27.toFloat() * cost) / 100
                        }

                        // Apply Rule 3 if applicable (The good citizen = Decrease by 10%)
                        if (cost > 0 && ServiceCostUtil().theGoodCitizen(it)) {
                            cost -= (10.toFloat() * cost) / 100
                        }

                        println("${consentItem.name} = $cost")
                        total += cost
                    }
                }
            }
        }
        totalCost.value = "%.2f".format(total)
    }
}