package com.dr.udaan.api.retrofit.Pojo

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class TransactionResponse (

        @SerializedName("success"      ) var success      : Boolean?                = null,
        @SerializedName("message"      ) var message      : String?                 = null,
        @SerializedName("transactions" ) var transactions : ArrayList<Transactions>? = null

        )

 class Transactions() : Parcelable{
        @SerializedName("id"             ) var id            : Int?    = null
        @SerializedName("user_id"        ) var userId        : Int?    = null
        @SerializedName("transaction_id" ) var transactionId : String? = null
        @SerializedName("amount"         ) var amount        : Int?    = null
        @SerializedName("status"         ) var status        : String? = null
        @SerializedName("created_at"     ) var createdAt     : String? = null
        @SerializedName("updated_at"     ) var updatedAt     : String? = null

        constructor(parcel: Parcel) : this() {
                id = parcel.readValue(Int::class.java.classLoader) as? Int
                userId = parcel.readValue(Int::class.java.classLoader) as? Int
                transactionId = parcel.readString()
                amount = parcel.readValue(Int::class.java.classLoader) as? Int
                status = parcel.readString()
                createdAt = parcel.readString()
                updatedAt = parcel.readString()
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeValue(id)
                parcel.writeValue(userId)
                parcel.writeString(transactionId)
                parcel.writeValue(amount)
                parcel.writeString(status)
                parcel.writeString(createdAt)
                parcel.writeString(updatedAt)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Transactions> {
                override fun createFromParcel(parcel: Parcel): Transactions {
                        return Transactions(parcel)
                }

                override fun newArray(size: Int): Array<Transactions?> {
                        return arrayOfNulls(size)
                }
        }
}



