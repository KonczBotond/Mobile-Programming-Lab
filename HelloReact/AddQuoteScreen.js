import React, { Component } from 'react';
import { StackNavigator } from 'react-navigation';
import {Alert, TouchableOpacity,StyleSheet,View, ListView,Text, Button,Linking,TextInput,AsyncStorage } from 'react-native';


const quotes=[
	{quote:'Don’t regret the past, just learn from it.'},

]

const ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});

export class AddQuoteScreen extends Component<{}> {

	constructor(props) {
	    super(props);

	    this.state = {
	    	dataSource: ds.cloneWithRows(quotes),
	    	quote: 'original quote',
	    	author: '',
	    	quoteList:[],
	    }
	 }

	componentDidMount () { 
	    this.updateList(); 
	} 

	async updateList(){
	    
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
		this.setState({
			dataSource: this.state.dataSource.cloneWithRows(this.state.quoteList),
		})
	}

	async addQuote(){
		q={quote:this.state.quote};
		this.state.quoteList.push(q); 
		list=JSON.stringify(this.state.quoteList);
	    await AsyncStorage.setItem('quoteList', list); 

	   	this.updateList();
	}

	addQuoteButtonClick(){

	   	Alert.alert(
		  'Add Quote',
		  'Are you sure you want to add '+this.state.quote,
		  [
		    {text: 'Cancel'},
		    {text: 'OK', onPress: () => this.addQuote()},
		  ],
		  { cancelable: false }
		 )

	}

	quoteTextChanged(value){
		this.setState({quote:value});
	}

	authorTextChanged(value){
		this.setState({author:value});
	}

	renderRow(quote,sectionId,rowId,highlightRow){
		return(
			<TouchableOpacity
				underlayColor="blue"
				//onPress={()=>this.showDetails(quote)}
			>
				<View style={styles.row}>
					<Text style={styles.rowText}>{quote.quote}</Text>
				</View>
			</TouchableOpacity>
		);
	}

	render() {
	    return (
	      <View>
	        <Text>Add Quote </Text>
	        <Text>{this.state.t} </Text>
	        <Text>Quote </Text>
	        <TextInput
	          style={{height: 40}}
	          placeholder="Write your quote here!"
	          onChangeText={(text) => this.quoteTextChanged(text)}
	        />
	        <Text>Author </Text>
	        <TextInput
	          style={{height: 40}}
	          placeholder="Enter the email address!"
	          onChangeText={(text) => this.authorTextChanged(text)}
	        />
	        
	        <Button
	        	onPress={this.addQuoteButtonClick.bind(this)}
			  	title="Add Quote"
			  	color="#841584"
			/> 
	      </View> 
	    );
	}
}

const styles=StyleSheet.create({
	row:{
		flexDirection:'row',
		justifyContent:'center',
		padding:10,
		backgroundColor: '#f4f4f4',
		marginBottom:3
	},
	rowText:{
		flex:1
	}
});