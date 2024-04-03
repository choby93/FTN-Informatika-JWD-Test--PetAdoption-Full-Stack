import React, { useCallback, useEffect, useState } from "react";
import { Button, Col, Form, FormCheck, Row } from "react-bootstrap";
import Axios from "../../apis/Axios";
import "../../index.css";
import { useNavigate } from "react-router-dom";
const Ljubimci = () => {
  const zaPretragu = {
    kategorijaId: "",
    pol: "",
    opis: "",
  };

  const [ljubimac, setLjubimac] = useState([]);
  const [kategorija, setKategorija] = useState([]);
  const [pageNo, setPageNo] = useState(0);
  const [totalPage, setTotalPage] = useState(0);
  const [pretraga, setPretraga] = useState(zaPretragu);
  const navigate = useNavigate();

  const admin = window.localStorage["role"] === "ROLE_ADMIN";
  const user = window.localStorage["role"] === "ROLE_KORISNIK";
  const ulogovan = window.localStorage["jwt"];

  useEffect(() => {
    getAllLjubimci(0);
    getAllKategorije();
  }, []);

  //preuzimanje svih zivotinja
  const getAllLjubimci = useCallback((nextPage) => {
    const config = {
      params: {
        kategorijaId: pretraga.kategorijaId,
        pol: pretraga.pol,
        opis: pretraga.opis,
        pageNo: nextPage,
      },
    };

    Axios.get("/ljubimci", config)
      .then((result) => {
        setLjubimac(result.data);
        setPageNo(nextPage);
        setTotalPage(result.headers["total-pages"]);
        console.log(result);
      })
      .catch((error) => {
        console.log(error);
        alert("Greška kod preuzimanja ljubimaca");
      });
  }, []);

  // preuzimanje kategorija
  const getAllKategorije = useCallback(() => {
    Axios.get("/kategorije")
      .then((result) => {
        setKategorija(result.data);
        console.log(result);
      })
      .catch((error) => {
        console.log(error);
        alert("Greška kod preuzimanja ljubimaca");
      });
  }, []);

  //render zivotinja
  const renderLjubimac = () => {
    return ljubimac.map((lj) => {
      return (
        <tr key={lj.id}>
          <td>{lj.ime}</td>
          <td>{lj.starost}</td>
          {admin && ulogovan ? (
            <td>
              <FormCheck
                type="checkbox"
                checked={lj.vakcinisan}
                onChange={() => vakcinacija(lj.id)}
              ></FormCheck>
            </td>
          ) : (
            <td>
              {lj.vakcinisan ? <p>Vakcinisan</p> : <p>Nije Vakcinisan</p>}
            </td>
          )}
          <td>{lj.pol}</td>
          <td>{lj.tezina}</td>
          <td>{lj.opis}</td>
          <td>{lj.kategorijaNaziv}</td>
          <td>
            {admin && ulogovan ? (
              <Button variant="danger" onClick={() => deleteLjubimac(lj.id)}>
                Obriši
              </Button>
            ) : null}
            {user && ulogovan && lj.vakcinisan ? (
              <Button onClick={() => udomljavanje(lj.id)}>Udomi</Button>
            ) : null}
          </td>
        </tr>
      );
    });
  };

  // brisanje ljubimaca
  const deleteLjubimac = (id) => {
    const confirmDelete = window.confirm(
      "Da li ste sigurni da želite da obrišete ljubimca?"
    );

    if (confirmDelete) {
      Axios.delete("/ljubimci/" + id)
        .then((result) => {
          console.log(result);
          osvezi();
        })
        .catch((error) => {
          console.log(error);
          alert("Došlo je do greške u brisanju ljubimca");
        });
    }
  };

  //pretraga ljubimca
  const pretragaLjubimca = (e) => {
    let name = e.target.name;
    let value = e.target.value;

    pretraga[name] = value;
    setPretraga(pretraga);
  };

  //select kategoruju
  const selectKategorija = () => {
    return kategorija.map((k) => {
      return (
        <option key={k.id} value={k.id}>
          {k.naziv}
        </option>
      );
    });
  };

  //vakcinacija
  const vakcinacija = (id) => {
    Axios.put("/ljubimci/" + id + "/vakcinacija")
      .then((result) => {
        console.log(result);
        osvezi();
      })
      .catch((error) => {
        console.log(error);
        alert("Greška kod vakcinacije");
      });
  };

  //udomljavanje

  const udomljavanje = (id) => {
    Axios.post("/ljubimci/" + id)
      .then((result) => {
        console.log(result);
        alert("Uspešno udomljavanje!");
        osvezi();
      })
      .catch((error) => {
        console.log(error);
        alert("Došlo je do greške prilikom udomljavanja");
      });
  };

  // idi na stranu za dodavanje novog ljubimca
  const idiNaDodavanje = () => {
    navigate("/ljubimci/addLjubimac");
  };

  //osvezavanje stranice
  const osvezi = () => {
    window.location.reload();
  };

  return (
    <>
      <Row>
        <Col>
          <Form className="d-flex gap-3">
            <Col>
              <Form.Label>Kategorina ljubimca</Form.Label>
              <Form.Select
                name="kategorijaId"
                onChange={(e) => pretragaLjubimca(e)}
              >
                <option value="">Izaberi kategoriju</option>
                {selectKategorija()}
              </Form.Select>
            </Col>
            <Col>
              <Form.Label>Pol</Form.Label>
              <Form.Select name="pol" onChange={(e) => pretragaLjubimca(e)}>
                <option value="">Izaberi pol</option>
                <option value={"MUŠKI"}>Muški</option>
                <option value={"ŽENSKI"}>Ženski</option>
              </Form.Select>
            </Col>

            <Col>
              <Form.Label>Opis</Form.Label>
              <Form.Control
                name="opis"
                type="text"
                onChange={(e) => pretragaLjubimca(e)}
              ></Form.Control>
            </Col>

            <Col style={{ marginTop: "31px" }}>
              <Button onClick={() => getAllLjubimci(0)}>Pretraga</Button>
              <Button
                style={{ marginLeft: "8px" }}
                variant="warning"
                onClick={() => osvezi()}
              >
                Osveži
              </Button>
            </Col>
          </Form>
        </Col>
      </Row>

      <Row className="d-flexs mt-4 justify-content-between">
        <Col>
          {admin && ulogovan ? (
            <Button variant="success" onClick={() => idiNaDodavanje()}>
              Dodaj ljubimca
            </Button>
          ) : null}
        </Col>

        <Col className="text-end">
          <Button
            variant="info"
            disabled={pageNo == "0"}
            onClick={() => {
              getAllLjubimci(pageNo - 1);
            }}
          >
            Prethodna
          </Button>
          <Button
            variant="info"
            disabled={pageNo + 1 === totalPage || ljubimac.length === 0}
            onClick={() => getAllLjubimci(pageNo + 1)}
          >
            Sledeća
          </Button>
        </Col>
      </Row>
      <Row>
        <Col>
          <table>
            <thead>
              <tr>
                <th>Ime</th>
                <th>Starost(broj meseci)</th>
                <th>Vakcinisan</th>
                <th>Pol</th>
                <th>Težina(kg)</th>
                <th>Opis</th>
                <th>Kategorija</th>
                <th></th>
              </tr>
            </thead>
            <tbody>{renderLjubimac()}</tbody>
          </table>
        </Col>
      </Row>
    </>
  );
};

export default Ljubimci;
