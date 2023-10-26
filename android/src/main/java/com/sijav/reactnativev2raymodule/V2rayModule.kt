package com.sijav.reactnativev2raymodule


import android.Manifest
import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.RemoteException
import android.util.Log
import android.widget.Toast
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.sijav.reactnativev2raymodule.service.V2RayServiceManager
import com.sijav.reactnativev2raymodule.util.MessageUtil
import com.sijav.reactnativev2raymodule.util.MmkvManager
import com.sijav.reactnativev2raymodule.util.Utils
import com.tbruyelle.rxpermissions.RxPermissions
import com.v2ray.ang.util.AngConfigManager
import com.tencent.mmkv.MMKV
import me.drakeet.support.toast.ToastCompat

class V2rayModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {
  private val reactContext: ReactApplicationContext = reactContext

  init {
    initMMKV()
    createHandler()
//    bindService(reactContext)
  }

  private fun initMMKV() {
    MMKV.initialize(reactContext)
  }

  private fun createHandler() {
//    mHandler = object : Handler(Looper.getMainLooper()) {
//      override fun handleMessage(msg: Message) {
//        val params: WritableMap = Arguments.createMap()
//        if (msg.what == MSG_UPDATE_STATE) {
//          val state: Array<String> = msg.obj.toString().split("\\|").toTypedArray()
//          latestState = if (state.size > 0) state[0] else "NOPROCESS"
//          latestMessage = if (state.size > 1) state[1] else ""
//          params.putString("state", latestState)
//          params.putString("msg", latestMessage)
//          sendEvent("STATE_CHANGED", params)
//        } else if (msg.what == MSG_UPDATE_MY_IP) {
//          params.putString("ip", msg.obj.toString())
//          sendEvent("IP_CHANGED", params)
//        }
//      }
//    }
  }

  //
//  private fun bindService(context: Context) {
//    val icsOpenVpnService = Intent(IOpenVPNAPIService::class.java.name)
//    icsOpenVpnService.`package` = context.packageName
//    context.bindService(icsOpenVpnService, mConnection, Context.BIND_AUTO_CREATE)
//  }
//
//  private fun unbindService() {
//    if (mConnection != null) {
//      reactContext.unbindService(mConnection)
//    }
//  }
  override fun getName(): String {
    return NAME
  }

  // Example method
  // See https://reactnative.dev/docs/native-modules-android
  @ReactMethod
  fun multiply(a: Double, b: Double, promise: Promise) {
    promise.resolve(a * b)
  }

  @ReactMethod
  fun connect(promise: Promise) {
    try {
      V2RayServiceManager.startV2Ray(context = reactContext)
      promise.resolve(null)
    } catch (e: RemoteException) {
      promise.reject(e)
    }
  }

  @ReactMethod
  fun disconnect(promise: Promise) {
    try {
      V2RayServiceManager.stopV2rayPoint()
    } catch (e: RemoteException) {
      promise.reject(e)
    }
  }

  @ReactMethod
  fun importConfig(server: String?, subid: String = "", promise: Promise) {
    try {
      MmkvManager.removeAllServer()
      val append = subid.isNullOrEmpty()
      var count = AngConfigManager.importBatchConfig(server, subid, append)
      if (count <= 0) {
        count = AngConfigManager.importBatchConfig(Utils.decode(server!!), subid, append)
      }
      if (count > 0) {
        Log.i("serverListttttt", "adddd")
      } else {
      }
      promise.resolve(count)
    } catch (e: RemoteException) {
      e.printStackTrace()
      promise.reject(e)
    }
  }

  @ReactMethod
  fun getConfig(promise: Promise) {
    var serverList = MmkvManager.decodeServerList()
    try {
      Log.i("serverlisttt", serverList.toString())

      promise.resolve(null)
    } catch (e: RemoteException) {
      promise.reject(e)
    }
  }

  @ReactMethod
  fun getStatus(promise: Promise) {
    try {
//      MessageUtil.sendMsg2Service(reactContext.applicationContext, AppConfig.MSG_MEASURE_DELAY, "")
      promise.resolve(null)
    } catch (e: RemoteException) {
      promise.reject(e)
    }
  }


  companion object {
    const val NAME = "V2rayModule"
  }
}
