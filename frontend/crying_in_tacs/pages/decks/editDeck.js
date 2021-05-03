import React, { useState, useEffect } from 'react';
import { DataGrid } from '@material-ui/data-grid';
import FetchApi from '../../services/FetchApi'
import ModalCardDetails from '../../components/modalCardDetails'

import useFetchTacsApi from '../../hooks/useFetchTacsApi';

import { Button, Modal, Container, Row, Col, Image, Form, Spinner } from 'react-bootstrap';


const fetchApi = new FetchApi();

const cardColumns = [
    { field: "id", hide: true },
    { field: "name", headerName: "Name", width: 200 },

    // { field: "powerstats.intelligence", headerName: "Intelligence", width: 140, valueGetter: (params) => `${params.getValue('powerstats').intelligence}` },
    // { field: "powerstats.strength", headerName: "Strength", width: 140, valueGetter: (params) => `${params.getValue('powerstats').strength}` },
    // { field: "powerstats.speed", headerName: "Speed", width: 140, valueGetter: (params) => `${params.getValue('powerstats').speed}` },
    // { field: "powerstats.durability", headerName: "Durability", width: 140, valueGetter: (params) => `${params.getValue('powerstats').durability}` },
    // { field: "powerstats.power", headerName: "Power", width: 140, valueGetter: (params) => `${params.getValue('powerstats').power}` },
    // { field: "powerstats.combat", headerName: "Combat", width: 140, valueGetter: (params) => `${params.getValue('powerstats').combat}` },
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
    const [rows, setstate] = useState([])
    const [selectedRows, setSelectedRows] = useState([])
    const [name, setName] = useState("")
    const [progressBar, setProgressBar] = useState(false)
    const [enableSave, setEnableSave] = useState(false)

    useEffect(() => {
        setName("nombre del mazo elegido");

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

    const saveNewDeck = () => {
        debugger;
        let requestParam = {
            nombre: name,
            cards: selectedRows
        }
        console.log(requestParam);

        setProgressBar(true);

        useFetchTacsApi().fetchPrelloApi("http://localhost:8080/api/decks",'POST',requestParam).then(() => {
            setProgressBar(false);
        })

    }
    //promiseRows().then;


    return (
        <Container fluid>

            <Row className="justify-content-md-left" style={{ padding: "10px" }}>
                <Col md={{ span: 2 }}>
                    <Form.Control type="text" placeholder="Enter deck name..."
                        onChange={(event) => enterName(event.target.value)}
                        value={name}
                    />
                </Col>
                <Col md={{ span: 1}}>
                    <Button variant="primary" block onClick={() => saveNewDeck()} disabled={!enableSave}>  Save </Button>
                </Col>
            </Row>

            <Row className="justify-content-md-left" style={{ padding: "10px" }}>
                <Col md={3}>
                    {progressBar && <Spinner animation="border" disable />}
                    <DataGrid
                        columns={cardColumns}
                        rows={rows}
                        autoHeight={true}
                        checkboxSelection={true}
                        onSelectionModelChange={
                            (newSelection) => { setSelectionModel(newSelection.selectionModel); }
                        }
                        pagination
                        pageSize={10}
                        rowsPerPageOptions={[5, 10, 20]}
                    />
                </Col>
            </Row>


        </Container>
    );
}