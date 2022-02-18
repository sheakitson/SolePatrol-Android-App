import React from "react";
import { View, Text, Button, TouchableOpacity } from "react-native";
import { styles } from "styled-system";
import { MapScreen } from "./MapScreen";

export function HomeScreen({ navigation }:any) {
  return (
    <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
      <Text>Home Screen</Text>
      <Button
        title="Go to Map"
        onPress={() => navigation.navigate('MapScreen')}
      />
    </View>
  );
}