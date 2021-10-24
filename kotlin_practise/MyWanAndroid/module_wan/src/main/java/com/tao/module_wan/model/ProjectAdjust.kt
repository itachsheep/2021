/**
 * @ClassName:      ProjectAdjust.kt
 * @Description:
 *
 * @author          taowei
 * @version         V1.0
 * @Date           2021/10/24 10:53 AM
 */
package com.tao.module_wan.model

import android.os.Parcel
import android.os.Parcelable
import com.hao.library.extensions.removeSymbol

data class ProjectAdjust(var id: Int): Parcelable{
    var name: String = ""
        get() {
            return if (null == field || "" == field) {
                ""
            } else {
                field.removeSymbol()
            }
        }

    constructor(parcel: Parcel): this(parcel.readInt()) {
        this.name = parcel.readString() ?: ""
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(name)
    }

    companion object CREATOR: Parcelable.Creator<ProjectAdjust> {
        override fun createFromParcel(source: Parcel): ProjectAdjust {
            return ProjectAdjust(source)
        }

        override fun newArray(size: Int): Array<ProjectAdjust?> {
            return arrayOfNulls(size)
        }

    }
}