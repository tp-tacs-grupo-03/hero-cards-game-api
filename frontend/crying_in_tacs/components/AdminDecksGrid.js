import React, { useState, useEffect } from 'react';
import { DataGrid } from '@material-ui/data-grid';
import superheroes from '../superheroes'
import ModalCardDetails from './modalCardDetails'

import { Button, Modal, Container, Row, Col, Image, Form, Spinner } from 'react-bootstrap';

import { useFetchTacsApi } from '../hooks/useFetchTacsApi';

const cardColumns = [
  { field: "id", hide: true },
  { field: "name", headerName: "Name", width: 140 },
  {
    field: "",
    headerName: "Action",
    disableClickEventBubbling: true,
    renderCell: (params) => {
      return <ModalCardDetails selectedCard={params.row} />;
    }
  }
];

export default function AdminDecksGrid({ deck }) {
  const fetchTacsApi = useFetchTacsApi();
  const [selectedRows, setSelectedRows] = useState([])
  const [name, setName] = useState(deck ? deck.name : "")
  const [progressBar, setProgressBar] = useState(false)
  const [enableSave, setEnableSave] = useState(false)

  const setSelectionModel = (model) => {
    setSelectedRows(model);
    setEnableSave(model.length > 0 && name.length > 0);
  }

  const enterName = (value) => {
    setName(value);
    setEnableSave(selectedRows.length > 0 && value.length > 0);
  }

  const saveNewDeck = async () => {

    setProgressBar(true);

    let requestBody = {
      name,
      cards: selectedRows
    }
    
    const data = await (deck ? fetchTacsApi(`decks/${deck.id}/cards`, "PUT", requestBody) : fetchTacsApi("decks", "POST", requestBody));

    console.log(data)

    setProgressBar(false);
  }


  return (
    <Container fluid>
      <Row className="justify-content-md-left">
        <Col md={{ span: 10 }}>
          <Form.Control type="text" value={name} placeholder="Enter deck name..." onChange={(event) => enterName(event.target.value)} />
        </Col>
        <Col md={{ span: 2 }}>
          <Button variant="primary" block onClick={() => saveNewDeck()} disabled={!enableSave}>  Save </Button>
        </Col>
      </Row>

      <Row className="justify-content-md-left" style={{ paddingTop: "10px" }}>
        <Col md={12}>
          {progressBar && <Spinner animation="border" />}
          <DataGrid
            columns={cardColumns}
            rows={superheroes}
            autoHeight={true}
            checkboxSelection={true}
            onSelectionModelChange={
              (newSelection) => { setSelectionModel(newSelection.selectionModel); }
            }
            pagination
            pageSize={11}
            rowsPerPageOptions={[5, 10, 20]}
            selectionModel={deck?.cardsId}
          />
        </Col>
      </Row>


    </Container>
  );
}



