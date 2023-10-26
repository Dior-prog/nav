import { NativeModule, NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-v2ray-module' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const V2rayModule =
  NativeModules.V2rayModule ??
  new Proxy(
    {},
    {
      get() {
        throw new Error(LINKING_ERROR);
      },
    }
  );

export interface V2rayVpnInterface extends NativeModule {
  importConfig: (vpnURL: String, subid: String) => Promise<string>;
  getConfig: () => Promise<null>;
  connect: () => Promise<null>;
  disconnect: () => Promise<null>;
  getStatus: () => Promise<null>;
}

declare module 'react-native' {
  interface NativeModulesStatic {
    V2rayModule: V2rayVpnInterface;
  }
}

// export const eventEmitted: NativeEventEmitter = new NativeEventEmitter(
//   V2rayModule
// );
//send URL via this
export const importConfig = NativeModules.V2rayModule.importConfig;
//get Config
export const getConfig = NativeModules.V2rayModule.getConfig;
//connect
export const connect = NativeModules.V2rayModule.connect;
//disconnect
export const disconnect = NativeModules.V2rayModule.disconnect;
//status
export const getStatus = NativeModules.V2rayModule.getStatus;
