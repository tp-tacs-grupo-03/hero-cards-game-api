import { StylesProvider } from '@material-ui/styles';
import React, { useState, useEffect } from 'react';
import styles from '../styles/Card.module.css'

import { Button, Modal, Container, Row, Col, Image, Spinner } from 'react-bootstrap';
import { useFetchTacsApi } from '../hooks/useFetchTacsApi';

export default function CardSelect({ cardSelected }) {

    const [cardData, setCardData] = useState()
    const fetchTacsApi = useFetchTacsApi()

    useEffect(() => {

        const fetchCardData = async () => {
            const data = await fetchTacsApi(`cards/${cardSelected.id}`)
            await setCardData(data)
        }

        fetchCardData()

        const card = document.getElementById("card");

        let click = false;
        let clientX = 0;

        document.addEventListener("mousedown", (e) => {
            clientX = e.clientX;
            click = true;
        });

        document.addEventListener("mouseup", (e) => {
            click = false;
        });
        document.addEventListener("mousemove", (e) => {
            if (click) {
                const rotateY = e.clientX - clientX;

                card.style.transform = `perspective(50rem) rotateX(5deg) rotateY(${rotateY}deg)`;
            }
        });

        document.addEventListener("touchstart", (e) => {
            clientX = e.targetTouches[0].clientX;
            click = true;
        });
        document.addEventListener("touchend", (e) => {
            click = false;
        });
        document.addEventListener("touchmove", (e) => {
            if (click) {
                const rotateY = e.targetTouches[0].clientX - clientX;

                card.style.transform = `perspective(50rem) rotateX(5deg) rotateY(${rotateY}deg)`;
            }
        });
    }, [])



    return (
        <div className={styles.card} id="card">
            <div className={styles.front}>
                {
                    cardData ? 
                        <div className={styles.image} style={{ backgroundImage: `url(${cardData.image.url})` }}>
                            <div className={styles.level}>{cardData.cardId}</div>
                        </div>
                    :
                        <div className={styles.image}>
                            <Spinner animation="border"></Spinner>
                        </div>
                        
                }
                {
                    cardData ?
                    <>
                        <div className={styles.title}>{cardData.name}</div>
                    <div className={styles.description}>
                    <Container fluid>
                        <Row className="justify-content-md-center" style={{ paddingTop: "3px" }}>
                            <Col> Intelligence: {cardData.powerStats.intelligence} </Col>
                            <Col> Strength: {cardData.powerStats.strength} </Col>
                        </Row>
                        <Row className="justify-content-md-center" style={{ paddingTop: "3px" }}>
                            <Col> Speed: {cardData.powerStats.speed} </Col>
                            <Col> Durability: {cardData.powerStats.durability} </Col>
                        </Row>
                        <Row className="justify-content-md-center" style={{ paddingTop: "3px" }}>
                            <Col> Power: {cardData.powerStats.power} </Col>
                            <Col> Combat: {cardData.powerStats.combat} </Col>
                        </Row>
                    </Container>
                            </div>
                    </>
                    :
                    <div className={styles.description}>
                        <Spinner animation="border"></Spinner>
                    </div>
                }
            </div>
            <div className={styles.back}>
               
            </div>
        </div>
    );

};

