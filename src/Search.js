import react, { Component } from 'react';
import axios, { post } from 'axios';
import ReactFileReader from 'react-file-reader';
import Button from 'react-bootstrap/Button';
import './App.css';
class SubmitComponent extends Component{

  
  constructor(props) {
    super(props);
    this.state ={
      file:null,
      valuesSearch :""
    }
    this.onFormSubmit = this.onFormSubmit.bind(this)
    this.onChange = this.onChange.bind(this)
    this.fileUpload = this.fileUpload.bind(this)
    this.submitsearch=this.submitsearch.bind(this);
    this.handleChangeSearch=this.handleChangeSearch.bind(this);
  }

  submitsearch(e){
    // alert(this.state.valuesSearch);
    axios.get("http://localhost:8080/api/CV/search?mots=Java").then((res)=>{
      if(res){
        console.log(res.data);
      }else{
        console.log("abouche")
      }
    });
  }

  handleChangeSearch(e){
    this.setState({valuesSearch:e.target.value});
  }

  onFormSubmit(e){
    e.preventDefault()
    this.fileUpload(this.state.file).then((response)=>{
      console.log(response) 
      alert("cv envoyer avec succes");
      
    })
  }

  onChange(e) {
    this.setState({file:e.target.files[0]})
  }

  fileUpload(file){
    const url = 'http://localhost:8080/api/CV/add';
    const formData = new FormData();
    formData.append('file',file)
    const config = {
        headers: {
            'content-type': 'multipart/form-data'
        }
    }
    return  post(url, formData,config)
  }

  render() {
    return (
      <div> 
       <h1>Welcom TO Our CVThèque</h1>
       <div className="monbloc"> 
      <form onSubmit={this.onFormSubmit}>
        <h1>Upload Your CV</h1>
        <input type="file" accept={".pdf , .docx" } onChange={this.onChange} />
        <Button type="submit" variant="dark">Upload</Button>
      </form>
      </div>
       
       <div className="monbloc2">
       <form onSubmit={this.submitsearch}>
       <h1>Search CV</h1>
       <label>
       <input type="text" value={this.state.valuesSearch} onChange={this.handleChangeSearch} name="name" />
      </label>
      <Button type="submit" variant="dark" >Search</Button>
      </form>
       </div>
      
      </div>
   )
  }
 


}

export default SubmitComponent;
