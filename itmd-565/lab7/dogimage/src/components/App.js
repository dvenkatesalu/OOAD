import React from 'react';

import DogImageComponent from './DogImage';
import DogSelectComponent from './DogSelect';

class App extends React.Component 
{

  constructor()
  {
      super();
      this.state = {
        imgUrl: '',
        breed: 'husky',
        errorTxt: 'image cannot be loaded',
      };

      this.urlPrefix = 'https://dog.ceo/api/breed/';
      this.urlSuffix = '/images/random';
      this.changeBreed = this.changeBreed.bind(this);
      this.fetchImage = this.fetchImage.bind(this);
      this.updateImage = this.updateImage.bind(this);
  }

  componentDidMount()
  {
    this.state.imgUrl = this.urlPrefix + this.state.breed + this.urlSuffix
    this.fetchImage();
  }

  changeBreed(e)
  {
    this.state.imgUrl = this.urlPrefix + e.target.value + this.urlSuffix;
    this.state.breed = e.target.value;
    this.fetchImage();
  }

  updateImage()
  {
    this.state.imgUrl = this.urlPrefix + this.state.breed + this.urlSuffix
    this.fetchImage();
  }

  fetchImage()
  {
    fetch(this.state.imgUrl).then((response) => {
        return response.json();
    }).then((respJson) => {
        if( respJson.status === 'success' )
        this.setState({
            imgUrl: respJson.message
        });

        else
        this.setState({
        errorTxt: respJson.message
        });
    });
  }

  render() 
  {
    return (
    	<div style={{textAlign: 'left'}}>
    		<h1>{this.state.breed} Dog Image Generator</h1>
    		<h2>Dharanip Priya Venkatesalu - dvenkatesalu@hawk.iit.edu - ITMD-565</h2>
        <p>Please press the button to generate a new random image.</p>
        <DogSelectComponent handleChangeBreed={this.changeBreed}></DogSelectComponent><button onClick={this.updateImage}>Fetch</button>
        <hr/>
        <DogImageComponent stateObj={this.state}/>
    	</div>
    );
  }
}

export default App;