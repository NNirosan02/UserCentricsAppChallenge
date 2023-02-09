package com.nnapps.uctest.mocks

import com.usercentrics.sdk.v2.settings.data.UsercentricsService

class UserCentricsServiceMock {
    companion object {
        // Use This mock when testing "Browser information", "Credit and debit card number", "First name", "Geographic location"
        val usercentricsServiceMock = UsercentricsService(
            templateId = "test1",
            dataCollectedList = listOf("Browser information", "Credit and debit card number", "First name", "Geographic location")
        )
    }
}