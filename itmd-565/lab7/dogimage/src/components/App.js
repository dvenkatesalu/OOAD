import React from 'react';
import img from '../images/logo.svg';

class App extends React.Component {
  render() {
    return (
    	<div style={{textAlign: 'center'}}>
    		<h1>Hello World</h1>
    		<img height="500" src={img} />
    	</div>
    );
  }
}

export default App;