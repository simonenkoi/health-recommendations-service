import React, {Component} from "react";
import {Form, Formik} from "formik";
import "./App.css";
import LabeledNumberField from "./common/LabeledNumberField";
import {Button, Container, Modal, Row} from "react-bootstrap";
import {getRecommendationMessage} from "./common/recommendation-message-holder";
import ReactHtmlParser from "react-html-parser";
import * as Yup from "yup";
import SleepOptionField from "./common/SleepOptionField";

const Schema = Yup.object()
    .shape({
               height: Yup.number()
                   .positive("Рост не может быть отрицательным"),
               weight: Yup.number()
                   .positive("Вес не может быть отрицательным"),
               physicalFrequency: Yup.number()
                   .min(0, "Дайте оценку от 0 до 5")
                   .max(5, "Дайте оценку от 0 до 5"),
               physicalState: Yup.number()
                   .min(0, "Дайте оценку от 0 до 5")
                   .max(5, "Дайте оценку от 0 до 5"),
           });

class App extends Component {
    constructor(props, context) {
        super(props, context);
        this.state = {
            modalShowed: false,
            recommendations: []
        }
    }

    hideModal = () => {
        this.setState({modalShowed: false});
    };

    showModal = () => {
        this.setState({modalShowed: true});
    };

    render() {
        return (
            <Container style={{marginTop: "15%"}}>
                <Formik
                    onSubmit={(values, {setSubmitting}) => {
                        fetch("http://localhost:5000/recommendation", {
                            method: "POST",
                            headers: {
                                Accept: "application/json",
                                "Content-Type": "application/json",
                            },
                            body: JSON.stringify(values)
                        }).then(response => {
                            setSubmitting(false);
                            return response.json()
                        }).then(response => {
                            this.showModal();
                            this.setState({recommendations: response.map(message => getRecommendationMessage(message))})
                        })
                    }}
                    validationSchema={Schema}
                >
                    {({isSubmitting}) => (
                        <Form>
                            <Container>
                                <Row>
                                    <LabeledNumberField name="weight" labelText="Вес, кг"/>
                                    <LabeledNumberField name="height" labelText="Рост, см"/>
                                    <SleepOptionField name="sleepDuration" labelText="Время сна, часы"/>
                                    <LabeledNumberField name="physicalFrequency"
                                                        labelText="Количество физической активности, (0-5)"/>
                                    <LabeledNumberField name="physicalState" labelText="Физическое состояние, (0-5)"/>
                                </Row>
                                <Row className="justify-content-md-center">
                                    <Button type="submit" disabled={isSubmitting}>
                                        Submit
                                    </Button>
                                </Row>
                            </Container>
                        </Form>
                    )}
                </Formik>
                <Modal show={this.state.modalShowed} onHide={this.hideModal}>
                    <Modal.Header closeButton>
                        <Modal.Title>Рекомендации</Modal.Title>
                    </Modal.Header>

                    <Modal.Body>
                        {this.state.recommendations.map((rec, i) => {
                            let separator = i !== (this.state.recommendations.length - 1) ? "<hr>" : "";
                            return (
                                <div>
                                    <p><b>{ReactHtmlParser(rec.assessment)}</b></p>
                                    {rec.aftermath ? <p><b>Последствия: </b> {ReactHtmlParser(rec.aftermath)}</p> : ""}
                                    <p><b>Рекомендации: </b>{ReactHtmlParser(rec.recommendation)}</p>
                                    {ReactHtmlParser(separator)}
                                </div>
                            )
                        })}
                    </Modal.Body>

                    <Modal.Footer>
                        <Button onClick={this.hideModal} variant="secondary">Close</Button>
                    </Modal.Footer>
                </Modal>
            </Container>
        );
    }
}

export default App;
