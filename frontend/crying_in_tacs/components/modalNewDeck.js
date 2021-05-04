import React, { useState, useEffect } from 'react';
import { Button, Modal, Container, Row, Col, Image } from 'react-bootstrap';
import AdminDecksGrid from './AdminDecksGrid'


export default function ModalNewDeck() {
  const [show, setShow] = useState(false);

  const handleOpen = () => setShow(true);
  const handleClose = () => setShow(false);

  return (
    <>
      <Row style={{ padding: "10px" }}>
        <Col md={12}>
          <Button variant="primary" onClick={handleOpen} block>Add new deck</Button>
        </Col>
      </Row>
      <Row style={{ padding: "10px" }}>
        <Col md={12}>
          <Modal
            show={show}
            onHide={handleClose}
            // 
            size="lg">
              <div style={{ 
                backgroundColor: "white",
                border: "1px solid rgba(0,0,0,.2)"
                }}>

            <Modal.Header closeButton>
              <Modal.Title>New Deck</Modal.Title>
            </Modal.Header>

            <Modal.Body>
              <AdminDecksGrid />
            </Modal.Body>
            </div>
          </Modal>
        </Col>
      </Row>
    </>
  );
}