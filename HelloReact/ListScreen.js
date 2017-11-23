import React, { Component } from 'react';
import { StackNavigator } from 'react-navigation';
import { View, Text, Button,ListView,StyleSheet,TouchableOpacity } from 'react-native';

const quotes=[
	{quote:'Don’t regret the past, just learn from it.'},
	{quote:'Life is trying things to see if they work.'},
	{quote:'It does not matter how slowly you go as long as you do not stop.'},
	{quote:'Wherever you go, go with all your heart.'},
	{quote:'Everything you can imagine is real.'},
	{quote:'Don’t wait. The time will never be just right.'},
	{quote:'Men are born to succeed, not fail.'},
	{quote:'Whatever you are, be a good one.'},
]

export class ListScreen extends Component<{}> {

	constructor(props){
		super(props);

		 const ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});

		this.state={
			navigation:this.props.navigation.state.params.navigation,
			dataSource: ds.cloneWithRows(quotes),
			oldQuote:this.props.navigation.state.params.oldQuote,
			newQuote:this.props.navigation.state.params.newQuote,
		}

	}

	showDetails(quote){
		this.state.navigation.navigate('Details',{ navigation:this.state.navigation, quote: quote.quote });

	}

	renderRow(quote,sectionId,rowId,highlightRow){
		return(
			<TouchableOpacity
				underlayColor="blue"
				onPress={()=>this.showDetails(quote)}
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
	        	<Text>Hello, </Text>
	        	<Text>{this.props.navigation.state.params.user}</Text>
	        	<ListView
		        	dataSource={this.state.dataSource}
		        	renderRow={this.renderRow.bind(this)}
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