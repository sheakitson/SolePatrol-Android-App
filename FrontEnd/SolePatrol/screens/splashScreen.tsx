import React from "react"
import {
  Spinner,
  VStack,
  Heading,
  Center,
  NativeBaseProvider,
  Progress
} from "native-base"

import {
  useColorMode,
  Button,
  HStack,
  Avatar,
  useColorModeValue,
  Text
} from 'native-base';
import { ActivityIndicator, Image } from "react-native";


export const Example = () => {
  return (
    <VStack space={4} alignItems="center" bg={useColorModeValue('#076AE1','#076AE1')}>
      <Heading textAlign="center" mb="10">
    <Center flex={1}>
    <Image source={require('../images/logo.png')}  style={{ width: 200, height: 200 }}/>
    </Center>
    <Progress colorScheme="emerald" value={55} />
      </Heading>
    </VStack>
  )
}

export default () => {
    return (
      <NativeBaseProvider>
        <Center px="0" bg={useColorModeValue('#076AE1','#076AE1')}>
          <Example />
        </Center>
      </NativeBaseProvider>
    )
  }