import React, { useState, useEffect } from 'react';
import { DataGrid } from '@material-ui/data-grid';
import FetchApi from '../../services/FetchApi'
import { Button, Modal, Container, Row, Col, Image, Form, Spinner } from 'react-bootstrap';
import Link from 'next/link'
import AdminDecksGrid from '../../components/AdminDecksGrid';

const cardRows = [
  {
    id: 1,
    name: "Mazo 1",
    cardsId: [1,5,6]
  }
]


export default function decks() {
  const [selectedDeck, setselectedDeck] = useState()

  const deckColumns = [
    { field: "id", hide: true },
    { field: "name", headerName: "Name", width: 140 },
    {
      field: "",
      headerName: "Actions",
      disableClickEventBubbling: true,
      renderCell: (params) => {
        return <Button variant="primary" onClick={() => setselectedDeck(params.row)}> Edit </Button>;
      }
    }
  ];

  const addNewDeck = () => {
        window.location.href = "/decks/newDeck";
  }

  return (
    <Container fluid>
      <Row style={{ padding: "10px" }}>
        <Col md={{ span: 2}}>
          <h1 style={{fontSize:"40px"}}>Decks actuales</h1>
        </Col>
        <Col md={{span: 2, offset: 8}}>
          <Button block variant="primary" onClick={() => addNewDeck()}>Add new deck</Button>
        </Col>
      </Row>

        <Row style={{ padding: "10px" }}>
          <Col md={4}>
            <DataGrid
              columns={deckColumns}
              rows={cardRows}
              autoHeight={true}
              pagination
              pageSize={20}
              rowsPerPageOptions={[5, 10, 20]}
            />
          </Col>
          { selectedDeck &&
          <Col>
            <AdminDecksGrid deck={selectedDeck}></AdminDecksGrid>
          </Col>
          }

        </Row>


    </Container>
  );
}



