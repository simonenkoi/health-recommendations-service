import React from "react";
import {Field} from "formik";
import "../App.css";
import {Col, Container, Row} from "react-bootstrap";

const LabeledField = (props) => {
    return (
        <Container>
            <Row className="justify-content-md-center">
                <Col sm={2}><label htmlFor={props.name}>{props.labelText}</label></Col>
                <Col sm={4}><Field type="number" id={props.name} name={props.name}/></Col>
            </Row>
        </Container>
    );
}

export default LabeledField;
