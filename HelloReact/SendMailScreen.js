import React, { Component } from 'react';
import { StackNavigator } from 'react-navigation';
import { View, Text, Button,Linking,TextInput } from 'react-native';


export class SendMailScreen extends Component<{}> {

	constructor(props) {
	    super(props);
	    this.state = {
	    	quotetext: '',
	    	email: ''
	    };
	 }

	sendMailButtonClick(){
		url='mailto:'
		url+=this.state.email;
		url+='?subject=Quote gift&body=';
		url+=this.state.quotetext;
	 	Linking.openURL(url);
	}

	quoteTextChanged(value){
		this.setState({quotetext:value});
	}

	emailTextChanged(value){
		this.setState({email:value});
	}

	render() {
	    return (
	      <View>
	        <Text>Send mail </Text>
	        <Text>Quote </Text>
	        <TextInput
	          style={{height: 40}}
	          placeholder="Write your quote here!"
	          onChangeText={(text) => this.quoteTextChanged(text)}
	        />
	        <Text>E-mail </Text>
	        <TextInput
	          style={{height: 40}}
	          placeholder="Enter the email address!"
	          onChangeText={(text) => this.emailTextChanged(text)}
	        />
	        <Text>{this.state.quotetext}</Text>
	        
	        <Button
	        	onPress={this.sendMailButtonClick.bind(this)}
			  	title="Send e-mail"
			  	color="#841584"
			  	accessibilityLabel="Learn more about this purple button"
			/> 
	      </View> 
	    );
	}
}

