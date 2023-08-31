package component.activity.res.layout

/**
 * Author：Stevie.Chen Time：2023/8/30
 * Class Comment：
 */
fun createActivityXml(
    isDataBinding: Boolean
): String {
    val sb = StringBuilder()
    if (isDataBinding) {
        sb.append(
            """
            <?xml version="1.0" encoding="utf-8"?>
            <layout xmlns:android="http://schemas.android.com/apk/res/android"
                xmlns:app="http://schemas.android.com/apk/res-auto"
                xmlns:tools="http://schemas.android.com/tools">
                
                <data>

                </data>

                <androidx.constraintlayout.widget.ConstraintLayout
                    android:layout_width="match_parent"
                    android:layout_height="match_parent">
            """
        )
    } else {
        sb.append(
            """
            <?xml version="1.0" encoding="utf-8"?>
            <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
                xmlns:app="http://schemas.android.com/apk/res-auto"
                xmlns:tools="http://schemas.android.com/tools"
                android:layout_width="match_parent"
                android:layout_height="match_parent">
            """
        )
    }
    sb.append(
        """
        </androidx.constraintlayout.widget.ConstraintLayout>
        """
    )
    if (isDataBinding) {
        sb.append(
            """
                
            </layout>
            """
        )
    }
    return sb.toString().trim()
}