package com.cw.myframework

import android.app.Application
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.cw.myframework.testAct.PrivacyComplianceScanUtil
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            //测试权限
            appListTrack(requireActivity().application)
            runCommand()
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    private fun appListTrack(app: Application) {
        try {
            val list =
                app.packageManager.getInstalledApplications(PackageManager.MATCH_UNINSTALLED_PACKAGES)
            val sb = StringBuilder()
            for (info in list) {
                if (!isSystemApp(info)) {
                    sb.append(info.packageName).append(",")
                }
            }
        } catch (e: Exception) {
        }
    }

    private fun isSystemApp(info: ApplicationInfo): Boolean {
        val isSysApp = info.flags and ApplicationInfo.FLAG_SYSTEM == 1
        val isSysUpd = info.flags and ApplicationInfo.FLAG_UPDATED_SYSTEM_APP == 1
        return isSysApp || isSysUpd
    }


    private fun runCommand() {
        try {
            val sb = StringBuffer()
            val process = Runtime.getRuntime().exec("adb shell pm list package -3")
            val bfr = BufferedReader(InputStreamReader(process.inputStream))
            var line = ""
            while (bfr.readLine().also { line = it } != null) {
                sb.append(line).append("\n")
            }

            println(sb.toString())
        } catch (e: Exception) {

        }

    }
}