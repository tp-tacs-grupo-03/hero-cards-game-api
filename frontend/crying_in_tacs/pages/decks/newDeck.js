import React, { useState, useEffect } from 'react';
import { DataGrid } from '@material-ui/data-grid';
import FetchApi from '../../services/FetchApi'
import superheroes from '../../superheroes'
import ModalCardDetails from '../../components/modalCardDetails'

import { Button, Modal, Container, Row, Col, Image, Form, Spinner } from 'react-bootstrap';

import { useFetchTacsApi } from '../../hooks/useFetchTacsApi';

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

export default function AdminDecksGrid() {
  const fetchTacsApi = useFetchTacsApi();
  const [selectedRows, setSelectedRows] = useState([])
  const [name, setName] = useState("")
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

    let requestBody = {
      nombre: name,
      cards: selectedRows
    }
    console.log(requestBody);
    setProgressBar(true);
  }


  return (
    <Container fluid>
      <Row className="justify-content-md-left" style={{ padding: "10px" }}>
        <Col md={{ span: 2 }}>
          <Form.Control type="text" placeholder="Enter deck name..." onBlur={(event) => enterName(event.target.value)} />
        </Col>
        <Col md={{ span: 1 }}>
          <Button variant="primary" block onClick={() => saveNewDeck()} disabled={!enableSave}>  Save </Button>
        </Col>
      </Row>

      <Row className="justify-content-md-left" style={{ padding: "10px" }}>
        <Col md={3}>
          {progressBar && <Spinner animation="border" disable />}
          <DataGrid
            columns={cardColumns}
            rows={superheroes}
            autoHeight={true}
            checkboxSelection={true}
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



