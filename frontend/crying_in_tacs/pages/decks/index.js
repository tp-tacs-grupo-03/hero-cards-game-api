import React, { useState, useEffect } from 'react';
import { DataGrid } from '@material-ui/data-grid';
import FetchApi from '../../services/FetchApi'
import { Button, Modal, Container, Row, Col, Image, Form, Spinner } from 'react-bootstrap';
import Link from 'next/link'

const deckColumns = [
  { field: "id", hide: true },
  { field: "name", headerName: "Name", width: 140 },
  {
    field: "",
    headerName: "Actions",
    disableClickEventBubbling: true,
    renderCell: (params) => {
      const changeRoute = () => window.location.href = "/decks/editDeck?id=" + params.id;
      return <Button variant="primary" onClick={() => changeRoute()}> Edit </Button>;
    }
  }
];

const cardRows = [
  {
    id: 1,
    name: "Mazo 1"
  }
]


export default function AdminDecksGrid() {
  const [rows, setRows] = useState(cardRows);
  const [name, setName] = useState("")
  const [progressBar, setProgressBar] = useState(false)
  const [selectedRows, setSelectedRows] = useState([])

  const setSelectionModel = (model) => {
    setSelectedRows(model);
  }

  const addNewDeck = () => {
        window.location.href = "/decks/newDeck";
  }

  return (
    <Container fluid>
      <Row style={{ padding: "10px" }}>
        <Col md={{span: 1, offset: 11}}>
          <Button variant="primary" onClick={() => addNewDeck()}>Add new deck</Button>
        </Col>
      </Row>

      <Row style={{ padding: "10px" }}>
        <Col md={4}>
          {progressBar && <Spinner animation="border" disable />}
          <DataGrid
            columns={deckColumns}
            rows={cardRows}
            autoHeight={true}
            onSelectionModelChange={
              (newSelection) => { setSelectionModel(newSelection.selectionModel); }
            }
            pagination
            pageSize={20}
            rowsPerPageOptions={[5, 10, 20]}
          />
        </Col>
      </Row>


    </Container>
  );
}



