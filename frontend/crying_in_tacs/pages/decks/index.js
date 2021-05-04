import React, { useState, useEffect } from 'react';
import { DataGrid } from '@material-ui/data-grid';
import FetchApi from '../../services/FetchApi'
import { Button, Modal, Container, Row, Col, Image, Form, Spinner } from 'react-bootstrap';
import Link from 'next/link'
import AdminDecksGrid from '../../components/AdminDecksGrid';
import ModalNewDeck from '../../components/modalNewDeck';

import { useFetchTacsApi } from '../../hooks/useFetchTacsApi';

import DeleteForeverSharpIcon from '@material-ui/icons/DeleteForeverSharp';
import BorderColorIcon from '@material-ui/icons/BorderColor';


const cardRows = [
  {
    id: 1,
    name: "Mazo 1",
    cardsId: [1,5,6]
  },
  {
    id: 2,
    name: "Mazo 2",
    cardsId: [2,3,6]
  }
]


export default function decks() {
  const fetchTacsApi = useFetchTacsApi();
  const [selectedDeck, setselectedDeck] = useState()
  const [rows, setRows] = useState(cardRows)

  useEffect(async () => {
      // const responseRows = await fetchTacsApi("decks",'GET');
      // setRows(responseRows);
  }, [])

  const deleteRow = async (row) => {
      await fetchTacsApi(`decks/${row.id}`, "DELETE", null)
      setRows(rows.filter(a=> a.id != row.id ))
  }

  const deckColumns = [
    { field: "id", hide: true },
    { field: "name", headerName: "Name", width: 140 },
    {
      field: "",
      headerName: "Action",
      disableClickEventBubbling: true,
      width: 200, 
      renderCell: (params) => {
        return <>
        <Button variant="primary" onClick={() => setselectedDeck(params.row)}> <BorderColorIcon /> </Button>
        <Button variant="secondary" className="glyphicon glyphicon-trash" onClick={() => deleteRow(params.row)}> 
            <DeleteForeverSharpIcon />
        </Button>
        </>
      }
    }
  ];


  return (
    <Container fluid>
      <Row style={{ padding: "10px" }}>
        <Col md={{ span: 2}}>
          <h1 style={{fontSize:"40px"}}>Current Decks</h1>
        </Col>
        <Col md={{span: 2, offset: 8}}>
            <ModalNewDeck/>
        </Col>
      </Row>

        <Row style={{ padding: "10px" }}>
          <Col md={4}>
            <DataGrid
              columns={deckColumns}
              rows={rows}
              autoHeight={true}
              pagination
              pageSize={10}
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



