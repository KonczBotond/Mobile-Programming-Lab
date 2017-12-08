import React, { Component } from 'react';
import { StackNavigator } from 'react-navigation';
import { Alert,View, Text, Button,ListView,StyleSheet,TouchableOpacity,TextInput,AsyncStorage } from 'react-native';

const quotes=[
	{quote:'Don’t regret the past, just learn from it.'},

]

const ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});

export class ListDetails extends Component<{}> {
	constructor(props){
		super(props);

		this.state={
			navigation:this.props.navigation.state.params.navigation,
			quote:this.props.navigation.state.params.quote,
			rowId:this.props.navigation.state.params.rowId,
			quoteList:quotes,
		}
	}

	componentDidMount () { 
	    this.updateList(); 
	} 

	async updateList(){
		this.setState({ 
	        s:'started' 
	    })
	    
		try {
			const value = await AsyncStorage.getItem('quoteList');
			if (value !== null){
			  	array=JSON.parse(value);

			    this.setState({
			    	quoteList:array,
			    })
			}	
		} catch (error) {
		  // Error retrieving data
		}
	}

	deleteButtonClick(value){
		Alert.alert(
		  'Delete Quote',
		  'Are you sure you want to delete '+this.state.quote,
		  [
		    {text: 'Cancel'},
		    {text: 'OK', onPress: () => this.deleteQuote()},
		  ],
		  { cancelable: false }
		 )
	}

	updateButtonClick(value){
		Alert.alert(
		  'Delete Quote',
		  'Are you sure you want to update to '+this.state.quote,
		  [
		    {text: 'Cancel'},
		    {text: 'OK', onPress: () => this.updateQuote()},
		  ],
		  { cancelable: false }
		 )
	}

	async deleteQuote(value){
		this.state.quoteList.splice(this.state.rowId, 1);
		list=JSON.stringify(this.state.quoteList);
		await AsyncStorage.setItem('quoteList',list);

		this.state.navigation.navigate('List',{ navigation:this.state.navigation});
	}

	async updateQuote(value){
		newQuote={quote:this.state.quote};
		this.state.quoteList[this.state.rowId]=newQuote;
		list=JSON.stringify(this.state.quoteList);
		await AsyncStorage.setItem('quoteList',list);

		this.state.navigation.navigate('List',{ navigation:this.state.navigation});
	}

	quoteTextChanged(value){
		this.setState({quote:value});
	}

	render() {
	    return (
	      	<View>
	        	<TextInput
		            style={{height: 40}}
		            defaultValue={this.state.quote}
           		 	onChangeText={(text) => this.quoteTextChanged(text)}
		        />
		        <Button
			      onPress={() => this.deleteButtonClick()}
			     
			      title="Delete Quote"
			    />
			    <Button
			      onPress={() => this.updateButtonClick()}
			     
			      title="Update Quote"
			    />
	        </View>	        
	    );
	}
}
/*
	showDetails(quote){
		this.state.navigation.navigate('Details',{ navigation:navigation})};

	}*/