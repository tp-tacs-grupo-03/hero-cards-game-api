import React, { useState, useEffect } from 'react';
import { DataGrid } from '@material-ui/data-grid';
import FetchApi from '../../services/FetchApi'
import ModalCardDetails from '../../components/modalCardDetails'

import { Button, Modal, Container, Row, Col, Image, Form, Spinner } from 'react-bootstrap';

import {useFetchTacsApi} from '../../hooks/useFetchTacsApi';


const fetchApi = new FetchApi();

const cardColumns = [
  { field: "id", hide: true },
  { field: "name", headerName: "Name", width: 140 },

//   { field: "powerstats.intelligence", headerName: "Intelligence", width: 140, valueGetter: (params) => `${params.getValue('powerstats').intelligence}` },
//   { field: "powerstats.strength", headerName: "Strength", width: 140, valueGetter: (params) => `${params.getValue('powerstats').strength}` },
//   { field: "powerstats.speed", headerName: "Speed", width: 140, valueGetter: (params) => `${params.getValue('powerstats').speed}` },
//   { field: "powerstats.durability", headerName: "Durability", width: 140, valueGetter: (params) => `${params.getValue('powerstats').durability}` },
//   { field: "powerstats.power", headerName: "Power", width: 140, valueGetter: (params) => `${params.getValue('powerstats').power}` },
//   { field: "powerstats.combat", headerName: "Combat", width: 140, valueGetter: (params) => `${params.getValue('powerstats').combat}` },
  {
    field: "",
    headerName: "Action",
    disableClickEventBubbling: true,
    renderCell: (params) => {
      return <ModalCardDetails selectedCard={params.row} />;
    }
  }
];





export default function AdminDecksGrid() {
  const promiseRows = async () => fetchApi.request('GET', "https://bc66ad40-3165-4f81-825c-2d0eafc1c6e1.mock.pstmn.io/api");
  const fetchTacsApi = useFetchTacsApi();
//   const postRequest = async (requestParam) => fetchApi.requestBodyParams('POST',"http://localhost:8080/api/decks", requestParam);


  const [rows, setstate] = useState([])
  const [selectedRows, setSelectedRows] = useState([])
  const [name, setName] = useState("")
  const [progressBar, setProgressBar] = useState(false)
  const [enableSave, setEnableSave] = useState(false)

  useEffect(() => {
    console.log("se llama");
    promiseRows().then((newRows) => {
      console.log(newRows);
      setstate(newRows.filter(a =>
        a.powerstats.intelligence != "null" &&
        a.powerstats.strength != "null" &&
        a.powerstats.speed != "null" &&
        a.powerstats.durability != "null" &&
        a.powerstats.power != "null" &&
        a.powerstats.combat != "null"
      )
      )
    })
  }, [])

  const setSelectionModel = (model) => {
    setSelectedRows(model);
    setEnableSave(model.length > 0 && name.length > 0);
  }

  const enterName = (value) => {
    setName(value);
    setEnableSave(selectedRows.length > 0 && value.length > 0);
  }

  const saveNewDeck = async () => {
    debugger;
    let requestBody = {
        nombre: name,
        cards: selectedRows
    }
    console.log(requestBody);

    setProgressBar(true);   
    

    var s = await fetchTacsApi("https://superheroapi.com/api/4339222169421762/1",'GET')
    console.log(s)
    debugger;

    // const options = {
    //     method: 'POST',
    //     body: JSON.stringify(requestBody),
    //     headers: {
    //         'Content-Type': "application/json",
    //         'Authorization': 'Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ink4TUprbHhzOVg4Y3dGZEp5OVZHNiJ9.eyJpc3MiOiJodHRwczovL2Rldi1qeDhmeXN2cS51cy5hdXRoMC5jb20vIiwic3ViIjoiZ29vZ2xlLW9hdXRoMnwxMTMwNTM0NTYyNTIyMzI2MjMwNjIiLCJhdWQiOlsiaHR0cHM6Ly90YWNzLjIwMjEuY29tIiwiaHR0cHM6Ly9kZXYtang4ZnlzdnEudXMuYXV0aDAuY29tL3VzZXJpbmZvIl0sImlhdCI6MTYxOTk5Nzc4MywiZXhwIjoxNjIwMDg0MTgzLCJhenAiOiJIOEVYMDBEZWd0TmJzcDJQZFVDWnh2TnV1MVJJNnZkWiIsInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwifQ.yvne_7dgau3qismMQ8LKMUT-jZqn0VM9PZo-bFFAhRkOJx39Wr32svA5lRwc3zfQjA_zbddy6MSduPcpi7XtG1tYSRUBHyPHk-H9DGo538JxEqbc_pOvwH3hPEJMNJzNLhoTu68xh-EtrEoOrZ_xlpNjx5lWeX6rtvQC36eDuzzqlBjBzkxjF_25Ubl-5NWMjPQ1NFfhPGallV2uJ1yUbAEwLKv_ve-ev7Ni2uEaRUUapmYZFyEOMh4LE5jSSVGD_dyxxIKlgUVnGIqCS_cE577S5hx3ryJlP1StVHslHFh4TCJbQ66qU-Iqbwg5yt3qvXTX_PPsyN6e7YM_yinlzA'
    //     }          

    // };


    // const data = await fetch("http://localhost:8080/api/decks", options)
    const response = await data.json()

    console.log(response);
    response.then((response) => {
        console.log(response)
        setProgressBar(false);
    })

}


  return (
    <Container fluid>

      <Row className="justify-content-md-left" style={{padding:"10px"}}>
      <Col md={{ span: 2 }}>
            <Form.Control type="text" placeholder="Enter deck name..." onBlur={(event) => enterName(event.target.value)} />
        </Col>
        <Col md={{span: 1 }}>
          <Button variant="primary" block  onClick={() => saveNewDeck()} disabled={!enableSave}>  Save </Button>
        </Col>
      </Row>

      <Row className="justify-content-md-left" style={{padding:"10px"}}>
        <Col md={3}>
        {progressBar && <Spinner animation="border" disable/>}
            <DataGrid
              columns={cardColumns}
              rows={rows}
              autoHeight={true}
              checkboxSelection={true}
              onSelectionModelChange={
                (newSelection) => { setSelectionModel(newSelection.selectionModel); }
              }
              pagination 
              pageSize = {20}
              rowsPerPageOptions={[5, 10, 20]}
            />
        </Col>
      </Row>
      

    </Container>
  );
}



