import React from "react";
import {ErrorMessage, Field} from "formik";
import "../App.css";
import {Col, Container, Row} from "react-bootstrap";

const LabeledNumberField = (props) => {
    return (
        <Container style={{marginBottom: "10px"}}>
            <Row className="justify-content-md-center">
                <Col sm={6} style={{textAlign: "right"}}>
                    <label htmlFor={props.name}>{props.labelText}</label>
                </Col>
                <Col sm={6}>
                    <Field type="number" id={props.name} name={props.name} style={{width: "100%"}}/>
                </Col>
            </Row>
            <Row className="justify-content-md-center text-danger">
                <ErrorMessage name={props.name} component="div"/>
            </Row>
        </Container>
    );
};

export default LabeledNumberField;
