import React from 'react';

class DogImageComponent extends React.Component
{
    render(){
        return (
            <img src = {this.props.stateObj.imgUrl} alt = {this.props.stateObj.breed}/>
        );
    }
}

export default DogImageComponent;