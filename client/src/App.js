import React, {Component} from "react";
import {Form, Formik} from "formik";
import "./App.css";
import LabeledField from "./common/LabeledField";
import {Button, Container, Modal, Row} from "react-bootstrap";
import {getRecommendationMessage} from "./common/recommendation-message-holder";
import ReactHtmlParser from "react-html-parser";
import * as Yup from "yup";

const Schema = Yup.object()
    .shape({
               height: Yup.number()
                   .positive("Рост не может быть отрицательным"),
               weight: Yup.number()
                   .positive("Вес не может быть отрицательным"),
               sleepDuration: Yup.number()
                   .positive("Время сна не может быть отрицательным"),
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
                        fetch("http://localhost:8080/recommendation", {
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
                                    <LabeledField name="weight" labelText="Вес, кг"/>
                                    <LabeledField name="height" labelText="Рост, см"/>
                                    <LabeledField name="sleepDuration" labelText="Время сна, часы"/>
                                    <LabeledField name="physicalFrequency"
                                                  labelText="Количество физической активности, (0-5)"/>
                                    <LabeledField name="physicalState" labelText="Физическое состояние, (0-5)"/>
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
                        <Modal.Title>Рекоммендации</Modal.Title>
                    </Modal.Header>

                    <Modal.Body>
                        {this.state.recommendations.map((rec, i) => {
                            let separator = i !== (this.state.recommendations.length - 1) ? "<hr>" : "";
                            return ReactHtmlParser(rec + (separator))
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
