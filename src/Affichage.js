import react, { Component } from 'react';
import axios, { post } from 'axios';
import ReactFileReader from 'react-file-reader';
import Button from 'react-bootstrap/Button';
import './App.css';
class Affichage extends Component{

    constructor(props){
        super(props)

    }

    render(){return (
        <div>
            {this.props.cv}
        </div>
    )}
}

export default Affichage;
