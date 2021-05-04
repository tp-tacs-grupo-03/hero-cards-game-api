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

export default function AdminDecksGrid({ initialdeck, closeNewDeckModal }) {
  const fetchTacsApi = useFetchTacsApi();

  const [deck, setDeck] = useState(initialdeck ? initialdeck : null)

  const [progressBar, setProgressBar] = useState(false)
  const [enableSave, setEnableSave] = useState(false)

  
  if (deck && deck.id != initialdeck.id) setDeck(initialdeck)


  const setSelectionModel = (model) => {
    setDeck({cardIds: model.map(a=>String(a)), name: deck.name, id: deck.id});
    setEnableSave(model.length > 0 && deck.name?.length > 0);
  }

  const enterName = (value) => {
    setDeck({cardIds: deck.cardIds, name: value, id: deck.id})
    setEnableSave(deck.cardIds?.length > 0 && value.length > 0);
  }

  const saveNewDeck = async () => {
    setProgressBar(true);
    const data = deck.id ? await fetchTacsApi(`decks/${deck.id}/cards`, "PUT", {cards: deck.cardIds, id: deck.id , name: deck.name}) : await fetchTacsApi("decks", "POST", {cards: deck.cardIds, name: deck.name});
    console.log(data);
    setProgressBar(false);
    closeNewDeckModal ? closeNewDeckModal() : undefined;
  }



  return (
    
    <Container fluid>
      <Row className="justify-content-md-left">
        <Col md={{ span: 10 }}>
          <Form.Control type="text" value={deck?.name} placeholder="Enter deck name..." onChange={(event) => enterName(event.target.value)} />
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
            pageSize={10}
            rowsPerPageOptions={[5, 10, 20]}
            selectionModel={deck?.cardIds}
          />
        </Col>
      </Row>


    </Container>
  );
}



