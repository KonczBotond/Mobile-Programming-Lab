import React, { Component } from 'react';
import { StackNavigator } from 'react-navigation';
import { View, Text, Button,ListView,StyleSheet,TouchableOpacity,TextInput } from 'react-native';

export class ListDetails extends Component<{}> {


	constructor(props){
		super(props);

		this.state={
			navigation:this.props.navigation.state.params.navigation,
			quote:this.props.navigation.state.params.quote,
		}
	}

	edtiQuote(value){
		this.state.navigation.navigate('List',{ navigation:this.state.navigation, oldQuote: this.state.quote,newQuote:value });
	}

	render() {
	    return (
	      	<View>
	        	<TextInput
		          style={{height: 40}}
		          placeholder={this.state.quote}
		          onChangeText={(text) => this.quoteTextChanged(text)}
		        />
		        <Button
			      onPress={() => edtiQuote()}
			      title="Edit"
			    />
	        </View>	        
	    );
	}
}
/*
	showDetails(quote){
		this.state.navigation.navigate('Details',{ navigation:navigation})};

	}*/