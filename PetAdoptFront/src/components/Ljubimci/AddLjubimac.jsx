import React, { useCallback, useEffect, useState } from "react";
import { Button, Col, Form, Row } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import Axios from "../../apis/Axios";

const AddLjubimac = () => {
  const ljubimac = {
    ime: "",
    starost: -1,
    pol: "",
    tezina: "",
    opis: "",
    kategorijaId: -1,
  };

  const [kategorija, setKategorija] = useState([]);
  const [novLjubimac, setNovLjubimac] = useState(ljubimac);
  const [validno, setValidno] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    getKategorije();
  }, []);

  //dodavanje ljubimca
  const dodajLjubimac = () => {
    const dto = {
      ime: novLjubimac.ime,
      starost: novLjubimac.starost,
      pol: novLjubimac.pol,
      tezina: novLjubimac.tezina,
      opis: novLjubimac.opis,
      kategorijaId: novLjubimac.kategorijaId,
    };

    Axios.post("/ljubimci", dto)
      .then((result) => {
        console.log(result);
        stranaLjubimci();
      })
      .catch((error) => {
        console.log("Došlo je do greške u dodavanju životinje");
        console.log(error);
      });
  };

  //preuzimanje kategorija
  const getKategorije = useCallback(() => {
    Axios.get("/kategorije")
      .then((result) => {
        setKategorija(result.data);
        console.log(result);
      })
      .catch((error) => {
        console.log(error);
        alert("Došlo je do greške u preuzimanju kategorija!");
      });
  }, []);

  //select kategorija
  const kategorijaSelect = () => {
    return kategorija.map((m) => {
      return (
        <option key={m.id} value={m.id}>
          {m.naziv}
        </option>
      );
    });
  };

  //validacija
  const validacija = () => {
    if (
      novLjubimac.ime === "" ||
      novLjubimac.starost === "" ||
      novLjubimac.pol === "" ||
      novLjubimac.tezina === "" ||
      novLjubimac.opis === "" ||
      novLjubimac.kategorijaId === ""
    ) {
      setValidno(false);
    } else {
      setValidno(true);
    }
  };

  //popunjavanje forme
  const valueInputChange = (e) => {
    let name = e.target.name;
    let value = e.target.value;

    novLjubimac[name] = value;
    setNovLjubimac(novLjubimac);
    validacija();
  };

  // prelazak na stranu "Ljubimci"
  const stranaLjubimci = () => {
    navigate("/ljubimci");
  };
  return (
    <Row className="d-flex justify-content-center">
      <Col md={6}>
        <Form className="d-grid gap-2">
          <Form.Group>
            <Form.Label>Ime</Form.Label>
            <Form.Control
              name="ime"
              placeholder="Ime ljubimca"
              type="text"
              onChange={(e) => valueInputChange(e)}
            />
          </Form.Group>
          <Form.Group>
            <Form.Label>Starost</Form.Label>
            <Form.Control
              name="starost"
              placeholder="Starost (broj meseci)"
              type="number"
              onChange={(e) => valueInputChange(e)}
            />
          </Form.Group>
          <Form.Group>
            <Form.Label>Pol</Form.Label>
            <Form.Select name="pol" onChange={(e) => valueInputChange(e)}>
              <option value="">Izaberi pol</option>
              <option value={"MUŠKI"}>Muški</option>
              <option value={"ŽENSKI"}>Ženski</option>
            </Form.Select>
          </Form.Group>
          <Form.Group>
            <Form.Label>Težina</Form.Label>
            <Form.Control
              name="tezina"
              placeholder="Težina (kg)"
              type="number"
              onChange={(e) => valueInputChange(e)}
            />
          </Form.Group>
          <Form.Group>
            <Form.Label>Opis</Form.Label>
            <Form.Control
              name="opis"
              placeholder="Opis ljubimca"
              as="textarea"
              rows={3}
              onChange={(e) => valueInputChange(e)}
            />
          </Form.Group>
          <Form.Group>
            <Form.Label>Kategorija</Form.Label>
            <Form.Select
              name="kategorijaId"
              onChange={(e) => valueInputChange(e)}
            >
              <option value="">Izaberi kategoriju</option>
              {kategorijaSelect()}s{" "}
            </Form.Select>
          </Form.Group>
          <Form.Group></Form.Group>
        </Form>
        <Col className="d-flex gap-3  mt-2">
          <Button onClick={() => dodajLjubimac()} disabled={!validno}>
            Kreiraj ljubimca
          </Button>
          <Button variant="warning" onClick={() => stranaLjubimci()}>
            Odustani
          </Button>
        </Col>
      </Col>
    </Row>
  );
};

export default AddLjubimac;
