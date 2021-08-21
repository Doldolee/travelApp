package com.seook.travelapp_hanium.api2

class UserApiMethod {
    companion object{
        fun nationSearch(
            nationName: String,
            callback: (response: NationCheckResponse?)->Unit
        ){

            RetrofitService.test.nationSearch(
                nationName
            ).enqueue(
                MethodCallback.generalCallback<NationCheckResponse, NationOkResponse, NationErrorResponse>(
                    callback
                )
            )
        }

    }
}