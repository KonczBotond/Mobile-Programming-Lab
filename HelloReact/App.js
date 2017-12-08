import React from 'react';
import { StackNavigator } from 'react-navigation';
import { View, Text, Button } from 'react-native';
import {ListScreen} from './ListScreen';
import {ListDetails} from './ListDetails';
import {SendMailScreen} from './SendMailScreen';
import {AddQuoteScreen} from './AddQuoteScreen';

const HomeScreen = ({ navigation }) => (
  <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
    <Text>Home Screen</Text>
    <Button
      onPress={() => navigation.navigate('SendMail')}
      title="Send email"
      color="#841584"
    />
    <Button
      onPress={() => navigation.navigate('List',{ navigation:navigation, user: 'Boti' })}
      title="List quotes"
      color="#841584"
    />
    <Button
      onPress={() => navigation.navigate('AddQuote',{ navigation:navigation})}
      title="Add Quote"
      color="#841584"
    />
  </View>
);

const RootNavigator = StackNavigator({
  Home: {
    screen: HomeScreen,
    navigationOptions: {
      headerTitle: 'Home',
    },
  },
  SendMail: {
    screen: SendMailScreen,
    navigationOptions: {
      headerTitle: 'SendMail',
    },
  },
  List: {
    screen: ListScreen,
    navigationOptions: {
      headerTitle: 'ListTitle',
    },
  },
  Details: {
    screen: ListDetails,
    navigationOptions: {
      headerTitle: 'Details',
    },
  },
  AddQuote: {
    screen: AddQuoteScreen,
    navigationOptions: {
      headerTitle: 'Add Quote',
    },
  },
});

export default RootNavigator;