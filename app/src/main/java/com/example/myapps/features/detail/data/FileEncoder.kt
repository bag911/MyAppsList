package com.example.myapps.features.detail.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileInputStream
import java.security.MessageDigest
import javax.inject.Inject


class FileEncoder @Inject constructor() {
    fun getApkChecksum(apkPath: String): String {
        if (apkPath.isEmpty()) return ""
        val digest = MessageDigest.getInstance("SHA-1")
        FileInputStream(File(apkPath)).use { fis ->
            val buffer = ByteArray(1024)
            var bytesRead: Int
            while (fis.read(buffer).also { bytesRead = it } != -1) {
                digest.update(buffer, 0, bytesRead)
            }
        }
        return digest.digest().joinToString("") { "%02x".format(it) }
    }
}