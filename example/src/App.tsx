import * as React from 'react';
import { Alert, PermissionsAndroid } from 'react-native';

import { StyleSheet, View, Button } from 'react-native';
import {
  importConfig,
  getConfig,
  connect,
  getStatus,
} from 'react-native-v2ray-module';

export default function App() {
  // const requestCameraPermission = async () => {
  //   try {
  //     const granted = await PermissionsAndroid.request(
  //       PermissionsAndroid.PERMISSIONS.POST_NOTIFICATIONS,
  //       {
  //         title: 'Notification promise',
  //         message: 'promise for notification',
  //         buttonNeutral: 'Ask Me Later',
  //         buttonNegative: 'Cancel',
  //         buttonPositive: 'OK',
  //       }
  //     );
  //     if (granted === PermissionsAndroid.RESULTS.GRANTED) {
  //       console.log('You can use the camera');
  //     } else {
  //       console.log('Camera permission denied');
  //     }
  //   } catch (err) {
  //     console.warn(err);
  //   }
  // };
  importConfig(
    'vmess://ew0KICAidiI6ICIyIiwNCiAgInBzIjogInNzaG9jZWFuLWRzYWRhIiwNCiAgImFkZCI6ICJhdTEudjJyYXlzZXJ2LmNvbSIsDQogICJwb3J0IjogIjQ0MyIsDQogICJpZCI6ICIxMjE1M2UwNC1mYThjLTQyZjktYWE0Mi0yZDM4MzQ4Mjc3ZTkiLA0KICAiYWlkIjogIjAiLA0KICAic2N5IjogImF1dG8iLA0KICAibmV0IjogIndzIiwNCiAgInR5cGUiOiAibm9uZSIsDQogICJob3N0IjogImF1MS52MnJheXNlcnYuY29tIiwNCiAgInBhdGgiOiAiL3ZtZXNzIiwNCiAgInRscyI6ICJ0bHMiLA0KICAic25pIjogImF1MS52MnJheXNlcnYuY29tIiwNCiAgImFscG4iOiAiIg0KfQ==',
    ''
  ).then((config) => {
    console.log(config);
  });

  getConfig();

  connect();

  getStatus();
  return (
    <View style={styles.container}>
      {/* <Text>Result: {iconfig}</Text> */}
      {/* <Text>Result: {config}</Text> */}
      <Button
        color="#841584"
        title="Press me"
        onPress={() => Alert.alert('Simple Button pressed')}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
  button: {
    width: 60,
    height: 20,
    marginTop: 10,
  },
});
