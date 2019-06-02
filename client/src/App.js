import React from "react";
import {Form, Formik} from "formik";
import "./App.css";
import LabeledField from "./common/LabeledField";
import {Button, Container, Row} from "react-bootstrap";

const App = () => (
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
                return response.text()
            }).then(response => console.log(response))
        }}
    >
        {({isSubmitting}) => (
            <Form>
                <Container>
                    <Row>
                        <LabeledField name="weight" labelText="Вес"/>
                        <LabeledField name="height" labelText="Рост, см"/>
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
);

export default App;
