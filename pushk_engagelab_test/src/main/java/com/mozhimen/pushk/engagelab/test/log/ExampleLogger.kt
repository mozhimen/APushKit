package com.mozhimen.pushk.engagelab.test.log

import android.os.Bundle
import android.util.Log
import org.json.JSONObject

/**
 * @ClassName ExampleLogger
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/12/6 0:56
 * @Version 1.0
 */
object ExampleLogger {
    private const val TAG_SUFFIX = "ENGAGELAB-PRIVATES-APP"

    fun i(tag: String, message: String) {
        Log.i(TAG_SUFFIX, " [$tag] $message")
    }

    fun d(tag: String, message: String) {
        Log.d(TAG_SUFFIX, " [$tag] $message")
    }

    fun w(tag: String, message: String) {
        Log.w(TAG_SUFFIX, " [$tag] $message")
    }

    fun e(tag: String, message: String) {
        Log.e(TAG_SUFFIX, " [$tag] $message")
    }

    fun toLogString(bundle: Bundle?): String {
        try {
            if (bundle == null) {
                return "null"
            }
            val stringBuilder = StringBuilder()
            stringBuilder.append("{ ")
            for (key in bundle.keySet()) {
                stringBuilder.append(key).append(":")
                stringBuilder.append(bundle[key]).append(" ")
            }
            stringBuilder.append("}")
            return stringBuilder.toString()
        } catch (throwable: Throwable) {
            return bundle.toString()
        }
    }

    fun toLogString(json: JSONObject?): String {
        if (json == null) {
            return "null"
        }
        try {
            val ret = json.toString(2)
            return System.getProperty("line.separator") + ret
        } catch (throwable: Throwable) {
            return json.toString()
        }
    }
}
