package com.dr.udaan.api.retrofit.Pojo

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName 

class QuestionResponse {

    @SerializedName("success")         var success        : Boolean? = null
    @SerializedName("message")         var message        : String?  = null
    @SerializedName("question_data")   var question_data   : ArrayList<QuestionData> = arrayListOf()

} 

class QuestionData() : Parcelable {
 
    @SerializedName("id") var id: Int? = null
   
    @SerializedName("question") var question: String? = null
    
    @SerializedName("solution") var solution: String? = null
    
    @SerializedName("option_a") var optionA: String? = null
    
    @SerializedName("option_b") var optionB: String? = null
    
    @SerializedName("option_c") var optionC: String? = null
    
    @SerializedName("option_d") var optionD: String? = null
    
    @SerializedName("answer") var answer: String? = null
    
    @SerializedName("status") var status: String? = null
    
    @SerializedName("deleted") var deleted: String? = null
    
    @SerializedName("created_at") var createdAt: String? = null
    
    @SerializedName("updated_at") var updatedAt: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        question = parcel.readString()
        solution = parcel.readString()
        optionA = parcel.readString()
        optionB = parcel.readString()
        optionC = parcel.readString()
        optionD = parcel.readString()
        answer = parcel.readString()
        status = parcel.readString()
        deleted = parcel.readString()
        createdAt = parcel.readString()
        updatedAt = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(question)
        parcel.writeString(solution)
        parcel.writeString(optionA)
        parcel.writeString(optionB)
        parcel.writeString(optionC)
        parcel.writeString(optionD)
        parcel.writeString(answer)
        parcel.writeString(status)
        parcel.writeString(deleted)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<QuestionData> {
        override fun createFromParcel(parcel: Parcel): QuestionData {
            return QuestionData(parcel)
        }

        override fun newArray(size: Int): Array<QuestionData?> {
            return arrayOfNulls(size)
        }
    }

}