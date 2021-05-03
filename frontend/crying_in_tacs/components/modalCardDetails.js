import React, { useState, useEffect } from 'react';
import { Button, Modal, Container, Row, Col, Image } from 'react-bootstrap';
import CardSelect from './Card'
import styles from '../styles/Card.module.css'


export default function ModalCardDetails({ selectedCard }) {
  const [show, setShow] = useState(false);

  const handleOpen = () => setShow(true);
  const handleClose = () => setShow(false);

  return (
    <>
      <Button variant="primary" onClick={handleOpen}>Details</Button>
      <div style={{
        backgroundColor: "rgba(0,0,0,0) !important",
        border: "none !important"
      }}>
        <Modal 
          id="modal"
          show={show}
          onHide={handleClose}
          keyboard={false}
          backdropClassName={styles['modal-backdrop']}
        >
          <Modal.Body >
            <CardSelect cardSelected={selectedCard} />
          </Modal.Body>

        </Modal>
      </div>
    </>
  );
}