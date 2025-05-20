package com.example.remove_ads

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log

class AdBlocker (private val context: Context) {
    private val TAG = "AdBlocker"

    fun blockAds() {

        //Obtener la lista de anuncios
        val adHosts = getAdHosts()

        // Bloquear los anuncios
        blockHosts(adHosts)
    }

    private fun getAdHosts(): List<String> {
        //Cargar las lista de host de anuncios de los archivos o desde una base de datos

        // Para este ejemplo, utilizaremos una lista de hardcoded
        return listOf("ads.google..com", "ad.doubleclick.net", "pagead2.googlesyndication.com")
    }

    private fun blockHosts(hosts: List<String>) {

        // Utiliza el archivo hosts para bloquear el anuncio
        val hostsFile = "/system/etc/hosts"
        val hostsContent = hosts.joinToString(separator = "/n") { "127.0.0.1 $it" }

        try {
            // Escribir el archivo hosts
            val process = Runtime.getRuntime().exec("su")
            val outputStream = process.outputStream
            outputStream.write("echo \"$hostsContent\" >> \$ hostsFile\n".toByteArray())
            outputStream.write("exit\n".toByteArray())
            outputStream.flush()
            outputStream.close()

            Log.d(TAG, "Anuncios bloqueados con exito")
        } catch (e: Exception) {
            Log.e(TAG, "Error al bloquear anuncio", e)
        }
    }
}