package com.nnapps.uctest.utils

import com.nnapps.uctest.domain.model.enums.DataCostModel
import com.usercentrics.sdk.v2.settings.data.UsercentricsService

class ServiceCostUtil {
    fun bankingSnoopy(usercentricsServiceData: UsercentricsService): Boolean {
        return usercentricsServiceData.dataCollectedList.contains(DataCostModel.PURCHASE_ACTIVITY.collectedData) && usercentricsServiceData.dataCollectedList.contains(
            DataCostModel.BANK_DETAILS.collectedData
        ) && usercentricsServiceData.dataCollectedList.contains(DataCostModel.CREDIT_AND_DEBIT_CARD_NUMBER.collectedData)
    }

    fun whyDoYouCare(usercentricsServiceData: UsercentricsService): Boolean {
        return (usercentricsServiceData.dataCollectedList.contains(DataCostModel.SEARCH_TERMS.collectedData) && usercentricsServiceData.dataCollectedList.contains(
            DataCostModel.GEOGRAPHIC_LOCATION.collectedData
        ) && usercentricsServiceData.dataCollectedList.contains(DataCostModel.NUMBER_OF_PAGE_VIEWS.collectedData)
                )
    }

    fun theGoodCitizen(usercentricsServiceData: UsercentricsService): Boolean {
        return (usercentricsServiceData.dataCollectedList.size <= 4)
    }
}