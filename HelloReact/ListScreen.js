import React, { Component } from 'react';
import { StackNavigator } from 'react-navigation';
import { TouchableOpacity,StyleSheet,View, ListView,Text, Button,Linking,TextInput,AsyncStorage } from 'react-native';


const quotes=[
	{quote:'Don’t regret the past, just learn from it.'},

]

const ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});

export class ListScreen extends Component<{}> {

	constructor(props) {
	    super(props);

	    this.state = {
	    	navigation:this.props.navigation.state.params.navigation,
	    	dataSource: ds.cloneWithRows(quotes),
	    	quote: 'original quote',
	    	author: '',
	    	quoteList:quotes,
	    	l:'',
	    	s:'not',
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
			    	s:JSON.stringify(this.state.quoteList),
			    })
			}	
		} catch (error) {
		  // Error retrieving data
		}
		this.setState({
    		s:'add<3',
			dataSource: this.state.dataSource.cloneWithRows(this.state.quoteList),
		})
	}

	showDetails(quote,rowId){
		this.state.navigation.navigate('Details',{ navigation:this.state.navigation, quote: quote.quote,rowId:rowId });

	}

	renderRow(quote,sectionId,rowId,highlightRow){
		return(
			<TouchableOpacity
				underlayColor="blue"
				onPress={()=>this.showDetails(quote,rowId)}
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
	        <Text>Your quotes: </Text>

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