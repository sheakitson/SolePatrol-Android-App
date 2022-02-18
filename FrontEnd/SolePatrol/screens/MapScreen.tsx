import { createNativeStackNavigator } from "@react-navigation/native-stack";
import React, { Component } from "react";
import { Alert, Button,  Dimensions,  Platform, StyleSheet, Text, View } from "react-native";
import { Login } from './login';
import MapView, { PROVIDER_GOOGLE } from 'react-native-maps';


const Stack = createNativeStackNavigator();



export function MapScreen({ navigation }:any) {
    return (
      <View style={styles.container}>
      <MapView
         style={styles.mapcontainer}
         provider={PROVIDER_GOOGLE}
         showsUserLocation
         initialRegion={{
         latitude: 54.584001,
         longitude: -5.941532,
         latitudeDelta: 0.0922,
         longitudeDelta: 0.0421}}
      >
      </MapView>
      </View>

    );
    
  }
  var { width, height } = Dimensions.get('window')
  const styles = StyleSheet.create({
    container: {
      flex: 1,
    },mapcontainer: {
      flex: 1,
      width: width,
      height: height,
    },
  });


  /**
   *   <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
        <Text>Home Screen</Text>

      </View>
           <Button
          title="Go to Details... again"
          onPress={() => navigation.push('Details')}
        />
        <Button title="Go to Home" onPress={() => navigation.navigate('Home')} />
        <Button title="Go back" onPress={() => navigation.goBack()} />
   */