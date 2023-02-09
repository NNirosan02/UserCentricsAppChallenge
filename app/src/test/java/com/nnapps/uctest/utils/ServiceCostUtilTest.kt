package com.nnapps.uctest.utils

import com.nnapps.uctest.mocks.UserCentricsServiceMock
import com.usercentrics.sdk.v2.settings.data.UsercentricsService
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test


class ServiceCostUtilTest {
    @Test
    fun theGoodCitizen() {
        val theGoodCitizen =
            ServiceCostUtil().theGoodCitizen(UserCentricsServiceMock.usercentricsServiceMock)
        assertEquals(true, theGoodCitizen)
    }
}