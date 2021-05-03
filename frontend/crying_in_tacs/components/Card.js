import { StylesProvider } from '@material-ui/styles';
import React, { useState, useEffect } from 'react';
import styles from '../styles/Card.module.css'

import { Button, Modal, Container, Row, Col, Image } from 'react-bootstrap';
import { useFetchTacsApi } from '../hooks/useFetchTacsApi';

export default function CardSelect({ cardSelected }) {

    const [cardData, setCardData] = useState()
    const fetchTacsApi = useFetchTacsApi()

    useEffect(() => {

        const fetchCardData = async () => {
            const response = await fetch("http://localhost:8080/api/decks")
            console.log(data)
            const data = await response.json()
            console.log(data)
            setCardData(data)
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
        !cardData ? <div></div> : <div className={styles.card} id="card">
            <div className={styles.front}>
                <div className={styles.image} style={{ backgroundImage: `url(${cardData.image.url})` }}>
                    <div className={styles.level}>{cardData.id}</div>
                </div>
                <div className={styles.title}>{cardData.name}</div>
                <div className={styles.description}>
                    <Container fluid>
                        <Row className="justify-content-md-center" style={{ paddingTop: "3px" }}>
                            <Col> Intelligence: {cardData.powerstats.intelligence} </Col>
                            <Col> Strength: {cardData.powerstats.strength} </Col>
                        </Row>
                        <Row className="justify-content-md-center" style={{ paddingTop: "3px" }}>
                            <Col> Speed: {cardData.powerstats.speed} </Col>
                            <Col> Durability: {cardData.powerstats.durability} </Col>
                        </Row>
                        <Row className="justify-content-md-center" style={{ paddingTop: "3px" }}>
                            <Col> Power: {cardData.powerstats.power} </Col>
                            <Col> Combat: {cardData.powerstats.combat} </Col>
                        </Row>
                    </Container>

                </div>

            </div>
            <div className={styles.back}>
               
            </div>
        </div>
    );

};

