import React from "react";
import {ErrorMessage, Field} from "formik";
import "../App.css";
import {Col, Container, Row} from "react-bootstrap";

const SleepOptionField = (props) => {
    return (
        <Container style={{marginBottom: "10px"}}>
            <Row className="justify-content-md-center">
                <Col sm={6} style={{textAlign: "right"}}>
                    <label htmlFor={props.name}>{props.labelText}</label>
                </Col>
                <Col sm={6}>
                    <Field component="select" id={props.name} name={props.name} style={{width: "15%"}}>
                        <option value="" selected>-</option>
                        <option value="3" defaultValue>1-5</option>
                        <option value="7">6-8</option>
                        <option value="10">9+</option>
                    </Field>
                </Col>
            </Row>
            <Row className="justify-content-md-center text-danger">
                <ErrorMessage name={props.name} component="div"/>
            </Row>
        </Container>
    );
};

export default SleepOptionField;
