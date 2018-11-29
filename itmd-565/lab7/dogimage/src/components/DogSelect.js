import React from 'react';

class DogSelectComponent extends React.Component{

    render(){
        return(
            <select onChange={this.props.handleChangeBreed}>
                <option value='husky'>Husky</option>
                <option value='labrador'>Labrador</option>
                <option value='beagle'>Beagle</option>
            </select>
        );
    }
}

export default DogSelectComponent;