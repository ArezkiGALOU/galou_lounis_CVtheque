import react, { Component } from 'react';
import axios, { post } from 'axios';
import ReactFileReader from 'react-file-reader';
import Button from 'react-bootstrap/Button';
import './App.css';
import Affichage from './Affichage';
class SubmitComponent extends Component{

  
  constructor(props) {
    super(props);
    this.state ={
      file:null,
      cvs:[],
      b:true,
      nbcvs : 0,
      current: ""
    }
    this.onFormSubmit = this.onFormSubmit.bind(this)
    this.onChange = this.onChange.bind(this)
    this.fileUpload = this.fileUpload.bind(this)
    this.submitsearch=this.submitsearch.bind(this);
    this.clear=this.clear.bind(this);
  }

  submitsearch (){
 
    axios.get("http://localhost:8080/api/CV/search?mots="+this.refs.txt.value.split(" ")).then((res)=>{
      res.data.map( (c)=> {
        this.addElement(c);
      })
      this.setState({current:"search"})
    });
    this.setState({b:!this.state.b})
  }

  addElement(c){
    this.state.cvs.push(c);

  }
  onFormSubmit(e){
    e.preventDefault()
    this.fileUpload(this.state.file).then((response)=>{
      console.log(response) 
      alert("cv envoyer avec succes");
      
    })
  }
  clear(e){
    this.setState({current : "",cvs:[]})
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
     
       <h1>Search CV</h1>
       <label>
       <input type="text"  ref="txt" name="name" />
      </label>
      <input  type="submit"
                                value=" Search "
                                class="searchsubmit"
                                onClick={this.submitsearch}/>
      <input  type="submit"
                                value=" Clear "
                                class="searchsubmit"
                                onClick={this.clear}/>
       </div>
      
       
        { 
        this.state.current==="search" ?  <ul><h3>La liste des CVs qui match: <br/> </h3>{this.state.cvs.map( (c) => <li><div>ID : {c["id"]}  {c["nom"]} </div></li>
                                                     )} </ul>: <div>
                                                       
                                                      Vous avez pas effectuer de recherche
                                                      
                                                     </div>
          }

        

      </div>
   )
  }
 


}

export default SubmitComponent;


